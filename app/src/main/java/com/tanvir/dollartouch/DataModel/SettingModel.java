package com.tanvir.dollartouch.DataModel;

public class SettingModel {

    String vpn,upgradePriority,privacyPolicy,contactEmail,currency="uk";
    double referBonus,welcomeBonus,clickBonus,versionNo;

    public SettingModel(String vpn, String upgradePriority, String privacyPolicy, String contactEmail, double referBonus, double versionNo, double welcomeBonus, double clickBonus,String currency) {
        this.vpn = vpn;
        this.upgradePriority = upgradePriority;
        this.privacyPolicy = privacyPolicy;
        this.contactEmail = contactEmail;
        this.referBonus = referBonus;
        this.welcomeBonus = welcomeBonus;
        this.clickBonus = clickBonus;
        this.currency=currency;
        this.versionNo=versionNo;
    }

    public String getVpn() {
        return vpn;
    }

    public void setVpn(String vpn) {
        this.vpn = vpn;
    }

    public String getUpgradePriority() {
        return upgradePriority;
    }

    public void setUpgradePriority(String upgradePriority) {
        this.upgradePriority = upgradePriority;
    }

    public String getPrivacyPolicy() {
        return privacyPolicy;
    }

    public void setPrivacyPolicy(String privacyPolicy) {
        this.privacyPolicy = privacyPolicy;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public double getReferBonus() {
        return referBonus;
    }

    public double getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(double versionNo) {
        this.versionNo = versionNo;
    }

    public void setReferBonus(double referBonus) {
        this.referBonus = referBonus;
    }

    public double getWelcomeBonus() {
        return welcomeBonus;
    }

    public void setWelcomeBonus(double welcomeBonus) {
        this.welcomeBonus = welcomeBonus;
    }

    public double getClickBonus() {
        return clickBonus;
    }

    public void setClickBonus(double clickBonus) {
        this.clickBonus = clickBonus;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
