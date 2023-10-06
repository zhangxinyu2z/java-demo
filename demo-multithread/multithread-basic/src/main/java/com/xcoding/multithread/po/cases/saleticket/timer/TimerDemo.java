package com.xcoding.multithread.po.cases.saleticket.timer;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {
    public static void main(String[] args) throws ParseException {
        new Timer().schedule(new MyTask()
                , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .parse("2020-08-20 16:06:00"));
//        Timer t =new Timer();
//        t.schedule(new MyTask(),5000);
    }
}

class MyTask extends TimerTask {

    @Override
    public void run() {
        File f = new File("D:\\CodeDemo\\renameto");
        delete(f);
    }

    private void delete(File f) {
        File[] files = f.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    delete(file);
                } else {
                    file.delete();
                }
            }
        }
        f.delete();
    }
}
