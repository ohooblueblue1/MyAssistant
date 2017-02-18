package com.zdd.assistant.provider;

import android.support.annotation.NonNull;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.zdd.assistant.app.MyApplication;
import com.zdd.assistant.entity.weather.WeatherDaily;
import com.zdd.assistant.entity.weather.WeatherNow;
import com.zdd.assistant.entity.weather.WeatherSuggestion;
import com.zdd.assistant.util.UrlUtil;

import java.util.HashMap;

/**
 * Project Name: MyAssistant
 * File Name:    WeatherProvider.java
 *
 * Description: 天气信息数据提供者
 *
 * @author ZDD
 * @date 2017年02月18日 14:52
 */
public class WeatherProvider
{
    //天气API key
    public static final String KEY = "315ihivewwmt0oib";
    //URL--当前天气
    public static final String URL_WEATHER_NOW = "https://api.thinkpage.cn/v3/weather/now.json";
    //URL--多日天气
    public static final String URL_WEATHER_DAILY = "https://api.thinkpage.cn/v3/weather/daily.json";
    //URL--生活指数
    public static final String URL_WEATHER_SUGGESTION = "https://api.thinkpage.cn/v3/life/suggestion.json";
    //URL--搜索城市
    public static final String URL_CITY_SEARCH = "https://api.thinkpage.cn/v3/location/search.json";


    //获取已经添加key的HashMap
    private HashMap<String, String> getBaseParamsMap()
    {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", KEY);
        return params;
    }


    /**
     * 加载当前天气
     *
     * @param location 当前城市，传入"ip"可直接识别ip所在城市
     */
    public void loadWeatherNow(@NonNull String location, final OnResponseListener listener)
    {
        //加载预处理
        listener.onBefore();
        //拼接参数
        HashMap<String, String> paramsMap = getBaseParamsMap();
        paramsMap.put("location", location);
        String url = UrlUtil.getURL(URL_WEATHER_NOW, paramsMap);
        //发起请求
        GsonRequest<WeatherNow> request = new GsonRequest<WeatherNow>(url, WeatherNow.class, new Response.Listener<WeatherNow>()
        {
            @Override
            public void onResponse(WeatherNow weatherNow)
            {
                listener.onSuccess(weatherNow);
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
                     .add(request);
    }


    /**
     * 加载多日天气
     * @param location 查询的城市
     * @param days     查询天数（1-3天）
     * @param listener
     */
    public void loadWeatherDaily(@NonNull String location, int days, final OnResponseListener listener)
    {

        //加载预处理
        listener.onBefore();
        //拼接参数
        HashMap<String, String> paramsMap = getBaseParamsMap();
        paramsMap.put("location", location);
        paramsMap.put("days", days + "");
        String url = UrlUtil.getURL(URL_WEATHER_DAILY, paramsMap);
        //发起请求
        GsonRequest<WeatherDaily> request = new GsonRequest<WeatherDaily>(url, WeatherDaily.class, new Response.Listener<WeatherDaily>()
        {
            @Override
            public void onResponse(WeatherDaily weatherDaily)
            {
                listener.onSuccess(weatherDaily);
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
                     .add(request);
    }


    /**
     * 加载生活指数
     * @param location 查询的城市
     * @param listener
     */
    public void loadWeatherSuggestion(@NonNull String location,final OnResponseListener listener){
        //加载预处理
        listener.onBefore();
        //拼接参数
        HashMap<String, String> paramsMap = getBaseParamsMap();
        paramsMap.put("location", location);
        String url = UrlUtil.getURL(URL_WEATHER_SUGGESTION, paramsMap);
        //发起请求
        GsonRequest<WeatherSuggestion> request  = new GsonRequest<WeatherSuggestion>(url, WeatherSuggestion.class, new Response.Listener<WeatherSuggestion>()
        {
            @Override
            public void onResponse(WeatherSuggestion weatherSuggestion)
            {
                listener.onSuccess(weatherSuggestion);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
                listener.onFailure();
            }
        });
        MyApplication.getRequestQueue().add(request);
    }

}
