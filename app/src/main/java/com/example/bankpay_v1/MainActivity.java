package com.example.bankpay_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * The Main Activity class which is the HOME page for the app
 */

public class MainActivity extends AppCompatActivity {

    Button start_btn;
    Button edit_start;
    TextView about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Goes to the Main Accounts Display page
        start_btn = findViewById(R.id.start_btn);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, start_app_display_accounts.class);
                startActivity(intent);
            }
        });

        //Goes to the Edit Accounts Page
        edit_start = findViewById(R.id.edit_start_btn);
        edit_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, display_accounts_home.class);
                startActivity(intent);
            }
        });

        //Goes to the About page
        about = findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, About.class);
                startActivity(intent);
            }
        });


    }
}
