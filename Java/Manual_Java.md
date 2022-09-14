# Java Manual

by Ing. Giovanni Frison

---

# JDK - Java Development Kit

It is important to chose the correct version of the JDK when starting programming in JAVA since backward compatibility can be an issue, in particular for large companies or project that invested in JAVA years ago and therefore are bounded by legacy to older version. an **LTS** version of the JDK is released every 3 years (currently JDK17) and minor release happen every 6 month.

Moreover, there are different vendors (Oracle, Amazon, IBM etc..) that release their own version of the JDK.

# Hello World

Following some basic information to start a java project.

## Keywords

*https://en.wikipedia.org/wiki/List_of_Java_keywords* -> list of java keywords

Keyword are case sensitive **reserved** name used by jdk to perform specific task. For example:

* `public` is an **access modifier**, it defines the scope of the class, i.e. how other part of the code or someone else code can access the class.
* `static` 
* `void` indicates that the method won't return any information

## General style rules

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

---

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

---

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

---

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

---

# The for loop

As always, the for statement is used to creates loop in our code. The for loop needs 3 parameters: an initial value, a termination criteria that will stop the loop when evaluated to false and an increment step. In JAVA the syntax is the following.

```java
for (int i = 0; i < 4; i++) {
    // for loop body
        System.out.println( i + "!");
    }
```

The keyword `break` can be used to terminate prematurely the looping while the keyword `continue` can be used to skip the execution of the code that is in the while block for the current iteration if a certain condition is met.

---

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

---

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

When running a program, java looks for the `main` method as an entry point


## Methods Overloading

Like in other programming language, overloading a function or a method means to have the same method defined multiple times with the same time but with a different number of arguments. Java will infer which method to call based on the number of argument we are actually passing. **println** is a common example of method overloading since it can be called with different datatype in argument and still produces the correct output. With overloading we improve the readability and the consistency of our code.


---

# Reading User-Input

N.B. *The Scanner class is part of the `java.util.Scanner` module*

```java
import java.util.Scanner
```

In order to read a user input in JAVA we need to use the class `Scanner` to create a scanner object, able to receive information in input from the system; in fact, the argument passed to the scanner will be **System.in** (while for printing to screen we use the **System.out**). The basic syntax is the following:

```java
Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = myScanner.nextLine();
        System.out.println("Your name is " + name);
        myScanner.close();
```

With the keyword `new` we are creating an instance of the class **Scanner** and we are storing in a variable called **name** the first line input by the user. After the operation of input are ended, the Scanner needs to be **closed**.

If we need to read more than one line in the same scanner, and in particular if one of the line to be read is a number (we need to use the method `myScanner.nextInt` in this case), we need to handle the fact that the user will have to **press the button return** that for the compiler has the same meaning of reading a next line; therefore we need an empty `myScanner.nextLine()` before reading some other user input data:

```java
Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter your year of birth: ");
        String year = myScanner.nextInt();
        myScanner.nextLine(); // handle next line character (enter key)
        //
        // other queries for the user to input
        //
        myScanner.close();
```

## Limiting the input

We want to avoid as much as possible our program o crush of course. Therefore, without talking about exception handling right now, we can use some of the Scanner built-in methods to check for the user input. For example, if we are expecting a number as input (or, to better say a string that can be directly casted into a number) we can use the method `scanner.hasNextInt()` and store the result into a boolean, do detect if the input can be casted into an **Int**; in this way we can structure our code around an if-then-else loop which check the condition of this boolean variable to proceed, avoiding the potential error in the tentative conversion of a string that can't be casted into an **Int** (e.g. "1989a").

---

# OOP in JAVA

*https://www.tutorialspoint.com/java/java_encapsulation.htm#:~:text=Encapsulation%20in%20Java%20is%20a,methods%20of%20their%20current%20class.* -> encapsulation

A class is essentially a blueprint for creating objects that can have many kind of behavior. 

