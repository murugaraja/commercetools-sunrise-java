package com.commercetools.sunrise.models.products;

import com.commercetools.sunrise.core.viewmodels.ViewModel;

public class ProductVariantReferenceViewModel extends ViewModel {

    private int id;
    private String url;

    public ProductVariantReferenceViewModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }
}