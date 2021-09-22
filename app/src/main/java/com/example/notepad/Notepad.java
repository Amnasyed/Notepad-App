package com.example.notepad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notepad.AppDatabase.Appdatabase;
import com.example.notepad.AppDatabase.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

public class Notepad extends AppCompatActivity {

    AlertDialog.Builder themeBuilder;
    AlertDialog themeDialog;
    String str,note;
    User noteupdate;
    EditText editText;
    Appdatabase appdatabase;
    FloatingActionButton savenote;
    LinearLayout notepadToolbar;
    View view2;
    int a;
    TextView red,lightGreen,yellow,darkGreen,orange,whColor,golden,brown,blue,grey,purple,pink;
    ImageView backarrownotepad,menuNotepad,themeNotepad;
    User user=new User();

    String from ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        //get view for window
        view2=this.getWindow().getDecorView();
        view2.setBackgroundResource(R.color.white);
        //****************************************************
        notepadToolbar=findViewById(R.id.notepadToolbar);
        editText=findViewById(R.id.etNotepad);
        savenote=findViewById(R.id.sendNotepad);
        backarrownotepad=findViewById(R.id.backNotepad);
        menuNotepad=findViewById(R.id.menuNotepad);
        themeNotepad=findViewById(R.id.themeNotepad);

        appdatabase= Appdatabase.getdbInstance(this);


        //get intent value for updating notepad

        from = getIntent().getStringExtra("from");
        Log.e("TAG", "From on: "+ from );

        if (from.equalsIgnoreCase("adapter")) {
            noteupdate = getIntent().getParcelableExtra("User");
            note = noteupdate.getEditdetail();
            editText.setText(note);
        }

        //back arrow on the top of the screen****************
        backarrownotepad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainscreen=new Intent(Notepad.this,MainActivity.class);
                startActivity(mainscreen);
                finishAffinity();

            }
        });
