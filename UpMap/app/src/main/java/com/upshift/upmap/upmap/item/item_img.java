package com.upshift.upmap.upmap.item;

/**
 * Created by PC on 11/18/2017.
 */

public class item_img {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    item_img(){}
    item_img(String _u){
        url = _u;
    }
}
