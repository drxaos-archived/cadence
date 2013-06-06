package ru.ihc.cadence.webhooks

import grails.converters.JSON

class JiraUpdate {

    static constraints = {
        json nullable: false, maxSize: 10000
        date nullable: false
        ticket nullable: true
        parent nullable: true
        action nullable: true
    }

    static mapping = {
        table "webhooks_jira"
    }

    Date date
    String ticket
    String parent
    String action

    String json

    def setJson(String json) {
        this.json = json
        reindex()
    }

    def reindex() {
        def data = data()

        def statusChanged = data.changelog?.items?.find { it.field == "status" }
        if (statusChanged) {
            this.action = statusChanged.toString?.toLowerCase()
        }
        this.ticket = data.issue?.key
        this.parent = data.issue?.fields?.parent?.key
    }

    def data() {
        JSON.parse json
    }
}
