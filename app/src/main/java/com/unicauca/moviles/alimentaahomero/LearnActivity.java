package com.unicauca.moviles.alimentaahomero;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class LearnActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener, View.OnClickListener {

    LinearLayout contenedor_fr;
    ImageView manzana, naranja, mango;
    ImageView img_info;
    LinearLayout boca, esofago, estomago, higado, pancreas, delgado, grueso1, grueso2, grueso3, grueso4;
    TextView titulo_info, contenido_info;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_learn);

        titulo_info = (TextView) findViewById(R.id.titulo_info);
        contenido_info = (TextView) findViewById(R.id.contenido_info);
        img_info = (ImageView) findViewById(R.id.img_info);

        boca = (LinearLayout) findViewById(R.id.pt_boca);
        esofago = (LinearLayout) findViewById(R.id.pt_esofago);
        estomago = (LinearLayout) findViewById(R.id.pt_estomago);
        higado = (LinearLayout) findViewById(R.id.pt_higado);
        pancreas = (LinearLayout) findViewById(R.id.pt_pancreas);
        delgado = (LinearLayout) findViewById(R.id.pt_delgado);
        grueso1 = (LinearLayout) findViewById(R.id.pt_grueso_1);
        grueso2 = (LinearLayout) findViewById(R.id.pt_grueso_2);
        grueso3 = (LinearLayout) findViewById(R.id.pt_grueso_3);
        grueso4 = (LinearLayout) findViewById(R.id.pt_grueso_4);

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

        manzana = (ImageView) findViewById(R.id.img_manzana);
        naranja = (ImageView) findViewById(R.id.img_naranja);
        mango = (ImageView) findViewById(R.id.img_uva);
        manzana.setOnTouchListener(this);
        naranja.setOnTouchListener(this);
        mango.setOnTouchListener(this);

        contenedor_fr = (LinearLayout) findViewById(R.id.contenedor_frutas);
        contenedor_fr.setOnDragListener(this);

        boca.setOnDragListener(this);
        esofago.setOnDragListener(this);
        estomago.setOnDragListener(this);
        higado.setOnDragListener(this);
        pancreas.setOnDragListener(this);
        delgado.setOnDragListener(this);
        grueso1.setOnDragListener(this);
        grueso2.setOnDragListener(this);
        grueso3.setOnDragListener(this);
        grueso4.setOnDragListener(this);
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
                titulo_info.setText("");
                img_info.setImageResource(R.drawable.homer_info);
                switch (view.getId()) {
                    case R.id.img_manzana:
                        contenido_info.setText(R.string.drag_manzana);
                        break;
                    case R.id.img_naranja:
                        contenido_info.setText(R.string.drag_naranja);
                        break;
                    case R.id.img_uva:
                        contenido_info.setText(R.string.drag_uva);
                        break;
                    default:
                        break;
                }
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                /*ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);*/

                LinearLayout container = (LinearLayout) v;
                //container.addView(view);
                view.setVisibility(View.VISIBLE);

                Intent intent = new Intent(this, ProcessActivity.class);

                switch (container.getId())
                {
                    case R.id.pt_boca:
                        ProcessActivity.ORGANO = "Boca";
                        startActivity(intent);
                        break;
                    case R.id.pt_esofago:
                        ProcessActivity.ORGANO = "Esofago";
                        startActivity(intent);
                        break;
                    case R.id.pt_estomago:
                        ProcessActivity.ORGANO = "Estomago";
                        startActivity(intent);
                        break;
                    case R.id.pt_higado:
                        ProcessActivity.ORGANO = "Higado";
                        startActivity(intent);
                        break;
                    case R.id.pt_pancreas:
                        ProcessActivity.ORGANO = "Pancreas";
                        startActivity(intent);
                        break;
                    case R.id.pt_delgado:
                        ProcessActivity.ORGANO = "Delgado";
                        startActivity(intent);
                        break;
                    case R.id.pt_grueso_1:
                        ProcessActivity.ORGANO = "Grueso";
                        startActivity(intent);
                        break;
                    case R.id.pt_grueso_2:
                        ProcessActivity.ORGANO = "Grueso";
                        startActivity(intent);
                        break;
                    case R.id.pt_grueso_3:
                        ProcessActivity.ORGANO = "Grueso";
                        startActivity(intent);
                        break;
                    case R.id.pt_grueso_4:
                        ProcessActivity.ORGANO = "Grueso";
                        startActivity(intent);
                        break;
                    case R.id.contenedor_frutas:
                        titulo_info.setText(R.string.titulo_info_aprender);
                        contenido_info.setText(R.string.contenido_info_aprender);
                        break;
                }
                break;

            case DragEvent.ACTION_DRAG_ENDED:
                if (!event.getResult()) {
                    /*ViewGroup owner2 = (ViewGroup) view.getParent();
                    owner2.removeView(view);*/
                    LinearLayout container2 = (LinearLayout) v;
                    //container2.addView(view);
                    view.setVisibility(View.VISIBLE);

                    switch (container2.getId())
                    {
                        case R.id.pt_boca:
                            contenido_info.setText("La boca se encarga de masticar y triturar el alimento.");
                            break;
                        case R.id.pt_esofago:
                            contenido_info.setText("El esofago bla, bla...");
                            break;
                        case R.id.contenedor_frutas:
                            titulo_info.setText(R.string.titulo_info_aprender);
                            contenido_info.setText(R.string.contenido_info_aprender);
                            break;
                    }
                }
                break;
            default:
                break;
        }
        Log.i("Info:","Event_drag");
        return true;
    }

    @Override
    public void onClick(View v)
    {
        String organos[] = getResources().getStringArray(R.array.organos);
        String info_organos[] = getResources().getStringArray(R.array.info_organos);

        switch (v.getId())
        {
            case R.id.pt_boca:
                titulo_info.setText(organos[0]);
                contenido_info.setText(info_organos[0]);
                img_info.setImageResource(R.drawable.boca);
                break;
            case R.id.pt_esofago:
                titulo_info.setText(organos[2]);
                contenido_info.setText(info_organos[2]);
                img_info.setImageResource(R.drawable.esofago);
                break;
            case R.id.pt_estomago:
                titulo_info.setText(organos[3]);
                contenido_info.setText(info_organos[3]);
                img_info.setImageResource(R.drawable.estomago);
                break;
            case R.id.pt_delgado:
                titulo_info.setText(organos[4]);
                contenido_info.setText(info_organos[4]);
                img_info.setImageResource(R.drawable.delgado);
                break;
            case R.id.pt_higado:
                titulo_info.setText(organos[5]);
                contenido_info.setText(info_organos[5]);
                img_info.setImageResource(R.drawable.higado);
                break;
            case R.id.pt_pancreas:
                titulo_info.setText(organos[6]);
                contenido_info.setText(info_organos[6]);
                img_info.setImageResource(R.drawable.pancreas);
                break;
            case R.id.pt_grueso_1:
                titulo_info.setText(organos[7]);
                contenido_info.setText(info_organos[7]);
                img_info.setImageResource(R.drawable.grueso);
                break;
            case R.id.pt_grueso_2:
                titulo_info.setText(organos[7]);
                contenido_info.setText(info_organos[7]);
                img_info.setImageResource(R.drawable.grueso);
                break;
            case R.id.pt_grueso_3:
                titulo_info.setText(organos[7]);
                contenido_info.setText(info_organos[7]);
                img_info.setImageResource(R.drawable.grueso);
                break;
            case R.id.pt_grueso_4:
                titulo_info.setText(organos[7]);
                contenido_info.setText(info_organos[7]);
                img_info.setImageResource(R.drawable.grueso);
                break;
        }
    }
}