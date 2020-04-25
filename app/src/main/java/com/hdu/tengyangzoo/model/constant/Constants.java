package com.hdu.tengyangzoo.model.constant;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    /**
     * fragment 对应的常量
     */
    public static final int TYPE_LUBO = 0;
    public static final int TYPE_DONGTAI = 1;
    public static final int TYPE_MINE = 2;

    public static final String CURRENT_FRAGMENT_KEY = "current_fragment";

    public static final String LUBO_CID = "lubo_cid";

    //UpID
    //看桑，小风，歌手,魔男,P元帅
    public static final int KanSang = 223236400;
    public static final int XiaoFeng = 180166708;
    public static final int GeShou = 17678573;
    public static final int MoNan = 172917055;
    public static final int PYuanShuai = 109629667;
    public static final int[] UpId= {KanSang,XiaoFeng,GeShou,MoNan,PYuanShuai};
    public static final String[] UpName = {"就看看桑","海绵宝宝小风儿","不是歌手","火焰鼠的带魔王","P元帅丶"};
    public static Map<Integer,String> uidName = new HashMap<>();

    public static Map<Integer,String> getUidName(){
        if(uidName.size()==0){
            for(int i=0;i<UpId.length;i++)
                uidName.put(UpId[i],UpName[i]);
        }
        return uidName;
    }
}
