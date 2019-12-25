package com.example.bankpay_v1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

/**
 * Displays all the accounts in the accounts home page, where you can deposit and withdraw and check balances
 */
public class start_app_display_accounts extends AppCompatActivity {

    AccountDatabase db;
    ListView listView;
    Button back;
    Button withdraw;


    ArrayList<BankAccount> listItem;
    ArrayAdapter adapter;

    //BankAccount bankAccount;
    //ListAdapter listAdapter;

    EditText amount_to_change;
    EditText id_to_change;
    Button deposit;
    Button to_edit_accounts;

    String spacing = "                   ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_app_display_accounts);

        db = new AccountDatabase(this);
        listView = findViewById(R.id.start_display);
        listItem = new ArrayList<>();
        viewData();

        //goes back to the home page
        back = findViewById(R.id.display_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(start_app_display_accounts.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //goes to the edit accounts page
        to_edit_accounts = findViewById(R.id.to_edit_acnt);
        to_edit_accounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(start_app_display_accounts.this, display_accounts_home.class);
                startActivity(intent);
            }
        });

        //bank id that you want to change the amount of
        amount_to_change = findViewById(R.id.amount_change);
        id_to_change = findViewById(R.id.id_change);

        /*
            Deposit button which deposits entered amount into entered bank account id and updates the database
         */
        deposit = findViewById(R.id.deposit);
        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (amount_to_change.getText().toString().matches("") || id_to_change.getText().toString().matches("")){
                    Toast.makeText(start_app_display_accounts.this, "Enter an ID and Amount", Toast.LENGTH_LONG).show();
                    return;
                }

                double amc_double = Double.parseDouble(amount_to_change.getText().toString());
                int id_ident = Integer.parseInt(id_to_change.getText().toString());

                boolean b = false;

                for (BankAccount bank : listItem){
                    if (bank.getId() == id_ident){
                        double x = bank.getBalance();
                        b = db.updateDataDeposit(id_to_change.getText().toString(), amc_double, x);
                    }
                }

                if (b == true){
                    Toast.makeText(start_app_display_accounts.this, spacing + "(SUCCESS) \n Bank Account data was updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(start_app_display_accounts.this, spacing + "(ERROR) Invalid ID \n Bank Account data was NOT UPDATED", Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(start_app_display_accounts.this, checkID.class);
                startActivity(intent);
            }
        });

         /*
            Withdraw button which withdraws entered amount from entered bank account id and updates the database
         */
        withdraw = findViewById(R.id.withdraw);
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (amount_to_change.getText().toString().matches("") || id_to_change.getText().toString().matches("")){
                    Toast.makeText(start_app_display_accounts.this, "Enter an ID and Amount", Toast.LENGTH_LONG).show();
                    return;
                }

                double amc_double = Double.parseDouble(amount_to_change.getText().toString());
                int id_ident = Integer.parseInt(id_to_change.getText().toString());

                boolean b = false;

                for (BankAccount bank : listItem){
                    if (bank.getId() == id_ident){
                        double x = bank.getBalance();
                        b = db.updateDataWithdrawal(id_to_change.getText().toString(), amc_double, x);
                    }
                }

                if (b == true){
                    Toast.makeText(start_app_display_accounts.this, spacing + "(SUCCESS) \n Bank Account data was updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(start_app_display_accounts.this, spacing + "(ERROR) Invalid ID \n Bank Account data was NOT UPDATED", Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(start_app_display_accounts.this, checkID.class);
                startActivity(intent);
            }
        });


        /*
            Sets up a fragmented activity when clicking on individual items in the listview
            Also passes data through Bundles
         */
        final FragmentManager manager = getSupportFragmentManager();
        final DisplayFragment fragment = new DisplayFragment();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction t = manager.beginTransaction();
                Bundle b1 = new Bundle();
                b1.putString("name", listView.getItemAtPosition(position).toString());
                t.add(R.id.frag_frame, fragment);
                t.commit();
                fragment.setArguments(b1);
            }
        });

    }

    //adds the data from the database to an array which presents all the bank account information using an adapter
    public void viewData(){

        Cursor cursor = db.getListContents();
        while (cursor.moveToNext()){
            String id_ = cursor.getString(0);
            String name = cursor.getString(1);
            double balance = cursor.getDouble(2);
            int id = Integer.parseInt(id_);
            listItem.add(new BankAccount(name,id,balance));
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItem);
        listView.setAdapter(adapter);
    }




}
