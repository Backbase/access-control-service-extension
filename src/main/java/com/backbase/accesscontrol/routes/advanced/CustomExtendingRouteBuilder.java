package com.backbase.accesscontrol.routes.advanced;

import com.backbase.accesscontrol.routes.datagroup.ValidateDataGroupRoute;
import org.apache.camel.model.RouteDefinition;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * An example of how to extend the out-of-the-box RouteBuilder implementation to add custom camel logic before and after
 * the standard behavior.
 */
@Component
@Primary
public class CustomExtendingRouteBuilder extends ValidateDataGroupRoute {


    @Override
    protected void configurePreHook(RouteDefinition rd) throws Exception {
        rd.to(CustomEndpoints.CUSTOM_PRE_HOOK_END_POINT);
    }

}
