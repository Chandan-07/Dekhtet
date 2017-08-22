package com.wordpress.keepup395.dekhte;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by user on 22-08-2017.
 */

public class UserListAdapter extends ArrayAdapter<String> {
    /*Calendar c = Calendar.getInstance();
    int mYear = c.get(Calendar.YEAR);
    int mMonth = c.get(Calendar.MONTH);
    int mDay;
    int mTime;
    String checkdecodestartdate = "";
    long checkStart;*/
    public String[] name;
    Activity context;
    private String[] email1;
    private String[] phone;

    private String[] bikename;
    private String[] cost;
    private String[] startdate;
    private String[] enddate;
    //private String[] decodestartdate;

    public UserListAdapter(Activity context, String[] name, String[] email1, String[] phone, String[] bikename, String[] cost, String[] startdate, String[] enddate) {
        super(context, 0, bikename);
        this.context = context;
        this.name = name;
        this.email1 = email1;
        this.phone = phone;
        this.bikename = bikename;
        this.cost = cost;
        this.startdate = startdate;
        this.enddate = enddate;
        //this.decodestartdate = decodestartdate;


       /* if (mMonth < 9) {
            checkdecodestartdate = "" + mYear + "0" + (mMonth + 1);
        } else {
            checkdecodestartdate = "" + mYear + (mMonth + 1);
        }
        mDay = c.get(Calendar.DAY_OF_MONTH);
        if (mDay < 10) {
            checkdecodestartdate = checkdecodestartdate + "0" + mDay;
        } else {
            checkdecodestartdate = checkdecodestartdate + "" + mDay;
        }
        mTime = c.get(Calendar.HOUR_OF_DAY);
        if (mTime < 10) {
            checkdecodestartdate = checkdecodestartdate + "0" + mTime;
        } else {
            checkdecodestartdate = checkdecodestartdate + mTime;
        }
        checkStart = Long.parseLong(checkdecodestartdate);*/

    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.userlist, null);
        TextView user = (TextView) rowView.findViewById(R.id.name);
        TextView email = (TextView) rowView.findViewById(R.id.email);
        TextView phonee = (TextView) rowView.findViewById(R.id.phoneno);
        TextView tvbikename = (TextView) rowView.findViewById(R.id.bikename);
        TextView tvstartdate = (TextView) rowView.findViewById(R.id.startdate);
        TextView tvenddate = (TextView) rowView.findViewById(R.id.enddate);
        TextView tvcost = (TextView) rowView.findViewById(R.id.cost);

        user.setText("name:" + name[position]);
        email.setText("email:" + email1[position]);
        phonee.setText("phone:" + phone[position]);
        tvbikename.setText("bike :" + bikename[position]);
        tvstartdate.setText("Start Date & Time : " + startdate[position]);
        tvenddate.setText("End Date & Time : " + enddate[position]);
        tvcost.setText("Cost : Rs " + cost[position]);

        //imageView.setImageBitmap(Bitmap.createBitmap(bitmaps[position]));
        return rowView;

    }


}
