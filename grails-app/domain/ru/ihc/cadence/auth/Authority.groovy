package ru.ihc.cadence.auth

class Authority {
    static hasMany = [people: Person, roles: Role]
    static belongsTo = [Person, Role]

	String name
    String description

    static mapping = {
		cache true
        table "auth_authority"
	}

	static constraints = {
		name blank: false, unique: true
        description nullable: true
	}
}
