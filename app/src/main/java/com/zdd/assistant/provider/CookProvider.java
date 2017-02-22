package com.zdd.assistant.provider;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.zdd.assistant.app.MyApplication;
import com.zdd.assistant.entity.cook.CookDetail;
import com.zdd.assistant.entity.cook.CookList;

/**
 * Project Name: MyAssistant
 * File Name:    CookProvider.java
 * ClassName:    CookProvider
 *
 * Description: 菜谱信息提供者
 *
 * @author ZDD
 * @date 2017年02月21日 20:12
 */
public class CookProvider
{
    public static final String IMG_URL_HEAD = "http://tnfs.tngou.net/image";
    //菜谱分类
    public static final String URL_COOK_CATEGORY = "http://www.tngou.net/api/cook/classify";

    public static final String URL_COOK_LIST = "http://www.tngou.net/api/cook/list?id=";

    public static final String URL_COOK_DETAIL = "http://www.tngou.net/api/cook/show?id=";



    /**
     * 根据分类id获取该分类下菜谱列表
     *
     * @param id       分类id
     * @param listener
     */
    public void getCookList(int id, final OnResponseListener listener)
    {
        listener.onBefore();
        GsonRequest<CookList> gsonRequest = new GsonRequest<CookList>(URL_COOK_LIST + id, CookList.class, new Response.Listener<CookList>()
        {
            @Override
            public void onResponse(CookList cookList)
            {
                listener.onSuccess(cookList);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
                listener.onFailure();
            }
        });
        MyApplication.getRequestQueue()
                     .add(gsonRequest);
    }

    public void getCookDetail(long id, final OnResponseListener listener)
    {
        listener.onBefore();
        GsonRequest<CookDetail> gsonRequest = new GsonRequest<CookDetail>(URL_COOK_DETAIL + id, CookDetail.class, new Response.Listener<CookDetail>()
        {
            @Override
            public void onResponse(CookDetail cookDetail)
            {
                listener.onSuccess(cookDetail);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
                listener.onFailure();
            }
        });
        MyApplication.getRequestQueue().add(gsonRequest);
    }


}
