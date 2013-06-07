package ru.ihc.cadence.auth

import org.springframework.transaction.annotation.Transactional

class PersonService {

    def passwordGeneratorService
    def springSecurityService

    @Transactional
    def createPerson(String username, String email, String fullname, String password, List authorities) {
        def salt = passwordGeneratorService.makeSalt()
        def user = new Person(
                username: username,
                email: email,
                fullname: fullname,
                epassword: springSecurityService.encodePassword(password, salt),
                salt: salt,
                enabled: true,
                accountExpired: false,
                accountLocked: false,
                passwordExpired: false,
        )
        authorities?.each {
            user.addToAuthorities(it)
        }
        return user.save(failOnError: true)
    }

}
