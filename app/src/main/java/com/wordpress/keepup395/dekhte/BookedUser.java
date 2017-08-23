package com.wordpress.keepup395.dekhte;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
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

public class BookedUser extends AppCompatActivity {
    public ListView bookedusers;

    public String[] name;
    public String[] phone;
    public String[] email;
    public String[] startdate;
    public String[] enddate;
    public String[] cost;
    public String[] bikename;
    public ProgressDialog loading;
    String[] imageString;
    String[] decodestartdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_user);
        bookedusers = (ListView) findViewById(R.id.bookeduser);

        execuute();

    }

    public void execuute() {
        String link = "http://www.twondfour.com/fetch/totalbookeduser.php?";
        new GetBookedUserDetails().execute();
    }

    class GetBookedUserDetails extends AsyncTask<String, Void, Boolean> {
        @Override
        protected void onPreExecute() {

            // Toast.makeText(BookedUser.this,"hefddrgr jh",Toast.LENGTH_LONG).show();
            super.onPreExecute();
            loading = ProgressDialog.show(BookedUser.this, "Loading...", "Please wait...", true, true);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            //Toast.makeText(BookedUser.this,"hey",Toast.LENGTH_LONG).show();
            try {
                //Toast.makeText(BookedUser.this,"hefdjh",Toast.LENGTH_LONG).show();
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(URI.create("http://www.twondfour.com/fetch/totalbookeduser.php?"));
                HttpResponse response = client.execute(request);
                int status = response.getStatusLine().getStatusCode();
                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    name = new String[jsonArray.length()];
                    email = new String[jsonArray.length()];
                    phone = new String[jsonArray.length()];
                    startdate = new String[jsonArray.length()];
                    enddate = new String[jsonArray.length()];
                    cost = new String[jsonArray.length()];
                    bikename = new String[jsonArray.length()];
                    //image = new Bitmap[jsonArray.length()];
                    //decodestartdate = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        //url = new URL(object.getString("image"));
                        //bitmaps[i]= BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        name[i] = object.getString("user_name");
                        email[i] = object.getString("email_id");
                        phone[i] = object.getString("phone_no");

                        bikename[i] = object.getString("bike_name");
                        cost[i] = object.getString("cost");
                        startdate[i] = object.getString("start_date_time");
                        enddate[i] = object.getString("end_date_time");
                        //decodestartdate[i] = object.getString("decode_start_date");
                    }

                }
                return false;
            } catch (Exception e) {
                Toast.makeText(BookedUser.this, "Error", Toast.LENGTH_LONG).show();
            }
            Toast.makeText(BookedUser.this, "hefdjh", Toast.LENGTH_LONG).show();
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            // Toast.makeText(BookedUser.this,"hefdjh",Toast.LENGTH_LONG).show();
            UserListAdapter yourRide = new UserListAdapter(BookedUser.this, name, email, phone, bikename, cost, startdate, enddate);
            bookedusers.setAdapter(yourRide);
            loading.dismiss();
        }
    }
}
