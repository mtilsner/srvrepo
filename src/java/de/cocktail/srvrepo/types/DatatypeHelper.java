package de.cocktail.srvrepo.types;

import de.cocktail.srvrepo.HelperService;
import java.util.List;
import java.util.ArrayList;
import srvrepo.Basetype;

public class DatatypeHelper {
	public static String disassemble(Datatype value)  {
		if(value == null) return null;
		
		if(value instanceof TupleDatatype) {
			String s = "Tuple(";
			for(Datatype d: ((TupleDatatype) value).getTypes()) { s+=disassemble(d)+","; }
			return s.substring(0,s.length()-1)+")";
			
		} else if(value instanceof ListDatatype) {
			return "List(" + disassemble(((ListDatatype) value).getListType()) + ")";
			
		} else if(value instanceof MapDatatype) {
			return "Map(" + disassemble(((MapDatatype) value).getKey()) + "," + disassemble(((MapDatatype) value).getValue()) + ")";
			
		} else if(value instanceof Basetype){
			return ((Basetype) value).getName();
			
		} else
			return null;
	}

    public static Datatype assemble(String c) {
		if(c == null) return null;
	
		if(c.length() > 5 && "Map(".equals(c.substring(0,4))) {
			List<String> parts = HelperService.splitAtInEnvironment(c.substring(4,c.length()-1),",");
			return new MapDatatype(assemble(parts.get(0)), assemble(parts.get(1)));

		} else if(c.length() > 6 && "List(".equals(c.substring(0,5))) {
			return new ListDatatype(assemble(c.substring(5,c.length()-1)));

		} else if(c.length() > 7 && "Tuple(".equals(c.substring(0,6))) {
			List<String> parts = HelperService.splitAtInEnvironment(c.substring(6,c.length()-1),",");
			List<Datatype> types = new ArrayList<Datatype>();
			for(String part: parts) { types.add(assemble(part)); }
			return new TupleDatatype(types);

		} else
			return HelperService.findBasetypeByName(c);
	}
	
}