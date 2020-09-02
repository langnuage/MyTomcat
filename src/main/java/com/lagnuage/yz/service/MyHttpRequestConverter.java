package com.lagnuage.yz.service;

import com.lagnuage.yz.entity.MyHttpRequest;

public interface MyHttpRequestConverter {

    public MyHttpRequest httpRequestConvert(String resource, String[] lines);

}
