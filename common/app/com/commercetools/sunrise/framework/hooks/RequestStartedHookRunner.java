package com.commercetools.sunrise.framework.hooks;

import com.commercetools.sunrise.framework.hooks.events.RequestStartedHook;
import play.inject.Injector;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

/**
 * Action that runs the {@link com.commercetools.sunrise.framework.hooks.events.RequestStartedHook} for the annotated request.
 */
final class RequestStartedHookRunner extends Action<RunRequestStartedHook> {

    private final Injector injector;

    @Inject
    RequestStartedHookRunner(final Injector injector) {
        this.injector = injector;
    }

    @Override
    public CompletionStage<Result> call(final Http.Context ctx) {
        // On creation of this action there isn't any HTTP context, necessary to initialize the HookRunner
        final HookRunner hookRunner = injector.instanceOf(HookRunner.class);
        RequestStartedHook.runHook(hookRunner, ctx);
        return delegate.call(ctx);
    }
}