The class definition begins with a statement composed by an `access modifier` (e.g. **public**) which defines the accessibility we are giving to the class (e.g. whether we can reach it from other classes or not), the `class` keyword and the custom defined `ClassName` (the standard in JAVA is to have capitalized names for classes).

Inside a class we have `field` which contains the `state components` of a class; as for the name class, also the fields begins with a statement but in this case it is most common to use the access modifier **private** due to the concept of `encapsulation`.

`Encapsulation` : the internal definitions of the fields of a class are hidden from the class's user perspective (unlike python where the concept of private does not exist). The variable or the methods defined as **private** inside the class are **accessible only from the method of the class itself an not from the instance of the class we create later**.

```java
public class Car {
    public String name;
    // encapsulation
    private String owner;
}
```

To create an object from a class, as for a primitive datatype, we need to specify the datatype, the **name of the instance** and then instantiate the class using the keyword `new`.

```java
Car ferrari = new Car(); // initialization of a new object of Car type
ferrari.name = "Enzo"; // the state name of the Class Car is accessible from outside the class since it has been defined as public
// ferrari.owner is not accessible since it has been defined as private 
```

However, leaving public access on variable is against the programming style of java which aims for absolute encapsulation. Therefore, the correct way to **access and modify** the state of a class is through the use of **methods** that instead are defined as **public** and therefore accessible from an instance of the class.

```java
public class Car {
    private String name;
    private String owner;

    public void setName(name) {
        this.name = name;
    }
}
```
N.B. notice the `this` keyword used inside the method **setName**; it refers to the class (it is equal to say Car.name) in order to distinguish the **name** that refers to the private state variable with the **name** that indicates the argument of the function setName.

Now we can set the name of our Car without illegally access a state variable but through an had hoc method of which behavior should have been structured in a way of not potentially harming the stability of the code.

```java
Car ferrari = new Car();
ferrari.setName = "Enzo";
```

Standard methods are usually divided into `setter` and `getters` depending on the fact that they are specialized to retrieve or to modify the state of an object. The meaning of having setter and getters is to be able to create specific rules or validations that will keep under control the behavior of the class; for example we may want to limit the length of our car name to a string of 10 characters and this rule can be easily implemented inside the method **setName**.

```java
// Inside the context of the Car class...
// setter
public void setName(name) {
        this.name = name;
    }

// getter
public void getName(name) {
        return this.name;
    }
```

When creating a class, java automatically equips it with a series of methods (to be precise, unless otherwise specified, a class always inherit from the `Object` java base class).

## Constructors  

A `constructor` is a special method that is used by JAVA when the `new ClassName()` is called to create an instance of our class. We can enrich the standard behavior of the constructor implemented by java adding more functionality to it (e.g. setting some default values for the state variables). Moreover, constructor can be **overloaded** to gain more flexibility in the class initialization.

```java
public class Car {
    private String name;
    private String owner;

    public Car(){
        //this is an empty constructor
    }

    // Overload the constructor
    public Car(String name, String owner){
        this.name = name;
        this.owner = owner;
    }
}
```

Once the constructor is overloaded with some required arguments, we can initialize a new Car object specifying those parameters directly without calling setters manually after initialization. Moreover, we can **call a constructor within another constructor** with a special use of the keyword `this`; for example, to allow for setting default values even when an empty constructor is called:


```java
```java
public class Car {
    private String name;
    private String owner;

    public Car(){
        // overload the "standard" constructor with another constructor to which default values are passed.
        this("default name", "default owner");
    }

    public Car(String owner){
        // partial overloaded constructor
        this("default name");
        this.owner = owner;
    }

    public Car(String name, String owner){
        this.name = name;
        this.owner = owner;
    }
}
```

**N.B. it is a common non-written rule in java to not use methods inside constructors since the object is still being created during initialization and some unexpected behavior might arise**




---

# Tools

## DiffMerge

DiffMerge is a tool used to visually compare and merge files on any OS










