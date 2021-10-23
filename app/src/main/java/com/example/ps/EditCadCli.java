package com.example.ps;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ps.databinding.ActivityEditCadCliBinding;

import database.PaymentSystemOpenHelper;
import dominio.entidade.Cliente;
import dominio.repositorio.RepositorioCliente;
import dominio.repositorio.RespositorioPagamento;

public class EditCadCli extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityEditCadCliBinding binding;
    private PaymentSystemOpenHelper paymentSystemOpenHelper;
    private SQLiteDatabase conexao;
    private EditText editFreq;
    private EditText editfirstdata;
    private EditText editvalor;
    private RepositorioCliente repositorioCliente;
    private Cliente cliente;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditCadCliBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_edit_cad_cli);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        editFreq=(EditText)findViewById(R.id.editFreq);
        editfirstdata=(EditText)findViewById(R.id.editfirstdata);
        editvalor=(EditText)findViewById(R.id.editvalor);
        criarConexao();
        /*verificaParametro();*/
    }
    private void verificaParametro(){
        Bundle bundle = getIntent().getExtras();
        if ((bundle != null) && (bundle.containsKey("CLIENTE")) ){
            Cliente cliente = (Cliente) bundle.getSerializable("CLIENTE");
        }
    }
    private void criarConexao(){

        try{
            paymentSystemOpenHelper = new PaymentSystemOpenHelper(this);
            conexao = paymentSystemOpenHelper.getWritableDatabase();
            /*repositorioPagamento = new RespositorioPagamento(conexao);*/

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
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_edit_cad_cli);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_editcli,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){

            case R.id.action_ok_btn:

                break;
            case R.id.action_cancel_btn:
                repositorioCliente.excluir(cliente.getIdcli());
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}