package com.lagnuage.yz.entity;

public class MyHttpRequest {

    private String resource;

    private String[] lines;

    private String jsonData;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String[] getLines() {
        return lines;
    }

    public void setLines(String[] lines) {
        this.lines = lines;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }
}
