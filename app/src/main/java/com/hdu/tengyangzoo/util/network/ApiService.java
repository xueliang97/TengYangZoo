package com.hdu.tengyangzoo.util.network;

import com.hdu.tengyangzoo.model.BilibiliVideoBaseModel;
import com.hdu.tengyangzoo.model.LuBoInfo;
import com.hdu.tengyangzoo.model.BilibiliBaseModel;
import com.hdu.tengyangzoo.model.UpInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    String BILIBILI_URL = "https://api.bilibili.com/";//"https://api.kaaass.net/biliapi/user/";
    String BILIBILI_SPACE_URL = "https://space.bilibili.com/";

    /**
     * https://api.kaaass.net/biliapi/user/contribute?id=223236400&page=6
     * https://space.bilibili.com/ajax/member/getSubmitVideos?mid=223236400&pagesize=10&page=1
     *
     * @return
     */
    @GET("ajax/member/getSubmitVideos")
    Observable<BilibiliVideoBaseModel<LuBoInfo>> getLuBoInfo(@Query("mid")int uid, @Query("pagesize") int pagesize, @Query("page") int pageNum);

    /**
     * https://api.kaaass.net/biliapi/user/space?id=92527298
     * https://api.bilibili.com/x/space/acc/info?mid=94898339
     */
    @GET("x/space/acc/info")
    Observable<BilibiliBaseModel<UpInfo>> getUpInfo(@Query("mid") int uid);

}
