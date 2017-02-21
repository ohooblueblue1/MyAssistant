package com.zdd.assistant.provider;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.zdd.assistant.app.MyApplication;
import com.zdd.assistant.entity.cook.CookList;
import com.zdd.assistant.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.BmobUser;

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
    //图灵机器人API key
    public static final String API_KEY = "c9e91825064d4ccda8893b3733686592";

    public static final String API_URL = "http://www.tuling123.com/openapi/api";

    public void  searchCook(String info, final OnResponseListener listener){
        listener.onBefore();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, API_URL, getParams(info), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                if(jsonObject.optInt("code") != 308000){
                    ToastUtil.showToast(MyApplication.getAppContext(),"对不起，未查找到您要的菜谱信息");
                    listener.onFailure();
                    return;
                }
                Gson gson = new Gson();
                CookList result = gson.fromJson(jsonObject.toString(), CookList.class);
                listener.onSuccess(result);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onFailure();
            }
        });
        MyApplication.getRequestQueue().add(request);
    }


    private JSONObject getParams(String info) {
        //根据输入的用户名密码包装参数
        JSONObject msgObject = new JSONObject();
        try {
            msgObject.put("key",API_KEY);
            msgObject.put("info",info);
            msgObject.put("userid", BmobUser.getCurrentUser().getUsername());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return msgObject;
    }

}
