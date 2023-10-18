package com.wiztech.taxation_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.wiztech.taxation_app.Entities.User;
import com.wiztech.taxation_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.wiztech.taxation_app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    NavController navController;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        binding.appBarMain.ivLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showAlert();
//            }
//        });

         drawer = binding.drawerLayout;
         navigationView = binding.navView;
         bottomNavigationView=findViewById(R.id.nav_view1);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.logout)
                .setOpenableLayout(drawer)
                .build();
         navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
       // NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id){
                    case R.id.navigation_home:
                        navController.popBackStack(R.id.nav_billDetail,true);
                        navController.popBackStack(R.id.nav_gallery,true);
                        navController.navigate(R.id.nav_home);
                        break;
                    case R.id.navigation_contactus:
                        navController.popBackStack(R.id.nav_billDetail,true);
                        navController.popBackStack(R.id.nav_home,true);
                        navController.navigate(R.id.nav_gallery);
                        break;
                    case R.id.navigation_profile:
                        navController.popBackStack(R.id.nav_home,true);
                        navController.popBackStack(R.id.nav_gallery,true);
                        navController.navigate(R.id.nav_billDetail);

                        break;
                }
                return false;
            }
        });



        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                System.out.println();
            }
        });

        MenuItem item=navigationView.getMenu().getItem(3);
        View view=navigationView.findViewById(R.id.logout);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert();

            }
        });



        User user=LoginSession.getUser(getApplicationContext());
        setValueInNavHeader(user);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }

    public void showAlert(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
        builder1.setMessage("Are you sure, you want to logout?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        moveToLogin();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    public void showCloseAppAlert(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
        builder1.setMessage("Are you sure, you want to Close App?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        System.exit(0);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

    public void moveToLogin(){
        LoginSession.logout(MainActivity.this);
        finish();
        Intent intent=new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    public void  openCloseNavigationDrawer(View view) {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            binding.drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    void setValueInNavHeader(User user){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navName = (TextView) headerView.findViewById(R.id.tvName);
        TextView navEmail = (TextView) headerView.findViewById(R.id.tvEmail);

        navName.setText(""+user.getFirstName()+" "+user.getLastName());
        navEmail.setText(""+user.getEmail());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.logout:
                showAlert();
                break;

        }
        return false;
    }


    @Override
    public void onBackPressed() {
        navController.getVisibleEntries();
        int count =getSupportFragmentManager().getBackStackEntryCount();
        NavBackStackEntry navBackStackEntry=navController.getCurrentBackStackEntry();
        if (drawer!=null && drawer.isDrawerOpen(Gravity.LEFT)){
            drawer.closeDrawer(Gravity.LEFT);
        }else{
            System.out.println("+++++++++++++++++"+navBackStackEntry.getDestination().getDisplayName());
            if (count==0 && !navBackStackEntry.getDestination().getDisplayName().contains("nav_home")){
                navController.popBackStack(R.id.nav_billDetail,true);
                navController.popBackStack(R.id.nav_gallery,true);
                navController.navigate(R.id.nav_home);
            }else if (count>0){
                super.onBackPressed();
            }
            else{
               showCloseAppAlert();
            }
        }

    }
}