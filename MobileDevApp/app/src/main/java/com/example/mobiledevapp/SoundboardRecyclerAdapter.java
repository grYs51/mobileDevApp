package com.example.mobiledevapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SoundboardRecyclerAdapter extends RecyclerView.Adapter<SoundboardRecyclerAdapter.SoundBoardViewHolder> {

    private ArrayList<SoundObject> soundObjects;

    public SoundboardRecyclerAdapter(ArrayList<SoundObject> SoundObjects){
        this.soundObjects = SoundObjects;
    }

    @Override
    public SoundBoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sound_item,null);

    return new SoundBoardViewHolder(itemView);
}

@Override
    public void onBindViewHolder(SoundBoardViewHolder holder, int position){

        final SoundObject tempObject = soundObjects.get(position);
        final Integer soundID = tempObject.getItemID();


        holder.itemTextView.setText(tempObject.getItemName());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                EventHandlerClass.startMediaPlayer(v,soundID);
            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                EventHandlerClass.popupManager(v , tempObject);
                return true;
            }
        });
}





@Override
    public  int getItemCount(){

    return soundObjects.size();
}

public class SoundBoardViewHolder extends RecyclerView.ViewHolder {

    TextView itemTextView;

    public SoundBoardViewHolder(View itemView) {
        super(itemView);
        itemTextView = itemView.findViewById(R.id.TextViewItem);
    }
}
}