//*****theme***************************
        themeNotepad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeBuilder=new AlertDialog.Builder(Notepad.this);
                themeBuilder.setTitle("themes");
                themeBuilder.setCancelable(false);
                View view1= LayoutInflater.from(getApplicationContext()).inflate(R.layout.themedialog,null,false);

                //clicklistners here****************

                //red color
                red=view1.findViewById(R.id.red);
                red.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Resources resources =getResources();
                        a=(getResources().getColor(R.color.red_colorPrimary));
                        view2.setBackgroundColor(a);
                        //      resources.getIdentifier("Red","color",getPackageName())
                        themeDialog.dismiss();
                    }
                });

                //lightGreen
                lightGreen=view1.findViewById(R.id.lightgreen);
                lightGreen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Resources resources =getResources();
                        a=(getResources().getColor(R.color.LightGreen));
                        view2.setBackgroundColor(a);
                        //      resources.getIdentifier("Red","color",getPackageName())
                        themeDialog.dismiss();
                    }
                });

                //Yellow color
                yellow=view1.findViewById(R.id.yellow);
                yellow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Resources resources =getResources();
                        a=(getResources().getColor(R.color.red_colorAccent));
                        view2.setBackgroundColor(a);
                        //      resources.getIdentifier("Red","color",getPackageName())
                        themeDialog.dismiss();
                    }
                });



                //darkGreen
                darkGreen=view1.findViewById(R.id.darkgreen);
                darkGreen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Resources resources =getResources();
                        a=(getResources().getColor(R.color.green_colorPrimary));
                        view2.setBackgroundColor(a);
                        //      resources.getIdentifier("Red","color",getPackageName())
                        themeDialog.dismiss();
                    }
                });

                // orange
                orange=view1.findViewById(R.id.orange);
                orange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Resources resources =getResources();
                        a=(getResources().getColor(R.color.darkpink_colorAccent));
                        view2.setBackgroundColor(a);
                        //      resources.getIdentifier("Red","color",getPackageName())
                        themeDialog.dismiss();
                    }
                });
               // whColor
                whColor=view1.findViewById(R.id.whatsappColorApp);
                whColor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Resources resources =getResources();
                        a=(getResources().getColor(R.color.teal_700));
                        view2.setBackgroundColor(a);
                        //      resources.getIdentifier("Red","color",getPackageName())
                        themeDialog.dismiss();
                    }
                });

                //golden color

                golden=view1.findViewById(R.id.golden);
                golden.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Resources resources =getResources();
                        a=(getResources().getColor(R.color.grey_colorAccent));
                        view2.setBackgroundColor(a);
                        //      resources.getIdentifier("Red","color",getPackageName())
                        themeDialog.dismiss();
                    }
                });

                //brown
                brown=view1.findViewById(R.id.brown);
                brown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Resources resources =getResources();
                        a=(getResources().getColor(R.color.brown_colorPrimary));
                        view2.setBackgroundColor(a);
                        //      resources.getIdentifier("Red","color",getPackageName())
                        themeDialog.dismiss();
                    }
                });


                //blue color
                blue=view1.findViewById(R.id.blue);
                blue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Resources resources =getResources();
                        a=(getResources().getColor(R.color.blue_colorPrimary));
                        view2.setBackgroundColor(a);
                        //      resources.getIdentifier("Red","color",getPackageName())
                        themeDialog.dismiss();
                    }
                });

                //grey color

                grey=view1.findViewById(R.id.grey);
                grey.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Resources resources =getResources();
                        a=(getResources().getColor(R.color.grey_colorPrimary));
                        view2.setBackgroundColor(a);
                        //      resources.getIdentifier("Red","color",getPackageName())
                        themeDialog.dismiss();
                    }
                });
                //purple

                purple=view1.findViewById(R.id.purple);
                purple.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Resources resources =getResources();
                        a=(getResources().getColor(R.color.darkpink_colorPrimary));
                        view2.setBackgroundColor(a);
                        //      resources.getIdentifier("Red","color",getPackageName())
                        themeDialog.dismiss();
                    }
                });
                //pink

                pink=view1.findViewById(R.id.pink);
                pink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Resources resources =getResources();
                        a=(getResources().getColor(R.color.brown_colorAccent));
                        view2.setBackgroundColor(a);
                        //      resources.getIdentifier("Red","color",getPackageName())
                        themeDialog.dismiss();
                    }
                });

                themeBuilder.setView(view1);
                themeDialog=themeBuilder.create();
                themeDialog.show();

            }
        });


        //savenote clicklistner**************
        savenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str=editText.getText().toString();
                String color=String.valueOf(a);

                Log.e("TAG", "onClick: "+color);
                if(from.equalsIgnoreCase("activity"))
                {

                    Log.e("TAG4", "onClick: "+color );
                    //getting timestamp
                    Long tsLong = System.currentTimeMillis();
                    String ts = tsLong.toString();
//                    User user=new User();
                  /*  user.setTimestamp(ts);
                    user.setColourCode((color));
                    user.setEditdetail(str);
                  */  User mUser = new User(str,ts,color);


                    Log.e("TAG4", "onClick: "+mUser.getColourCode() );

                    appdatabase.getuserDao().insert(mUser);
                    Toast.makeText(Notepad.this, "Saved", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Log.e("TAG", "update " + str );
                    noteupdate.setEditdetail(str);
                    Log.e("TAG", "user "+ noteupdate.getEditdetail());
                    Log.e("TAG", "user "+ noteupdate.getId());
                    appdatabase.getuserDao().update(noteupdate);
                    Toast.makeText(Notepad.this, "data updated successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //menu popup notepad************************
        menuNotepad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(Notepad.this,view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_notepad,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId())
                        {
                            case R.id.undo_edit:

                                str=editText.getText().toString();
                                Log.e("TAG", "onMenuItemClick: "+ str );
                                editText.setText("");
                                return true;
                            case R.id.deletemenu:
                                Intent deletemenu=new Intent(Notepad.this,MainActivity.class);
                                startActivity(deletemenu);
//                                return true;
//                            case R.id.sharemenu:
//                                Intent sharemenu=new Intent(Notepad.this,Notepad.class);
//                                startActivity(sharemenu);
//                                return true;
                        }

                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent mainscreen=new Intent(Notepad.this,MainActivity.class);
        startActivity(mainscreen);

        super.onBackPressed();
        finishAffinity();
    }
}