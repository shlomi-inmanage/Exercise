package com.example.exerecise.Models.Server_Request;

public class ServerRequestParameters {

    private Header header;
    private Body body;
    private int method;
    private UrlBuilder builder;
    private int fragment_to_return_response;
    private boolean show_loader;

    public ServerRequestParameters(Header header, Body body, int method, UrlBuilder builder, int fragment_to_return_response, boolean show_loader) {
        this.header = header;
        this.body = body;
        this.method = method;
        this.builder = builder;
        this.fragment_to_return_response = fragment_to_return_response;
        this.show_loader = show_loader;
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

    public int getFragment_to_return_response() {
        return fragment_to_return_response;
    }

    public void setFragment_to_return_response(int fragment_to_return_response) {
        this.fragment_to_return_response = fragment_to_return_response;
    }

    public boolean isShow_loader() {
        return show_loader;
    }

    public void setShow_loader(boolean show_loader) {
        this.show_loader = show_loader;
    }
}
