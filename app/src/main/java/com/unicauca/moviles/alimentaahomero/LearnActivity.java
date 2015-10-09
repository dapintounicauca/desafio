package com.unicauca.moviles.alimentaahomero;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class LearnActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener, View.OnClickListener {

    public static final String PREFS_NAME = "MyPrefsFile1";
    ImageView manzana, naranja, uvas, img_info, img_ayuda;
    LinearLayout contenedor_fr, boca, esofago, estomago, higado, pancreas, delgado, grueso, ano;
    TextView titulo_info, contenido_info;
    CheckBox dontShowAgain;



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
        grueso = (LinearLayout) findViewById(R.id.pt_grueso);
        ano = (LinearLayout) findViewById(R.id.pt_ano);

        boca.setOnClickListener(this);
        esofago.setOnClickListener(this);
        estomago.setOnClickListener(this);
        higado.setOnClickListener(this);
        pancreas.setOnClickListener(this);
        delgado.setOnClickListener(this);
        grueso.setOnClickListener(this);
        ano.setOnClickListener(this);

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

                LinearLayout container = (LinearLayout) v;
                view.setVisibility(View.VISIBLE);

                switch (container.getId())
                {
                    case R.id.pt_boca:
                        show_dialog(v, "Boca");
                        break;
                    case R.id.pt_esofago:
                        show_dialog(v, "Esofago");
                        break;
                    case R.id.pt_estomago:
                        show_dialog(v, "Estomago");
                        break;
                    case R.id.pt_higado:
                        show_dialog(v, "Higado");
                        break;
                    case R.id.pt_pancreas:
                        show_dialog(v, "Pancreas");
                        break;
                    case R.id.pt_delgado:
                        show_dialog(v, "Delgado");
                        break;
                    case R.id.pt_grueso:
                        show_dialog(v, "Grueso");
                        break;
                    case R.id.pt_ano:
                        show_dialog(v, "Ano");
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
                dontShowAgain = (CheckBox) dialog_layout.findViewById(R.id.cb_skip);
                AlertDialog.Builder db = new AlertDialog.Builder(LearnActivity.this);
                db.setView(dialog_layout);
                db.setTitle("Indicaciones");
                db.setPositiveButton("ENTENDIDO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dontShowAgain.setVisibility(View.INVISIBLE);
                db.show();
                break;
        }
    }

    public void show_dialog(View v, String name)
    {
        FragmentManager manager = getFragmentManager();
        ContentActivity dialog = new ContentActivity();
        dialog.init(name);
        dialog.show(manager,"ContentActivity");
    }

    public void show_dialog_help()
    {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialog_layout = inflater.inflate(R.layout.dialog_help_learn, null);
        dontShowAgain = (CheckBox) dialog_layout.findViewById(R.id.cb_skip);
        AlertDialog.Builder db = new AlertDialog.Builder(LearnActivity.this);
        db.setView(dialog_layout);
        db.setTitle("Indicaciones");
        db.setPositiveButton("ENTENDIDO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                String checkBoxResult = "NOT checked";
                if (dontShowAgain.isChecked())
                    checkBoxResult = "checked";

                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("skipMessageLearn", checkBoxResult);
                editor.commit();
                return;
            }
        });
        dontShowAgain.setVisibility(View.VISIBLE);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String skipMessage = settings.getString("skipMessageLearn", "NOT checked");
        if (!skipMessage.equals("checked"))
            db.show();
    }
}