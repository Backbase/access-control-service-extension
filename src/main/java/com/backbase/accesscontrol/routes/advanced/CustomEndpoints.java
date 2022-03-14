package com.backbase.accesscontrol.routes.advanced;

import com.backbase.accesscontrol.dto.DataItemsValidatable;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;
import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import org.apache.camel.Body;
import org.apache.camel.Consume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * The custom camel Endpoints to be used in the behavior extension.
 */
@Component
public class CustomEndpoints {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomEndpoints.class);
    public static final String CUSTOM_PRE_HOOK_END_POINT = "direct:accessgroup.datagroup.add";

    @Consume(CUSTOM_PRE_HOOK_END_POINT)
    public void validatePostDataGroupBody(@Body InternalRequest<DataItemsValidatable> request) {
        LOGGER.info("======== In pre hook example method ========");
        DataItemsValidatable requestData = request.getData();
        if (requestData.getItems().isEmpty()) {
            LOGGER.warn("======== In pre hook Bad request Data group with empty items list: preHook ========");
            throw new BadRequestException()
                    .withMessage("Cannot have data group without items.");
        }
    }

}
