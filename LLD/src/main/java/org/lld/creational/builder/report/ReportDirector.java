package org.lld.creational.builder.report;

// 5. The Director: Standardizes the construction step sequence
public class ReportDirector {
    private ReportBuilder builder;

    public void setBuilder(ReportBuilder builder) {
        this.builder = builder;
    }

    // Standard business assembly line sequence
    public void constructReport() {
        builder.buildHeader();
        builder.buildBody();
        builder.buildFooter();
    }
}
