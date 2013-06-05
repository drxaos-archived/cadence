dataSource {
    dialect = "ru.ihc.cadence.hibernate.MySQLDialect"
}

hibernate {
    cache.use_second_level_cache = false
    cache.use_query_cache = false
    cache.provider_class = 'com.opensymphony.oscache.hibernate.OSCacheProvider'
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop','update'
            jndiName = "java:comp/env/jdbc/cadence"
            logSql = false
        }
    }
    test {
        dataSource {
            dbCreate = ""
            url = "jdbc:mysql://localhost:3306/cadence_test"
            logSql = false
            pooled = true
            driverClassName = "com.mysql.jdbc.Driver"
            username = "root"
            password = "root"
        }
    }
    production {
        dataSource {
            dbCreate = ""
            jndiName = "java:comp/env/jdbc/cadence"
            logSql = false
        }
    }
}
