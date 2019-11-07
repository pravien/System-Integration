/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodea.bank;

/**
 *
 * @author pravien
 */
public class Bank {
    
   final String name = "Bank Lovro";
   
   public double calculateInterest(int months, double amount){
     
       if (months < 12){
           return 5.00;
       }
       else if (months < 16){
           return 4.5;
       }
       else{
           return 3.5;
       }
   }
    
}
