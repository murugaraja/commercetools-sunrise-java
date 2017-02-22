package com.commercetools.sunrise.myaccount.mydetails.viewmodels;

import com.commercetools.sunrise.common.models.FormPageContentFactory;
import com.commercetools.sunrise.common.models.customers.CustomerInfoViewModelFactory;
import com.commercetools.sunrise.common.utils.PageTitleResolver;
import com.commercetools.sunrise.myaccount.mydetails.MyPersonalDetailsFormData;
import io.sphere.sdk.customers.Customer;
import play.data.Form;

import javax.inject.Inject;

public class MyPersonalDetailsPageContentFactory extends FormPageContentFactory<MyPersonalDetailsPageContent, Customer, MyPersonalDetailsFormData> {

    private final PageTitleResolver pageTitleResolver;
    private final CustomerInfoViewModelFactory customerInfoViewModelFactory;
    private final MyPersonalDetailsFormSettingsViewModelFactory myPersonalDetailsFormSettingsViewModelFactory;

    @Inject
    public MyPersonalDetailsPageContentFactory(final PageTitleResolver pageTitleResolver, final CustomerInfoViewModelFactory customerInfoViewModelFactory,
                                               final MyPersonalDetailsFormSettingsViewModelFactory myPersonalDetailsFormSettingsViewModelFactory) {
        this.pageTitleResolver = pageTitleResolver;
        this.customerInfoViewModelFactory = customerInfoViewModelFactory;
        this.myPersonalDetailsFormSettingsViewModelFactory = myPersonalDetailsFormSettingsViewModelFactory;
    }

    @Override
    protected MyPersonalDetailsPageContent getViewModelInstance() {
        return new MyPersonalDetailsPageContent();
    }

    @Override
    public final MyPersonalDetailsPageContent create(final Customer customer, final Form<? extends MyPersonalDetailsFormData> form) {
        return super.create(customer, form);
    }

    @Override
    protected void initialize(final MyPersonalDetailsPageContent model, final Customer data, final Form<? extends MyPersonalDetailsFormData> form) {
        super.initialize(model, data, form);
        fillCustomer(model, data, form);
        fillPersonalDetailsForm(model, data, form);
        fillPersonalDetailsFormSettings(model, data, form);
    }

    @Override
    protected void fillTitle(final MyPersonalDetailsPageContent model, final Customer customer, final Form<? extends MyPersonalDetailsFormData> form) {
        model.setTitle(pageTitleResolver.getOrEmpty("myAccount:myPersonalDetailsPage.title"));
    }

    protected void fillCustomer(final MyPersonalDetailsPageContent model, final Customer customer, final Form<? extends MyPersonalDetailsFormData> form) {
        model.setCustomerInfo(customerInfoViewModelFactory.create(customer));
    }

    protected void fillPersonalDetailsForm(final MyPersonalDetailsPageContent model, final Customer customer, final Form<? extends MyPersonalDetailsFormData> form) {
        model.setPersonalDetailsForm(form);
    }

    protected void fillPersonalDetailsFormSettings(final MyPersonalDetailsPageContent model, final Customer customer, final Form<? extends MyPersonalDetailsFormData> form) {
        model.setPersonalDetailsFormSettings(myPersonalDetailsFormSettingsViewModelFactory.create(form));
    }
}