package com.z2xinyu.reflection._proxy;

/**
 * 电影院，用来播放电影
 * 
 * @author zhang xinyu
 * @date 2021-05-19 09:41:00
 * @version v1.0
 */
public class WandaCinemaProxy implements Cinema {
    private Cinema cinema;

    public WandaCinemaProxy() {
        this.cinema = new WandaCinema();
    }

    @Override
    public void play() {
        advertise(true);
        cinema.play();
        advertise(false);
    }

    /**
     * 广告
     * @param flag
     */
    public void advertise(boolean flag) {
        if (flag) {
            System.out.println("爆米花、可乐、口香糖9.8折，快来买啊");
        } else {
            System.out.println("爆米花、可乐、口香糖9.8折，快来买啊");
        }

    }
}
