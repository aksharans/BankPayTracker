package com.example.bankpay_v1;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The CheckId class which displays all the bank accounts and their ID's so the user can add and delete them
 * from the edit accounts page
 */

public class checkID extends AppCompatActivity {

    AccountDatabase db;
    String all_accounts = "";
    TextView list_accounts;
    Button back;
    Button to_accounts_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkid);


        db = new AccountDatabase(this);

        list_accounts = findViewById(R.id.listnames);

        //Cursor moves through the database to display all the accounts in the textview
        Cursor cursor = db.getListContents();
        while (cursor.moveToNext()){

            String r = cursor.getString(0);
            String s = cursor.getString(1);
            double balance = cursor.getDouble(2);
            all_accounts+= "(ID: " + r + ")" + " Name: '" + s + "' Balance: " + balance+ " \n" + "\n";

        }
        list_accounts.setText(all_accounts + " ");

        //Goes back to the Edit Accounts page
        back = findViewById(R.id.id_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(checkID.this, display_accounts_home.class);
                startActivity(intent);
            }
        });

        //Goes to the Main Accounts Display page
        to_accounts_home = findViewById(R.id.display_back);
        to_accounts_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(checkID.this, start_app_display_accounts.class);
                startActivity(intent);
            }
        });

    }
}
