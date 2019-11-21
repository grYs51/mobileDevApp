package com.example.mobiledevapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mobiledevapp.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.smalltalkapp.extra.MESSAGE";
    public boolean[] listOptions={true,true,true};
    public static final int BOOL_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar); //Shows name of app in the toolbar.
        setSupportActionBar(myToolbar);
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
            case R.id.action_background:
                //launchBackground();
                break;
            case  R.id.action_login:
                launchLogin();
                break;
            case  R.id.action_calender:
                launchCalender();
                break;

        }
        return  super.onOptionsItemSelected(item);
    }

    private void launchCalender() {
        Intent intent = new Intent(this, CalenderActivity.class);
        intent.putExtra(EXTRA_MESSAGE,listOptions);
        startActivityForResult(intent, BOOL_REQUEST);
    }

    private void launchLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(EXTRA_MESSAGE,listOptions);
        startActivityForResult(intent, BOOL_REQUEST);
    }

    private void launchSettings() {
        Intent intent = new Intent(this,SettingsActivity.class);
        intent.putExtra(EXTRA_MESSAGE,listOptions);
        startActivityForResult(intent, BOOL_REQUEST);
    }
}
