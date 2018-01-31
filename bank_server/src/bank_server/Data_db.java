
package bank_server;

abstract public class Data_db {
    protected java.sql.Statement stmt;
    protected DBConnection mdbc;

    public Data_db(java.sql.Statement stmt, DBConnection mdbc) {
        this.stmt = stmt;
        this.mdbc = mdbc;
    }

    public String quotate(String content) {
        return "'" + content + "'";
    }
}
