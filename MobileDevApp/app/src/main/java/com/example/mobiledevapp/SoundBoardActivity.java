package com.example.mobiledevapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;


public class SoundBoardActivity extends AppCompatActivity {

    public boolean[] listOptions;
    public static final String EXTRA_REPLY = "com.example.smalltalkapp.extra.REPLY";


    String[] soundList = {"test1", "test2", "test3"};

    RecyclerView SoundView;
    SoundboardRecyclerAdapter SoundAdapter = new SoundboardRecyclerAdapter(soundList);
    RecyclerView.LayoutManager SoundLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundboard);

        Toolbar myToolbar = findViewById(R.id.toolbar_soundboard); //Shows name of app in the toolbar.
        setSupportActionBar(myToolbar);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SoundView = (RecyclerView) findViewById(R.id.soundboardRecyclerView);

        SoundLayoutManager = new GridLayoutManager(this, 3);
        SoundView.setLayoutManager(SoundLayoutManager);
        SoundView.setAdapter(SoundAdapter);



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
