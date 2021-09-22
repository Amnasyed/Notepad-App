package com.example.notepad;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.AppDatabase.Appdatabase;
import com.example.notepad.AppDatabase.Bin;
import com.example.notepad.AppDatabase.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NotepadAdapter extends RecyclerView.Adapter<NotepadAdapter.MyViewHolder>{
    //Variables
    AlertDialog.Builder deletebuilder;
    AlertDialog deletedialog;
    ArrayList<User> data;
    Context context;
    Button deleteButton,cancelButton;
    private Appdatabase appDatabase;
    String deleteNote,note;
    //constructor
    public NotepadAdapter(ArrayList<User> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.listitem,parent,false);
        return new MyViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        User user=data.get(position);
         note=user.getEditdetail();
         String time=user.getTimestamp();
         String color= user.getColourCode();
         if (color != null){
             //int index = position;
             try{

                 int mColor = Integer.parseInt(color);
                 holder.itemView.setBackgroundColor(mColor);
             }catch (Exception e){

             }
         }
         else{
             holder.itemView.setBackgroundColor(R.color.white);
         }
        Log.e("TAG", "onBindViewHolder: " + color);
        //convert timestamp to day/,month/year & hours/seconds  &am/pm
        Calendar calendar=Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(Long.parseLong(String.valueOf(time)));
        String dateTime= DateFormat.format("dd/MM/yyyy hh:mm aa",calendar).toString();

        Log.e("TAG", "onBindViewHolder: "+ time );

        holder.textView.setText(note);
        holder.timestamp.setText(dateTime);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Notepad.class);
                if(user!=null)
                {
                    intent.putExtra("User", user);
                }
//                intent.putExtra("note",note);
                intent.putExtra("from","adapter");
                context.startActivity(intent);

            }

        });

        // set on Long click listener to delete the node on recycler view

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                deletebuilder=new AlertDialog.Builder(context);
                deletebuilder.setTitle("Delete Note");
                deletebuilder.setCancelable(false);
                View view1=LayoutInflater.from(context).inflate(R.layout.deletedialogbox,null,false);
                //delete button of delete dialog box
                deleteButton=view1.findViewById(R.id.yesdelete);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        appDatabase =Appdatabase.getdbInstance(context);
                        deleteNote= user.getEditdetail();
                        //getting  bin timestamp
                        Long binLong = System.currentTimeMillis();
                        String bints = binLong.toString();
//                         Log.e("TAG", "recycle bin " + deleteNote );
                        Bin bin=new Bin();
                        bin.setDeletetimestamp(bints);
                        bin.setDeletenote(deleteNote);
                        Log.e("TAG", " delete"+ deleteNote);
                        //insert Data in recycle bin**********
                        appDatabase.getbinDao().insert(bin);
                        //Delete data from the adapter*********
                        appDatabase.getuserDao().deleteall(user);
                        //data remove from the list
                        data.remove(data.get(position));
                        notifyDataSetChanged();
                        //or use this for better perfomance
                        deletedialog.dismiss();

                    }
                });

                //cancel button of Delete dialog box
                cancelButton=view1.findViewById(R.id.notdelete);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deletedialog.dismiss();
                    }
                });

                //set view for dialog box

                deletebuilder.setView(view1);
                deletedialog=deletebuilder.create();
                deletedialog.show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //filter list search method
    public void filterlist(ArrayList<User> filteredList) {

        data=filteredList;
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView,timestamp;

        //ConstraintLayout notelisetner;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.noteTextview);
            timestamp=itemView.findViewById(R.id.notetimeTV);
        }
    }

    //updata data on the Recycler view
    public void UpdateData(int position,User user)
    {
        data.remove(position);
        data.add(user);
        notifyItemChanged(position);
        notifyDataSetChanged();
    }
}
