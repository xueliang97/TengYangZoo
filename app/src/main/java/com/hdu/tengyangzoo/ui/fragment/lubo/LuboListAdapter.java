package com.hdu.tengyangzoo.ui.fragment.lubo;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hdu.tengyangzoo.R;
import com.hdu.tengyangzoo.model.LuBoInfo;
import com.hdu.tengyangzoo.model.Vlist;
import com.hdu.tengyangzoo.util.Utils;

import java.util.List;

import androidx.annotation.Nullable;

public class LuboListAdapter extends BaseQuickAdapter<Vlist, BaseViewHolder> {

    public LuboListAdapter(int layoutResId, @Nullable List<Vlist> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Vlist item) {
        helper.setText(R.id.item_lubo_list_title,item.title)
                .setText(R.id.item_lubo_list_danmaku,Utils.convertDanmaku(item.review))
                .setText(R.id.item_lubo_list_play,Utils.convertPlay(item.play))
                .setText(R.id.item_lubo_list_posttime, Utils.convertTime(item.created));
    }
}
