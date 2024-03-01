# Scriptio

## About

Scriptio is an (incomplete) interpreted programming language that uses Latin keywords. In it's current state, the language has a lexer and parser, which outputs a formatted abstract syntax tree to the terminal.

## Support, Language Features

### Tokens

Here is the complete list of currently supported tokens:
- VariableDeclaration
- Identifier
- Mutable
- Type
- UpdateOperator
- AssignmentOperator
- Number
- String
- Boolean
- OpenParen
- CloseParen
- BinaryOperator
- SemiColon

And this is the list of the language's reserved key words:
- fac
- numerus
- verbum
- veredictumne
- variabilis
- verus
- falsus

### Syntax

Scriptio is currently capable of parsing variable declarations of three different types:
- integers
- strings
- booleans
Scriptio can also parse reassignment with `=`, and also supports `++` (increment), `--` (decrement), `+=` (plus-equals), `-=` (minus-equals), `*=` (times-equals), `/=` (divide-equals), and `%=` (modulo-equals). Additionally, the five binary expressions, namely `+` (add), `-` (subtract), `*` (multiply), `/` (divide), and `%` (mod).

Variables are considered immutable by default. Variables can be declared with the `fac` keyword + the optional `variabilis` keyword (which declares a variable as mutable) + a type keyword (`numerus` - integer, `verbum` - string, and `veredictumne` - boolean). If the variable is mutable, this sequence can be followed by a `;`, however, if it is immutable, it must be followed by `=`, then a value that matches the type, then a `;`.

All mutable variables can be reassigned using the `=` operator. In addition to this, mutable integer variables can be reassigned using any of the reassignment tokens. All reassignments must be followed with a `;`.

<code style="color: red">NOTE: reassignment types and operators are not checked by the parser, but would need to be checked during the program's evaluation!</code>

## Running Scriptio

To run a Scriptio script, you must first have [Gradle](https://gradle.org/) installed. Then download the Scriptio source code. In `./app/examples` there is a variable declaration example (`variables.scriptio`). You can parse your own Scriptio code by adding a file to the `examples` directory. 

To run the pre-existing example or your own Scriptio code, first run `gradle build` in the command line at the project root. Then, run `gradle run --args $EXAMPLE_FILE_PATH`. 

<code style="color: red">The file path passed must be relative to the example directory, not the project root directory.</code>

For example:
```
gradle run --args variables.scriptio
```
