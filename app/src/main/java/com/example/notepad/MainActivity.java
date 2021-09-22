package com.example.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.notepad.AppDatabase.Appdatabase;
import com.example.notepad.AppDatabase.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Initialize variables
    ArrayList<User> arrayList;
    NotepadAdapter adapter;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView imagemenu;
    FloatingActionButton Notepad;
    RecyclerView recyclerView;
    private Appdatabase appDatabase;
    User user=new User();
    EditText edittextToolbar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign values

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_Layout);
        navigationView = findViewById(R.id.navmenu);
        navigationView.setNavigationItemSelectedListener(this);
        imagemenu=findViewById(R.id.menuImage);
        recyclerView=findViewById(R.id.notepadRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        appDatabase= Appdatabase.getdbInstance(this);
        Notepad=findViewById(R.id.add_fab);
        edittextToolbar=findViewById(R.id.SearchToolbar);

        //search listner on toolbar  by edittext
        edittextToolbar.addTextChangedListener(new TextWatcher() {
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

        // Array list pass in database
        arrayList =  (ArrayList<User>) appDatabase.getuserDao().getalluser();
        //set adapter
        Log.e("TAG111", "onCreate: check color " + arrayList );
        adapter= new NotepadAdapter(arrayList,MainActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //update adapter*********
        initAdapter();


        //Notepad Screen**********
        Notepad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, Notepad.class);
                intent.putExtra("from","activity");
                startActivity(intent);
                finishAffinity();
            }
        });

        //image Menu click Listener
        imagemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //Toggle

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        toggle.syncState();
    }

    //update data of recycler view

    private void initAdapter() {
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_myNotes:
                Toast.makeText(MainActivity.this, "my Notes panel is open", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                Intent notepad=new Intent(MainActivity.this,Notepad.class);
                notepad.putExtra("from","activity");
                startActivity(notepad);
                finishAffinity();
                break;

            case R.id.menu_recyclebin:
                Toast.makeText(MainActivity.this, "recycle bin panel is open", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                Intent intent=new Intent(MainActivity.this,RecycleBin.class);
                startActivity(intent);
                finishAffinity();
                break;
            case R.id.menu_theme:
                drawerLayout.closeDrawer(GravityCompat.START);
                Intent intent1=new Intent(MainActivity.this,Theme.class);
                startActivity(intent1);
                finishAffinity();
                break;
        }
        return true;

    }

    //filter  search result
    private void filter(String text) {

        ArrayList<User> filteredList=new ArrayList<>();
        for(User item:arrayList)
        {
            if(item.getEditdetail().toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(item);
            }
        }
        adapter.filterlist(filteredList);
    }
    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}