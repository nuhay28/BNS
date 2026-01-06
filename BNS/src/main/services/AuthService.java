package main.services;

public class AuthService {
    // Mocking the backend call
  // remove: throws Exception
public boolean login(String email, String password)
  {
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
    throw new IllegalArgumentException("Email and password cannot be null or empty");
}
email = email.trim();
password = password.trim();

        // Logic to check DB would go here
        return true; 
    }
    public boolean register(String name, String email, String password, String phone, String role) throws Exception {
        // Logic to save user to DB
        return true;
    }
}


