package com.web.ecom_template.utilities;

import org.springframework.stereotype.Service;

@Service
public class SleepServer extends Thread{
    public boolean sleepThis(int timer) {
        //sleep timer
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
