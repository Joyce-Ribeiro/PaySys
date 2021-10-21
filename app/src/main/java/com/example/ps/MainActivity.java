package com.example.ps;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ps.databinding.ActivityMainBinding;

import java.util.List;

import database.PaymentSystemOpenHelper;
import dominio.entidade.Cliente;
import dominio.repositorio.RepositorioCliente;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private PaymentSystemOpenHelper paymentSystemOpenHelper;
    private SQLiteDatabase conexao;
    private ConstraintLayout layoutContentMain;
    private RecyclerView list_cli_emp;
    private RepositorioCliente repositorioCliente;
    private ClienteAdapter clienteAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, ChooseAct.class);
                startActivity(it);

            }
        });
        list_cli_emp = (RecyclerView)findViewById(R.id.list_cli_emp);
        layoutContentMain = (ConstraintLayout)findViewById(R.id.layoutContentMain);
        criarConexao();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        list_cli_emp.setLayoutManager(linearLayoutManager);
        list_cli_emp.setHasFixedSize(true);
        repositorioCliente = new RepositorioCliente(conexao);
        List<Cliente> dados = repositorioCliente.buscarTodos();
        clienteAdapter = new ClienteAdapter(dados);
        list_cli_emp.setAdapter(clienteAdapter);


    }
    private void criarConexao(){

        try{
            paymentSystemOpenHelper = new PaymentSystemOpenHelper(this);
            conexao = paymentSystemOpenHelper.getWritableDatabase();
            Snackbar.make(layoutContentMain, "SUCESSO", Snackbar.LENGTH_SHORT).setAction("ok",null).show();
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
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}