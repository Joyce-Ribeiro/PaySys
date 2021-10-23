package dominio.repositorio;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;


import dominio.entidade.Adiciona;
import dominio.entidade.Cliente;
import dominio.entidade.Pagamento;

public class RespositorioPagamento {
    private SQLiteDatabase conexao;
    public RespositorioPagamento(SQLiteDatabase conexao) {
    }
    public void inserirPagamento(Pagamento pagamento){
        ContentValues contentValues = new ContentValues();
        contentValues.put("CODIGO_EMP",pagamento.empresa.getCodigo());
        contentValues.put("ID_CLIENT",pagamento.cliente.getIdcli());
        contentValues.put("DATA",pagamento.getData());
        contentValues.put("VALOR",pagamento.getData());
        contentValues.put("STATUS",pagamento.isStatus());
        contentValues.put("FREQUENCIA",pagamento.getFrequencia());


        conexao.insertOrThrow("PAGAMENTO",null,contentValues );
    }
    public void inserirPdf(Pagamento pagamento, String idpag){
        ContentValues contentValues = new ContentValues();
        contentValues.put("PDF",pagamento.getPdf());

        conexao.insertOrThrow("PAGAMENTO WHERE IDPAG = ?",idpag,contentValues );
    }
}
