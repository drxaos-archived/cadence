package ru.ihc.cadence.exceptions.domain

class DomainPersistException extends Exception {
    DomainPersistException() {
    }

    DomainPersistException(String s) {
        super(s)
    }

    DomainPersistException(String s, Throwable throwable) {
        super(s, throwable)
    }

    DomainPersistException(Throwable throwable) {
        super(throwable)
    }
}
