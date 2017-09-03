package com.wordpress.keepup395.dekhte;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //private Spinner spinner;
    public List<userModule> result;
    public userAdapter adapter1;
    //private static final String[] paths = {"By bikes", "By users",};
    ListView listView;
    DatabaseReference reference;
    MediaPlayer mediaPlayer;
    RecyclerView recyclerView;
    String email1;
    ProgressDialog progressDialog;
    //ArrayList<String> list=new ArrayList<>();
    //ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.isntt);

        /*Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/


        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId("1:281687960511:android:a1382e7bfd262273") // Required for Analytics.
                .setApiKey("AIzaSyBPoeiCofCeL8vhaUBEx4_NlmNKf9Cm1yc") // Required for Auth.
                .setDatabaseUrl("https://navi-f790e.firebaseio.com/") // Required for RTDB.
                .build();
        FirebaseApp.initializeApp(this, options, "secondary");
        FirebaseApp app = FirebaseApp.getInstance("secondary");
        FirebaseDatabase secondaryDatabase = FirebaseDatabase.getInstance(app);


        reference = secondaryDatabase.getReference("data");
        result = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.get_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lim = new LinearLayoutManager(this);
        lim.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lim);
        progressDialog = new ProgressDialog(MainActivity.this);
        adapter1 = new userAdapter(result, this);
        recyclerView.setAdapter(adapter1);

        updatelist();
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case 0:
                removeUser(item.getGroupId());
                new DeleteUser().execute();
                break;
        }

        return super.onContextItemSelected(item);
    }

    public void updatelist() {
        reference.addChildEventListener(new

                                                ChildEventListener() {
                                                    @Override
                                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                                        result.add(dataSnapshot.getValue(userModule.class));
                                                        adapter1.notifyDataSetChanged();

                                                        showNotification();


                                                    }

                                                    @Override
                                                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                                                        userModule module = dataSnapshot.getValue(userModule.class);
                                                        int index = getItemIndex(module);
                                                        result.set(index, module);
                                                        adapter1.notifyItemChanged(index);

                                                    }

                                                    @Override
                                                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                                                        userModule module = dataSnapshot.getValue(userModule.class);
                                                        int index = getItemIndex(module);
                                                        result.remove(index);
                                                        adapter1.notifyItemRemoved(index);


                                                    }

                                                    @Override
                                                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                }

        );
    }


    private int getItemIndex(userModule data) {
    int index= -1;
    for(int i=0;i< result.size();i++)
    {
        if(result.get(i).chatId.equals(data.chatId)){
            index = i;
            break;
        }
    }
    return  index;
}


    private void removeUser(int position) {
        reference.child(result.get(position).chatId).removeValue();

        this.result.remove(position);
        email1 = result.get(position).email;

        adapter1.notifyDataSetChanged();

    }

    public void showNotification(){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_account_circle_black_24dp);
        builder.setContentTitle("Notification");
        builder.setContentText("User Booked the bike ");
        builder.setAutoCancel(true);
        mediaPlayer.start();
        Context context = getApplicationContext();


        Intent notificationIntent = new Intent(context, MainActivity.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent intent = PendingIntent.getActivity(context, 0,
                new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(intent);

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(0,builder.build());


    }

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
                String url = "http://www.twondfour.com/fetch/deleteUsersBook.php?id=" + email1;
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(url));
                HttpResponse response = client.execute(request);
                int status = response.getStatusLine().getStatusCode();
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
            return null;
        }
    }

}
