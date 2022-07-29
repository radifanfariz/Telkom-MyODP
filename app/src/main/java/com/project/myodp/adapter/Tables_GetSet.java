package com.project.myodp.adapter;

public class Tables_GetSet {
    private String no;
    private String odp_name;
    private String lat;
    private String lng;
    private String link;

    public Tables_GetSet(String no, String odp_name, String lat, String lng) {
        this.no = no;
        this.odp_name = odp_name;
        this.lat = lat;
        this.lng = lng;
        this.link = link;
    }

    public String getNo() {
        return no;
    }

    public String getOdp_name() {
        return odp_name;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

}
