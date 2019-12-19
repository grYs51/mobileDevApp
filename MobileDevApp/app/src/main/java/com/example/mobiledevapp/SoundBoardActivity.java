package com.example.mobiledevapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SoundBoardActivity extends AppCompatActivity {

    public boolean[] listOptions;
    public static final String EXTRA_REPLY = "com.example.smalltalkapp.extra.REPLY";

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
                new SoundObject(nameList.get(22), R.raw.pacthetankengine),
                new SoundObject(nameList.get(23), R.raw.kanyetankengine),
                new SoundObject(nameList.get(24), R.raw.kfc),
                new SoundObject(nameList.get(25), R.raw.thepriceisright),
                new SoundObject(nameList.get(26), R.raw.wtfsoundeffect),
                new SoundObject(nameList.get(27), R.raw.mlgcenturyfox),
                new SoundObject(nameList.get(28), R.raw.smallloan),
                new SoundObject(nameList.get(29), R.raw.angryvoice),
                new SoundObject(nameList.get(30), R.raw.inchesdeep),
                new SoundObject(nameList.get(31), R.raw.byehavea),
                new SoundObject(nameList.get(32), R.raw.hellodarkness),
                new SoundObject(nameList.get(33), R.raw.canttie),
                new SoundObject(nameList.get(34), R.raw.thuglife),
                new SoundObject(nameList.get(35), R.raw.mlgvoice),
                new SoundObject(nameList.get(36), R.raw.nice),
                new SoundObject(nameList.get(37), R.raw.thatsracist),
                new SoundObject(nameList.get(38), R.raw.nonigga),
                new SoundObject(nameList.get(39), R.raw.thuglifeeffect),
                new SoundObject(nameList.get(40), R.raw.thuglifeeffect2),
                new SoundObject(nameList.get(41), R.raw.universalmlg),
                new SoundObject(nameList.get(42), R.raw.watchur),
                new SoundObject(nameList.get(43), R.raw.whatrthose),
                new SoundObject(nameList.get(44), R.raw.whyyou),
                //new SoundObject(nameList.get(44), R.raw.),
                //new SoundObject(nameList.get(45), R.raw.),



        };

        soundList.addAll(Arrays.asList(soundItems)); //items van hierboven in de lijst plaatsen


        SoundView = findViewById(R.id.soundboardRecyclerView);

        SoundLayoutManager = new GridLayoutManager(this, 3);
        SoundView.setLayoutManager(SoundLayoutManager);
        SoundView.setAdapter(SoundAdapter);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventHandlerClass.releaseMediaPlayer();
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
