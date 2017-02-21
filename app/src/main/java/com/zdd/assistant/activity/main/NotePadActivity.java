package com.zdd.assistant.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zdd.assistant.R;
import com.zdd.assistant.base.BaseActivity;

public class NotePadActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_pad);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, NotePadActivity.class);
        context.startActivity(intent);
    }
}