package com.dayrayaneh.automation.model.pishKhanModel;

import android.graphics.drawable.Drawable;

public class PishKhanModel {

    String title;
    int icon;
    int id;

    public PishKhanModel(String title, int icon , int id) {
        this.title = title;
        this.icon = icon;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public int getIcon() {
        return icon;
    }

    public int getId() {
        return id;
    }
}
