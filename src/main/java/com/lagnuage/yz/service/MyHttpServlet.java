package com.lagnuage.yz.service;

import com.lagnuage.yz.entity.MyHttpRequest;
import com.lagnuage.yz.entity.MyHttpResponse;

public interface MyHttpServlet {

    public void doService(MyHttpRequest request, MyHttpResponse response);
    public String getResource();
    public void setResource(String resource);
}
