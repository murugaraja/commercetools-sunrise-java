package demo.myaccount;

import com.commercetools.sunrise.common.cache.NoCache;
import com.commercetools.sunrise.common.reverserouter.myaccount.AddressBookReverseRouter;
import com.commercetools.sunrise.common.reverserouter.myaccount.AuthenticationReverseRouter;
import com.commercetools.sunrise.common.template.engine.TemplateRenderer;
import com.commercetools.sunrise.hooks.RegisteredComponents;
import com.commercetools.sunrise.myaccount.CustomerFinder;
import com.commercetools.sunrise.myaccount.addressbook.AddressFinder;
import com.commercetools.sunrise.myaccount.addressbook.addresslist.view.AddressBookPageContentFactory;
import com.commercetools.sunrise.myaccount.addressbook.removeaddress.DefaultRemoveAddressFormData;
import com.commercetools.sunrise.myaccount.addressbook.removeaddress.RemoveAddressExecutor;
import com.commercetools.sunrise.myaccount.addressbook.removeaddress.SunriseRemoveAddressController;
import com.commercetools.sunrise.common.CommonControllerComponentSupplier;
import demo.PageHeaderControllerComponentSupplier;
import io.sphere.sdk.customers.Customer;
import play.data.FormFactory;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

@NoCache
@RegisteredComponents({
        CommonControllerComponentSupplier.class,
        PageHeaderControllerComponentSupplier.class
})
public final class RemoveAddressController extends SunriseRemoveAddressController<DefaultRemoveAddressFormData> {

    private final AuthenticationReverseRouter authenticationReverseRouter;
    private final AddressBookReverseRouter addressBookReverseRouter;

    @Inject
    public RemoveAddressController(final TemplateRenderer templateRenderer,
                                   final FormFactory formFactory,
                                   final CustomerFinder customerFinder,
                                   final AddressFinder addressFinder,
                                   final RemoveAddressExecutor removeAddressExecutor,
                                   final AddressBookPageContentFactory addressBookPageContentFactory,
                                   final AuthenticationReverseRouter authenticationReverseRouter,
                                   final AddressBookReverseRouter addressBookReverseRouter) {
        super(templateRenderer, formFactory, customerFinder, addressFinder, removeAddressExecutor, addressBookPageContentFactory);
        this.authenticationReverseRouter = authenticationReverseRouter;
        this.addressBookReverseRouter = addressBookReverseRouter;
    }

    @Override
    public String getTemplateName() {
        return "my-account-address-book";
    }

    @Override
    public Class<DefaultRemoveAddressFormData> getFormDataClass() {
        return DefaultRemoveAddressFormData.class;
    }

    @Override
    public CompletionStage<Result> handleNotFoundCustomer() {
        return redirectTo(authenticationReverseRouter.showLogInForm());
    }

    @Override
    public CompletionStage<Result> handleNotFoundAddress() {
        return redirectTo(addressBookReverseRouter.addressBookCall());
    }

    @Override
    public CompletionStage<Result> handleSuccessfulAction(final Customer updatedCustomer, final DefaultRemoveAddressFormData formData) {
        return redirectTo(addressBookReverseRouter.addressBookCall());
    }
}
