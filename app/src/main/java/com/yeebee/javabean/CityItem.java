package com.yeebee.javabean;

import java.util.List;

/**
 * Created by 16245 on 2016/09/15.
 */

public class CityItem {


    /**
     * List : [{"KEYID":1,"KEYNAME":"北京市"},{"KEYID":73,"KEYNAME":"上海市"},{"KEYID":74,"KEYNAME":"南京市"},{"KEYID":87,"KEYNAME":"杭州市"},{"KEYID":170,"KEYNAME":"武汉市"},{"KEYID":198,"KEYNAME":"广州市"},{"KEYID":200,"KEYNAME":"深圳市"},{"KEYID":237,"KEYNAME":"成都市"}]
     * Recode : 1
     * Remsg : 成功
     */

    private int Recode;
    private String Remsg;
    /**
     * KEYID : 1
     * KEYNAME : 北京市
     */

    private java.util.List<ListBean> List;

    public int getRecode() {
        return Recode;
    }

    public void setRecode(int Recode) {
        this.Recode = Recode;
    }

    public String getRemsg() {
        return Remsg;
    }

    public void setRemsg(String Remsg) {
        this.Remsg = Remsg;
    }

    public List<ListBean> getList() {
        return List;
    }

    public void setList(List<ListBean> List) {
        this.List = List;
    }

    public static class ListBean {
        private int KEYID;
        private String KEYNAME;

        public int getKEYID() {
            return KEYID;
        }

        public void setKEYID(int KEYID) {
            this.KEYID = KEYID;
        }

        public String getKEYNAME() {
            return KEYNAME;
        }

        public void setKEYNAME(String KEYNAME) {
            this.KEYNAME = KEYNAME;
        }
    }
}
