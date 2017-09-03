package com.wordpress.keepup395.dekhte;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 08-08-2017.
 */

public class userAdapter extends RecyclerView.Adapter<userAdapter.UserViewHolder> {

    public List<userModule> list;
    Context context;
    int FFF5DE5;
    int FFFFFF;


    public userAdapter(List<userModule> list,Context context) {
        this.list = list;
        this.context=context;

    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item,parent,false);
        UserViewHolder userViewHolder=new UserViewHolder(view,context,list);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, final int position) {
        userModule data= list.get(position);
        holder.bikename.setText(data.bikename);
        holder.startdate.setText(data.chatdata);
        //holder.chatId.setText(data.chattitl);
        holder.phone.setText(data.chatstart);
        //holder.title.setText(data.chattitle);
        //holder.id1.setText("OTP :");
        holder.user.setText(data.chatuser);
        //holder.otp.setText(data.chatId);
        holder.cost.setText("Rs: "+data.cost+"/-");
        holder.enddate.setText(data.enddate);
        // holder.itemView.setBackgroundColor(FFF5DE5);

       /* holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                holder.not.setBackgroundColor(FFFFFF);
                return false;
            }
        });*/


        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

                //contextMenu.add(holder.getAdapterPosition(), 0, 0, "oops ! you can't delete. please go to next activity by clicking the user and remove there and then try again");

                contextMenu.add(holder.getAdapterPosition(), 0, 0, "remove the user completely from list?");

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView bikename,startdate,phone,title,user,cost,enddate,chatId;
        ImageView not;
        Context context;
        List<userModule> lists;

        public UserViewHolder(View itemView,Context context,List<userModule> lists) {
            super(itemView);
            this.context=context;
            this.lists=lists;

            itemView.setOnClickListener(this);
            chatId=(TextView)itemView.findViewById(R.id.title);
            bikename=(TextView) itemView.findViewById(R.id.bikename);
            user=(TextView)itemView.findViewById(R.id.user);
            phone=(TextView)itemView.findViewById(R.id.phone);
            //not=(ImageView)itemView.findViewById(R.id.not);
            //title=(TextView)itemView.findViewById(R.id.id);
            //id1=(TextView)itemView.findViewById(R.id.id1);
            //otp=(TextView)itemView.findViewById(R.id.otp);
            startdate=(TextView)itemView.findViewById(R.id.startdate);
            enddate=(TextView)itemView.findViewById(R.id.enddate);
           cost=(TextView)itemView.findViewById(R.id.cost);

        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            userModule list=this.lists.get(position);

            Intent intent=new Intent(this.context,AdminView.class);
            intent.putExtra("user",list.chatuser);
            intent.putExtra("bikename",list.bikename);
            intent.putExtra("phone",list.chatstart);
            intent.putExtra("cost",list.cost);
            intent.putExtra("chatId",list.chatId);
            intent.putExtra("chattitl",list.chattitl);
            intent.putExtra("email",list.email);

            //intent.putExtra("startdate",list.chatdata);
           // intent.putExtra("enddate",list.enddate);
            this.context.startActivity(intent);

        }


    }
}
