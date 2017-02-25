package com.zdd.assistant.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zdd.assistant.R;
import com.zdd.assistant.activity.cook.CookActivity;
import com.zdd.assistant.activity.guide.GuideActivity;
import com.zdd.assistant.activity.notepad.NotePadActivity;
import com.zdd.assistant.activity.robot.RobotActivity;
import com.zdd.assistant.base.BaseActivity;
import com.zdd.assistant.entity.weather.WeatherNow;
import com.zdd.assistant.entity.weather.WeatherSuggestion;
import com.zdd.assistant.provider.OnResponseListener;
import com.zdd.assistant.provider.WeatherProvider;
import com.zdd.assistant.util.ActivityCollector;
import com.zdd.assistant.util.DateUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Project Name: MyAssistant
 * File Name:    MainActivity.java
 * <p>
 * Description: 主活动界面
 *
 * @author zdd
 * @date 2017年02月05日 21:13
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private DrawerLayout mDrawer;

    //天气信息相关
    private TextView mTvCityName;
    private TextView mTvUpdateTime;
    private ImageView mIvWeatherIcon;
    private TextView mTvTemperature;
    private TextView mTvWeatherName;
    //运动指数
    private TextView mTvSport;
    //洗车指数
    private TextView mTvCarWashing;
    //流感指数
    private TextView mTvFlu;
    //紫外线强度
    private TextView mTvUv;

    private WeatherProvider mWeatherProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initDataProVider();
        loadWeatherData();
    }

    private void initView() {
        //设置此页面不能侧滑关闭
        setSwipeBackEnable(false);

        //关闭其他Activity
        ActivityCollector.finishAllOther(this);

        //初始化toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //初始化侧滑菜单
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //设置主界面功能tab监听
        findViewById(R.id.rl_tab_cook).setOnClickListener(this);
        findViewById(R.id.rl_tab_guide).setOnClickListener(this);
        findViewById(R.id.rl_tab_notepad).setOnClickListener(this);
        findViewById(R.id.rl_tab_robot).setOnClickListener(this);
        findViewById(R.id.rl_tab_weather).setOnClickListener(this);

        //初始化天气相关View
        mTvCityName = (TextView) findViewById(R.id.tv_city_name);
        mTvUpdateTime = (TextView) findViewById(R.id.tv_update_time);
        mIvWeatherIcon = (ImageView) findViewById(R.id.iv_weather_icon);
        mTvTemperature = (TextView) findViewById(R.id.tv_temperature);
        mTvWeatherName = (TextView) findViewById(R.id.tv_weater_name);
        mTvSport = (TextView) findViewById(R.id.tv_do_sport);
        mTvCarWashing = (TextView) findViewById(R.id.tv_do_car_washing);
        mTvFlu = (TextView) findViewById(R.id.tv_do_flu);
        mTvUv = (TextView) findViewById(R.id.tv_do_uv);

        //刷新天气按钮
        findViewById(R.id.iv_refresh_weather).setOnClickListener(this);
    }


    private void initDataProVider() {
        mWeatherProvider = new WeatherProvider();
    }


    private void loadWeatherData() {

        if (!checkIsNetAvailable()) {
            return;
        }

        showProgressDialog("更新天气信息...");
        //当前天气
        mWeatherProvider.loadWeatherNow("ip", new OnResponseListener() {
            @Override
            public void onBefore() {
            }

            @Override
            public void onSuccess(Object response) {
                fillWeatherNow((WeatherNow) response);
            }

            @Override
            public void onFailure() {
            }
        });

        //生活指数
        mWeatherProvider.loadWeatherSuggestion("ip", new OnResponseListener() {
            @Override
            public void onBefore() {
            }

            @Override
            public void onSuccess(Object response) {
                fillWeatherSuggestion((WeatherSuggestion) response);
                dismissProgressDialog();
            }

            @Override
            public void onFailure() {
                dismissProgressDialog();
            }
        });
    }


    private void fillWeatherNow(WeatherNow response) {
        WeatherNow.ResultsBean result = response.getResults()
                                                .get(0);
        mTvCityName.setText(result.getLocation()
                                  .getName());
        mTvTemperature.setText(result.getNow()
                                     .getTemperature());
        mTvWeatherName.setText(result.getNow()
                                     .getText());
        try {
            InputStream in = getAssets().open("weather/" + result.getNow()
                                                                 .getCode() + ".png");
            mIvWeatherIcon.setImageDrawable(Drawable.createFromStream(in, null));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mTvUpdateTime.setText(DateUtil.getWeatherTimeString(result.getLast_update()));
    }


    private void fillWeatherSuggestion(WeatherSuggestion response) {
        WeatherSuggestion.ResultsBean.SuggestionBean result = response.getResults()
                                                                      .get(0)
                                                                      .getSuggestion();
        mTvSport.setText("运动指数: " + result.getSport()
                                          .getBrief());
        mTvFlu.setText("流感指数: " + result.getFlu()
                                        .getBrief());
        mTvCarWashing.setText("洗车指数: " + result.getCar_washing()
                                               .getBrief());
        mTvUv.setText("紫外线强度: " + result.getUv()
                                        .getBrief());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 侧滑菜单项点击事件监听
     *
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.rl_tab_cook:
                CookActivity.actionStart(MainActivity.this);
                break;

            case R.id.rl_tab_guide:
                GuideActivity.actionStart(MainActivity.this);
                break;

            case R.id.rl_tab_notepad:
                NotePadActivity.actionStart(MainActivity.this);
                break;

            case R.id.rl_tab_robot:
                RobotActivity.actionStart(MainActivity.this);
                break;

            case R.id.iv_refresh_weather:
                //刷新天气信息
                loadWeatherData();
                break;
        }
    }
}
