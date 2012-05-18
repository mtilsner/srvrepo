package de.cocktail.srvrepo.predicates;

import de.cocktail.srvrepo.HelperService;
import de.cocktail.srvrepo.types.DatatypeHelper;
import java.util.List;
import java.util.ArrayList;
import srvrepo.Predicate;

public class FormulaHelper {
	public static String disassemble(Formula value)  {
		if(value == null) return null;
	
		if(value instanceof True) {
			return "True";
		} else if(value instanceof False) {
			return "False";
		} else if(value instanceof AndCombinator) {
			String s = "And(";
			for(Formula d: ((AndCombinator) value).getFormulas()) { s+=disassemble(d)+","; }
			return s.substring(0,s.length()-1)+")";
			
		} else if(value instanceof OrCombinator) {
			String s = "Or(";
			for(Formula d: ((OrCombinator) value).getFormulas()) { s+=disassemble(d)+","; }
			return s.substring(0,s.length()-1)+")";
			
		} else if(value instanceof NotCombinator) {
			return "Not(" + disassemble(((NotCombinator) value).getFormula()) + ")";
			
		} else if(value instanceof ExistsCombinator) {
			return "Exists(" + disassembleVariable(((ExistsCombinator) value).getVariable()) + "," 
					+ disassemble(((ExistsCombinator) value).getFormula()) + ")";
			
		} else if(value instanceof InstantiatedPredicate) {
			String s = ((InstantiatedPredicate) value).getPredicate()+"(";
			for(Variable v: ((InstantiatedPredicate) value).getVariables()) { s+=disassembleVariable(v)+","; }
			return s.substring(0,s.length()-1)+")";
			
		} else
			return null;
	}
	public static String disassembleVariable(Variable value) {
		if(value == null) return null;
		return value.getName()+":"+DatatypeHelper.disassemble(value.getType());
	}
	
	public static Formula assemble(String c) throws VariableConflictException{
		if(c == null || c.equals("")) return null;

		if(c.equals("True")) {
			return new True();
		} else if(c.equals("False")) {
			return new False();
		} else if(c.length() > 4 && "Or(".equals(c.substring(0,3))) {
			List<String> parts = HelperService.splitAtInEnvironment(c.substring(4,c.length()-1),",");
			List<Formula> formulas = new ArrayList<Formula>();
			for(String part: parts) { formulas.add(assemble(part)); }
			return new OrCombinator(formulas);
			
		} else if(c.length() > 5 && "And(".equals(c.substring(0,4))) {
			List<String> parts = HelperService.splitAtInEnvironment(c.substring(4,c.length()-1),",");
			List<Formula> formulas = new ArrayList<Formula>();
			for(String part: parts) { formulas.add(assemble(part)); }
			return new AndCombinator(formulas);
		
		} else if(c.length() > 5 && "Not(".equals(c.substring(0,4))) {
			return new NotCombinator(assemble(c.substring(4,c.length()-1)));
			
		} else if(c.length() > 9 && "Exists(".equals(c.substring(0,7))) {
			List<String> parts = HelperService.splitAtInEnvironment(c.substring(7,c.length()-1),",");
			return new ExistsCombinator(assembleVariable(parts.get(0)), assemble(parts.get(1)));
			
		} else {
			int i = c.indexOf("(");
			List<String> parts = HelperService.splitAtInEnvironment(c.substring(i+1,c.length()-1),",");
			List<Variable> variables = new ArrayList<Variable>();
			for(String part: parts) { variables.add(assembleVariable(part)); }
			Predicate predicate = HelperService.findPredicateByName(c.substring(0,i));
			if(predicate == null) return null;
			return new InstantiatedPredicate(predicate, variables);
	
		}		
	}
	public static Variable assembleVariable(String c) {
		if(c == null) return null;
		int i = c.indexOf(":");
		return new Variable(c.substring(0,i), DatatypeHelper.assemble(c.substring(i+1,c.length())));
	}
}