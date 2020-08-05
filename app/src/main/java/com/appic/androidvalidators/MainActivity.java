package com.appic.androidvalidators;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.appic.androidvalidators.model.Email;
import com.appic.androidvalidators.model.Password;
import com.appic.androidvalidators.model.PhoneNumber;
import com.appic.androidvalidators.model.SpinnerValidator;
import com.appic.androidvalidators.model.Username;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    String[] country = {"India", "USA", "China", "Japan", "Other"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner);
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

        PhoneNumber phoneNumber = new PhoneNumber.PhoneNumberBuilder("8866651281")
                .setRequired(true)
                .build();

        SpinnerValidator validator = new SpinnerValidator.SpinnerBuilder(spinner)
                .setRequired(true)
                .build();
        Password password = new Password.PasswordBuilder("123456")
                .setMinValue(6)
                .setMaxValue(16)
                .setSpecialCharacterRequired(true)
                .setNumbersOnly(false)
                .setRequired(true)
                .setConfirmPassword("1234567891011")
                .build();


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.e("Msg", (String) spinner.getSelectedItem());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}