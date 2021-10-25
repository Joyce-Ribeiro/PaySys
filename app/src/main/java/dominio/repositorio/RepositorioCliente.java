package dominio.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;


import dominio.entidade.Adiciona;
import dominio.entidade.Cliente;
import dominio.entidade.Empresa;

public class RepositorioCliente{
    private SQLiteDatabase conexao;


    public RepositorioCliente(SQLiteDatabase conexao) {
        this.conexao = conexao;
    }


    public void inserirCli(Cliente cliente) {

        ContentValues contentValues = new ContentValues();
        String nome = new String(cliente.getNome());
        String numero;
        numero = cliente.getNumero();
        String senha = new String(cliente.getSenha());
        contentValues.put("NOME", nome);
        contentValues.put("NUMERO", numero);
        contentValues.put("SENHA", senha);

        conexao.insertOrThrow("CLIENTE", null, contentValues);


    }
    public void inserirAdd(String numero, int codigo) {


        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ID ");
        sql.append("    FROM CLIENTE ");
        sql.append("    WHERE NUMERO = ? ");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(numero);

        Cursor resultado = conexao.rawQuery(sql.toString(), parametros);
        if (resultado.getCount()>0) {
            resultado.moveToFirst();
            int id= resultado.getColumnIndex("ID");
            int idcli = Integer.parseInt(resultado.getString(id));
            ContentValues contentValues = new ContentValues();

            contentValues.put("CODIGO_EMP", codigo);
            contentValues.put("ID_CLIENT", idcli);


            conexao.insertOrThrow("ADICIONA", null, contentValues);
        }





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

    public List<Cliente> buscarTodos(int codigo) {
        List<Cliente> clientes = new ArrayList<Cliente>();


        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT CLIENTE.NOME, CLIENTE.NUMERO ");
        sql.append("    FROM CLIENTE, ADICIONA, EMPRESA ");
        sql.append("    WHERE ADICIONA.CODIGO_EMP = ? AND ADICIONA.ID_CLIENT= CLIENTE.ID ");
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(codigo);
        Cursor resultado = conexao.rawQuery(sql.toString(), parametros);

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
        }

    return clientes;
    }
    public int buscarCliente(String numero, String senha, int cod) {
        Cliente cliente = new Cliente();
        int idres = 0;

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT CLIENTE.ID, CLIENTE.SENHA ");
        sql.append("    FROM CLIENTE, ADICIONA, EMPRESA ");
        sql.append("    WHERE ADICIONA.CODIGO_EMP = ? AND CLIENTE.NUMERO = ? AND ADICIONA.ID_CLIENT = CLIENTE.ID ");


        String[] parametros = new String[2];
        parametros[0] = String.valueOf(cod);
        parametros[1] = String.valueOf(numero);
        Cursor resultado = conexao.rawQuery(sql.toString(), parametros);

        if (resultado.getCount()>0) {
            resultado.moveToFirst();

            int senhares= resultado.getColumnIndex("SENHA");
            cliente.setSenha(resultado.getString(senhares));

            System.out.println(cliente.getSenha());
            System.out.println(senhares);
            if(senha.equals(cliente.getSenha())) {
                idres = resultado.getColumnIndex("ID");
                cliente.setIdcli(Integer.parseInt(resultado.getString(idres)));


            }

            }
        return cliente.getIdcli();
    }
}



