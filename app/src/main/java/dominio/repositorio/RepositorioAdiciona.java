/*package dominio.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

import dominio.entidade.Adiciona;
import dominio.entidade.Cliente;

public class RepositorioAdiciona {
    private SQLiteDatabase conexao;
    public RepositorioAdiciona(SQLiteDatabase conexao) {
    }
    public void inserir(Adiciona adiciona){
        ContentValues contentValues = new ContentValues();
        contentValues.put("CODIGO_EMP",adiciona.getEmpresa().getCodigo());
        contentValues.put("ID_CLIEN",adiciona.getCliente().getIdcli());

        conexao.insertOrThrow("ADICIONA",null,contentValues );
    }
    public int buscarCliNum(String numero){
        List<Cliente> clientes = new ArrayList<Cliente>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT MAX(ID) ");
        sql.append("    FROM CLIENTE");
        sql.append("    WHERE CLIENTE.NUMERO = ? ");
        String[] parametros = new String[1];
        parametros[0]=String.valueOf(numero);
        Cursor resultado = conexao.rawQuery(sql.toString(),parametros );
        Cliente cli = new Cliente();
        cli.setNome( resultado.getString( resultado.getColumnIndexOrThrow("NOME")));
        cli.setIdcli(resultado.getInt( resultado.getColumnIndexOrThrow("ID")));

        int id = cli.getIdcli();
        return id;

    }
}*/

