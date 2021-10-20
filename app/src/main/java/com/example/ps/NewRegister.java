package com.example.ps;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ps.databinding.ActivityNewRegisterBinding;

public class NewRegister extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityNewRegisterBinding binding;
    private Button btncadcli;
    private Button btncademp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_new_register);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        binding.btncadcli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(NewRegister.this, NewCliente.class);
                startActivity(it);

            }
        });
        binding.btncademp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent it = new Intent(NewRegister.this, NewEmp.class);
                startActivity(it);

        }
    });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_new_register);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}