The purpose of test planning is for the group to identify which components of your game should be unit-tested. The test plan is a simple document, which should:

- **List the classes** in your implemented design that should have some unit testing.
- Within each class, **list methods** that can be tested in isolation.
- For each class, if there are conditions on the class’ behaviour that cannot be tested by calling one method in isolation, give at least one example of a test for such a condition. A very simple example could be something like “After calling `add(...)`, the number returned by `size()` has increased by 1.”

Do **not** include in your test plan the predefined static methods for which we have already provided unit tests.





