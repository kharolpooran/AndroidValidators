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

                password = new Password.PasswordBuilder(userpassword.getText().toString())
                        .setMinValue(6)
                        .setMaxValue(8)
                        .setSpecialCharacterRequired(true)
                        .setNumbersOnly(false)
                        .setRequired(true)
                        .setConfirmPassword(confpassword.getText().toString())
                        .comparePassword(true)
                        .setLowerCase(false)
                        .setUpperCase(false)
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


                if (user.get(Username.SUCCESS) && password.get(Password.SUCCESS) && emailValidation.get(Email.SUCCESS) && phoneNumber.get(PhoneNumber.SUCCESS) && spinnerHash.get(SpinnerValidator.SUCCESS) && checkBoxValidation.get(CheckBoxValidator.SUCCESS)) {
                    showSnackBar("Success");
                } else {
                    if (user.get(Username.SUCCESS)) {
                        if (password.get(Password.SUCCESS)) {
                            if (emailValidation.get(Email.SUCCESS)) {
                                if (phoneNumber.get(PhoneNumber.SUCCESS)) {
                                    if (spinnerHash.get(SpinnerValidator.SUCCESS)) {
                                        if (!checkBoxValidation.get(CheckBoxValidator.SUCCESS)) {
                                            if (checkBoxValidation.get(CheckBoxValidator.IS_REQUIRED)) {
                                                if (!checkBoxValidation.get(CheckBoxValidator.NULL)) {
                                                    showSnackBar("Select any one item in Checkbox.");
                                                }
                                            } else {
                                                showSnackBar("CheckBox is required.");
                                            }
                                        }
                                    } else {
                                        if (spinnerHash.get(SpinnerValidator.IS_REQUIRED)) {
                                            if (!spinnerHash.get(SpinnerValidator.NULL)) {
                                                showSnackBar("Select any one item in Spinner.");
                                            }
                                        } else {
                                            showSnackBar("Spinner is required.");
                                        }
                                    }
                                } else {
                                    if (phoneNumber.get(PhoneNumber.IS_REQUIRED)) {
                                        if (!phoneNumber.get(Email.EMPTY)) {
                                            if (!phoneNumber.get(PhoneNumber.MAX_LENGTH) && !phoneNumber.get(PhoneNumber.MIN_LENGTH)) {
                                                showSnackBar("number must be between length");
                                            }
                                        } else {
                                            showSnackBar("number is should not be empty");
                                        }

                                    } else {
                                        showSnackBar("number is required");
                                    }
                                }
                            } else {
                                //if (emailValidation.get(Email.IS_REQUIRED)) {
                                //if (!emailValidation.get(Email.EMPTY)) {
                                if (!emailValidation.get(Email.IS_EMAIL)) {
                                    showSnackBar("incorrect email address");
                                }
                                //} else {
                                //   showSnackBar("email is should not be empty");
                                //}
                                // } else {
                                //   showSnackBar("email is required");

                                // }
                            }
                        } else {
                            /*if (password.get(Password.IS_REQUIRED)) {
                                if (!password.get(Password.EMPTY)) {
                                    if (password.get(Password.RANGE)) {
                                        if (password.get(Password.NUMBER)) {
                                            if (password.get(Password.SPECIAL_CHARACTER)) {
                                                if (password.get(Password.UPPER_CASE)) {
                                                    if (password.get(Password.LOWER_CASE)) {
                                                        if (password.get(Password.CONFIRM_PASSWORD)) {
                                                            if (!password.get(Password.MATCH_PASSWORD)) {
                                                                showSnackBar("Password and Confirm Password doesn't match.");
                                                            }
                                                        } else {
                                                            showSnackBar("Confirm Password Missing !");
                                                        }
                                                    } else {
                                                        showSnackBar("Password should contain lower case !");
                                                    }
                                                } else {
                                                    showSnackBar("Password should contain upper case !");
                                                }
                                            } else {
                                                showSnackBar("Password Should contatin special character !");
                                            }
                                        } else {
                                            showSnackBar("Password should contain number");
                                        }
                                    } else {
                                        showSnackBar("Password Length Is Incorrect");
                                    }
                                } else {
                                    showSnackBar("Password is should not be empty");
                                }
                            }
                            else {
                                showSnackBar("Password is required");
                            }*/


                            /*if (password.get(Password.IS_REQUIRED)) {
                                if (!password.get(Password.EMPTY)) {
                                    if (password.get(Password.RANGE)) {
                                        if (password.get(Password.NUMBER_REQUIRED)) {
                                            if (!password.get(Password.NUMBER)) {
                                                showSnackBar("Password should contain number");
                                            } else {
                                                if (password.get(Password.SPECIAL_CHARACTER_REQUIRED)) {
                                                    if (!password.get(Password.SPECIAL_CHARACTER)) {
                                                        showSnackBar("Password Should contain special character !");
                                                    } else {
                                                        if (password.get(Password.UPPER_CASE_REQUIRED)) {
                                                            if (!password.get(Password.UPPER_CASE)) {
                                                                showSnackBar("Password should contain upper case !");
                                                            } else {
                                                                if (password.get(Password.LOWER_CASE_REQUIRED)) {
                                                                    if (!password.get(Password.LOWER_CASE)) {
                                                                        showSnackBar("Password should contain lower case !");
                                                                    } else {
                                                                        if (!password.get(Password.CONFIRM_PASSWORD)) {
                                                                            showSnackBar("Confirm Password Missing !");
                                                                        } else {
                                                                            if (!password.get(Password.MATCH_PASSWORD)) {
                                                                                showSnackBar("Password and Confirm Password doesn't match.");
                                                                            }
                                                                        }
                                                                    }
                                                                } else {
                                                                    showSnackBar("Password should contain lower case !");
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    //Check for other conditions
                                                    showSnackBar("Password Should contain special character !");
                                                }
                                            }
                                        } else {
                                            //Check for other conditions
                                        }
                                    } else {
                                        showSnackBar("Password Length Is Incorrect");
                                    }
                                } else {
                                    showSnackBar("Password is should not be empty");
                                }
                            } else {
                                showSnackBar("Password is required");
                            }*/

                            if (!password.get(Password.LOWER_CASE)) {
                                showSnackBar("LowerCAse");
                            }

                        }
                    } else {
                        /*if (user.get(Username.IS_REQUIRED)) {
                            if (user.get(Username.EMPTY)) {
                                showSnackBar("username is should not be empty");
                            } else {
                                if (user.get(Username.IS_CASE_SENSITIVE)) {
                                    if (!user.get(Username.IS_ALL_LOWER_CASE)) {
                                        showSnackBar("username must be in lower case");
                                    }
                                } else {
                                    showSnackBar("username must be in lower case");
                                }
                            }
                        } else {
                            showSnackBar("username is required");
                        }*/
                        if (!user.get(Username.IS_CASE_SENSITIVE)) {
                            showSnackBar("username must be in lower case");
                        }
                    }
                }


            }
        });
    }

    private boolean isChecked() {


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

        if (phoneNumber.get(SUCCESS)) {
            return true;
        } else {

        }
        return true;
    }

    private boolean isValidPassword() {


        if (password.get(Password.SUCCESS)) {
            return true;
        } else {
            if (password.get(Password.IS_REQUIRED)) {
                //Toast.makeText(MainActivity.this, IS_REQUIRED, Toast.LENGTH_SHORT).show();
                showSnackBar("password is required");
                return false;
            } else {
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