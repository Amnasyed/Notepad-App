package com.example.notepad;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.notepad.AppDatabase.Appdatabase;
import com.example.notepad.AppDatabase.Bin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class RecyclebinAdapter extends RecyclerView.Adapter<RecyclebinAdapter.MyBinViewHolder> {
    ArrayList<Bin> data;
    Context context;
    AlertDialog.Builder deletebuilder;
    AlertDialog deletedialog;
    Button deleteButton,cancelButton;
    Appdatabase appDatabase;

    public RecyclebinAdapter(ArrayList<Bin> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyBinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.deletelistitem,parent,false);
        return new MyBinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBinViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Bin bin=data.get(position);
        String note=bin.getDeletenote();
        String bintimeStamp=bin.getDeletetimestamp();

        //convert timestamp to day/,month/year & hours/seconds  &am/pm
        Calendar calendar=Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(Long.parseLong(String.valueOf(bintimeStamp)));
        String dateTime= DateFormat.format("dd/MM/yyyy hh:mm aa",calendar).toString();


        //set text in holder
        holder.textView.setText(note);
        holder.bintime.setText(dateTime);
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
                        appDatabase = Appdatabase.getdbInstance(context);
                        //    String  deleteNote= bin.getDeletenote();
                        //Delete data from the adapter*********
                        appDatabase.getbinDao().delete(bin);
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

    // item count**********
    @Override
    public int getItemCount() {
        return data.size();
    }

    // search data of deleted item
    public void filterlist(ArrayList<Bin> filteredList) {
        data=filteredList;
        notifyDataSetChanged();
    }

    public class MyBinViewHolder extends RecyclerView.ViewHolder {

        TextView textView,bintime;
        //ConstraintLayout deletenote;
        public MyBinViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.deletenoteTextview);
            bintime=itemView.findViewById(R.id.deletetimeTV);
          //  deletenote=itemView.findViewById(R.id.deletenote);
        }
    }
}
