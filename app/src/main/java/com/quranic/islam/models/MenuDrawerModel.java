package com.quranic.islam.models;

import com.quranic.islam.base.BaseModel;

public class MenuDrawerModel extends BaseModel {
    private String title;
    private int logo;

    public MenuDrawerModel(String title, int logo) {
        this.title = title;
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
