class UrlMappings {

	static mappings = {
		"/gui/$controller/$action?/$id?" { }
/*		
		"/api/$e/$id?" {
			controller = { "API_$e" }
			action = {
				if(params.id)
					switch(request.method) {
						case "DELETE": "delete"; break;
						case "PUT": "update"; break;
						default: "show"
					}
				else
					switch(request.method) {
						case "POST": "save"; break;
						case "PUT": "search"; break;
						default: "list"
					}
			}			
		}
*/		
		"/api/service" {
			controller = "API_Service"
			action = [GET:"list", PUT:"search", POST:"create"]
		}
		"/api/service/$id" {
			controller = "API_Service"
			action = [GET:"read", PUT:"update", DELETE:"delete"]
		}

		"/api/specification" {
			controller = "API_Specifications"
			action = [GET:"list", PUT:"search"]
		}
		"/api/specification/$id" {
			controller = "API_Specifications"
			action = [GET:"read"]
		}

		"/api/type" {
			controller = "API_Types"
			action = [GET:"list"]
		}
		"/api/type/$id" {
			controller = "API_Types"
			action = [GET:"read"]
		}

		"/api/predicate" {
			controller = "API_Predicates"
			action = [GET:"list"]
		}
		"/api/predicate/$id" {
			controller = "API_Predicates"
			action = [GET:"read"]
		}

		"/api/keyword" {
			controller = "API_Keywords"
			action = [GET:"list"]
		}
		"/api/keyword/$id" {
			controller = "API_Keywords"
			action = [GET:"read"]
		}

		"/"(controller:"default",action:"index")
		"500"(view:'/error')
	}
}