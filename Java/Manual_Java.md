# Java Manual

by Ing. Giovanni Frison

## JAVA LANGUAGE UPDATES

*https://docs.oracle.com/en/java/javase/19/language/java-language-changes.html* 

---

# JDK - Java Development Kit

It is important to chose the correct version of the JDK when starting programming in JAVA since backward compatibility can be an issue, in particular for large companies or project that invested in JAVA years ago and therefore are bounded by legacy to older version. an **LTS** version of the JDK is released every 3 years (currently JDK17) and minor release happen every 6 month.

Moreover, there are different vendors (Oracle, Amazon, IBM etc..) that release their own version of the JDK.

# Hello World

Following some basic information to start a java project.

## Keywords

*<https://en.wikipedia.org/wiki/List_of_Java_keywords>* -> list of java keywords

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

## Naming convention

*<https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html>*

---

# Access modifier

*<https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html>*

Access modifier are essentially keywords that define the accessibility of classes, methods and variables.

Following, a list of the access modifier :

* `public`: the object is visible to all classes everywhere, both from the same package or even if imported from an outside package
* `private`: the object is visible only from within the class in which it is declared (not visible even in subclasses)
* `protected`: the object is visible anywhere in it own package and also in subclasses that are defined in other packages (same as package-private except for subclasses)
* `package-private`: meaning that no access modifier is explicitly stated

N.B. Methods without access modifier are inherently public

At Top level (the outer level of a .java file) we have the following limitations:

* Only **classes, interfaces and enums** can exist, everything else must be enclosed in one of these
* Top level classes **CAN'T** be private

N.B. as a rule of thumb, be as restrictive as it makes sense for the application you are developing; public should be avoided unless specifically needed.

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

# Generics

**N.B. DON'T USE RAW TYPES!**

Prior to java 1.5 there was no definition of generics types and a data structure, say an ArrayList could only be initialized as `raw type` (without specifying the type of objects it will contains) and this is a very unsafe operation for a structured language such as java. Right now it is still possible to initialize an objects to raw type but only to ensure backward compatibility, **it should never be done!** we have generics now

```java
ArrayList items = new ArrayList();
items.add(1);
items.add("a");
// for what concerns the compiler, what we have done is perfectly fine, since no type has been declared for the list "items".
// so it is clear how easily this can be a source of bugs in our code!
```

What we should do is to declare `parametrized types` in angle brackets <> in the statement of the data structure

```java
ArrayList<Integer> items = new ArrayList<>(); //parametrized type <Integer>, the second one is not necessary but should be placed empty <>
```

## <T> generic type parameter

So, we now know that we should never user raw typed classes because introduce liability in our code, but still we need a way to define in general terms the type passed at initialization to our class, both for validation and for reducing the quantity of duplicated code (if we need a class to behave differently for each type passed we could potentially create a class for each exception, but this is far from optimal in DRY terms).

To solve this problem the `<T>` notation was introduced, that indicates a `generic type`. A class defined with a generic type input will have the ability to adapt to different data types, since the final state will be reached only in the initialization statement, when the **<T>** will be replaced by the actual type we need.

**Example**
Imagine we have a class **Team** tha can receive objects representing the team players; players are created extending a **Player** abstract class, one for each type of sport. We want to use this class for different sports, therefore the Team class needs to have the ability to recognize the type of player it is receiving (we cant have a football and a basketball player in the same team). As said before, the easiest solution would be to duplicate the team class, one time for each sports (FootballTeam, BasteTeam etc..), but this will duplicate codes that essentially perform the same task. The java solution is to define the team class with the ability to receive a generic type (instead of a **Player** type)

```java
public class Team<T> {
    /*
    Some variables
    */
    private ArrayList<T> teamMembers = new ArrayList<>();
    /*
    methods common to all the kind of team
    */
}
```

Now, supposing we have 2 class **extended** from the abstract class **Player**, **FootballPlayer** and **BasketPlayer**, we can initialize the class **Team** explicating which kind of object it should receive instead of the generic `<T>`:

```java
// inside Main..
Team<FootballPlayer> juventus = new Team<>();
Team<BasketPlayer> fortitudo = new Team<>();
```

N.B. once we specify the generic type parameters **<T>**, our class can receive only objects and not primitive variables like **int** (but it can receive the boxing class **Integer**).

### Extending generic type (bounded type parameter)

In the way we have written the **Team** class signature we don't have any validation regarding which type of class we are passing instead of the generic type **<T>**; this is unsafe since we could potentially pass a **String** type and our IDE won't be able to identify any compilation error. What we want to do is to tell java that the class **Team** can accept any subclass of the abstract class **Player**, and to do so we need to **extend** the generic type behavior:

```java
public class Team<T extends Player> {...}
```

In this way we can be sure that any object passed to **Team** will be compliant with the abstract class **Player**; in this way, if other methods referred to the **Player** class are invoked inside **Team** w won't need to cast variables into the **Player** class because java now knows that the object passed as argument must be of **Player** type.

We can apply multiple bounds to a generic type but this involves multiple inheritance and, as such, the use of interfaces. Multiple interfaces can be applied in concatenation with the `&` sign:

```java
public class Team<T extends Player & Coach & Manager> {...} // if Player is a class has to appear first in front of the interface Coach and Manager.
```

We can also have a mixture of one class and multiple interfaces but the first argument in the extension **MUST BE** the class

---

# Data structures

Until now we have seen so called `value types`, i.e. a variable directly holds the value of the prescribed datatype declared in the statement. Now we are going to investigate what are `reference types`, i.e. variables that holds a reference to the object assigned but not the object itself.

```java
int myInt = 10;
int mySecondInt = myInt;
myInt++;
// the two variables holds their value, therefore, even if they are created one as a copy of the other, their value is not linked
// and the ++ on miUnt won't affect mySecondInt

int[] myArr1 = new int[10];
int[] myArr2 = myArr1;
// since arrays are reference type, myArr2 that is not initialized with the keyword **new** is actually a copy of the memory reference of myArr1;
// the two are pointing to the same array of ten int, and therefore, a change to one will affect also the other, since they are not actually holding the object, only its memory reference
```

## Array

