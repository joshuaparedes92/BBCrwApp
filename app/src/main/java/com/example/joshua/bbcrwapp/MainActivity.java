package com.example.joshua.bbcrwapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Activity activity = (MainActivity) this;

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
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



        //Spinner1 Element
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);

        //Spinner Click Listener
        spinner1.setOnItemSelectedListener(this);

        //Spinner Drop Down elements
        List<String> answer1 = new ArrayList<String>();
        answer1.add("Yes");
        answer1.add("No");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, answer1);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter);

        //Spinner2 Element
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        //Spinner Click Listener
        spinner2.setOnItemSelectedListener(this);

        //Spinner Drop Down elements
        List<String> answer2 = new ArrayList<String>();
        answer2.add("Living Room");
        answer2.add("Small Bedroom");
        answer2.add("Large Bedroom");
        answer2.add("Kitchen");
        answer2.add("Media room");
        answer2.add("Outside");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, answer2);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter2);

    }


    private static Bitmap takeScreenShot(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay()
                .getHeight();

        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        Log.e("Screenshot", "taken successfully");
        return b;
    }

    private void saveBitmap(Bitmap bitmap) {
        Date now = new Date();
        File imagePath = new File(Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            Log.e("Screenshot", "saved successfully");

            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
        shareImage(imagePath);
    }


    private void shareImage(File imagefile){
        Uri uri = Uri.fromFile(imagefile);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");

        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Customer Recommendation Worksheet");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "Here is your customized Recommendation worksheet." +
                "When you come to the store next time please provide this sheet to an associate to help" +
                "streamline the checkout process!");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(intent, "Share Screenshot"));
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    //This is going to be the Intent to Switch to the Second Activity
    public void onGetNameClick(View view) {
        Intent getNameScreenIntent = new Intent(this, Main2Activity.class);

        startActivity(getNameScreenIntent);
    }

}

/* TODO Prepare for Alpha release
   TODO 1. Fix TV Stand image in content_main2.xml
   TODO 2. Create String Resources for various TextFields
   TODO 3. Add more cable companies to YTM_Spinner 1
   TODO 4. Program FAB button to open up Gmail
   TODO 5. Create an Icon for the program
   TODO 6. Add Page 2 text to page 2 button
   TODO 7. Create a Screenshot button that takes a screen of the current Activity
   TODO 8. Create Localizations
   TODO 9. Find a way to save the state of the Second Activity
  */
