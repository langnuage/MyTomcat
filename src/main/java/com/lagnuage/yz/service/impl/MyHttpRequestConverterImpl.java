package com.lagnuage.yz.service.impl;

import com.lagnuage.yz.entity.MyHttpRequest;
import com.lagnuage.yz.service.MyHttpRequestConverter;

public class MyHttpRequestConverterImpl implements MyHttpRequestConverter {
    @Override
    public MyHttpRequest httpRequestConvert(String resource, String[] lines) {
        MyHttpRequest myHttpRequest = new MyHttpRequest();
        myHttpRequest.setResource(resource);
        myHttpRequest.setLines(lines);
        myHttpRequest.setJsonData(lines[8]);
        return myHttpRequest;
    }
}
