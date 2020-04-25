package com.hdu.tengyangzoo.util.network;

import android.content.Context;
import android.widget.Toast;

import com.hdu.tengyangzoo.model.BilibiliBaseModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseBilibiliObserver<T> implements Observer<BilibiliBaseModel<T>> {
    private static final String TAG = "BaseBilibiliObserver";
    private Context context;


    public  abstract void onSuccess(T t);

    public BaseBilibiliObserver(Context context) {
        this.context = context;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BilibiliBaseModel<T> bilibiliBaseModel) {
        if (bilibiliBaseModel.code==0){
            Toast.makeText(context,"成功连接",Toast.LENGTH_SHORT).show();
            onSuccess(bilibiliBaseModel.data);
        }else {
            onFail(bilibiliBaseModel.code,bilibiliBaseModel.message);
        }
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(context,"没能获得信息",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onComplete() {

    }

    public void onFail(int code ,String message){
        Toast.makeText(context,"错误码"+code+" 信息"+message,Toast.LENGTH_SHORT).show();
    }
}
