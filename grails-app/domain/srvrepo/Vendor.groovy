package srvrepo

class Vendor {
	static hasMany = [services: Service]

	String name

    static constraints = {
		name(unique: true)
    }
}
