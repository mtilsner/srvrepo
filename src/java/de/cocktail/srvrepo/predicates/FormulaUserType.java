package de.cocktail.srvrepo.predicates;

import org.hibernate.usertype.UserType;
import org.hibernate.*;
import java.sql.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class FormulaUserType implements UserType {

    private static final int[] SQL_TYPES = {Types.VARCHAR};    
    public int[] sqlTypes() { return SQL_TYPES; }
	
    public Object deepCopy(Object o) { return o; }
    public boolean equals(Object x, Object y) { return x != null && y != null && ((Formula) x).equals((Formula) y); }
    public int hashCode(Object o) { return disassemble(o).hashCode(); } 
    public boolean isMutable() { return false; }
    public Object replace(Object original, Object target, Object owner) { return original; }
    public Class returnedClass() { return Formula.class; }

    public Object nullSafeGet(ResultSet rs, String[] names, Object owner) {
		try {
        	String result = rs.getString(names[0]);
        	if (result != null && result.trim().length() > 0) 
				return assemble(result, null);
			else
				return null;
        } catch(SQLException e) {
			return null;
		}
    }
    public void nullSafeSet(PreparedStatement st, Object value, int index) {
		try {
        	st.setString(index, (String) disassemble(value));
		} catch(SQLException e) {}
    }

	public Serializable disassemble(Object value)  {
		return FormulaHelper.disassemble((Formula) value);
	}
    public Object assemble(Serializable cached, Object owner) {
		try {
			return FormulaHelper.assemble((String) cached);
		} catch(VariableConflictException e) { throw new RuntimeException(e); }
	}
}
