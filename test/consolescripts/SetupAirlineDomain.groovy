import srvrepo.* 

def s = new Basetype(name: "String") 
s.save() 

def c = new Basetype(name: "City") 
c.save() 

def c1 = new Attribute(name: "Name", type: s, basetype: c) 
c1.save() 

def a = new Basetype(name: "Airport") 
a.save() 
def a1 = new Attribute(name: "Name", type: s, basetype: a) 
a1.save() 
def a2 = new Attribute(name: "City", type: c, basetype: a) 
a2.save() 

def l = new Basetype(name: "Airline") 
l.save() 
def l1 = new Attribut(name: "Name", type: s, basetype: l) 
l1.save() 

def f = new Basetype(name: "Flight") 
f.save() 
def f1 = new Attribute(name: "FlightCode", type: s, basetype: f) 
f1.save() 
def f2 = new Attribute(name: "Origin", type: a, basetype: f) 
f2.save() 
def f3 = new Attribute(name: "Destination", type: a, basetype: f) 
f3.save()