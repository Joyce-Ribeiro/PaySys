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

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ps.databinding.ActivityNewClienteBinding;

import database.PaymentSystemOpenHelper;
import dominio.entidade.Adiciona;
import dominio.entidade.Cliente;
import dominio.repositorio.RepositorioCliente;

public class NewCliente extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityNewClienteBinding binding;
    private EditText edNomeCli;
    private EditText edNumCli;
    private EditText edCodCli;
    private EditText edSenhaCli;
    private ConstraintLayout layoutContentNewCli;
    private RepositorioCliente repositorioCliente;
    private PaymentSystemOpenHelper paymentSystemOpenHelper;
    private SQLiteDatabase conexao;
    private Cliente cliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewClienteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_new_cliente);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        edNomeCli=(EditText)findViewById(R.id.edNomeCli);
        edNumCli=(EditText)findViewById(R.id.edNumCli);
        edCodCli=(EditText)findViewById(R.id.edCodCli);
        edSenhaCli= (EditText)findViewById(R.id.edSenhaCli);

        layoutContentNewCli = (ConstraintLayout)findViewById(R.id.layoutContentNewCli);
        criarConexao();
    }
    private void criarConexao(){

        try{
            paymentSystemOpenHelper = new PaymentSystemOpenHelper(this);
            conexao = paymentSystemOpenHelper.getWritableDatabase();
            Snackbar.make(layoutContentNewCli, "SUCESSO", Snackbar.LENGTH_SHORT).setAction("ok",null).show();
            repositorioCliente = new RepositorioCliente(conexao);

        }catch (SQLException ex){
            AlertDialog.Builder dlg =new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("ok",null);
            dlg.show();
        }
    }
    private void confirmar(){
        cliente = new Cliente();
        if(validaInfoCli()== false) {

            cliente.setNumero(edNumCli.getText().toString());
            cliente.setNome(edNomeCli.getText().toString());
            cliente.setSenha(edSenhaCli.getText().toString());
            try {
                repositorioCliente.inserir(cliente);
            }catch (SQLException ex){
                AlertDialog.Builder dlg =new AlertDialog.Builder(this);
                dlg.setTitle("Erro");
                dlg.setMessage(ex.getMessage());
                dlg.setNeutralButton("ok",null);
                dlg.show();
            }
            finish();

        }
    }
    private boolean validaInfoCli(){
        boolean res = false;
        String nome = edNomeCli.getText().toString();
        String numero = edNumCli.getText().toString();
        String codigo = edCodCli.getText().toString();
        String senha = edSenhaCli.getText().toString();

        cliente.setNome(nome);
        cliente.setNumero(numero);
        cliente.setSenha(senha);

        if (res = isCampoEmpty(nome)){
            edNomeCli.requestFocus();
        }
        else if (res = isCampoEmpty(numero)){
            edNumCli.requestFocus();
        }
        else if (res = isCampoEmpty(codigo)){
            edCodCli.requestFocus();
        }
        else if (res = isCampoEmpty(senha)){
            edSenhaCli.requestFocus();
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
                confirmar();
                break;
            case R.id.action_cancel_btn:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_new_cliente);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}