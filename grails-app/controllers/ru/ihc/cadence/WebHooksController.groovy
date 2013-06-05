package ru.ihc.cadence

import grails.converters.JSON

class WebHooksController {

    def jiraWebhooksService

    def jira() {
        try {
            def json = request.JSON
            jiraWebhooksService.parseAndSave(json)
            render([status: "ok"] as JSON)
        } catch (JSONException) {
            render([status: "error", message: "Can't parse JSON"] as JSON)
        }
    }
}
