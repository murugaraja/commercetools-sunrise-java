package com.commercetools.sunrise.framework.components;

import java.util.Collections;
import java.util.List;

public interface ControllerComponent extends SunriseComponent, ControllerComponentsSupplier {

    @Override
    default List<ControllerComponent> get() {
        return Collections.singletonList(this);
    }
}