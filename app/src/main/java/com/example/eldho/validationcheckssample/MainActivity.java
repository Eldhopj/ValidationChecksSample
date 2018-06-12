package com.example.eldho.validationcheckssample;
/**
 * Add dependency
 * colors for hint and error texts
 * styles for hint text and error text
 * xml code
 * java coding
 * */

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout nameTIL,emailTIL,password1TIL,password2TIL;
    String name,emailID,password1,password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTIL = findViewById(R.id.name);
        emailTIL = findViewById(R.id.email);
        password1TIL = findViewById(R.id.password1);
        password2TIL = findViewById(R.id.password2);

        nameTIL.getEditText().addTextChangedListener(watch);
        emailTIL.getEditText().addTextChangedListener(watch);
        password1TIL.getEditText().addTextChangedListener(watch);
        password2TIL.getEditText().addTextChangedListener(watch);
    }

    private final TextWatcher watch = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.d("beforeTextChanged:", "");
// removes the error message when starts typing
            nameTIL.setError("");
            emailTIL.setError("");
            password1TIL.setError("");
            password2TIL.setError("");

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private boolean validateName() {
        name = nameTIL.getEditText().getText().toString().trim();
        if (name.isEmpty()) {
            nameTIL.setError("Field can't be empty");
            return false;
        } else {
            nameTIL.setError(null);
            return true;
        }
    }
    private boolean validateEmail() {
        emailID = emailTIL.getEditText().getText().toString().trim();
        if (!(Patterns.EMAIL_ADDRESS.matcher(emailID).matches())) {
            emailTIL.setError("Enter a valid Email Address");
            return false;
        } else {
            emailTIL.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        password1 = password1TIL.getEditText().getText().toString().trim();
        password2 = password2TIL.getEditText().getText().toString().trim();
        if (password1.isEmpty()){
            password1TIL.setError("Field Cant be Empty");
            return false;
        }
        else if (password1.length() < 6){
            password1TIL.setError("Minimum 6 characters needed");
            return false;
        }
        else if (!Objects.equals(password1, password2))           // checking both passwords are similar
        {
            password2TIL.setError("Password Mismatches");
            return false;
        }
        else {
            password1TIL.setError(null);
            password2TIL.setError(null);
            return true;
        }

    }

    public void submit(View view) {
        if (!(validateEmail() & validateName() & validatePassword())){
            return;
        }
        else {
            Toast.makeText(this, "All Validation checks successful", Toast.LENGTH_SHORT).show();
        }
    }
}
