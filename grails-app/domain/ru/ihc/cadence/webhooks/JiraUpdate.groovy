package ru.ihc.cadence.webhooks

class JiraUpdate {

    static constraints = {
        json maxSize: 10000
    }

    static mapping = {
        table "webhooks_jira"
    }

    Date date
    String ticket
    String action

    String json

}
