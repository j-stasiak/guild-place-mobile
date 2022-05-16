package com.jsit.guildplace2.activities.auth.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.jsit.guildplace2.R;
import com.jsit.guildplace2.activities.auth.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private boolean checkFieldAndSetErrorIfEmpty(TextInputLayout inputLayout) {
        if(inputLayout.getEditText().getText().toString().isEmpty()) {
            inputLayout.setError("Field is required!");
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.loginButton);
        TextInputLayout usernameInput = findViewById(R.id.usernameInput);
        TextInputLayout passwordInput = findViewById(R.id.passwordInput);
        Button registerRedirectButton = findViewById(R.id.registerRedirectButton);

        registerRedirectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(view.getContext(), RegisterActivity.class);
                startActivity(loginIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = usernameInput.getEditText().getText().toString();
                String password = passwordInput.getEditText().getText().toString();

                if (!checkFieldAndSetErrorIfEmpty(usernameInput) ||
                        !checkFieldAndSetErrorIfEmpty(passwordInput)) {
                    return;
                }

                LoginController loginController = new LoginController(view.getContext());
                loginController.sendRequest(new LoginRequest(email, password));
            }
        });
    }
}