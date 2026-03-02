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

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public final class ApexFileType extends LanguageFileType {

    public static final ApexFileType INSTANCE = new ApexFileType();

    private ApexFileType() {
        super(ApexLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Apex";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Salesforce Apex source file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "cls";
    }

    @Override
    public Icon getIcon() {
        return IconLoader.getIcon("/icons/apex.svg", ApexFileType.class);
    }
}
