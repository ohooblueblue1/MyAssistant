package com.zdd.assistant.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zdd.assistant.R;

public class RobotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RobotActivity.class);
        context.startActivity(intent);
    }
}
