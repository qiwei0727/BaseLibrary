package com.oliver.base.net;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * OkHttp默认的引擎
 */
public class OkHttpEngine implements IHttpEngine {
    private static OkHttpClient mOkHttpClient = new OkHttpClient();

    public OkHttpEngine() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                .addNetworkInterceptor(new StethoInterceptor())
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient = builder.build();
    }

    @Override
    public void post(Context context, String url, Map<String, Object> params, final
    EngineCallBack callBack) {

        final String jointUrl = HttpUtils.jointParams(url, params);  //打印
        Log.e("Post请求路径：", jointUrl);

        RequestBody requestBody = appendBody(params);
        final Request request = new Request.Builder()
                .url(url)
                .tag(context)
                .post(requestBody)
                .build();

        Call call = mOkHttpClient.newCall(request);

        call.enqueue(
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        callBack.onError(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        // 这个 两个回掉方法都不是在主线程中
                        if (response.isSuccessful()) {
                            String result = response.body().string();
                            Log.d("post:", result);
                            callBack.onSuccess(result);
                        } else {
                            callBack.onError(new Exception("请求失败"));
                        }
                    }
                }
        );
    }

    /**
     * 取消网络请求
     *
     * @param tag
     */
    public void cancelTag(Object tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    /**
     * 组装post请求参数body
     */
    protected RequestBody appendBody(Map<String, Object> params) {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        addParams(builder, params);
        return builder.build();
    }

    // 添加参数
    private void addParams(MultipartBody.Builder builder, Map<String, Object> params) {
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                Object value = params.get(key);
                if (value instanceof File) { // 处理文件 --> Object File
                    File file = (File) value;
                    builder.addFormDataPart(key, file.getName(), RequestBody
                            .create(MediaType.parse(guessMimeType(file
                                    .getAbsolutePath())), file));
                } else if (value instanceof List) { // 代表提交的是 List集合
                    try {
                        List<File> listFiles = (List<File>) value;
                        for (int i = 0; i < listFiles.size(); i++) { // 获取文件
                            File file = listFiles.get(i);
                            builder.addFormDataPart(key + i, file.getName(), RequestBody
                                    .create(MediaType.parse(guessMimeType(file
                                            .getAbsolutePath())), file));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    builder.addFormDataPart(key, value + "");
                }
            }
        }
    }

    /**
     * 猜测文件类型
     */
    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    @Override
    public void get(Context context, String url, Map<String, Object> params, final EngineCallBack
            callBack) {
        url = HttpUtils.jointParams(url, params);

        Log.d("Get请求路径：", url);

        Request.Builder requestBuilder = new Request.Builder().url(url).tag(context);
        //可以省略，默认是GET请求
        Request request = requestBuilder.build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resultJson = response.body().string();
                callBack.onSuccess(resultJson);
                Log.d("Get返回结果：", resultJson);
            }
        });
    }
}
