package com.zdd.assistant.activity.cook;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zdd.assistant.R;
import com.zdd.assistant.base.BaseActivity;
import com.zdd.assistant.entity.cook.CookDetail;
import com.zdd.assistant.provider.CookProvider;
import com.zdd.assistant.provider.OnResponseListener;

public class CookDetailActivity extends BaseActivity {

    private long mId;

    private TextView mTvName;
    private TextView mTvMsg;
    private TextView mTvKeywords;
    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_detail);

        mId = getIntent().getLongExtra("id",1);
        initView();
        loadData();
    }


    private void initView() {
        //初始化Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvMsg = (TextView) findViewById(R.id.tv_message);
        mTvKeywords = (TextView) findViewById(R.id.tv_keywords);
        mImageView = (ImageView) findViewById(R.id.iv_cook_img);
    }

    private void loadData() {
        if(!checkIsNetAvailable()){
            return;
        }
        new CookProvider().getCookDetail(mId, new OnResponseListener() {
            @Override
            public void onBefore() {
                showProgressDialog("加载中...");
            }

            @Override
            public void onSuccess(Object response) {
                bindData((CookDetail) response);
                dismissProgressDialog();
            }

            @Override
            public void onFailure() {
                dismissProgressDialog();
            }
        });
    }

    private void bindData(CookDetail cookDetail) {
        mTvName.setText(cookDetail.getName());
        Glide.with(this).load(CookProvider.IMG_URL_HEAD + cookDetail.getImg())
             .error(R.drawable.lodingimgfailed)
             .into(mImageView);
        mTvKeywords.setText(cookDetail.getKeywords());
        //设置菜谱详情信息，解析html格式的字符串
        Spanned message;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            message = Html.fromHtml(cookDetail.getMessage(), Html.FROM_HTML_MODE_LEGACY);
        } else {
            message = Html.fromHtml(cookDetail.getMessage());
        }
        mTvMsg.setText(message);
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


    public static void actionStart(Context context, long id) {
        Intent intent = new Intent(context, CookDetailActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }
}
