package com.zdd.assistant.provider;

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
    //URL--当前天气
    public static final String URL_WEATHER_NOW = "https://api.thinkpage.cn/v3/weather/now.json";
    //URL--多日天气
    public static final String URL_WEATHER_DAILY = "https://api.thinkpage.cn/v3/weather/daily.json";
    //URL--生活指数
    public static final String URL_WEATHER_SUGGESTION = "https://api.thinkpage.cn/v3/life/suggestion.json";
    //URL--搜索城市
    public static final String URL_CITY_SEARCH = "https://api.thinkpage.cn/v3/location/search.json";

    /**
     * 加载当前天气
     */
    public void loadWeatherNow(){

    }

}