In Java, an array is an object that store multiple elements of the same type. To tell the jdk that we are coding an array we need to inter in the statement, after the datatype specification, the square brackets `[]`.

```java
int [] myIntArray = new int[10];
myIntArray[5] = 69;
```

With the line above we have created an array that contains **10** elements of type **int** and we are storing at 6th position (java starts counting from 0). If we already know how many and which elements will be in the array, we explicitly input at creation phase without the **new** keyword:

```java
int [] myIntArray = {0,1,2,3,4,5,6,7,8,9};
```

We are implicitly telling java that we want to initialize an array object with 10 elements from 0 to 9. (this king of assignation with `{}` can be done only at the initialization of the array). A third way to initialize an array can be through a for loop where essentially we compress the first approach in the looping (myIntArray[i] = someFormula in the loop). If not specified, the initialization of an array will use the default value for the datatype chosen; for **int** it will be **0**, for **boolean** will be **false** while for **String** and other objects will be **null**.

Arrays are objects that comes equipped with useful methods like `.length` to retrieve the length of the array.

One useful methods from the `java.util.Arrays` class, is the method `.toString()` to which we can pass an array and have its content printed (like a python list).

If we use a negative index or an out of bound index we get the exception `ArrayIndexOutOfBoundsException`.

## List and ArrayList

*<https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html>*

Lists in java are an `interface` (which extends the **Collection** interface), i.e. a class that can be inherit from multiple classes (unlike **extends** which imply single inheritance), and `ArrayList` is class that **implements** (the keyword for inherit an interface) the interface `List` (actually an **AbstractList** which in turns implement the List interface). The main difference with standard **Array** is that ArrayList are dynamic in size, therefore in the initialization we don't need to specify the number of elements that will be contained (also because often is something difficult to know a priori).

The initialization for objects like ArrayLists is different from value types or simple arrays; as we have seen we usually declare a datatype in front of the constructor, for ArrayLists, since they are containers, we need to tell java which type of elements will be contained and this is performed with the `ArrayList<T>` notation. This is called a `generic` and the **T** stands for type, meaning that at initialization we need to replace the **T** with the actual datatype we are going to insert in the list.

```java
import java.util.ArrayList;
private ArrayList<String> myList = new ArrayList<String>(); // initialization
myList.add(0, "first element"); // add with index
myList.add("last element"); // add without index
myList.remove(0); // remove element at index 0
myList.contains("first element"); // true or false
myList.indexOf("first element"); // return index of the element if exist
myList.get(0); // get element at index 0
```

The `()` at the end of the statement are needed to invoke the constructor behind ArrayList since it is actually a class and has to be initialized. The insertion of elements inside the list is performed simply using the `.add()` method implemented in the class (overloaded to be used with or without specifying the position of insertion), and its definition will handle under the hood all the resizing, allocation etc.

### Autoboxing and Unboxing

The reference datatype that we pass to the ArrayList constructor needs to be a class, therefore primitive type like **int** or **double** are not accepted. A way to overcome this issue would be to create a class that simply receive and int as argument in the constructor (a wrapper essentially to the **int** primitive) and pass this class as datatype to the array list, but it become tedious quite fast.

The `autoboxing` is the java functionality builtin to take care of this problem. All the primitives in java as a boxing class that does exactly what the wrapper above was supposed to do; instead of passing the **int** primitive type we pass the boxing class `Integer` to the ArrayList (same applies for the other primitive datatype).

```java
ArrayList<Integer> myIntList = new ArrayList<Integer>; // initialize a list of int through the boxing class Integer
myIntList.add(Integer.valueOf(10)); //adding the value 10 to the list
myIntList.get(0).intValue(); // unboxing, we are retrieving the element at index 0 which is an instance of the Integer class and we are extracting its value back to a primitive int
```

In the last row we see the concept of `unboxing`, i.e. retrieving the primitive type from the boxing class.

N.B. Java should be able to extract from the context the fact that we are adding/retrieving an int to the ArrayList even if we don't explicitly write the **.valueOf** and **.intValue()** methods.

## LinkedLists

LinkedLists are another type of list data structure that differs from standard list for the way they store the reference in memory to the objects that they contain. As a matter of fact, lists use continuos blocks of memories to store their content while in LL each slots contains the object and the particular address in memory of that object. Each slot is called **node** and contains the object with it's memory address and a pointer to the next element.

