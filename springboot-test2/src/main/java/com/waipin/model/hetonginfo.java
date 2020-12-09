package com.waipin.model;

public class hetonginfo {
    private String keshidanjia;
    private String zongxueshi;
    private String kechengmingcheng;
    private String renkebanji;
    private String kechengbianhao;
    private Integer size;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "hetonginfo{" +
                "keshidanjia='" + keshidanjia + '\'' +
                ", zongxueshi='" + zongxueshi + '\'' +
                ", kechengmingcheng='" + kechengmingcheng + '\'' +
                ", renkebanji='" + renkebanji + '\'' +
                ", kechengbianhao='" + kechengbianhao + '\'' +
                ", size=" + size +
                '}';
    }

    public String getKeshidanjia() {
        return keshidanjia;
    }

    public void setKeshidanjia(String keshidanjia) {
        this.keshidanjia = keshidanjia;
    }

    public String getZongxueshi() {
        return zongxueshi;
    }

    public void setZongxueshi(String zongxueshi) {
        this.zongxueshi = zongxueshi;
    }

    public String getKechengmingcheng() {
        return kechengmingcheng;
    }

    public void setKechengmingcheng(String kechengmingcheng) {
        this.kechengmingcheng = kechengmingcheng;
    }

    public String getRenkebanji() {
        return renkebanji;
    }

    public void setRenkebanji(String renkebanji) {
        this.renkebanji = renkebanji;
    }

    public String getKechengbianhao() {
        return kechengbianhao;
    }

    public void setKechengbianhao(String kechengbianhao) {
        this.kechengbianhao = kechengbianhao;
    }
}
