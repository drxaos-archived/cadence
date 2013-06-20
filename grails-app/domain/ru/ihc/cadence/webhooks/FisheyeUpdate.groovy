package ru.ihc.cadence.webhooks

import grails.converters.JSON

class FisheyeUpdate {

    static constraints = {
        json nullable: false, maxSize: 10000
        date nullable: false
        story nullable: false
        tasks nullable: true
        csid nullable: false
    }

    static mapping = {
        table "webhooks_fisheye"
    }

    String json

    Date date

    String story
    String tasks

    String csid

    static FisheyeUpdate build(String json) {
        new FisheyeUpdate(json: json).reindex()
    }

    FisheyeUpdate reindex() {
        def data = data()

        this.date = new Date(data.changeset?.date)

        def matcher = (data.changeset?.comment =~ /^([A-Z]+-[0-9]+)\s(\((([A-Z]+-[0-9]+)[^)]*)+\))?.+$/)

        if (matcher.size() == 0) {
            return null
        }

        this.story = matcher[0][1]
        this.tasks = matcher[0][3]
        this.csid = data.changeset?.csid

        return this
    }

    def data() {
        JSON.parse json
    }
}
