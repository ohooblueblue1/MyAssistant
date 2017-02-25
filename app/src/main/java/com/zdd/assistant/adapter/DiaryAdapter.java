package com.zdd.assistant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zdd.assistant.R;
import com.zdd.assistant.entity.event.StartUpdateDiaryEvent;
import com.zdd.assistant.entity.notepad.Diary;
import com.zdd.assistant.util.DateUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Project Name: MyAssistant
 * File Name:    DiaryAdapter.java
 * ClassName:    DiaryAdapter
 *
 * Description: TODO.
 *
 * @author ZDD
 * @date 2017年02月23日 17:44
 *
 */
public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Diary> mDiaryList;

    public DiaryAdapter(Context context, List<Diary> mDiaryList){
        mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mDiaryList = mDiaryList;
    }
    @Override
    public DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DiaryViewHolder(mLayoutInflater.inflate(R.layout.item_rv_diary, parent, false));
    }

    @Override
    public void onBindViewHolder(final DiaryViewHolder holder, final int position) {

        String dateSystem = DateUtil.getDate().toString();
        if(mDiaryList.get(position).getDate().equals(dateSystem)){
            holder.mIvCircle.setImageResource(R.drawable.circle_orange);
        }
        holder.mTvDate.setText(mDiaryList.get(position).getDate());
        holder.mTvTitle.setText(mDiaryList.get(position).getTitle());
        holder.mTvContent.setText("       " + mDiaryList.get(position).getContent());
        holder.mIvEdit.setVisibility(View.INVISIBLE);
        holder.mLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.mIvEdit.getVisibility() == View.INVISIBLE) {
                    holder.mIvEdit.setVisibility(View.VISIBLE);
                }else {
                    holder.mIvEdit.setVisibility(View.INVISIBLE);

                }
            }
        });

        holder.mIvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new StartUpdateDiaryEvent(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDiaryList.size();
    }

    public static class DiaryViewHolder extends RecyclerView.ViewHolder{

        TextView mTvDate;
        TextView mTvTitle;
        TextView mTvContent;
        ImageView mIvEdit;
        LinearLayout mLlTitle;
        LinearLayout mLl;
        ImageView mIvCircle;
        LinearLayout mLlControl;
        RelativeLayout mRlEdit;

        DiaryViewHolder(View view){
            super(view);
            mIvCircle = (ImageView) view.findViewById(R.id.main_iv_circle);
            mTvDate = (TextView) view.findViewById(R.id.main_tv_date);
            mTvTitle = (TextView) view.findViewById(R.id.main_tv_title);
            mTvContent = (TextView) view.findViewById(R.id.main_tv_content);
            mIvEdit = (ImageView) view.findViewById(R.id.main_iv_edit);
            mLlTitle = (LinearLayout) view.findViewById(R.id.main_ll_title);
            mLl = (LinearLayout) view.findViewById(R.id.item_ll);
            mLlControl = (LinearLayout) view.findViewById(R.id.item_ll_control);
            mRlEdit = (RelativeLayout) view.findViewById(R.id.item_rl_edit);
        }
    }
}