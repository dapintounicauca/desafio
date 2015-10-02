package com.unicauca.moviles.alimentaahomero;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class LearnActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {

    LinearLayout contenedor_fr;
    RelativeLayout contenedor_sd;
    ImageView manzana, naranja, mango;
    TextView guia;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        manzana = (ImageView) findViewById(R.id.img_manzana);
        naranja = (ImageView) findViewById(R.id.img_naranja);
        mango = (ImageView) findViewById(R.id.img_mango);

        contenedor_fr = (LinearLayout) findViewById(R.id.contenedor_frutas);
        contenedor_sd = (RelativeLayout) findViewById(R.id.contenedor_organos);
        guia = (TextView) findViewById(R.id.txt_guia);

        manzana.setOnTouchListener(this);
        naranja.setOnTouchListener(this);
        mango.setOnTouchListener(this);

        contenedor_fr.setOnDragListener(this);
        contenedor_sd.setOnDragListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(null, shadowBuilder, v, 0);
            v.setVisibility(View.INVISIBLE);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {

        int action = event.getAction();
        View view = (View) event.getLocalState();

        switch (action)
        {
            case DragEvent.ACTION_DRAG_STARTED:

                switch (view.getId()) {
                    case R.id.img_manzana:
                        guia.setText("Suelta la manzana en un órgano del sistema digestivo.");
                        break;
                    case R.id.img_naranja:
                        guia.setText("Suelta la naranja en un órgano del sistema digestivo.");
                        break;
                    case R.id.img_mango:
                        guia.setText("Suelta el mango en un órgano del sistema digestivo.");
                        break;
                    default:
                        break;
                }
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;

            case DragEvent.ACTION_DROP:
                ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);
                RelativeLayout container = (RelativeLayout) v;
                container.addView(view);
                view.setVisibility(View.VISIBLE);

                if(container.getId()==contenedor_sd.getId()) //Si arrastró la fruta al sistema digestivo
                {
                    //Toca direccionar a otro activity pero por ahora mostremos un mensaje
                    //Hay que hacer más if.. para saber a que organo arrastró la fruta.
                    guia.setText("La boca se encarga de masticar y triturar el alimento.");
                }

                if(container.getId()==contenedor_fr.getId())//La fruta la arrastró al contenedor de frutas
                {
                    guia.setText("Arrastra y suelta una de las frutas en alguno de los órganos del sistema digestivo de Homero.");
                }
                break;

            case DragEvent.ACTION_DRAG_ENDED:
                if (!event.getResult()) {
                    view.setVisibility(View.VISIBLE);
                    ViewGroup owner2 = (ViewGroup) view.getParent();
                    owner2.removeView(view);
                    RelativeLayout container2 = (RelativeLayout) v;
                    container2.addView(view);
                    if(container2.getId()==contenedor_sd.getId())
                        guia.setText("La boca se encarga de masticar y triturar el alimento.");
                    if(container2.getId()==contenedor_fr.getId())
                        guia.setText("Arrastra y suelta una de las frutas en alguno de los órganos del sistema digestivo de Homero.");
                }
                break;
            default:
                break;
        }
        return true;
    }
}