package com.wordpress.keepup395.dekhte;

/**
 * Created by user on 08-08-2017.
 */

public class userModule  {
    String bikename,chatdata,chatId,chatuser,enddate;
    String cost,chattitl;
    String  chatstart;
    String email;
    public userModule(){

    }

    public userModule(String bikename,String chatId, String chatdata, String chatstart,  String chattitl,String chatuser,String cost,String email, String enddate) {
        this.bikename = bikename;
        this.chatId = chatId;
        this.chatdata = chatdata;
        this.chatstart = chatstart;
        this.chattitl = chattitl;
        this.chatuser = chatuser;
        this.cost = cost;
        this.email=email;

        this.enddate = enddate;
    }
}
