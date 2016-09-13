package com.yeebee.utils;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/7/5 0005.
 */

public class GsonTools {

    //解析出一个对象
    public static <T> T getObject(String jsonStr, Class<T> cls) {
        T t = null;
        try {
            t = new Gson().fromJson(jsonStr, cls);
        } catch (Exception e) {

        }
        return t;
    }
}
