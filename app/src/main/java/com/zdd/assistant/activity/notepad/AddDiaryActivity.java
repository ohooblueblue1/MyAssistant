package com.zdd.assistant.activity.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zdd.assistant.R;
import com.zdd.assistant.custom.LinedEditText;
import com.zdd.assistant.db.DiaryDatabaseHelper;
import com.zdd.assistant.util.GetDate;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.trity.floatingactionbutton.FloatingActionButton;
import cc.trity.floatingactionbutton.FloatingActionsMenu;

public class AddDiaryActivity extends AppCompatActivity
{

    @Bind(R.id.add_diary_tv_date)
    TextView mAddDiaryTvDate;
    @Bind(R.id.add_diary_et_title)
    EditText mAddDiaryEtTitle;
    @Bind(R.id.add_diary_et_content)
    LinedEditText mAddDiaryEtContent;
    @Bind(R.id.add_diary_fab_back)
    FloatingActionButton mAddDiaryFabBack;
    @Bind(R.id.add_diary_fab_add)
    FloatingActionButton mAddDiaryFabAdd;

    @Bind(R.id.right_labels)
    FloatingActionsMenu mRightLabels;

    private DiaryDatabaseHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);

        initView();
    }

    private void initView()
    {
        //初始化Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("添加日记");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        mAddDiaryEtTitle.setText(intent.getStringExtra("title"));
        mAddDiaryTvDate.setText("今天，" + GetDate.getDate());
        mAddDiaryEtContent.setText(intent.getStringExtra("content"));
        mHelper = new DiaryDatabaseHelper(this, "Diary.db", null, 1);
    }


    @OnClick({R.id.add_diary_et_title, R.id.add_diary_et_content, R.id.add_diary_fab_back, R.id.add_diary_fab_add})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.add_diary_et_title:
                break;
            case R.id.add_diary_et_content:
                break;
            case R.id.add_diary_fab_back:
                String date = GetDate.getDate()
                                     .toString();
                String title = mAddDiaryEtTitle.getText()
                                               .toString() + "";
                String content = mAddDiaryEtContent.getText()
                                                   .toString() + "";
                if (!title.equals("") || !content.equals(""))
                {
                    SQLiteDatabase db = mHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("date", date);
                    values.put("title", title);
                    values.put("content", content);
                    db.insert("Diary", null, values);
                    values.clear();
                }
                NotePadActivity.actionStart(this);
                break;
            case R.id.add_diary_fab_add:
                final String dateBack = GetDate.getDate()
                                               .toString();
                final String titleBack = mAddDiaryEtTitle.getText()
                                                         .toString();
                final String contentBack = mAddDiaryEtContent.getText()
                                                             .toString();
                if (!titleBack.isEmpty() || !contentBack.isEmpty())
                {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                    alertDialogBuilder.setMessage("是否保存日记内容？")
                                      .setPositiveButton("确定", new DialogInterface.OnClickListener()
                                      {
                                          public void onClick(DialogInterface dialog, int which)
                                          {
                                              SQLiteDatabase db = mHelper.getWritableDatabase();
                                              ContentValues values = new ContentValues();
                                              values.put("date", dateBack);
                                              values.put("title", titleBack);
                                              values.put("content", contentBack);
                                              db.insert("Diary", null, values);
                                              values.clear();
                                              NotePadActivity.actionStart(AddDiaryActivity.this);
                                          }
                                      })
                                      .setNegativeButton("取消", new DialogInterface.OnClickListener()
                                      {
                                          public void onClick(DialogInterface dialog, int which)
                                          {
                                              NotePadActivity.actionStart(AddDiaryActivity.this);
                                          }
                                      })
                                      .show();
                }
                else
                {
                    NotePadActivity.actionStart(this);
                }

                break;
        }
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
        Intent intent = new Intent(context, AddDiaryActivity.class);
        context.startActivity(intent);
    }

    public static void actionStart(Context context, String title, String content)
    {
        Intent intent = new Intent(context, AddDiaryActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        context.startActivity(intent);
    }

}
