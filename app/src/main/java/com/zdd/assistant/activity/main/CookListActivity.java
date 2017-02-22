package com.zdd.assistant.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.zdd.assistant.R;
import com.zdd.assistant.adapter.CookListAdapter;
import com.zdd.assistant.base.BaseActivity;
import com.zdd.assistant.entity.cook.CookList;
import com.zdd.assistant.provider.CookProvider;
import com.zdd.assistant.provider.OnResponseListener;

import java.util.List;

public class CookListActivity extends BaseActivity implements CookListAdapter.OnItemClickListener
{

    private int mId;
    private String mCategory;

    private CookProvider mCookProvider;
    private RecyclerView mRvCookList;
    private CookListAdapter mAdapter;

    private List<CookList.TngouBean> mTngouBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_list);

        mId = getIntent().getIntExtra("id", -1);
        mCategory = getIntent().getStringExtra("category");
        mCookProvider = new CookProvider();
        initView();
        loadData();
    }

    private void initView()
    {
        //初始化Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("菜谱 | " + mCategory);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mRvCookList = (RecyclerView) findViewById(R.id.rv_cook_list);
        mRvCookList.setLayoutManager(new LinearLayoutManager(this));
    }


    private void loadData()
    {
        if (!checkIsNetAvailable())
        {
            return;
        }
        mCookProvider.getCookList(mId, new OnResponseListener()
        {
            @Override
            public void onBefore()
            {
                showProgressDialog(mCategory + "类菜谱加载中...");
            }

            @Override
            public void onSuccess(Object response)
            {
                CookList cookList = (CookList) response;
                mTngouBeanList = cookList.getTngou();
                mAdapter = new CookListAdapter(CookListActivity.this, cookList.getTngou());
                mAdapter.setOnItemClickLitener(CookListActivity.this);
                mRvCookList.setAdapter(mAdapter);
                dismissProgressDialog();
            }

            @Override
            public void onFailure()
            {
                dismissProgressDialog();
            }
        });
    }


    public static void actionStart(Context context, int id, String category)
    {
        Intent intent = new Intent(context, CookListActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("category", category);
        context.startActivity(intent);
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


    @Override
    public void onItemClick(View view, long position)
    {
        CookDetailActivity.actionStart(this,position);
    }

    @Override
    public void onItemLongClick(View view, int position)
    {

    }
}
