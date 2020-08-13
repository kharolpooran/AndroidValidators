package com.appic.androidvalidators;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.appic.androidvalidators.interfaces.ErrorCallBack;
import com.appic.androidvalidators.model.CheckBoxValidator;
import com.appic.androidvalidators.model.Email;
import com.appic.androidvalidators.model.Password;
import com.appic.androidvalidators.model.PhoneNumber;
import com.appic.androidvalidators.model.SpinnerValidator;
import com.appic.androidvalidators.model.Username;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;

import static com.appic.androidvalidators.model.PhoneNumber.EMPTY;
import static com.appic.androidvalidators.model.PhoneNumber.IS_REQUIRED;
import static com.appic.androidvalidators.model.PhoneNumber.MAX_LENGTH;
import static com.appic.androidvalidators.model.PhoneNumber.SUCCESS;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ErrorCallBack {

    Spinner spinner;
    String[] country = {"Mr", "Mrs"};
    EditText username, email, number, userpassword, confpassword;
    Button btn;
    HashMap<String, Boolean> phoneNumber;
    HashMap<String, Boolean> spinnerHash;
    HashMap<String, Boolean> user;
    LinearLayout LLMain;
    CheckBox checkbox;
    HashMap<String, Boolean> emailValidation;
    HashMap<String, Boolean> passwordValidation;
    HashMap<String, Boolean> checkBoxValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LLMain = findViewById(R.id.LLMain);
        spinner = findViewById(R.id.spinner);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        number = findViewById(R.id.number);
        userpassword = findViewById(R.id.userpassword);
        confpassword = findViewById(R.id.confpassword);
        btn = findViewById(R.id.btn);
        checkbox = findViewById(R.id.checkbox);
        spinner.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);


        spinnerHash = new SpinnerValidator.SpinnerBuilder(spinner)
                .setRequired(true)
                .build();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                spinnerHash = new SpinnerValidator.SpinnerBuilder(spinner)
                        .setRequired(true)
                        .build();

                passwordValidation = new Password.PasswordBuilder(userpassword.getText().toString())
                        .setMinValue(6)
                        .setMaxValue(8)
                        .setNumbersOnly(true)
                        .setConfirmPassword(confpassword.getText().toString())
                        .comparePassword(true)
                        .usePreDefinePattern(true)/*If Both number only and pre define is true then it will use pre define pattern*/
                        .build();

                user = new Username.UsernameBuilder(username.getText().toString())
                        .setRequired(true)
                        .setCaseSensitive(true)
                        .build();

                emailValidation = new Email.EmailBuilder(email.getText().toString())
                        .setRequired(true)
                        .build();

                phoneNumber = new PhoneNumber.PhoneNumberBuilder(number.getText().toString())
                        .setRequired(true)
                        .setMinLength(10)
                        .setMaxLength(10)
                        .build();

                checkBoxValidation = new CheckBoxValidator.CheckBoxBuilder(checkbox)
                        .setRequired(true)
                        .build();
                SpinnerValidator validator = new SpinnerValidator(MainActivity.this);
                Username username = new Username(MainActivity.this);
                Email email = new Email(MainActivity.this);
                PhoneNumber number = new PhoneNumber(MainActivity.this);
                Password password = new Password(MainActivity.this);
                CheckBoxValidator checkBox = new CheckBoxValidator(MainActivity.this);
                if (validator.isValid(spinnerHash) && username.isValid(user) && email.isValid(emailValidation)
                        && number.isValid(phoneNumber) && password.isValid(passwordValidation)
                        && checkBox.isValid(checkBoxValidation)) {
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    private void showSnackBar(String msg) {
        Snackbar snackbar = Snackbar
                .make(LLMain, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.e("Msg", (String) spinner.getSelectedItem());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onError(String s) {
        showSnackBar(s);
    }
}