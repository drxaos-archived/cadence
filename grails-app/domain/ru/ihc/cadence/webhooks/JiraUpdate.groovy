package ru.ihc.cadence.webhooks

import grails.converters.JSON
import ru.ihc.cadence.utils.DateUtils

class JiraUpdate {

    static constraints = {
        json nullable: false, maxSize: 10000
        date nullable: false
        story nullable: false
        tasks nullable: true
        action nullable: true
    }

    static mapping = {
        table "webhooks_jira"
    }

    String json

    Date date

    String tasks
    String story

    String action

    static JiraUpdate build(String json) {
        new JiraUpdate(json: json, date: DateUtils.now()).reindex()
    }

    JiraUpdate reindex() {
        def data = data()

        def statusChanged = data.changelog?.items?.find { it.field == "status" }
        if (statusChanged) {
            this.action = statusChanged.toString?.toLowerCase()
        }
        this.tasks = data.issue?.key
        this.story = data.issue?.fields?.parent?.key

        if (this.tasks && !this.story) {
            this.story = this.tasks
            this.tasks = null
        }

        return this
    }

    def data() {
        JSON.parse json
    }
}
