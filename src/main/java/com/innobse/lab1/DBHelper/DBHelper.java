package com.innobse.lab1.DBHelper;

import com.innobse.lab1.Const;
import com.innobse.lab1.DBHelper.models.ITable;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import static com.innobse.lab1.DBHelper.STC_Connection.Connections.DB_STC;

/**
 * Created by bse71 on 21.02.2017.
 */
public final class DBHelper {
    private static final Logger log = Logger.getLogger(DBHelper.class);

    public static <T extends ITable.ICortege> void getAll(String table, Collection<T> target, Class classCortege){
        String sql = "SELECT * FROM " + table + ";";
        Connection conn = DB_STC.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            T cortege;
            while(rs.next()){
                cortege = (T) classCortege.newInstance();
                target.add(cortege);
                for (Field f : classCortege.getDeclaredFields()) {
                    f.setAccessible(true);
                    setField(cortege, rs, f, f.getName());
                }
            }

        } catch (SQLException e) {
            log.error(Const.ERROR + e.getMessage());
        } catch (IllegalAccessException e) {
            log.error(Const.ERROR + e.getMessage());
        } catch (InstantiationException e) {
            log.error(Const.ERROR + e.getMessage());
        }
    }

    public static <T extends ITable.ICortege> void setAll(String table, Collection<T> content, Class tableClass){
        Field[] fields = tableClass.getDeclaredClasses()[0].getDeclaredFields();
        String query = "";

        //gen sql string
        StringBuilder sb = new StringBuilder("INSERT INTO ");
        sb.append(table);
        sb.append("(");
        sb.append(fields[0].getName());
        for (int i = 1; i < fields.length; i++) {
            sb.append(", ");
            sb.append(fields[i].getName());
        }
        sb.append(") VALUES(?");
        for (int i = 1; i < fields.length; i++) {
            sb.append(", ?");
        }
        sb.append(");");
        String sql = sb.toString();

        Connection conn = DB_STC.getConnection();
        try {
            for (T tmp : content) {
                PreparedStatement ps = conn.prepareStatement(sql);
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);
                    //if (field.getName().equals("id")) continue;
                    ps.setObject(i+1, fields[i].get(tmp));
                }
                query = ps.toString();
                ps.execute();
            }

        } catch (SQLException e) {
            log.error(Const.ERROR + e.getMessage());
        } catch (IllegalAccessException e) {
            log.error(Const.ERROR + e.getMessage());
        }
    }

    private static <T extends ITable.ICortege> void setField(T cortege, ResultSet rs, Field field, String nameField) throws SQLException, IllegalAccessException {

        switch(field.getType().getSimpleName()){
            case "Character":
            case "char": field.set(cortege, rs.getString(nameField).charAt(0)); break;
            case "Date": field.set(cortege, rs.getDate(nameField)); break;
            case "Timestamp": field.set(cortege, rs.getTimestamp(nameField)); break;
            case "short": field.setShort(cortege, rs.getShort(nameField)); break;
            default: field.set(cortege, rs.getObject(nameField)); break;
        }
    }

}
