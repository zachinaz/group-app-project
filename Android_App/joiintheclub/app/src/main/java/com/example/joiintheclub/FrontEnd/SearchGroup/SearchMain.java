package com.example.joiintheclub.FrontEnd.SearchGroup;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.example.joiintheclub.R;

public class SearchMain extends Activity {

    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mToggle;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);
        mdrawerLayout = (DrawerLayout) findViewById(R.id.search_navigation_bar);
        mToggle = new ActionBarDrawerToggle(this,mdrawerLayout,R.string.create,R.string.error_field_required);
        mdrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
      //  Objects.requireNonNull(getActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
