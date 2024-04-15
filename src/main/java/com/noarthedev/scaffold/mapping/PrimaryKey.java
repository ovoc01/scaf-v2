package com.noarthedev.scaffold.mapping;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import com.noarthedev.scaffold.template.lang.DbField;

public class PrimaryKey extends Column {

    @Override
    public void init(ResultSet rs, Map<String, DbField> db) throws SQLException {
        setName(rs.getString("COLUMN_NAME"));
        ResultSetMetaData resultSetMetaData = rs.getMetaData();


        //System.out.printf("ito ny seraaa be  %s \n", rs.getString("TYPE_NAME"));


        //DbField dbF = db.get(resultSetMetaData.getColumnType(1));

        //setType(dbF.getName());
        //setImportToDo(dbF.getImports() == null ? null : dbF.getImports().get());
    }

}
