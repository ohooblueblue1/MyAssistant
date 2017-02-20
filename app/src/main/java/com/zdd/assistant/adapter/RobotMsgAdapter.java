package com.zdd.assistant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zdd.assistant.R;
import com.zdd.assistant.entity.robot.Message;

import java.util.List;

/**
 * Project Name: MyAssistant
 * File Name:    RobotMsgAdapter.java
 *
 * Description: 智能机器人聊天信息适配器
 *
 * @author ZDD
 * @date 2017年02月20日 20:02
 */
public class RobotMsgAdapter extends RecyclerView.Adapter<RobotMsgAdapter.MsgViewHolder>
{

    private Context mContext;

    private List<Message> mMessagesList;


    public RobotMsgAdapter(Context context,List<Message> messagesList)
    {
        mContext = context;
        mMessagesList = messagesList;
    }

    @Override
    public MsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(mContext)
                                  .inflate(R.layout.item_chat_message, parent, false);
        return new MsgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgViewHolder holder, int position)
    {
        Message message = mMessagesList.get(position);
        // TODO: 2017/2/20 动态隐藏显示布局
    }

    @Override
    public int getItemCount()
    {
        return mMessagesList.size();
    }

    static class MsgViewHolder extends RecyclerView.ViewHolder
    {

        public MsgViewHolder(View itemView)
        {
            super(itemView);
        }
    }

}
