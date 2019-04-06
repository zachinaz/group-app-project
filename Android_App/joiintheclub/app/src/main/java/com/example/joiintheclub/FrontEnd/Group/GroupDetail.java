package com.example.joiintheclub.FrontEnd.Group;

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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.joiintheclub.FrontEnd.Setting.SettingMain;
import com.example.joiintheclub.FrontEnd.UserProfile.UserProfileMain;
import com.example.joiintheclub.R;

@SuppressLint("Registered")
public class GroupDetail extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    Button group_detail_back_btn;
    Button announcement_btn;
    Button poll_btn;
    Button event_btn;
    Button inbox_btn;
    ImageButton groupProfileBtn;
    Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);

        recyclerView = findViewById(R.id.group_detail_recycle_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new GroupDetailRecycleAdapter();
        recyclerView.setAdapter(adapter);


         group_detail_back_btn = findViewById(R.id.group_detail_back_btn);
         announcement_btn = findViewById(R.id.announcement_btn);
         poll_btn = findViewById(R.id.poll_btn);
         event_btn = findViewById(R.id.event_btn);
         inbox_btn = findViewById(R.id.inbox_btn);
         groupProfileBtn = findViewById(R.id.group_profile_pic);
         mDialog = new Dialog(this);


         group_detail_back_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });

        announcement_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAnnouncementDialog();
            }
        });

        poll_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        event_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        inbox_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        groupProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.GDbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.group_detail_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.groupDetailNav);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void openAnnouncementDialog() {

        final AutoCompleteTextView title;
        final AutoCompleteTextView announcement_content;
        ImageButton close_btn;
        ImageView announce;


        mDialog.setContentView(R.layout.activity_announcement);
        mDialog.show();

        title = mDialog.findViewById(R.id.Atitle);
        announcement_content = mDialog.findViewById(R.id.Acontent);
        close_btn = mDialog.findViewById(R.id.announce_close_icon);
        announce = mDialog.findViewById(R.id.announce_publish);

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        announce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stitle = title.getText().toString();
                String Content = announcement_content.getText().toString();
                String n;
            }
        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.group_detail_drawer);
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
            Intent intent = new Intent(GroupDetail.this, UserProfileMain.class);
            startActivity(intent);
        } else if (id == R.id.search) {
            Intent intent = new Intent(GroupDetail.this, com.example.joiintheclub.FrontEnd.SearchGroup.SearchMain.class);
            startActivity(intent);

        } else if (id == R.id.club) {
            Intent intent = new Intent(GroupDetail.this, GroupMain.class);
            startActivity(intent);

        } else if (id == R.id.setting) {
            Intent intent = new Intent(GroupDetail.this, SettingMain.class);
            startActivity(intent);

        } else if (id == R.id.logout) {

        }

        DrawerLayout drawer = findViewById(R.id.group_detail_drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
