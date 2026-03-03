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

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Persistent settings for the Qualimetry Apex Analyzer plugin.
 * Stores per-rule enabled/disabled overrides and severity overrides,
 * mirroring the VS Code extension's {@code apexAnalyzer.rules} setting.
 */
@State(name = "QualimetryApexAnalyzerSettings", storages = @Storage("qualimetry-apex.xml"))
public final class ApexAnalyzerSettings implements PersistentStateComponent<ApexAnalyzerSettings> {

    public boolean enabled = true;
    public boolean rulesReplaceDefaults = false;

    /**
     * Per-rule overrides. Key = rule key (e.g. "qa-apex-syntax"),
     * value = serialised override (enabled, severity).
     */
    public Map<String, RuleOverride> rules = new HashMap<>();

    public static ApexAnalyzerSettings getInstance() {
        return ApplicationManager.getApplication().getService(ApexAnalyzerSettings.class);
    }

    @Override
    public ApexAnalyzerSettings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull ApexAnalyzerSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public boolean isRuleEnabled(String ruleKey) {
        RuleOverride override = rules.get(ruleKey);
        if (override != null) {
            return override.enabled;
        }
        if (rulesReplaceDefaults) {
            return false;
        }
        return true;
    }

    public String getRuleSeverity(String ruleKey) {
        RuleOverride override = rules.get(ruleKey);
        if (override != null && override.severity != null && !override.severity.isBlank()) {
            return override.severity;
        }
        return null;
    }

    public static class RuleOverride {
        public boolean enabled = true;
        public String severity;

        @SuppressWarnings("unused")
        public RuleOverride() {
        }

        public RuleOverride(boolean enabled, String severity) {
            this.enabled = enabled;
            this.severity = severity;
        }
    }
}