Long story short, LL has a computational efficiency greater than standard lists in **insertion** and **deletion** of elements (O(1) compared to O(n) since standard lists have to shift all the elements. th opposite happens for elements retrieval where list performs better O(1) while LL have a O(n/2).

* storage: 4 bytes for each integer, 8 bytes for each double

## Map interface

*<https://docs.oracle.com/javase/tutorial/collections/interfaces/map.html>*

Map is not a true **Collections** but is an unordered set of key:value pairs.

```java
Map<String, String> mapName = new HashMap<>();
// first parameter is the key and the second the value
mapName.put("key1", "value associated to key1"); // insertion 
mapName.get("key1"); // retrieve
mapName.remove("key1"); // remove a key:value pair
```

For a particular key, only one value can exist, therefore, the standard behavior is that idf we try to place a new value for an already taken key, than the old value will be replaced.

## Set interface

*<https://docs.oracle.com/javase/tutorial/collections/interfaces/set.html>*

The main difference between sets and lists is that the sets are un **UNORDERED collection of UNIQUE elements**. The interface implements the standard methods **.add()**, **.remove()**, **isEmpty()**  etc.. to manipulates the objects contained in the set. A peculiarity is that we **cannot retrieve an element directly** from a set but only check for it existence with **.contains()**. The most efficient way to use sets is probably through the **hashSet**, essentially an hashmap composed only by keys; again the order of insertion is not retained as well as the order over time might change. All the operation on set theory are allowed.

## LinkedHashMap and LinkedHashSet

*<https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedHashMap.html>*

Essentially we are talking about hashMaps and hashSet that retain the order of insertion due to a linked list running below. Their main use it when writing and reading Maps or Set to file, if we don't use their corresponding linked version we can't be sure that we are writing and then reading back the content in the same order, and this might cause some problem in the program's logic.

---

# Operators, operands and expression

*<https://docs.oracle.com/javase/tutorial/java/nutsandbolts/opsummary.html>* -> list of operators

*<http://www.cs.bilkent.edu.tr/~guvenir/courses/CS101/op_precedence.html>* -> operators precedence

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

## for-each

To improve the inelegance of the standard for loop, the for-each has been introduces to iterate over iterators types of objects. The syntax is more clean and concise, therefore it can be used always, the only limitation is that the for-each hides the iterator and therefore it is not possible to call **.remove** or filtering in general (also element replacements during traversing).
The syntax is the following:

```java
int [] intArray = { 10, 20, 30, 40, 50 };
        
for( int value : intArray ) {
   System.out.println( value );
}
```

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

# Try-catch-finally

The **try** statement is the most common construct to handle exceptions. In the try block we define statements that, for some reason, might throw an exception; a classic situation where java obligates us to use a `try-catch` is when dealing with I/O operation due to the checked exception **IOException** (see I/O chapter). If a particular exception is expected we can catch it in the `catch-block` where we can define the behavior of our program: we can decide to handle that exception or expose it (it is not always a good practice trying to by pass all the exceptions, some of them needs to be thrown due to their relevance). Like in Python, also java has a `finally` keyword that can be used in place of or in addition to the catch-block; the peculiarity of the final block is that it is always executed (unless the jvm crush!) therefore ensuring some fallbacks operability (e.g. closing a file to avoid data leakage or its inaccessibility).

```java
try{
    //Code that is checked for error
} catch(Exception e){
    //Code runs when error is caught
} finally {
    //This run no matter what
}
```

## try-with-resources

*<https://docs.oracle.com/javase/7/docs/technotes/guides/language/try-with-resources.html>*

From jdk 7 an addition to the try statement, called `try-with-resources`; the behavior is similar to a context manager and can be used with all the classes that inherit from `java.lang.AutoCloseable`. In the case of opening a file, the try-with resources ensure that the file read stream is closed even if an exception occur. Another advantage of this syntax is that in a try-finally block, the potential errors rasing in the try block are shadowed by the finally statement; here instead, the error of the try block is exposed (and usually is the one we are interested in.. e.g. why we couldn't open the file rather than why it didn't close).

```java
try (FileWriter localFile = new FileWriter("file.txt")) {
    // some I/O operations
    }
```

We can place more than one resources simply separating them with a semicolon `try (resource1; resource2) {}`

---

# Lambda Expressions

Introduced in jdk 8, **lambda expression** are a handy way to work with interfaces that **has only one method**.
It is composed of 3 parts:

* the argument list `()`
* the arrow token `->`
* the body : the code that needs to run



```java
// creating a runnable thread and calling it directly, without the use of a Thread class or an anonymous class
new Thread(()-> System.out.println("Printing from runnable")).start();
```

In the example above, the Thread implements the **Runnable** interface that has only the method **Run** that require no arguments; the compiler is therefore able to match the argument list with the requirement of the **Run** method.
Interfaces like **Runnable** that contains only one method are referred as `functional interfaces`
















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

## Methods Overloading vs Overriding

Like in other programming language, overloading a function or a method means to have the same method defined multiple times with the same time but with a different number of arguments. Java will infer which method to call based on the number of argument we are actually passing. **println** is a common example of method overloading since it can be called with different datatype in argument and still produces the correct output. With overloading we improve the readability and the consistency of our code.

Overloading is different from **polymorphism** but it is often referred by java developers as **compile time polymorphism** since it is the compiler that choose the appropriate version of the method depending on the number of argument and the return type.

In essence the rules for overloading a method are:

* having the same method name
* having different arguments

If this condition are satisfied, the the methods can have:

* different return types
* different access modifier
* different exception handling

**Overriding** instead means to redefine a method in a child class that is already present in the parent class (**derived method**); overriding is known as **runtime polymorphism** and **dynamic method dispatch** because the JVM decide at runtime which method to call. To declare to the compiler that we are overriding a derived method we need to place the `@Override` signature above the method in the child class.

In essence the rules for overriding a method are:

* having same name and **same arguments**
* return type can be a subclass of the return type in the parent class
* it can't have a lower access modifier
* it can't thrown new or broader exceptions

Moreover:

* methods can be overridden only in child classes
* constructor and private methods **can't be overridden**
* methods that are final cannot be overridden

## Wildcard ?

*<https://docs.oracle.com/javase/tutorial/extra/generics/wildcards.html>*

In methods is it possible to specify a wildcard with the symbol `<?>` when the object type passed to the method is not always uniquely defined. We can also bound a wildcard for example stating a mandatory extension to a specific class e.g. `List<? extends somClass>`.

A common example of wildcard usage is to print the elements of a Collection subclass using the for each loop:

```java
void printCollection(Collection<?> c) {
    for (Object e : c) {
        System.out.println(e);
    }
}
```

---

# I/O functionality

## Reading User-Input

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

Other useful methods of the scanner class are:

* `scanner.useDelimiter("")` : to set a pattern for the delimiter while parsing a file
* `scanner.skip(scanner.delimiter)`: skip the delimiter while parsing
* `scanner.next()`: scan the next token, where a token is the input between two delimiters

### Limiting the input

We want to avoid as much as possible our program o crush of course. Therefore, without talking about exception handling right now, we can use some of the Scanner built-in methods to check for the user input. For example, if we are expecting a number as input (or, to better say a string that can be directly casted into a number) we can use the method `scanner.hasNextInt()` and store the result into a boolean, do detect if the input can be casted into an **Int**; in this way we can structure our code around an if-then-else loop which check the condition of this boolean variable to proceed, avoiding the potential error in the tentative conversion of a string that can't be casted into an **Int** (e.g. "1989a").

## Reading/Writing from/to file

To read or write to file there is a specific class : `java.io.FileWriter`. Unlike python, java doesn't provide a syntactic sugar for context manager (**with** block in python) therefore, in order to initialize an instance of **FileWriter** we have to deal with a checked exception that requires our file to be opened in a safe context. This is due to the fact that, if not handled, leaving a file open when a programs end or crush might lead to data leakage or to the inaccessibility of the output file/source. Therefore, the exception that needs to be handled is the `IOException` and this can be done with a **try block** or, since we are talking about a **checked exception** (an exception that is handled ar compilation time and not at runtime) be adding to the method signature  `throws IOException`:

```java
// exposing the checked exception in the method signature
public static void main(String[] args) throws IOException {
    try {
    String string = "This is going to be written to file";
    FileWriter file = new FileWriter("file.txt");
    for (String i: string.split(" ")) {file.write(i + "\n");}
    } 
}

// or with the try-catch
public static void main(String[] args) {
    FileWriter file = null;
    try {
        String string = "This is going to be written to file";
        file = new FileWriter("file.txt");
        for (String i : string.split(" ")) {file.write(i + "\n");}
    } catch (IOException e) {
        e.printStackTrace();
    } finally { if (file != null) {
        try {
            file.close();
            } catch (IOException e) {
            e.printStackTrace();
            }
        }
    }
}
```

To be noted, in the try-catch approach we need to initialize the **file** variable outside the try block due to the fact that the try block has it own locale scope that it is not accessible from outside; therefore, defining the variable inside the block won't allow us to further reference it in other part of the code.

A neater way is to use a **try-with-resources**, leaving to java the task of ensuring that the file will be closed also in the event of an exception:

```java
try (FileWriter file = new FileWriter("file.txt")) {
        // some manipulation on the file
        file.close();
    }
```

Similarly we can read from a file making use of the **Scanner** class

```java
public static void loadData() throws IOException{
        try(Scanner scanner = new Scanner(new FileReader("file.txt"));) {
            scanner.useDelimiter(",");
            while(scanner.hasNextLine()) {
                String someString = scanner.nextLine();
                System.out.println(someString);
            }
        }
```

### Buffer reader

*<https://docs.oracle.com/javase/tutorial/essential/io/buffers.html>*

A buffer reader reads text from the **input stream** and buffers the character into a character array. The main advantage of buffering is that is usually much more efficient than scanning the input line by line from a **FileReader**; this because buffer reads from a memory area called **buffer** while for **FileReader** the request is handled by the OS (triggering dicks access and network activity). The buffer reader implements the **Closable** interface and therefore its **IOException** can be handled in a try-with-resources block.

We can buffer in input and in output both bytes and characters.

```java
 try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("file.txt")))) {
        scanner.useDelimiter(",");
        while (scanner.hasNextLine()) {
            String someString = scanner.nextLine();
            System.out.println(someString);
        }
    } finally {scanner.close();}
```

The same concept applies to writing files:

```java
try (BufferWriter file = new BufferWriter("file.txt")) {
        // some manipulation on the file
        file.close();
    }
```

### Byte Stream

*<https://docs.oracle.com/javase/tutorial/essential/io/bytestreams.html>*

We can perform the same I/O operations but working with a stream of bytes; the only difference is that we need a different java class (`DataOutputStream`, buffered with `BufferedOutputStream`, and the same for reading but with the word **Input**) that has custom methods to decode/encode the proper java types (e.g. `.writeInt()`, `writeUTF` etc..).

```java
// an example of writing a file in a byte stream; the file i called locations.dat and contains the fields of an hasMap called locations<Int, String>
try (DataOutputStream file = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("file.dat")))) {
    for (Map<Integer, String> map : map.values()) {
        file.writeInt(map.getKey());
        file.writeUTF(map.getValues);
```

### Object Serialization/Deserialization

*<https://docs.oracle.com/javase/tutorial/jndi/objects/serial.html#:~:text=To%20serialize%20an%20object%20means,io>.*

We could decide to serialize an entire object instead of simple digits or string and to allow this java requires us to implement the `Serializable` interface (and also that the other class that are initialized inside our class are serializable as well). This interface doesn't have any methods but it is a flag for the jdk to say "hey, I may need to serialize this object!".

Another requirement that is not mandatory, but can create difficult-to-spot error, exception or compatibility issue depending on the compiler we use, is the `serialVersionUID` that is a sort of serial number for our class. This should have a **private** access modifier and should be of type **long**.

```java
try (ObjectOutputStream file = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("file.dat")))) {
    for(Map map : map.values()) {
        file.writeObject(map);
    }
}
```

### RandomAccessFile

*<https://docs.oracle.com/javase/tutorial/essential/io/rafs.html>*

Without entering too much into details, while until now we have read/wrote files sequentially it is possible also to access files in a non-sequential (or random) way. This means that the writer/reader needs to be able to store a pointer to the particular position at which we want to access.

## Java NIO.2 (New Input/Output)

*<https://docs.oracle.com/en/java/javase/15/core/java-nio.html>*

*<https://docs.oracle.com/javase/tutorial/essential/io/fileio.html>*

*<https://www.baeldung.com/java-nio-vs-nio-2>*

As an improvement to **java.io**, `java.nio` was introduced as an improvement mainly for the fact that allows I/O operations in a **non-blocking manner** (when using the **java.io** package a thread will block until the end of the reading/writing operation)and because improves the working with the **file system**.

### I/O with NIO.2

Differences between java.io and java.nio syntax:

The use of `Path` is preferred by **nio**, therefore, thanks to the `FileSystem` class, instead of instantiate a class representing a file, we initialize a **Path** object. The **BufferedWriter** is not created directly but through the `Files` class which accept a **Path** instance (**nio** can still accept file instance instead of path, e.g. ifa constructor requires it, but if possible it s always better to work with paths because they belong to a more robust class in terms of files handling).

```java
Path filePath1 = FileSystems.getDefault().getPath("file1.txt");
Path filePath2 = FileSystems.getDefault().getPath("file2.txt");
try (BufferedWriter file1 = Files.newBufferedWriter(filePath1);
    BufferedWriter file2 = Files.newBufferedWriter(filePath2)) {
    locFile.write("Something to write on file1");
    dirFile.write("Something to write on file2");
} catch(IOException e) {
    System.out.println("IOException: " + e.getMessage());
}
```

All the operation described above related to reading/writing files to characters or bytes with the standard I/O can be done equivalently with the NIO.2 package.

### The FileSystem

Let's first define what a filepath is: a filepath is an unique address to a directory or a file on a machine composed by **nodes** representing drive or folders (usually the root node is the driver location e.g. **C:\\**) separated by **delimiters** that can differ from one OS to another (e.g. backslash on windows and forward slash on Unix). Paths can be **absolute or relative** depending if they include the root node or not; often we use the concept of **current directory** meaning the relative path (**.** to indicate the cwd) from which we are working/launching our own application. When creating a java Path object with NIO we are specifying **FileSystems.getDefault().getPath("file.txt")**, where the path in **getPath()** is the relative path (since in the example the file is in the project directory) and the method **getDefault()** is used to set the current directory as root. Similarly we could decide to use a Path object with its method **get()** and inside it specify the absolute path to the directory/file we are interested in. In practice, absolute path aren't going to fit since our application will be installed by users in different machine and directories, therefore the application need to orient itself by using relative paths depending on where it is actually installed/launched.

```java
Path filePath = FileSystems.getDefault().getPath("file.txt"); // relative path - file in project directory (cwd)
Path filePath = FileSystems.getDefault().getPath("..\\file.txt"); // relative path - go back one folder
Path filePath = FileSystems.getDefault().getPath("files", "file.txt"); // relative path - enter folder files
Path filePath = Paths.get("C:\\path\\to\\file\\file.txt"); // absolute path
```

Useful methods from the Path class are:

* `.toAbsolutePath()` : return absolute path from a relative one
* `.normalize()` : always good practice to normalize a path received from a user since t removes all the redundant or placeholder names (like the dots notations)

N.B. also the standard java.io has the ability to handle paths but it is implemented with very few exception handling; moreover, it can't resolve symbolic links (link to a file that points to another file) used in networking to point to a remote location.

N.B. path separators can be OS-specific. For example, windows use a back slash while unix uses a forward slash. To be able to generalize path separators, the **FileSystems** class has a method called `FileSystems.getDefault().getSeparator()` that returns the separator character specific to the OS.

Other useful FileSystems methods are:

* `FileSystems.getDefault().getFileStores()` :  which returns an iterable containing all the mounting point in the machines.
* `FileSystems.getDefault().getRootDirectories()` : to get an iterable containing the root directory path

#### Handling files

*<https://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html>*

In the NIO module, the Path class is only devoted to handle paths, but a path can be abstract and be resolved without any issue even if not exists. To do operation that aims to check, for example, whether a file/directory exist or to delete a file, we have the `java.nio.file.Files` Class. Among the useful methods (most of them are static therefore can be used without initialization of the class) we have:

* `Files.exists(filePath)` : check whether a file exist
* `Files.copy(fromFile, toFile)` : create toFile as a copy of fromFile
* `Files.copy(fromFile, toFile, StandardCopyOptions.REPLACE_EXISTING)` : copy with the oprion to replace if existing; it prevents to raise the exception for a file already existing. We cna also copy a folder, but without walking the tree we will copy only the folder itself and not what it contains
* `Files.move(fileFromFolder, fileToFolder)`: move a file to a new location; if the location is the same, rename a file. We can also move folder but only if their are empty.. not very useful
* `Files.deleteIfExist(fileName)`: delete a file or handle the exception if the file doesn't exist
* `Files.create(fileName)`: create an empty file name.. at this point is it better to directly use a stream so that we can also write to it
* `Files.createDirectory(dirName)`: can specify also a nested structure of folders and create them at once (e.g. **/dir1/dir2/dir3** passed in the Path object)
* `Files.size(fileName)`: return the size of a file in Bytes
* `Files.getLastModifiedTime(fileName)`: return the date of last modification
  * Actually we can get all the file information in a single construct using the `BasicFileAttributes` class initialized as `Files.readAttributes(fileName, BasicFileAttribute.class)`; then we can interrogate the resulting variable with the methods **.size()**, **.getLastModifiedTime()**, **isDirectory()**, **.creationTime()** etc..

#### Walking directory tree with `newDirectoryStream`

To navigate through files in folder, and later on to copy them in bulk, the smart way is to use a stream implemented in the class **Files** called `newDirectoryStream` which implements iterable and therefore can be used to recursively find the path of folders, subfolder and files.

```java
private static void walkDir() {
    Path dir = FileSystems.getDefault().getPath("../");
    try (DirectoryStream<Path> content = Files.newDirectoryStream(dir)) {
        for (Path file : content) {
            System.out.println(file.getFileName());
        }
    } catch (IOException | DirectoryIteratorException e) {
        System.out.println(e.getMessage());
    }
}
```

In the **newDirectoryStream** we could specify a second argument with a regex expression to pars only files that match specific patterns. We can also specify a filter build with the `DirectoryStream.Filter` class where we can code a boolean expression (e.g. is file or not) and use it as a filter in the directory stream.

#### Walking file tree with `SimpleFileVisitor`

By creating a class that extends  the **SimpleFileVisitor** class,  we are able to leverage the `Files.walkFileTree()` methods to traverse al the files and folder from the path specified. the method takes two arguments: the path from which the traverse start and a new instance of our custom class that extends **SimpleFileVisitor**. Similarly we can create a method that not only walks down the tree but copy all the files and folder in the process. But we won't enter into detail now. (see Section 14 of Tim Buchalka Udemy course).

---

# Concurrency and Threads

First define some semantics of java concurrency:

* a `process` is a unit of execution that has its own **memory space** (called `heap`), each instance of the jvm run as a process; running a java application start a process. If two process are launched simultaneously, each process has its own heap and the two can't interact (the heap can't be shared).

