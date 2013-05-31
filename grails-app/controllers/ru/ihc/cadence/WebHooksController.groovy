package ru.ihc.cadence

class WebHooksController {

    def jira() {
        log.error(request.getInputStream().readLines())
        log.error(request.JSON)
        log.error(params)
    }
}
