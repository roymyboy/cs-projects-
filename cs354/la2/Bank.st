sNum"Bank class"
"Utsav Roy"

Object subclass: Bank[
 |accounts|

	"creates a new bank account "
	Bank class[
		new[
			^super new init.
		 ]
    ]
	"Initialize to set account"
	init [
        accounts := Set new.
	]
	
	"adds an account to set"
	add: account[
	    accounts add: account.
	]

	"loops throug all the account"
	accure: rate[
		accounts do: [ :account | account accure: rate].
	]

	"prints out all the bank account statement"
	asString[
		|r|
		r := ''.
		accounts do: [ :account | r:=r,(account asString), (Character nl asString)].
		^r 
	]
]

"assigning value to the bank account"
|bank| bank := Bank new.
|customer| customer := Customer new: 'Ann'.
|checking| checking := CheckingAccount new: '01001' customer: customer balance: 100.00.
bank add: checking.
|savings| savings := SavingsAccount new: '01002' customer: customer balance: 200.00.
bank add: savings.
bank accure: 0.02.
bank asString display. 