* a `thread` is a unit of execution within a process; each process can have multiple threads (but at least one, called **main thread**). In reality, most of the application we write in java use multiple threads, even if not explicitly coded, to handle operation like memory management or I/O. Unlike different processes, different threads share the same heap; this can be an advantage but also a source of problems. Each thread has also a `thread stack` which is the memory dedicated only to that specific thread.

* `concurrency` is the ability of an application of doing more than one thing at the time; it doesn't mean that the execution of more than one task happen simultaneously, but that a task doesn't need to way for the prior task to finish, they can concur together (e.g. a call to an external db is hold by one task, instead of blocking the application waiting for the response, with concurrency another task can start).

The advantage to use multiple threads is to not block the main thread while performing other task (e.g. querying a database, an operation that can take some time). Another reason might be that an API requires to use a thread at a certain point in the code. Working with threads we are at the mercy of the jvm and the OS since it is in their power to decide threads priority (actually we can set threads priority but it is not guaranteed) (for this reason, when running a concurrent app on different machine we might see slightly different behavior/output).

## Threads

*https://docs.oracle.com/javase/tutorial/essential/concurrency/runthread.html*

---

# OOP in JAVA

*<https://www.tutorialspoint.com/java/java_encapsulation.htm#:~:text=Encapsulation%20in%20Java%20is%20a,methods%20of%20their%20current%20class>.* -> encapsulation

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
    // this is an empty constructor
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

