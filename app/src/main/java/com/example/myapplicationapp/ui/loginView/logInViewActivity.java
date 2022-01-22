package com.example.myapplicationapp.ui.loginView;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationapp.MainActivity;
import com.example.myapplicationapp.R;
import com.example.myapplicationapp.ui.utilities.validations;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class logInViewActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextInputLayout email_input_app, password_input_app;
    private ProgressDialog mDialog;
    private validations mValidations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_view);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mDialog = new ProgressDialog(this);
        mValidations = new validations(this);
        mAuth = FirebaseAuth.getInstance();

        email_input_app = findViewById(R.id.email_input_app);
        password_input_app = findViewById(R.id.password_input_app);

        TextView check_in_id = findViewById(R.id.check_in_id);
        check_in_id.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), logUpViewActivity.class)));

        Button log_in_id = findViewById(R.id.log_in_id);
        log_in_id.setOnClickListener(v -> logIn());
    }

    private void logIn() {


        if (!mValidations.validateEmailPassword(email_input_app) | !mValidations.validateEmailPassword(password_input_app)) {
            return;
        }
        mDialog.setMessage("Ingresando...");
        mDialog.setCancelable(false);
        mDialog.show();
        String email = Objects.requireNonNull(email_input_app.getEditText()).getText().toString().trim();
        String password = Objects.requireNonNull(password_input_app.getEditText()).getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        mDialog.dismiss();
                        mainActivity();
                    } else {
                        mDialog.dismiss();
                        inv_login();
                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            mainActivity();
        }
    }

    private void mainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void inv_login() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Error");
        builder.setMessage("Correo o contraseña invalido")
                .setPositiveButton(getString(R.string.confirm), (dialog, which) -> dialog.dismiss());
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.parseColor("#FF000000"));

    }
}