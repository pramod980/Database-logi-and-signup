package com.example.loginproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    TextView textView;
    CheckBox checkBox;
    EditText editText,editText1,editText2;
    Button button;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textView=findViewById(R.id.signup);
        checkBox=findViewById(R.id.check);
        editText=findViewById(R.id.editText);
        editText2=findViewById(R.id.editText2);
        button=findViewById(R.id.log);
        editText1=findViewById(R.id.editText1);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    editText1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    editText1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                validate();
                if(validate()) {
                    String emial = editText2.getText().toString();
                    String password = editText.getText().toString();
                    String password1 = editText1.getText().toString();
                    storingData store = new storingData(emial, password, password1);
                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference("user");
                    myRef.child(password).setValue(store);
                    Toast.makeText(Register.this, "Data store successfully", Toast.LENGTH_SHORT).show();
                }

            }

        });



    }



    private boolean validate()
    {
        String Email=editText2.getText().toString();
        String password=editText.getText().toString();
        String password1=editText1.getText().toString();
        String pass="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        String regex="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        if(Email.length()==0)
        {
            editText2.requestFocus();
            editText2.setError("This field is required");
            return false;
        }
        else if(!Email.matches(regex))
        {
            editText2.requestFocus();
            editText2.setError("Enter valid email");
            return false;

        }
        else if(password.isEmpty())
        {
            editText.requestFocus();
            editText.setError("password is required");
            return false;
        }
        else if(password.length()<6)
        {
            editText.requestFocus();
            editText.setError("At lest 6 digit password is required");
            return false;
        }
        else if (!password.matches(pass))
        {
            editText.requestFocus();
            editText.setError("At least one upper,one lower,one digit,one special character is required ");
            return false;
        }

        else if(!password.matches(password1))
        {
            editText1.requestFocus();
            editText1.setError("Password Does not match");
            return false;

        }
        return true;

    }





}