package com.hdu.tengyangzoo.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hdu.tengyangzoo.R;
import com.hdu.tengyangzoo.model.constant.Constants;
import com.hdu.tengyangzoo.ui.fragment.dongtai.DongtaiFragment;
import com.hdu.tengyangzoo.ui.fragment.lubo.LuBoFragment;
import com.hdu.tengyangzoo.ui.fragment.mine.MineFragment;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    Toolbar mToolbar;
    TextView mTitle;
    FrameLayout mFrameLayout;
    BottomNavigationView mBottomNavigationView;


    private LuBoFragment mLuBoFragment;
    private DongtaiFragment mDongTaiFragment;
    private MineFragment mMineFragment;
    private int mCurrentFgIndex = 0;
    private int mLastFgIndex = -1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {//恢复存入的fragment索引
        if(savedInstanceState!=null){
            mCurrentFgIndex = savedInstanceState.getInt(Constants.CURRENT_FRAGMENT_KEY);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {//存入当前的fragment索引
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.CURRENT_FRAGMENT_KEY , mCurrentFgIndex);
    }

    @Override
    protected void initWidget() {
        mToolbar = findViewById(R.id.toolbar);
        mTitle = findViewById(R.id.toolbar_title);
        mFrameLayout = findViewById(R.id.fragment_group);
        mBottomNavigationView = findViewById(R.id.bottom_navigation_view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayShowTitleEnabled(false);
            mTitle.setText(R.string.lubo);
        }
    }

    @Override
    protected void initView() {
        showFragment(mCurrentFgIndex);
        initBottomNavigationView();
    }


    @Override
    protected void initEventAndData() {

    }

    private void showFragment(int index) {
        mCurrentFgIndex = index;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        mLastFgIndex = index;
        switch (index){
            case Constants.TYPE_LUBO:
                mTitle.setText(R.string.lubo);
                if (mLuBoFragment==null){
                    mLuBoFragment = LuBoFragment.newInstance();
                    transaction.add(R.id.fragment_group,mLuBoFragment);
                }
                transaction.show(mLuBoFragment);
                break;
            case Constants.TYPE_DONGTAI:
                mTitle.setText(R.string.dongtai);
                if (mDongTaiFragment==null){
                    mDongTaiFragment = DongtaiFragment.newInstance();
                    transaction.add(R.id.fragment_group,mDongTaiFragment);
                }
                transaction.show(mDongTaiFragment);
                break;
            case  Constants.TYPE_MINE:
                mTitle.setText(R.string.mine);
                if (mMineFragment==null){
                    mMineFragment = MineFragment.newInstance();
                    transaction.add(R.id.fragment_group,mMineFragment);
                }
                transaction.show(mMineFragment);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        switch (mLastFgIndex){
            case Constants.TYPE_LUBO:
                if (mLuBoFragment!=null)
                    transaction.hide(mLuBoFragment);
                break;
            case Constants.TYPE_DONGTAI:
                if (mDongTaiFragment!=null)
                    transaction.hide(mDongTaiFragment);
                break;
            case Constants.TYPE_MINE:
                if (mMineFragment!=null)
                    transaction.hide(mMineFragment);
                break;
            default:
                break;

        }
    }

    private void initBottomNavigationView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab_lubo:
                        showFragment(Constants.TYPE_LUBO);
                        break;
                    case R.id.tab_dongtai:
                        showFragment(Constants.TYPE_DONGTAI);
                        break;
                    case R.id.tab_mine:
                        showFragment(Constants.TYPE_MINE);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }







}
