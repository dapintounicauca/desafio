package com.unicauca.moviles.alimentaahomero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ProcessActivity extends AppCompatActivity {

    public static String ORGANO = "";

    TextView contenido_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);
        contenido_info = (TextView) findViewById(R.id.contenido_info);
        showProcess(ORGANO);
    }

    public void showProcess(String name)
    {
        String organos[] = getResources().getStringArray(R.array.organos);
        String procesos[] = getResources().getStringArray(R.array.procesos_alimento);

        switch (name)
        {
            case "Boca":
                contenido_info.setText(procesos[0]);
                break;
            case "Esofago":
                contenido_info.setText(procesos[1]);
                break;
            case "Estomago":
                contenido_info.setText(procesos[2]);
                break;
            case "Delgado":
                contenido_info.setText(procesos[3]);
                break;
            case "Higado":
                contenido_info.setText(procesos[4]);
                break;
            case "Pancreas":
                contenido_info.setText(procesos[5]);
                break;
            case "Grueso":
                contenido_info.setText(procesos[6]);
                break;
        }
    }
}
