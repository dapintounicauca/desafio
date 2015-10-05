package com.unicauca.moviles.alimentaahomero;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EvaluateActivity extends AppCompatActivity implements View.OnClickListener {

    TextView num_pregunta, pregunta;
    String[] preguntas, respuestas;

    ImageView boca, esofago, estomago, higado, pancreas, delgado, grueso1, grueso2, grueso3, grueso4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);

        num_pregunta = (TextView) findViewById(R.id.num_pregunta);
        pregunta = (TextView) findViewById(R.id.pregunta);

        generarPreguntasAleatorias();
        num_pregunta.setText("Pregunta 1");
        pregunta.setText(preguntas[0]);

        boca     = (ImageView) findViewById(R.id.pt_boca);
        esofago  = (ImageView) findViewById(R.id.pt_esofago);
        estomago = (ImageView) findViewById(R.id.pt_estomago);
        higado   = (ImageView) findViewById(R.id.pt_higado);
        pancreas = (ImageView) findViewById(R.id.pt_pancreas);
        delgado  = (ImageView) findViewById(R.id.pt_delgado);
        grueso1  = (ImageView) findViewById(R.id.pt_grueso_1);
        grueso2  = (ImageView) findViewById(R.id.pt_grueso_2);
        grueso3  = (ImageView) findViewById(R.id.pt_grueso_3);
        grueso4  = (ImageView) findViewById(R.id.pt_grueso_4);

        boca.setOnClickListener(this);
        esofago.setOnClickListener(this);
        estomago.setOnClickListener(this);
        higado.setOnClickListener(this);
        pancreas.setOnClickListener(this);
        delgado.setOnClickListener(this);
        grueso1.setOnClickListener(this);
        grueso2.setOnClickListener(this);
        grueso3.setOnClickListener(this);
        grueso4.setOnClickListener(this);
    }

    public void generarPreguntasAleatorias()
    {
        Random random = new Random();

        List<Integer> aux = new ArrayList();

        String preguntasOrdenadas[] = getResources().getStringArray(R.array.preguntas);
        String respuestasOrdenadas[] = getResources().getStringArray(R.array.respuestas);

        int n = preguntasOrdenadas.length;
        preguntas = new String[n];
        respuestas = new String[n];

        for(int i=0; i<n; i++)
        {
            int index = random.nextInt(n);
            if(aux.isEmpty())
            {
                aux.add(index);
                preguntas[i] = preguntasOrdenadas[index];
                respuestas[i] = respuestasOrdenadas[index];
            }
            else {
                while(aux.contains(index))
                    index = random.nextInt(n);
                aux.add(index);
                preguntas[i] = preguntasOrdenadas[index];
                respuestas[i] = respuestasOrdenadas[index];
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch(num_pregunta.getText().toString())
        {
            case "Pregunta 1":
                if(getResources().getResourceEntryName(v.getId()).equals(respuestas[0])) {
                    Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
                    siguientePregunta();
                }
                else
                    Toast.makeText(this,"Incorrecto",Toast.LENGTH_SHORT).show();
                break;
            case "Pregunta 2":
                if(getResources().getResourceEntryName(v.getId()).equals(respuestas[1])) {
                    Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
                    siguientePregunta();
                }
                else
                    Toast.makeText(this,"Incorrecto",Toast.LENGTH_SHORT).show();
                break;
            case "Pregunta 3":
                if(getResources().getResourceEntryName(v.getId()).equals(respuestas[2])) {
                    Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
                    siguientePregunta();
                }
                else
                    Toast.makeText(this,"Incorrecto",Toast.LENGTH_SHORT).show();
                break;
            case "Pregunta 4":
                if(getResources().getResourceEntryName(v.getId()).equals(respuestas[3])) {
                    Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
                    siguientePregunta();
                }
                else
                    Toast.makeText(this,"Incorrecto",Toast.LENGTH_SHORT).show();
                break;
            case "Pregunta 5":
                if(getResources().getResourceEntryName(v.getId()).equals(respuestas[4])) {
                    Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
                    siguientePregunta();
                }
                else
                    Toast.makeText(this,"Incorrecto",Toast.LENGTH_SHORT).show();
                break;
            case "Pregunta 6":
                if(getResources().getResourceEntryName(v.getId()).equals(respuestas[5])) {
                    Toast.makeText(this, "¡FELICIDADES!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(this,"Incorrecto",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    public void siguientePregunta()
    {
        String[] tockens  = num_pregunta.getText().toString().split(" ");
        int num = Integer.parseInt(tockens[1]);
        num_pregunta.setText("Pregunta "+(num+1));
        pregunta.setText(preguntas[num]);
    }
}
