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

## Installation / Running Magenta

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
```
- Integer
```
Integer int = 2;
```
- Decimal
```
Decimal pi = 3.141592;
```
- String
```
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
~~ This is a comment~
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



