package dominio.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dominio.entidade.Cliente;

public class RepositorioCliente{
    private SQLiteDatabase conexao;


    public RepositorioCliente(SQLiteDatabase conexao) {
        this.conexao = conexao;
    }


    public void inserir(Cliente cliente) {
        ContentValues contentValues = new ContentValues();
        String nome = new String(cliente.getNome());
        contentValues.put("NOME", nome);
        contentValues.put("NUMERO", "cliente.getNumero()");
        contentValues.put("SENHA", "cliente.getSenha()");

        conexao.insert("CLIENTE", null, contentValues);

    }

    public void excluir(int idcli) {
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(idcli);

        conexao.delete("CLIENTE", "ID = ?", parametros);

    }

    public void alterar(Cliente cliente) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", cliente.getNome());
        contentValues.put("NUMERO", cliente.getNumero());
        contentValues.put("SENHA", cliente.getSenha());

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(cliente.getIdcli());

        conexao.update("CLIENTE", contentValues, "ID = ?", parametros);
    }

    public List<Cliente> buscarTodos() {
        List<Cliente> clientes = new ArrayList<Cliente>();


        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ID, NOME, SENHA, NUMERO ");
        sql.append("    FROM CLIENTE ");
        if (conexao!=null){
        Cursor resultado = conexao.rawQuery(sql.toString(), null);

        if (resultado.getCount()>0) {
            resultado.moveToFirst();
            do {
                Cliente cli = new Cliente();
                int nomeres = resultado.getColumnIndex("NOME");
                int resnum = resultado.getColumnIndex("NUMERO");
                cli.setNome(resultado.getString(nomeres));
                cli.setNumero(resultado.getString(resnum));


                clientes.add(cli);

            } while (resultado.moveToNext());
        }}
    return clientes;
    }
}



