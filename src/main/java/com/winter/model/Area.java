package com.winter.model;

public class Area {
    private Integer id;

    private String sdd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSdd() {
        return sdd;
    }

    public void setSdd(String sdd) {
        this.sdd = sdd == null ? null : sdd.trim();
    }
}