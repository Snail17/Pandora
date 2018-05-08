package com.pandora.core.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.pandora.model.PandoraApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * SharedPreferences 工具类
 */
public class SPUtils {

    private static final String CONFIG = "config";
    /**
     * 获取SharedPreferences实例对象
     *
     * @param fileName fileName
     */
    private static SharedPreferences getSharedPreference(String fileName) {
        return PandoraApplication.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }


    /**
     * 保存一个String类型的值！
     * @param key key
     * @param value value
     */
    public static void putString(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreference(CONFIG).edit();
        editor.putString(key, value).apply();
    }

    /**
     * 获取String的value
     * @param key key
     * @param defValue defValue
     * @return return
     */
    public static String getString(String key, String defValue) {
        SharedPreferences sharedPreference = getSharedPreference(CONFIG);
        return sharedPreference.getString(key, defValue);
    }


    /**
     * 保存一个Boolean类型的值！
     * @param key key
     * @param value value
     */
    public static void putBoolean(String key, Boolean value) {
        SharedPreferences.Editor editor = getSharedPreference(CONFIG).edit();
        editor.putBoolean(key, value).apply();
    }

    /**
     * 获取boolean的value
     * @param key key
     * @param defValue defValue
     * @return return
     */
    public static boolean getBoolean(String key, Boolean defValue) {
        SharedPreferences sharedPreference = getSharedPreference(CONFIG);
        return sharedPreference.getBoolean(key, defValue);
    }

    /**
     * 保存一个int类型的值！
     * @param key key
     * @param value value
     */
    public static void putInt(String key, int value) {
        SharedPreferences.Editor editor = getSharedPreference(CONFIG).edit();
        editor.putInt(key, value).apply();
    }

    /**
     * 获取int的value
     * @param key key
     * @param defValue defValue
     * @return return
     */
    public static int getInt(String key, int defValue) {
        SharedPreferences sharedPreference = getSharedPreference(CONFIG);
        return sharedPreference.getInt(key, defValue);
    }

    /**
     * 保存一个float类型的值！
     * @param fileName fileName
     * @param key key
     * @param value value
     */
    public static void putFloat(String fileName, String key, float value) {
        SharedPreferences.Editor editor = getSharedPreference(fileName).edit();
        editor.putFloat(key, value).apply();
    }

    /**
     *
     * 获取float的value
     * @param key key
     * @param defValue defValue
     * @return return
     */
    public static float getFloat(String key, Float defValue) {
        SharedPreferences sharedPreference = getSharedPreference(CONFIG);
        return sharedPreference.getFloat(key, defValue);
    }

    /**
     * 保存一个long类型的值！
     * @param key key
     * @param value value
     */
    public static void putLong(String key, long value) {
        SharedPreferences.Editor editor = getSharedPreference(CONFIG).edit();
        editor.putLong(key, value).apply();
    }

    /**
     * 获取long的value
     * @param key key
     * @param defValue defValue
     * @return return
     */
    public static long getLong(String key, long defValue) {
        SharedPreferences sharedPreference = getSharedPreference(CONFIG);
        return sharedPreference.getLong(key, defValue);
    }

    /**
     * 取出List<String>
     *
     * @param key     List<String> 对应的key
     * @return List<String>
     */
    public static List<String> getStrListValue(String key) {
        List<String> strList = new ArrayList<String>();
        int size = getInt(key + "size", 0);
        //Log.d("sp", "" + size);
        for (int i = 0; i < size; i++) {
            strList.add(getString(key + i, null));
        }
        return strList;
    }

    /**
     * 存储List<String>
     *
     * @param key     List<String>对应的key
     * @param strList 对应需要存储的List<String>
     */
    public static void putStrListValue(String key, List<String> strList) {
        if (null == strList) {
            return;
        }
        // 保存之前先清理已经存在的数据，保证数据的唯一性
        removeStrList(key);
        int size = strList.size();
        putInt(key + "size", size);
        for (int i = 0; i < size; i++) {
            putString(key + i, strList.get(i));
        }
    }

    /**
     * 清空List<String>所有数据
     *
     * @param key     List<String>对应的key
     */
    public static void removeStrList(String key) {
        int size = getInt(key + "size", 0);
        if (0 == size) {
            return;
        }
        remove(key + "size");
        for (int i = 0; i < size; i++) {
            remove(key + i);
        }
    }

    /**
     * 清空对应key数据
     * @param key key
     */
    public static void remove(String key) {
        SharedPreferences.Editor editor = getSharedPreference(CONFIG).edit();
        editor.remove(key).apply();
    }

    /**
     * 保存 对象
     * @param key s
     * @param object s
     */
    public static void putObjectToJson(String key, Object object) {
        SharedPreferences sharedPreference = getSharedPreference(CONFIG);
        SharedPreferences.Editor editor = sharedPreference.edit();
        Gson gson = new Gson();
        String json = gson.toJson(object);
        editor.putString(key, json);
        editor.commit();
    }

    /**
     *
     * @param key  d
     * @param clazz d
     * @param <T> d
     * @return d
     */
    public static  <T> T getObjectFromJson(String key, Class<T> clazz) {
        T t;
        SharedPreferences sharedPreference = getSharedPreference(CONFIG);
        Gson gson = new Gson();
        String json = sharedPreference.getString(key, null);
        t = gson.fromJson(json, clazz);
        return t;
    }



}
