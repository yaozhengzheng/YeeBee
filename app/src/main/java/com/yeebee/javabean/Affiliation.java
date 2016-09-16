package com.yeebee.javabean;

import java.util.List;

/**
 * Created by 16245 on 2016/09/16.
 */

public class Affiliation {

    /**
     * List : [{"KEYID":0,"KEYNAME":"其他"},{"KEYID":106,"KEYNAME":"IA资本"},{"KEYID":2,"KEYNAME":"IDG"},{"KEYID":84,"KEYNAME":"安芙兰资本"},{"KEYID":353,"KEYNAME":"安信证券"},{"KEYID":76,"KEYNAME":"北大创业众筹"},{"KEYID":147,"KEYNAME":"北京创梦创业投资有限公司"},{"KEYID":66,"KEYNAME":"北京创业谷科技孵化器有限公司"},{"KEYID":62,"KEYNAME":"北京恩科美达投资管理有限公司"},{"KEYID":142,"KEYNAME":"北京金三科技股份有限公司"},{"KEYID":83,"KEYNAME":"北京经开投资开发股份有限公司"},{"KEYID":80,"KEYNAME":"北京朗玛峰创业投资管理有限公司"},{"KEYID":102,"KEYNAME":"北京朗玛峰创业投资管理有限公司"},{"KEYID":141,"KEYNAME":"北京朗玛峰创业投资中心"},{"KEYID":203,"KEYNAME":"北京明峰资本管理有限公司"},{"KEYID":365,"KEYNAME":"北京清科同润科技投资有限公司"},{"KEYID":355,"KEYNAME":"北京瑞峰时代投资公司"},{"KEYID":221,"KEYNAME":"北京天和融汇投资基金"},{"KEYID":42,"KEYNAME":"北京投融盈智投资咨询有限公司"},{"KEYID":67,"KEYNAME":"北京五板投资管理有限公司"},{"KEYID":64,"KEYNAME":"北京新美互通科技有限公司"},{"KEYID":73,"KEYNAME":"北京洋葱投资管理有限公司"},{"KEYID":78,"KEYNAME":"北京元亨创融科技有限公司"},{"KEYID":79,"KEYNAME":"北京元迅投资基金管理中心"},{"KEYID":266,"KEYNAME":"北京智德盛投资有限公司"},{"KEYID":424,"KEYNAME":"贝壳菁汇"},{"KEYID":43,"KEYNAME":"伯乐遇马天使投资有限公司"},{"KEYID":343,"KEYNAME":"成都紫东迎泰股权投资基金管理中心"},{"KEYID":269,"KEYNAME":"传迈国际传媒"},{"KEYID":74,"KEYNAME":"创客100基金"},{"KEYID":46,"KEYNAME":"创客总部"},{"KEYID":77,"KEYNAME":"大河套（北京）投资有限公司"},{"KEYID":105,"KEYNAME":"大江投资"},{"KEYID":344,"KEYNAME":"大唐电信"},{"KEYID":14,"KEYNAME":"鼎晖资本"},{"KEYID":267,"KEYNAME":"东旭集团"},{"KEYID":1,"KEYNAME":"独立个人"},{"KEYID":268,"KEYNAME":"恩美投资"},{"KEYID":281,"KEYNAME":"方信资本"},{"KEYID":223,"KEYNAME":"奋斗栏目组"},{"KEYID":242,"KEYNAME":"逢时资本"},{"KEYID":348,"KEYNAME":"高能资本"},{"KEYID":6,"KEYNAME":"高盛亚洲"},{"KEYID":81,"KEYNAME":"观执投资"},{"KEYID":161,"KEYNAME":"光合空间 孵化器"},{"KEYID":202,"KEYNAME":"广州聚人投资有限公司"},{"KEYID":72,"KEYNAME":"国安龙巢"},{"KEYID":349,"KEYNAME":"海航投资"},{"KEYID":362,"KEYNAME":"瀚海资本"},{"KEYID":270,"KEYNAME":"浩然资本"},{"KEYID":354,"KEYNAME":"和君资本"},{"KEYID":341,"KEYNAME":"黑马资本"},{"KEYID":382,"KEYNAME":"恒业基金"},{"KEYID":5,"KEYNAME":"红杉资本"},{"KEYID":264,"KEYNAME":"厚谷投资"},{"KEYID":403,"KEYNAME":"互融资本"},{"KEYID":282,"KEYNAME":"华盛基金"},{"KEYID":346,"KEYNAME":"华夏幸福基业股份"},{"KEYID":17,"KEYNAME":"机构名称"},{"KEYID":426,"KEYNAME":"坚果创投"},{"KEYID":222,"KEYNAME":"坚果创投"},{"KEYID":11,"KEYNAME":"今日资本"},{"KEYID":265,"KEYNAME":"金桐网投资有限公司"},{"KEYID":82,"KEYNAME":"京北众筹"},{"KEYID":262,"KEYNAME":"九合创投"},{"KEYID":122,"KEYNAME":"九派创业投资有限公司"},{"KEYID":241,"KEYNAME":"九泰基金管理有限公司"},{"KEYID":101,"KEYNAME":"九洲创投园"},{"KEYID":4,"KEYNAME":"凯雷投资集团"},{"KEYID":283,"KEYNAME":"凯旋投资"},{"KEYID":10,"KEYNAME":"兰馨亚洲投资集团"},{"KEYID":345,"KEYNAME":"蓝帆医疗股份"},{"KEYID":163,"KEYNAME":"乐创企业家俱乐部"},{"KEYID":145,"KEYNAME":"乐科资本"},{"KEYID":301,"KEYNAME":"雷锋资本"},{"KEYID":103,"KEYNAME":"丽睿客创投加速器"},{"KEYID":384,"KEYNAME":"丽睿客加速器"},{"KEYID":13,"KEYNAME":"联想投资有限公司"},{"KEYID":261,"KEYNAME":"联想之星"},{"KEYID":321,"KEYNAME":"联讯证券"},{"KEYID":21,"KEYNAME":"绿洲资本"},{"KEYID":143,"KEYNAME":"马力创业投资有限公司"},{"KEYID":8,"KEYNAME":"美国华平投资集团"},{"KEYID":7,"KEYNAME":"摩根士丹利"},{"KEYID":342,"KEYNAME":"你好未来投资管理有限公司"},{"KEYID":224,"KEYNAME":"牛投网"},{"KEYID":61,"KEYNAME":"纽信创投"},{"KEYID":271,"KEYNAME":"欧美同学基金会"},{"KEYID":383,"KEYNAME":"葡萄创投"},{"KEYID":360,"KEYNAME":"其乐投资有限公司"},{"KEYID":164,"KEYNAME":"乾黎资本"},{"KEYID":22,"KEYNAME":"乾元资本"},{"KEYID":244,"KEYNAME":"青创伯乐"},{"KEYID":181,"KEYNAME":"清创空间"},{"KEYID":3,"KEYNAME":"软银中国创业投资"},{"KEYID":363,"KEYNAME":"三和系资本"},{"KEYID":68,"KEYNAME":"上海连力创投"},{"KEYID":201,"KEYNAME":"上海棋盘投资"},{"KEYID":70,"KEYNAME":"深圳创意投资集团"},{"KEYID":9,"KEYNAME":"深圳达晨创业投资"},{"KEYID":442,"KEYNAME":"深圳德本基金管理有限公司"},{"KEYID":441,"KEYNAME":"数码视讯"},{"KEYID":205,"KEYNAME":"松禾资本"},{"KEYID":322,"KEYNAME":"太平洋证券股份有限公司股份转让业务部"},{"KEYID":75,"KEYNAME":"天和金融"},{"KEYID":359,"KEYNAME":"天津万实投资担保有限公司"},{"KEYID":63,"KEYNAME":"天星资本"},{"KEYID":284,"KEYNAME":"同渡资本"},{"KEYID":104,"KEYNAME":"万众金服"},{"KEYID":162,"KEYNAME":"葳尔资本"},{"KEYID":461,"KEYNAME":"沃田资产"},{"KEYID":423,"KEYNAME":"梧桐树资本"},{"KEYID":45,"KEYNAME":"小树技术创投"},{"KEYID":144,"KEYNAME":"新生代创投"},{"KEYID":121,"KEYNAME":"星河互联"},{"KEYID":71,"KEYNAME":"星火金融"},{"KEYID":69,"KEYNAME":"星库空间（北京）创业投资有限公司"},{"KEYID":351,"KEYNAME":"亿润投资"},{"KEYID":356,"KEYNAME":"引航创投"},{"KEYID":364,"KEYNAME":"盈合资本"},{"KEYID":361,"KEYNAME":"盈泰财富云"},{"KEYID":421,"KEYNAME":"优利瑞投"},{"KEYID":12,"KEYNAME":"浙江浙商创业投资"},{"KEYID":285,"KEYNAME":"浙商创投"},{"KEYID":422,"KEYNAME":"知投资本"},{"KEYID":41,"KEYNAME":"直通新三板"},{"KEYID":357,"KEYNAME":"中产宏博"},{"KEYID":65,"KEYNAME":"中创快孵"},{"KEYID":381,"KEYNAME":"中国投资商会"},{"KEYID":347,"KEYNAME":"中国文化传媒集团"},{"KEYID":85,"KEYNAME":"中國投资人中心"},{"KEYID":401,"KEYNAME":"中海软银投资管理有限公司"},{"KEYID":350,"KEYNAME":"中航航空"},{"KEYID":204,"KEYNAME":"中金智远（北京）有限责任公司"},{"KEYID":443,"KEYNAME":"中科招商云投汇"},{"KEYID":263,"KEYNAME":"中融大有商业保理"},{"KEYID":146,"KEYNAME":"\u201c众创互联\u201d协同创新云孵化平台"},{"KEYID":182,"KEYNAME":"众合资本"},{"KEYID":225,"KEYNAME":"卓富资本"},{"KEYID":358,"KEYNAME":"卓瑞金控集团"}]
     * Recode : 1
     * Remsg : 成功
     */

    private int Recode;
    private String Remsg;
    /**
     * KEYID : 0
     * KEYNAME : 其他
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
