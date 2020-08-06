package com.appic.androidvalidators;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.appic.androidvalidators.model.Email;
import com.appic.androidvalidators.model.Password;
import com.appic.androidvalidators.model.PhoneNumber;
import com.appic.androidvalidators.model.SpinnerValidator;
import com.appic.androidvalidators.model.Username;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        number = findViewById(R.id.number);
        userpassword = findViewById(R.id.userpassword);
        confpassword = findViewById(R.id.confpassword);
        btn = findViewById(R.id.btn);
        spinner.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);

        Email email = new Email.EmailBuilder("poorankharol@gmail.com")
                .setRequired(true)
                .build();

        Username username = new Username.UsernameBuilder("pooran")
                .setRequired(true)
                .setCaseSensitive(true)
                .build();

        phoneNumber = new PhoneNumber.PhoneNumberBuilder("88666")
                .setRequired(true)
                .setMaxLenght(10)
                .build();

        SpinnerValidator validator = new SpinnerValidator.SpinnerBuilder(spinner)
                .setRequired(true)
                .build();

        final Password password = new Password.PasswordBuilder("123456")
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
                if (phoneNumber.get(IS_REQUIRED)) {
                    if (phoneNumber.get(EMPTY)) {
                        if (phoneNumber.get(MAX_LENGTH)) {
                            if (phoneNumber.get(SUCCESS)) {
                                Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, MAX_LENGTH, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, EMPTY, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, IS_REQUIRED, Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.e("Msg", (String) spinner.getSelectedItem());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}