package com.jsit.guildplace2.activities.auth.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.jsit.guildplace2.R;
import com.jsit.guildplace2.activities.auth.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_register);

        Button registerButton = findViewById(R.id.registerButton);
        TextInputLayout emailInput = findViewById(R.id.usernameRegisterInput);
        TextInputLayout passwordInput = findViewById(R.id.passwordRegisterInput);

        Button loginRedirectButton = findViewById(R.id.loginRedirectButton);

        loginRedirectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(view.getContext(), LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getEditText().getText().toString();
                String password = passwordInput.getEditText().getText().toString();

                if (!checkFieldAndSetErrorIfEmpty(emailInput) ||
                        !checkFieldAndSetErrorIfEmpty(passwordInput)) {
                    return;
                }

                RegisterController registerController = new RegisterController(view.getContext());
                registerController.sendRequest(new RegisterRequest(email, password));
            }
        });
    }
}