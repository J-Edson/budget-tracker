package budgetTracker

	
import grails.gorm.transactions.Transactional

class AssetController {

    def index () {
        def assetList = Asset.list()
        def totalBalance = 0
        for (asset in assetList) {
            totalBalance += asset.balance
        }
        println totalBalance
        [assetList: assetList, totalBalance:totalBalance]
    }

    def show (Long id) {
        def assetInstance = Asset.get(id)
        def assetList = Asset.list()
        [assetInstance:assetInstance, assetList:assetList]
    }

    @Transactional
    def save () {
        println "params " + params
        def assetInstance = new Asset(
            assetName: params.assetName,
            balance: params.balance
        )
        println assetInstance.assetName
        println assetInstance.balance
        assetInstance.save()
        println "AssetSave"
        redirect(action: "index")
    }

    @Transactional
    def delete () {
        println "params " + params
        def assetInstance = Asset.get(params.id)
        assetInstance.delete(flush: true)
        redirect(action: "index")
    }

    @Transactional
    def updateBalance (Long id) {
        println "%param%" + params
        def assetInstance = Asset.get(id)
        def assetBalance = assetInstance.balance
        def txnAmt = params.txnAmt.toDouble()
        if(params.transactionType.equals("debit")) {
            println "debitBalance"
            assetInstance.balance += txnAmt
        }else{
            println "creditBalance"
            if(assetBalance >= txnAmt){
                println "assetBalance < txnAmt"
                assetInstance.balance -= txnAmt
            }
        }
        println "%newBalance% " + assetInstance.balance  
        assetInstance.save()
        redirect(action: "index")
    }

    @Transactional
    def transferBalance (Long id) {
        println "%transferBalanceParams% " + params
        def creditAsset = Asset.get(id)
        def debitAsset = Asset.get(params.debitAssetID)
        def txnAmt = params.txnAmt.toDouble()

        if(txnAmt <= creditAsset.balance){
            creditAsset.balance -= txnAmt
            debitAsset.balance += txnAmt
        }

        creditAsset.save()
        debitAsset.save()
        redirect(action: "index")
    }

}