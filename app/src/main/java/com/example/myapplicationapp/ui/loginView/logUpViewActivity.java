package com.example.myapplicationapp.ui.loginView;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.example.myapplicationapp.R;
import com.example.myapplicationapp.ui.dispensador.ModelDispensador;
import com.example.myapplicationapp.ui.models.userModel;
import com.example.myapplicationapp.ui.utilities.validations;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class logUpViewActivity extends AppCompatActivity {

    private TextInputLayout name_input_app, email_input_app, password_input_app, password_validate_input_app;
    private validations mValidations;
    private FirebaseAuth mAuth;
    private DatabaseReference mReference;
    private DatabaseReference mReferenceDis;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_up_view);

        mDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        mReference = FirebaseDatabase.getInstance().getReference("users");
        mReferenceDis = FirebaseDatabase.getInstance().getReference("dispensador");

        mValidations = new validations(this);

        name_input_app = findViewById(R.id.name_input_app);
        email_input_app = findViewById(R.id.email_input_app);
        password_input_app = findViewById(R.id.password_input_app);
        password_validate_input_app = findViewById(R.id.password_validate_input_app);


        Button registration_id = findViewById(R.id.registration_id);
        registration_id.setOnClickListener(v -> Registration());

    }

    private void Registration() {
        if (!mValidations.validateEmailPassword(name_input_app) | !mValidations.validateEmailPassword(email_input_app)
                | !mValidations.validateEmailPassword(password_input_app) | !mValidations.validateEmailPassword(password_validate_input_app)) {
            return;
        }
        if (!mValidations.validateEmail(email_input_app)) {
            return;
        }

        if (!mValidations.validate_passwords(password_input_app, password_validate_input_app)) {
            return;
        }

        String email = Objects.requireNonNull(email_input_app.getEditText()).getText().toString().trim();
        String password = Objects.requireNonNull(password_input_app.getEditText()).getText().toString().trim();
        String fullNa = Objects.requireNonNull(name_input_app.getEditText()).getText().toString();

        mDialog.setMessage("Registrando...");
        mDialog.setCancelable(false);
        mDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        assert user != null;
                        user.getUid();

                        userModel userModel = new userModel();
                        userModel.setFullName(fullNa);

                        mReference.child(user.getUid()).setValue(userModel).addOnSuccessListener(unused -> {

                            ModelDispensador dispensador = new ModelDispensador();
                            dispensador.setHorario1("0");
                            dispensador.setHorario2("0");
                            dispensador.setHorario3("0");
                            dispensador.setHorario4("0");
                            dispensador.setCantidadAgua("0");
                            dispensador.setCantidadComida("0");
                            dispensador.setAutomatico("1");
                            dispensador.setProgramable("0");


                            mReferenceDis.child(user.getUid()).push().setValue(dispensador).addOnSuccessListener(unused1 -> {
                                mDialog.dismiss();
                                Intent intent = new Intent(getApplicationContext(), logInViewActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            });

                        });
                    } else {
                        mDialog.dismiss();
                        Mail_registered();
                    }
                });
    }

    public void Mail_registered() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Error");
        builder.setMessage("Correo ya registrado o la contraseña es muy corta")
                .setPositiveButton(getString(R.string.confirm), (dialog, which) -> dialog.dismiss());
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(Color.parseColor("#FF000000"));

    }
}