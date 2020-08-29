package com.example.h_mal.flavourednewsapp.ui.main.home;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h_mal.flavourednewsapp.R;
import com.example.h_mal.flavourednewsapp.data.room.entities.NewsEntity;
import com.example.h_mal.flavourednewsapp.ui.main.MainActivity;
import com.example.h_mal.flavourednewsapp.ui.main.overview.OverviewFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.h_mal.flavourednewsapp.utils.DateUtils.dateStringOnly;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    MainActivity mainActivity;
    List<NewsEntity> newsList;

    public NewsRecyclerAdapter(MainActivity mainActivity, List<NewsEntity> newsList) {
        this.mainActivity = mainActivity;
        this.newsList = newsList;
    }

    public void updateList(List<NewsEntity> newsList) {
        this.newsList.clear();
        this.newsList.addAll(newsList);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (newsList.isEmpty()) {
            View EmptyView = LayoutInflater.from(mainActivity).inflate(R.layout.empty_list_cell, parent, false);
            return new RecyclerView.ViewHolder(EmptyView) {
                @Override
                public String toString() {
                    return super.toString();
                }
            };
        } else {
            View itemOne = LayoutInflater.from(mainActivity).inflate(R.layout.news_item_cell, parent, false);
            return new ItemOne(itemOne);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemOne) {
            NewsEntity currentNews = newsList.get(position);
            ((ItemOne) holder).bindView(currentNews);
            ((ItemOne) holder).buttonOpen.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentNews.url));
                mainActivity.startActivity(intent);
            });
            ((ItemOne) holder).buttonShare.setOnClickListener(v -> {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, currentNews.title);
                mainActivity.startActivity(Intent.createChooser(shareIntent, "choose one"));
            });
            holder.itemView.setOnClickListener(view ->
                    mainActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, OverviewFragment.newInstance(currentNews.url))
                            .addToBackStack("details")
                            .commit()
            );
        }
    }

    @Override
    public int getItemCount() {
        if (newsList.isEmpty()) {
            return 1;
        } else {
            return newsList.size();
        }
    }

    static class ItemOne extends RecyclerView.ViewHolder {
        TextView dateTv;
        TextView titleTv;
        TextView authorTv;
        ImageView cellImg;
        CardView buttonShare;
        CardView buttonOpen;

        public ItemOne(@NonNull View itemView) {
            super(itemView);
            dateTv = itemView.findViewById(R.id.card_date);
            titleTv = itemView.findViewById(R.id.card_title);
            authorTv = itemView.findViewById(R.id.card_author);
            buttonShare = itemView.findViewById(R.id.button_share);
            buttonOpen = itemView.findViewById(R.id.button_open);
            cellImg = itemView.findViewById(R.id.cell_image_view);
        }

        public void bindView(NewsEntity news) {
            String date = dateStringOnly(news.publishedAt);
            dateTv.setText(date);
            titleTv.setText(news.title);
            authorTv.setText(news.author);
            if (news.urlToImage != null && !news.urlToImage.isEmpty()) {
                Picasso.get().load(news.urlToImage).into(cellImg);
            }
        }
    }

}
