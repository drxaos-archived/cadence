package ru.ihc.cadence.exceptions.webhooks

class WebhookSecurityException extends Exception {
    WebhookSecurityException() {
    }

    WebhookSecurityException(String s) {
        super(s)
    }

    WebhookSecurityException(String s, Throwable throwable) {
        super(s, throwable)
    }

    WebhookSecurityException(Throwable throwable) {
        super(throwable)
    }
}
