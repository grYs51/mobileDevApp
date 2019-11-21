package com.example.mobiledevapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

public class CalenderActivity extends AppCompatActivity {

    public boolean[] listOptions;
    public static final String EXTRA_REPLY = "com.example.smalltalkapp.extra.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);



        //Toolbar
        Toolbar myToolbar = findViewById(R.id.toolbar_calender); //Shows name of app in the toolbar.
        setSupportActionBar(myToolbar);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY,listOptions);
        setResult(RESULT_OK,replyIntent);
        finish();
        return true;
    }
}
