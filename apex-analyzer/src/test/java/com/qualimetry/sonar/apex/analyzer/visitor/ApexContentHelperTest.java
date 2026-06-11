/*
 * Copyright 2026 SHAZAM Analytics Ltd
 */
package com.qualimetry.sonar.apex.analyzer.visitor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApexContentHelperTest {

    @Test
    void stripCommentsAndStrings_blockComment() {
        String input = "code /* comment */ more";
        String result = ApexContentHelper.stripCommentsAndStrings(input);

        assertThat(result).hasSize(input.length());
        assertThat(result).startsWith("code ");
        assertThat(result).endsWith(" more");
        assertThat(result).doesNotContain("comment");
    }

    @Test
    void stripCommentsAndStrings_lineComment() {
        String input = "code // comment\nnext";
        String result = ApexContentHelper.stripCommentsAndStrings(input);

        assertThat(result).hasSize(input.length());
        assertThat(result).contains("\n");
        assertThat(result).doesNotContain("comment");
        assertThat(result).endsWith("next");
    }

    @Test
    void stripCommentsAndStrings_stringLiteral() {
        String input = "x = 'hello';";
        String result = ApexContentHelper.stripCommentsAndStrings(input);

        assertThat(result).hasSize(input.length());
        assertThat(result).doesNotContain("hello");
        assertThat(result).startsWith("x = ");
        assertThat(result).endsWith(";");
    }

    @Test
    void stripCommentsAndStrings_escapedQuote() {
        String input = "x = 'it\\'s';";
        String result = ApexContentHelper.stripCommentsAndStrings(input);

        assertThat(result).hasSize(input.length());
        assertThat(result).doesNotContain("it");
        assertThat(result).doesNotContain("'");
    }

    @Test
    void stripCommentsAndStrings_multiLineBlock() {
        String input = "a\n/* line1\nline2\nline3 */\nb";
        String result = ApexContentHelper.stripCommentsAndStrings(input);

        assertThat(result).hasSize(input.length());
        String[] inputLines = ApexContentHelper.splitLines(input);
        String[] resultLines = ApexContentHelper.splitLines(result);
        assertThat(resultLines).hasSameSizeAs(inputLines);
    }

    @Test
    void stripCommentsAndStrings_preservesLineNumbers() {
        String input = "line1\n/* block\ncomment */\nline4 // inline\nline5";
        String result = ApexContentHelper.stripCommentsAndStrings(input);

        String[] inputLines = ApexContentHelper.splitLines(input);
        String[] resultLines = ApexContentHelper.splitLines(result);
        assertThat(resultLines).hasSameSizeAs(inputLines);
        assertThat(result).hasSize(input.length());
    }

    @Test
    void stripCommentsAndStrings_mixedContent() {
        String input = "String s = 'SELECT'; // query\nint x = 1; /* block */";
        String result = ApexContentHelper.stripCommentsAndStrings(input);

        assertThat(result).hasSize(input.length());
        assertThat(result).doesNotContain("SELECT");
        assertThat(result).doesNotContain("query");
        assertThat(result).doesNotContain("block");
        assertThat(result).contains("String s = ");
        assertThat(result).contains("int x = 1;");
    }

    @Test
    void splitLines_windowsLineEndings() {
        String input = "line1\r\nline2\r\nline3";
        String[] lines = ApexContentHelper.splitLines(input);

        assertThat(lines).containsExactly("line1", "line2", "line3");
    }

    @Test
    void splitLines_mixedLineEndings() {
        String input = "a\r\nb\rc\nd";
        String[] lines = ApexContentHelper.splitLines(input);

        assertThat(lines).containsExactly("a", "b", "c", "d");
    }

    @Test
    void countChar_basic() {
        assertThat(ApexContentHelper.countChar("abcabc", 'a')).isEqualTo(2);
        assertThat(ApexContentHelper.countChar("abcabc", 'z')).isZero();
        assertThat(ApexContentHelper.countChar("{{}}", '{')).isEqualTo(2);
        assertThat(ApexContentHelper.countChar("", 'x')).isZero();
    }

    @Test
    void lineAtOffset_basic() {
        String content = "abc\ndef\nghi";
        assertThat(ApexContentHelper.lineAtOffset(content, 0)).isEqualTo(1);
        assertThat(ApexContentHelper.lineAtOffset(content, 3)).isEqualTo(1);
        assertThat(ApexContentHelper.lineAtOffset(content, 4)).isEqualTo(2);
        assertThat(ApexContentHelper.lineAtOffset(content, 8)).isEqualTo(3);
    }
}
