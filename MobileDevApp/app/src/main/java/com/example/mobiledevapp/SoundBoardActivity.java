package com.example.mobiledevapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class SoundBoardActivity extends AppCompatActivity implements SoundboardRecyclerAdapter.ItemClickListener {

    public boolean[] listOptions;
    public static final String EXTRA_REPLY = "com.example.smalltalkapp.extra.REPLY";
    private SoundboardRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundboard);

        //toolbar
        Toolbar myToolbar = findViewById(R.id.toolbar_soundboard); //Shows name of app in the toolbar.
        setSupportActionBar(myToolbar);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //RecyclerView 1

        ArrayList<Integer> viewSounds = new ArrayList<>();
        viewSounds.add(Color.WHITE);
        viewSounds.add(Color.GRAY);
        viewSounds.add(Color.GREEN);
        viewSounds.add(Color.BLUE);
        viewSounds.add(Color.YELLOW);
        viewSounds.add(Color.MAGENTA);
        viewSounds.add(Color.RED);
        viewSounds.add(Color.CYAN);

        ArrayList<String> soundNames = new ArrayList<>();
        soundNames.add("sound 1");
        soundNames.add("sound 2");
        soundNames.add("sound 3");
        soundNames.add("sound 4");
        soundNames.add("sound 5");
        soundNames.add("sound 6");
        soundNames.add("sound 7");
        soundNames.add("sound 8");

        //setup recyclerView

        RecyclerView recyclerView = findViewById(R.id.soundboardRecyclerView);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(SoundBoardActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        adapter = new SoundboardRecyclerAdapter(this, viewSounds, soundNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

//        SoundView = (RecyclerView) findViewById(R.id.soundboardRecyclerView);
//        SoundLayoutManager = new GridLayoutManager(this, 3);
//        SoundView.setLayoutManager(SoundLayoutManager);
//        SoundView.setAdapter(SoundAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY,listOptions);
        setResult(RESULT_OK,replyIntent);
        finish();
        return true;
    }

    @Override
    public void onItemClick(View view, int position) {

        Toast.makeText(this, "playing sound: " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
    }
}
