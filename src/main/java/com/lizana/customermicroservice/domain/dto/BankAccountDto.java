package com.lizana.customermicroservice.domain.dto;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto {


  private String accountNumber; //numeor de la cuenta
  private String customerId;
  private String accountType;  // ahorro //cuenta corriente // plazo fijo
  private BigDecimal balance;  // saldo de la cuenta
  private int monthlyMovementLimit; //limite de movimientos mensuales
  private double maintenanceFee; //tarifa de mantenimiento
  private Date maturityDate;  //fecha de vencimiento
  private List<Signatory> signatoryes; // personas firmantes o titular de la cuenta
  private List<DepositAmountDto> deposits;  /// lista de depostos
  private List<WithdrawalAmountDto> withdrawalAmounts; //lista de retiros
  private double interestRate; //tasa de interes de la cuenta
  private int numberTransactions; //numoero maximo de transacciones
  private double transactionFee; //comision por trnasacciiones
  private LocalDate openingDate; // fecha de apertura de la cuenta
  private List<String> alerts;
// "low_balance","large_transaction" tipos de alerta que podria tener o emviarse al clietne
  private boolean temporaryBlock; //true or false  cuenta bloqueada
  private String accountStatus; //vip|normal
  private BigDecimal availableBalance;
	  // saldo disponible podria ser diferente devido a retenciones o bloqueos por deuda
  private List<String> bankReferences;
//"loan_account",  "credit_card" referencia a otros productos asociados a la cuenta


}
