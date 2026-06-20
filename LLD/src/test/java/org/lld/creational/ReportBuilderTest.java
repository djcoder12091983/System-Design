package org.lld.creational;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.DPTestSuit;
import org.lld.creational.builder.report.Report;
import org.lld.creational.builder.report.ReportBuilder;
import org.lld.creational.builder.report.ReportDirector;
import org.lld.creational.builder.report.html.HtmlReportBuilder;
import org.lld.creational.builder.report.text.PlainTextReportBuilder;

import static org.junit.jupiter.api.Assertions.*;

// These integration tests verify that the Director controls the sequential assembly line process properly,
// while changing the concrete builder produces completely different, isolated output schemas.
@DisplayName("Classic GoF Builder - Report Director Test Suite")
public class ReportBuilderTest extends DPTestSuit {

    private ReportDirector director;

    @BeforeEach
    void initDirector() {
        director = new ReportDirector();
    }

    @Test
    @DisplayName("Director orchestrating an HtmlReportBuilder must accurately assemble HTML tag structures")
    void testHtmlReportGenerationWorkflow() {
        ReportBuilder htmlBuilder = new HtmlReportBuilder();
        director.setBuilder(htmlBuilder);

        // Director handles running sequential operations behind the scenes
        director.constructReport();
        Report result = htmlBuilder.getResult();

        assertNotNull(result);
        result.view(); // Render output to parse buffers
        String docOutput = outputStreamCaptor.toString();

        assertTrue(docOutput.contains("<html><head><title>"), "Must bundle HTML header schema elements");
        assertTrue(docOutput.contains("<h1>Revenue: $50,000</h1>"), "Must compile target dataset metrics into HTML markers");
        assertTrue(docOutput.contains("</footer></body></html>"), "Must complete structural file closure templates");
    }

    @Test
    @DisplayName("Director orchestrating a PlainTextReportBuilder must create raw string borders instead of tags")
    void testPlainTextReportGenerationWorkflow() {
        ReportBuilder textBuilder = new PlainTextReportBuilder();
        director.setBuilder(textBuilder);

        director.constructReport();
        Report result = textBuilder.getResult();

        assertNotNull(result);
        result.view();
        String docOutput = outputStreamCaptor.toString();

        assertTrue(docOutput.contains("=== FINANCIAL REPORT ==="), "Must render plain ascii boundary line titles");
        assertTrue(docOutput.contains("Net Income: $30,000"), "Must parse math metrics inside core string body variables");
        assertFalse(docOutput.contains("<html>"), "Plain text engine must never emit HTML tagging layouts");
    }
}
