package com.unicauca.moviles.alimentaahomero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button aprender, evaluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aprender = (Button) findViewById(R.id.btn_aprender);
        evaluar = (Button) findViewById(R.id.btn_evaluar);

        aprender.setOnClickListener(this);
        evaluar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_aprender: {
                Intent intent = new Intent(this, LearnActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_evaluar: {
                Intent intent = new Intent(this, EvaluateActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
