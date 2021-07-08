package feature

import io.mockk.Ordering.SEQUENCE
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import kotlin.test.BeforeTest
import kotlin.test.Test

class PrintStatementFunctionalTest {

    private lateinit var account: Account
    private var console: Console = mockk()

    @BeforeTest
    fun begin() {
        account = Account(TransactionRepository())
    }

    @Test
    fun `print statement containing all transactions`() {

        account.deposit(1000)
        account.withdraw(100)
        account.deposit(500)

        account.printStatement()

        verifySequence {
            verify { console.printStatement("Date | Amount | Balance") }
            verify { console.printStatement("10/06/2021 | 500.00 | 1400.00") }
            verify { console.printStatement("02/06/2021 | -100.00 | 900.00") }
            verify { console.printStatement("01/06/2021 | 1000.00 | 1000.00") }
        }



    }

}