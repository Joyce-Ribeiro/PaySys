package com.example.ps;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ps.databinding.ActivityLoginCliBinding;

import database.PaymentSystemOpenHelper;
import dominio.repositorio.RepositorioCliente;
import dominio.repositorio.RepositorioEmpresa;

public class LoginCli extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityLoginCliBinding binding;
    private RepositorioCliente repositorioCliente;
    private PaymentSystemOpenHelper paymentSystemOpenHelper;
    private SQLiteDatabase conexao;
    private EditText edLogNumCli;
    private EditText edLogSenhaCli;
    private EditText edLogCodCli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginCliBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_login_cli);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        edLogNumCli=(EditText)findViewById(R.id.edLogNumCli);
        edLogSenhaCli=(EditText)findViewById(R.id.edLogSenhaCli);
        edLogCodCli=(EditText) findViewById(R.id.edLogCodCli);
        criarConexao();
    }
    private boolean validaInfoCli(){
        boolean res = false;
        String numero = edLogNumCli.getText().toString();
        String codigo = edLogCodCli.getText().toString();
        String senha = edLogSenhaCli.getText().toString();

        if (res = isCampoEmpty(numero)){
            edLogNumCli.requestFocus();
        }
        else if (res = isCampoEmpty(codigo)){
            edLogCodCli.requestFocus();
        }
        else if (res = isCampoEmpty(codigo)){
            edLogSenhaCli.requestFocus();
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
        inflater.inflate(R.menu.menu_act_newcli,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.action_ok_btn:
                validaInfoCli();
                String numero = edLogNumCli.getText().toString();
                String senha = edLogSenhaCli.getText().toString();
                int codigo = Integer.parseInt(edLogCodCli.getText().toString());
                int resposta;
                resposta = repositorioCliente.buscarCliente(numero,senha,codigo);
                if(resposta>0){
                    Intent it = new Intent(LoginCli.this, MainActivity.class);
                    it.putExtra("Cliente", resposta);
                    it.putExtra("Clientecod", codigo);
                    startActivity(it);
                }
                break;
            case R.id.action_cancel_btn:
                Intent it = new Intent(LoginCli.this, LoginAct.class);
                startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }
    private void criarConexao(){

        try{
            paymentSystemOpenHelper = new PaymentSystemOpenHelper(this);
            conexao = paymentSystemOpenHelper.getWritableDatabase();
            repositorioCliente = new RepositorioCliente(conexao);
        }catch (SQLException ex){
            AlertDialog.Builder dlg =new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("ok",null);
            dlg.show();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_login_cli);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}