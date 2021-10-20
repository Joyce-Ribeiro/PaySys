package com.example.ps;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ps.databinding.ActivityNewEmpBinding;

public class NewEmp extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityNewEmpBinding binding;
    private EditText edNomeEmp;
    private EditText edNumEmp;
    private EditText edSenhaEmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewEmpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_new_emp);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        edNomeEmp=(EditText)findViewById(R.id.edNomeEmp);
        edNumEmp=(EditText)findViewById(R.id.edNumEmp);
        edSenhaEmp= (EditText)findViewById(R.id.edSenhaEmp);
    }
    private boolean validaInfoCli() {
        boolean res = false;
        String nome = edNomeEmp.getText().toString();
        String numero = edNumEmp.getText().toString();
        String senha = edSenhaEmp.getText().toString();

        if (res = isCampoEmpty(nome)) {
            edNomeEmp.requestFocus();
        } else if (res = isCampoEmpty(numero)) {
            edNumEmp.requestFocus();
        } else if (res = isCampoEmpty(senha)) {
            edSenhaEmp.requestFocus();
        }
        if (res) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage("Há campos inválidos");
            dlg.setNeutralButton("ok", null);
            dlg.show();
        }
        return res;
    }
    private boolean isCampoEmpty(String valor){
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty() );
        return resultado;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_newemp,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.action_ok_btn:
                validaInfoCli();
                break;
            case R.id.action_cancel_btn:
                Intent it = new Intent(NewEmp.this, NewRegister.class);
                startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_new_emp);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}