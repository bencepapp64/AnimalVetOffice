package org.example.frontend;

public interface BackendManager {
    public void start();
    public void stop();
    public void test();
    public void saveOwner(String name, String phone, String email);
}
