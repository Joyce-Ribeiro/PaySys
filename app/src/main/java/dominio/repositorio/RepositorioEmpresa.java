package dominio.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

import dominio.entidade.Empresa;

public class RepositorioEmpresa {
    private SQLiteDatabase conexao;

    public RepositorioEmpresa(SQLiteDatabase conexao) {
    }

    public void EmpresaRepositorio(SQLiteDatabase conexao) {
        this.conexao = conexao;
    }

    public void inserirEmp(Empresa empresa){
        ContentValues contentValues = new ContentValues();
        String nome = new String(empresa.getNome());
        String numero = new String(empresa.getNumero());
        String senha = new String(empresa.getSenha());
        contentValues.put("NOME",nome);
        contentValues.put("NUMERO",numero);
        contentValues.put("SENHA",senha);

        conexao.insertOrThrow("EMPRESA",null,contentValues );

    }
    public void excluir(int codigo){
        String[] parametros = new String[1];
        parametros[0]=String.valueOf(codigo);

        conexao.delete("EMPRESA","CODIGO = ?", parametros );

    }
    public void alterar(Empresa empresa){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME",empresa.getNome());
        contentValues.put("NUMERO",empresa.getNumero());
        contentValues.put("SENHA",empresa.getSenha());

        String[] parametros = new String[1];
        parametros[0]=String.valueOf(empresa.getCodigo());

        conexao.update("EMPRESA",contentValues,"CODIGO = ?", parametros );
    }
    public List<Empresa> buscarTodos(){
        List<Empresa> empresas = new ArrayList<Empresa>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT NOME, NUMERO, SENHA ");
        sql.append("    FROM EMPRESA");

        Cursor resultado = conexao.rawQuery(sql.toString(),null);

        if (resultado.getCount()>0){
            resultado.moveToFirst();

            do {
                Empresa emp = new Empresa();
                emp.setNome( resultado.getString( resultado.getColumnIndexOrThrow("NOME")));
                emp.setNumero(resultado.getString( resultado.getColumnIndexOrThrow("NUMERO")));

                empresas.add(emp);

            }while(resultado.moveToNext());
        }
        return empresas;
    }

}
