package org.lld.creational.builder.report.text;

import org.lld.creational.builder.report.Report;
import org.lld.creational.builder.report.ReportBuilder;

// 4. Concrete Builder B: Plain Text Formatter
public class PlainTextReportBuilder implements ReportBuilder {
    private Report report = new Report();

    @Override public void buildHeader() { report.setHeader("=== FINANCIAL REPORT ==="); }
    @Override public void buildBody() { report.setBody("Net Income: $30,000 (Revenue: $50k, Expenses: $20k)"); }
    @Override public void buildFooter() { report.setFooter("========================"); }
    @Override public Report getResult() { return this.report; }
}