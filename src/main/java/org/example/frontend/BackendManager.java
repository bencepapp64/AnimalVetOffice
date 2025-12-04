package org.example.frontend;

import org.example.backend.model.Owner;

import java.util.List;

public interface BackendManager {
    public void start();
    public void stop();
    public void test();
    public void saveOwner(String name, String phone, String email);
    public List<Owner> getOwners();
    public void deleteOwnerById(Long id);
}
