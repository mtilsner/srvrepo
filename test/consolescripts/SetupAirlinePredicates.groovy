import srvrepo.*
import de.cocktail.srvrepo.types.*

def vC = new Predicate(name: "validCity",
                        input: Basetype.findByName("City"), 
                        description: "checks if the city is valid")
vC.save()

def vA = new Predicate(name: "validAirport",
                        input: Basetype.findByName("Airport"), 
                        description: "checks if the airport is valid")
vA.save()

def cWa = new Predicate(name: "cityWithAirport",
                        input: Basetype.findByName("City"),
                        description: "checks if the airport has a city")
cWa.save()

def aIc = new Predicate(name: "airportInCity",
                        input: new TupleDatatype([ Basetype.findByName("Airport"),
                                                   Basetype.findByName("City")]),
                        description: "checks if the airport is in this city")
aIc.save()

def fFc = new Predicate(name: "flightsFromCity",
                        input: new TupleDatatype([ new ListDatatype(Basetype.findByName("Flight")),
                                                   Basetype.findByName("City")]),
                        description: "checks if these flights originate form an airport in this city")
fFc.save()

def fFa = new Predicate(name: "flightsFromAirport",
                        input: new TupleDatatype([ new ListDatatype(Basetype.findByName("Flight")),
                                                   Basetype.findByName("Airport")]),
                        description: "checks if these flights originate form this airport")
fFa.save() 