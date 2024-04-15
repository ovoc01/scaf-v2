package com.noarthedev.scaffold.mapping.keys;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.noarthedev.scaffold.helper.Helper;
import com.noarthedev.scaffold.mapping.ForeignKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExportedKey extends ForeignKey{
   String reference;
   String referenceTable;

   public void init(ResultSet rs) throws SQLException {
      setReferenceTable(rs.getString("FKTABLE_NAME"));
      setReference(rs.getString("FKCOLUMN_NAME"));
      setType(Helper.toPascalCase(getReferenceTable()));
   }
}
