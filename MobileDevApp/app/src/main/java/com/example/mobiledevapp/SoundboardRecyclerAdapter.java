package com.example.mobiledevapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SoundboardRecyclerAdapter extends RecyclerView.Adapter<SoundboardRecyclerAdapter.ViewHolder> {

//    private String[] soundObjects;
//    public SoundboardRecyclerAdapter(String[] SoundObjects){
//        this.soundObjects = SoundObjects;
//    };
//@Override
//    public SoundBoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
//
//    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sound_item,null);
//
//    return new SoundBoardViewHolder(itemView);
//}
//
//@Override
//    public void onBindViewHolder(SoundBoardViewHolder holder, int position){
//
//    holder.itemTextView.setText(soundObjects[position]);
//}
//
//@Override
//    public  int getItemCount(){
//
//    return soundObjects.length;
//}
//
//public class SoundBoardViewHolder extends RecyclerView.ViewHolder {
//
//    TextView itemTextView;
//
//    public SoundBoardViewHolder(View itemView) {
//        super(itemView);
//        itemTextView = (TextView) itemView.findViewById(R.id.TextViewItem);
//    }
//}

    private List<Integer> soundObjects;
    private List<String> TextViewItem;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    SoundboardRecyclerAdapter(Context context, List<Integer> sounds, List<String> soundName) {
        this.mInflater = LayoutInflater.from(context);
        this.soundObjects = sounds;
        this.TextViewItem = soundName;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.sound_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int color = soundObjects.get(position);
        String soundName = TextViewItem.get(position);
        holder.myView.setBackgroundColor(color);
        holder.myTextView.setText(soundName);
    }

    @Override
    public int getItemCount() {
        return TextViewItem.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View myView;
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myView = itemView.findViewById(R.id.ImageViewItem);
            myTextView = itemView.findViewById(R.id.TextViewItem);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return TextViewItem.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

