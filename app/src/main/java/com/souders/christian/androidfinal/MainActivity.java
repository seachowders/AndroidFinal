package com.souders.christian.androidfinal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialize_LR_Buttons();



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

    public void initialize_LR_Buttons()
    {

        Button lbut = (Button)findViewById(R.id.leftButton);
        Button rbut = (Button)findViewById(R.id.rightButton);

        lbut.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //button pressed down
                GameView gv = (GameView)findViewById(R.id.view);
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    gv.paddle.setMovementState(gv.paddle.LEFT);
                    // Do what you want
                    return true;
                }
                //button press lifted
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    gv.paddle.setMovementState(gv.paddle.STOPPED);
                    //Toast.makeText(MainActivity.this, "UPCLICK", Toast.LENGTH_SHORT).show();
                    // Do what you want
                    return true;
                }
                return false;
            }
        });

        rbut.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //button pressed down
                GameView gv = (GameView)findViewById(R.id.view);
                gv.paused = false;
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    gv.paddle.setMovementState(gv.paddle.RIGHT);
                    gv.update();
                    // Do what you want
                    return true;
                }
                //button press lifted
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    gv.paddle.setMovementState(gv.paddle.STOPPED);
                    // Do what you want
                    return true;
                }
                return false;
            }
        });
    }



}
