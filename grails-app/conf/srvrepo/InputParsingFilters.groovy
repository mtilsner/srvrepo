package srvrepo

import grails.converters.*

class InputParsingFilters {

    def filters = {
/*		search(controller:'service', action:'search') {
			before = {
				if(request.getHeader("Content-Type") == "application/json")
					params.searchString = request.JSON.toString(3)
				else {
					def json = JSON.parse(params.searchString)
					for(k in json.names)
						params[k] = json[k]
				}
			}
		}
        all(controller:'*', action:'*') {
            before = {
                if(request.getHeader("Content-Type") == "application/json") {
					def json = request.JSON
					for(k in json.names) params[k] = json[k]
				}
            }
        }*/
    }
    
}
