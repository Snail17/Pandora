/**
 * Created by WHY on 2015/8/5 11:34
 * Copyright (c) 2015年 Beijing Yunshan Information Technology Co., Ltd. All rights reserved.
 */
package com.pandora.core.utils;


import android.content.Context;
import android.text.TextUtils;
import android.util.Log;


import com.pandora.BuildConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 */
public class LogUtils {

    private static final String v = "v";
    private static final String d = "d";
    private static final String i = "i";
    private static final String w = "w";
    private static final String e = "e";
    private static final int MAX_LOG_LINE_LENGTH = 2048 * 1024;
    private static final int LOG_KEEP_TIME = 1000 * 60 * 60 * 24 * 3;
    private static Context context;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");

    private static final boolean ENCRYPT_LOG = false;
    private static String DEFAULT_TAG = "Pandora";
    private static boolean sDebuggable = BuildConfig.DEBUG;
    private static long sTimestamp = 0;
    private static List<Runnable> tasks = new ArrayList<>();
    private static AtomicBoolean taskExecuting = new AtomicBoolean(false);

    /**
     * @param r s
     */
    private static synchronized void addTask(Runnable r) {
        tasks.add(r);
        notifyNewTask();
    }

    /**
     * @return s
     */
    private static synchronized Runnable dequeueTask() {
        if (tasks.size() > 0) {

            return tasks.remove(tasks.size() - 1);
        } else {

            return null;
        }
    }

    /**
     *
     */
    private static void notifyNewTask() {
        if (taskExecuting.get()) {

            return;
        }
        taskExecuting.set(true);
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    Runnable task = dequeueTask();
                    if (task == null) {
                        taskExecuting.set(false);
                        break;
                    }
                    task.run();
                }
            }
        }.start();
    }

    /**
     * 清理本地已缓存的日志信息
     */
    public static void updateLogFile() {
        File logDir = new File(context.getCacheDir().getAbsolutePath() + "/log");
        if (logDir.exists() && logDir.isDirectory()) {
            File[] files = logDir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String filename) {
                    return filename.endsWith(".log");
                }
            });
            for (File file : files) {
                try {
                    String substring = file.getName().substring(0, file.getName().lastIndexOf("."));
                    Date date = sdf.parse(substring);
                    Date currentDate = sdf.parse(getCurrentDate());
                    if ((currentDate.getTime() - date.getTime()) > LOG_KEEP_TIME) {
                        file.delete();
                    }
                } catch (ParseException e1) {
                    e(e1);
                }
            }
        }
    }

    /**
     * @param context s
     */
    public static void setContext(Context context) {
        LogUtils.context = context;
    }

    /**
     * @param tag s
     */
    public static void setDefaultTag(String tag) {
        DEFAULT_TAG = tag;
    }

    /**
     * @param t s
     */
    public static void e(Throwable t) {
        log(e, DEFAULT_TAG, t);
    }

    /**
     * @param msg s
     */
    public static void e(String msg) {
        log(e, DEFAULT_TAG, msg);
    }

    /**
     * @param msg s
     */
    public static void v(String msg) {
        log(v, DEFAULT_TAG, msg);
    }

    /**
     * @param msg s
     */
    public static void d(String msg) {
        log(d, DEFAULT_TAG, msg);
    }

    /**
     * @param msg s
     */
    public static void i(String msg) {
        log(i, DEFAULT_TAG, msg);
    }

    /**
     * @param msg s
     */
    public static void w(String msg) {
        log(w, DEFAULT_TAG, msg);
    }

    /**
     * @param level s
     * @param sTag  s
     * @param msg   s
     */
    private static void log(String level, String sTag, String msg) {
        if (sDebuggable) {
            try {
                Method method = Log.class.getMethod(level, new Class[]{String.class, String.class});
                if (null != msg && msg.length() > MAX_LOG_LINE_LENGTH) {
                    int start = 0;
                    int end = 0;
                    int len = msg.length();
                    while (true) {
                        start = end;
                        end = start + MAX_LOG_LINE_LENGTH;
                        String logText;
                        if (end >= len) {
                            logText = msg.substring(start, len);
                            method.invoke(null, sTag, logText);
                            break;
                        } else {
                            logText = msg.substring(start, end);
                            method.invoke(null, sTag, logText);
                        }
                    }
                } else {
                    method.invoke(null, sTag, msg);
                }
            } catch (Exception e1) {
                Log.e(DEFAULT_TAG, "", e1);
            }
        }
    }

    /**
     * @param level s
     * @param sTag  s
     * @param tr    s
     */
    private static void log(String level, String sTag, Throwable tr) {
        if (sDebuggable) {
            try {
                Method method = Log.class.getMethod(level, new Class[]{String.class, String.class, Throwable.class});
                method.invoke(null, sTag, "", tr);
            } catch (Exception e1) {
                Log.e(DEFAULT_TAG, "", e1);
            }
        }
    }

    /**
     * @param msg s
     */
    public static void markStart(String msg) {
        sTimestamp = System.currentTimeMillis();
        if (!TextUtils.isEmpty(msg)) {
            e("[Started|" + sTimestamp + "]" + msg);
        }
    }

    /**
     * @param msg s
     */
    public static void elapsed(String msg) {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - sTimestamp;
        sTimestamp = currentTime;
        e("[Elapsed|" + elapsedTime + "]" + msg);
    }

    /**
     * @return s
     */
    public static boolean isDebuggable() {
        return sDebuggable;
    }

    /**
     * @param debugable s
     */
    public static void setDebuggable(boolean debugable) {
        sDebuggable = debugable;
    }

    /**
     * @param log s
     */
    public static void log2File(String log) {
        log2File(log, true);
    }

    /**
     * @param log    s
     * @param append s
     */
    public static void log2File(String log, boolean append) {
//        if (ENCRYPT_LOG) {
//            log = Base64.encodeToString(log.getBytes());
//        }
//        synchronized (sLogLock) {
//            FileUtils.writeFile(log + "\r\n", path, append);
//        }
        context.getCacheDir().getAbsolutePath();
        BufferedWriter bos = null;
        try {
            File logDir = new File(context.getCacheDir().getAbsolutePath() + "/log");
            logDir.mkdirs();
            bos = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logDir.getAbsolutePath() +
                    "/" + getCurrentDate() + ".log", append), "utf-8"));
            bos.write(log);
            bos.newLine();
        } catch (Exception e1) {
            e(e1);
        } finally {
            if (bos != null) {

                try {
                    bos.close();
                } catch (IOException e1) {
                    e(e1);
                }
            }
        }
    }

    /**
     *
     */
    public static void clearCachedLogFiles() {
        File logDir = new File(context.getCacheDir().getAbsolutePath() + "/log");
        if (!logDir.exists() || logDir.isDirectory()) {

            return;
        }
        for (File file : logDir.listFiles()) {
            file.delete();
        }
    }

    /**
     * @return s
     */
    public static File[] getCachedLogFiles() {
        File logDir = new File(context.getCacheDir().getAbsolutePath() + "/log");
        if (!logDir.exists() || logDir.isDirectory()) {

            return null;
        }
        File[] files = logDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                if (!filename.endsWith(".log")) {

                    return false;
                }
                String substring = filename.substring(0, filename.lastIndexOf("."));
                try {
                    sdf.parse(substring);
                    return true;
                } catch (ParseException e1) {
                    e(e1);
                }
                return false;
            }
        });
        return files;
    }

    /**
     * @return s
     */
    private static String getCurrentDate() {
        return sdf.format(new Date());
    }

    /**
     * @param tr s
     * @return s
     */
    public static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        return sw.toString();
    }

}
