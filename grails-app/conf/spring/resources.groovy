import ru.ihc.cadence.security.CadenceUserDetailsService

// Place your Spring DSL code here
beans = {
    userDetailsService(CadenceUserDetailsService) { bean ->
        bean.autowire = "byName"
    }
}
