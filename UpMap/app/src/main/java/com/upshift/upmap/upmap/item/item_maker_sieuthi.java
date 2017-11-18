package com.upshift.upmap.upmap.item;

import java.util.ArrayList;

/**
 * Created by PC on 11/16/2017.
 */

public class item_maker_sieuthi {
    private double lat;
    private double lon;
    private String name;
    private String add;
    private String url ;
    private Integer service;
    private String Content;
    private String phone;
    private ArrayList<item_cmt> l_cmt = new ArrayList<>();



    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public Integer getService() {
        return service;
    }

    public void setService(Integer service) {
        this.service = service;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<item_cmt> getL_cmt() {
        return l_cmt;
    }

    public void setL_cmt(ArrayList<item_cmt> l_cmt) {
        this.l_cmt = l_cmt;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
