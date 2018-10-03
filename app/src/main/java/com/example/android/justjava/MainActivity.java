package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URI;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private int quantity = 0;
    private int price = 5;
    private int creamPrice = 1;
    private int chocoPrice = 2;
    private int userPrice = 0;
    private boolean toppingAdd = false;
    private boolean toppingAddChoco = false;

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

    private String createOrderSummary(int number, boolean toppingAdd, boolean toppingAddChoco, String name){
        String price = "$" + Integer.toString(number);
        String orderSummary = getString(R.string.order_summary_name, name);
        orderSummary += "\n" + getString(R.string.order_summary_whipped_cream, toppingAdd);
        orderSummary += "\n" + getString(R.string.order_summary_chocolate, toppingAddChoco);
        orderSummary += "\n" + getString(R.string.order_summary_quantity, quantity);
        orderSummary += "\n" + getString(R.string.order_summary_price, price);
        orderSummary += "\n" + getString(R.string.thank_you);
        return orderSummary;
    }

    private int calculatePrice(int quantity, boolean toppingAdd, boolean toppingAddChoco){
        int tempPrice = price;

        if(toppingAdd){
            tempPrice += creamPrice;
        }

        if(toppingAddChoco){
            tempPrice += chocoPrice;
        }

        tempPrice *= quantity;

        return tempPrice;
    }

    public void submitOrder(View view){
        CheckBox toppingBox = (CheckBox) findViewById(R.id.toppings);
        toppingAdd = toppingBox.isChecked();

        toppingBox = (CheckBox) findViewById(R.id.chocoToppings);
        toppingAddChoco = toppingBox.isChecked();

        EditText editName = (EditText) findViewById(R.id.name);
        String name = editName.getText().toString();

        userPrice = calculatePrice(quantity, toppingAdd, toppingAddChoco);

        //displayPrice(userPrice);
        //displayMessage(createOrderSummary(userPrice, toppingAdd, toppingAddChoco, name));
        openEmail(createOrderSummary(userPrice, toppingAdd, toppingAddChoco, name), name);
    }

    private void openEmail(String orderSummary, String name){
        String subject = getString(R.string.order_summary_email_subject, name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    private void display(int number){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        /*TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText("Total: $" + number);*/
    }

    private void displayMessage(String msg) {
        /*TextView priceTextView = (TextView) findViewById(R.id.price_message_view);
        priceTextView.setText(msg);*/
    }
}
