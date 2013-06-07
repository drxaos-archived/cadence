package parts

environments {
    development {
        grails.logging.jul.usebridge = true
        disable.auto.recompile = false
        grails.gsp.enable.reload = true
    }
    test {

    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}
