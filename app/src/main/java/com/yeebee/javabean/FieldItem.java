package com.yeebee.javabean;

import java.util.List;

/**
 * Created by 16245 on 2016/09/16.
 */

public class FieldItem {

    /**
     * List : [{"KEYID":81,"KEYNAME":"TMT"},{"KEYID":184,"KEYNAME":"汽车服务"},{"KEYID":61,"KEYNAME":"企业服务"},{"KEYID":62,"KEYNAME":"环保"},{"KEYID":63,"KEYNAME":"大消费"},{"KEYID":85,"KEYNAME":"软件工程"},{"KEYID":181,"KEYNAME":"新能源"},{"KEYID":82,"KEYNAME":"农业"},{"KEYID":86,"KEYNAME":"艺术设计"},{"KEYID":8,"KEYNAME":"O2O"},{"KEYID":9,"KEYNAME":"电子商务"},{"KEYID":10,"KEYNAME":"移动互联网"},{"KEYID":11,"KEYNAME":"信息技术"},{"KEYID":12,"KEYNAME":"游戏娱乐"},{"KEYID":13,"KEYNAME":"房产酒店"},{"KEYID":14,"KEYNAME":"餐饮服务"},{"KEYID":16,"KEYNAME":"教育金融"},{"KEYID":17,"KEYNAME":"社交服务"},{"KEYID":101,"KEYNAME":"健康医疗"},{"KEYID":45,"KEYNAME":"其他"},{"KEYID":46,"KEYNAME":"智能硬件"},{"KEYID":47,"KEYNAME":"互联网+"},{"KEYID":48,"KEYNAME":"高端制造"},{"KEYID":49,"KEYNAME":"云计算"},{"KEYID":50,"KEYNAME":"大数据"},{"KEYID":51,"KEYNAME":"物联网"},{"KEYID":121,"KEYNAME":"文化旅游"}]
     * Recode : 1
     * Remsg : 成功
     */

    private int Recode;
    private String Remsg;
    /**
     * KEYID : 81
     * KEYNAME : TMT
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
