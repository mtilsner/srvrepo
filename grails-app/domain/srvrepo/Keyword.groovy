package srvrepo

class Keyword {

	String name
	String comparator

	static mapping = {
		comparator(type: 'text')
	}

    static constraints = {
		name(unique: true)
    }
}
