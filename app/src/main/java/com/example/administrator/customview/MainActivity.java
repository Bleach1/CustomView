package com.example.administrator.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.administrator.customview.catloading.CatLoadingView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ljn";
    private View custom_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        custom_view = findViewById(R.id.custom_view);
        custom_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PieActivity.class));
            }
        });
        new Thread() {
            @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                    Log.i(TAG, "onCreate: " + "\n"
                            + "getTop--" + custom_view.getTop() + "\n"
                            + "getLeft--" + custom_view.getLeft() + "\n"
                            + "getBottom--" + custom_view.getBottom() + "\n"
                            + "getRight--" + custom_view.getRight());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        CatLoadingView view = new CatLoadingView();
        view.show(getSupportFragmentManager(), "");


    }
}
