package com.example.android.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private int quantity = 0;
    private int price = 5;
    private int userPrice = 0;
    private boolean toppingAdd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view){
        quantity++;
        display(quantity);
    }

    public void decrement(View view){
        if(quantity > 0) {
            quantity--;
        }
        display(quantity);
    }

    private String createOrderSummary(int number, boolean toppingAdd){
        String orderSummary ="Name: Skips Snow";
        orderSummary += "\nAdd Whipped Cream: " + toppingAdd;
        orderSummary += "\nQuantity: " + quantity;
        orderSummary += "\nThank You!";
        return orderSummary;
    }

    public void submitOrder(View view){
        CheckBox toppingBox = (CheckBox) findViewById(R.id.toppings);

        toppingAdd = toppingBox.isChecked();
        userPrice = price*quantity;

        displayPrice(price*quantity);
        displayMessage(createOrderSummary(price*quantity, toppingAdd));
    }

    private void display(int number){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText("Total: $" + number);
    }

    private void displayMessage(String msg) {
        TextView priceTextView = (TextView) findViewById(R.id.price_message_view);
        priceTextView.setText(msg);
    }

    private void calculatePrice(int quantity){

    }
}
