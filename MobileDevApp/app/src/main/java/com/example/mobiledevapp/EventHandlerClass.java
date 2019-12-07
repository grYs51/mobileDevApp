package com.example.mobiledevapp;

import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class EventHandlerClass {


    private static final String LOG_TAG = "EVENTHANDLER";
    private static MediaPlayer mp;

    public static void startMediaPlayer(View view, Integer soundID){
        try{
            if (soundID != null){
                if (mp != null)
                    mp.reset();

                mp = MediaPlayer.create(view.getContext(), soundID);
                mp.start();
            }

        } catch (Exception e){
            Log.e(LOG_TAG, "Initialiseren van mediaplayer mislukt" + e.getMessage());
        }
    }

    public static void releaseMediaPlayer(){

        if (mp != null){
            mp.release();
            mp = null;
        }
    }

    public static void popupManager(final View view, final SoundObject soundObject){
        PopupMenu popup = new PopupMenu(view.getContext(),view);
        popup.getMenuInflater().inflate(R.menu.longclick,popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.action_send || item.getItemId() == R.id.action_ringtone){
                    final String fileName = soundObject.getItemName() + ".mp3";
                    File storage = Environment.getRootDirectory(); //dit is een gokje hehe
                    File directory = new File(storage.getAbsolutePath()+ "/my_soundboard/");
                    directory.mkdirs();

                    final File file = new File(directory, fileName);

                    InputStream in = view.getContext().getResources().openRawResource(soundObject.getItemID());

                    try{

                        OutputStream out = new FileOutputStream(file);
                        byte[] buffer = new byte[1024];

                        int len;
                        while((len = in.read(buffer,0,buffer.length)) != -1){
                            out.write(buffer, 0,len);
                        }

                        in.close();
                        out.close();

                    } catch (IOException e){

                        Log.e(LOG_TAG,"Opslaan van bestand mislukt "+ e.getMessage());
                    }
                }
                return true;
            }
        });

        popup.show();
    }

}
