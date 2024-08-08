package com.example.authentication_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpPageActivity extends AppCompatActivity {

    private SignUpPageActivity binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sign_up_page);

        Button signUpButton = findViewById(R.id.button2);
        EditText emailInput = findViewById(R.id.Gmail);
        EditText passwordInput = findViewById(R.id.phone); // Make sure this IDis correct for the password field
        EditText usernameInput = findViewById(R.id.username);
        EditText addressInput = findViewById(R.id.Address);
        EditText phoneInput = findViewById(R.id.phone); // Make sure this ID is correct and unique
        TextView loginLink = findViewById(R.id.logintag);

        signUpButton.setOnClickListener(v -> handleSignUpClick(emailInput, passwordInput, usernameInput, addressInput, phoneInput));
        loginLink.setOnClickListener(v -> handleLoginLinkClick());

        setupEdgeToEdgeDisplay();
    }

    public void handleSignUpClick(EditText emailInput, EditText passwordInput, EditText usernameInput, EditText addressInput, EditText phoneInput) {
        if (validateInput(emailInput, passwordInput, usernameInput, addressInput, phoneInput)) {
            // Input is valid, proceed with sign-up
            Intent intent = new Intent(SignUpPageActivity.this, home_page.class);
            startActivity(intent);
        } else {
            // Error messages are set within validateInput()
            Toast.makeText(this, "Please check your input", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleLoginLinkClick() {
        Intent intent = new Intent(SignUpPageActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private boolean validateInput(EditText emailInput, EditText passwordInput, EditText usernameInput, EditText addressInput, EditText phoneInput) {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String username = usernameInput.getText().toString();
        String address = addressInput.getText().toString();
        String phone = phoneInput.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("Please enter a valid email");
            return false;
        }

        if (password.isEmpty() || password.length() < 6) {
            passwordInput.setError("Password must be at least 6 characters");
            return false;
        }

        if (username.isEmpty()) {
            usernameInput.setError("Please enter a username");
            return false;
        }

        // Add validation for address and phone number as needed

        return true;
    }

    private void setupEdgeToEdgeDisplay() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}