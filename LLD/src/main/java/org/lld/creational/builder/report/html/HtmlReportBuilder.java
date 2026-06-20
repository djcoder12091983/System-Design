package org.lld.creational.builder.report.html;

import org.lld.creational.builder.report.Report;
import org.lld.creational.builder.report.ReportBuilder;

// 3. Concrete Builder A: HTML Formatter
public class HtmlReportBuilder implements ReportBuilder {
    private final Report report = new Report();

    @Override public void buildHeader() { report.setHeader("<html><head><title>Monthly Financials</title></head><body>"); }
    @Override public void buildBody() { report.setBody("<h1>Revenue: $50,000</h1><p>Expenses: $20,000</p>"); }
    @Override public void buildFooter() { report.setFooter("<footer>Confidential - Automated System</footer></body></html>"); }
    @Override public Report getResult() { return this.report; }
}