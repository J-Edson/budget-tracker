package budgetTracker

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class AssetCRUDControllerSpec extends Specification implements ControllerUnitTest<AssetCRUDController> {

     void "test index action"() {
        when:
        controller.index()

        then:
        status == 200

     }
}
