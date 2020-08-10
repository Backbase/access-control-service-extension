package com.backbase.accesscontrol.routes.simple;

import com.backbase.accesscontrol.routes.serviceagreement.AddServiceAgreementRoute;
import com.backbase.buildingblocks.backend.communication.extension.annotations.BehaviorExtension;
import com.backbase.buildingblocks.backend.communication.extension.annotations.PostHook;
import com.backbase.buildingblocks.backend.communication.extension.annotations.PreHook;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import com.backbase.presentation.accessgroup.rest.spec.v2.accessgroups.serviceagreements.ServiceAgreementPostRequestBody;
import com.backbase.presentation.accessgroup.rest.spec.v2.accessgroups.serviceagreements.ServiceAgreementPostResponseBody;
import org.apache.camel.Exchange;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An example of how to provide a behavior extension using annotations.
 */
@BehaviorExtension(
    // The name parameter is used as a configuration key to enable/disable this specific extension.
    // For example, "backbase.behavior-extensions.example-behavior.enabled=false".
    // (Extensions are enabled by default.)
    name = "example-behavior",
    // The routeId parameter is the value returned by the getRouteId() method of the target SimpleExtensibleRouteBuilder
    // and is typically exposed as a constant by that route builder.  E.g.:
    routeId = "AddServiceAgreementRoute"
)
public class ExampleBehaviorExtension extends AddServiceAgreementRoute {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleBehaviorExtension.class);

    @PreHook
    public void examplePreHook(InternalRequest<ServiceAgreementPostRequestBody> body, Exchange exchange) {
        // Custom pre-hook code here.
        // Update the "body" parameter type according to the producer method signature (parameter type) of the route you extend.
        // See the Camel documentation for details about how parameters are bound: http://camel.apache.org/bean-binding.html#BeanBinding-Parameterbinding
        // If no pre-hook behavior is required, this method can be deleted.
        LOGGER.info("======== In pre hook example method ========");
        if (!StringUtils.isAlphanumericSpace(body.getData().getDescription())) {
            LOGGER.warn("======== In pre hook Bad request on AddServiceAgreementRoute ========");
            throw new BadRequestException("Description of Service Agreement cannot contain special characters.");
        }
    }

    @PostHook
    public void examplePostHook(InternalRequest<ServiceAgreementPostResponseBody> responseBody, Exchange exchange) {
        // Custom post-hook code here.
        // Update the "body" parameter type according to the last consumer method signature (return type) of the route you extend.
        // See the Camel documentation for details about how parameters are bound: http://camel.apache.org/bean-binding.html#BeanBinding-Parameterbinding
        // If no post-hook behavior is required, this method can be deleted.
        LOGGER.info("======== In post hook example method ========");
        LOGGER.info("======== Printing response service agreement id: {} ========", responseBody.getData().getId());
    }

}
