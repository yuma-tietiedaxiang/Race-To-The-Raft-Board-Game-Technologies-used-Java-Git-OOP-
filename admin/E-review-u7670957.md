## Code Review

Reviewed by: Yu Ma, u7670957

Reviewing code written by:  Weiqi Huang  u7739624

Component: RaceToTheRaft  line208-284

### Comments 

This part is to implement a method for The main function of this method is to randomly draw cards 
from the corresponding deck based on the draw request and update the game state.
The code accepts as input an array of strings called gameState and a string called drawRequest, 
representing which deck to draw from and how many cards to draw from each deck respectively.
The code first creates a Map<Character, Deck> with the keys being the character identifiers 
of the decks ('A' through 'D') and the values being the Deck objects representing each deck. 
Then, a HashMap<Character, Integer> is constructed from the string of the card draw request, 
recording the number of cards to be drawn from each deck. 
Next, the code iterates through each deck, draws cards based on the draw request, 
and append the cards in order into a temporary StringBuilder.
Finally, update the deck information and hand 
information in the game state and return it.

The best features of the code include:
Appropriate data structures such as HashMap and StringBuilder are used to efficiently manage and 
manipulate game state and deck information.
The code implements clear logic by using appropriate loops and conditional statements, 
such as drawing cards from the deck and updating the game state.
Appropriate data types and methods are used, such as the use of Map to store deck information 
and StringBuilder to construct the final hand.
The code is clearly commented, providing a description of each method and the main logic, 
making the code easy to understand and maintain.
The code is well documented, clearly explaining what each method does and the implementation 
of the main logic. The decomposition of the programme is also appropriate, dividing the 
logic into sensible methods, improving the readability and maintainability of the code. 
The code follows Java code conventions, with proper method and variable naming and consistent 
style. For example, camel nomenclature is used for method and variable names, and there are 
stylistic conventions such as code indentation.

To point out errors in the code, there may be instances where the code still attempts to draw 
when the number of cards requested is greater than the actual number of cards remaining in the deck without sufficient checking 
and handling during the card extraction process. This can lead to array out-of-bounds or other 
exceptions.


