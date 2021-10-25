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
}
