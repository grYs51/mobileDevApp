package com.example.mobiledevapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class SoundboardRecyclerAdapter extends RecyclerView.Adapter<SoundboardRecyclerAdapter.SoundBoardViewHolder> {

    private String[] soundObjects;
    public SoundboardRecyclerAdapter(String[] SoundObjects){
        this.soundObjects = SoundObjects;
    };
@Override
    public SoundBoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sound_item,null);

    return new SoundBoardViewHolder(itemView);
}

@Override
    public void onBindViewHolder(SoundBoardViewHolder holder, int position){

    holder.itemTextView.setText(soundObjects[position]);
}

@Override
    public  int getItemCount(){

    return soundObjects.length;
}

public class SoundBoardViewHolder extends RecyclerView.ViewHolder {

    TextView itemTextView;

    public SoundBoardViewHolder(View itemView) {
        super(itemView);
        itemTextView = (TextView) itemView.findViewById(R.id.TextViewItem);
    }
}
}
