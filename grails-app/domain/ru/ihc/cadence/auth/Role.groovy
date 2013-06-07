package ru.ihc.cadence.auth

class Role {
    static hasMany = [authorities: Authority]

    String name
    String description

	static mapping = {
		cache true
        table "auth_role"
	}

	static constraints = {
		name blank: false, unique: true
        description nullable: true
	}
}
