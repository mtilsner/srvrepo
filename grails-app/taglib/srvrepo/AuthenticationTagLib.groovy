package srvrepo

class AuthenticationTagLib {

	def authenticationService

	static namespace = "auth"

	def hasOneRole = { attrs,body ->
		def roles = attrs.roles
		def isAuthenticated = false
		for(role in roles) {
			if(authenticationService.isAuthorized(session.oauth2, role)) isAuthenticated = true
		}
		if(isAuthenticated) out << body()
	}
	
	def hasAllRoles = { attrs,body ->
		def roles = attrs.roles
		def isAuthenticated = false
		for(role in roles) {
			if(!authenticationService.isAuthorized(session.oauth2, role)) isAuthenticated = false
		}
		if(isAuthenticated) out << body()
	}
	
}
