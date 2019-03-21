package com.example.joiintheclub.FrontEnd.Init;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PrograssBarAnimation extends Animation {

    private Context context;
    private ProgressBar prograssBar;
    TextView textView;
    private float from;
    private float to;

    public PrograssBarAnimation(Context context, ProgressBar prograssBar, TextView textView, float from, float to)
    {
        this.context = context;
        this.prograssBar = prograssBar;
        this.textView = textView;
        this.from = from;
        this.to = to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from)*interpolatedTime;
        prograssBar.setProgress((int) value);
        textView.setText((int)value+" %");

        if(value == to)
            context.startActivity(new Intent(context, LoginActivity.class));
    }
}
