package com.appic.androidvalidators;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.appic.androidvalidators.model.Email;
import com.appic.androidvalidators.model.PhoneNumber;
import com.appic.androidvalidators.model.Username;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
    }
}