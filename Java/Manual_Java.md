# Java Manual

by Ing. Giovanni Frison

---

# JDK - Java Development Kit

It is important to chose the correct version of the JDK when starting programming in JAVA since backward compatibility can be an issue, in particular for large companies or project that invested in JAVA years ago and therefore are bounded by legacy to older version. an **LTS** version of the JDK is released every 3 years (currently JDK17) and minor release happen every 6 month.

Moreover, there are different vendors (Oracle, Amazon, IBM etc..) that release their own version of the JDK.

# Hello World

Following some basic information to start a java project.

## Keywords

Keyword are case sensitive special name used by jdk to perform specific task. For example:

* `public` is an **access modifier**, it defines the scope of the class, i.e. how other part of the code or someone else code can access the class.
* `static` 
* `void` indicates that the method won't return any information


## Methods

When running a program, java looks for the `main` method as an entry point

# General style rules

* semicolon is needed at the end of any statement
* indentation is not significant
* string are enclosed in double quotes only **"**
* **=** is the assign while **==** is the comparison 

---

# Primitives

## Variables

JAVA is as static programming language and as such it requires its variable to be defined with a precise datatype and this can't be modified during execution. the definition of a variable is called `declaration statement` amd it is composed by the data type keyword the variable name and optionally and expression that set the variable to a certain value.

```java
int myVar = 5
```

## Integers

### Int

Integers are declared with the keyword `int` and, depending on the operating system we can see the maximum and the minimum values that can be stored with `Integer.MAX_VALUE` (or MIN_VALUES). If we try to set a number greater than the max value (say the max value + 1) we get an **overflow** (or underflow) and the computer skips to the back to the minimum number (or the maximum), a behavior that we usually don't expect and don't want. Int has a *width of 32*

### Bytes

Byte datatype is meant for very small integers [-128, 127] on a x64 OS and their are used when we want to restrict the input, reduce the size or speed up the access to an int that fits in this range. A byte is composed by 8 bits therefore we say that a byte has a width of 8

### Short

Short are meant for medium range integers [-32768, 32767] and have a *width of 16* 

### Long

Long is meant for vey big integers and have a *width of 64* (the double of an Int). To tell the computer that a value is long we should also place a capital L after the numeric value assigned `long val = 100L` (if we don't do so, the number it treated like an Int).


### Arithmetic operations on Integers

When we perform an arithmetic operation on a datatype, by default the result is an Int, therefore we can directly assign to a Byte variable a Byte multiplied by two even if we already know that it would fit the Byte width. To tell the computer that the results of that operation is still a Byte we need to use `Casting`, the process to treat or convert a number from one type to another

```java
byte val = 10
byte notWorking = (val/2) // what is inside () is treated like an int
// we need casting
byte Working = (byte)(val/2) 
```
        
Casting is not needed when working with long since it will fall back to basic Int unless required by the width.


## Floating Numbers

### Float

Floats are *single precision* decimal numbers that occupy 32 bits. Like Int, double is the default decimal datatype, therefore, when writing literal float values we need to add the letter **f** after the value (`float myFloat = 5.25f`). 

### Double

Double are *double precision* decimal numbers that occupy 64 bits. We could use the suffix **d** after the literal value, but it is not required since double are the default java format. 

N.B. despite the logic, doubles are usually faster to process than floats (even if they occupy more space) because modern architecture and math ans scientific modules are optimized to work with doubles instead of floats.

### BigDecimal

Due to how floats and doubles are stored in memory, they cannot be used when precise calculations are required (e.g. currency conversion). Therefore, we need another datatype that handle the truncation exactly as specified so that arithmetic operation on decimals are exact an not approximate by floats approximate representation.


## Strings

### Char

The char datatype is meant to store a single digit but still it occupies 16 bits since it can store also Unicode characters (65535 different symbols with only 2 bytes). Unicode character can be input directly with their digit code as string preceded by **\u** (`'\u0044'` -> D).

### String

Strings datatype **are not actually primitives** but class instead with some exception over regular classes. A string can contain any sequence of any character upt to the width of Int MAX_VALUE (2.14 Billion). If we try to add another datatype to a string type, java is smart enough to understand that casting is required, therefore the conversion is done automatically.

Strings are an **immutable** datatype, therefore any modification to its original values require java to create a new variable under the hood. This is not particularly efficient, in fact we will use a class called **StringBuffer** that is mutable instead.


## Boolean

As for any programming languages boolean values are used a lot to create the logic behind a software. Also in java, we have two reserved keywords for boolean values: `true` and `false`.
The truth value of a variable can be reversed with the **negation symbol `!`**

```java
boolean var = true;
boolean negation = !var; // false
```


# Operators, operands and expression

Operators are those symbols that can be used to perform operations on variable such as addition, multiplication etc. Operands are the subjects of the operation. An expression is the whole block where the variable is defined as the combination of two or more operands with an operator

```java
// an example of expression
int myInt = 10 + 5; // + is the operator, 10 and 5 are the operands
```

## Operators shortcut

There are some shortcut that can be used when dealing with operators; some example:

* `var++` : add 1 to var
* `var--` : subtract 1 to var
* `var += 2` : in place addition to var


# The if-then Statement

The if-then statement in the most basic **conditional logic** constructor we have in JAVA; it allows to check if a condition evaluates to true or false and therefore to decide if a code block has to be executed or not.

```java
boolean human = false;
    if (human == false) {
        System.out.println("It is an Alien!");
    }
```

notice how the **if ()** line is not ended with a semicolon that is placed instead at the end of the condition. We could potentially avoid the code block (the curly brackets) but then we could only have one expression in the if-statement, moreover the code would be less readable!