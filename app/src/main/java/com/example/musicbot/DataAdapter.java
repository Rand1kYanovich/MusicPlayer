package com.example.musicbot;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;



class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Music> musics ;
    ;
    DataAdapter(Context context, List<Music> musics) {
        this.musics = musics;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        Music music = musics.get(position);
        holder.title.setText(music.getTitle());
        holder.artist.setText(music.getArtist());


    }

    @Override
    public int getItemCount() {
        return musics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView title,artist;
        ViewHolder(View view){
            super(view);
            view.setOnClickListener(this);
            title = (TextView) view.findViewById(R.id.title);
            artist= (TextView) view.findViewById(R.id.artist);




        }
        @Override
        public void onClick(View v){
            //Click adapter
        }


    }
}