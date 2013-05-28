package ru.ihc.cadence

class Book {
    String title
    String author

    static constraints = {
        title(blank: false)
        author(blank: false)
    }
}