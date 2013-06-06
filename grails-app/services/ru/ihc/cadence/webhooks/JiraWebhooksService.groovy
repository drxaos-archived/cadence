package ru.ihc.cadence.webhooks

import ru.ihc.cadence.exceptions.domain.DomainPersistException
import ru.ihc.cadence.utils.DateUtils

class JiraWebhooksService {

    def countUpdates() {
        return JiraUpdate.count()
    }

    def reindex() {
        def c = JiraUpdate.createCriteria().setMaxResults(1024)
        def scroll = c.scroll()
        while (true) {
            scroll.get().each {
                it.reindex()
            }
            if (!scroll.next()) {
                break
            }
        }
    }

    def parseAndSave(json) {
        if (json == null) {
            throw new IllegalArgumentException("Json must not be null")
        }

        def update = new JiraUpdate(
                date: DateUtils.now(),
                json: json.toString(),
        )

        if (update.validate() && update.save(flush: true)) {
            return update
        } else {
            log.error("Can't save JiraUpdate: ${update.errors.allErrors}")
            throw new DomainPersistException("Can't save JiraUpdate: ${update.errors.allErrors}")
        }
    }
}
