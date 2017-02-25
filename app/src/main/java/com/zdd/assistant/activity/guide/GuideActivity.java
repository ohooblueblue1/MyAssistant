package com.zdd.assistant.activity.guide;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.zdd.assistant.R;
import com.zdd.assistant.base.BaseActivity;

public class GuideActivity extends BaseActivity
{

    private SensorManager manager;
    private ImageView compassImg;

    private SensorListener listener = new SensorListener();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();
        initSensor();
    }

    private void initView()
    {
        //初始化Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("指南针");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        compassImg = (ImageView) findViewById(R.id.iv_compass);
    }


    private void initSensor()
    {
        //获取系统服务（SENSOR_SERVICE)返回一个SensorManager 对象
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume()
    {
        //获取方向传感器 通过SensorManager对象获取相应的Sensor类型的对象
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        //应用在前台时候注册监听器
        manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME);
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        //应用不在前台时候销毁掉监听器
        manager.unregisterListener(listener);
        super.onPause();
    }

    //Toolbar菜单点击事件监听
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            //按下返回导航按钮，结束当前Activity
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void actionStart(Context context)
    {
        Intent intent = new Intent(context, GuideActivity.class);
        context.startActivity(intent);
    }


    private final class SensorListener implements SensorEventListener
    {

        private float predegree = 0;

        @Override
        public void onSensorChanged(SensorEvent event)
        {
            /**
             *  values[0]: x-axis 方向加速度
             　　 values[1]: y-axis 方向加速度
             　　 values[2]: z-axis 方向加速度
             */
            float degree = event.values[0];// 存放了方向值
            /**动画效果*/
            RotateAnimation animation = new RotateAnimation(predegree, degree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(200);
            compassImg.startAnimation(animation);
            predegree = -degree;

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy)
        {

        }

    }

}
