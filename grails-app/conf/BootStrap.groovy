import ru.ihc.cadence.Book
import ru.ihc.cadence.auth.Authority
import ru.ihc.cadence.auth.Person
import ru.ihc.cadence.auth.Role

class BootStrap {

    def personService

    def init = { servletContext ->

        // Check whether the test data already exists.
        if (!Book.count()) {
            Book.withTransaction {
                new Book(author: "Stephen King", title: "The Shining").save(failOnError: true)
                new Book(author: "James Patterson", title: "Along Came a Spider").save(failOnError: true)
            }
        }
        if (!Person.count()) {
            Person.withTransaction {
                def roleSuperadmin = new Role(name: "ROLE_SUPERADMIN", description: "Admin everything").save(failOnError: true)
                def roleHooks = new Role(name: "ROLE_HOOKS", description: "WebHooks").save(failOnError: true)
                def roleBooks = new Role(name: "ROLE_BOOKS", description: "Books").save(failOnError: true)

                def authSuperadmin = new Authority(name: "SUPERADMIN", description: "Super Administrators").addToRoles(roleSuperadmin).save(failOnError: true)
                def authAdmin = new Authority(name: "AUTH_ADMIN", description: "Administrators").addToRoles(roleHooks).save(failOnError: true)
                def authLib = new Authority(name: "AUTH_LIB", description: "Librarians").addToRoles(roleBooks).save(failOnError: true)

                personService.createPerson("admin", "admin@localhost", "Administrator", "12345", [authSuperadmin, authAdmin, authLib])
                personService.createPerson("operator", "operator@localhost", "Operator", "12345", [authLib])
            }
        }
    }

    def destroy = {
    }
}
