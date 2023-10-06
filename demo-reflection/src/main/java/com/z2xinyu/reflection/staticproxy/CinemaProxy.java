package com.z2xinyu.reflection.staticproxy;

/**
 * 电影院，用来播放电影
 * 
 * @author zhang xinyu
 * @date 2021-05-19 09:41:00
 * @version v1.0
 */
public class CinemaProxy implements Movie {
    private Movie movie;

    public CinemaProxy() {
        this.movie = new RealMovie();
    }

    @Override
    public void play() {
        guanggao(true);
        movie.play();
        guanggao(false);
    }

    public void guanggao(boolean flag) {
        if (flag) {
            System.out.println("爆米花、可乐、口香糖9.8折，快来买啊");
        } else {
            System.out.println("爆米花、可乐、口香糖9.8折，快来买啊");
        }

    }
}
