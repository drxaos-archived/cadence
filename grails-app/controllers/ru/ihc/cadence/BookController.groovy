package ru.ihc.cadence

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_BOOKS'])
class BookController {
    def scaffold = Book

    def test = {
        println "Hello test"
    }
}