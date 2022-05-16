package com.jsit.guildplace2.activities.auth.register;

public class RegisterRequest {
    private String email;
    private String password;
    private String role;

    public RegisterRequest(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = "user";
    }
}
