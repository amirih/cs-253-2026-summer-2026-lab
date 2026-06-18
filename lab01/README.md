# Lab 01: Java and OOP Foundations

Lab01 introduces basic Java syntax, object-oriented programming, recursion, and algorithm analysis. It is the foundation for the later data structure and algorithm labs.

## Concepts

| Step | Concept                                 | Simple description                                                                               | Example                                                                                       |
| ---- | --------------------------------------- | ------------------------------------------------------------------------------------------------ | --------------------------------------------------------------------------------------------- |
| 1    | Variables and methods                   | Variables store values. Methods group reusable actions or calculations.                          | `int age = 20;` stores a number. `add(2, 3)` returns `5`.                                     |
| 2    | Classes and objects                     | A class is a blueprint. An object is one real instance of that blueprint.                        | `Student` is a class. `new Student()` creates one student object.                             |
| 3    | Constructors, fields, and encapsulation | A constructor initializes an object. Private fields protect data. Public methods control access. | A `BankAccount` keeps `balance` private and changes it through `deposit()` and `withdraw()`.  |
| 4    | Inheritance and overriding              | A child class can reuse and customize behavior from a parent class.                              | `Dog` extends `Animal` and overrides `speak()` to print `woof`.                               |
| 5    | Polymorphism                            | Different object types can be treated through the same parent type.                              | A `Circle` and `Rectangle` can both be stored as `Shape` objects and both can calculate area. |
| 6    | Interfaces and dependency inversion     | Code depends on a general behavior instead of one exact class.                                   | `AlertService` uses a `Notifier`, so it can work with email or SMS.                           |
| 7    | Enumerations                            | An enum limits a value to a fixed set of options.                                                | `EngineType` can be `GASOLINE`, `ELECTRIC`, or `HYBRID`.                                      |
| 8    | Generics                                | Generics let one class or method work safely with many data types.                               | `Box<String>` stores text. `Box<Integer>` stores numbers.                                     |
| 9    | Exceptions and error handling           | Exceptions report invalid situations instead of silently failing.                                | Adding grade `150` throws an error because grades must be between `0` and `100`.              |
| 10   | Advanced OOP design                     | Interfaces, generics, inheritance, and services are combined into a flexible design.             | A repository can save `Student` and `Teacher` users and find one by ID.                       |
| 11   | Recursion                               | A method solves a problem by calling itself on a smaller version of the problem.                 | `factorial(5)` becomes `5 * factorial(4)` and eventually returns `120`.                       |
| 12   | Big O notation                          | Big O describes how running time grows as input size grows.                                      | One loop is usually `O(n)`. Two nested loops are usually `O(n^2)`.                            |

## Big ideas

- Java programs are built from classes, objects, fields, and methods.
- Encapsulation protects object state from invalid changes.
- Inheritance, interfaces, and polymorphism make code more flexible.
- Recursion needs a base case and a recursive case.
- Big O helps compare algorithm efficiency without measuring exact seconds.

## Quick examples

```java
// Method example
static int add(int a, int b) {
    return a + b;
}

// Object example
Student s = new Student();
s.name = "Ava";
```

```text
factorial(3) = 3 * 2 * 1 = 6
```

## Study order

Start with variables and methods, then classes and objects, then OOP design. Finish with recursion and Big O because they are used heavily in later labs.
