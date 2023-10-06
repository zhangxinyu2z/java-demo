package com.z2xinyu.reflection.staticproxy;

/**
 * 具体放映的电影
 * 
 * @author zhang xinyu
 * @date 2021-05-19 09:41:30
 * @version v1.0
 */
public class RealMovie implements Movie {

    @Override
    public void play() {
        System.out.println("放映电影 陆小凤传奇");
    }

}
