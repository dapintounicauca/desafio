package com.unicauca.moviles.alimentaahomero;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ContentActivity extends DialogFragment {

    VideoView video;
    //Button btn_video;
    int ms_pauso;
    Context ctx;
    String fruta;

    public void init(String fruta)
    {
        this.fruta = fruta;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle(fruta);

        View view = inflater.inflate(R.layout.content_activity, null);
        video = (VideoView) view.findViewById(R.id.video);
        Uri path = null;
        String url = "android.resource://com.unicauca.moviles.alimentaahomero/raw/";
        switch (fruta)
        {
            case "Boca":
                path = Uri.parse(url + R.raw.boca);
                break;
            case "Esofago":
                path = Uri.parse(url + R.raw.esofago);
                break;
            case "Estomago":
                path = Uri.parse(url + R.raw.esofago);
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

        video.setVideoURI(path);
        video.requestFocus();
        video.start();

        return view;
    }
}
