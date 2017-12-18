package com.oliver.baselibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void post(View v) {
//        Flowable.create(new FlowableOnSubscribe<String>() {
//            @Override
//            public void subscribe(@NonNull final FlowableEmitter<String> e1) throws Exception {
//
//                HttpUtils.with(MainActivity.this).addParam("", new File(""))
//                        .url("").post().execute(new EngineCallBack() {
//                    @Override
//                    public void onPreExecute(Context context, Map<String, Object> params) {
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        e1.onError(new Throwable(e.getMessage()));
//                    }
//
//                    @Override
//                    public void onSuccess(String result) {
//                        e1.onNext(result);
//                    }
//                });
//
//            }
//        }, BackpressureStrategy.BUFFER)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String file) throws Exception {
//
//                    }
//                });

    }
}
