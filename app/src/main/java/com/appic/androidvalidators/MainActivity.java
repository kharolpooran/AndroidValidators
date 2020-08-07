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


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
    HashMap<String, Boolean> password;

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

        emailValidation = new Email.EmailBuilder("poorankharol@gmail.com")
                .setRequired(true)
                .build();


        spinnerHash = new SpinnerValidator.SpinnerBuilder(spinner)
                .setRequired(true)
                .build();


        password = new Password.PasswordBuilder("123456")
                .setMinValue(6)
                .setMaxValue(16)
                .setSpecialCharacterRequired(true)
                .setNumbersOnly(false)
                .setRequired(true)
                .setConfirmPassword("1234567891011")
                .build();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isValidUsername()) {
                    if (isValidEmail()) {
                        if (isValidNumber()) {
                            if (isValidPassword()) {
                                if (isChecked()) {
                                    showSnackBar("Success");
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    private boolean isChecked() {

        HashMap<String, Boolean> user = new CheckBoxValidator.CheckBoxBuilder(checkbox)
                .setRequired(true)
                .build();
        if (user.get(CheckBoxValidator.SUCCESS)) {
            return true;
        } else {
            if (user.get(CheckBoxValidator.IS_REQUIRED)) {
                //Toast.makeText(MainActivity.this, IS_REQUIRED, Toast.LENGTH_SHORT).show();
                showSnackBar("please accept");
                return false;
            } else {
                if (user.get(CheckBoxValidator.NULL)) {
                    //Toast.makeText(MainActivity.this, Username.IS_CASE_SENSITIVE, Toast.LENGTH_SHORT).show();
                    showSnackBar("checkbox is null");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidUsername() {
        user = new Username.UsernameBuilder(username.getText().toString())
                .setRequired(true)
                .setCaseSensitive(true)
                .build();
        if (user.get(Username.SUCCESS)) {
            //Toast.makeText(MainActivity.this, SUCCESS, Toast.LENGTH_SHORT).show();
            //showSnackBar("");
            return true;
        } else {
            if (user.get(Username.IS_REQUIRED)) {
                //Toast.makeText(MainActivity.this, IS_REQUIRED, Toast.LENGTH_SHORT).show();
                showSnackBar("username is required");
                return false;
            } else {
                if (user.get(Username.EMPTY)) {
                    //Toast.makeText(MainActivity.this, EMPTY, Toast.LENGTH_SHORT).show();
                    showSnackBar("username is should not be empty");
                    return false;
                } else {
                    if (user.get(Username.IS_CASE_SENSITIVE)) {
                        //Toast.makeText(MainActivity.this, Username.IS_CASE_SENSITIVE, Toast.LENGTH_SHORT).show();
                        showSnackBar("username must be in lower case");
                        return false;
                    } else {
                        if (!user.get(Username.IS_ALL_LOWER_CASE)) {
                            //Toast.makeText(MainActivity.this, Username.IS_ALL_LOWER_CASE, Toast.LENGTH_SHORT).show();
                            showSnackBar("username must be in lower case");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean isValidEmail() {

        emailValidation = new Email.EmailBuilder(email.getText().toString())
                .setRequired(true)
                .build();
        if (emailValidation.get(Email.SUCCESS)) {
            return true;
        } else {
            if (emailValidation.get(Email.IS_REQUIRED)) {
                //Toast.makeText(MainActivity.this, IS_REQUIRED, Toast.LENGTH_SHORT).show();
                showSnackBar("email is required");
                return false;
            } else {
                if (emailValidation.get(Email.EMPTY)) {
                    //Toast.makeText(MainActivity.this, EMPTY, Toast.LENGTH_SHORT).show();
                    showSnackBar("email is should not be empty");
                    return false;
                } else {
                    if (!emailValidation.get(Email.IS_EMAIL)) {
                        //Toast.makeText(MainActivity.this, MAX_LENGTH, Toast.LENGTH_SHORT).show();
                        showSnackBar("incorrect email address");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isValidNumber() {
        phoneNumber = new PhoneNumber.PhoneNumberBuilder(number.getText().toString())
                .setRequired(true)
                .setMaxLenght(10)
                .build();

        if (phoneNumber.get(SUCCESS)) {
            return true;
        } else {
            if (phoneNumber.get(IS_REQUIRED)) {
                //Toast.makeText(MainActivity.this, IS_REQUIRED, Toast.LENGTH_SHORT).show();
                showSnackBar("number is required");
                return false;
            } else {
                if (phoneNumber.get(EMPTY)) {
                    //Toast.makeText(MainActivity.this, EMPTY, Toast.LENGTH_SHORT).show();
                    showSnackBar("number is should not be empty");
                    return false;
                } else {
                    if (!phoneNumber.get(MAX_LENGTH)) {
                        //Toast.makeText(MainActivity.this, MAX_LENGTH, Toast.LENGTH_SHORT).show();
                        showSnackBar("incorrect number");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isValidPassword() {
        password = new Password.PasswordBuilder(userpassword.getText().toString())
                .setMinValue(6)
                .setMaxValue(16)
                .setSpecialCharacterRequired(true)
                .setNumbersOnly(false)
                .setRequired(true)
                .setConfirmPassword(confpassword.getText().toString())
                .build();

        if (password.get(Password.SUCCESS)) {
            return true;
        }
        else {
            if (password.get(Password.IS_REQUIRED)) {
                //Toast.makeText(MainActivity.this, IS_REQUIRED, Toast.LENGTH_SHORT).show();
                showSnackBar("password is required");
                return false;
            }
            else {
                if (password.get(Password.EMPTY)) {
                    //Toast.makeText(MainActivity.this, EMPTY, Toast.LENGTH_SHORT).show();
                    showSnackBar("password is should not be empty");
                    return false;
                }
            }
        }

        return true;
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
}