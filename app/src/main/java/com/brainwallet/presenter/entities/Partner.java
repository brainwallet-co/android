package com.brainwallet.presenter.entities;

import com.brainwallet.presenter.fragments.FragmentBuy;

public class Partner {
    private int logo;
    private int title;
    private int details;
    private FragmentBuy.Partner code;

    public Partner(int logo, int title, int details, FragmentBuy.Partner code) {
        this.logo = logo;
        this.title = title;
        this.details = details;
        this.code = code;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getDetails() {
        return details;
    }

    public void setDetails(int details) {
        this.details = details;
    }

    public FragmentBuy.Partner getCode() {
        return code;
    }
}
