package com.z2xinyu.io.wrapper;

import java.io.IOException;
import java.io.InputStream;

public class MyInputStream  extends InputStream {

    private InputStream in ;
    private int key;
    public MyInputStream(InputStream in,int key) {
        this.in = in;
        this.key = key;
    }
    @Override
    public int read() throws IOException {
        
         return in.read() + key;
    }
      
}
