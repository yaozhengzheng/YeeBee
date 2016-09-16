package com.yeebee.javabean;

import java.util.List;

/**
 * Created by 16245 on 2016/09/14.
 */

public class Stage {

    /**
     * List : [{"KEYID":221,"KEYNAME":"钟子期"},{"KEYID":222,"KEYNAME":"A轮"}]
     * Recode : 1
     * Remsg : 成功
     */

    private int Recode;
    private String Remsg;
    /**
     * KEYID : 221
     * KEYNAME : 钟子期
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
