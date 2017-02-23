package com.zdd.assistant.activity.cook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.zdd.assistant.R;
import com.zdd.assistant.adapter.CookMenuAdapter;
import com.zdd.assistant.base.BaseActivity;
import com.zdd.assistant.entity.cook.CookMenu;

import java.util.ArrayList;
import java.util.List;

public class CookActivity extends BaseActivity {

    private GridView mGvCookMenu;
    private CookMenuAdapter mAdapter;

    private List<CookMenu> mListMenus;
    private final int[] mArrIds = {1, 10, 15, 52, 62, 68, 75, 82, 98, 112, 147, 161, 218, 166, 182, 188,
                                   192, 197, 202, 205, 212, 227, 132};
    private final String[] mArrNames = {"美容", "减肥", "保健养生", "人群", "时节", "餐时", "器官", "调养",
                                        "肠胃消化", "孕产哺乳", "经期", "女性疾病", "男性", "呼吸道", "血管", "心脏", "肝胆脾胰",
                                        "神经系统", "口腔", "肌肉骨骼", "皮肤", "癌症", "其他"};
    private final int[] mImgRes = {R.drawable.meirong, R.drawable.jianfei, R.drawable.jiankangyangsheng,
                                   R.drawable.renqun, R.drawable.shijie, R.drawable.canshi, R.drawable.qiguan, R.drawable.tiaoyang,
                                   R.drawable.chagnweixiaohua, R.drawable.yunchanpuru, R.drawable.jingqi, R.drawable.nvxingjibing,
                                   R.drawable.nanxing, R.drawable.huxidao, R.drawable.xueguan, R.drawable.xinzang, R.drawable.gandanpiy,
                                   R.drawable.shenjingxitong, R.drawable.kouqiang, R.drawable.jirouguge, R.drawable.pifu,
                                   R.drawable.aizheng, R.drawable.qita};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook);

        initView();
    }

    private void initView() {
        //初始化Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //初始化菜谱分类菜单
        mGvCookMenu = (GridView) findViewById(R.id.gv_menu);
        mAdapter = new CookMenuAdapter(addMenus(),this);
        mGvCookMenu.setAdapter(mAdapter);

        mGvCookMenu.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                CookListActivity.actionStart(CookActivity.this, mArrIds[position], mArrNames[position]);
            }
        });
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


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CookActivity.class);
        context.startActivity(intent);
    }

    /**
     * 给菜单分类List添加数据
     *
     * @return 菜单分类List
     */
    private List<CookMenu> addMenus() {
        List<CookMenu> data = new ArrayList<>();
        for (int i = 0; i < mArrIds.length; i++) {
            CookMenu bean = new CookMenu();
            bean.setId(mArrIds[i]);
            bean.setImgId(mImgRes[i]);
            bean.setName(mArrNames[i]);
            data.add(bean);
        }
        return data;
    }

}
