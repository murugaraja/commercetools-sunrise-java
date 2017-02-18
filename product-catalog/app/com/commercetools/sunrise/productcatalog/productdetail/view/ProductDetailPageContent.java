package com.commercetools.sunrise.productcatalog.productdetail.view;

import com.commercetools.sunrise.common.pages.PageContent;
import com.commercetools.sunrise.productcatalog.common.BreadcrumbBean;
import com.commercetools.sunrise.productcatalog.common.ProductBean;
import com.commercetools.sunrise.productcatalog.common.SuggestionsBean;

public class ProductDetailPageContent extends PageContent {

    private BreadcrumbBean breadcrumb;
    private ProductBean product;
    private SuggestionsBean suggestions;

    public ProductDetailPageContent() {
    }

    public BreadcrumbBean getBreadcrumb() {
        return breadcrumb;
    }

    public void setBreadcrumb(final BreadcrumbBean breadcrumb) {
        this.breadcrumb = breadcrumb;
    }

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(final ProductBean product) {
        this.product = product;
    }

    public SuggestionsBean getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(final SuggestionsBean suggestions) {
        this.suggestions = suggestions;
    }
}