package de.cocktail.srvrepo

import srvrepo.Basetype
import srvrepo.Predicate

class HelperService {

    static transactional = true

    public static Basetype findBasetypeByName(String name) {
		Basetype.findByName(name)
    }

	public static Predicate findPredicateByName(String name) {
		Predicate.findByName(name)
	}

	def static ENVIRONMENTS = [["(", ")"],["[","]"],['"','"']];
	def static findFirstIndexInEnvironment(String context, String item) {
		def counts = [:]
		def count = 0
		for(env in ENVIRONMENTS) counts[env] = 0
		for(int i=0; i<context.size(); i++) {
			for(env in ENVIRONMENTS) {
				if(context[i] == env[0]) counts[env]++
				if(context[i] == env[1]) counts[env]--
			}
			if(context[i] == item && !(counts.any {key,value -> value > 0}))
				return i
		}
		return -1;
	}
	public static List<String> splitAtInEnvironment(String context, String item) {
		def parts = []
		def i = findFirstIndexInEnvironment(context, item)
		while(i != -1) {
			parts.add(context[0..(i-1)])
			context = context[(i+1)..-1]
			i = findFirstIndexInEnvironment(context, item)
		}
		parts.add(context)
		return parts
	}
}
