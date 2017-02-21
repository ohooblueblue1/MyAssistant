package com.zdd.assistant.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zdd.assistant.R;
import com.zdd.assistant.base.BaseActivity;
import com.zdd.assistant.entity.cook.CookList;
import com.zdd.assistant.provider.CookProvider;
import com.zdd.assistant.provider.OnResponseListener;
import com.zdd.assistant.util.TextUtil;
import com.zdd.assistant.util.ToastUtil;

public class CookActivity extends BaseActivity implements View.OnClickListener
{

    private EditText mEdtSearch;
    private TextView mBtnSearch;
    private RecyclerView mRvCook;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook);

        initView();

        t1();
    }

    private void t1()
    {
        new CookProvider().searchCook("鱼香肉丝", new OnResponseListener()
        {
            @Override
            public void onBefore()
            {

            }

            @Override
            public void onSuccess(Object response)
            {
                CookList c = (CookList) response;

                ToastUtil.showToast(CookActivity.this, c.getList()
                                                        .get(0)
                                                        .getInfo());
            }

            @Override
            public void onFailure()
            {

            }
        });
    }

    private void initView()
    {
        //初始化Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEdtSearch = (EditText) findViewById(R.id.edt_search);
        mBtnSearch = (TextView) findViewById(R.id.btn_search);
        mBtnSearch.setOnClickListener(this);

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
        Intent intent = new Intent(context, CookActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_search:
                tryToSearchCook();
                break;
        }
    }

    private void tryToSearchCook()
    {
        if (TextUtil.isEmpty(mEdtSearch))
        {
            ToastUtil.showToast(this, "请先输入您要查询的内容哦~");
            return;
        }
        if (!checkIsNetAvailable())
        {
            return;
        }
        // TODO: 发起请求
    }
}
