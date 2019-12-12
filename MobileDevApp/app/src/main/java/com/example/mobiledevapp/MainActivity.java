package com.example.mobiledevapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.smalltalkapp.extra.MESSAGE";
    public static int counter;
    public boolean[] listOptions={true,true,true};
    public static final int BOOL_REQUEST = 1;
    private FirebaseAnalytics mFirebaseAnalytics;

    public static TextView tvCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar); //Shows name of app in the toolbar.
        setSupportActionBar(myToolbar);
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

         tvCounter = findViewById(R.id.tvCounter);
        tvCounter.setText(String.valueOf(counter));
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
