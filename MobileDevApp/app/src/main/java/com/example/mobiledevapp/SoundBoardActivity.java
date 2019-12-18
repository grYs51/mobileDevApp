package com.example.mobiledevapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class SoundBoardActivity extends AppCompatActivity {

    public boolean[] listOptions;
    public static final String EXTRA_REPLY = "com.example.mobiledevapp.extra.REPLY";

    ArrayList<SoundObject> soundList = new ArrayList<SoundObject>();

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
        List<String> nameList = Arrays.asList(getResources().getStringArray(R.array.soundNames)); //Array van button namen opvragen

        SoundObject[] soundItems = {
                new SoundObject(nameList.get(0), R.raw.audio01),
                new SoundObject(nameList.get(1), R.raw.audio02),
                new SoundObject(nameList.get(2), R.raw.audio03),
                new SoundObject(nameList.get(3), R.raw.audio04),
                new SoundObject(nameList.get(4), R.raw.deeznuts),
                new SoundObject(nameList.get(5), R.raw.gotchabitch),
                new SoundObject(nameList.get(6), R.raw.gtavwastedbusted),
                new SoundObject(nameList.get(7), R.raw.idontgiveaf),
                new SoundObject(nameList.get(8), R.raw.illuminaticonfirmed),
                new SoundObject(nameList.get(9), R.raw.leeroyjenkins),
                new SoundObject(nameList.get(10), R.raw.mlghorns),
                new SoundObject(nameList.get(11), R.raw.ohbabyatripple),
                new SoundObject(nameList.get(12), R.raw.suprisemotherfcker),
                new SoundObject(nameList.get(13), R.raw.tadaah),
                new SoundObject(nameList.get(14), R.raw.youneedtostfu),
                new SoundObject(nameList.get(15), R.raw.airhornsonata),
                new SoundObject(nameList.get(16), R.raw.damnsonwheredyouthis),
                new SoundObject(nameList.get(17), R.raw.hit),
                new SoundObject(nameList.get(18), R.raw.neverdonethat),
                new SoundObject(nameList.get(19), R.raw.sanic),
                new SoundObject(nameList.get(20), R.raw.shotsfired),
                new SoundObject(nameList.get(21), R.raw.wow),


        };
        soundList.addAll(Arrays.asList(soundItems)); //items van hierboven in de lijst plaatsen
        SoundAdapter.CopyList();


        SoundView = findViewById(R.id.soundboardRecyclerView);

        SoundLayoutManager = new GridLayoutManager(this, 3);
        SoundView.setLayoutManager(SoundLayoutManager);
        SoundView.setAdapter(SoundAdapter);


        //DEBUG
        for (SoundObject item : soundList){
            //LOG DE NAAM VAN DE SOUNDS
            Log.d("soundList item naam",item.getItemName());

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventHandlerClass.releaseMediaPlayer();
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, listOptions);
        setResult(RESULT_OK, replyIntent);
        finish();
        return true;
    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_soundboard,menu);

        MenuItem searchItem = menu.findItem(R.id.action_searh);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE); //Omdat onze waarden toch automatisch updaten hebben we het vergrootglas in het toetsenbord niet meer nodig, daarom veranderen we het icoontje op het toetsenbord naar een vinkje

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                SoundAdapter.getFilter().filter(newText);

                return false;
            }
        });
        return true;
    }



}
