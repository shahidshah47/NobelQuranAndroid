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
import com.quranic.islam.databinding.ItemSurahParahBinding;
import com.quranic.islam.interfaces.OnItemClickListener;

import java.util.List;

public class SurahParahAdapter extends BaseAdapter {
    private String[] dataList;
    private int type;

    public SurahParahAdapter(Activity mAct, List<BaseModel> list, int type, OnItemClickListener listener) {
        super(mAct, list, listener);
        this.listener = listener;
        this.type = type;

        if (type == ST.SURAH_TYPE) {
            dataList = app.qdh.urduSurahNames;
        } else if (type == ST.PARAH_TYPE) {
            dataList = app.qdh.urduParahName;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSurahParahBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_surah_parah, parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
        if (viewHolder instanceof ItemViewHolder) {
            ItemViewHolder holder = (ItemViewHolder) viewHolder;
            holder.binding.englishNameTV.setText(dataList[position]);
            holder.binding.arabicNameTV.setText(dataList[position]);
            holder.binding.countTV.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return dataList.length;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemSurahParahBinding binding;

        public ItemViewHolder(ItemSurahParahBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}