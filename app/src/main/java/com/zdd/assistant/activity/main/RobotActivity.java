package com.zdd.assistant.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zdd.assistant.R;
import com.zdd.assistant.adapter.RobotMsgAdapter;
import com.zdd.assistant.base.BaseActivity;
import com.zdd.assistant.entity.robot.Message;
import com.zdd.assistant.entity.robot.Result;
import com.zdd.assistant.provider.OnResponseListener;
import com.zdd.assistant.provider.RobotMsgProvider;
import com.zdd.assistant.util.TextUtil;
import com.zdd.assistant.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class RobotActivity extends BaseActivity implements View.OnClickListener
{
    private TextView mBtnSendMsg;
    private EditText mEdtMsg;
    private ImageView mBtnSpeak;

    //聊天信息列表
    private RecyclerView mRvMessage;
    private RobotMsgAdapter mAdapter;
    private List<Message> mMessagesList;

    private RobotMsgProvider mRobotMsgProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot);

        initView();
    }

    private void initView()
    {
        //初始化Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBtnSendMsg = (TextView) findViewById(R.id.btn_send);
        mBtnSendMsg.setOnClickListener(this);
        mEdtMsg = (EditText) findViewById(R.id.edt_msg);

        mBtnSpeak = (ImageView) findViewById(R.id.btn_speak);
        mBtnSpeak.setOnClickListener(this);

        //初始化RecyclerView
        mMessagesList = new ArrayList<Message>();
        mAdapter = new RobotMsgAdapter(this,mMessagesList);
        mRvMessage = (RecyclerView) findViewById(R.id.rv_message);
        mRvMessage.setLayoutManager(new LinearLayoutManager(this));
        mRvMessage.setAdapter(mAdapter);

        mRobotMsgProvider = new RobotMsgProvider();
    }

    //Toolbar菜单点击事件监听
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            //按下返回导航按钮，结束当前Activity
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RobotActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()){

            case R.id.btn_send:
                tryToSendMsg();
                break;

            case R.id.btn_speak:

                break;

        }
    }

    /**
     * 尝试发送消息
     */
    private void tryToSendMsg() {
        if(TextUtil.isEmpty(mEdtMsg)){
            ToastUtil.showToast(this,"不能发送空消息哦~");
            return;
        }
        if(!checkIsNetAvailable()){
            return;
        }

        mRobotMsgProvider.sendMessage(mEdtMsg.getText()
                                             .toString(), new OnResponseListener() {
            @Override
            public void onBefore() {
                Message message = new Message();
                message.setType(Message.TYPE_SEND);
                message.setContent(mEdtMsg.getText().toString());
                mEdtMsg.setText("");
                mMessagesList.add(message);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onSuccess(Object response) {
                Result result = (Result) response;
                Message message = new Message();
                message.setType(Message.TYPE_RECEIEVE);
                message.setContent(result.getText());
                mMessagesList.add(message);
                mAdapter.notifyDataSetChanged();
                mRvMessage.smoothScrollToPosition(mMessagesList.size());
            }

            @Override
            public void onFailure() {
                ToastUtil.showToast(RobotActivity.this,"消息发送失败");
            }
        });

    }
}
