import srvrepo.*

import de.cocktail.srvrepo.types.*
import de.cocktail.srvrepo.predicates.*

def input = Basetype.findByName("City")
def output = new ListDatatype(Basetype.findByName("Flight"))

def _in = new Variable("input",input)
def _out = new Variable("output",output)

def precondition = new AndCombinator(
    [ new InstantiatedPredicate(Predicate.findByName("validCity"),[_in]),
      new InstantiatedPredicate(Predicate.findByName("cityWithAirport"),[_in])
    ]
)

def postcondition = new InstantiatedPredicate(Predicate.findByName("flightsFromCity"),[_out,_in])

def service = new Service(
    name: "FlightsFromCity",
    input: input,
    output: output,
    precondition: precondition,
    postcondition: postcondition
)
service.save() 