package org.lld.creational.builder.report;

// report building logic
public interface ReportBuilder {
    void buildHeader();
    void buildBody();
    void buildFooter();
    Report getResult();
}
