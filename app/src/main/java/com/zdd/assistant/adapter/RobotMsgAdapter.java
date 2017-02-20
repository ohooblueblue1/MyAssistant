package com.zdd.assistant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class RobotMsgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private Context mContext;

    private List<Message> mMessagesList;


    public RobotMsgAdapter(Context context,List<Message> messagesList)
    {
        mContext = context;
        mMessagesList = messagesList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = null;
        if(viewType == Message.TYPE_SEND){
            view = LayoutInflater.from(mContext)
                                 .inflate(R.layout.item_msg_send, parent, false);
            return new SendHolder(view);
        }else {
            view = LayoutInflater.from(mContext)
                                 .inflate(R.layout.item_msg_receieve, parent, false);
            return new ReceieveHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        Message message = mMessagesList.get(position);
        if(getItemViewType(position) == Message.TYPE_SEND){
            SendHolder sendHolder = (SendHolder) holder;
            sendHolder.tvContent.setText(message.getContent());
        }else {
            ReceieveHolder receieveHolder = (ReceieveHolder) holder;
            receieveHolder.tvContent.setText(message.getContent());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mMessagesList.get(position).getType();
    }


    @Override
    public int getItemCount()
    {
        return mMessagesList.size();
    }

    static class SendHolder extends RecyclerView.ViewHolder
    {
        TextView tvContent;

        public SendHolder(View itemView)
        {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.msg_send);
        }
    }

    static class ReceieveHolder extends RecyclerView.ViewHolder
    {
        TextView tvContent;

        public ReceieveHolder(View itemView)
        {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.msg_receieve);
        }
    }

}
