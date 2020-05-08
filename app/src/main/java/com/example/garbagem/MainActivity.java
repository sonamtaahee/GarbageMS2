package com.example.garbagem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerlayout;
    Toolbar toolbar;
    NavigationView navigationView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button =(Button) findViewById(R.id.loadlocation);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBinStatus();
            }
        });

        drawerlayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle;
        toggle = new ActionBarDrawerToggle(this,drawerlayout,toolbar,R.string.open,R.string.close);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();

    }
    public void openBinStatus(){
        Intent inten = new Intent(this, BinStatus.class);
        startActivity(inten);

    }

    @Override
    public void onBackPressed() {

        if(drawerlayout.isDrawerOpen(GravityCompat.START)){
            drawerlayout.closeDrawers();
        }else {
            super.onBackPressed();
        }
    }

    /* CODE FOR NAVIGATION DRAWER ITEM SELECTED */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home:
                Intent hom = new Intent(MainActivity.this, MainActivity.class);
                startActivity(hom);
                break;

            case R.id.status:
                Intent sta = new Intent(MainActivity.this, BinStatus.class);
                startActivity(sta);
                break;

            case R.id.announcement:
                Intent anno = new Intent(MainActivity.this, Announcement.class);
                startActivity(anno);
                break;

            case R.id.notification:
                Intent not = new Intent(MainActivity.this, Notification.class);
                startActivity(not);
                break;

            case R.id.share:
                Intent in = new Intent();
                in.setAction(Intent.ACTION_SEND);
                in.putExtra(Intent.EXTRA_TEXT, "https://play.google.co/store/apps/details?id= hi");
                in.setType("text/plain");
                startActivity(Intent.createChooser(in, "Share"));
                break;
        }
        drawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }
}