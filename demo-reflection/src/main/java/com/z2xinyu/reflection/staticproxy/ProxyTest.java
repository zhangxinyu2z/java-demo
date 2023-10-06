package com.z2xinyu.reflection.staticproxy;

/**
 * 静态代理模式
 * 
 * 
 * @author zhang xinyu
 * @date 2021-05-19 10:00:55
 * @version v1.0
 */
public class ProxyTest {

    public static void main(String[] args) {
        /*
         * 静态代理：直接去电影院看电影，电影院会放映广告
         * 装饰器：自己在家可以看电影，但是我想看广告，所以就去电影院
         */
        Movie movie = new CinemaProxy(); 
        movie.play();
    }
}
