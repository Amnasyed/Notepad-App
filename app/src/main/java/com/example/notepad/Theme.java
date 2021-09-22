package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class Theme extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Switch aSwitch;

    TextView red,lightGreen,yellow,darkGreen,orange,whColor;
    String themeName;
    TextView cancel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set theme
        sharedPreferences = getSharedPreferences("Theme", Context.MODE_PRIVATE);
        themeName = sharedPreferences.getString("ThemeName", "Default");

        if (themeName.equalsIgnoreCase("AppTheme_red")) {
            // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            setTheme(R.style.AppTheme_red);
        } else if (themeName.equalsIgnoreCase("AppTheme_green")) {
            setTheme(R.style.AppTheme_green);

        }
        else if (themeName.equalsIgnoreCase("AppTheme_darkgreen")) {
            setTheme(R.style.AppTheme_darkgreen);

        }
        else if (themeName.equalsIgnoreCase("AppTheme_orange")) {
            setTheme(R.style.AppTheme_orange);

        }
        else if (themeName.equalsIgnoreCase("AppTheme_dark_blue")) {
            setTheme(R.style.AppTheme_dark_blue);

        }
        else  {
            setTheme(R.style.AppTheme_yellow);
        }


        setContentView(R.layout.activity_theme);
        red=findViewById(R.id.red);
        cancel=findViewById(R.id.cancelTheme);
        orange=findViewById(R.id.orange);
        lightGreen=findViewById(R.id.lightgreen);
        darkGreen=findViewById(R.id.darkgreen);
        whColor=findViewById(R.id.whatsappColorApp);
        yellow=findViewById(R.id.yellow);
        aSwitch=findViewById(R.id.switchDark);
//        SharedPreferences sharedPreferences
//                = getSharedPreferences(
//                "sharedPrefs", MODE_PRIVATE);
//        final SharedPreferences.Editor editor
//                = sharedPreferences.edit();
//        final boolean isDarkModeOn
//                = sharedPreferences
//                .getBoolean(
//                        "isDarkModeOn", false);
//

//        // When user reopens the app
//        // after applying dark/light mode
//        if (isDarkModeOn) {
//            AppCompatDelegate
//                    .setDefaultNightMode(
//                            AppCompatDelegate
//                                    .MODE_NIGHT_YES);
//        }
//        else {
//            AppCompatDelegate
//                    .setDefaultNightMode(
//                            AppCompatDelegate
//                                    .MODE_NIGHT_NO);
//        }
        //cancel theme**************
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Theme.this,MainActivity.class);
                startActivity(intent);
                finishAffinity();

            }
        });

        //red theme for whole window
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTheme("AppTheme_red");
                Intent intent=new Intent(Theme.this,MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
        //orange theme for whole window
        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTheme("AppTheme_orange");
                Intent intent=new Intent(Theme.this,MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
        //lightgreen theme for whole window
        lightGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTheme("AppTheme_green");
                Intent intent=new Intent(Theme.this,MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
        //darkgreen theme for whole window
        darkGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTheme("AppTheme_darkgreen");
                Intent intent=new Intent(Theme.this,MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
        //tint  theme for whole window
        whColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTheme("AppTheme_dark_blue");
                Intent intent=new Intent(Theme.this,MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
        //yellow theme for whole window
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTheme("AppTheme_yellow");
                Intent intent=new Intent(Theme.this,MainActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });


//        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
//                if (isDarkModeOn) {
//
//                    // if dark mode is on it
//                    // will turn it off
//                    AppCompatDelegate
//                            .setDefaultNightMode(
//                                    AppCompatDelegate
//                                            .MODE_NIGHT_NO);
//                    // it will set isDarkModeOn
//                    // boolean to false
//                    editor.putBoolean(
//                            "isDarkModeOn", false);
//                    editor.apply();
//                }
//                else {
//
//                    // if dark mode is off
//                    // it will turn it on
//                    AppCompatDelegate
//                            .setDefaultNightMode(
//                                    AppCompatDelegate
//                                            .MODE_NIGHT_YES);
//
//                    // it will set isDarkModeOn
//                    // boolean to true
//                    editor.putBoolean(
//                            "isDarkModeOn", true);
//                    editor.apply();
//
//                }
//            }
//        });


    }
    public void setTheme(String name) {
        // Create preference to store theme name
        SharedPreferences preferences = getSharedPreferences("Theme", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ThemeName", name);
        editor.apply();
        recreate();
    }
}
