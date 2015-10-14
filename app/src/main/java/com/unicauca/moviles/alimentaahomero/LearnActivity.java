package com.unicauca.moviles.alimentaahomero;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class LearnActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener, View.OnClickListener {

    public static final String PREFS_NAME = "MyPrefsFile1";
    ImageView manzana, naranja, uvas, img_info, img_ayuda;
    LinearLayout contenedor_fr, boca, esofago, estomago, higado, pancreas, delgado, grueso, ano;
    TextView titulo_info, contenido_info, txt_frutas;
    Typeface typeface;
    ImageView img_home;
    TextView txt_intro_indicaciones,txt_indicacion1,txt_indicacion2, txt_intro_video;
    CheckBox dontShowAgain;
    Button btn_entendido;
    VideoView video;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_learn);

        titulo_info = (TextView) findViewById(R.id.titulo_info);
        contenido_info = (TextView) findViewById(R.id.contenido_info);
        img_info = (ImageView) findViewById(R.id.img_info);
        txt_frutas = (TextView) findViewById(R.id.txt_frutas);

        boca = (LinearLayout) findViewById(R.id.pt_boca);
        esofago = (LinearLayout) findViewById(R.id.pt_esofago);
        estomago = (LinearLayout) findViewById(R.id.pt_estomago);
        higado = (LinearLayout) findViewById(R.id.pt_higado);
        pancreas = (LinearLayout) findViewById(R.id.pt_pancreas);
        delgado = (LinearLayout) findViewById(R.id.pt_delgado);
        grueso = (LinearLayout) findViewById(R.id.pt_grueso);
        ano = (LinearLayout) findViewById(R.id.pt_ano);

        img_home = (ImageView) findViewById(R.id.img_home);

        boca.setOnClickListener(this);
        esofago.setOnClickListener(this);
        estomago.setOnClickListener(this);
        higado.setOnClickListener(this);
        pancreas.setOnClickListener(this);
        delgado.setOnClickListener(this);
        grueso.setOnClickListener(this);
        ano.setOnClickListener(this);
        img_home.setOnClickListener(this);

        boca.setOnDragListener(this);
        esofago.setOnDragListener(this);
        estomago.setOnDragListener(this);
        higado.setOnDragListener(this);
        pancreas.setOnDragListener(this);
        delgado.setOnDragListener(this);
        grueso.setOnDragListener(this);
        ano.setOnDragListener(this);

        manzana = (ImageView) findViewById(R.id.img_manzana);
        naranja = (ImageView) findViewById(R.id.img_naranja);
        uvas = (ImageView) findViewById(R.id.img_uva);
        manzana.setOnTouchListener(this);
        naranja.setOnTouchListener(this);
        uvas.setOnTouchListener(this);

        contenedor_fr = (LinearLayout) findViewById(R.id.contenedor_frutas);
        contenedor_fr.setOnDragListener(this);

        typeface = Typeface.createFromAsset(getAssets(),"fonts/BradBunR.ttf");
        txt_frutas.setTypeface(typeface);
        contenido_info.setTypeface(typeface);
        titulo_info.setTypeface(typeface);

        show_dialog_help();

        img_ayuda = (ImageView) findViewById(R.id.img_ayuda);
        img_ayuda.setOnClickListener(this);
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
                titulo_info.setText("hum...y ahora qué?");
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

                LinearLayout container = (LinearLayout) v;
                view.setVisibility(View.VISIBLE);

                switch (container.getId())
                {
                    case R.id.pt_boca:
                        show_dialog_video("Boca");
                        break;
                    case R.id.pt_esofago:
                        show_dialog_video("Esofago");
                        break;
                    case R.id.pt_estomago:
                        show_dialog_video("Estomago");
                        break;
                    case R.id.pt_higado:
                        show_dialog_video("Higado");
                        break;
                    case R.id.pt_pancreas:
                        show_dialog_video("Pancreas");
                        break;
                    case R.id.pt_delgado:
                        show_dialog_video("Delgado");
                        break;
                    case R.id.pt_grueso:
                        show_dialog_video("Grueso");
                        break;
                    case R.id.pt_ano:
                        show_dialog_video("Ano");
                        break;
                    case R.id.contenedor_frutas:
                        titulo_info.setText(R.string.titulo_info_aprender);
                        contenido_info.setText(R.string.contenido_info_aprender);
                        break;
                }
                break;

            case DragEvent.ACTION_DRAG_ENDED:
                if (!event.getResult()) {
                    view.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
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
                titulo_info.setText(organos[1]);
                contenido_info.setText(info_organos[1]);
                img_info.setImageResource(R.drawable.esofago);
                break;
            case R.id.pt_estomago:
                titulo_info.setText(organos[2]);
                contenido_info.setText(info_organos[2]);
                img_info.setImageResource(R.drawable.estomago);
                break;
            case R.id.pt_delgado:
                titulo_info.setText(organos[3]);
                contenido_info.setText(info_organos[3]);
                img_info.setImageResource(R.drawable.delgado);
                break;
            case R.id.pt_higado:
                titulo_info.setText(organos[4]);
                contenido_info.setText(info_organos[4]);
                img_info.setImageResource(R.drawable.higado);
                break;
            case R.id.pt_pancreas:
                titulo_info.setText(organos[5]);
                contenido_info.setText(info_organos[5]);
                img_info.setImageResource(R.drawable.pancreas);
                break;
            case R.id.pt_grueso:
                titulo_info.setText(organos[6]);
                contenido_info.setText(info_organos[6]);
                img_info.setImageResource(R.drawable.grueso);
                break;
            case R.id.pt_ano:
                titulo_info.setText(organos[7]);
                contenido_info.setText(info_organos[7]);
                img_info.setImageResource(R.drawable.ano);
                break;
            case R.id.img_ayuda:
                LayoutInflater inflater = LayoutInflater.from(this);
                View dialog_layout = inflater.inflate(R.layout.dialog_help_learn, null);

                AlertDialog.Builder db = new AlertDialog.Builder(LearnActivity.this);
                db.setView(dialog_layout);

                txt_intro_indicaciones = (TextView) dialog_layout.findViewById(R.id.txt_intro_indicaciones);
                txt_indicacion1 = (TextView) dialog_layout.findViewById(R.id.txt_indicacion1);
                txt_indicacion2 = (TextView) dialog_layout.findViewById(R.id.txt_indicacion2);
                dontShowAgain = (CheckBox) dialog_layout.findViewById(R.id.cb_skip);
                btn_entendido = (Button) dialog_layout.findViewById(R.id.btn_entendido);

                txt_intro_indicaciones.setTypeface(typeface);
                txt_indicacion1.setTypeface(typeface);
                txt_indicacion2.setTypeface(typeface);
                btn_entendido.setTypeface(typeface);
                dontShowAgain.setTypeface(typeface);

                dontShowAgain.setVisibility(View.INVISIBLE);

                final AlertDialog show = db.show();
                btn_entendido.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        show.dismiss();
                    }
                });
                break;
            case R.id.img_home:
                this.finish();
                break;
        }
    }

    public void show_dialog_help()
    {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialog_layout = inflater.inflate(R.layout.dialog_help_learn, null);
        AlertDialog.Builder db = new AlertDialog.Builder(LearnActivity.this);
        db.setView(dialog_layout);

        txt_intro_indicaciones = (TextView) dialog_layout.findViewById(R.id.txt_intro_indicaciones);
        txt_indicacion1 = (TextView) dialog_layout.findViewById(R.id.txt_indicacion1);
        txt_indicacion2 = (TextView) dialog_layout.findViewById(R.id.txt_indicacion2);
        btn_entendido = (Button) dialog_layout.findViewById(R.id.btn_entendido);
        dontShowAgain = (CheckBox) dialog_layout.findViewById(R.id.cb_skip);

        txt_intro_indicaciones.setTypeface(typeface);
        txt_indicacion1.setTypeface(typeface);
        txt_indicacion2.setTypeface(typeface);
        dontShowAgain.setTypeface(typeface);
        btn_entendido.setTypeface(typeface);

        dontShowAgain.setVisibility(View.VISIBLE);

        final AlertDialog show = db.show();
        btn_entendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });
    }

    public void show_dialog_video(String organo) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialog_layout = inflater.inflate(R.layout.dialog_video_learn, null);
        AlertDialog.Builder db = new AlertDialog.Builder(LearnActivity.this);
        db.setView(dialog_layout);

        txt_intro_video = (TextView) dialog_layout.findViewById(R.id.txt_intro_video);
        btn_entendido = (Button) dialog_layout.findViewById(R.id.btn_entendido);

        txt_intro_video.setTypeface(typeface);
        btn_entendido.setTypeface(typeface);

        txt_intro_video.setText(organo);

        video = (VideoView) dialog_layout.findViewById(R.id.video);
        Uri path = null;
        String url = "android.resource://com.unicauca.moviles.alimentaahomero/raw/";
        switch (organo)
        {
            case "Boca":
                path = Uri.parse(url + R.raw.boca);
                break;
            case "Esofago":
                path = Uri.parse(url + R.raw.esofago);
                break;
            case "Estomago":
                path = Uri.parse(url + R.raw.estomago);
                break;
            case "Higado":
                path = Uri.parse(url + R.raw.higado);
                break;
            case "Pancreas":
                path = Uri.parse(url + R.raw.pancreas);
                break;
            case "Delgado":
                path = Uri.parse(url + R.raw.delgado);
                break;
            case "Grueso":
                path = Uri.parse(url + R.raw.grueso);
                break;
            case "Ano":
                path = Uri.parse(url + R.raw.ano);
                break;
        }

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(video);
        video.setMediaController(mediaController);
        video.setVideoURI(path);
        video.requestFocus();
        video.start();

        final AlertDialog show = db.show();
        btn_entendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });
    }
}