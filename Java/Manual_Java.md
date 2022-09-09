# Java Manual

by Ing. Giovanni Frison

---

# JDK - Java Development Kit

It is important to chose the correct version of the JDK when starting programming in JAVA since backward compatibility can be an issue, in particular for large companies or project that invested in JAVA years ago and therefore are bounded by legacy to older version. an **LTS** version of the JDK is released every 3 years (currently JDK17) and minor release happen every 6 month.

Moreover, there are different vendors (Oracle, Amazon, IBM etc..) that release their own version of the JDK.

# Hello World

Following some basic information to start a java project.

# Keywords

*https://en.wikipedia.org/wiki/List_of_Java_keywords* -> list of java keywords

Keyword are case sensitive **reserved** name used by jdk to perform specific task. For example:

* `public` is an **access modifier**, it defines the scope of the class, i.e. how other part of the code or someone else code can access the class.
* `static` 
* `void` indicates that the method won't return any information


## Methods

When running a program, java looks for the `main` method as an entry point

# General style rules

* semicolon is needed at the end of any statement
* we can have more than one statement inline but is not good for readability
* indentation is not significant but helps readability
* string are enclosed in double quotes only **"**
* **=** is the assign while **==** is the comparison 
* there is no implicit conversion to boolean of particular symbols (e.g. 0 and 1 are only int, don't have an implicit boolean conversion)

---

# Primitives

## Constants

Constants are by definition immutable object that are usually defined at the root of the class to be accessible from all the methods. To be so, we define a constant with the keyword `private static final`:

```java
private static final String MY_ERROR_MESSAGE = "This is an Error!"
```

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

Being a class, String comes with a series of method attached that can be extremely useful for string manipulation and comparison; some of the most common are:

* `.toLowerCase()`
* `.isEmpty()`
* `.format("%.2f", myString)`


## Boolean

As for any programming languages boolean values are used a lot to create the logic behind a software. Also in java, we have two reserved keywords for boolean values: `true` and `false`.
The truth value of a variable can be reversed with the **negation symbol `!`**

```java
boolean var = true;
boolean negation = !var; // false
```

N.B. there is no implicit conversion to boolean of particular symbols (e.g. 0 and 1 are only int, don't have an implicit boolean conversion)


# Operators, operands and expression

*https://docs.oracle.com/javase/tutorial/java/nutsandbolts/opsummary.html* -> list of operators

*http://www.cs.bilkent.edu.tr/~guvenir/courses/CS101/op_precedence.html* -> operators precedence


Operators are those symbols that can be used to perform operations on variable such as addition, multiplication etc. Operands are the subjects of the operation. An expression is the whole block where the variable is defined as the combination of two or more operands with an operator

```java
// an example of expression -> myInt = 10 + 5
int myInt = 10 + 5; // + is the operator, 10 and 5 are the operands
```

## Operators shortcut

There are some shortcut that can be used when dealing with operators; some example:

* `var++` : add 1 to var
* `var--` : subtract 1 to var
* `var += 2` : in place addition to var

## Logical operators

Logical operators are used to compare boolean values (the standard rules of boolean logic applies, also short-circuit). In particular we have:

* `&&` : logical AND
* `||` : logical OR
* `&` : bitwise AND (comparison at bit level, more advanced)
* `|` : bitwise OR (comparison at bit level, more advanced)
* `==` : logical EQUALS TO
* `!` : logical NEGATION

## The Ternary operator

The ternary operator is essentially a shortcut for an inline if-then-else and is composed in java by so:

```java
boolean ternary = ourTest ? true : false;
```

In the example, **ourTest** is the variable/expression that is tested to be true or false, whats after the **?** is the true condition and after **:** is the false condition


# The if-then statement

The if-then statement in the most basic **conditional logic** constructor we have in JAVA; it allows to check if a condition evaluates to true or false and therefore to decide if a code block has to be executed or not.

```java
boolean human = false;
    if (human == false) {
        System.out.println("It is an Alien!");
    }
```

notice how the **if ()** line is not ended with a semicolon that is placed instead at the end of the condition. We could potentially avoid the code block (the curly brackets) but then we could only have one expression in the if-statement, moreover the code would be less readable!

## if-then-else

As usual, we can give an **else** condition to the if-then statement that will be executed in case the expression given is evaluated to false:

```java
int test = 101;
if (test < 100) {
    System.out.println("the test is < 100");
} else if (test > 100) {
    System.out.println("the test is > 100");
} else {
    System.out.println("something different");
}
```

**N.B variables created inside a code block are not accessible from outside the block scope! The opposite instead is possible, a code block can always access variable in its outer blocks scopes**

# The switch statement 

The switch statement is an alternative to a possible messy concatenation of nested if-elseif statement. From a python perspective, we are essentially talking of a dictionary (a defaultdict to be precise) where a specific output is set depending on the input. The reserved JAVA keyword is `switch(someValue)` where **someValue** will be the testing parameter. The switch statement can only be use with some primitive types: **int, long, byte, char, String, Enum**.

Here an example:

```java
switch (value) {
            case 1:
                System.out.println("You chose 1");
                break;

            case 2:
                System.out.println("You chose 2");
                break;

            case 3: case 4: case 5:
                System.out.println("You chose either 3, 4 or 5");
                break;

            default:
                System.out.println("You chose " + value);
        }
```

The limitation of the switch statement is that is checking only one variable while the if statement can contain different conditions not related.

N.B. the **break** keyword is necessary to close the case test otherwise java will execute also all the condition after the one correctly selected with the input value.

# The for loop

As always, the for statement is used to creates loop in our code. The for loop needs 3 parameters: an initial value, a termination criteria that will stop the loop when evaluated to false and an increment step. In JAVA the syntax is the following.

```java
for (int i = 0; i < 4; i++) {
    // for loop body
        System.out.println( i + "!");
    }
```

The keyword `break` can be used to terminate prematurely the looping while the keyword `continue` can be used to skip the iteration if a certain condition is met.

# The while loop

Instead a predefined number of times, we may want to loop until a specific condition is met; for this we need a while loop. The syntax in JAVA is the following:

```java
int count = 1;
while (count!=5) {
    // while loop body
    count ++
    }
```

The while loop is often used to create **infinite loops** with the expression `while(true)`; in this case the exit strategy needs to be coded with a **break** inside the while body, otherwise the loop will be de facto endless.

## The `do` while statement

The difference of a do-while loop is that we have an initial expression that is always executed at least one since the termination condition is checked only after. The syntax is the following: 

```java
int count = 0;
do {
    System.out.println(count);
} while(count <5);
```

In the example above, even if the initial value of *count* would have been 5 (thus evaluating to false in the while condition), we would have the execution of the **do statement** once; in a traditional while loop nothing would have been executed because the exit strategy would have been met before the beginning of the while body.

# Methods

A method is essentially a function bounded to a class instance. Methods can't be nested within each other. We can of course pass arguments and return variables from methods but we need to explicitly state the datatype:

```java
public static double kgToPound(double kilos) {
        double conversion = 2.205d;
        return kilos * conversion;
    }
```
If the methods has no return than the appropriate keyword is `void`.

```java
public static void kgToPound(double kilos) {
        double conversion = 2.205d;
        System.out.println("your weight in pound is: " + kilos * conversion);
    }
```

## Methods Overloading

Like in other programming language, overloading a function or a method means to have the same method defined multiple times with the same time but with a different number of arguments. Java will infer which method to call based on the number of argument we are actually passing. **println** is a common example of method overloading since it can be called with different datatype in argument and still produces the correct output. With overloading we improve the readability and the consistency of our code.










# Tools

## DiffMerge

DiffMerge is a tool used to visually compare and merge files on any OS










