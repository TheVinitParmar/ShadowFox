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

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.button);
        TextView signUpTextView = findViewById(R.id.textView2);
        EditText emailEditText = findViewById(R.id.editTextTextEmailAddress2);
        EditText passwordEditText = findViewById(R.id.editTextTextPassword);

        loginButton.setOnClickListener(v -> {

            if (validateInput()) {
                // Input is valid, proceed with login
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                // Start next activity or perform login logic here
            } else {
                // Error messages are set within validateInput()
                Toast.makeText(MainActivity.this, "Please check your input", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(MainActivity.this, home_page.class);
            startActivity(intent);
        });

        signUpTextView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUpPageActivity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private boolean validateInput() {
        EditText emailEditText = findViewById(R.id.editTextTextEmailAddress2);
        EditText passwordEditText = findViewById(R.id.editTextTextPassword);

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Please enter a valid email");
            return false;
        }

        if (password.isEmpty() || password.length() < 6){
            passwordEditText.setError("Password must be at least 6 characters");
            return false;
        }

        return true;
    }
}