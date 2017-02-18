package com.zdd.assistant.activity.main;

import android.content.Context;
import android.content.Intent;
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
import com.zdd.assistant.base.BaseActivity;
import com.zdd.assistant.util.ActivityCollector;

/**
 * Project Name: MyAssistant
 * File Name:    MainActivity.java
 * <p>
 * Description: 主活动界面
 *
 * @author zdd
 * @date 2017年02月05日 21:13
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
                                                          View.OnClickListener
{
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView()
    {
        //设置此页面不能侧滑关闭
        setSwipeBackEnable(false);
        //关闭其他Activity
        ActivityCollector.finishAllOther(this);
        //初始化toolbar,侧滑菜单
        initTop();

        //设置主界面功能tab监听
        findViewById(R.id.rl_tab_cook).setOnClickListener(this);
        findViewById(R.id.rl_tab_guide).setOnClickListener(this);
        findViewById(R.id.rl_tab_notepad).setOnClickListener(this);
        findViewById(R.id.rl_tab_robot).setOnClickListener(this);
        findViewById(R.id.rl_tab_weather).setOnClickListener(this);
    }

    private void initTop()
    {
        //初始化toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //初始化侧滑菜单
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open,
                                                                 R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * 按下返回键
     */
    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
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
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera)
        {

        }
        else if (id == R.id.nav_gallery)
        {

        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void actionStart(Context context)
    {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {

            case R.id.rl_tab_cook:

                break;

            case R.id.rl_tab_guide:

                break;

            case R.id.rl_tab_notepad:

                break;

            case R.id.rl_tab_robot:

                break;

        }
    }
}
