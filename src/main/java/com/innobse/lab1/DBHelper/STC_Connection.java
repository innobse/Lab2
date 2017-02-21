package com.innobse.lab1.DBHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by bse71 on 21.02.2017.
 */
public class STC_Connection {
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost/stc";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "25467789";

    @Override
    protected void finalize() throws Throwable {
        Connections.DB_STC.getConnection().close();
        super.finalize();
    }

    public enum Connections{
        DB_STC;
        private final Connection conn;

        Connections(){
            Connection conn = null;
            try {
                Class.forName(DB_DRIVER);
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.conn = conn;
        }

        public final Connection getConnection(){
            return conn;
        }



    }


}
