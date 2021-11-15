package com.dayrayaneh.automation.model.pishKhanModel;

import android.graphics.drawable.Drawable;

public class PishKhanModel {

    String title;
    int icon;

    public PishKhanModel(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public int getIcon() {
        return icon;
    }
}
