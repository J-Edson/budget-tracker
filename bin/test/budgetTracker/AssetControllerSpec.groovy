package budgetTracker

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class AssetControllerSpec extends Specification implements ControllerUnitTest<AssetController> {

     void "test index action"() {
        when:
        controller.index()

        then:
        status == 200

     }
}
