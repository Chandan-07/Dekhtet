package com.wordpress.keepup395.dekhte;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.text.DateFormat;
import java.util.Date;

import static com.wordpress.keepup395.dekhte.R.id.id;

public class AdminView extends AppCompatActivity {
    ProgressDialog progressDialog;

    String user1, email1,otp;
    String id;

    private String messagetime = DateFormat.getDateTimeInstance().format(new Date());
    TextView user, bikename, phone, cost, startdate, enddate, chatId, call, email;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);
        user = (TextView) findViewById(R.id.use);
        bikename = (TextView) findViewById(R.id.bikenam);
        TextView time1 = (TextView) findViewById(R.id.time);
        phone = (TextView) findViewById(R.id.phon);
        cost = (TextView) findViewById(R.id.cos);
        email = (TextView) findViewById(R.id.email);
        chatId = (TextView) findViewById(R.id.chatId);
        call = (TextView) findViewById(R.id.call);

        progressDialog = new ProgressDialog(AdminView.this);
        //startdate=(TextView)findViewById(R.id.startdat);
        // enddate=(TextView)findViewById(R.id.enddat);
        time1.setText(messagetime);

        user.setText(getIntent().getStringExtra("user"));
        bikename.setText(getIntent().getStringExtra("bikename"));
        phone.setText(getIntent().getStringExtra("phone"));
        cost.setText("Rs: "+getIntent().getStringExtra("cost")+"/-");
        user1 = user.getText().toString();

        email.setText(getIntent().getStringExtra("email"));
        chatId.setText(getIntent().getStringExtra("chattitl"));
        call.setText("call " + user1 + "?  ");
        otp=chatId.getText().toString();
        email1 = email.getText().toString();
        button = (Button) findViewById(R.id.remove);
        //startdate.setText(getIntent().getStringExtra("startdate"));
        // enddate.setText(getIntent().getStringExtra("enddate"));
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + phone.getText().toString()));
                    callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(callIntent);
                } catch (ActivityNotFoundException activityException) {
                    Log.e("Calling a Phone Number", "Call failed", activityException);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteUser().execute();

            }
        });

    }

    /*class RemoveRideCompleteUser extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(AdminView.this,"Pela",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            Toast.makeText(AdminView.this,"jhuu",Toast.LENGTH_LONG).show();
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            try {
                String url = "http://www.twondfour.com/fetch/multiplebikecheck.php?email="+email1;
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(url));
                HttpResponse response = client.execute(request);
                int status = response.getStatusLine().getStatusCode();
                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    JSONObject object = jsonArray.getJSONObject(0);
                    //url = new URL(object.getString("image"));
                    //bitmaps[i]= BitmapFactory.decodeStream(url.openConnection().getInputStream());

                    id = object.getString("id");
                    //checkenddate = Long.parseLong(object.getString("enddate"));
                }
            } catch (Exception e) {
                Toast.makeText(AdminView.this, "Error", Toast.LENGTH_LONG).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
           /* String checkdecodestartdate="";
            long today;
            Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            if(mMonth<9){
                checkdecodestartdate=""+mYear+"0"+(mMonth+1);
            }
            else{
                checkdecodestartdate=""+mYear+(mMonth+1);
            }
            int mDay = c.get(Calendar.DAY_OF_MONTH);
            if(mDay<10){
                checkdecodestartdate=checkdecodestartdate+"0"+mDay;
            }
            else{
                checkdecodestartdate=checkdecodestartdate+""+mDay;
            }
            int hour_x = c.get(Calendar.HOUR_OF_DAY);
            if (hour_x<10){
                checkdecodestartdate=checkdecodestartdate+"0"+hour_x;
            }
            else{
                checkdecodestartdate=checkdecodestartdate+hour_x;
            }
            today = Long.parseLong(checkdecodestartdate);

            //Toast.makeText(Booknow.this,""+today,Toast.LENGTH_LONG).show();
            //Toast.makeText(Booknow.this,""+checkenddate+"  "+today,Toast.LENGTH_LONG).show();
            if(checkenddate!=123){
                if(checkenddate<today){
            new DeleteUser().execute();

        }*/






    class DeleteUser extends AsyncTask<String, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            //loading.dismiss();
            progressDialog.dismiss();

        }

        @Override
        protected Boolean doInBackground(String... params) {
            //Toast.makeText(AdminView.this,"hd",Toast.LENGTH_LONG).show();
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            try {
                String url = "http://www.twondfour.com/fetch/deleteUsersBook.php?id=" +email1;
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(url));
                HttpResponse response = client.execute(request);
                int status = response.getStatusLine().getStatusCode();
            } catch (Exception e) {
                Toast.makeText(AdminView.this, "Error", Toast.LENGTH_LONG).show();
            }
            return null;
        }
    }

}