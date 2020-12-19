<img src="https://github.com/angelina-tsuboi/Magenta/blob/master/styles/images/Logo.png" alt="Magenta logo" width="500" height="150"/>

## A structured, simple, and organized programming language made using Java

*Bottles of Beer Song* in Magenta 
```
~~ Classic Beer Song
task beerSong: Vacant() ->  
    String bottles;
    Integer i = 99;
    while [i >= 1] ->

        if [i == 1] ->
        bottles = "bottle";
        <-
        
        if [i > 1] ->
        bottles = "bottles";
        <-

        emit(i + " " + bottles + " of beer on the wall,");
        emit(i + " " + bottles + " of beer,");
        emit("Take one down, pass it around,");

        i = i - 1;
        emit(i + " bottles of beer on the wall!");
    <-
<-
```

## Website

[Magenta Official Website](https://www.easyelementfinder.com/Magenta/)

## Installation / Running Magenta

### Installing: 
```
git clone https://github.com/angelina-tsuboi/Magenta.git
```
### Running a Magenta File: 
```
cd Magenta/src
./rm-gen.sh && ./run.sh FILE_NAME.mgnta
```

## Features

### Class Structure
```
block Robot ->
  Integer age = 3;
  String name = "Bob";
  task greeting: Vacant() -> 
    emit "Hello, my name is " + age;
  <-
<-
```

### Function Structure
```
task getSum: Integer(Integer one, Integer two) ->
  Integer sum = one + two;
  pass sum;
<-
```

### Types
- Boolean
```
Boolean bool = true;
Boolean bool2 = false;
```
- Integer
```
Integer int = 2;
Integer age = 14;
```
- Decimal
```
Decimal dec = 289.3;
Decimal pi = 3.141592;
```
- String
```
String alphabet = "abcdef";
String intro = "Hello World";
```
- Vacant
```
task sayHello: Vacant -> 
  emit("hello");
<-
```
### Statements
- **Emit**: prints out a value
```
emit("Hello World!");
emit("Emit prints stuff out!");
```

- **Pass**: returns value from function or method
```
task getPi: Decimal() -> 
  pass 3.14;
<-
```
### Comments
- **One-line comments**:
```
~~ This is a comment
```
- **Multi-line comments**:
```
~* 
  This is a 
  multi-line 
  comment
  block!
*~
```

## Examples

### Function to Check if Number is Even or Odd
```
task evenOrOdd: Vacant(Integer number) -> 
    if(number % 2 == 0) ->
     emit("Number is even");
     pass;
    <-
    
    emit("Number is odd");
<-
```
### Simple Car Block (Class)
```
block Car ->
    Decimal fuel = 17.89;
    Integer miles = 88;
    String brand = "Mercedes";
    
    task introduce: Vacant() ->
        emit("This is a " + brand + " car!");
    <-
    
    task getFuel: Decimal() ->
        pass fuel;
    <-
<-
```
