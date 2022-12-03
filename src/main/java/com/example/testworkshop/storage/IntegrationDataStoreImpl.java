package com.example.testworkshop.storage;

import com.example.testworkshop.model.IntegrationData;
import org.springframework.stereotype.Component;

@Component
public class IntegrationDataStoreImpl implements IntegrationDataStore {

    private IntegrationDataRepository repository;

    public IntegrationDataStoreImpl (IntegrationDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public void store(IntegrationData integrationData) {
        repository.save(integrationData);
    }

    @Override
    public IntegrationData getById(String id) {

        if (id == null) {
            return null;
        }

        return repository.findById(id);
    }

    @Override
    public IntegrationData getByName(String name) {

        if (name == null) {
            return null;
        }

        return repository.findByName(name);
    }

    @Override
    public IntegrationData getByEmail(String email) {

        if (email == null) {
            return null;
        }

        return repository.findByEmail(email);
    }

    @Override
    public IntegrationData makeInactive(IntegrationData integrationData) {

        if (integrationData == null) {
            return null;
        }

        integrationData.setActive(false);
        repository.save(integrationData);
        return integrationData;
    }
}
