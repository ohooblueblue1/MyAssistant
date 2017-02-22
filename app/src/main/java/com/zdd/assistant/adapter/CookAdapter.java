package com.zdd.assistant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zdd.assistant.R;

/**
 * Project Name: MyAssistant
 * File Name:    CookAdapter.java
 * <p>
 * Description: 菜谱适配器
 *
 * @author zdd
 * @date 2017年02月21日 21:17
 */
public class CookAdapter extends RecyclerView.Adapter<CookAdapter.CookHolder> {

    private Context mContext;

    public CookAdapter(Context context) {
        mContext = context;

    }

    @Override
    public CookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                                  .inflate(R.layout.item_cook, parent, false);
        return new CookHolder(view);
    }

    @Override
    public void onBindViewHolder(CookHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class CookHolder extends RecyclerView.ViewHolder {

        private TextView mInfo;
        private TextView mTvName;

        public CookHolder(View itemView) {
            super(itemView);


        }

    }


}
