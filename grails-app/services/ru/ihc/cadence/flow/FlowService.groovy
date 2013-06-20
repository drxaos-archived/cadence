package ru.ihc.cadence.flow

import ru.ihc.cadence.webhooks.FisheyeUpdate
import ru.ihc.cadence.webhooks.JiraUpdate

class FlowService {

    List listEvents() {
        def jira = JiraUpdate.list().collect { [type: "jira", action: "", date: it.date, story: it.story, info: it.action] }
        def fisheye = FisheyeUpdate.list().collect { [type: "fisheye", action: "revision", date: it.date, story: it.story, info: it.csid] }

        return (jira + fisheye).sort { it.date }
    }
}
