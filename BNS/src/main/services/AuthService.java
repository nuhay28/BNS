package main.services;

public class AuthService {
    // Mocking the backend call
    public boolean login(String email, String password) throws Exception {
        if (email.isEmpty() || password.isEmpty()) throw new Exception("Fields cannot be empty");
        // Logic to check DB would go here
        return true; 
    }

    public boolean register(String name, String email, String password, String phone, String role) throws Exception {
        // Logic to save user to DB
        return true;
    }
}