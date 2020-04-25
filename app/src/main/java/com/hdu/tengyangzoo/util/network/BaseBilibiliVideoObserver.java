package com.hdu.tengyangzoo.util.network;

import android.content.Context;
import android.widget.Toast;

import com.hdu.tengyangzoo.model.BilibiliVideoBaseModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseBilibiliVideoObserver<T> implements Observer<BilibiliVideoBaseModel<T>> {

    private static final String TAG = "BaseBilibiliVideoObserv";
    private Context context;


    public  abstract void onSuccess(T t);

    public BaseBilibiliVideoObserver(Context context) {
        this.context = context;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BilibiliVideoBaseModel<T> t) {
        if (t.status){
            Toast.makeText(context,"成功获取",Toast.LENGTH_SHORT).show();
            onSuccess(t.data);
        }else{
            Toast.makeText(context,"失败 "+t.data,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(context,"获取失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete() {

    }
}
