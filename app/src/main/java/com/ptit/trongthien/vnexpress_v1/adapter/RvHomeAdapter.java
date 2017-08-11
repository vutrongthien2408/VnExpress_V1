package com.ptit.trongthien.vnexpress_v1.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ptit.trongthien.vnexpress_v1.R;
import com.ptit.trongthien.vnexpress_v1.model.entity.ItemNews;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by TrongThien on 7/21/2017.
 */

public class RvHomeAdapter extends RecyclerView.Adapter<RvHomeAdapter.MyViewHolder> {
    private ArrayList<ItemNews> itemNewses = new ArrayList<>();
    private View view;

    public RvHomeAdapter(ArrayList itemNewses) {
        this.itemNewses = itemNewses;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, int position) {
        ArrayList<ItemNews> itemNewsesArr = new ArrayList<>();
        itemNewsesArr.addAll(itemNewses);
        final ItemNews itemNews = itemNewsesArr.get(position);
        Picasso.with(view.getContext()).load(itemNews.getImage()).resize(300,300).into(viewHolder.imgItemNews);
//        viewHolder.imgItemNews.setImageResource(itemNews.getImage());
        viewHolder.tvItemNewsTitle.setText(itemNews.getTitle());
        viewHolder.tvItemNewsContent.setText(itemNews.getContent());
    }



    @Override
    public int getItemCount() {
        return itemNewses.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imgItemNews;
        TextView tvItemNewsTitle;
        TextView tvItemNewsContent;
        public MyViewHolder(View view) {
            super(view);
            imgItemNews = (ImageView) view.findViewById(R.id.imgItemNews);
            tvItemNewsTitle = (TextView) view.findViewById(R.id.tvItemNewsTitle);
            tvItemNewsContent = (TextView) view.findViewById(R.id.tvItemNewsContent);
        }
    }
}
