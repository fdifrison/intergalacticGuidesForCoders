consol# Javascript Manual

Javascript is an high-level, object-oriented, multy-paradigm programming language. In web development, while htm and css are responsible for the content and the styling of the webpage, js is responsible for handling its dynamic and user interaction, to load data, e.g. from databases, to manipulate html and css content and so on. Frameworks like React, Angular and Vue.js are tools that enhance the creation of webpages and are 100% based on js. In the same way we can use js also outise the browser, on webserver, to build back-end services with framework like node.js.

## JS release
Starting from 2015, with the release of ES6 (ECMAScript) we talk about modern javascript, and since then an annual release has been done with some new features.

## Run JS code
JS code needs always to be related to an html file. We can direclty code inside the html file under the `<script>` tag. We can watch what we have exectuted usign the webpage inspector under the `console` tag only if we have redirected the output to the console itself using the command `console.log(my_var)`. However, what is done in practice is to create a separete `script.js` file that will contain the js instruction and will be loaded inside the html file. Theprimit connection between html and js is usually done at the bottom of the body part as:

```js
<script src="script.js"><script>
```

## Styling rules
* commands must be ended with semicolon `;`
* variables are written in camelCase style
* variables cannot start with numbers, and can contains only number, letters, dollar sign and underscore
* variables cannot use reserved keyword like *new* or *function*
* variables shoudnt start with upper case letter (uppercase are related to class)
* constant are writte in uppercase
* use descriptive variable even if a bit longer

### Comments
Simple comments are done with the `//` while multi-line comments are done with `/* ... */`

## Values and Variable
Values are basically the smallest unit of information that we have in js; these can be stored in variable with the assignation symbol `=`. To declare a variable we simply:

```js
let firstName = "Giovanni";
```

*firstname* is now a variable that stores a string and can be later be manipulated or printed to console. `let` has to be used only the first time we declare a variable, if we want to reassing a value to an existing variable we don't need it.

### Variable declaration
We have ssen that to declare a variable the first time we use the keyword `let`, while to mutate its content we can simply call the variable name. There are other methods from ES6 to declare varibles that are more expressive: 

* `const` is used to declare variable that cannot mutate; for this reason, it is not possible to declare empty const variable.

* `var` is the prior ES6 declaration, `not to be used anymore!`

As a best practice, it is better to always use `const` to declare a variable unless we are really sure it should be changed along the script. We want to have the minimum amoung of mutable variable in the code since these can be source of bug.

We can potentially use a variable withou using any of these constructure and it will work just fine, but it is a terrible idea since it want have a reference in the local scope of the script!

## Datatypes
Like python, js is a dynamically typed programming language, meaning that the type of data passed to a variable is not declared but directly inferred by js and, in general, can be changed at any time. It is the value thata as ah inherent type, not the variable.

We can inspect the type of a value or a variable by using the operator `typeof` 

```js
console.log(typeof true);
// output: boolean
```

There are several type of datatypes in js. but first of all lets distinguish between `object` and `primitive`.

`Primitive` are:
* numbers, by default floats
* string
* boolean
* Undefined, a variable that has been declared but not assigned
* Null, another empty value (n.b. for legacy reasons, *typeof null* will return *object*)
* Symbol, a value that is unique and cannot be changed
* BigInt (from ES2020), a larger integer, too large for the number type

