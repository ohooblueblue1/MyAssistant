package com.zdd.assistant.provider;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.zdd.assistant.app.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.BmobUser;

/**
 * Project Name: MyAssistant
 * File Name:    RobotMsgProvider.java
 * <p>
 * Description: 图灵机器人消息提供者
 *
 * @author zdd
 * @date 2017年02月19日 21:08
 */
public class RobotMsgProvider {

    //图灵机器人API key
    public static final String API_KEY = "c9e91825064d4ccda8893b3733686592";

    public static final String API_URL = "http://www.tuling123.com/openapi/api";

    /**
     * @param info     要发送的消息内容
     * @param listener
     */
    public void sendMessage(String info, final OnResponseListener listener) {
        listener.onBefore();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, API_URL, getParams(info), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                listener.onSuccess(jsonObject);
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
