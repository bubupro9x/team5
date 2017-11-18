package com.upshift.upmap.upmap.item;

/**
 * Created by PC on 11/17/2017.
 */

public class item_cmt {
    private String id;
    private String text;
    item_cmt(){};
    public item_cmt(String _n, String _c){
        id = _n;
        text = _c;
    }
    public String getName() {
        return id;
    }

    public void setName(String name) {
        this.id = name;
    }

    public String getCmt() {
        return text;
    }

    public void setCmt(String cmt) {
        this.text = cmt;
    }
}
