package com.example.joiintheclub.FrontEnd.Setting;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.joiintheclub.FrontEnd.Group.GroupMain;
import com.example.joiintheclub.FrontEnd.Init.LoginActivity;
import com.example.joiintheclub.FrontEnd.UserProfile.UserProfileMain;
import com.example.joiintheclub.R;

@SuppressLint("Registered")
public class SettingMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.Sbar);
        setSupportActionBar(toolbar);
        mDialog = new Dialog(this);

        Button Feedbcak;
        Button AboutUs;

        Feedbcak = findViewById(R.id.FeedbackBtn);
        AboutUs = findViewById(R.id.AboutUsBtn);


        Feedbcak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFeedbackDlg();

            }
        });

        AboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openAboutDlg();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.setting_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.settingNav);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.setting_drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //@SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile) {
            Intent intent = new Intent(com.example.joiintheclub.FrontEnd.Setting.SettingMain.this, UserProfileMain.class);
            startActivity(intent);
        } else if (id == R.id.search) {
            Intent intent = new Intent(com.example.joiintheclub.FrontEnd.Setting.SettingMain.this, com.example.joiintheclub.FrontEnd.SearchGroup.SearchMain.class);
            startActivity(intent);

        } else if (id == R.id.club) {
            Intent intent = new Intent(com.example.joiintheclub.FrontEnd.Setting.SettingMain.this, GroupMain.class);
            startActivity(intent);

        } else if (id == R.id.setting) {
            Intent intent = new Intent(com.example.joiintheclub.FrontEnd.Setting.SettingMain.this, SettingMain.class);
            startActivity(intent);

        } else if (id == R.id.logout) {

            Intent intent = new Intent(com.example.joiintheclub.FrontEnd.Setting.SettingMain.this, LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.setting_drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openFeedbackDlg() {

        final AutoCompleteTextView title;
        final AutoCompleteTextView event_content;
        ImageButton close_btn;
        ImageView event;


        mDialog.setContentView(R.layout.activity_feedbcak);
        mDialog.show();

        title = mDialog.findViewById(R.id.Btitle);
        event_content = mDialog.findViewById(R.id.Bcontent);
        close_btn = mDialog.findViewById(R.id.Feedback_close_icon);
        event = mDialog.findViewById(R.id.Feedback_publish);

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stitle = title.getText().toString();
                String Content = event_content.getText().toString();
                String n;
            }
        });

    }

    private void openAboutDlg() {

        ImageButton close_btn;

        mDialog.setContentView(R.layout.activity_about_us);
        mDialog.show();

        close_btn = mDialog.findViewById(R.id.Feedback_close_icon);

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

    }
}