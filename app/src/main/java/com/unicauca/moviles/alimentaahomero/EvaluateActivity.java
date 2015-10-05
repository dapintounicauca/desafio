package com.unicauca.moviles.alimentaahomero;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
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
    Integer num_pregunta_actual;

    ImageView boca, esofago, estomago, higado, pancreas, delgado, grueso, ano, img_resultado;

    MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);

        num_pregunta = (TextView) findViewById(R.id.num_pregunta);
        pregunta = (TextView) findViewById(R.id.pregunta);

        generarPreguntasAleatorias();
        num_pregunta.setText("Pregunta 1");
        pregunta.setText(preguntas[0]);
        num_pregunta_actual = 1;

        boca     = (ImageView) findViewById(R.id.pt_boca);
        esofago  = (ImageView) findViewById(R.id.pt_esofago);
        estomago = (ImageView) findViewById(R.id.pt_estomago);
        higado   = (ImageView) findViewById(R.id.pt_higado);
        pancreas = (ImageView) findViewById(R.id.pt_pancreas);
        delgado  = (ImageView) findViewById(R.id.pt_delgado);
        grueso = (ImageView) findViewById(R.id.pt_grueso);
        ano = (ImageView) findViewById(R.id.pt_ano);
        img_resultado = (ImageView) findViewById(R.id.img_result);

        boca.setOnClickListener(this);
        esofago.setOnClickListener(this);
        estomago.setOnClickListener(this);
        higado.setOnClickListener(this);
        pancreas.setOnClickListener(this);
        delgado.setOnClickListener(this);
        grueso.setOnClickListener(this);
        ano.setOnClickListener(this);
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

        if(getResources().getResourceEntryName(v.getId()).equals(respuestas[num_pregunta_actual-1])) {
            img_resultado.setImageResource(R.drawable.yuju);
            sonar(0);
            siguientePregunta();
        }
        else {
            img_resultado.setImageResource(R.drawable.doh);
            sonar(1);
        }
    }

    public void siguientePregunta()
    {
        num_pregunta_actual += 1;
        num_pregunta.setText("Pregunta "+num_pregunta_actual);
        pregunta.setText(preguntas[num_pregunta_actual-1]);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            //code
        }
        return super.onKeyDown(keyCode, event);
    }
    public void sonar(int opc)
    {
        mp.reset();
        mp.release();
        if(opc == 0)
            mp= MediaPlayer.create(this, R.raw.yuju);
        else
            mp= MediaPlayer.create(this, R.raw.doh);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.seekTo(0);
        mp.start();
    }
}
