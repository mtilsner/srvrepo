package srvrepo

import groovy.text.SimpleTemplateEngine

class Configuration {

	String name
	String value

    static constraints = {
		name(unique: true)
    }

	public String evaluate(Map bindings) {
		def engine = new SimpleTemplateEngine()
		def template = engine.createTemplate(value).make(bindings)
		return template.toString()		
	}

}
