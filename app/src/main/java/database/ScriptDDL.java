package database;

public class ScriptDDL {

    public static String getCreateTableCliente() {
        StringBuilder sql = new StringBuilder();

        sql.append("    CREATE TABLE IF NOT EXISTS CLIENTE ( ");
        sql.append("        ID  INTEGER     PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("        NOME  VARCHAR(250)     NOT NULL DEFAULT (''), ");
        sql.append("        SENHA  VARCHAR(250)     NOT NULL, ");
        sql.append("        NUMERO  VARCHAR(250)     NOT NULL DEFAULT ('') ) ");

        return sql.toString();
    }

    public static String getCreateTablePagamento(){
        StringBuilder sql = new StringBuilder();

        sql.append("    CREATE TABLE IF NOT EXISTS PAGAMENTO ( ");
        sql.append("        IDPAG INTEGER  PRIMARY KEY AUTOINCREMENT   NOT NULL DEFAULT (''), " );
        sql.append("        DATA VARCHAR(10)     NOT NULL DEFAULT (''), " );
        sql.append("        ID_CLI  INTEGER     NOT NULL DEFAULT (''), " );
        sql.append("        ID_Emp  INTEGER     NOT NULL DEFAULT (''), " );
        sql.append("        VALOR  FLOAT     NOT NULL DEFAULT (''), " );
        sql.append("        STATUS  BOOLEAN     NOT NULL DEFAULT (''), " );
        sql.append("        FREQUENCIA  INTEGER     NOT NULL DEFAULT (''), " );
        sql.append("        PDF  BLOB     DEFAULT (''), " );
        sql.append("        FOREIGN KEY(ID_CLI) REFERENCES CLIENTE(ID), ");
        sql.append("        FOREIGN KEY(ID_CLI) REFERENCES EMPRESA(ID) )");


        return sql.toString();
    }
    public static String getCreateTableEmpresa(){
        StringBuilder sql = new StringBuilder();

        sql.append("    CREATE TABLE IF NOT EXISTS EMPRESA ( ");
        sql.append("        CODIGO  INTEGER     PRIMARY KEY AUTOINCREMENT NOT NULL DEFAULT (''), " );
        sql.append("        NOME  VARCHAR(250)     NOT NULL DEFAULT (''), " );
        sql.append("        NUMERO  VARCHAR(250)     NOT NULL DEFAULT (''), " );
        sql.append("        SENHA  VARCHAR(250)     NOT NULL DEFAULT ('') ) ");

        return sql.toString();
    }
    public static String getCreateTableAdiciona(){
        StringBuilder sql = new StringBuilder();

        sql.append("    CREATE TABLE IF NOT EXISTS ADICIONA ( ");
        sql.append("        CODIGO_EMP  INTEGER     NOT NULL DEFAULT (''), " );
        sql.append("        ID_CLIENT  INTEGER     NOT NULL DEFAULT (''), " );
        sql.append("        FOREIGN KEY(ID_CLIENT) REFERENCES CLIENTE(ID), " );
        sql.append("        FOREIGN KEY (CODIGO_EMP) REFERENCES EMPRESA(CODIGO), " );
        sql.append("        PRIMARY KEY (ID_CLIENT, CODIGO_EMP) ) ");

        return sql.toString();
    }


}