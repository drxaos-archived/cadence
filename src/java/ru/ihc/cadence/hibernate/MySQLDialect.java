package ru.ihc.cadence.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;

public class MySQLDialect extends MySQL5InnoDBDialect {
    public MySQLDialect() {
        super();
        registerFunction("date_add_interval", new SQLFunctionTemplate(Hibernate.DATE, "date_add(?1, INTERVAL ?2 ?3)"));
        registerFunction("date_sub_interval", new SQLFunctionTemplate(Hibernate.DATE, "date_sub(?1, INTERVAL ?2 ?3)"));
        registerFunction("days_from_0", new SQLFunctionTemplate(Hibernate.INTEGER, "TO_DAYS(?1)"));
        registerFunction("inet_aton", new StandardSQLFunction("inet_aton", Hibernate.LONG));
    }
}
