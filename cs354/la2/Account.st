"Account class"

Object subclass: Account [

"declaring variable"
|number customer balance|

	"constructor"
	accure: rate [
		^self subclassResponsibility.
	]

	"returns balance"
	balance [
		^balance.
	]

	"adds deposited amount to the existiong balance"
	deposit: amount [
		balance := balance + amount.
	]

	"subtracts withdrawal amount from existing balance"
	withdraw: amount [
		balance := balance-amount.
	]
	
	"returns the account number, the customer and balance"
	asString [
		^(number asString),': ',(customer asString),': ',(balance asString).
	]
]
