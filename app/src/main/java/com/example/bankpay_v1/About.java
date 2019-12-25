package com.example.bankpay_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The About class which gives some information about the app
 */

public class About extends AppCompatActivity {

    Button back;
    TextView about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        //Goes back to the HOME page
        back = findViewById(R.id.abt_back_home);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(About.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //about this app
        about = findViewById(R.id.abt_text);
        about.setText("This is a simple app designed to keep track of all your bank accounts. \n \n " +
                "It utilizes a basic SQL Database to keep track of all the accounts. \n \n" +
                "You can edit the accounts, which are stored in a list adapter and check the Id for each account. \n \n" +
                "You can deposit or withdraw money to any account, though this feature is implemented at a basic level. \n \n" +
                "This app also utlizes fragmented activites when you click on each account.");

    }

}
