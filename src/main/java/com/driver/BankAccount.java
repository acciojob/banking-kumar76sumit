package com.driver;

import java.util.Random;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public String getName() {
        return name;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public BankAccount(String name, double balance, double minBalance) {
        this.name=name;
        this.balance=balance;
        this.minBalance=minBalance;
    }

    public double getBalance() {
        return balance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        Random random = new Random();
        int currSum=0;
        for(int i=0;i<digits;i++)
        {
            currSum+=9;
        }
        if(currSum<sum)
        {
            throw new Exception("Account Number can not be generated");
        }
        else {
            StringBuilder stringBuilder = new StringBuilder();
            int currentSum = 0;

            for (int i = 0; i < digits - 1; i++) {
                int digit = random.nextInt(Math.min(sum - currentSum,10));
                stringBuilder.append(digit);
                currentSum += digit;
            }

            int lastDigit = sum - currentSum;
            stringBuilder.append(lastDigit);

            return stringBuilder.toString();
        }
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance+=amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(balance-amount>=minBalance)
        {
            balance-=amount;
        }
        else {
            throw new Exception("Insufficient Balance");
        }
    }

}