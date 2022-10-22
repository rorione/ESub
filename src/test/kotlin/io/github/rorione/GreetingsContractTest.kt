package io.github.rorione

import kotlin.test.Test
import org.web3j.EVMTest
import org.web3j.greeter.Greeter
import org.web3j.protocol.Web3j
import org.web3j.tx.TransactionManager
import org.web3j.tx.gas.ContractGasProvider
import kotlin.test.assertEquals

@Suppress("JUnitMalformedDeclaration")
@EVMTest
class GreetingsContractTest {
    @Test
    fun helloWorldTest(
        web3j: Web3j,
        transactionManager: TransactionManager,
        gasProvider: ContractGasProvider,
    ) {
        // Prepare
        val expectedValue = "Hello Blockchain World!"
        val greeter: Greeter = Greeter.deploy(web3j, transactionManager, gasProvider, expectedValue).send()

        // Action
        val actualValue: String = greeter.greeting.send()

        // Assert
        assertEquals(expectedValue, actualValue)
    }
}
