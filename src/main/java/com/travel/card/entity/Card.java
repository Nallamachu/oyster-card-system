package com.travel.card.entity;

import com.travel.card.exception.CustomException;

public class Card {
    private float balance;

    public Card(float balance){
        this.balance = balance;
    }
    public Card(){
        this.balance = 0.0f;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void addMoney(float money){
        this.balance = balance+money;
    }

    public void out(float fare) {
        validateFare(fare);
        this.balance = this.balance - fare;
    }

    public void validateFare(float fare) throws CustomException{
        if(balance < fare)
            throw  new CustomException("Do not have sufficient balance. Please recharge it.");
    }

    public void in(float f) {
        this.balance = this.balance + f;
    }
}
