package com.project.myodp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TableResponse {

    @SerializedName("no")
    @Expose
    private Integer no;
    @SerializedName("odp_name")
    @Expose
    private String odpName;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getOdpName() {
        return odpName;
    }

    public void setOdpName(String odpName) {
        this.odpName = odpName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
