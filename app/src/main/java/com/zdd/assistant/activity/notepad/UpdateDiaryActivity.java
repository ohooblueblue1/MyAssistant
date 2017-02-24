package com.zdd.assistant.activity.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zdd.assistant.R;
import com.zdd.assistant.base.BaseActivity;
import com.zdd.assistant.custom.LinedEditText;
import com.zdd.assistant.db.DiaryDatabaseHelper;
import com.zdd.assistant.util.DateUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.trity.floatingactionbutton.FloatingActionButton;
import cc.trity.floatingactionbutton.FloatingActionsMenu;

public class UpdateDiaryActivity extends BaseActivity
{

    @Bind(R.id.update_diary_tv_date)
    TextView mUpdateDiaryTvDate;
    @Bind(R.id.update_diary_et_title)
    EditText mUpdateDiaryEtTitle;
    @Bind(R.id.update_diary_et_content)
    LinedEditText mUpdateDiaryEtContent;
    @Bind(R.id.update_diary_fab_back)
    FloatingActionButton mUpdateDiaryFabBack;
    @Bind(R.id.update_diary_fab_add)
    FloatingActionButton mUpdateDiaryFabAdd;
    @Bind(R.id.update_diary_fab_delete)
    FloatingActionButton mUpdateDiaryFabDelete;
    @Bind(R.id.right_labels)
    FloatingActionsMenu mRightLabels;

    private DiaryDatabaseHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_diary);

        initView();
    }

    private void initView()
    {
        //初始化Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("修改日记");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        mHelper = new DiaryDatabaseHelper(this, "Diary.db", null, 1);

        Intent intent = getIntent();
        mUpdateDiaryTvDate.setText("今天，" + DateUtil.getDate());
        mUpdateDiaryEtTitle.setText(intent.getStringExtra("title"));
        mUpdateDiaryEtContent.setText(intent.getStringExtra("content"));
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

    public static void actionStart(Context context, String title, String content)
    {
        Intent intent = new Intent(context, UpdateDiaryActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        context.startActivity(intent);
    }


    @OnClick({R.id.update_diary_tv_date, R.id.update_diary_et_title, R.id.update_diary_et_content, R.id.update_diary_fab_back, R.id.update_diary_fab_add, R.id.update_diary_fab_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update_diary_tv_date:
                break;
            case R.id.update_diary_et_title:
                break;
            case R.id.update_diary_et_content:
                break;
            case R.id.update_diary_fab_back:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("确定要删除该日记吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        String title = mUpdateDiaryEtTitle.getText().toString();
                        SQLiteDatabase dbDelete = mHelper.getWritableDatabase();
                        dbDelete.delete("Diary", "title = ?", new String[]{title});
                        NotePadActivity.actionStart(UpdateDiaryActivity.this);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
                break;
            case R.id.update_diary_fab_add:
                SQLiteDatabase dbUpdate = mHelper.getWritableDatabase();
                ContentValues valuesUpdate = new ContentValues();
                String title = mUpdateDiaryEtTitle.getText().toString();
                String content = mUpdateDiaryEtContent.getText().toString();
                valuesUpdate.put("title", title);
                valuesUpdate.put("content", content);
                dbUpdate.update("Diary", valuesUpdate, "title = ?", new String[]{title});
                dbUpdate.update("Diary", valuesUpdate, "content = ?", new String[]{content});
                NotePadActivity.actionStart(this);
                break;
            case R.id.update_diary_fab_delete:
                NotePadActivity.actionStart(this);
                break;
        }
    }

}
