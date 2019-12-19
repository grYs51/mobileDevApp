package com.example.mobiledevapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.EventLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SoundboardRecyclerAdapter extends RecyclerView.Adapter<SoundboardRecyclerAdapter.SoundBoardViewHolder> implements Filterable {

    private ArrayList<SoundObject> soundObjects;
    private ArrayList<SoundObject> soundObjectsCopy; //Voor het opzoeken van de items uit de orignele lijst.

    public SoundboardRecyclerAdapter(ArrayList<SoundObject> SoundObjects) {
        this.soundObjects = SoundObjects;
    }

    public void CopyList(){
        this.soundObjectsCopy = new ArrayList<>(soundObjects);

    }


    @Override
    public SoundBoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sound_item, null);
        return new SoundBoardViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(SoundBoardViewHolder holder, int position) {

        final SoundObject tempObject = soundObjects.get(position);
        final Integer soundID = tempObject.getItemID();



        holder.itemTextView.setText(tempObject.getItemName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventHandlerClass.startMediaPlayer(v, soundID);
                MainActivity.counter = MainActivity.counter + 1;
                MainActivity.tvCounter.setText(String.valueOf(MainActivity.counter));
            }
        });
    }

    @Override
    public int getItemCount() {

        return soundObjects.size();
    }

    public class SoundBoardViewHolder extends RecyclerView.ViewHolder {

        TextView itemTextView;

        public SoundBoardViewHolder(View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.TextViewItem);
        }
    }


    //ZOEKFUNCTIE
    @Override
    public Filter getFilter() {

        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) { //wordt uitgevoerd in de achtergrond zodat de app niet blijft vasthangen
            List<SoundObject> filteredList = new ArrayList<>();

            Log.d("constraintText", constraint.toString().toLowerCase().trim());
            if (constraint == null || constraint.length() == 0) { //Constraint is de search query van de gebruiker.
                filteredList.addAll(soundObjectsCopy);

            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (SoundObject item : soundObjectsCopy) {

                    Log.d("itemNaam", item.getItemName().toLowerCase()); //DEBUG
                    if (item.getItemName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);

                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            soundObjects.clear();
            soundObjects.addAll((List) results.values);

            notifyDataSetChanged();

        }
    };









}
