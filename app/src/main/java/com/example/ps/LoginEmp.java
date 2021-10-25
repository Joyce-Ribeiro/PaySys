package com.example.ps;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.ps.databinding.ActivityLoginEmpBinding;

import database.PaymentSystemOpenHelper;
import dominio.repositorio.RepositorioCliente;
import dominio.repositorio.RepositorioEmpresa;

public class LoginEmp extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityLoginEmpBinding binding;
    private RepositorioEmpresa repositorioEmpresa;
    private PaymentSystemOpenHelper paymentSystemOpenHelper;
    private SQLiteDatabase conexao;
    private EditText edLogNumEmp;
    private EditText edLogSenhaEmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginEmpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_login_emp);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        edLogNumEmp=(EditText)findViewById(R.id.edLogNumEmp);
        edLogSenhaEmp=(EditText)findViewById(R.id.edLogSenhaEmp);
        criarConexao();
    }
    private boolean validaInfoCli(){
        boolean res = false;
        String numero = edLogNumEmp.getText().toString();
        String senha = edLogSenhaEmp.getText().toString();

        if (res = isCampoEmpty(numero)){
            edLogNumEmp.requestFocus();
        }

        else if (res = isCampoEmpty(senha)){
            edLogSenhaEmp.requestFocus();
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
        inflater.inflate(R.menu.menu_act_logemp,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_ok_btn:
                validaInfoCli();
                String numero=edLogNumEmp.getText().toString() ;
                String senha=edLogSenhaEmp.getText().toString();

                int resposta;
                resposta = repositorioEmpresa.buscarEmpresa(numero, senha);

                if(resposta>0){
                    Intent it = new Intent(LoginEmp.this, MainActivity.class);
                    it.putExtra("Empresa", resposta);
                    startActivity(it);
            }

                break;
            case R.id.action_cancel_btn:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_login_emp);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private void criarConexao(){

        try{
            paymentSystemOpenHelper = new PaymentSystemOpenHelper(this);
            conexao = paymentSystemOpenHelper.getWritableDatabase();
            repositorioEmpresa = new RepositorioEmpresa(conexao);
        }catch (SQLException ex){
            AlertDialog.Builder dlg =new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("ok",null);
            dlg.show();
        }
    }
}