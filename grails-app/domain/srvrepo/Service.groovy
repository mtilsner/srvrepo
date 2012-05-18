package srvrepo

import org.codehaus.groovy.grails.web.json.*

class Service {
	static belongsTo = [vendor: Vendor]
	static hasMany = [qualities: Quality, specifications: Specification]
	
	String name
	URL url
	Boolean activated

    static constraints = {
		name(unique: true)
    }
}
