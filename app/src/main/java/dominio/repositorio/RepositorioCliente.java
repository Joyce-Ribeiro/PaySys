package dominio.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dominio.entidade.Cliente;

public class RepositorioCliente {
    private SQLiteDatabase conexao;

    public RepositorioCliente(SQLiteDatabase conexao) {
    }


    public void ClienteRepositorio(SQLiteDatabase conexao){
        this.conexao=conexao;
    }

    public void inserir(Cliente cliente){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME",cliente.getNome());
        contentValues.put("NUMERO",cliente.getNumero());
        contentValues.put("SENHA",cliente.getSenha());

        conexao.insertOrThrow("CLIENTE",null,contentValues );
    }
    public void excluir(int idcli){
        String[] parametros = new String[1];
        parametros[0]=String.valueOf(idcli);

        conexao.delete("CLIENTE","ID = ?", parametros );

    }
    public void alterar(Cliente cliente){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME",cliente.getNome());
        contentValues.put("NUMERO",cliente.getNumero());
        contentValues.put("SENHA",cliente.getSenha());

        String[] parametros = new String[1];
        parametros[0]=String.valueOf(cliente.getIdcli());

        conexao.update("CLIENTE",contentValues,"ID = ?", parametros );
    }
    public List<Cliente> buscarTodos(){
        List<Cliente> clientes = new ArrayList<Cliente>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT NOME, NUMERO, SENHA ");
        sql.append("    FROM CLIENTE");

        Cursor resultado = conexao.rawQuery(sql.toString(),null);

        if (resultado.getCount()>0){
            resultado.moveToFirst();

            do {
                Cliente cli = new Cliente();
                cli.setNome(resultado.getString( resultado.getColumnIndexOrThrow("NOME")));
                cli.setNumero( resultado.getString( resultado.getColumnIndexOrThrow("NUMERO")));

                clientes.add(cli);

            }while(resultado.moveToNext());
        }
        return clientes;
    }
    public Cliente buscarCliente(int codigo){
        List<Cliente> clientes = new ArrayList<Cliente>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT NOME, NUMERO ");
        sql.append("    FROM CLIENTE, ADICIONA, EMPRESA");
        sql.append("    WHERE ADICIONA.CODIGO_EMP = ? AND ADICIONA.ID_CLIENT=CLIENTE.ID");
        String[] parametros = new String[1];
        parametros[0]=String.valueOf(codigo);

        Cursor resultado = conexao.rawQuery(sql.toString(),parametros );

        if (resultado.getCount()>0){
            resultado.moveToFirst();

            do {
                Cliente cli = new Cliente();
                cli.setNome( resultado.getString( resultado.getColumnIndexOrThrow("NOME")));
                cli.setNumero(resultado.getString( resultado.getColumnIndexOrThrow("NUMERO")));

                clientes.add(cli);

            }while(resultado.moveToNext());
        }
        return null;
    }
}
