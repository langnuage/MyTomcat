package com.lagnuage.yz;

import com.lagnuage.yz.entity.MyHttpRequest;
import com.lagnuage.yz.entity.MyHttpResponse;
import com.lagnuage.yz.service.MyHttpServlet;
import com.lagnuage.yz.service.impl.MyDefaultHttpServletImpl;

import java.util.HashMap;
import java.util.Map;

public class MyTomcatContainer {

    private final Map<String, MyHttpServlet> signupCenter = new HashMap<>();

    private static final MyTomcatContainer myTomcatContainer = new MyTomcatContainer();

    public boolean isInited = false;

    private MyTomcatContainer(){}

    public static MyTomcatContainer getInstance() {
        return myTomcatContainer;
    }

    public boolean isInited() {
        return this.isInited;
    }

    public MyTomcatContainer init() {
        this.isInited = true;
        return MyTomcatContainer.getInstance();
    }

    public MyTomcatContainer setServlet(String resource, MyHttpServlet myHttpServlet) {
        if(this.signupCenter.containsKey(resource)) {
            this.signupCenter.replace(resource, myHttpServlet);
            return MyTomcatContainer.getInstance();
        }
        this.signupCenter.put(resource, myHttpServlet);
        return MyTomcatContainer.getInstance();
    }

    public void doService(MyHttpRequest myHttpRequest, MyHttpResponse myHttpResponse) {
        if(!this.signupCenter.containsKey(myHttpRequest.getResource())) {
            new MyDefaultHttpServletImpl().doService(myHttpRequest, myHttpResponse);
            return ;
        }
        this.signupCenter.get(myHttpRequest.getResource()).doService(myHttpRequest, myHttpResponse);
    }

}
