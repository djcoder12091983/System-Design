package org.lld.creational.builder.report;

import org.lld.creational.builder.report.html.HtmlReportBuilder;
import org.lld.creational.builder.report.text.PlainTextReportBuilder;

public class Main {
    // TODO we have created dummy test case but in reality we need to setup proper test cases
    public static void main(String[] args) {
        ReportDirector director = new ReportDirector();

        // Generate an HTML Report
        ReportBuilder htmlBuilder = new HtmlReportBuilder();
        director.setBuilder(htmlBuilder);
        director.constructReport(); // Director executes the precise structure algorithm
        Report htmlReport = htmlBuilder.getResult();
        htmlReport.view();

        // Generate a Plain Text Report using the exact same Director
        ReportBuilder textBuilder = new PlainTextReportBuilder();
        director.setBuilder(textBuilder);
        director.constructReport();
        Report textReport = textBuilder.getResult();
        textReport.view();
    }
}

