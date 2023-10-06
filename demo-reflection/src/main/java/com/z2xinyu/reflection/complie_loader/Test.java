package com.z2xinyu.reflection.complie_loader;

public class Test {
     String s;
    
    public Test(String s) {
        this.s = s;
    }
    
   public Test() {}
    
    
    public static void main(String[] args) {
        System.out.println("被测试的编译文件");
    }
    
    public void show() {
        System.out.println(s);
    }
}
