package com.wordpress.keepup395.dekhte;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.URI;

/**
 * Created by user on 22-08-2017.
 */

public class UserListAdapter extends ArrayAdapter<String> {
    public String[] name;
    /*Calendar c = Calendar.getInstance();
    int mYear = c.get(Calendar.YEAR);
    int mMonth = c.get(Calendar.MONTH);
    int mDay;
    int mTime;
    String checkdecodestartdate = "";
    long checkStart;*/
    String id2;
    UserListAdapter adapter1;
    Activity context;
    private String[] email1;
    private String[] phone;
    private String[] id;
    private String[] bikename;
    private String[] cost;
    private String[] startdate;
    private String[] enddate;
    //private String[] decodestartdate;

    public UserListAdapter(Activity context, String[] id, String[] name, String[] email1, String[] phone, String[] bikename, String[] cost, String[] startdate, String[] enddate) {
        super(context, 0, bikename);
        this.context = context;
        this.id = id;
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
        final View rowView = inflater.inflate(R.layout.userlist, null);
        TextView user = (TextView) rowView.findViewById(R.id.name);
        TextView email = (TextView) rowView.findViewById(R.id.email);
        TextView num = (TextView) rowView.findViewById(R.id.number);
        TextView phonee = (TextView) rowView.findViewById(R.id.phoneno);
        TextView tvbikename = (TextView) rowView.findViewById(R.id.bike);
        TextView tvstartdate = (TextView) rowView.findViewById(R.id.startdate);
        TextView tvenddate = (TextView) rowView.findViewById(R.id.enddate);
        TextView tvcost = (TextView) rowView.findViewById(R.id.cost);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to remove the ride?");
                builder.setTitle("Remove user");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new Deleters().execute();

                    }

                });
                builder.show();

            }
        });

        //num.setText(id[position]);
        num.setText(id[position]);
        user.setText(name[position]);
        email.setText(email1[position]);
        phonee.setText(phone[position]);
        tvbikename.setText(bikename[position]);

        tvstartdate.setText("Start Date & Time : " + startdate[position]);
        tvenddate.setText("End Date & Time : " + enddate[position]);
        tvcost.setText("Cost : Rs " + cost[position] + "/-");
        id2 = num.getText().toString();
        //imageView.setImageBitmap(Bitmap.createBitmap(bitmaps[position]));
        return rowView;

    }

    class Deleters extends AsyncTask<String, Void, Boolean> {
        ProgressDialog loading = new ProgressDialog(context);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading.show();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            //loading.dismiss();
            adapter1.notifyDataSetChanged();
            loading.dismiss();

        }

        @Override
        protected Boolean doInBackground(String... params) {


            //Toast.makeText(AdminView.this,"hd",Toast.LENGTH_LONG).show();
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            try {
                String url = "http://www.twondfour.com/fetch/deleteuserlist.php?id=" + id2;
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(url));
                HttpResponse response = client.execute(request);
                int status = response.getStatusLine().getStatusCode();
            } catch (Exception e) {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
            }
            return null;
        }
    }

}
