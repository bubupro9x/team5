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
    private ArrayList<item_img> img = new ArrayList<>();
    private Integer service;
    private String Content;
    private String phone;
    private ArrayList<item_cmt> l_cmt = new ArrayList<>();

    public item_maker_sieuthi(){}
    public item_maker_sieuthi(double _lat, double _lon, String _name, String _add){
        lat = _lat;
        lon=_lon;
        name=_name;
        add=_add;
    }
    public item_maker_sieuthi(double _lat, double _lon, String _name, String _add, ArrayList<item_cmt> _l){
        lat = _lat;
        lon=_lon;
        name=_name;
        add=_add;
        l_cmt = _l;
    }
    public item_maker_sieuthi(String _c, Double _lat, ArrayList<item_img> _img,  ArrayList<item_cmt> _l, Double _lon,
                              String _n, String _p, Integer _se  ){
        img = _img;
        lat = _lat;
        lon=_lon;
        name=_n;
        l_cmt = _l;
        Content = _c;
        phone = _p;
        service = _se;
    }
    public item_maker_sieuthi(String _c, Double _lat, ArrayList<item_img> _img,  ArrayList<item_cmt> _l, Double _lon,
                              String _n, String _p, Integer _se, String _add  ){
        setImg(_img);
        lat = _lat;
        lon=_lon;
        name=_n;
        l_cmt = _l;
        Content = _c;
        add= _add;
        phone = _p;
        service = _se;
    }


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

    public ArrayList<item_img> getImg() {
        return img;
    }

    public void setImg(ArrayList<item_img> img) {
        this.img = img;
    }
}