## Inheritance

**N.B. even if not specified, each java class implicitly inherit from the `java.lang.Object` class which is the root of the class hierarchy and it implements some basic functionality like *equals()* , *toString()* etc..**

**N.B. with standard inheritance we can only extend our class with one class, oterwise we need to create an Interface or a Composition**

Inheritance is the ability of classes to inherit behavior from other classes allowing to build more and more specific objects that still share a common blueprint. To inherit from another class we need to use the keyword `extends` in the new class statement. Moreover, **if the class from which we are inheriting has a constructor** we need the new class to initialize that constructor (otherwise we get a compilation error).

```java
public class Animal {
    private String name;
    private int legs;
    private boolean tail;

    public Animal(String name, int legs, boolean tail) {
        this.name = name;
        this.legs = legs;
        this.tail = tail;
    }


public class Dog extends Animal{
    private String breed;
    private int teeth; 

    public Dog(String name, int legs, boolean tail) {
        super(name, legs, tail);
    }
}
```

In the new constructor, we will have the keyword `super()` that is essentially calling the constructor of the class we are extending from (Animal in this case); from this point, we can enrich the Dog class with extra states and methods, keeping the animal class as base blueprint (which represents the basic feature of an Animal). All the methods that are defined in the Animal class are accessible automatically also in the Dog class.

