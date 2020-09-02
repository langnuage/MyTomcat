package com.lagnuage.yz.service.impl;

import com.lagnuage.yz.entity.MyHttpRequest;
import com.lagnuage.yz.entity.MyHttpResponse;
import com.lagnuage.yz.service.MyHttpServlet;

public class MyHttpServletImpl implements MyHttpServlet {

    private String resource;

    @Override
    public void doService(MyHttpRequest request, MyHttpResponse response) {

    }

    @Override
    public String getResource() {
        return this.resource;
    }

    @Override
    public void setResource(String resource) {
        this.resource = resource;
    }

}
