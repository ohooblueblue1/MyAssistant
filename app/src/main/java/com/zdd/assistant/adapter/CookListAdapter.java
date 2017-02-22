package com.zdd.assistant.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zdd.assistant.R;
import com.zdd.assistant.entity.cook.CookList;
import com.zdd.assistant.provider.CookProvider;

import java.util.List;

/**
 * Project Name: MyAssistant
 * File Name:    CookListAdapter.java
 * ClassName:    CookListAdapter
 *
 * Description: 菜谱某个分类列表适配器
 *
 * @author ZDD
 * @date 2017年02月22日 19:37
 *
 * Copyright (c) 2017年, 4399 Network CO.ltd. All Rights Reserved.
 */
public class CookListAdapter extends RecyclerView.Adapter<CookListAdapter.CookListHolder>
{
    private Context mContext;
    private List<CookList.TngouBean> mTngouBeanList;

    private OnItemClickListener mListener;

    public CookListAdapter(Context context, List<CookList.TngouBean> tngouBeanList){
        mContext = context;
        mTngouBeanList = tngouBeanList;
    }

    @Override
    public CookListHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cook_rv,parent,false);
        return new CookListHolder(view);
    }

    @Override
    public void onBindViewHolder(final CookListHolder holder, int position)
    {
        CookList.TngouBean tngouBean = mTngouBeanList.get(position);
        Glide.with(mContext).load(CookProvider.IMG_URL_HEAD + tngouBean.getImg())
             .asBitmap()
             .diskCacheStrategy(DiskCacheStrategy.ALL)
             .error(R.drawable.lodingimgfailed)
             .into(new SimpleTarget<Bitmap>() {
                 @Override
                 public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                     holder.ivCook.setImageBitmap(resource);
                 }
             });
        holder.tvInfo.setText(tngouBean.getKeywords());
        holder.tvName.setText(tngouBean.getName());


        //如果设置了回调，则设置点击事件
        if (mListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mListener.onItemClick(holder.itemView,position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mListener.onItemLongClick(holder.itemView,position);
                    return true;
                }
            });

        }
    }

    @Override
    public int getItemCount()
    {
        return mTngouBeanList.size();
    }

    public void setOnItemClickLitener(OnItemClickListener listener){
        mListener = listener;
    }

    static class CookListHolder extends RecyclerView.ViewHolder{

        ImageView ivCook;
        TextView tvInfo;
        TextView tvName;

        public CookListHolder(View itemView)
        {
            super(itemView);
            ivCook = (ImageView) itemView.findViewById(R.id.iv_cook);
            tvInfo = (TextView) itemView.findViewById(R.id.tv_info);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }

    }

    //定义点击，长按事件监听接口
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view,int position);
    }
}