There are different scenario in the handling of the inherited constructor; for example we may want to set some default parameters from the parent class Animal as default in the child class Dog (e.g. all dogs have tails, therefore it is something we don't want to specify when calling the Dog class directly). Therefore, we can modify the parent class constructor, removing the arguments of the parent class that we don't need and specifying a constant value inside the **super()** constructor; then we can extend the constructor with the states component relative to the child class.

```java
public class Dog extends Animal{
    private String breed;
    private int teeth; 

    // removing the state "tail" and setting a default "true" values since all dogs have a tail
    public Dog(String name, int legs, String breed, int teeth) {
        super(name, legs, true);
        // extending the base constructor of the parent class
        this.breed = breed;
        this.teeth = teeth;
    }
}
```

The child class can also modify the methods that are inherited from the parent class by redefine the method with the same name and the `@Override` keyword placed abode the method definition. By default, the overwritten methods contains the keyword `super.myMethod()` where **super** indicates the parent class, hence we are calling the method of the parent class directly from the child class.

```java
@Override
public void talk() {
    super.talk();
    }
```

## `this` vs `super`

As already seen, the keyword `super` is used to access/call the parent class variables and methods while the `this` keyword is used to call the current class variable and methods (it is also required when a method has an argument with the same name of a state variable). These can't be used in **static blocks/methods**.

**this()** and **super()** in java can also be called as methods, in particular:

* `this()` is used to call a constructor from another overloaded constructor in the same class. It can be used **only inside a constructor** and has to be the **first argument** of the constructor (it helps to reduce duplicate code).
* `super()` is used as **first argument** (mandatory) in a constructor of a child class to inherit the constructor of the parent class; if not specified, java insert an empty **super()** constructor when a child class is instantiated.

**N.B. a constructor can have one between *super()* or *this()* but not both**

## `static` vs `instance` methods

*Summary: static methods belong to the class and not to its instances*

Static methods, declared with the `static` modifier, can't access instance methods or instance variables directly, in fact their are used for operation that don't require any data from the instance of the class; therefore a good indicator of a static method is a method that doesn't use instance variable, and as a confirmation, the `this` keyword cant be used inside a static method. Another key difference is that static method can be used without instantiate the class (i.e. without using the `new` keyword) but simply calling `className.staticMethodName()`.

As opposite, instance methods are bounded to an instance of the class, therefore they can only be called if the object is created. Instance methods can access instance methods and variables as well as static methods and static variables.

## `static` vs `instance` variables

Static variables are definitely less used than instance variable but they can become quite handy in certain situation. The power of a static variables is that they are shared by the instance of the classes, if one instance change the static variable, that change will affect all the other instance of that same class.

As opposite, instance variables are build without the **static** keyword and belong to a specific instance of the class and their value is not shared between different instance.

## final variables

Final keyword has a different meaning depending on the object to which is applied:

* *final* variable: the variable can't be change by any instance of the class -> become a CONSTANT value
* *final* method: the method can't be overridden
* *final* class: the class cannot be inherited or subclassed

Values that are truly constant are usually initialized as **static final** since there is no meaning that each subclass should store that value but only the main parent class (es. **Math.PI** is declared as **public static final double**)

## static block

Static blocks are part of codes enclosed in curly brackets and preceded only by the word **static** (`static {}`). This are used to initialize static variable and have the peculiarity of being executed before the class' constructor is called (we can have more than one static block, even placed below the constructor, but still executed first in their relative order).

## Composition

While inheritance is the way in which we create classes and subclasses of objects that have a common origin, with composition we have one or more classes which are composed by one or more other classes, this to create an higher level class which is composed by all the functionality implemented in the classes that compose it. Imagine a PC, which is composed by a case, a monitor, a motherboard etc.. To create the class PC, instead of creating a very complex class we can create as many subclasses as we need and then **compose** them in the higher level Pc class which will retain all the functionality of a case, a monitor, a motherboard etc..

Standard inheritance allows for a single extension (meaning that one class can only inherit from one other class); with composition (and later on with interfaces) we are able to create more complex structure. In general, composition gives much more flexibility then inheritance.

**Inheritance expose the relationship `is a` between two objects while Composition `has a`**

## Encapsulation

Encapsulation is the mechanism that allow us to restrict the access to certain components of the object created; this is fundamental to have more control on the code flow and in the possible raise of unexpected behavior. Particularly true for state variable, which scope should be strictly restricted to the class in which are defined (thus defined as **private**), if we are able to access them from outside the class then unexpected behavior might arise in the call of methods that uses those variables expecting a particular type/value.

Another reason for encapsulation along side with security is code **duplication**, **checking** and **validation**; as a matter of fact if we modify states variables from other classes and at some point we need to modify the parent variable or the behavior of a parent method (that should have been declared as **private**), then all the child classes would be broken and need updating. A lot of work and an extremely easy source of hidden errors.

## Polymorphism

When we have classes bounded by inheritance to a parent class we might have the same method inherit to all the child classes but it might have a different behavior depending on the specif child class which is calling it. The ability of having the same method displaying different behavior based on the context of the child class is exactly the definition of **polymorphism** (many forms), i.e. having the same method (with the same name) with different output.

Imagine we have a parent class called Animal that implements a method called ***sound()*** which should reproduce the cry of a particular animal; then we have several child classes, representing several type of animal, each with its peculiar sound, thats **inherit** and **override** the method ***Animal.sound()***. We can now call the same method on instances of the child classes and gain a different output (the sound of the particular animal).

This behavior is called polymorphism. N.B. if one of the child classes would have the method *.sound()* overridden, then calling the method on these classes would result in calling the original parent method *Animal.sound()*

**At the end, using polymorphism means to write generic code that can be reused in different scenarios**

## Immutable objects

Depending on the use in the outer world of our classes, it might be a good idea to expose only what is strictly needed to perform further manipulation and keep private the rest. The goal is to transform our class in an immutable object (from the outside). Following a set of best practice:

*<https://docs.oracle.com/javase/tutorial/essential/concurrency/imstrat.html>*

---

## Anonymous classes

*<https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html>*

Anonymous class represents a syntactic sugar, i.e. they are expressions that allow to declare and instantiate a class at the same time and without giving it a name. A classic example is when creating a new thread, instead of implementing a thread class that implements the runnable **Runnable** interface we can:

```java
new Thread(new Runnable() {
    @Override
    public void run() {System.out.println("Printing from runnable");}
}).start();
```

---

# Interfaces

The idea behind interfaces is to create a common behavior that can be used by several classes that implements the same interface. The interface itself is **abstract** meaning that it doesn't contains any actual code for the methods it implements but only the `signature` (like the statement for private variables), the methods are actually coded inside the classes that implements them.

In practical terms, the interface are implemented using the signature `public interface interfaceName {}` and inside the curly brackets we will write the signatures off all the methods that the interface implements and that will be inherited from the classes that implements the interface.

```java
public interface MyInterface {
    void methodOne();
    boolean methodTwo(boolean var1);
    int methodThree(int var1, int var2);
}
```

A class, in order to implements an interface has to declare the `implements` keyword in its signature:

```java
public class MyClass implements MyInterface {
    @Override
    public void methodOne() {}
    @Override
    public boolean methodTwo(boolean var1) {return var1}
    @Override
    public int methodThree(int var1, int var2) {return var1 + var2}
}
```

In this case, since **MyClass** is implementing the interface **MyInterface** to be valid (to be compiled) it needs to implements all the methods that are stated inside the interface itself, respecting of course the prescribed datatype. If a specific method from the interface is not needed we can simply override it (actually all the methods are overridden) and giving it a dummy behavior (do nothing).

The power of interfaces is that they enable `multiple inheritance` since in our classes we can **implements** as many interfaces as we want given that the class then implements all the methods inside the interface signature (e.g. we could implements the interface **List** because we are interested in a particular behavior that lists has, but still we will need to implements all the methods present in the List interface).

## Type inheritance

A cool feature of interfaces is that, if we initialize a variable with the interface signature as datatype, we can reassign that variable to new objects from different classes given that these all implements the same interface. Imagine we have an interface called **ITelephone** and two classes that implements the same interface **Mobile** and **Desk**; if that so then the following is valid:

```java
// IT WORKS!
ITelephone myPhone;
myPhone = new Desk();
myPhone = new Mobile(); // OK! both Desk() and Mobile() implements the interface ITelephone

// It DO NOT works
Desk myPhone;
myPhone = new Desk();
myPhone = new Mobile(); // ERROR the class Desk was declared to hold the variable "myPhone"!
```

N.B. a good practice is to start interfaces names with the capital `I` (independently by the name that follows).
N.B. Methods implemented in the Interface are inherently `abstract` even if not explicitly stated.
N.B. Java libraries make extensive use of interfaces!

## Useful Interfaces

Java makes extensive use of interfaces and some of them are particularly useful to add functionality to our custom classes.

### Comparable <T>

*<https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html>*

Interface to implements a natural ordering comparison of objects from the same class.

---

# Nested and Inner classes

*<https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html>*

**Personal tough: I won't go in deep of nested classes at this stage since I don't think it is a rather advanced topic to apply in production.. see you later nested classes**

Why Use Nested Classes?
Compelling reasons for using nested classes include the following:

* **It is a way of logically grouping classes that are only used in one place**: If a class is useful to only one other class, then it is logical to embed it in that class and keep the two together. Nesting such "helper classes" makes their package more streamlined.

* **It increases encapsulation**: Consider two top-level classes, A and B, where B needs access to members of A that would otherwise be declared private. By hiding class B within class A, A's members can be declared private and B can access them. In addition, B itself can be hidden from the outside world.

* **It can lead to more readable and maintainable code**: Nesting small classes within top-level classes places the code closer to where it is used.

---

# Abstract classes

*<https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html#:~:text=An%20abstract%20class%20is%20a,but%20they%20can%20be%20subclassed.&text=When%20an%20abstract%20class%20is,methods%20in%20its%20parent%20class>.*

Abstraction is when we define the functionality needed for a particular task without directly implementing them; **interfaces** are a perfect example of pure abstraction, where the interface itself holds nothing more than the methods signatures which will be then actually implemented in the classes that implement the interface. This results in the fact that we cannot instantiate directly the interface (since it is abstract) but we need to instantiate a class that **implements** the interface.

Java implements both abstract classes and methods; an abstract class, like an interface, cannot be instantiated directly but can be subclassed with the keyword **extends**. An abstract class can implement both standard methods and statements, and these will inherit as supposed by the subclasses, together with abstract methods that, like for interfaces, contains only the signature of the method itself. A subclass, in order not to be abstract as well, is required to implements all the abstract methods (same as a class that implements an interface).

Summing up, the main differences between interfaces and abstract classes are:

* abstract classes can contain both private and public methods, while interfaces signature are public and abstract by nature
* abstract classes are de facto forced to **single inheritance**
* abstract classes are more suited when we are designing a set of classes that are strictly related in semantics, while interfaces can be a mean to enhance  unrelated classes with a totally different scopes (e.g. many constructor might benefit from the List interface but for totally different use).

Abstract classes and Interfaces can be concatenated, meaning that we can have a subclass of an abstract class that in turn implements an interface.

---

# Exceptions

*<https://docs.oracle.com/javase/specs/jls/se11/html/jls-11.html>*
*<https://docs.oracle.com/javase/8/docs/api/index.html?java/lang/Exception.html>*
*<https://docs.oracle.com/javase/tutorial/essential/exceptions/throwing.html>*

An exception is event that happen during the execution of a program that disrupts its normal flow.

Since Java is a compiled programming language, we have two kind of exception: the `compile-time` and the `run-time` exceptions. The base class upon which all the exception are built is the `java.lang.Exception` which extends the `java.lang.Throwable` which is the superclass of all errors and exceptions (therefore, only objects that are subclassed by this class can be thrown by java **throw** statement).

To throw an exception we simply need to call the **throw** method and initialize the exception object and this is actually something pretty useful to do during testing and debugging.

```java
throw new NoSuchElementException("Something went wrong!")
```

LBYL = **L**ook **B**efore **Y**ou **L**eave

EAFP = **E**asier to **A**sk for **F**orgiveness than **P**ermission

LBYL and EAFP are two common ways to handling exceptions, the former is usually preferred in java and it essentially require to test a variable before performing operation on it (e.g. **if(var !=null)**) while the latter is based on the concept of trying an operation and handle it in case it generates an unexpected error/output ("try.. except" in python).

```java
private static int divideLBYL(int x, int y) {
    // the exception is handled before it can happen
    if (y != 0) {
        return x / y;
    } else return 0;
}

private static int divideEAFP(int x, int y) {
    // the operation is performed and in case it fails the exception is handled
    try {
        return x / y;
    } catch (ArithmeticException e) {
        e.printStackTrace();
        return 0;
    }
}
```

Both the approach are valuable, which one to use should be sized by the context.

We can catch more than one exception at the time simply creating more **catch block** or using the logical **or** to concatenate them if their handling traduce in the same action :

```java
try {
    return x / y;
} catch (ArithmeticException e) {
    throw new ArithmeticException("Can't divide by 0!")
} catch (NoSuchElementException e) {
    throw new ArithmeticException("Invalid input type!")
}

try {
    return x / y;
} catch (ArithmeticException | NoSuchElementException e) {
    throw new ArithmeticException("Something went wrong!")

```

## stack-trace and call-stack

When raising an exception, java prints in output the stack-trace, which duty is to store the call-stack of each method in execution; at the point in which the exception occur, java call the stack-trace to return the call-stack, i.e. a list of all the methods called in a particular point in the code (the one that is causing the exception raising), in the specific point where the program crushed. The call stack is usually composed of many lines:

* the first line usually indicates the exception raised
* then starting from the bottom we first have the call-stack inside the code, i.e. the methods or statements that we directly called (usually indicated in a different color)
* above these, there is the call-stack of the methods called by java under the hood to actually perform the operation we required

```java
Exception in thread "main" java.util.InputMismatchException // exception that caused the program to crush
    // java internal call-stack
 at java.base/java.util.Scanner.throwFor(Scanner.java:939) // Scanner method to determine which type of exception raise
 at java.base/java.util.Scanner.next(Scanner.java:1594)
 at java.base/java.util.Scanner.nextInt(Scanner.java:2258)
 at java.base/java.util.Scanner.nextInt(Scanner.java:2212)
    // our code call-stack
 at Main.divideNoExceptionHandling(Main.java:25)
 at Main.main(Main.java:5)

Process finished with exit code 1
```

---

# The java.util.Collections Class

*<https://docs.oracle.com/javase/9/docs/api/java/util/Collections.html>*
*<https://docs.oracle.com/javase/tutorial/collections/interfaces/index.html>*

The collection class is consist exclusively of static methods that operates and/or return collections objects. Ii is part of the Java Collection Framework. Among the most useful class that can be operated through the collections methods we have Set, List, Queue and Deque (all at the same level of hierarchy). These data structure are implemented  so that they can be interchanged as easily as possible, providing the best performance depending on the specif use case.

One useful things we can do with is implementing a binary search tree (`Collections.binarySearch`) for find elements in **sorted** lists (it require our class to implements the **Comparable** interface) which is much faster than a brute force for loop.

We can sort, reverse shuffle, or more in general manipulate lists.

## Sorting with Comparable vs Comparator

The **java.lang.Comparable** is an interface that require our class to implements the method **compareTo(T, o)**. Therefore, our class needs to be modified since it has to be extended with the comparable interface (not always possible if our class is in a Jar), moreover, only a single criteria of sorting can be specified. In essence, it is the best choice only if our sequence follows natural ordering. Once our class has become *comparable*, we can then call the **Collection.sort(T)** on our sequence

The **java.util.Comparator** is a more flexible interface that require our classes to implement two methods: **compare(T, T)** and **equals()**. We don't need to modify our class, instead we can create a new class that implements the comparator and define the logic of comparison. Once our comparator class is defined we can call the **Collection.sort(T, c)** where **c** is our comparator class (that contains our specific logic of comparison) and T is the specific collection we need to sort.

---

# Tools

## DiffMerge

DiffMerge is a tool used to visually compare and merge files on any OS
