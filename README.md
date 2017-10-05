# CGStandardLib
A standard library to use in all CreativeGames libraries

## Language JSON system:
Lang system uses JSON files to get data,
a classic lang file is configured like this:

```JSON
 {
  "lang":{
    "@globalvar":"I'm a global var",
    "textwithglobal": "global variable is @globalvar",
    "givenvar": "My name is $0",
    "classictext": "I don't take vars in my text"
  }
 }
```

(The JSON **ALWAYS** have to start with a "lang" root Object !)

### Global variables
The "@globalvar" variable is a global variable (thx captain obvious !)
It means that the variable is defined once and can be used as many times in all the other variables

Ex: the "textwithglobal" contains the global variable '@globalvar' and will automatically be replaced with the content of this variable
this means the variable "textwithglobal" will be "global variable is I'm a global var"

### Inner variables

Tou can declare inner variable in the language like the "givenvar" in the JSON example, //TODO
