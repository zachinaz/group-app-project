package com.example.joiintheclub.FrontEnd.UserProfile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.joiintheclub.BackEnd.User;
import com.example.joiintheclub.FrontEnd.Group.GroupMain;
import com.example.joiintheclub.FrontEnd.SearchGroup.SearchMain;
import com.example.joiintheclub.FrontEnd.Setting.SettingMain;
import com.example.joiintheclub.R;

@SuppressLint("Registered")
public class UserProfileMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView profileEmail;
    TextView profileFirstName;
    TextView profileLastName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.PTbar);
        setSupportActionBar(toolbar);


        profileEmail = findViewById(R.id.profileEmail);
        profileFirstName = findViewById(R.id.profileFirstName);
        profileLastName = findViewById(R.id.profileLastName);


        setProfileData();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.profile_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.profileNav);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setProfileData() {

        String[] UserData = new String[6];

        UserData = User.userInformation("12321");

        profileEmail.setText(UserData[3]);
        profileLastName.setText(UserData[2]);
        profileFirstName.setText(UserData[1]);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.profile_drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //@SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile) {
            Intent intent = new Intent(com.example.joiintheclub.FrontEnd.UserProfile.UserProfileMain.this, UserProfileMain.class);
            startActivity(intent);
        } else if (id == R.id.search) {
            Intent intent = new Intent(com.example.joiintheclub.FrontEnd.UserProfile.UserProfileMain.this, SearchMain.class);
            startActivity(intent);

        } else if (id == R.id.club) {
            Intent intent = new Intent(com.example.joiintheclub.FrontEnd.UserProfile.UserProfileMain.this, GroupMain.class);
            startActivity(intent);

        } else if (id == R.id.setting) {
            Intent intent = new Intent(com.example.joiintheclub.FrontEnd.UserProfile.UserProfileMain.this, SettingMain.class);
            startActivity(intent);

        } else if (id == R.id.logout) {

        }

       // DrawerLayout drawer =  findViewById(R.id.profileNav);
       // drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
