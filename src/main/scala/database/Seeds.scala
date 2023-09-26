package database
import model.InvestmentType
import model.Bank
import model.AccountType

object DatabaseSeeder{
  // Seed data
  val seedInvestmentType = Seq(
    InvestmentType(None, "Poupança"),
    InvestmentType(None, "CDB"),
    InvestmentType(None, "LCI"),
  )

  val seedBank = Seq(
    Bank(None, "Banco do Brasil"),
    Bank(None, "Caixa"),
    Bank(None, "Nubank"),
    Bank(None, "Inter"),
    Bank(None, "Will"),
  )

  val seedAccountType = Seq(
    AccountType(None, "Corrente"),
    AccountType(None, "Poupança"),
    AccountType(None, "Conjunta"),
    AccountType(None, "Salário"),
  )
}
