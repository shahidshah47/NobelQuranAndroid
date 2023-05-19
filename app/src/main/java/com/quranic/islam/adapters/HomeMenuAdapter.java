package com.quranic.islam.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.quranic.islam.R;
import com.quranic.islam.base.BaseAdapter;
import com.quranic.islam.base.BaseModel;
import com.quranic.islam.databinding.ItemHomeMenuBinding;
import com.quranic.islam.interfaces.OnItemClickListener;
import com.quranic.islam.models.MenuDrawerModel;

import java.util.List;

public class HomeMenuAdapter extends BaseAdapter {
    public HomeMenuAdapter(Activity mAct, List<BaseModel> list, OnItemClickListener listener) {
        super(mAct, list, listener);
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeMenuBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_home_menu, parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
        if (viewHolder instanceof ItemViewHolder) {
            ItemViewHolder holder = (ItemViewHolder) viewHolder;
            holder.model = (MenuDrawerModel) list.get(position);
            holder.binding.iconIV.setImageResource(holder.model.getLogo());
            holder.binding.titleTV.setText(holder.model.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemHomeMenuBinding binding;
        private MenuDrawerModel model;

        public ItemViewHolder(ItemHomeMenuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}