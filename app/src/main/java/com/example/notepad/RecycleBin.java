package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.notepad.AppDatabase.Appdatabase;
import com.example.notepad.AppDatabase.Bin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RecycleBin extends AppCompatActivity {

    //Initialize variables
    ArrayList<Bin> arrayList;
    RecyclebinAdapter adapter;
    RecyclerView deleterecyclerView;
    Appdatabase appDatabase;
    EditText editdtTextToolbar;
    Bin bin=new Bin();
    FloatingActionButton deleteFloatAction;
    ImageView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_bin);
        deleterecyclerView=findViewById(R.id.recyclebinRV);
        deleterecyclerView.setLayoutManager(new LinearLayoutManager(this));
        appDatabase= Appdatabase.getdbInstance(this);
        editdtTextToolbar=findViewById(R.id.dtSearchToolbar);
        deleteFloatAction=findViewById(R.id.delete);
        menu=findViewById(R.id.recyclemenuImage);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RecycleBin.this, MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });


        //search listner on toolbar  by edittext
        editdtTextToolbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
        // set adapter
        // Array list pass in database
        arrayList =  (ArrayList<Bin>) appDatabase.getbinDao().getadeleteUser();
        //set adapter
        Log.e("TAG", "onCreate: " + arrayList );
        adapter= new RecyclebinAdapter(arrayList,RecycleBin.this);

        deleterecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //delete  all data on adapter****************
        deleteFloatAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //arrayList.clear();
                appDatabase.getbinDao().delete();
                adapter.notifyDataSetChanged();
                Intent intent=new Intent(RecycleBin.this,MainActivity.class);
                Toast.makeText(RecycleBin.this, "All data deleted", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finishAffinity();
            }
        });
    }

    private void filter(String text) {

        ArrayList<Bin> filteredList=new ArrayList<>();
        for(Bin item:arrayList)
        {
            if(item.getDeletenote().toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(item);
            }
        }
        adapter.filterlist(filteredList);
    }
}