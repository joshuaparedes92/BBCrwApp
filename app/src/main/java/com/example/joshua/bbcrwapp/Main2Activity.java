package com.example.joshua.bbcrwapp;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.graphics.Typeface;
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
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.Size1).setOnClickListener(ColorChange);
        findViewById(R.id.Size2).setOnClickListener(ColorChange);
        findViewById(R.id.Size3).setOnClickListener(ColorChange);
        findViewById(R.id.SecondCol1).setOnClickListener(ColorChange);
        findViewById(R.id.SecondCol2).setOnClickListener(ColorChange);
        findViewById(R.id.SecondCol3).setOnClickListener(ColorChange);
        findViewById(R.id.ThirdCol1).setOnClickListener(ColorChange);
        findViewById(R.id.ThirdCol2).setOnClickListener(ColorChange);
        findViewById(R.id.ThirdCol3).setOnClickListener(ColorChange);
        findViewById(R.id.FourthCol1).setOnClickListener(ColorChange);
        findViewById(R.id.FourthCol2).setOnClickListener(ColorChange);
        findViewById(R.id.FourthCol3).setOnClickListener(ColorChange);


        findViewById(R.id.textView3).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.textView5).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.textView6).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.textView7).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.textView8).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.textView9).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.textView10).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.textView12).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.textView13).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.textView14).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.textView15).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.textView16).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.textView17).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.textView18).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.textView19).setOnTouchListener(new MyTouchListener());


        findViewById(R.id.topLeft).setOnDragListener(new MyDragListener());
        findViewById(R.id.topRight).setOnDragListener(new MyDragListener());
        findViewById(R.id.bottomLeft).setOnDragListener(new MyDragListener());
        findViewById(R.id.bottomRight).setOnDragListener(new MyDragListener());

        //Draggable Initialize
        /*findViewById(R.id.textView3).setOnTouchListener(this);
        findViewById(R.id.textView5).setOnTouchListener(this);
        findViewById(R.id.textView6).setOnTouchListener(this);
        findViewById(R.id.textView7).setOnTouchListener(this);
        findViewById(R.id.textView8).setOnTouchListener(this);
        findViewById(R.id.textView9).setOnTouchListener(this);
        findViewById(R.id.textView10).setOnTouchListener(this);
        findViewById(R.id.textView12).setOnTouchListener(this);
        findViewById(R.id.textView13).setOnTouchListener(this);
        findViewById(R.id.textView14).setOnTouchListener(this);
        findViewById(R.id.textView15).setOnTouchListener(this);
        findViewById(R.id.textView16).setOnTouchListener(this);
        findViewById(R.id.textView17).setOnTouchListener(this);
        findViewById(R.id.textView18).setOnTouchListener(this);
        findViewById(R.id.textView19).setOnTouchListener(this);*/


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
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*boolean moving = false;
    float x,y = 0.0f;
    @Override
    public boolean onTouch(View v, MotionEvent e){
        switch(e.getAction()){
            case MotionEvent.ACTION_DOWN:
                moving= true;
                v.getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                if(moving){
                    x= e.getRawX() - v.getWidth();
                    y= e.getRawY() - v.getHeight()-700;
                    v.setX(x);
                    v.setY(y);
                }
                break;
            case MotionEvent.ACTION_UP:
                moving = false;
                break;
        }
        return true;
    }*/


     public final class MyTouchListener implements View.OnTouchListener{
         public boolean onTouch(View view, MotionEvent motionEvent){
             if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                 ClipData data = ClipData.newPlainText("", "");
                 View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                 view.startDrag(data, shadowBuilder, view, 0);
                 view.setVisibility(View.INVISIBLE);
                 return true;
             }else{
                 return false;
             }
         }
     }

     class MyDragListener implements View.OnDragListener {
         @Override
         public boolean onDrag(View v, DragEvent event) {
             int action = event.getAction();
             switch (event.getAction()){
                 case DragEvent.ACTION_DRAG_STARTED:
                     //do nothing
                     break;
                 case DragEvent.ACTION_DRAG_ENTERED:
                     //do nothing
                     break;
                 case DragEvent.ACTION_DRAG_EXITED:
                     //do nothing
                     break;
                 case DragEvent.ACTION_DROP:
                     View view = (View) event.getLocalState();
                     ViewGroup owner = (ViewGroup) view.getParent();
                     owner.removeView(view);
                     LinearLayout container = (LinearLayout) v;
                     container.addView(view);
                     view.setVisibility(View.VISIBLE);
                     break;
                 case DragEvent.ACTION_DRAG_ENDED:
                     //do nothing
                     default:
                         break;
             }
             return true;
         }
     }


    private boolean stateChanged1, stateChanged2, stateChanged3, stateChanged4, stateChanged5;
    private boolean stateChanged6, stateChanged7, stateChanged8, stateChanged9, stateChanged10;
    private boolean stateChanged11, stateChanged12;

    private View.OnClickListener ColorChange = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.Size1:
                    if(stateChanged1){
                        v.setBackgroundColor(Color.parseColor("#9aaff4"));
                    }else{
                        v.setBackgroundColor(Color.GREEN);
                    }
                    stateChanged1 = !stateChanged1;
                    break;

                case R.id.Size2:
                if(stateChanged2){
                    v.setBackgroundColor(Color.GREEN);
                }else{
                    v.setBackgroundColor(Color.parseColor("#9aaff4"));
                }
                stateChanged2 = !stateChanged2;
                break;

                case R.id.Size3:
                    if(stateChanged3){
                        v.setBackgroundColor(Color.GREEN);
                    }else{
                        v.setBackgroundColor(Color.parseColor("#9aaff4"));
                    }
                    stateChanged3 = !stateChanged3;
                    break;

                case R.id.SecondCol1:
                    if(stateChanged4){
                        v.setBackgroundColor(Color.parseColor("#9aaff4"));
                    }else{
                        v.setBackgroundColor(Color.GREEN);
                    }
                    stateChanged4 = !stateChanged4;
                    break;

                case R.id.SecondCol2:
                    if(stateChanged5){
                        v.setBackgroundColor(Color.GREEN);
                    }else{
                        v.setBackgroundColor(Color.parseColor("#9aaff4"));
                    }
                    stateChanged5 = !stateChanged5;
                    break;

                case R.id.SecondCol3:
                    if(stateChanged6){
                        v.setBackgroundColor(Color.parseColor("#9aaff4"));
                    }else{
                        v.setBackgroundColor(Color.GREEN);
                    }
                    stateChanged6 = !stateChanged6;
                    break;

                case R.id.ThirdCol1:
                    if(stateChanged7){
                        v.setBackgroundColor(Color.parseColor("#9aaff4"));
                    }else{
                        v.setBackgroundColor(Color.GREEN);
                    }
                    stateChanged7 = !stateChanged7;
                    break;

                case R.id.ThirdCol2:
                    if(stateChanged8){
                        v.setBackgroundColor(Color.GREEN);
                    }else{
                        v.setBackgroundColor(Color.parseColor("#9aaff4"));
                    }
                    stateChanged8 = !stateChanged8;
                    break;

                case R.id.ThirdCol3:
                    if(stateChanged9){
                        v.setBackgroundColor(Color.parseColor("#9aaff4"));
                    }else{
                        v.setBackgroundColor(Color.GREEN);
                    }
                    stateChanged9 = !stateChanged9;
                    break;

                case R.id.FourthCol1:
                    if(stateChanged10){
                        v.setBackgroundColor(Color.parseColor("#9aaff4"));
                    }else{
                        v.setBackgroundColor(Color.GREEN);
                    }
                    stateChanged10 = !stateChanged10;
                    break;

                case R.id.FourthCol2:
                    if(stateChanged11){
                        v.setBackgroundColor(Color.GREEN);
                    }else{
                        v.setBackgroundColor(Color.parseColor("#9aaff4"));
                    }
                    stateChanged11 = !stateChanged11;
                    break;

                case R.id.FourthCol3:
                    if(stateChanged12){
                        v.setBackgroundColor(Color.parseColor("#9aaff4"));
                    }else{
                        v.setBackgroundColor(Color.GREEN);
                    }
                    stateChanged12 = !stateChanged12;
                    break;
            }
        }
    };

}
