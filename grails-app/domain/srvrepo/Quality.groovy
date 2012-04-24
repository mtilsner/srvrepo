package srvrepo

class Quality {
	static belongsTo = [service: Service, keyword: Keyword]

	String value
	byte[] file
	String fileName
	String contentType

	static mapping = {
		value(type: 'text')
	}

    static constraints = {
		file(maxSize: 1024*1024*50)
    }
}
