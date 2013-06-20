package ru.ihc.cadence.webhooks

import org.springframework.transaction.annotation.Transactional
import ru.ihc.cadence.exceptions.domain.DomainPersistException
import ru.ihc.cadence.exceptions.webhooks.WebhookSecurityException

class JiraWebhooksService {

    def grailsApplication

    def checkWebhookKey(String key) throws WebhookSecurityException {
        def configKey = grailsApplication.config.ru.ihc.cadence.webhooks.jira.key
        if (key != configKey) {
            throw new WebhookSecurityException("unauthorized")
        }
        return true
    }

    def countUpdates() {
        return JiraUpdate.count()
    }

    @Transactional
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

    @Transactional
    def parseAndSave(json) throws DomainPersistException {
        if (json == null) {
            throw new IllegalArgumentException("Json must not be null")
        }

        def update = JiraUpdate.build(json.toString())

        if (!update || !update.action) {
            return null
        }

        if (update.validate() && update.save(flush: true)) {
            return update
        } else {
            log.error("Can't save JiraUpdate: ${update.errors.allErrors}")
            throw new DomainPersistException("Can't save JiraUpdate: ${update.errors.allErrors}")
        }
    }
}
