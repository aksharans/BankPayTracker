package com.example.bankpay_v1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * display the accounts, where you can edit them by adding or deleting
 */

public class display_accounts_home extends AppCompatActivity {


   AccountDatabase db = new AccountDatabase(this);
   static ArrayList<String> arr;
   listAdapter listAdapter;
   EditText account_name;
   EditText del_account;

   String spacing = "                   ";
   String spacing2 = "          ";
   String spacing3 = "                           ";

   Button add;
   Button back;
   Button to_accounts_home;
   Button check_id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_accounts_home);

        final ListView listView = findViewById(R.id.list_accounts);
        arr = new ArrayList<>();

        listAdapter = new listAdapter(arr, this);
        listView.setAdapter(listAdapter);


        Cursor cursor = db.getListContents();
        while (cursor.moveToNext()){
            String s = cursor.getString(1);
            arr.add(s);
        }


        // go back to the home page
        back = findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (display_accounts_home.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /*
            Add button which creates an account with the name entered
         */

        account_name = findViewById(R.id.enter_name);

        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameToInsert = account_name.getText().toString().trim();
                if (nameToInsert.matches("")) {
                        Toast.makeText(display_accounts_home.this, "Enter a Bank Account Name to add", Toast.LENGTH_LONG).show();
                        return;
                }

                if (arr.size() >=10) {
                    Toast.makeText(display_accounts_home.this, spacing3 + "ERROR \n Max Number of Accounts Reached (10)", Toast.LENGTH_LONG).show();
                    return;
                }

                account_name.getText().clear();

                arr.add(nameToInsert);
                listAdapter.notifyDataSetChanged();

                boolean inserted = db.insertData(nameToInsert);
                if (inserted == true) {
                    Toast.makeText(display_accounts_home.this, spacing3 + "(SUCCESS) \n Added Bank Account with Name: " + nameToInsert, Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(display_accounts_home.this, checkID.class);
                startActivity(intent);
            }
        });

        /*
            Delete button which deletes the account with the id entered
         */
        del_account = findViewById(R.id.del_name);

        Button del = findViewById(R.id.del_btn);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String i = del_account.getText().toString();

                if (i.matches("")){
                    Toast.makeText(display_accounts_home.this, "Enter an ID to delete", Toast.LENGTH_LONG).show();
                    return;
                }

                int deleted = db.deleteData(i);

                listAdapter.notifyDataSetChanged();

                if (i.equals("-1")){
                    Toast.makeText(display_accounts_home.this, "DELETED ALL ACCOUNTS ", Toast.LENGTH_LONG).show();
                } else if (deleted >= 1){
                    Toast.makeText(display_accounts_home.this, spacing + "(SUCCESS) \n Deleted Bank Account with ID: " + i, Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(display_accounts_home.this, spacing2 + "(ERROR) Invalid ID \n DID NOT delete Bank Account", Toast.LENGTH_LONG).show();
                }

                del_account.getText().clear();

                Intent intent = new Intent(display_accounts_home.this, checkID.class);
                startActivity(intent);

            }
        });


        //go to the display of account balances page
        to_accounts_home = findViewById(R.id.acnt_balances);
        to_accounts_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(display_accounts_home.this, start_app_display_accounts.class);
                startActivity(intent);
            }
        });

        //go to the check Id page
        check_id = findViewById(R.id.check_id);
        check_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(display_accounts_home.this, checkID.class);
                startActivity(intent);
            }
        });


    }


}
