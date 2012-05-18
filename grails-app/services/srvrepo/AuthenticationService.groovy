package srvrepo

import groovyx.net.http.RESTClient

class AuthenticationService {

    static transactional = true

	static String SERVICE_ADMINISTRATOR = "service_administrator"
	static String REPOSITORY_ADMINISTRATOR = "repository_administrator"
	static String MASHUP_KOMPONIST = "mashup_komponist"
	static String ENDNUTZER = "endbenutzer"
	static String ADMINISTRATOR = "administration"
	static String MANDANTEN_ADMINISTRATOR = "mandanten_administrator"

    def isAuthorized(String ticket, String role) {
		def url = new URL(Configuration.findByName("autorisierungsAbfrage").evaluate([accessToken:ticket, rolle:role.toLowerCase()]))
		def autorisierungsAbfrage = new RESTClient("${url.protocol}://${url.authority}")
		try {
			def query_params = [:]
			if(url.query) {
				def query = url.query.split("&")
				for(q in query) {
					def items = q.split("=")
					query_params[items[0]] = items[1]
				}
			}
			def status = autorisierungsAbfrage.get( path: "${url.path}", query: query_params, headers: [Authorization: "OAuth2 $ticket"]).status
			return status == 200
		} catch(groovyx.net.http.HttpResponseException ex) {
			return false
		}
    }

}
