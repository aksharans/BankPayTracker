package com.example.bankpay_v1;


/**
 * A Bank Accounts class which stores all the information related to the bank account
 */

public class BankAccount {

    private String name;
    private double balance;
    private int id;

    public BankAccount(){
       this.name = "";
       this.balance = 0;
       this.id = -1;
    }

    public BankAccount(String name, int id){
        this.name = name;
        this.balance = 0;
        this.id = id;
    }

    public BankAccount(String name, int id, double balance){
        this.name = name;
        this.balance = balance;
        this.id = id;
    }


    public double getBalance(){
        return this.balance;
    }

    public int getId(){ return  this.id;}

    public void deposit(double dep){
        this.balance += dep;
    }

    public void withdraw(double wid){
        this.balance -= wid;
    }

    @Override
    public String toString(){
        return "Name: " + this.name + " |  Id: " + this.id + " | Balance: " + this.balance;
    }


}
