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
package com.qualimetry.sonar.apex.analyzer.visitor;

/**
 * Shared text-processing utilities for Apex analysis checks.
 * All methods are stateless and safe to call from any thread.
 */
public final class ApexContentHelper {

    private ApexContentHelper() {
    }

    /**
     * Replaces block comments, line comments, and string literals with spaces,
     * preserving the exact length and all newline positions so that line numbers
     * remain stable.
     */
    public static String stripCommentsAndStrings(String content) {
        char[] chars = content.toCharArray();
        int len = chars.length;
        int i = 0;

        while (i < len) {
            if (i + 1 < len && chars[i] == '/' && chars[i + 1] == '*') {
                chars[i] = ' ';
                chars[i + 1] = ' ';
                i += 2;
                while (i + 1 < len && !(chars[i] == '*' && chars[i + 1] == '/')) {
                    if (chars[i] != '\n') {
                        chars[i] = ' ';
                    }
                    i++;
                }
                if (i + 1 < len) {
                    chars[i] = ' ';
                    chars[i + 1] = ' ';
                    i += 2;
                }
            } else if (i + 1 < len && chars[i] == '/' && chars[i + 1] == '/') {
                chars[i] = ' ';
                chars[i + 1] = ' ';
                i += 2;
                while (i < len && chars[i] != '\n') {
                    chars[i] = ' ';
                    i++;
                }
            } else if (chars[i] == '\'') {
                chars[i] = ' ';
                i++;
                while (i < len) {
                    if (chars[i] == '\\' && i + 1 < len && chars[i + 1] == '\'') {
                        chars[i] = ' ';
                        chars[i + 1] = ' ';
                        i += 2;
                    } else if (chars[i] == '\'') {
                        chars[i] = ' ';
                        i++;
                        break;
                    } else {
                        if (chars[i] != '\n') {
                            chars[i] = ' ';
                        }
                        i++;
                    }
                }
            } else {
                i++;
            }
        }

        return new String(chars);
    }

    /**
     * Normalises line endings to {@code \n} and splits into lines.
     */
    public static String[] splitLines(String content) {
        return content.replace("\r\n", "\n").replace('\r', '\n').split("\n", -1);
    }

    /**
     * Counts occurrences of {@code c} in {@code s}.
     */
    public static int countChar(String s, char c) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the 1-based line number for the given 0-based character offset.
     */
    public static int lineAtOffset(String content, int charOffset) {
        int line = 1;
        for (int i = 0; i < charOffset && i < content.length(); i++) {
            if (content.charAt(i) == '\n') {
                line++;
            }
        }
        return line;
    }

    /**
     * Determines whether {@code lineIndex} (0-based) falls inside a block
     * introduced by {@code blockKeyword} (e.g. "for", "while", "try").
     * Uses brace-counting on already-stripped lines, scanning backwards.
     */
    public static boolean isInsideBlock(String[] strippedLines, int lineIndex, String blockKeyword) {
        int braceDepth = 0;
        for (int i = lineIndex; i >= 0; i--) {
            String line = strippedLines[i];
            for (int j = line.length() - 1; j >= 0; j--) {
                char ch = line.charAt(j);
                if (ch == '}') {
                    braceDepth++;
                } else if (ch == '{') {
                    braceDepth--;
                    if (braceDepth < 0) {
                        return lineContainsKeyword(strippedLines, i, blockKeyword);
                    }
                }
            }
        }
        return false;
    }

    private static boolean lineContainsKeyword(String[] lines, int braceLineIndex, String keyword) {
        for (int i = braceLineIndex; i >= 0; i--) {
            String line = lines[i].trim();
            if (line.matches("(?i).*\\b" + keyword + "\\b.*")) {
                return true;
            }
            if (i < braceLineIndex && !line.isEmpty() && !line.equals("{")) {
                break;
            }
        }
        return false;
    }
}
