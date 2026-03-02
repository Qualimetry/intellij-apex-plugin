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
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import com.qualimetry.sonar.apex.analyzer.checks.CheckList;
import com.qualimetry.sonar.apex.analyzer.visitor.BaseCheck;
import org.jetbrains.annotations.NotNull;
import org.sonar.check.Rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * IntelliJ inspection that runs the shared Qualimetry Apex analysis engine.
 * Uses the same {@link CheckList} and {@link BaseCheck} classes as the
 * SonarQube plugin and VS Code extension.
 */
public final class ApexInspection extends LocalInspectionTool {

    private static final Map<String, Class<? extends BaseCheck>> CHECK_CLASS_BY_KEY = new HashMap<>();

    static {
        for (Class<? extends BaseCheck> clazz : CheckList.getAllChecks()) {
            Rule rule = clazz.getAnnotation(Rule.class);
            if (rule != null && rule.key() != null && !rule.key().isEmpty()) {
                CHECK_CLASS_BY_KEY.put(rule.key(), clazz);
            }
        }
    }

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        PsiFile psiFile = holder.getFile();
        if (!(psiFile instanceof ApexFile)) {
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
            if (!settings.isRuleEnabled(key)) {
                continue;
            }

            try {
                checks.add(clazz.getDeclaredConstructor().newInstance());
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

        int lineStartOffset = document.getLineStartOffset(line - 1);
        int lineEndOffset = document.getLineEndOffset(line - 1);

        PsiElement element = psiFile.findElementAt(lineStartOffset);
        if (element == null) {
            return;
        }

        String message = "[" + issue.ruleKey() + "] " + issue.message();
        holder.registerProblem(element, message, highlightType);
    }

    private String getRuleKey(BaseCheck check) {
        Rule rule = check.getClass().getAnnotation(Rule.class);
        return (rule != null) ? rule.key() : "unknown";
    }
}
