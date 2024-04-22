The purpose of test planning is for the group to identify which components of your game should be unit-tested. The test plan is a simple document, which should:

- **List the classes** in your implemented design that should have some unit testing.
- Within each class, **list methods** that can be tested in isolation.
- For each class, if there are conditions on the class’ behaviour that cannot be tested by calling one method in isolation, give at least one example of a test for such a condition. A very simple example could be something like “After calling `add(...)`, the number returned by `size()` has increased by 1.”

Do **not** include in your test plan the predefined static methods for which we have already provided unit tests.





1. Class: Cat

   method: 

   overlap : check if overlap with fire

    isExausted

2. Class: Challange

   method:

   setChallange: check if the difficulty is vaild

3. Class: Deck

   method:

   hasNoCards

4. Class: FireTile

   method:

   rotate: check if rotated right

   flip: check if flipped right

   notOverLap: not on fire, cat

   isAdjacent: adjacent to other fire on theBoard

5. Class: IslandBoard

   method:

   rotateIsland

6. Class: PathwayCard

   method:

   parseCardString

   getNumberOfCardsInHand

   getCardsInHand

7. Class: TheBoard

   method:

   hasCat

   hasFire

   parseBoardString

   





