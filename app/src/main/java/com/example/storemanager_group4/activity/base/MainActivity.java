package com.example.storemanager_group4.activity.base;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.storemanager_group4.R;
import com.example.storemanager_group4.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends BaseActivity {

    Toolbar toolbar;
    TextView toolbarText;
    View fragment;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbarText = findViewById(R.id.toolbarText);
        fragment = findViewById(R.id.fragment);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.black));
        SharedPreferences sharedPreferences= getSharedPreferences("userDetails",0);


        View headerView = navigationView.getHeaderView(0);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment, new HomeFragment()).commit();
        toolbarText.setText("Home");



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.homeMenu) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new HomeFragment()).commit();
                    toolbarText.setText("Home");
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId() == R.id.signOutMenu) {
                    signOut();
                }
                return true;
            }
        });

    }

    public void signOut() {
        SharedPreferences sharedPreferences = getSharedPreferences("userDetails", 0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putBoolean("isLogin", false);
        sharedPreferencesEditor.apply();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}