package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PaymentSystemOpenHelper extends SQLiteOpenHelper {
    public PaymentSystemOpenHelper(@Nullable Context context) {
        super(context, "Payment System", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( ScriptDDL.getCreateTableCliente());
        db.execSQL( ScriptDDL.getCreateTableEmpresa());
        db.execSQL( ScriptDDL.getCreateTablePagamento());
        db.execSQL( ScriptDDL.getCreateTableAdiciona());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
