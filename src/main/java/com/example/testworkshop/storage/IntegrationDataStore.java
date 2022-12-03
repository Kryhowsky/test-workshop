package com.example.testworkshop.storage;

import com.example.testworkshop.model.IntegrationData;

public interface IntegrationDataStore {

    void store(IntegrationData integrationData);
    IntegrationData getById(String id);
    IntegrationData getByName(String name);
    IntegrationData getByEmail(String email);
    IntegrationData makeInactive(IntegrationData integrationData);
}
