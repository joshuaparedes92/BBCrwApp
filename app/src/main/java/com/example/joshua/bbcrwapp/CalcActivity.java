package com.example.joshua.bbcrwapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CalcActivity extends MainActivity {
    //TODO: See if you can make this page look better.
    //TODO: Something is wrong with the checkboxes. Fix them.

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

       /*This is the CodeBlock that Initializes the GSP, Branded Payment,
        *and total price functions.
        */

        final CheckBox twoYearBox = (CheckBox) findViewById(R.id.checkBox2Year);
        final CheckBox fiveYearBox = (CheckBox) findViewById(R.id.checkBox5Year);
        final EditText pr = (EditText) findViewById(R.id.price);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        addDrawerItems();
        setupDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        pr.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press. If the user enters null the program prints
                    // a message telling the user they need to enter a number.
                    try {
                        double p = Double.parseDouble(pr.getText().toString());
                        Calc(p, twoYearBox, fiveYearBox);
                        return true;
                    } catch (Throwable e) {
                        Toast.makeText(getApplicationContext(), "Please enter a price.", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

        final Button refresh = (Button) findViewById(R.id.button);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double p = Double.parseDouble(pr.getText().toString());
                    Calc(p, twoYearBox, fiveYearBox);
                } catch (Throwable e){
                    Toast.makeText(getApplicationContext(), "There is nothing to calculate.",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        final Activity activity = this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sharing...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                try {
                    // image naming and path  to include sd card  appending name you choose for file
                    Bitmap bitmap = takeScreenShot(activity);
                    saveBitmap(bitmap);

                } catch (Throwable e) {
                    // Several error may come out with file handling or OOM
                    e.printStackTrace();
                }
            }
        });
    }

 //**************************************FUNCTIONS**************************************************

    @SuppressLint("SetTextI18n")
    private void Calc(double price, final CheckBox twoyear, CheckBox fiveYear) {
        double gspPriceTwo;
        double gspPriceFive;


        TextView twoYearPrice = (TextView) findViewById(R.id.twoYearText);
        TextView fiveYearPrice = (TextView) findViewById(R.id.fiveYearText);
        TextView twoPerYear = (TextView) findViewById(R.id.twoYearPerYear);
        TextView fivePerYear = (TextView) findViewById(R.id.fiveYearPerYear);
        TextView twoPerMonth = (TextView) findViewById(R.id.twoYearPerMonth);
        TextView fivePerMonth = (TextView) findViewById(R.id.fiveYearPerMonth);
        TextView twoPerDay = (TextView) findViewById(R.id.twoYearPerDay);
        TextView fivePerDay = (TextView) findViewById(R.id.fiveYearPerDay);


        if (price > 0.00 && price <= 99.99) {
            gspPriceTwo = 14.99;
            gspPriceFive = 24.99;

            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);

            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 100.00 && price <= 149.99) {
            gspPriceTwo = 19.99;
            gspPriceFive = 29.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 150.00 && price <= 199.99) {
            gspPriceTwo = 24.99;
            gspPriceFive = 39.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 200.00 && price <= 249.99) {
            gspPriceTwo = 29.99;
            gspPriceFive = 49.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 250.00 && price <= 299.99) {
            gspPriceTwo = 39.99;
            gspPriceFive = 59.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 300.00 && price <= 349.99) {
            gspPriceTwo = 39.99;
            gspPriceFive = 69.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 350.00 && price <= 399.99) {
            gspPriceTwo = 49.99;
            gspPriceFive = 79.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 400.00 && price <= 449.99) {
            gspPriceTwo = 59.99;
            gspPriceFive = 89.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 450.00 && price <= 499.99) {
            gspPriceTwo = 69.99;
            gspPriceFive = 99.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 500.00 && price <= 599.99) {
            gspPriceTwo = 69.99;
            gspPriceFive = 119.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 600.00 && price <= 699.99) {
            gspPriceTwo = 79.99;
            gspPriceFive = 149.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 700.00 && price <= 799.99) {
            gspPriceTwo = 99.99;
            gspPriceFive = 179.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 800.00 && price <= 899.99) {
            gspPriceTwo = 119.99;
            gspPriceFive = 199.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 900.00 && price <= 999.99) {
            gspPriceTwo = 129.99;
            gspPriceFive = 219.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 1000.00 && price <= 1199.99) {
            gspPriceTwo = 149.99;
            gspPriceFive = 249.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 1200.00 && price <= 1399.99) {
            gspPriceTwo = 169.99;
            gspPriceFive = 299.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 1400.00 && price <= 1599.99) {
            gspPriceTwo = 179.99;
            gspPriceFive = 329.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 1600.00 && price <= 1799.99) {
            gspPriceTwo = 199.99;
            gspPriceFive = 369.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 1800.00 && price <= 1999.99) {
            gspPriceTwo = 229.99;
            gspPriceFive = 399.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 2000.00 && price <= 2499.99) {
            gspPriceTwo = 279.99;
            gspPriceFive = 499.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 2500.00 && price <= 2999.99) {
            gspPriceTwo = 349.99;
            gspPriceFive = 599.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 3000.00 && price <= 3499.99) {
            gspPriceTwo = 399.99;
            gspPriceFive = 699.99;
            twoYearPrice.setText("$" + Double.toString(Math.round((gspPriceTwo) * 100.0) / 100.0));
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("$" + Double.toString(Math.round((gspPriceTwo / 2) * 100.0) / 100.0));
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("$" + Double.toString(Math.round((gspPriceTwo / 12 / 2) * 100.0) / 100.0));
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("$" + Double.toString(Math.round((gspPriceTwo / 365 / 2) * 100.0) / 100.0));
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (twoyear.isChecked()) {
                bpCalc(price + gspPriceTwo);
            } else if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            }
            else{
                bpCalc(price);
            }
        } else if (price >= 3500.00 && price <= 3999.99) {
            gspPriceFive = 799.99;
            twoYearPrice.setText("N/A");
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("N/A");
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("N/A");
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("N/A");
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            } else {
                bpCalc(price);
            }
        } else if (price >= 4000.00 && price <= 4999.99) {
            gspPriceFive = 999.99;
            twoYearPrice.setText("N/A");
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("N/A");
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("N/A");
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("N/A");
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            } else {
                bpCalc(price);
            }
        } else if (price >= 5000.00 && price <= 7499.99) {
            gspPriceFive = 1199.99;
            twoYearPrice.setText("N/A");
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("N/A");
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("N/A");
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("N/A");
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            } else {
                bpCalc(price);
            }
        } else if (price >= 7500.00 && price <= 9999.99) {
            gspPriceFive = 1499.99;
            twoYearPrice.setText("N/A");
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("N/A");
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("N/A");
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("N/A");
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            } else {
                bpCalc(price);
            }
        } else if (price >= 10000.00 && price <= 14999.99) {
            gspPriceFive = 1699.99;
            twoYearPrice.setText("N/A");
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("N/A");
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("N/A");
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("N/A");
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            } else {
                bpCalc(price);
            }
        } else if (price >= 15000.00 && price <= 19999.99) {
            gspPriceFive = 2099.99;
            twoYearPrice.setText("N/A");
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("N/A");
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("N/A");
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("N/A");
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            } else {
                bpCalc(price);
            }
        } else if (price >= 20000.00 && price <= 24999.99) {
            gspPriceFive = 2499.99;
            twoYearPrice.setText("N/A");
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("N/A");
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("N/A");
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("N/A");
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            } else {
                bpCalc(price);
            }
        } else if (price >= 25000.00 && price <= 49999.99) {
            gspPriceFive = 3899.99;
            twoYearPrice.setText("N/A");
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("N/A");
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("N/A");
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("N/A");
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            } else {
                bpCalc(price);
            }
        } else {
            gspPriceFive = 5499.99;
            twoYearPrice.setText("N/A");
            fiveYearPrice.setText("$" + Double.toString(Math.round((gspPriceFive) * 100.0) / 100.0));
            twoPerYear.setText("N/A");
            fivePerYear.setText("$" + Double.toString(Math.round((gspPriceFive / 5) * 100.0) / 100.0));
            twoPerMonth.setText("N/A");
            fivePerMonth.setText("$" + Double.toString(Math.round((gspPriceFive / 12 / 5) * 100.0) / 100.0));
            twoPerDay.setText("N/A");
            fivePerDay.setText("$" + Double.toString(Math.round((gspPriceFive / 365 / 5) * 100.0) / 100.0));

            if (fiveYear.isChecked()) {
                bpCalc(price + gspPriceFive);
            } else {
                bpCalc(price);
            }
        }
    }

    private void bpCalc(double price) {
        double bpPrice1, bpPrice2, bpPrice3;
        TextView sixC = (TextView) findViewById(R.id.sixCost);
        TextView twelveC = (TextView) findViewById(R.id.twelveCost);
        TextView TfourC= (TextView) findViewById(R.id.twentyfourCost);


        //TODO: Use resource strings with placeholder's for localization purposes.

        if( price<=399.99 && price > 199.99){
            bpPrice1 = Math.round((price/6)*100.0)/100.0;
            String bp1 = Double.toString(bpPrice1);
            sixC.setText("$"+ bp1 + "/month");
            twelveC.setText("N/A");
            TfourC.setText("N/A");
            total(price);
        } else if (price>=400.00 && price< 799.99){
            bpPrice1 = Math.round((price/6)*100.0)/100.0;
            bpPrice2 = Math.round((price/12)*100.0)/100.0;
            String bp1 = Double.toString(bpPrice1);
            String bp2 = Double.toString(bpPrice2);
            sixC.setText("$"+ bp1 + "/month");
            twelveC.setText("$"+ bp2 + "/month");
            TfourC.setText("N/A");
            total(price);
        } else if (price >799.99){
            bpPrice1 = Math.round((price/6)*100.0)/100.0;
            bpPrice2 = Math.round((price/12)*100.0)/100.0;
            bpPrice3 = Math.round((price/24)*100.0)/100.0;
            String bp1 = Double.toString(bpPrice1);
            String bp2 = Double.toString(bpPrice2);
            String bp3 = Double.toString(bpPrice3);
            sixC.setText("$"+ bp1 + "/month");
            twelveC.setText("$"+ bp2 + "/month");
            TfourC.setText("$"+ bp3 + "/month");
            total(price);
        }
        else {
            sixC.setText("N/A");
            twelveC.setText("N/A");
            TfourC.setText("N/A");
            total(price);
        }
    }

    private void total(double price) {
        TextView grandT = (TextView) findViewById(R.id.totalCost);

        String grandTotal;
        double tax;

        tax = price *.09;

        grandTotal = Double.toString(Math.round((price + tax) * 100.0) / 100.0);

        grandT.setText("$" + grandTotal);
        grandT.setTextColor(Color.GREEN);

    }


@Override
protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    // Sync the toggle state after onRestoreInstanceState has occurred.
    mDrawerToggle.syncState();
}

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
