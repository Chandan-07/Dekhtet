package com.wordpress.keepup395.dekhte;

import java.util.Date;

/**
 * Created by user on 14-08-2017.
 */

public class getTime {
    private long messagetime;
    public getTime(){
        messagetime = new Date().getTime();
    }
    public long getMessagetime() {
        return messagetime;
    }

    public void setMessagetime(long messagetime) {
        this.messagetime = messagetime;
    }
}

