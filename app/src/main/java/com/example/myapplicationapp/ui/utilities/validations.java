package com.example.myapplicationapp.ui.utilities;

import android.content.Context;
import android.util.Log;

import com.example.myapplicationapp.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class validations {

    private final Context mContext;

    public validations(Context mContext) {
        this.mContext = mContext;
    }

    public boolean validateEmailPassword(TextInputLayout criterion) {

        String data = Objects.requireNonNull(criterion.getEditText()).getText().toString().trim();

        if (data.isEmpty()) {
            criterion.setError(mContext.getString(R.string.val_empty));
            return false;
        } else {
            criterion.setError(null);
            criterion.setErrorEnabled(false);
            return true;
        }
    }

    public boolean validate_passwords(TextInputLayout password1, TextInputLayout password2) {

        String pass1 = Objects.requireNonNull(password1.getEditText()).getText().toString().trim();
        String pass2 = Objects.requireNonNull(password2.getEditText()).getText().toString().trim();

        Log.e("pas1 ", pass1);
        Log.e("pas2 ", pass2);

        if (!pass1.equals(pass2)) {
            password1.setError(mContext.getString(R.string.Passwords_match));
            password2.setError(mContext.getString(R.string.Passwords_match));
            return false;
        } else {
            password1.setError(null);
            password2.setError(null);
            password1.setErrorEnabled(false);
            password2.setErrorEnabled(false);
            return true;
        }
    }

    public boolean validateEmail(TextInputLayout email) {

        String EMAIL = Objects.requireNonNull(email.getEditText()).getText().toString().replace(" ", "").trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (!EMAIL.matches(emailPattern)) {
            email.setError(mContext.getString(R.string.invalid_email));
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }
}
