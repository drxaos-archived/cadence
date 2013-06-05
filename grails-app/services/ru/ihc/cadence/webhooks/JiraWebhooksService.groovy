package ru.ihc.cadence.webhooks

import grails.converters.JSON
import ru.ihc.cadence.exceptions.domain.DomainPersistException
import ru.ihc.cadence.utils.DateUtils

class JiraWebhooksService {

    def parseAndSave(json) {
        if (json == null){
            throw new IllegalArgumentException("Json must not be null")
        }

        def update = new JiraUpdate(
                json: json.toString(),
                date: DateUtils.now(),
                action: "unknown",
                ticket: "IHC-0",
        )

        if (update.validate() && update.save(flush: true)) {
            return update
        } else {
            throw new DomainPersistException("Can't save", update.errors.allErrors)
        }
    }
}
