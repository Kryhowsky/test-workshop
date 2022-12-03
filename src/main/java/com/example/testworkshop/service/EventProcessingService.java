package com.example.testworkshop.service;

import com.example.testworkshop.exception.IntegrationDataNotFoundException;
import com.example.testworkshop.model.IntegrationData;
import com.example.testworkshop.storage.IntegrationDataStore;
import org.springframework.stereotype.Service;

@Service
public class EventProcessingService {

    private IntegrationDataStore dataStore;
    private OpenBrokerService brokerService;

    public EventProcessingService(IntegrationDataStore dataStore, OpenBrokerService brokerService) {

        this.dataStore = dataStore;
        this.brokerService = brokerService;
    }

    public void processApplication(String id) {

        IntegrationData integrationData = dataStore.getById(id);
        if (integrationData == null) {
            String message = String.format("Application with id %s not found", id);
            brokerService.message(id, message);
            throw new IntegrationDataNotFoundException();
        }

        if (integrationData.isActive()) {
            brokerService.rejection(id);
            dataStore.makeInactive(integrationData);
            IntegrationData newData = new IntegrationData();
            integrationData.setId(integrationData.getId() + 1);
            integrationData.setName("new name");
            integrationData.setEmail(" new email");
            integrationData.setActive(true);
            dataStore.store(newData);
        } else {
            String name = integrationData.getName();
            integrationData.setName(name + "stub");
            brokerService.accept(id);
            dataStore.store(integrationData);
        }
    }
}
