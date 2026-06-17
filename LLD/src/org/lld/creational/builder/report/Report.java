package org.lld.creational.builder.report;

// 1. The Complex Product Container
public class Report {
    private String header = "";
    private String body = "";
    private String footer = "";

    public void setHeader(String h) { this.header = h; }
    public void setBody(String b) { this.body = b; }
    public void setFooter(String f) { this.footer = f; }

    public void view() {
        System.out.println(header + "\n" + body + "\n" + footer + "\n");
    }
}