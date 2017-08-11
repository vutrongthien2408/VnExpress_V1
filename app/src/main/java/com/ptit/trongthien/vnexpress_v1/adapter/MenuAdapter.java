package com.ptit.trongthien.vnexpress_v1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.ptit.trongthien.vnexpress_v1.R;
import com.ptit.trongthien.vnexpress_v1.model.entity.ItemMenu;

import java.util.ArrayList;

/**
 * Created by TrongThien on 7/30/2017.
 */

public class MenuAdapter extends BaseAdapter {
    private ArrayList<ItemMenu> itemMenus = new ArrayList<>();
    private LayoutInflater inflater;

    public MenuAdapter(ArrayList<ItemMenu> itemMenus, Context context) {
        inflater = LayoutInflater.from(context);
        this.itemMenus = itemMenus;
    }

    @Override
    public int getCount() {
        return itemMenus.size();
    }

    @Override
    public Object getItem(int i) {
        return itemMenus.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_menu,viewGroup,false);
        TextView tvMenu = (TextView) view.findViewById(R.id.tvMenu);
        tvMenu.setText(itemMenus.get(i).getName());
        return view;
    }
}
