package com.hdu.tengyangzoo.ui.fragment.lubo;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.hdu.tengyangzoo.R;
import com.hdu.tengyangzoo.model.BilibiliBaseModel;
import com.hdu.tengyangzoo.model.BilibiliVideoBaseModel;
import com.hdu.tengyangzoo.model.LuBoInfo;
import com.hdu.tengyangzoo.model.UpInfo;
import com.hdu.tengyangzoo.model.constant.Constants;
import com.hdu.tengyangzoo.ui.fragment.BaseFragment;
import com.hdu.tengyangzoo.util.network.ApiService;
import com.hdu.tengyangzoo.util.network.BaseBilibiliObserver;
import com.hdu.tengyangzoo.util.network.BaseBilibiliVideoObserver;
import com.hdu.tengyangzoo.util.network.HttpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LuBoFragment extends BaseFragment {
    private static final String TAG = "LuBoFragment";
    TabLayout mTabLayout;
    ViewPager mViewPager;

//    //看桑，小风，歌手,魔男,P元帅
//    public int[] UpId= {223236400,180166708,17678573,172917055,109629667};
    private List<UpInfo> UpInfoList = new ArrayList<>();
    private SparseArray<LuboListFragment> fragmentSparseArray = new SparseArray<>();
    private List<Integer> upInfo;
    private LuboListFragment currentFragment;

    public static LuBoFragment newInstance(){
        return new LuBoFragment();
    }

    @Override
    protected void initWidget(View view) {
        mTabLayout = view.findViewById(R.id.lubo_tabLayout);
        mViewPager = view.findViewById(R.id.lubo_viewpager);
    }

    @Override
    protected void initEventAndData() {
        getUpInfo();

    }

    private void getUpInfo() {
        for(int i=0;i<Constants.UpId.length;i++) {
            Observable<BilibiliBaseModel<UpInfo>> observable = HttpClient.getApi(ApiService.BILIBILI_URL).getUpInfo(Constants.UpId[i]);
            observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseBilibiliObserver<UpInfo>(_mActivity) {
                        @Override
                        public void onSuccess(UpInfo upInfo) {
                            Log.e(TAG, "onSuccess: " + upInfo.name);
                            UpInfoList.add(upInfo);
                            if(UpInfoList.size()==Constants.UpId.length) {
                                Log.e(TAG, "总长度为 " + UpInfoList.size());
                                initViewPagerAndTabLayout();
                            }

                        }
                    });
        }

    }

    @Override
    protected void initView() {
        Log.e(TAG, "initView: " );

    }

    private void initViewPagerAndTabLayout() {
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                LuboListFragment luboListFragment = fragmentSparseArray.get(position);
                Log.e(TAG, "绑定TabLayout" );
                if (luboListFragment!=null)
                    return luboListFragment;
                else{
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.LUBO_CID,UpInfoList.get(position).mid);
                    luboListFragment = LuboListFragment.newInstance(bundle);
                    fragmentSparseArray.put(position,luboListFragment);
                    return luboListFragment;
                }
            }

            @Override
            public int getCount() {
                return UpInfoList==null ? 0:UpInfoList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return UpInfoList.get(position).name;

            }
        });

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //取消页面切换动画
                mViewPager.setCurrentItem(tab.getPosition(),false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_lubo;
    }

    @Override
    public void onDestroy() {
        if (fragmentSparseArray!=null){
            fragmentSparseArray.clear();
            fragmentSparseArray = null;
        }
        if(UpInfoList!=null){
            UpInfoList.clear();
            UpInfoList = null;
        }


        super.onDestroy();
    }
}
