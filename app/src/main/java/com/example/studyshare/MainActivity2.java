package com.example.studyshare;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnDismissListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TextView cambiodefragmento;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Button toolbar, usuariosubearchivos, subirArchivos;

    private boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Obtén el NavController desde el NavHostFragment
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                // Obtén el ID del fragmento actual
                int currentDestinationId = navController.getCurrentDestination().getId();
                cambiodefragmento = findViewById(R.id.cambiodefragment);
                // Actualiza el texto de la cabecera según el fragmento actual
                if (currentDestinationId == R.id.inicio) {
                    cambiodefragmento.setText("Inicio");
                    cambiodefragmento.setTextColor(getColor(R.color.white));
                } else if (currentDestinationId == R.id.files) {
                    cambiodefragmento.setText("Mis archivos");
                    cambiodefragmento.setTextColor(getColor(R.color.white));
                } else if (currentDestinationId == R.id.user) {
                    cambiodefragmento.setText("Usuario");
                    cambiodefragmento.setTextColor(getColor(R.color.white));
                }
            }
        });

        //Lateral menu bar
        drawerLayout =  findViewById(R.id.drawerLayout);
        navigationView =  findViewById(R.id.menureal);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar = findViewById(R.id.menu);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir o cerrar el cajón (drawer) al hacer clic en el botón
                if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    drawerLayout.closeDrawer(GravityCompat.END);
                } else {
                    drawerLayout.openDrawer(GravityCompat.END);
                }
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (doubleBackToExitPressedOnce){
                    finish();
                } else {
                    Toast.makeText(MainActivity2.this, "Pulsa otra vez para salir", Toast.LENGTH_SHORT).show();
                    doubleBackToExitPressedOnce = true;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce = false;
                        }
                    },1000);
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
        navegar();

        subirArchivos = findViewById(R.id.subirArchivos);
        subirArchivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, AddFilesMisArchivos.class);
                startActivity(intent);
            }
        });

    }
    private void navegar(){

        BottomNavigationView bottomNavigationView = findViewById(R.id.botones);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        long id =  item.getItemId();
        if (id==R.id.facebook){
            Toast.makeText(this, "hola", Toast.LENGTH_SHORT).show();
            return true;
        }
        return true;
    }


}
