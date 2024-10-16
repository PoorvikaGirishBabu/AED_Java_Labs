/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.util.Date;

/**
 *
 * @author poorv
 */
public class Account {
    
    private String RoutingNumber;
    private String AccountNumber;
    private String BankName;
    private int balance;
    private Date createdon;

    public Account() {
        
        this.createdon = new Date();
    }
    
    

    public String getRoutingNumber() {
        return RoutingNumber;
    }

    public void setRoutingNumber(String RoutingNumber) {
        this.RoutingNumber = RoutingNumber;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String AccountNumber) {
        this.AccountNumber = AccountNumber;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String BankName) {
        this.BankName = BankName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Date getCreatedon() {
        return createdon;
    }
    
    @Override
    public String toString(){
        return BankName;
    }
   
}
