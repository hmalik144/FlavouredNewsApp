package com.example.h_mal.flavourednewsapp.ui.main.overview;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h_mal.flavourednewsapp.R;
import com.example.h_mal.flavourednewsapp.data.room.entities.NewsEntity;
import com.squareup.picasso.Picasso;

class OverviewRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    NewsEntity news;


    public OverviewRecyclerAdapter(Context context, NewsEntity news) {
        this.context = context;
        this.news = news;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View itemOne = LayoutInflater.from(context).inflate(R.layout.item_one_layout, parent, false);
            return new ItemOne(itemOne);
        }else {
            View itemTwo = LayoutInflater.from(context).inflate(R.layout.item_two_layout, parent, false);
            return new ItemTwo(itemTwo);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemOne){
            ((ItemOne) holder).bindView(news);
        }else {
            ((ItemTwo) holder).bindView(getItemDetails(position));
        }
    }

    private Pair<String, String> getItemDetails(int position){
        switch (position){
            case 1:
            return new Pair("Title", news.getTitle());
            case 2:
                return new Pair("Content", news.getContent());
            case 3:
                return new Pair("Description", news.getDescription());
            case 4:
                return new Pair("Author", news.getAuthor());
            case 5:
                return new Pair("Url", news.getUrl());
            default:
                return new Pair("", "");
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        }else {
            return 2;
        }
    }

    static class ItemOne extends RecyclerView.ViewHolder {
        ImageView cellImageView;

        public ItemOne(@NonNull View itemView) {
            super(itemView);
            cellImageView = itemView.findViewById(R.id.image_header);
        }

        public void bindView(NewsEntity news){
            if (news.urlToImage != null && !news.urlToImage.isEmpty()){
                Picasso.get().load(news.urlToImage).into(cellImageView);
            }
        }
    }

    static class ItemTwo extends RecyclerView.ViewHolder {
        TextView top;
        TextView bottom;

        public ItemTwo(@NonNull View itemView) {
            super(itemView);
            top = itemView.findViewById(android.R.id.text1);
            bottom = itemView.findViewById(android.R.id.text2);
        }

        public void bindView(Pair<String, String> entry){
            top.setText(entry.first);
            bottom.setText(entry.second);
        }
    }
}
