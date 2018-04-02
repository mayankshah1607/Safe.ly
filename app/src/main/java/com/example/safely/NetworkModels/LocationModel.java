package com.example.safely.NetworkModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by luci4 on 31/3/18.
 */

public class LocationModel {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("geolocation")
    @Expose
    private String geolocation;

    @SerializedName("cost")
    @Expose
    private Number cost;

    @SerializedName("risk")
    @Expose
    private String risk;

    @SerializedName("keyword")
    @Expose
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @SerializedName("user")
    @Expose
    private UserModel user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }

    public Number getCost() {
        return cost;
    }

    public void setCost(Number cost) {
        this.cost = cost;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
