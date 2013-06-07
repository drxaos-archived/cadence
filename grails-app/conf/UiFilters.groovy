
class UiFilters {

    def springSecurityService

    def filters = {
        userDetails(controller: '*', action: '*') {
            before = {
                //println "before ${controllerName} : ${actionName}"
            }
            after = { Map model ->
                //println "after ${controllerName} : ${actionName}"
                if(model){
                    model["ui_person"] = springSecurityService.currentUser
                }
            }
            afterView = { Exception e ->
                //println "afterView ${controllerName} : ${actionName}"
            }
        }
    }
}
