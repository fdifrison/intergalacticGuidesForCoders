<h1>General Python</h1>

TOC

- [PEP8 - Naming conventions](#pep8---naming-conventions)
- [Everything is an Object](#everything-is-an-object)
- [Variables and Memory](#variables-and-memory)
  - [`id` function](#id-function)
  - [Reference Counting](#reference-counting)
  - [Shared Reference](#shared-reference)
  - [Garbage Collection](#garbage-collection)
  - [Object Mutability](#object-mutability)
  - [Variable Equality](#variable-equality)
- [Built-in methods](#built-in-methods)
  - [`isinstance(object, class)`](#isinstanceobject-class)
  - [`issubclass(subclass, class)`](#issubclasssubclass-class)
- [Loops](#loops)
  - [While loop](#while-loop)
  - [Try statement](#try-statement)
    - [COMMON EXCEPTION](#common-exception)
- [Strings](#strings)
  - [Common methods](#common-methods)
- [Classes](#classes)
  - [Getters and Setters](#getters-and-setters)
  - [Overload methods](#overload-methods)
    - [`str` method](#str-method)
    - [`repr` method](#repr-method)
    - [`eq` method](#eq-method)
- [Functions](#functions)
  - [Docstrings and annotations (PEP 257)](#docstrings-and-annotations-pep-257)
  - [`lambda` expression](#lambda-expression)
  - [Function Intorspection](#function-intorspection)
  - [Unpacking iterables](#unpacking-iterables)
  - [Unpacking with *](#unpacking-with-)
  - [Nested unpacking](#nested-unpacking)
  - [*args and **kwargs](#args-and-kwargs)
  - [Parameters default](#parameters-default)
  - [Map, Filter and Zip functions](#map-filter-and-zip-functions)
  - [Reducing Functions](#reducing-functions)
  - [Partial functions](#partial-functions)
  - [The `operator` module](#the-operator-module)
- [Scopes and Namespaces](#scopes-and-namespaces)
  - [Masking](#masking)
  - [NonLocal scope](#nonlocal-scope)
- [Closure](#closure)
  - [Shared extend scope](#shared-extend-scope)
  - [Nested Closure](#nested-closure)
- [Python optimizations](#python-optimizations)
  - [Interning](#interning)
  - [Peephole](#peephole)
- [Numeric Types](#numeric-types)
  - [Integers](#integers)
    - [Operations](#operations)
    - [Base](#base)
  - [Rational Numbers](#rational-numbers)
  - [Floats (Real Numbers)](#floats-real-numbers)
    - [equality](#equality)
  - [Booleans (PEP 285)](#booleans-pep-285)
    - [Booleans operators](#booleans-operators)
    - [Short-Circuiting](#short-circuiting)
- [Common Modules](#common-modules)
  - [string](#string)

# PEP8 - Naming conventions

- `packages`: short lowercase and without underscore es. `utilities`
- `modules`: short lowercase and with underscore es. `db_utils`
- `classes`: first letter of each word are uppercase, no spaces and no underscore es. `MyClass`
- `functions` lowercase and with underscore es. `open_account`
- `variables` lowercase and with underscore es. `account_id`
- `constants` all uppercase with underscore es `MIN_VAL`

# Everything is an Object

In python everything is an object. Functions for example, inherit from the built-in function class; the same happen for classes which inherit from class function. This implies that every objects has a memory address (yes, even function and classes). In the same way every object che by assigned to a variable, passed as argument to a function or returned by a function. We can look at the object type of any variable with the `type` built-in function.

# Variables and Memory

When a variable is created, what python is doing under the hood is to link the variable name to the memory slot (slots) which contains the element assign to the variable. Therefore the name is nothing more than a reference to the memory slot.

## `id` function

`id`is the function that returns the memory address of a variable in base-10 ( can be converted with `hex` to see the hexadecimal representation).

## Reference Counting

Reference counting is a process carried out by the python memory manager internally. Each time we create a new variable, we are creating a reference to a memory slot. If we create a new variable that is equal to an existing one, we are adding a reference to the same memory slot (which now has a reference count equal to two).

```py
my_var = 10 # my_var is pointing to the memory slot id(my_var)

other_var = my_var # other var is pointing to the same memory slot of my_var
# at this point, the ref count of id(my_var) is equal to 2

import sys
sys.getrefcount(my_var) 
# return the ref count of the variable + 1 far the call of sys itself

import ctypes
ctypes.c_long.from_address(id(my_var)).value 
# is a lower level way to find the ref count of a memory slot
```

## Shared Reference

```py
a = 10
b = a
# b is not copying the content of a, it is pointing to the same memory address

a = 10
b = 10
# since the number 10 is immutable, both a and b are pointing to the same memory address

a = [1,2,3]
b = a
b.append(4)
# now both b and a are equal to [1,2,3,4] since a list is mutable object and appending an element modify only its internal state with the same memory address

a = [1,2,3]
b = [1,2,3]
# in this case python doesn't create shared references, so a and b are pointing to different objects, this to prevent that modifying b affects also a
```

N.b. There will be always a shared reference to `None` object, created automatically by python.

## Garbage Collection

It is the way python use to avoid memory leaks such that generated by circular references (objects pointing one to the other). Garbage collection can be controlled using the `gc` method. It can be turned off (only if we are super-sure that there are not circular reference in the code, in order to improve performance). The gc runs periodically on its own but can also be called manually to program a specific cleanup of the code.

## Object Mutability

Changing the data inside an object is called `modifying the internal state` since the memory address is not changed but only its content (es. appending an element to a list). So we can distinguish between `Mutable` and `Immutable` object depending on the possibility of changing the internal state.

`Immutable objects:`

- Numbers
- Strings
- Tuples (if contains mutable elements, es. lists, those remain mutable)
- Frozen Sets
- User_Defined Classes (if so defined)

`Mutable objects:`

- Lists
- Sets
- Dictionaries
- User_Defined Classes (if so defined)

Care must be taken when we talk about immutability of an object that is given to a function as an argument. We have to distinguish between the `module scope` and the `function scope`.
When we pass an object to a function we are in reality passing the `reference` of the object itself. So if we are passing to a function an immutable object, say a string, at the beginning both the module scope and the function scope point to the same memory reference, but as soon as the function modify the string (es. concatenating another string), then a new object with a new reference is created. If the object is mutable, say a list, and the function modify the list (es. appending an element), then python doesn't create a new object but simply modify the internal state of the existing memory reference

```py
# IMMUTABLE
def process(s):
  # s has still the same memory reference as my_string
  s = 'hello' + s
  # now s has been modified, and since it was an immutable object, a new object with a new reference is created.
  return s

my_string = 'world'
process(my_string) 

# MUTABLE
def process(lst):
  # lst has the same memory reference as my_list
  lst.append(5)
  # since lst is a mutable object, only the internal state is changed but the memory reference is still the same.

my_list = 'world'
process(my_list) 

```

## Variable Equality

There are two ways to verify the equality of two variables in python: the `is` and the `==` operators, respectively the identity and equality operators. While the identity operator compares the memory reference of two objects, the equality operator compares their internal state (data). Their negation are `is not` and `!=`.

```py
a = 10
b = a

a is b # True since the memory address is the same (int are immutable objects)
a == b # True

a = 500
b = 500

a is b # False, preloaded integers are in the range [-5, 256] see Interning
a == b # True



a = [1,2,3]
b = [1,2,3]

a is b # False, different memory address
a == b # True


a = 'hello'
b = 'hello'

a is b # True, but not always!
a == b # True

a = 10.0
b = 10

a is b # False, float and int are different objects
a == b # True, python recognize the have the same value
```

# Built-in methods

## `isinstance(object, class)`

Return `True` if an object is an instance of a particular class, `False` otherwise.

## `issubclass(subclass, class)`

Return `True` if class inherits from another upper class, `False` otherwise.

# Loops

## While loop

to generate an infinite loop:

```py
while True:
    print('Infinite Loop')
```

`else` statement is executed after a while loop only if it terminates without a `break`

`continue` is used to interrupt the execution of the current iteration are restart the loop with the next iteration. Only `finally` statement is executed after a continue statement.

## Try statement

test a code block.

`except` is used to captures errors and handle exceptions

`finally` is a code block that is always executed, whether an exception or a break are invoked

### COMMON EXCEPTION

- ZeroDivisionError

-

# Strings

## Common methods

- isalpha() -> check if is alphanumeric
- isprintable() -> check if is printable

# Classes

`__init__` is the default method that is called after the class object is created. The first argument is always `self`, i.e. the instance of the object created calling the class object.

```py
class Rectangle:
  def __init__(self, width, height):
    self.width = width
    self.height = height

r1 = Rectangle(10,20)
# r1 is a instance of the class Rectangle, referred as `self` inside the class constructor    

```

## Getters and Setters

Getter and setter methods are implemented to impose some dynamics in the class structure. In python there are no private attributes (even if we can specify to the reader that a variable is private beginning its name with _). Setter and getter methods impose to the user a constrain

## Overload methods

There are some special methods that are built into the `class` constructor and are passed automatically to any instance of the class even if they are not defined explicitly. For example if we call the python method `str` on an instance of the class we will receive a standard output specifying the memory address of the instance. Unless we overwrite or `overload` this method explicitly inside the class definition.

### `str` method

```py
class Rectangle:
  def __init__(self, width, height):
    self.width = width
    self.height = height

  def __str__(self):
    return f'Rectangle: width:{self.width}, height:{self.height}'

r1 = Rectangle(10,20)

str(r1) # will print what inside the __str__ method
# if __str__ is not defined than the output will be:
# <__main__.Rectangle object at "some memory address">

```

### `repr` method

It is similar to `__str__` method but its use is more developer-oriented. The repr method should return the string representation of the class instance called

```py
class Rectangle:
  def __init__(self, width, height):
    self.width = width
    self.height = height

  def __repr__(self):
    return f'Rectangle({self.width}, height:{self.height})'

r1 = Rectangle(10,20)

repr(r1) # will print what inside the __repr__ method that is exactly Rectangle(10,20), how the object r1 has been created in the first place

```

### `eq` method

Is the method needed to compare two objects generated by the same class with the `==` operator.

# Functions

A first semantic difference in functions is the definition of `parameters` and `arguments`; the first is referred to the variables in the function definition while the second is refereed to the variables passed to the instance of the function.

```py
def my_func(a, b): # a and b are the parameters of the function
  pass

x = 10
y = 'a'
my_func(x, y) # x and y are the arguments of the function
```

To be noted that x and y are passed by `reference` to `my_func`, i.e. the memory addresses of x and y are stored into a and b.
Therefore, the `Function Scope` contains the memory addresses of the variables that are passed to the function (x and y in the example).

Another pythonic difference is the definition of `functions` and `methods`. They are defined in the same way but a method is bound to a class, it is an attribute of the class that is callable.

```py
ismethod(obj)
isfucntion(obj)
```

## Docstrings and annotations (PEP 257)

N.B. Docstring has to the first line of code in the function/class definition, otherwise it won't be inserted into the `__doc__` method and won't be displayed with the `help()` function.

Docstrings (single quote or triple quote) are the way to generate documentation inside the python code. They are different from comments (#) since the former are actually compiled by the interpreter and stored in the `__doc__` property of functions and classes. The `__doc__` property can be invoked with the `help()` function on any object that implements it.

```py
def my_func():
  '''
  Here it goes the doctring that contains
  the instruction on function usage and arguments types and boundaries.
  This will be displayed invocking the `__doc__` method
  with the `help()` function.
  '''
  pass
```

Another way to document our code is to use annotations. These are not stored in the `__doc__` method but can be invoked by the `help()` function. Annotations can be also functions that are evaluated as constant during first compilation; however they don't bind the code to a specific behavior (a: int -> doesn't bind a to be an int), they are only metadata stored in teh `__annotations__` method which is a dictionary with parameters as key and annotations as values. These can be used by external modules like `Sphinx` to automatically generate documentation for our code.

```py
def my_func(a: 'string', b: 'integer') -> 'a string':
  return a*b
```

## `lambda` expression

`lambda` expressions are another way to create function without the `def` statement. They are also referred as to `anonymous functions`. It has to be a single expression, therefore no assignment is allowed aswell as no type hinting (annotations)

```py
# lambda [parameters list]: expression
lambda x: x**2
lambda x, y: x + y
lambda : 'hello' # we can assign 0 parameters and just return a constant

# we can assign lambda function to variable and later call it
my_func = lambda x: x**2
type(my_func) # -> function
my_func(3) # -> 9
```

The lambda expression generates a `function object` that returns the expression when called.

## Function Intorspection

Function are first-class objects that, when created are shipped with a series of default dunder methods in additions to the ones that we implemented. To look at all the function attributes we can use the built-in function `dir()`. Among the dunders method we have:

```py
func.__name__ # the name of the function
func.__defaults__ # tuple containing default positional parameters
func.__kwdefaults__ # dictionary containing default keyword parameters
func.__code__ # return an code object which has its own methods:
  func.__code__.co_varnames # return the paramters and then the local variables (defined inside the function scope) of the function
  func.__code__.co_argcount # return the number of parameters except *args and **kwargs
func.__name__
```

Also the module `inspect` can be used to retrieve information about the function:

```py
import inspect

inspect.getcomments(my_func) # returns the comment just above the function definition
```

## Unpacking iterables

Iterables are `packed` structures that bundle values together (list, tuple, strings, set, dictionary..). As per the world meaning, `unpacking` is an operation that assigned the packed values to variables:

```py
a, b, c = [1,2,3]
a, b, c, d = 'ciao'

# N.B. we can unpack in the same way a dictionary or a set but in that case the order of assignment will be casual because these are unordered types of objects.
```

It comes handy when we want to swap values between variables:

```py
a = 10
b = 20

a, b = b, a
# this works in python because the LHS is evaluated first, where the memory address of "b" and "a" is copied in a tuple and only after assigned to the new swap) variables "a" and "b".
```

## Unpacking with *

We may want to unpack an iterable in more than one variable, and in this case it comes handy the `*` operator:

```py
l = [1,2,3,4]
# we want to unpack the first element of l and the others apart
#we could do it simply with list slicing and unpacking the
a, b = l[0], l[1:]

# or in a more elegant way with the * operator
a, *b = l # a=1, b =[2,3,4] 

a, *b, c = l # a=1, b=[2,3], c=4
a, *b, *c = l # ERROR we can unpack with only one *, otherwise python won't understand who assign to whom
```

Another advantage of the `*` operator is that can be used also with objects that don't support slicing (like sets or dictionaries, since they have no ordering). N.B. if more than one element is unpacked with *, it will always end up in a list (even if, for example, the item unpacked is a tuple).

The * operator can be used also for unpacking objects on the RHS:

```py
l1 = [1,2]
l2 = [3,4]
l = [*l1, *l2] # l=[1,2,3,4]
```

With dictionaries we have both keys and values that can be unpacked (unordered unpacking since there is no order!). With the `*` operator we unpack the keys of the dict only, while with the `**` we can unpack both keys and values (N.B. `**` can be used only on the RHS).

```py
d1 = {'a': 1, 'b': 2}
d2 = {'b': 3, 'c': 4}
d = {*d1, *d2} # d={'a','b','c'} -> n.b. b was not repeated because keys are unique in sets and dictionaries.

d = {**d1, **d2} # d={'a': 1, 'b': 3, 'c': 4} -> n.b. b has the values contained in d2 since it was unpacked for second and overwrite the keys from d1.
```

## Nested unpacking

We can unpack also nested structures, such as list of lists, with the same operators.

```py
a, b*, (c, d*) = [1, 2, 3, 'python']
# a=1, b=[2,3], c='p' d=['y','t','h','o','n']
```

## *args and **kwargs

Unpacking can be done also in functions parameters in order to specify a variable number of arguments as input:

```py
def my_func(a, b, *args): # N.B. the name 'args' is just a conventions
  pass

my_func(1,2,3,4) # a=1, b=2, c=(3,4)
# note that inside function scope, arguments are unpacked into tuples e not lists
```

The positional argument constructor `*args` has to be the last positional argument in the function since it exhaust all the non-assigned positional arguments; after that only keyword arguments are allowed and these can be unpacked with the `**kwargs` parameter. The `*` and `**` operators can be used to limit the use of positional or keyword arguments.

```py
def my_func(*, name):
  # in this way my_func doesn't allow positional arguments.
  # name is automatically a keyword argument.
  pass

def my_func(a, *, name, **):
  # in this way my_func allow only one positional argument `a` and one keyword argument `name'.
  pass

def my_func(*, **kwarg): # ERROR an explicit keyword argument is required after the `*`
def my_func(*, name, **kwarg): # OK
```

## Parameters default

Care must be taken when assigning default values to functions arguments, in particular if these are mutable objects. Wehn Python compile the script it stores in memory the function definition and any argument with default values. This means that each time that function is called, if the default parameters is left unchanged, it will use the specified values. In some case it may results in unwanted behaviors:

```py
# creating a function that store a message in a log file with the datetime
from datetime import DateTime

def log(msg, *, dt=datetime.utcnow()):
  print(f'{dt}: {msg}')

# Now, since the value of dt is stored at runtime, each time we call
log('first log')
# we will see that the time printed is alway the same since it has been stored at compilation time as a CONSTANT!

# SOLUTION
def log(msg, *, dt=None):
  dt = dt or datetime.utcnow() # if dt is false (None) the 'or' statement is executed
  print(f'{dt}: {msg}')
# we can set dt=None and check if the user actually input a values for dt. If not the function will call datetime.utcnow().
# Since this call is performed in the function scope, it gets executed each time the function is called. 
```

Another example is when we create a mutable object directly as argument of a function. Also in this case, that object is evaluated as a constant at compilation time and reused as reference each time the function is called. This

```py
# create a function that store values into a list
def add_item(item, func_list=[]):
  func_list.append(item)
  return func_list

my_list1 = add_item('banana') # a list is created that references to `func_list`
# so if i create another list of items
my_list2 = add_item('coca')
# now we have:
# my_list1 = ['banana', 'coca']
# my_list2 = ['banana', 'coca']
# because they are both referencing to func_list!

# SOLUTION
def add_item(item, func_list=None):
  func_list = func_list or list()
  func_list.append(item)
  return func_list
```

`KEY TAKE-AWAY`: Never use mutable objects as `default` arguments. Instead use None and create the object in the function scope. The only time it can come in handy is when using `memoization` to cache values from a function that is executed multiple times.

## Map, Filter and Zip functions

N.B. Map anf Filter have been mostly replaced by list comprehension and generator functions.

These are `higher order functions` (i.e. function that takes a function as parameter and/or returns a function),

`map` return an `iterator` that applies the function to each element in the iterable.

```py
map(func, *iterables)

def sq(x):
  return x**256

l = [1, 2, 3]

list(map(sq, l)) # => [1, 4, 9]
# map returns a generator, therefore we need to pass it to list()
list(map(lambda x: x**2, l)) # same thing using lambda
```

The number of iterables that are provided to map() is determined by the function that it is passed; if more than one iterable is provided, only the length of the shortest will be mapped.

`filter` is a function that takes a function and a single iterable and returns the elements of the iterable that satisfies the condition given in the function, i.e. it filters the iterable.

```py
l = [0,1,2,3,4,5]
list(filter(lambda n: n % 2 == 0, l)) # [0, 2, 4]
```

## Reducing Functions

Also called, accumulators, aggregators or folding functions; are functions that recombine an iterable recursively, returning a single value (es. finding the max in an array, or summing up its elements).

```py
# write a reducing function to compute max, min and sum of an iterable.
l = [5, 8, 6, 10, 9]

add = lambda a, b: a+b
find_max = lambda a, b: a if a > b else b
find_min = lambda a, b: a if a < b else b

def _reduce(fn, sequence):
  result = sequence[0]
  for x in sequence[1:]:
    result = fn(result, x)
  return result
```

The function `_reduce` takes two arguments, a function (add, find_max or find_min), and sequence of numbers. It applies the function recursively a return a single value (the sum, the max or the min) depending on the function passed.
Python has a builtin modules that contain the function `reduce` similar to the one defined above, but that works on any iterables, also non index ones.

```py
from functools import reduce

l = [5, 8, 6, 10, 9]
reduce(lambda a, b: a if a > b else b, l) # find max of l
'''
Reduce has a third argument called 'initializer' that serve as first value for the reduced function. This is to avoid runtime error in the case, for example of trying to apply sum-reduce to an empty list.
'''
```

Other builtin reducing function in python are:

```py
max()
min()
sum()
any() # return True if at least one element in the sequence evaluates to True
all() # return True if all the elements in the sequence evaluates to True
```

## Partial functions

Partial functions are a way to reduce the number of argument required by a function, setting some of them as default. We could write ourself a wrapper to a function ore use the builtin `functools.partial` module.

```py
# create a function that compute the power of a number

def pow(base, exponent):
  return base ** exponent

# we can create a partial function that specify the exponent so that it computes alway the square
def square(base):
  return pow(base, exponent=2)

# or we can use the functools.partial module
from functools import partial

square = partial(pow, exponent=2)

'''
N.B. if we define a variable prior to the partial definition, and we assign that variable as argument, the argument won't point to the variable but to the values associated with its memory address. therefore even if we change the variable after, the value at which the partial function is pointing will remain the same
'''

a = 2
square = partial(pow, exponent=a)
# exponent is pointing at the same memory address of 'a' (the value 2) and not to a itself
a = 5
# the value assigned in the square function remain the same (2)
```

## The `operator` module

The `operator` module is a builtin suits shipped with standard python installation. Its main purpose is to construct functional equivalents to arithmetic operation.

```py
from functools import reduced
from operator import mul

# we have seen how we can use lambda expression together with reduce to create recursive functions on sequences
reduce(lambda x, y: x*y, [1,2,3,4]) # return the product of the elements of the list
# the same can be achieved with the operator.mul
reduce(mul, [1,2,3,4])
```

There is a variety of different methods in the operator module, for arithmetic/boolean operations (`mul()`, `add()`, `le()`, `is_()` ..), for sequences handling (`getitem()`, `setitem()`, `delitem()` ...) and for handling functions (`itemgetter()`, `attrgetter()`, `methodgetter()`...). These last ones don't return values but instances of the method called; they become an operator thyself (a function essentially) to be call on another objects.

```py
from operator import itemgetter
l = [5, 8, 6, 10, 9]
f = itemgetter(1, 3) # create a function that return the item at index 1 and 3
f(l) # -> (8, 10)

```

# Scopes and Namespaces

The `Scope` is the portion of the code in which a variable name is defined; it has an associated `namespace`, essentially a table that lists all the variables in the Scope and the associated memory addresses. There are different `Scope` in python and are defined in a nested structure. At the top, we have the `built-in` scope, the only truly global scopes that exists across each modules of Python, which contains the definitions of core elements such as `True`, `None`, `dict` etc. Nested inside the built-in scope there is the so called `Global` scope (even if it is not global in the sense that exist only inside a single file). Moreover, each function has its own scope, named `Local`, that is created each time the function is called (until the function is not called, the variables defined in its own scope are not compiled, therefore does not exist in the Global scope, where the function is defined).


```py
# module1.py
print(True)
# both print and True are not defined in the module1 scope, therefore python automatically goes up one level and look up for their definition in the builtin-scope
print(a)
# Error! 'a' has not been defined in the module scope and neither is in the built-in scope, therefore Python trows an error `run -time Name Error`
```

We can summarize that at `compile time` python looks at the code and predetermine which variables will eventually be in the local or global scope. When it encounter a `def` (function), it will look inside of it; if there are assignations (e.g. a = 100), it will understand that that variable will be part of the global scope only, unless the `global a` keyword is specified; if a variable is called but not assigned inside the function (e.g. print(a)), the compiler will determine that it is a non-local reference.

```py
a = 0 # global scope/namespace

def func1():
  print(a) # the compiler understand it is a non-local variable since there is no assignment inside the local scope

def func2():
  a = 100 # the compiler knows that this will be a local variable

def func3():
  global a # the compiler knows that this will refer to a global variable
  a = 100 
```

## Masking

It is defined masking, and should be avoided, when we overwrite a keyword from the built-in scope. Since Python first look at the module scope, if we have assigned a variable to an existing element in the built-in namespace, we will modify its standard behavior.

```py
# module1.py
print = lambda x: f'Hello {x}'
# we are redefining locally the 'meaning' of the variable print so now:
print('world') # -> 'Hello world'
# python is invoking the local definition of print e not the built-in one
```

The same behavior is applied between Global and Local scopes. When assigning a variable inside a functional scope, python sees it at compilation time and stores it (it will be created only when the function is called but the compiler is already aware that it exists).
Therefore, if the same variable exist in the Global scope, when the function is called, it will be masked by the assignation in the local scope.

```py
a = 0 # global scope/namespace

def my_func():
  a = 100 # local scope/namespace
  print(a)

my_func() # a = 100, the global scope 'a' has been masked 

print(a) # a = 0, the global scope hasn't been modified, and the local scope of `my_func` has been destroyed after its execution.
```

There is also the possibility to avoid masking by explicitly tell python that the variable assigned in the local spaces are actually owned in the global space. This is done by declaring the `global` keyword at the beginning of a local scope. This is telling the compiler to look first in the global scope for that particular variable and, if not found, to create a new one.

```py
a = 0

def my_func():
  global a
  a = 100 # local scope/namespace
  print(a)

my_func() # a = 100, since `global a` has been declared, the variable `a` in the global scope has been modified

print(a) # a = 100
```

## NonLocal scope

When we define a function inside another function, a new scope is created which is not the global (module level) scope, nor the local (function level) scope. It is a middle scope called `non-local scope`. Variables belonging to the nonlocal scope are called `free variables`.

```py
def outer_func():
  x = 10 # local scope of outer_func == non-local scope of inner_func
  
  def inner_func():
    x = 20 # local scope of inner_func
  
  inner_func()

  print(x)

# if we call outer_func:
outer_func() # x = 10 since the local scope of inner func has not modified the non-local scope (local scope of outer_func)
```

In the same way as we tell python that a variable in a local scope is `global`, we can specify a variable to be `non-local` (i.e. with the same reference of the one in the outer_func scope). 

```py
def outer_func():
  x = 10 # local scope of outer_func == non-local scope of inner_func
  
  def inner_func():
    nonlocal x # now the reference of x is shared with the non-local (outer_func) scope
    x = 20 # local scope of inner_func
  
  inner_func()

  print(x)

# if we call outer_func:
outer_func() # x = 20 since the local scope of inner func has modified the non-local scope (local scope of outer_func)
```

N.B. if in a local scope we define a `global` variable, python will look in global scope for a match, otherwise it will create the global variable. Instead, when defining a `nonlocal` variable, python will look only in the non-local scope (the local scope of the parent function).

```py
def outer():
  x = 0 # local scope of outer
  
  def inner1():
    # local scope of inner1 == nonlocal scope of inner2
    def inner2():
      nonlocal x
      x = 10 
    inner2()

  inner1()
  print(x) # x = 0 because inner2 looked only in its nonlocal scope
```

# Closure

A `Closure` is a special python constructor that is composed by a function and an extended scope (nonlocal scope) that contains free variables (nonlocal variables). This means that both the functions and the extended scope point to the same object, but python don't do this directly. Instead a `cell object` is created, pointing to the value of the free variable, while the free variable, in both the extended scope and the function scope, point to the cell object.

```py
def outer():
  a = 10

  x = 'python'  #----------
                # THIS IS A
  def inner():  # CLOSURE
    print(x)    #----------
  
  return inner

fn = outer() 
'''
now outer has returned the function inner which should print x without directly containing a reference to the variable.
Therefore, since the scope of the function outer is exhausted after it is called, we should expect that the variable x=python is lost and can't be referenced by fn. Instead, it is possible since python, during compilation, sees a Closure and create an intermediate cell object that share the reference to x both from inner and outer functions.
'''

x = python #--|
             #|--> cell object --> str object `python`
  print(x) #--|              
```

Both `x` in outer and inner functions point to a `cell object` which contains a reference to another object in memory containing the string `python`. This lets us be able to call the function inner, returned from the function outer, even if the scope of outer is already exhausted. We can inspect the closure and free variables of an object:

```py
# from previous example
fn = outer()

fn.__code__.co_freevars # (x) while a is not a 
fn.__closure__ # cell object at address xxx containing a str object ('python') at address yyy
# the memory address of both the `x` (local and free) is the same and pointing to yyy
```

We can have multiple instance of the same closure, this means che each time a cell object is created, leaving the behavior of the different instance of the closure independent.

```py
def counter():
  # beginning of Closure
  count = 0

  def inc():
    nonlocal count
    count += 1
    return count
  # end of Closure

  return inc

  f1 = counter()
  f2 = counter()
  # f1 and f2 behavior is independent
```
## Shared extend scope

At the same time we can have `shared extended scope` of two different closures.

```py
def outer():
  count = 0

  def inc1():
    nonlocal count
    count += 1
    return count

  def inc2():
    nonlocal count
    count += 1
    return count

  return inc1, inc2

f1, f2 = outer() 
```

`f1` and `f2` are two closure that share the free variable `count` therefore both the functions, when called, will increment the value of count. If this behavior is wanted, then no problem, but often happens to share the same free variable without knowing it.

```py
# create a list of functions that add a two values
adders = []
for n in range(1, 4):
  adders.append(lambda x: x + n)

# what we expect to have is a list of functions
adders = [f1(n=1), f2(n=2), f3(n=3)]
# therefore calling 
adders[2](10) # should return 12 = 10 + 2 
# instead all the three functions will add 3, i.e. the last value at which n was pointing to
```

`n` is pointing to the cell object created by python during compilation, and it doesn't get evaluated until the function is called at which point the cell is pointing to the last value n was pointing to, therefore 3.

## Nested Closure

It is common, e.g. in decorators, to have nested closures:

```py
# define a function that takes an increment and a starting values and return a function that add the increment each time is called. The
def increment(n):

  def inner(start):
    current = start
    
    def inc():
      nonlocal current
      current += n
      return current

    return inc
  return inner

# Now inc has two free variables (current, n) one that lives in the `inner` scope and one in the `increment` scope.
# if we call:
fn = increment(2) # we will return the inner function with the variable n = 2
fn.__code__.co_freevars # `n` is the free variable of the closure containing the `inner` function
# if we than call:
inc_2 = fn(100) # we will return the `inc` function with the variable n = 2 and current = 100
inc.__code__.co_freevars # `n` and `current` are the free variables of the closure containing the `inc` function
# now if we call:
inc_2() # -> 102
inc_2() # -> 104
.
.
```


# Python optimizations

## Interning

Python at startup automatically pre-loads (caches) a global list of integer in the range [-5, 256], so these integers have a fixed memory reference. Since these numbers show up often, avoid to reference these each time they appear results in an optimization. A number outside this range will require a new memory reference, and that's why:

```py
a = 500
b = 500
a is b # will return False
```

The caches integers are called `Singletons`, basically classes that can be instantiated only once.

The same might happen with some strings; python can interning some string (that follow certain rules, letters and numbers concatenated with underscores) in order to speed up the equality (if a string in interned than i can use the `is` operator, otherwise i have to use the `==` character by character). We can force python to interning strings with the sys module:

```py
'''
usually is something we don't need, unless for example we are working with a large ste of string for NPL and we need to tokenize some words that are reaped often. In this case it can be a useful optimization, since if a string is interned it becomes a Singleton and can be compared with the mush faster 'is' operator.
'''
import sys

a = sys.intern('this will be interned')
```

## Peephole

Is an optimization that occur at compile time (so it is repeated each time the script is launched). For example we can have `Constant expression` like numeric calculation thata are better read as the operation rather than the results:

```py
minute_in_day = 60 * 24 # 1440
```

The expression `60 * 24` is more readable than `1440` but we may thing that, if the variable is called multiple times, we may have performance issues. This is not the case because this is a constant expression and python knows it, so the first time it encounters the variable stores its results, without having to compute it again.

The same happen for membership tests, i.e. check if an object is in a list. If we have a constant expression, python will replace the mutable object with is immutable counterpart (list-> tuples, sets -> fronzensets)

```py
for i in range(100000):
  if i in [1,2,3]:
    pass
```

The list `[1,2,3]` is converted into a tuple `(1,2,3)` so that, being immutable, it has a fixed memory address.

N.B. sets, since are similar to dictionaries (hashmaps), are much more efficient than lists for membership testing!

# Numeric Types

## Integers

Integer are represented internally using base-2 digits (binary representation)

```
# es. binary representation of 19

 0   0   0   1   0   0   1   1 
--- --- --- --- --- --- --- ---   -> max num of bits = 8
2^7 2^6 2^5 2^4 2^3 2^2 2^1 2^0

1x2^4 + 0x2^3 + 0x2^2 + 1*2^1 + 1*2^0 = 16 + 2 + 1 = 19

(10011)base_2 = (19)base_10

To represent the number 19 are required 5 digits, hence 5 bits.
```

But which is the largest number we can store depending on the number of bits we want to store? It depends whether or not we care about negative values, since in order to store the sign we have to allocate one bit.
The general formula is:

```py
max_unsigned_digit = 2^n -1 # where n is the number of bits
max_signed_digits = [-2^(n-1), 2^(n-1)-1]
```

Side-Note: a 32 bit Operative system can store 2^32 unsigned integers (roughly 4Gb) and this limits also the number of memory address that can be stored at the same time. This is why having more than 4Gb of ram on a 32 bit OS is essentially useless since the machine can't store more than 4 Gb at the same time.

### Operations

How mod `//` (floor division) and div `%` (modulo) operators works in python? mod returns the floor division (rounded to the smaller integer) while div return the remainder. They have to satisfy the following equation:

```py
n = d * (n // d) + (n % d) # where n is the numerator and d is the denominator

#n.b. the `floor` of a real number `a` is the largest integer `<= a` 
floor(3.14) = 3
floor(-3.1) = 3
```

### Base

We can create an `int` object by calling the `int()` constructor; this has an optional parameter that is the base that python has to use to translate the argument (it may also be a string). The default values is base=10, since it is the way we are use to read numbers (while machines works in binary, so base=2). If the base is greater then 10 that the numbers start to be encoded with letters ( base[0, 10] = [0, 10], base[11, 27] = [A, Z])

Python has some built-in function to translate the most common base like `bin()` for binary `hex()` for hexadecimal.

```py
bin(10) = 0b1010 # the 0b is telling us that the base 2 (binary)
```

## Rational Numbers

Rational numbers are those number which are not integer and can be represented with a finite number of digits or translated into a fraction of rational numbers. The module `fraction` can be used to represent rational numbers, since the float representation can be misleading due to machine precision.

```py
from fraction import Fraction

Fraction(1,2) #(numerator, denominator)
Fraction('0.125')
Fraction(22/7)

# CAVEAT
'''
Some numbers can't have a finite representation due to machine precision. For example 0.3 it is actually an approximation. The problem is that we have to look at something like the 20th decimal position in order to realize that this is the case. If we pass this number to Fraction() we would imagine to receive 3/10 as output; instead we would get a fraction of very huge numbers that best approximate that imprecision in the machine representation of 0.3 (0.2999999999999999999998977..)
'''
```

## Floats (Real Numbers)

In CPython floats are implemented as `C double` which implements the `binary64` (IEEE 754).
Floats use a fixed size of 64 bits divide as follow:

- sign -> 1 bit
- exponent (in the range[-1022, 1023]) -> 11bit
- significant digits -> 52 bit (15-17 significant digit in base_10)

To have a precise representation of real numbers (since float may be effected by machine precision), we can use the `decimal` module.

```py
# decimal representation of a real number
123.45 = 1*10^3 + 2*10^1 + 3*10^0 + 4*10^-1 + 5*10^-2 
```

### equality

Care must be taken when looking at the equality of floats since there are some decimal numbers that cannot be represented by a finite binary representation:

```py
base_10(0.1) = base_2(0.0 0011 0011 0011 ...) 
# therefore
x = 0.1 + 0.1 + 0.1 # 0.300000000000000175
y = 0.3 # 0.2999999999999999999998977
x == y # -> False
```

One workaround is to set a range delta (es. a percentage of the size of the larger number involved in the equality operation) as discriminant values to determine if two numbers are equal:

```py
|a - b| < epsilon
```

The pythonic way to approach this problem is to use the module `math.isclose` with the care of specifying appropriate relative and absolute tolerance:

```py
import math

math.isclose(x, y, rel_tol, abs_tol)
```

## Booleans (PEP 285)

Booleans are a subclass of the int class (i.e. it inherits all its methods). Two constant are defined: `True` (int = 1) and `False` (int = 0). They are Singleton objects, i.e. the point to a fixed address in memory and can be compared with the identity and the equality operator aswell. N.b. even if True and False evaluates to the int 1 and 0 respectively, they don't point ot the same memory address, since they are not the same type of object:

```py
int(True) == 1 and int(False) == 0
id(True) != id(1) # True is 1 -> False ; True == 1 -> True
id(False) != id(0)
```

Objects have an associated `truth value`, meaning that they have a defined truth state. In particular, every object will evaluate to `True` by default except for:

- None
- False
- 0 (in any numeric type, float, complex ..)
- empty sequence (list, tuple string..)
- empty mapping (dictionary, sets..)
- implementing __bool__ or __len__ in a custom class

Has a matter of fact, when we call `bool()` on an object, it will look for the definition of the dunder method `__bool__`, if this is not defined, then it will look for the `__len__` method and if also this is not defined the it will evaluate to `True`.

```py
# es. __bool__ implementation for the int class
def __bool__(self):
  return self != 0
```

### Booleans operators

```py
# Truth Table
X Y  not X  X and Y  X or Y
0 0    1       0       0
0 1    1       0       1
1 0    0       0       1 
1 1    0       1       1
```

```py
# De Morgan's Theorem
not(A or B) == (not A) and (not B)
not(A and B) == (not A) or (not B)

# Operations precedence (in descending order)
< > <= >= == != in is
not
and
or
```

### Short-Circuiting

Looking at a truth table there are two case in which the program can simply is job evaluating only part of a boolean statement. Thi is called `short-circuiting`:

```py
True or Y -> True 
# with an or statement, id the first argument is True it doesn't matter whether the second argument is True or False, the operation will always evaluate to True
False and Y -> False
# with an and statement, id the first argument is False it doesn't matter whether the second argument is True or False, the operation will always evaluate to False
```

This is very useful when we have to concatenate two conditions together, one of the which may results in rising an exception (and breaking the code) if evaluated. With short-circuiting we can add a first statement that check for a particular exception that may rise with second argument.

```py
my_string = 'ciao'
if 'a' == my_string[0]:
  do_something
# we are checking if 'a' is the first letter of my_string. But what happen if my_string is empty? the code breaks. We can solve this with short-circuiting the first
if my_string and 'a' == my_string[0]:
  do_something
# Since an "and" expression will evaluate to True only if the two members are True, we can safeguard our code from breaking checking first if my_string evaluates to False (i.e. if is an empty string); if it is so, the second part of the "and" statement won't be executed thus safeguarding our code of breaking due to IndexError exception.
```

# Common Modules

## string

Module with some useful string constants and representation.
