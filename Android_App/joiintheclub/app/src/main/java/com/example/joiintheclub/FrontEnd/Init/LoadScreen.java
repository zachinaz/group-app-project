package com.example.joiintheclub.FrontEnd.Init;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.joiintheclub.R;

public class LoadScreen extends AppCompatActivity {

    ProgressBar progressBar;
    TextView loadText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadscreen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.progressBar);
        loadText = findViewById(R.id.loadText);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        progressAnimation();
    }

    private void progressAnimation() {
        PrograssBarAnimation anim = new PrograssBarAnimation(this,progressBar,loadText,0f,100f);
        anim.setDuration(8000);
        progressBar.setAnimation(anim);
    }
}