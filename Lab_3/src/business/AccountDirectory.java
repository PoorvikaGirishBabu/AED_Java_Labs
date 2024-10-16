/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.util.ArrayList;


/**
 *
 * @author poorv
 */
public class AccountDirectory {
   private ArrayList<Account> accountlist;

    public AccountDirectory() {
        this.accountlist = new ArrayList<Account>();
    }
   
   
    public ArrayList<Account> getAccountlist() {
        return accountlist;
    }

    public void setAccountlist(ArrayList<Account> accountlist) {
        this.accountlist = accountlist;
    }
   
    public Account addaccount(){
        Account account = new Account();
        accountlist.add(account);
        return account;
    } 
    
    public void deleteaccount(Account account)
    {
        accountlist.remove(account);
        
    }
    
    public Account SearchAccount(String accountNumber){
        for(Account account: accountlist){
            if(account.getAccountNumber().equals(accountNumber)){
                return account;
                
            }
        }
        return null;
    }
    
}
