package com.backbase.accesscontrol.routes.flows;

import com.backbase.accesscontrol.business.datagroup.strategy.WorkerFactory;
import com.backbase.accesscontrol.business.flows.datagroup.CreateDataGroupFlow;
import com.backbase.accesscontrol.business.persistence.datagroup.AddDataGroupHandler;
import com.backbase.accesscontrol.business.service.AgreementsPersistenceService;
import com.backbase.accesscontrol.configuration.ValidationConfig;
import com.backbase.accesscontrol.service.impl.PersistenceServiceAgreementService;
import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import com.backbase.presentation.accessgroup.event.spec.v1.DataGroupBase;
import com.backbase.presentation.accessgroup.rest.spec.v2.accessgroups.datagroups.DataGroupsPostResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class ExternalCreateDataGroupFlow extends CreateDataGroupFlow {

    private static final Logger LOGGER = LoggerFactory
        .getLogger(ExternalCreateDataGroupFlow.class);


    @Override
    protected void externalValidation(DataGroupBase dataGroupBase) {

        // you can add additional logic here, for example calling external endpoints for validation
        LOGGER.info("======== In external validation example method ========");
    }

    @Override
    protected void preHook(DataGroupBase data) {
        ///you can add validation here in preHook
        LOGGER.info("======== In pre hook example method ========");
        if (data.getItems().isEmpty()) {
            LOGGER.warn("======== In pre hook Bad request Data group with empty items list: preHook ========");
            throw new BadRequestException("Must contain data group items");
        }
    }

    @Override
    protected DataGroupsPostResponseBody postHook(DataGroupBase data, DataGroupsPostResponseBody result) {
        //modification on additional properties goes here in the postHook and return should be
        //response body.
        LOGGER.info("======== In post hook example method ========");

        LOGGER.info("Data group ID before modification: {}", result.getId());
        String modifiedResponse = result.getId().toUpperCase();

        LOGGER.info("Data group id after modification: {}", modifiedResponse);
        LOGGER.info("======== Exiting post hook example method ========");
        return new DataGroupsPostResponseBody().withId(modifiedResponse);
    }

    public ExternalCreateDataGroupFlow(
        WorkerFactory workerFactory,
        ValidationConfig validationConfig,
        AgreementsPersistenceService agreementsPersistenceService,
        PersistenceServiceAgreementService persistenceServiceAgreementService,
        AddDataGroupHandler addDataGroupHandler) {
        super(workerFactory, validationConfig, agreementsPersistenceService, persistenceServiceAgreementService,
            addDataGroupHandler);

    }
}
