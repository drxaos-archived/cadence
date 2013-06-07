package ru.ihc.cadence.auth

class Person {
    static hasMany = [authorities: Authority]

    static transients = ['roles']

    transient springSecurityService

    String username
    String email

    String fullname

    String epassword
    String salt = 'test-salt'

    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    static constraints = {
        username blank: false, unique: true
        username blank: false
        epassword blank: false
    }

    static mapping = {
        table "auth_person"
    }

    Set<Role> getRoles() {
        def result = new HashSet<Role>()
        authorities.each {Authority authority ->
            result.addAll(authority.roles)
        }
        return result
    }


    // Password

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('epassword')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        epassword = springSecurityService.encodePassword(epassword, salt)
    }
}
