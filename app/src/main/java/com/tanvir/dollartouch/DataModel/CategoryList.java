package com.tanvir.dollartouch.DataModel;

import java.util.List;

public class CategoryList {
    private List<Category> categorys=null;

    public CategoryList(List<Category> categorys) {
        this.categorys = categorys;
    }

    public List<Category> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<Category> categorys) {
        this.categorys = categorys;
    }
}
