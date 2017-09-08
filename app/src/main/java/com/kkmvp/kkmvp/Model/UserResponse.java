package com.kkmvp.kkmvp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KK00120638 on 9/8/2017.
 */

public class UserResponse {
    @SerializedName("userId")

    private String userId;
    @SerializedName("id")

    private int id;
    @SerializedName("title")

    private String title;
    @SerializedName("body")

    private String body;


    public String getUserId() {
        return userId + "";
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
