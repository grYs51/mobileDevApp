package com.example.mobiledevapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.preference.PreferenceManager;
import android.content.Context;
import androidx.core.view.MenuItemCompat;
import android.widget.Button;
import android.widget.SearchView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.mobiledevapp.extra.MESSAGE";
    public static int counter;
    public static SharedPreferences settings;
    private String sharedPrefFile = "com.example.mobiledevapp";


    public boolean[] listOptions={true,true,true};
    public static final int BOOL_REQUEST = 1;
    private FirebaseAnalytics mFirebaseAnalytics;


    public static TextView tvCounter;


    public static void saveCount() { //Dit crasht de app


        counter = counter + 1;

        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("counter", counter);

        editor.commit();
        MainActivity.tvCounter.setText(Integer.toString(counter));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar); //Shows name of app in the toolbar.
        setSupportActionBar(myToolbar);
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        settings = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        counter = settings.getInt("counter", 0);

         tvCounter = findViewById(R.id.tvCounter);

        tvCounter.setText(Integer.toString(counter));


        Button toSoundboard = findViewById(R.id.toSoundboardButton);
        Button toSettings = findViewById(R.id.toSettingsButton);
        toSoundboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSoundboard();
            }
        });

        toSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSettings();
            }
        });
    }



    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Log.d("Menu-",item.toString());
        Log.d("Menu-",item.getItemId()+"");
        switch (item.getItemId()){
            case R.id.action_settings:
                launchSettings();
                break;
//            case R.id.action_background:
//                //launchBackground();
//                break;
            case R.id.action_soundboard:
                launchSoundboard();
                break;



        }
        return  super.onOptionsItemSelected(item);
    }

    public void logEvent (String name){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    private void launchSettings() {
        Intent intent = new Intent(this,SettingsActivity.class);
        intent.putExtra(EXTRA_MESSAGE,listOptions);
        startActivityForResult(intent, BOOL_REQUEST);
        logEvent("LaunchSettings");

    }
    private void launchSoundboard() {
        Intent intent = new Intent(this,SoundBoardActivity.class);
        intent.putExtra(EXTRA_MESSAGE,listOptions);
        startActivityForResult(intent, BOOL_REQUEST);
    }


    }

