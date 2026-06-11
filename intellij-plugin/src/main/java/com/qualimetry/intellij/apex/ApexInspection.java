/*
 * Copyright 2026 SHAZAM Analytics Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qualimetry.intellij.apex;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.codeInspection.options.OptPane;
import com.intellij.codeInspection.options.OptRegularComponent;
import com.intellij.codeInspection.options.OptionController;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import com.qualimetry.sonar.apex.analyzer.checks.CheckList;
import com.qualimetry.sonar.apex.analyzer.visitor.BaseCheck;
import org.jetbrains.annotations.NotNull;
import org.sonar.check.Rule;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * IntelliJ inspection that runs the shared Qualimetry Apex analysis engine.
 * Uses the same {@link CheckList} and {@link BaseCheck} classes as the
 * SonarQube plugin and VS Code extension.
 */
public final class ApexInspection extends LocalInspectionTool {

    private static final Set<String> DEFAULT_PROFILE = Set.copyOf(CheckList.getDefaultRuleKeys());

    /**
     * Per-rule enabled state from the inspection profile (Settings &gt; Editor &gt; Inspections).
     * Used by Qodana and inspection profile UI. Keys are "rule_" + rule key.
     */
    @SuppressWarnings("WeakerAccess")
    public Map<String, Boolean> inspectionProfileRuleEnabled = new LinkedHashMap<>();

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        PsiFile psiFile = holder.getFile();
        String name = psiFile.getName();
        if (name == null || !(name.endsWith(".cls") || name.endsWith(".trigger"))) {
            return PsiElementVisitor.EMPTY_VISITOR;
        }

        ApexAnalyzerSettings settings = ApexAnalyzerSettings.getInstance();
        if (!settings.enabled) {
            return PsiElementVisitor.EMPTY_VISITOR;
        }

        return new PsiElementVisitor() {
            @Override
            public void visitFile(@NotNull PsiFile file) {
                runAnalysis(file, holder, settings);
            }
        };
    }

    private void runAnalysis(@NotNull PsiFile psiFile, @NotNull ProblemsHolder holder,
                             @NotNull ApexAnalyzerSettings settings) {
        Document document = psiFile.getViewProvider().getDocument();
        if (document == null) {
            return;
        }

        String content = document.getText();
        List<BaseCheck> checks = instantiateChecks(settings);
        if (checks.isEmpty()) {
            return;
        }

        for (BaseCheck check : checks) {
            check.clearIssues();
            check.scan(null, content);

            String ruleKey = getRuleKey(check);
            ProblemHighlightType highlightType = resolveHighlightType(ruleKey, check, settings);

            for (BaseCheck.Issue issue : check.getIssues()) {
                registerProblem(psiFile, document, holder, issue, highlightType);
            }
        }
    }

    private List<BaseCheck> instantiateChecks(@NotNull ApexAnalyzerSettings settings) {
        List<BaseCheck> checks = new ArrayList<>();
        for (Class<? extends BaseCheck> clazz : CheckList.getAllChecks()) {
            Rule rule = clazz.getAnnotation(Rule.class);
            if (rule == null) continue;

            String key = rule.key();
            if (!isRuleEnabled(key, settings)) {
                continue;
            }

            try {
                BaseCheck check = clazz.getDeclaredConstructor().newInstance();
                ApexAnalyzerSettings.RuleOverride override = settings.rules.get(key);
                if (override != null) {
                    RulePropertyApplier.apply(check, override.params);
                }
                checks.add(check);
            } catch (Exception e) {
                // skip checks that cannot be instantiated
            }
        }
        return checks;
    }

    private ProblemHighlightType resolveHighlightType(String ruleKey, BaseCheck check,
                                                       ApexAnalyzerSettings settings) {
        String overrideSeverity = settings.getRuleSeverity(ruleKey);
        if (overrideSeverity != null) {
            return SeverityMapper.toHighlightType(overrideSeverity);
        }
        return SeverityMapper.toHighlightType(check.getClass());
    }

    private void registerProblem(@NotNull PsiFile psiFile, @NotNull Document document,
                                  @NotNull ProblemsHolder holder,
                                  @NotNull BaseCheck.Issue issue,
                                  @NotNull ProblemHighlightType highlightType) {
        int line = issue.line();
        if (line < 1 || line > document.getLineCount()) {
            return;
        }

        int lineIndex = line - 1;
        int lineStart = document.getLineStartOffset(lineIndex);
        int lineEnd = document.getLineEndOffset(lineIndex);
        String lineText = document.getText(new TextRange(lineStart, lineEnd));

        int trimStart = 0;
        while (trimStart < lineText.length() && Character.isWhitespace(lineText.charAt(trimStart))) {
            trimStart++;
        }
        int trimEnd = lineText.length();
        while (trimEnd > trimStart && Character.isWhitespace(lineText.charAt(trimEnd - 1))) {
            trimEnd--;
        }
        if (trimStart >= trimEnd) {
            return;
        }

        TextRange range = new TextRange(lineStart + trimStart, lineStart + trimEnd);
        String message = "[Qualimetry] " + issue.message();
        holder.registerProblem(psiFile, message, highlightType, range);
    }

    private String getRuleKey(BaseCheck check) {
        Rule rule = check.getClass().getAnnotation(Rule.class);
        return (rule != null) ? rule.key() : "unknown";
    }

    /**
     * Effective enabled state: settings panel overrides first, then inspection profile options, then default profile.
     */
    private boolean isRuleEnabled(String ruleKey, @NotNull ApexAnalyzerSettings settings) {
        if (settings.rules.containsKey(ruleKey)) {
            return settings.isRuleEnabled(ruleKey);
        }
        String bindId = "rule_" + ruleKey;
        if (inspectionProfileRuleEnabled.containsKey(bindId)) {
            return Boolean.TRUE.equals(inspectionProfileRuleEnabled.get(bindId));
        }
        return DEFAULT_PROFILE.contains(ruleKey);
    }

    @NotNull
    @Override
    public OptPane getOptionsPane() {
        List<OptRegularComponent> components = new ArrayList<>();
        for (Class<? extends BaseCheck> clazz : CheckList.getAllChecks()) {
            Rule rule = clazz.getAnnotation(Rule.class);
            if (rule == null) continue;
            String key = rule.key();
            String label = rule.name() != null && !rule.name().isBlank() ? rule.name() : keyToDescription(key);
            components.add(OptPane.checkbox("rule_" + key, label));
        }
        return OptPane.pane(components.toArray(new OptRegularComponent[0]));
    }

    @NotNull
    @Override
    public OptionController getOptionController() {
        return OptionController.of(
                bindId -> {
                    if (!bindId.startsWith("rule_")) return true;
                    String ruleKey = bindId.substring(5);
                    ApexAnalyzerSettings settings = ApexAnalyzerSettings.getInstance();
                    if (settings.rules.containsKey(ruleKey)) {
                        return settings.isRuleEnabled(ruleKey);
                    }
                    return inspectionProfileRuleEnabled.getOrDefault(bindId, DEFAULT_PROFILE.contains(ruleKey));
                },
                (bindId, value) -> inspectionProfileRuleEnabled.put(bindId, (Boolean) value)
        );
    }

    private static String keyToDescription(String key) {
        if (key == null || key.isEmpty()) return key;
        StringBuilder sb = new StringBuilder();
        boolean cap = true;
        for (char c : key.toCharArray()) {
            if (c == '-') {
                sb.append(' ');
                cap = true;
            } else if (cap) {
                sb.append(Character.toUpperCase(c));
                cap = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
