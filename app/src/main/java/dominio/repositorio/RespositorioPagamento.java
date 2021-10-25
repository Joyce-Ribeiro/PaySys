package dominio.repositorio;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;


import dominio.entidade.Pagamento;

public class RespositorioPagamento {
    private SQLiteDatabase conexao;
    public RespositorioPagamento(SQLiteDatabase conexao) {this.conexao= conexao;
    }
    public void inserirPagamento(Pagamento pagamento){
        ContentValues contentValues = new ContentValues();
        int idemp;
        idemp = pagamento.getId_emp();
        int idcli;
        idcli = pagamento.getId_cli();
        String data;
        data =pagamento.getData();
        Float valor;
        valor=pagamento.getValor();
        boolean status;
        status = pagamento.isStatus();
        int freq;
        freq = pagamento.getFrequencia();

        contentValues.put("ID_EMP",idemp);
        contentValues.put("ID_CLI",idcli);
        contentValues.put("DATA",data);
        contentValues.put("VALOR",valor);
        contentValues.put("STATUS",status);
        contentValues.put("FREQUENCIA",freq);


        conexao.insertOrThrow("PAGAMENTO",null,contentValues);
    }
    public void inserirPdf(Pagamento pagamento, String idpag){
        ContentValues contentValues = new ContentValues();
        contentValues.put("PDF",pagamento.getPdf());

        conexao.insertOrThrow("PAGAMENTO WHERE IDPAG = ?",idpag,contentValues );
    }
    public List<Pagamento> buscarTodos(int id, int cod ) {
        List<Pagamento> pagamentos = new ArrayList<Pagamento>();


        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT DATA, VALOR, STATUS, FREQUENCIA ");
        sql.append("    FROM PAGAMENTO ");
        sql.append("    WHERE  ID_CLI = ? AND ID_EMP = ?");
        String[] parametros = new String[2];
        parametros[0] = String.valueOf(id);
        parametros[1] = String.valueOf(cod);
        Cursor resultado = conexao.rawQuery(sql.toString(), parametros);

        if (resultado.getCount()>0) {
            resultado.moveToFirst();
            do {
                Pagamento pag = new Pagamento();
                int resdata = resultado.getColumnIndex("DATA");
                int resvalor = resultado.getColumnIndex("VALOR");
                int resstatus = resultado.getColumnIndex("STATUS");
                int resfreq = resultado.getColumnIndex("FREQUENCIA");

                pag.setData(resultado.getString(resdata));
                pag.setValor( Integer.parseInt(resultado.getString(resvalor)));
                pag.setStatus(Boolean.parseBoolean(resultado.getString(resstatus)));
                pag.setFrequencia(Integer.parseInt(resultado.getString(resfreq)));



                pagamentos.add(pag);

            } while (resultado.moveToNext());
        }

        return pagamentos;
    }
}
