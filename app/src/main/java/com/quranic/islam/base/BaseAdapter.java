package com.quranic.islam.base;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quranic.islam.MyApplication;
import com.quranic.islam.SingletonClass;
import com.quranic.islam.interfaces.OnItemClickListener;

import java.util.List;

public class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Activity mAct;
    public SingletonClass ST;
    public MyApplication app;
    public OnItemClickListener listener;
    public List<BaseModel> list;

    protected BaseAdapter(Activity mAct, List<BaseModel> list, OnItemClickListener listener) {
        this.mAct = mAct;
        this.ST = SingletonClass.getInstance();
        this.app = (MyApplication) mAct.getApplication();
        this.listener = listener;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
