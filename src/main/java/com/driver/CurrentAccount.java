package com.driver;

import java.util.PriorityQueue;

class Pair
{
    char ch;
    int freq;
    Pair(char ch,int freq)
    {
        this.ch=ch;
        this.freq=freq;
    }
}
public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId=tradeLicenseId;
        if(getBalance()<5000)
        {
            throw new Exception("Insufficient Balance");
        }
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        boolean flag=true;
        String s=tradeLicenseId;
        for(int i=1;i<s.length();i++)
        {
            if(s.charAt(i)==s.charAt(i-1))
            {
                flag=false;
            }
        }

        if(flag)
        {
            // create the freq array
            int[] freqArray=new int[26];
            for(int i=0;i<s.length();i++)
            {
                freqArray[s.charAt(i)-'a']++;
            }

            // retrieve on the basis of max freq because they can cause problem
            PriorityQueue<Pair> pq=new PriorityQueue<>((a, b)->
            {
                return b.freq-a.freq;
            });
            for(int i=0;i<26;i++)
            {
                if(freqArray[i]>0)
                {
                    pq.add(new Pair((char)(i+'a'),freqArray[i]));
                }
            }

            StringBuilder sb=new StringBuilder();
            // append the first max char and reduce the freq
            // because we will use the pair as block so that we can not use it in adjacent
            Pair block=pq.remove();
            sb.append(block.ch);
            block.freq--;

            // perfrom this ops while pq have pairs
            while(pq.size()!=0)
            {
                // append and update
                Pair temp=pq.remove();
                sb.append(temp.ch);
                temp.freq--;

                // only add in pq if the block ch is left
                if(block.freq>0)
                {
                    pq.add(block);
                }
                // make the temp as block for next iteration
                block=temp;
            }

            // after that if any ch are still left means we cannot reorganise them
            if(block.freq>0)
            {
                throw new Exception("Valid License can not be generated");
            }
            else
            {
                tradeLicenseId=sb.toString();
            }
        }
    }
}
