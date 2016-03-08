package com.example.joshua.bbcrwapp;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
, View.OnTouchListener, View.OnDragListener {

    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Draggable Initialize
        findViewById(R.id.textView3).setOnTouchListener(this);
        findViewById(R.id.textView5).setOnTouchListener(this);
        findViewById(R.id.textView6).setOnTouchListener(this);
        findViewById(R.id.textView7).setOnTouchListener(this);
        findViewById(R.id.textView8).setOnTouchListener(this);
        findViewById(R.id.textView9).setOnTouchListener(this);
        findViewById(R.id.textView10).setOnTouchListener(this);
        findViewById(R.id.container).setOnDragListener(this);
        findViewById(R.id.container1).setOnDragListener(this);


        //YTM_Spinner1 Initialization
        //Spinner Element
        Spinner spinner1 = (Spinner) findViewById(R.id.YTM_spinner1);

        //Spinner Click Listener
        spinner1.setOnItemSelectedListener(this);

        //Spinner Drop Down elements
        List<String> answer1 = new ArrayList<String>();
        answer1.add("Cablevision");
        answer1.add("Verizon");
        answer1.add("Comcast");
        answer1.add("DirectTV");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, answer1);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter);
    /**********************************************************************************************/

        //YTM_Spinner2 Initialization
        //Spinner Element
        Spinner spinner2 = (Spinner) findViewById(R.id.YTM_spinner2);

        //Spinner Click Listener
        spinner2.setOnItemSelectedListener(this);

        //Spinner Drop Down elements
        List<String> answer2 = new ArrayList<String>();
        answer2.add("10");
        answer2.add("20");
        answer2.add("30");
        answer2.add("40");
        answer2.add("50");
        answer2.add("60");
        answer2.add("70");
        answer2.add("80");
        answer2.add("90");
        answer2.add("100");
        answer2.add("110");
        answer2.add("120+");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, answer2);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter2);
        /******************************************************************************************/

        //YTM_Spinner3 Initialization
        //Spinner Element
        Spinner spinner3 = (Spinner) findViewById(R.id.YTM_spinner3);

        //Spinner Click Listener
        spinner3.setOnItemSelectedListener(this);

        //Spinner Drop Down elements
        List<String> answer3 = new ArrayList<String>();
        answer3.add("1");
        answer3.add("2");
        answer3.add("3");
        answer3.add("4");
        answer3.add("5+");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, answer3);

        // Drop down layout style - list view with radio button
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner3.setAdapter(dataAdapter3);
        /******************************************************************************************/

        //YTM_Spinner4 Initialization
        //Spinner Element
        Spinner spinner4 = (Spinner) findViewById(R.id.YTM_spinner4);

        //Spinner Click Listener
        spinner4.setOnItemSelectedListener(this);

        //Spinner Drop Down elements
        List<String> answer4 = new ArrayList<String>();
        answer4.add("50");
        answer4.add("100");
        answer4.add("200");
        answer4.add("300");
        answer4.add("400");
        answer4.add("600");
        answer4.add("800+");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, answer4);

        // Drop down layout style - list view with radio button
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner4.setAdapter(dataAdapter4);
        /******************************************************************************************/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //Drag Function
    @Override
    public boolean onTouch(View v, MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(null, shadowBuilder, v, 0);
            v.setVisibility(View.INVISIBLE);
            return true;
        }
            else{
            return false;
        }
    }

    @Override
    public boolean onDrag(View v, DragEvent e) {

            if (e.getAction() == DragEvent.ACTION_DROP) {
                View view = (View) e.getLocalState();
                ViewGroup from = (ViewGroup) view.getParent();
                from.removeView(view);
                LinearLayout to = (LinearLayout) v;
                to.addView(view);
                view.setVisibility(View.VISIBLE);
            }
            return true;
        }
}
