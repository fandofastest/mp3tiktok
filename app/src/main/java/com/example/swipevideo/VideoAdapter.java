package com.example.swipevideo;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;
import java.util.Random;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<MusicItem> musicItems;
    static   List<String>  listitem;
       static ProgressBar videoProgressBar;

    Context context;

    public VideoAdapter(Context context, List<MusicItem> musicItems, List<String> listitem) {
        this.musicItems = musicItems;
        this.listitem=listitem;
        this.context=context;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_video,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        MusicItem musicItem = musicItems.get(position);
        holder.tvVideoTitle.setText(musicItem.getTitle());
        holder.tvVideoDescription.setText(musicItem.getArtist());
        Glide.with(context).load(musicItem.getImageurl())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.imageView);
//        Log.e("errr", String.valueOf(position));


    }

    @Override
    public int getItemCount() {
        return musicItems.size();
    }



     static class VideoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        VideoView videoView;
        TextView tvVideoTitle, tvVideoDescription;


        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
//            videoView = itemView.findViewById(R.id.videoView);
            tvVideoTitle = itemView.findViewById(R.id.tv_videoTitle);
            tvVideoDescription = itemView.findViewById(R.id.tv_videoDescription);
            imageView=itemView.findViewById(R.id.background);
        }

    }



}
