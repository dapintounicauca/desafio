package com.unicauca.moviles.alimentaahomero;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EvaluateActivity extends AppCompatActivity implements View.OnClickListener, ViewPropertyAnimatorListener {

    public static final String PREFS_NAME2 = "MyPrefsFile2";

    TextView num_pregunta, pregunta, txt_vidas, txt_puntos;
    TextView txt_indicacion, txt_indicacion_titulo;
    Typeface typeface;

    //int state =0;
    String[] preguntas, respuestas;
    Integer num_pregunta_actual;

    ImageView boca, esofago, estomago, higado, pancreas, delgado, grueso, ano, img_resultado, img_ayuda, img_home;

    MediaPlayer mp = new MediaPlayer();

    //para llevar el registro de las estrellas o preguntas correctas a modo de puntaje
    TableLayout estrellas;
    Integer total_estrellas;
    //para llevar el registro de las vidas
    TableLayout vidas;
    Integer num_intentos;
    Integer num_corazones;
    CheckBox dontShowAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);

        num_pregunta = (TextView) findViewById(R.id.num_pregunta);
        pregunta = (TextView) findViewById(R.id.pregunta);
        txt_puntos = (TextView) findViewById(R.id.txt_puntos);
        txt_vidas = (TextView) findViewById(R.id.txt_vidas);



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

        typeface = Typeface.createFromAsset(getAssets(),"fonts/BradBunR.ttf");
        pregunta.setTypeface(typeface);
        num_pregunta.setTypeface(typeface);
        txt_vidas.setTypeface(typeface);
        txt_puntos.setTypeface(typeface);

        //ViewCompat.setAlpha(img_resultado, 0);

        boca.setOnClickListener(this);
        esofago.setOnClickListener(this);
        estomago.setOnClickListener(this);
        higado.setOnClickListener(this);
        pancreas.setOnClickListener(this);
        delgado.setOnClickListener(this);
        grueso.setOnClickListener(this);
        ano.setOnClickListener(this);

        img_ayuda = (ImageView) findViewById(R.id.img_ayuda);
        img_ayuda.setOnClickListener(this);

        img_home = (ImageView) findViewById(R.id.img_home);
        img_home.setOnClickListener(this);

        show_dialog_help();
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

        if(v.getId() == R.id.img_ayuda)
        {
            LayoutInflater inflater = LayoutInflater.from(this);
            View dialog_layout = inflater.inflate(R.layout.dialog_help_evaluate, null);
            dontShowAgain = (CheckBox) dialog_layout.findViewById(R.id.cb_skip);
            AlertDialog.Builder db = new AlertDialog.Builder(EvaluateActivity.this);
            db.setView(dialog_layout);
            db.setTitle("Indicaciones");
            db.setPositiveButton("ENTENDIDO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {}});
            dontShowAgain.setVisibility(View.INVISIBLE);
            db.show();
        }
        else if(v.getId() == R.id.btn_jugar)
        {
            Intent intent = new Intent(this, EvaluateActivity.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.img_home)
        {
            this.finish();
        }
        else if(v.getId() == R.id.btn_salir)
        {
            this.finish();
        }
        else {
            if(getResources().getResourceEntryName(v.getId()).equals(respuestas[num_pregunta_actual-1])) {
                //state = 0;
                img_resultado.setImageResource(R.drawable.yuju);
                //ViewCompat.animate(img_resultado).setDuration(100).alpha(100).setListener(this).start();
                pintar_estrella(); //hace visible una estrella en puntaje
                sonar(0);
                if(total_estrellas==10)
                    mostrar_resultado(1); //finaliza el juego al completar 10 preguntas
                else if(num_pregunta_actual==10)
                    mostrar_resultado(0);
                else
                    siguientePregunta();
            }
            else {
                img_resultado.setImageResource(R.drawable.doh);
                num_intentos+=1; //maximo 3 fallos por pregunta
                if(num_intentos==3){
                    perder_corazon();
                    if(num_corazones==0 || num_pregunta_actual==10){ //finaliza el juego al perder los 3 corazones
                        mostrar_resultado(0);
                    }else {
                        num_intentos = 0;
                        siguientePregunta(); //se salta la pregunta inten
                    }
                }
                sonar(1);
            }
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

    public void pintar_estrella()
    {
        int n_fil=0;
        int n_col=total_estrellas;
        if(total_estrellas>=5){ /*desde las sexta a la decima pregunta*/
            n_fil=1;
            n_col-=5;
        }
        TableRow fila = (TableRow)estrellas.getChildAt(n_fil); // Se obtiene la fila X
        ImageView estrella = (ImageView)fila.getChildAt(n_col); // Se obtiene el elemento de la fila
        estrella.setVisibility(View.VISIBLE);
        total_estrellas+=1;
    }

    public void mostrar_resultado(int tipo) {
        if(tipo==0){  /*pierde el juego*/
            show_dialog_result(0);
        }else{   /*gana el juego*/
            show_dialog_result(1);
        }

    }

    public void perder_corazon(){
        TableRow fila = (TableRow) vidas.getChildAt(0); // Se obtiene la fila X
        ImageView corazon = (ImageView) fila.getChildAt(num_corazones -1); // Se obtiene el elemento de la fila
        corazon.setVisibility(View.INVISIBLE);
        num_corazones -=1;
    }

    public void show_dialog_help()
    {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialog_layout = inflater.inflate(R.layout.dialog_help_evaluate, null);
        dontShowAgain = (CheckBox) dialog_layout.findViewById(R.id.cb_skip);


        txt_indicacion = (TextView) dialog_layout.findViewById(R.id.txt_indicacion_evalua);
        txt_indicacion_titulo = (TextView) dialog_layout.findViewById(R.id.txt_indicacion_titulo);

        txt_indicacion.setTypeface(typeface);
        txt_indicacion_titulo.setTypeface(typeface);

        AlertDialog.Builder db = new AlertDialog.Builder(EvaluateActivity.this);
        db.setView(dialog_layout);
        /*db.setPositiveButton("ENTENDIDO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                String checkBoxResult = "NOT checked";
                if (dontShowAgain.isChecked())
                    checkBoxResult = "checked";

                SharedPreferences settings = getSharedPreferences(PREFS_NAME2, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("skipMessageEvaluate", checkBoxResult);
                editor.commit();
                return;
            }
        });*/
        dontShowAgain.setTypeface(typeface);
        dontShowAgain.setVisibility(View.VISIBLE);
        /*
        SharedPreferences settings = getSharedPreferences(PREFS_NAME2, 0);
        String skipMessage = settings.getString("skipMessageEvaluate", "NOT checked");
        if (!skipMessage.equals("checked"))*/
            db.show();
    }
    public void show_dialog_result(int opc)
    {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialog_result = inflater.inflate(R.layout.dialog_result, null);

        ImageView titulo = (ImageView) dialog_result.findViewById(R.id.titulo_resultado);
        TextView contenido = (TextView) dialog_result.findViewById(R.id.txt_resultado);
        ImageView imagen = (ImageView) dialog_result.findViewById(R.id.img_resultado);
        TableLayout estrellas_dialog = (TableLayout) dialog_result.findViewById(R.id.tabla_estrellas_dialog);

        Button btn_jugar = (Button) dialog_result.findViewById(R.id.btn_jugar);
        Button btn_salir = (Button) dialog_result.findViewById(R.id.btn_salir);

        if(opc == 1) //Gano
        {
            titulo.setImageResource(R.drawable.excelente);
            contenido.setText(R.string.mensaje_final_bien);
            contenido.setTypeface(typeface);
            imagen.setImageResource(R.drawable.has_ganado);

            for (int i=0;i<2;i++){
                for(int j=0;j<5;j++){
                    TableRow fila = (TableRow)estrellas_dialog.getChildAt(i); // Se obtiene la fila X
                    ImageView estrella = (ImageView)fila.getChildAt(j); // Se obtiene el elemento de la fila
                    estrella.setVisibility(View.VISIBLE);
                }
            }
        }
        else { //Perdio
            titulo.setImageResource(R.drawable.sigue);
            contenido.setText(R.string.mensaje_final_mal);
            contenido.setTypeface(typeface);
            imagen.setImageResource(R.drawable.has_perdido);
            int cont = 0;
            int band = 0;
            for (int i=0;i<2;i++){
                for(int j=0;j<5;j++){
                    if(cont<total_estrellas){
                        TableRow fila = (TableRow)estrellas_dialog.getChildAt(i); // Se obtiene la fila X
                        ImageView estrella = (ImageView)fila.getChildAt(j); // Se obtiene el elemento de la fila
                        estrella.setVisibility(View.VISIBLE);
                        cont++;
                    }else
                        band=1;
                }
                if(band==1)
                    break;
            }
        }

        btn_jugar.setTextColor(Color.parseColor("#007286"));
        btn_jugar.setTypeface(typeface);
        btn_salir.setTextColor(Color.parseColor("#007286"));
        btn_salir.setTypeface(typeface);

        btn_jugar.setOnClickListener(this);
        btn_salir.setOnClickListener(this);

        AlertDialog.Builder db = new AlertDialog.Builder(EvaluateActivity.this);
        db.setView(dialog_result);
        db.show();


    }

    @Override
    public void onAnimationStart(View view) {

    }

    @Override
    public void onAnimationEnd(View view) {
        /*if(state ==0) {
            ViewCompat.animate(img_resultado).setStartDelay(100).setDuration(1000).alpha(0).setListener(this).start();
            state=1;
        }*/
    }

    @Override
    public void onAnimationCancel(View view) {

    }
}
