package com.wordpress.keepup395.dekhte;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 08-08-2017.
 */

public class userModule  {
    public String bikename, chatdata, chatId, chatuser, enddate;
    public String cost, chattitl;
    public String chatstart;
    //public String
    public String email;
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

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("bikename", bikename);
        result.put("chatId", chatId);
        result.put("chatdata", chatdata);
        result.put("chatstart", chatstart);
        result.put("chattitl", chattitl);
        result.put("cost", cost);
        result.put("email", email);
        result.put("enddate", enddate);
        return result;
    }
}
