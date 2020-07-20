package com.example.garbagem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.example.garbagem.Retrofit.ApiClient;
import com.example.garbagem.Retrofit.ApiInterface;
import com.example.garbagem.screen.Announcement;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerlayout;
    Toolbar toolbar;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerlayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        WebView webmap = (WebView) findViewById(R.id.webview);
        webmap.loadUrl("https://www.google.com");


        ActionBarDrawerToggle toggle;
        toggle = new ActionBarDrawerToggle(this,drawerlayout,toolbar,R.string.open,R.string.close);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();

    }
    //add button methods
    public void openAnnouncement(){
        Intent inten = new Intent(this, Announcement.class);
        startActivity(inten);

    }
    public void openNotification(){
        Intent inten = new Intent(this,     Notification.class);
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

            case R.id.announcement:
                Intent anno = new Intent(MainActivity.this, Announcement.class);
                startActivity(anno);
                break;

            case R.id.notification:
                Intent not = new Intent(MainActivity.this, Notification.class);
                startActivity(not);
                break;

            case R.id.feedback:
                try{
                    Intent fed = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + "com.android.chrome"));
                    startActivity(fed);
                    break;
                }catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(("https://play.google.com/store/apps/details?id=" + getPackageName()))));
                }

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