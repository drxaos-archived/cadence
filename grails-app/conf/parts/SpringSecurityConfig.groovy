package parts

// Added by the Spring Security Core plugin:

grails.plugins.springsecurity.userLookup.usernamePropertyName = 'username'
grails.plugins.springsecurity.userLookup.usernamePropertyName2 = 'email'

grails.plugins.springsecurity.userLookup.userDomainClassName = 'ru.ihc.cadence.auth.Person'
grails.plugins.springsecurity.userLookup.authoritiesPropertyName = 'roles'
grails.plugins.springsecurity.authority.className = 'ru.ihc.cadence.auth.Role'
grails.plugins.springsecurity.authority.nameField = 'name'

grails.plugins.springsecurity.switchUser.targetUrl = '/index'
grails.plugins.springsecurity.useSwitchUserFilter = true

// salt
grails.plugins.springsecurity.userLookup.passwordPropertyName = 'epassword'
grails.plugins.springsecurity.password.algorithm = 'SHA'
grails.plugins.springsecurity.dao.reflectionSaltSourceProperty = 'salt'


//security {
//
//    controllerAnnotationStaticRules = [
//            '/switchuser': ['ROLE_ADMIN', 'ROLE_CUSTOMER_SWITCHER'],
//            '/switchuser/**': ['ROLE_ADMIN', 'ROLE_CUSTOMER_SWITCHER'],
//            '/switchuser*': ['ROLE_ADMIN', 'ROLE_CUSTOMER_SWITCHER'],
//            '/switchUserExit': ['IS_AUTHENTICATED_REMEMBERED'],
//            '/switchUserExit*': ['IS_AUTHENTICATED_REMEMBERED'],
//            '/switchUserExit/**': ['IS_AUTHENTICATED_REMEMBERED']
//    ]
//
//    requestMapString = """
//        CONVERT_URL_TO_LOWERCASE_BEFORE_COMPARISON
//        PATTERN_TYPE_APACHE_ANT
//    """
//
//}
