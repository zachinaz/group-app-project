package com.example.joiintheclub.FrontEnd.Init;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joiintheclub.BackEnd.User;
import com.example.joiintheclub.R;

public class CreateAcount extends Activity {

    private AutoCompleteTextView createFirstName;
    private AutoCompleteTextView createLastName;
    private AutoCompleteTextView createEmail;
    private TextView createPassward;
    private TextView createConfirmPassward;
    private Button createAccountBtn;


    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acount);

        createFirstName = findViewById(R.id.createFirstName);
        createLastName = findViewById(R.id.createLastName);
        createEmail = findViewById(R.id.createEmail);
        createPassward = findViewById(R.id.createPassward);
        createConfirmPassward = findViewById(R.id.createConfirmPassward);
        createAccountBtn = findViewById(R.id.createAccountBtn);


        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempt();
            }
        });
    }

    @SuppressLint("ShowToast")
    private void attempt()
    {

        String fistName = createFirstName.getText().toString();
        String lastName = createLastName.getText().toString();
        String email = createEmail.getText().toString();
        String password = createPassward.getText().toString();
        String confirmPassword = createConfirmPassward.getText().toString();


        if(!password.equals(confirmPassword))
        {
            Toast.makeText(getApplicationContext(),"confirm email",Toast.LENGTH_LONG);
        }
        else if (isEmpty(fistName))
            createFirstName.setError("This filed can not be empty");
        else if (isEmpty(lastName))
            createLastName.setError("This filed can not be empty");
        else if (isEmpty(email))
            createEmail.setError("This filed can not be empty");
        else if (isEmpty(password))
            createPassward.setError("This filed can not be empty");
        else if (isEmpty(confirmPassword))
            createConfirmPassward.setError("This filed can not be empty");
        else
        {
            if(User.createUser(fistName,lastName,email,password)) {
                Toast.makeText(getApplicationContext(), "sucess", Toast.LENGTH_LONG);
                finish();
            }
            else
                Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_LONG);
        }

    }

    private boolean isEmpty(String userInput)
    {
        return userInput.isEmpty();
    }


}
