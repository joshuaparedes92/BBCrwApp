package com.example.joshua.bbcrwapp;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

        final EditText pr = (EditText) findViewById(R.id.price);
        pr.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    double p = Double.parseDouble(pr.getText().toString());
                    BpCalc(p);
                    return true;
                }
                return false;
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        addDrawerItems();
        setupDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

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

    private void BpCalc(double price) {
        double bpPrice1, bpPrice2 = 0, bpPrice3 = 0;
        TextView sixC = (TextView) findViewById(R.id.sixCost);
        TextView twelveC = (TextView) findViewById(R.id.twelveCost);
        TextView TfourC= (TextView) findViewById(R.id.twentyfourCost);

        //TODO: Insert Try-catch block here for when user enters a value less than 199.99

        if( price<=399.99 && price > 199.99){
            bpPrice1 = Math.round((price/6)*100.0)/100.0;
            String bp1 = Double.toString(bpPrice1);
            sixC.setText(bp1);
        }
        else if (price>=400.00 && price< 799.99){
            bpPrice1 = Math.round((price/6)*100.0)/100.0;
            bpPrice2 = Math.round((price/12)*100.0)/100.0;
            String bp1 = Double.toString(bpPrice1);
            String bp2 = Double.toString(bpPrice2);
            sixC.setText(bp1);
            twelveC.setText(bp2);
        }
        else{
            bpPrice1 = Math.round((price/6)*100.0)/100.0;
            bpPrice2 = Math.round((price/12)*100.0)/100.0;
            bpPrice3 = Math.round((price/24)*100.0)/100.0;
            String bp1 = Double.toString(bpPrice1);
            String bp2 = Double.toString(bpPrice2);
            String bp3 = Double.toString(bpPrice3);
            sixC.setText(bp1);
            twelveC.setText(bp2);
            TfourC.setText(bp3);
        }
    }


//**************************************FUNCTIONS**************************************************

    //TODO: Implement Functions to calculate GSP cost, Total Cost.


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
