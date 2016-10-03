"Customer class"
Object subclass: Customer [
	|name|

	"constructor"
	Customer class [
		new: thisName [
			^super new init: thisName.
		]
	]

	"Initialize name"
	init: thisName [
		name := thisName
	]
	
	"Returns a string"
	asString[
		^(name asString)
	]	
]
