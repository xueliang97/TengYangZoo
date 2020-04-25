package com.hdu.tengyangzoo.ui.activity;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class BaseActivity extends SupportActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        
        initWidget();
        initToolbar();
        initView();
        initEventAndData();
    }

    protected abstract void initWidget();


    protected abstract int getLayoutId() ;

    protected abstract void initToolbar();

    protected abstract void initView();

    protected abstract void initEventAndData();


}
