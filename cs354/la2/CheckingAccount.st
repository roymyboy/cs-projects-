"CheckingAccount class extends Account"
Account subclass: CheckingAccount[
	
	"Constructor"	
	CheckingAccount class [
		new: thisNumber customer: thisCustomer balance: thisBalance [
			^super new setAccount: thisNumber customer: thisCustomer balance: thisBalance
		]
	]
	
	"initalizing the variables "
  	setAccount: thisNumber customer: thisCustomer balance: thisBalance[
		number:= thisNumber.
		customer:= thisCustomer.
		balance:= thisBalance.
	]

	accure: rate []
]
