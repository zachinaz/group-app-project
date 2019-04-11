package com.example.joiintheclub.FrontEnd.Group;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.joiintheclub.R;

public class vote extends AppCompatDialogFragment {

    public  AutoCompleteTextView title;
    public AutoCompleteTextView vote_content;
    public ImageButton close_btn;
    public ImageView announce;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        @SuppressLint("InflateParams") View view =inflater.inflate(R.layout.activity_vote,null);

        builder.setView(view)
                .setTitle("vote")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("vote", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        title = view.findViewById(R.id.vote_title);
        vote_content = view.findViewById(R.id.vote_content);
        close_btn = view.findViewById(R.id.vote_close_icon);
        announce = view.findViewById(R.id.vote_publish);


        close_btn.setOnContextClickListener(new View.OnContextClickListener() {
            @Override
            public boolean onContextClick(View v) {


                return false;
            }
        });

        announce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stitle = title.getText().toString();
                String Content = vote_content.getText().toString();
                String n;

            }
        });


        return builder.create();
    }


}
