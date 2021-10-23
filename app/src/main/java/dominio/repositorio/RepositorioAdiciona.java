package dominio.repositorio;

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
    public void inserirAdiciona(Adiciona adiciona){
        ContentValues contentValues = new ContentValues();
        contentValues.put("CODIGO_EMP",adiciona.getEmpresa().getCodigo());
        contentValues.put("ID_CLIENT",adiciona.getCliente().getIdcli());

        conexao.insertOrThrow("ADICIONA",null,contentValues );
    }
    public void excluir(int idcli, int idemp) {
        String[] parametros = new String[2];
        parametros[0] = String.valueOf(idcli);
        parametros[1] = String.valueOf(idemp);
        conexao.delete("CLIENTE", "ID_CLIENT = ? AND CODIGO_EMP = ?", parametros);

    }
}

