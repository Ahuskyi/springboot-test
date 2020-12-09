package com.waipin.model;
import	java.util.Date;

public class Xueqi {
    private String date;
    private String text;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Xueqi{" +
                "date='" + date + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
