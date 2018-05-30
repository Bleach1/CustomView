package com.example.administrator.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class PieActivity extends AppCompatActivity {
    private ArrayList<PieData> pieData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);
        PieView pie_view = findViewById(R.id.pie_view);

        for (int i = 0; i < 4; i++) {
            PieData pieData = new PieData();
            pieData.setValue(90);
            this.pieData.add(pieData);
        }
        pie_view.setData(pieData);
    }
}
