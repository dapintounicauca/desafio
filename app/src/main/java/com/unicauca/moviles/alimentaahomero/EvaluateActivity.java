package com.unicauca.moviles.alimentaahomero;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EvaluateActivity extends AppCompatActivity implements View.OnClickListener {

    TextView num_pregunta, pregunta;

    String[] preguntas, respuestas;
    Integer num_pregunta_actual;

    ImageView boca, esofago, estomago, higado, pancreas, delgado, grueso, ano, img_resultado;

    MediaPlayer mp = new MediaPlayer();

    //para llevar el registro de las estrellas o preguntas correctas a modo de puntaje
    TableLayout estrellas;
    Integer total_estrellas;
    //para llevar el registro de las vidas
    TableLayout vidas;
    Integer num_intentos;
    Integer num_corazones;

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
        total_estrellas = 0;
        num_intentos = 0;
        num_corazones = 3;

        boca     = (ImageView) findViewById(R.id.pt_boca);
        esofago  = (ImageView) findViewById(R.id.pt_esofago);
        estomago = (ImageView) findViewById(R.id.pt_estomago);
        higado   = (ImageView) findViewById(R.id.pt_higado);
        pancreas = (ImageView) findViewById(R.id.pt_pancreas);
        delgado  = (ImageView) findViewById(R.id.pt_delgado);
        grueso = (ImageView) findViewById(R.id.pt_grueso);
        ano = (ImageView) findViewById(R.id.pt_ano);
        img_resultado = (ImageView) findViewById(R.id.img_result);

        estrellas = (TableLayout) findViewById(R.id.tabla_estrellas);
        vidas = (TableLayout) findViewById(R.id.tabla_vidas);

        boca.setOnClickListener(this);
        esofago.setOnClickListener(this);
        estomago.setOnClickListener(this);
        higado.setOnClickListener(this);
        pancreas.setOnClickListener(this);
        delgado.setOnClickListener(this);
        grueso.setOnClickListener(this);
        ano.setOnClickListener(this);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            //code
        }
        return super.onKeyDown(keyCode, event);
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
            pintar_estrella(0); //con 0 para pintar como correcta la estrella
            sonar(0);
            if(num_pregunta_actual==10)
                mostrar_resultado(1); //finaliza el juego al completar 10 preguntas
            else
                siguientePregunta();
        }
        else {
            if(num_corazones==0) //finaliza el juego al perder los 3 corazones
            {
                mostrar_resultado(0);
            }
            img_resultado.setImageResource(R.drawable.doh);
            num_intentos+=1; //maximo 3 fallos por pregunta
            if(num_intentos==3){
                perder_corazon();
                num_intentos=0;
                pintar_estrella(1); //con 1 para pintar como incorrecta la estrella
                siguientePregunta(); //se salta la pregunta inten
            }
            sonar(1);
        }
    }

    public void siguientePregunta()
    {
        num_pregunta_actual += 1;
        num_pregunta.setText("Pregunta " + num_pregunta_actual);
        pregunta.setText(preguntas[num_pregunta_actual - 1]);
        num_intentos=0;
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
    public void pintar_estrella(int tipo)
    {
        int n_fil=0;
        int n_col=num_pregunta_actual-1;
        if(num_pregunta_actual>=6){ /*desde las sexta a la decima pregunta*/
            n_fil=1;
            n_col-=5;
        }
        TableRow fila = (TableRow)estrellas.getChildAt(n_fil); // Se obtiene la fila X
        ImageView estrella = (ImageView)fila.getChildAt(n_col); // Se obtiene el elemento de la fila
        if(tipo==1) //si perdio todas las vidas se pierde la estrella
            estrella.setColorFilter(Color.DKGRAY); //quito el filtro para dejar visible la estrella
        else {
            total_estrellas+=1;
            estrella.setColorFilter(0); //quito el filtro para dejar visible la estrella
        }
    }
    public void mostrar_resultado(int tipo) {
        if(tipo==0){  /*pierde el juego*/

        }else{   /*gana el juego*/
            
        }

    }

    public void perder_corazon(){
        TableRow fila = (TableRow) vidas.getChildAt(0); // Se obtiene la fila X
        ImageView corazon = (ImageView) fila.getChildAt(num_corazones -1); // Se obtiene el elemento de la fila
        corazon.setColorFilter(Color.LTGRAY); //quito el filtro para dejar visible la estrella
        num_corazones -=1;
    }

}
