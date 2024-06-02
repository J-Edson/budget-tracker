package budgetTracker

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AssetSpec extends Specification implements DomainUnitTest<Asset> {

     void "test domain constraints"() {
        when:
        Asset domain = new Asset()
        //TODO: Set domain props here

        then:
        domain.validate()
     }
}
