package com.example.exerecise.Models.Server_Request;

public class ServerRequestParameters {

    private Header header;
    private Body body;
    private int method;
    private UrlBuilder builder;

    public ServerRequestParameters(Header header, Body body, int method, UrlBuilder builder) {
        this.header = header;
        this.body = body;
        this.method = method;
        this.builder = builder;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public UrlBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(UrlBuilder builder) {
        this.builder = builder;
    }
}
