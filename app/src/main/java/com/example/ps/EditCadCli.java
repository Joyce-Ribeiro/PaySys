package com.example.ps;

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
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ps.databinding.ActivityEditCadCliBinding;

import java.util.List;

import database.PaymentSystemOpenHelper;
import dominio.entidade.Cliente;
import dominio.entidade.Pagamento;
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
    private EditText editCod;
    private EditText editIdCli;
    private RespositorioPagamento repositorioPagamento;
    private Pagamento pagamento;
    private RepositorioCliente repositorioCliente;



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
        editCod=(EditText)findViewById(R.id.editCod);
        editIdCli=(EditText)findViewById(R.id.editIdCli);
        criarConexao();

    }


    private void criarConexao(){

        try{
            paymentSystemOpenHelper = new PaymentSystemOpenHelper(this);
            conexao = paymentSystemOpenHelper.getWritableDatabase();
            repositorioPagamento = new RespositorioPagamento(conexao);
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
        pagamento = new Pagamento();
        if(validaInfoCli()== false) {

            try {
                repositorioPagamento.inserirPagamento(pagamento);
            }catch (SQLException ex){
                /*AlertDialog.Builder dlg =new AlertDialog.Builder(this);
                dlg.setTitle("Erro");
                dlg.setMessage(ex.getMessage());
                dlg.setNeutralButton("ok",null);
                dlg.show();*/
            }
            finish();

        }
    }
    private boolean validaInfoCli(){
        boolean res = false;
        String frequencia = editFreq.getText().toString();
        String data = editfirstdata.getText().toString();
        String valor = editvalor.getText().toString();
        String codemp= editCod.getText().toString();
        String idcli = editIdCli.getText().toString();

        pagamento.setFrequencia(Integer.parseInt(frequencia));
        pagamento.setData(data);
        pagamento.setValor(Integer.parseInt(valor));
        pagamento.setId_cli(Integer.parseInt(idcli));
        pagamento.setId_emp(Integer.parseInt(codemp));
        pagamento.setStatus(false);


        if (res = isCampoEmpty(frequencia)){
            editFreq.requestFocus();
        }

        else if (res = isCampoEmpty(data)){
            editfirstdata.requestFocus();
        }
        else if (res = isCampoEmpty(valor)){
            editvalor.requestFocus();
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
                confirmar();
                break;
            case R.id.action_cancel_btn:

                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}