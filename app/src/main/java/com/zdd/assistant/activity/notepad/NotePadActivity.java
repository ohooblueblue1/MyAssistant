package com.zdd.assistant.activity.notepad;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.zdd.assistant.R;
import com.zdd.assistant.adapter.DiaryAdapter;
import com.zdd.assistant.base.BaseActivity;
import com.zdd.assistant.db.DiaryDatabaseHelper;
import com.zdd.assistant.entity.event.StartUpdateDiaryEvent;
import com.zdd.assistant.entity.notepad.DiaryBean;
import com.zdd.assistant.util.DateUtil;
import com.zdd.assistant.util.SharedPreferenceUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotePadActivity extends BaseActivity
{

    @Bind(R.id.main_iv_circle)
    ImageView mMainIvCircle;
    @Bind(R.id.main_tv_date)
    TextView mMainTvDate;
    @Bind(R.id.main_tv_content)
    TextView mMainTvContent;
    @Bind(R.id.item_ll_control)
    LinearLayout mItemLlControl;

    @Bind(R.id.main_rv_show_diary)
    RecyclerView mMainRvShowDiary;
    @Bind(R.id.main_fab_enter_edit)
    FloatingActionButton mMainFabEnterEdit;
    @Bind(R.id.main_rl_main)
    RelativeLayout mMainRlMain;
    @Bind(R.id.item_first)
    LinearLayout mItemFirst;
    @Bind(R.id.main_ll_main)
    LinearLayout mMainLlMain;
    private List<DiaryBean> mDiaryBeanList;

    private DiaryDatabaseHelper mHelper;

    private static String IS_WRITE = "true";

    /**
     * 标识今天是否已经写了日记
     */
    private boolean isWrite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_pad);

        initView();
    }

    private void initView()
    {
        //初始化Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("日记");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        mHelper = new DiaryDatabaseHelper(this, "Diary.db", null, 1);
        EventBus.getDefault()
                .register(this);
        SharedPreferenceUtil sharedPreferenceUtil = SharedPreferenceUtil.getInstance(this);
        getDiaryBeanList();
        mMainTvDate.setText("今天，" + DateUtil.getDate());
        mMainRvShowDiary.setLayoutManager(new LinearLayoutManager(this));
        mMainRvShowDiary.setAdapter(new DiaryAdapter(this, mDiaryBeanList));
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


    private List<DiaryBean> getDiaryBeanList()
    {
        mDiaryBeanList = new ArrayList<>();
        List<DiaryBean> diaryList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = mHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("Diary", null, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            do
            {
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String dateSystem = DateUtil.getDate()
                                            .toString();
                Logger.d("一级");
                Logger.d("date1" + date);
                Logger.d("date2" + DateUtil.getDate());
                if (date.equals(dateSystem))
                {
                    Logger.d("二级");
                    mMainLlMain.removeView(mItemFirst);
                    Logger.d("三级");
                    break;
                }
            }
            while (cursor.moveToNext());
        }


        if (cursor.moveToFirst())
        {
            do
            {
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                mDiaryBeanList.add(new DiaryBean(date, title, content));
            }
            while (cursor.moveToNext());
        }
        cursor.close();

        for (int i = mDiaryBeanList.size() - 1; i >= 0; i--)
        {
            diaryList.add(mDiaryBeanList.get(i));
        }

        mDiaryBeanList = diaryList;
        return mDiaryBeanList;
    }

    @Subscribe
    public void startUpdateDiaryActivity(StartUpdateDiaryEvent event)
    {
        String title = mDiaryBeanList.get(event.getPosition())
                                     .getTitle();
        String content = mDiaryBeanList.get(event.getPosition())
                                       .getContent();
        UpdateDiaryActivity.actionStart(this, title, content);
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        getDiaryBeanList();
        mMainTvDate.setText("今天，" + DateUtil.getDate());
        mMainRvShowDiary.setLayoutManager(new LinearLayoutManager(this));
        mMainRvShowDiary.setAdapter(new DiaryAdapter(this, mDiaryBeanList));
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        EventBus.getDefault()
                .unregister(this);
    }

    @OnClick(R.id.main_fab_enter_edit)
    public void onClick()
    {
        AddDiaryActivity.actionStart(this);
    }


    public static void actionStart(Context context)
    {
        Intent intent = new Intent(context, NotePadActivity.class);
        context.startActivity(intent);
    }

}
