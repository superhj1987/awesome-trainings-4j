package me.rowkey.trainings.h2;

import java.sql.*;

/**
 * Created by Bryant.Hang on 16/12/2.
 */
public class H2DBMemTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:delicious", "sa", ""); //h2数据库内存模式

        updateDatabase(connection, "CREATE TABLE test_table( test_val VARCHAR(10) )", new Object[0]);
        updateDatabase(connection, "insert into test_table values('aa')", new Object[0]);
        DBResource dbResource = queryDatabase(connection, "select * from test_table", new Object[0]);
        ResultSet set = dbResource.getResultSet();
        while (set.next()) {
            System.out.println(set.getString(1));
        }
        dbResource.close();

//        Server server = Server.createTcpServer().start();//启动一个tcp server接受其他进程通过tcp连接读取此jvm中h2数据库的内容
    }

    public static int updateDatabase(Connection conn, String sql, Object... params) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(sql);
        try {
            setParameters(stmt, params);
            return stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static DBResource queryDatabase(Connection conn, String sql, Object... params) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(sql);
        setParameters(stmt, params);
        return new DBResource(stmt, stmt.executeQuery());
    }

    private static void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            Object param = params[i];
            if (param instanceof Integer) {
                stmt.setInt(i + 1, (Integer) param);
            } else if (param instanceof String) {
                stmt.setString(i + 1, (String) param);
            } else {
                // TODO unsupported
            }
        }
    }
}

class DBResource {

    private PreparedStatement statement;

    private ResultSet resultSet;

    public DBResource(PreparedStatement statement) {
        setStatement(statement);
        setResultSet(null);
    }

    public DBResource(PreparedStatement statement, ResultSet resultSet) {
        setResultSet(resultSet);
        setStatement(statement);
    }

    /**
     * 获取ResultSet
     *
     * @return
     */
    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public PreparedStatement getStatement() {
        return statement;
    }

    public void setStatement(PreparedStatement statement) {
        this.statement = statement;
    }


    /**
     * 关闭数据库资源，关闭ResultSet、Statement，如果不在事务中，还会释放掉当前事务管理器中的Connection
     *
     * @return
     */
    public boolean close() {
        try {
            if (getResultSet() != null) {
                this.getResultSet().close();
            }
            if (this.getStatement() != null) {
                Connection connection = null;

                if (!this.getStatement().isClosed()) {
                    connection = this.getStatement().getConnection();
                    this.getStatement().close();
                }

                if (connection != null && !connection.isClosed()
                        && connection.getAutoCommit()) {  //这里加入对connection的关闭是因为slave connection的时候没有事务管理
                    connection.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库操作资源关闭失败" + e.getMessage(), e);
        }
        return true;
    }

}