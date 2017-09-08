package com.kkmvp.kkmvp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by KK00120638 on 9/6/2017.
 */

public class RestResponse {
    public List<String> messages;

    public List<obj> getResult() {
        return result;
    }

    public void setResult(List<obj> result) {
        this.result = result;
    }

    public List<obj> result;
}
