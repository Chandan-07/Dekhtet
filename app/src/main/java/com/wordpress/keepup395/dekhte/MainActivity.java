package com.wordpress.keepup395.dekhte;

import android.app.NotificationManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String[] paths = {"By bikes", "By users",};
    ListView listView;
    DatabaseReference reference;
    MediaPlayer mediaPlayer;
    RecyclerView recyclerView;
    private Spinner spinner;
    private List<userModule> result;
    private userAdapter adapter1;


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


        //    listView=(ListView)findViewById(R.id.get_view);
        //  adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);
        //listView.setAdapter(adapter);
        reference = secondaryDatabase.getReference("data");
        result = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.get_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lim = new LinearLayoutManager(this);
        lim.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lim);

        adapter1 = new userAdapter(result, this);
        recyclerView.setAdapter(adapter1);


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
            index=1;
            break;
        }
    }
    return  index;
}



    public void showNotification(){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_account_circle_black_24dp);
        builder.setContentTitle("Notification");
        builder.setContentText("User Booked the bike ");
        mediaPlayer.start();
        NotificationManager nm= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(0,builder.build());




    }

}
