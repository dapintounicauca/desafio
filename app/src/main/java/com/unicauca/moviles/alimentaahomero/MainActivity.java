package com.unicauca.moviles.alimentaahomero;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button aprender, evaluar;
    ImageView salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aprender = (Button) findViewById(R.id.btn_aprender);
        evaluar = (Button) findViewById(R.id.btn_evaluar);
        salir = (ImageView) findViewById(R.id.img_salir);

        Typeface typeface =Typeface.createFromAsset(getAssets(),"fonts/BradBunR.ttf");
        aprender.setTypeface(typeface);
        evaluar.setTypeface(typeface);

        aprender.setOnClickListener(this);
        evaluar.setOnClickListener(this);
        salir.setOnClickListener(this);
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
            case R.id.img_salir: {
                SharedPreferences settings = getSharedPreferences(LearnActivity.PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("skipMessageLearn", "NOT checked");
                editor.commit();

                settings = getSharedPreferences(EvaluateActivity.PREFS_NAME2, 0);
                editor = settings.edit();
                editor.putString("skipMessageEvaluate", "NOT checked");
                editor.commit();

                finish();
                break;
            }
        }
    }
}
