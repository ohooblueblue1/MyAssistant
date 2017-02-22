package com.zdd.assistant.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zdd.assistant.R;
import com.zdd.assistant.entity.cook.CookMenu;

import java.util.List;

/**
 * Project Name: MyAssistant
 * File Name:    CookMenuAdapter.java
 * ClassName:    CookMenuAdapter
 *
 * Description: 菜谱分类适配器
 *
 * @author ZDD
 * @date 2017年02月22日 18:48
 */
public class CookMenuAdapter extends BaseAdapter
{
    private List<CookMenu> mListMenus;
    private LayoutInflater mInflater;
    private Context mContext;

    public CookMenuAdapter(List<CookMenu> mListMenus, Context mContext)
    {
        this.mListMenus = mListMenus;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount()
    {
        return mListMenus.size();
    }

    @Override
    public CookMenu getItem(int position)
    {
        return mListMenus.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        CookMenu data = getItem(position);
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext)
                                        .inflate(R.layout.item_menu_gv, parent, false);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(data.getName());
        viewHolder.tvName.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(mContext, data.getImgId()), null, null);
        return convertView;
    }

    static class ViewHolder
    {
        TextView tvName;
    }

}