package ru.ihc.cadence.webhooks

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.web.json.JSONException
import ru.ihc.cadence.exceptions.webhooks.WebhookSecurityException

class WebHooksController {

    def jiraWebhooksService
    def fisheyeWebhooksService
    def flowService

    @Secured(['ROLE_HOOKS'])
    def index() {
        return [
                jira: jiraWebhooksService.countUpdates(),
                fisheye: fisheyeWebhooksService.countUpdates(),
                bamboo: "N/A",
                list: flowService.listEvents()
        ]
    }

    def jira() {
        try {
            jiraWebhooksService.checkWebhookKey(params.key)

            def json = request.JSON
            jiraWebhooksService.parseAndSave(json)

            render([status: "ok"] as JSON)
        } catch (WebhookSecurityException e) {
            log.info("Got wrong key: ${params.key}")
            render([status: "error", message: e.message] as JSON)
        } catch (JSONException e) {
            log.info("Got invalid JSON", e)
            render([status: "error", message: "Can't parse JSON"] as JSON)
        }
    }

    def fisheye() {
        try {
            fisheyeWebhooksService.checkWebhookKey(params.key)

            def json = request.JSON
            fisheyeWebhooksService.parseAndSave(json)

            render([status: "ok"] as JSON)
        } catch (WebhookSecurityException e) {
            log.info("Got wrong key: ${params.key}")
            render([status: "error", message: e.message] as JSON)
        } catch (JSONException e) {
            log.info("Got invalid JSON", e)
            render([status: "error", message: "Can't parse JSON"] as JSON)
        }
    }
}
