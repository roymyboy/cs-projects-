"SavingsAccount extends Account"
Account subclass: SavingsAccount[
	|interest|

	"constructor"
	SavingsAccount class [
		new: thisNumber customer: thisCustomer balance: thisBalance [
			^super new setAcct: thisNumber customer: thisCustomer balance: thisBalance.
		]
	]
	
	"initalizing variable"
	setAcct: thisNumber customer: thisCustomer balance: thisBalance[
		interest := 0.
		number := thisNumber.
		customer := thisCustomer.
		balance := thisBalance.
	]

	"balance and interent increament according to rate"
	accure: rate[
		balance := balance + (balance * rate).
		interest := interest + (balance*rate). 
	]
]
