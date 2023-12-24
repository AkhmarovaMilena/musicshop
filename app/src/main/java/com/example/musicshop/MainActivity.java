package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quanity = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.editTextText);

        createSpinner();
        createMap();
    }
    void createSpinner(){
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("Гитара");
        spinnerArrayList.add("Барабаны");
        spinnerArrayList.add("Пианино");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }
    void createMap(){
        goodsMap = new HashMap();
        goodsMap.put("Гитара", 500.0);
        goodsMap.put("Барабаны", 1500.0);
        goodsMap.put("Пианино", 1000.0);
    }

    public void plus(View view) {
        quanity = quanity + 1;
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText(""+quanity);
        TextView priceTextView1 = findViewById(R.id.priceTextView);
        priceTextView1.setText("" + quanity * price);
    }

    public void minus(View view) {
        quanity = quanity - 1;
        if(quanity<0) quanity=0;
        else{
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText(""+quanity);}
        TextView priceTextView1 = findViewById(R.id.priceTextView);
        priceTextView1.setText("" + quanity * price);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double)goodsMap.get(goodsName);
        TextView priceTextView1 = findViewById(R.id.priceTextView);
        priceTextView1.setText("" + quanity * price);

        ImageView goodsImageView = findViewById(R.id.goodsImageView);
        switch (goodsName){
            case "Гитара":
                goodsImageView.setImageResource(R.drawable.gitara);
                break;
            case "Барабаны":
                goodsImageView.setImageResource(R.drawable.baraban);
                break;
            case "Пианино":
                goodsImageView.setImageResource(R.drawable.piano);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.gitara);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quanity;
        order.orderPrice = price*quanity;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userNameForIntent", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("orderPrice", order.orderPrice);

        startActivity(orderIntent);
    }
}