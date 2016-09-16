package com.yeebee.javabean;

import java.util.List;

/**
 * Created by 16245 on 2016/09/12.
 */

public class InterviewList {

    /**
     * List : [{"Id":1,"InterviewsType":1,"ProDesc":"中国最具影响力的私人律师服务平台","ProImage":"201651010195.jpg","ProTitle":"解悠律师"}]
     * Recode : 1
     * Remsg :
     */

    private int Recode;
    private String Remsg;
    /**
     * Id : 1
     * InterviewsType : 1
     * ProDesc : 中国最具影响力的私人律师服务平台
     * ProImage : 201651010195.jpg
     * ProTitle : 解悠律师
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
        private int Id;
        private int InterviewsType;
        private String ProDesc;
        private String ProImage;
        private String ProTitle;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getInterviewsType() {
            return InterviewsType;
        }

        public void setInterviewsType(int InterviewsType) {
            this.InterviewsType = InterviewsType;
        }

        public String getProDesc() {
            return ProDesc;
        }

        public void setProDesc(String ProDesc) {
            this.ProDesc = ProDesc;
        }

        public String getProImage() {
            return ProImage;
        }

        public void setProImage(String ProImage) {
            this.ProImage = ProImage;
        }

        public String getProTitle() {
            return ProTitle;
        }

        public void setProTitle(String ProTitle) {
            this.ProTitle = ProTitle;
        }
    }

}
