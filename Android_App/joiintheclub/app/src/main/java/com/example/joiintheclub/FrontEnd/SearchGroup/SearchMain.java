package com.example.joiintheclub.FrontEnd.SearchGroup;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.joiintheclub.BackEnd.Group;
import com.example.joiintheclub.FrontEnd.Group.GroupMain;
import com.example.joiintheclub.FrontEnd.Init.LoginActivity;
import com.example.joiintheclub.FrontEnd.Setting.SettingMain;
import com.example.joiintheclub.FrontEnd.UserProfile.UserProfileMain;
import com.example.joiintheclub.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressLint("Registered")
public class SearchMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter<SearchRecycleAdapter.ViewHolder> adapter;
    EditText searchInput;

    private List<String> groupNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);


        String[][] groupInfo = Group.SearchGroup();
        String [] groupNameBuf = new String[groupInfo.length];

        for(int a = 0; a < groupInfo.length; a++)
        {
            groupNameBuf[a]= groupInfo[a][1];
        }
        groupNames = Arrays.asList(groupNameBuf);


        recyclerView = (RecyclerView) findViewById(R.id.search_recycle_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new SearchRecycleAdapter(groupNames);
        recyclerView.setAdapter(adapter);


        searchInput = findViewById(R.id.search_Input);
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.Tbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.search_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.searchNav);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void filter(String searchText)
    {

        String userInput = searchText.toLowerCase();
        ArrayList<String> newList = new ArrayList<>();

        for(String name : groupNames)
        {
            if(name.toLowerCase().contains(userInput))
            {
                newList.add(name);
            }
        }

        SearchRecycleAdapter.filterList(newList);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.search_drawer);
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
            Intent intent = new Intent(SearchMain.this, UserProfileMain.class);
            startActivity(intent);
        } else if (id == R.id.search) {
            Intent intent = new Intent(SearchMain.this, SearchMain.class);
            startActivity(intent);

        } else if (id == R.id.club) {
            Intent intent = new Intent(SearchMain.this, GroupMain.class);
            startActivity(intent);

        } else if (id == R.id.setting) {
            Intent intent = new Intent(SearchMain.this, SettingMain.class);
            startActivity(intent);

        } else if (id == R.id.logout) {

            Intent intent = new Intent(SearchMain.this, LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.search_drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);

    }
}

