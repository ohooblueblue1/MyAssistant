package com.zdd.assistant.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.zdd.assistant.adapter.FunctionsAdapter;
import com.zdd.assistant.base.BaseActivity;
import com.zdd.assistant.R;
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
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    // TODO: 2017/2/15 布局上方可展示天气信息
    
    private DrawerLayout mDrawer;
    //功能列表
    private RecyclerView mRvFuctions;
    //适配器
    // TODO: 2017/2/15 初始化
    private FunctionsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
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

        //初始化RecyclerView
        mRvFuctions = (RecyclerView) findViewById(R.id.rv_functions);
        //三行的布局管理器
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mRvFuctions.setLayoutManager(layoutManager);
        // TODO: 2017/2/15 设置adapter 
    }

    /**
     * 按下返回键
     */
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
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void actionStart(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
