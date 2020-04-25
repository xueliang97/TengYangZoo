package com.hdu.tengyangzoo.ui.fragment.lubo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hdu.tengyangzoo.R;
import com.hdu.tengyangzoo.model.BilibiliVideoBaseModel;
import com.hdu.tengyangzoo.model.LuBoInfo;
import com.hdu.tengyangzoo.model.Vlist;
import com.hdu.tengyangzoo.model.constant.Constants;
import com.hdu.tengyangzoo.ui.fragment.BaseFragment;
import com.hdu.tengyangzoo.util.network.ApiService;
import com.hdu.tengyangzoo.util.network.BaseBilibiliVideoObserver;
import com.hdu.tengyangzoo.util.network.HttpClient;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LuboListFragment extends BaseFragment {
    private static final String TAG = "LuboListFragment";
    SmartRefreshLayout mSmartRefreshLayout;
    RecyclerView mRecyclerView;

    private LuboListAdapter mAdapter;

    private int cid;
    private int currentPage = 1;
    private boolean isRefresh = true;

    public static LuboListFragment newInstance(Bundle bundle){
        LuboListFragment luboListFragment = new LuboListFragment();
        luboListFragment.setArguments(bundle);
        return luboListFragment;
    }

    @Override
    protected void initWidget(View view) {
        mSmartRefreshLayout = view.findViewById(R.id.lubo_smart_refresh_layout);
        mRecyclerView = view.findViewById(R.id.lubo_list_recyclerView);
    }

    //网络请求
    @Override
    protected void initEventAndData() {
        if (getArguments()!=null){
            cid = getArguments().getInt(Constants.LUBO_CID);
            initRefreshLayout();
            isRefresh = true;
            currentPage = 1;
            getLuBoListData();
        }
    }

    private void initRefreshLayout() {
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isRefresh = true;
                currentPage = 1;
                getLuBoListData();
                refreshLayout.finishRefresh();
            }
        });

        mSmartRefreshLayout.setOnLoadMoreListener(refreshLayout->{
            isRefresh = false;
            currentPage++;
            getLuBoListData();
            refreshLayout.finishLoadMore();


        });
    }

    private void getLuBoListData() {
        Observable<BilibiliVideoBaseModel<LuBoInfo>> observable1 = HttpClient.getApi(ApiService.BILIBILI_SPACE_URL).getLuBoInfo(cid,10,currentPage);
        observable1.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseBilibiliVideoObserver<LuBoInfo>(_mActivity){
                    @Override
                    public void onSuccess(LuBoInfo luBoInfo) {
                        //没得到 展示空页面
                        showLuBoInfoData(luBoInfo,isRefresh);
                       // Log.e(TAG, "onSuccess lubo: "+luBoInfo.vlist.get(0).title );
                    }
                });
    }

    private void showLuBoInfoData(LuBoInfo luBoInfo, boolean isRefresh) {
        if(mAdapter ==null)
            return;
        if(isRefresh){
            mAdapter.replaceData(luBoInfo.vlist);
        }else{
            mAdapter.addData(luBoInfo.vlist);
        }
    }

    //初始化Adapter
    @Override
    protected void initView() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        List<Vlist> mLuboInfoList = new ArrayList<>();
        mAdapter = new LuboListAdapter(R.layout.item_lubo_list,mLuboInfoList);
        //点击事件，子项点击事件
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(_mActivity,"转入视频页",Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_lubolist;
    }
}
