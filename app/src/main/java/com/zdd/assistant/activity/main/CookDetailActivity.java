package com.zdd.assistant.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zdd.assistant.R;

public class CookDetailActivity extends AppCompatActivity
{

    private int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_detail);

        mId = getIntent().getIntExtra("id", -1);
        initView();
    }

    private void initView()
    {

    }


    public static void actionStart(Context context, int id)
    {

        Intent intent = new Intent(context, CookDetailActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }
}
