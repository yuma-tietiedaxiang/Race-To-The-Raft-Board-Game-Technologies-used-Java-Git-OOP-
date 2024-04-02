# D2A:

1. Project description added  tue14b0
2. Add **all group members** to your group’s repo as **‘maintainers’**
3. upload Project avatar
4. **Verify your repo description** and membership <u>with your tutor before continuing.</u>
5. Exchange contact information. Record this in `admin/members.yml` (for each member add “contact: ").
6. Record your meeting time at the top of your tasks.md file.



# D2B w5:

1. Marks will be distributed according to the relative contribution of each group member as documented in `B-contribution.yml`

2. **design sketch**: class \ fields \ methods

3. | File                     | Description                          |
   | :----------------------- | :----------------------------------- |
   | admin/B-design.pdf       | Pdf file containing a design sketch. |
   | admin/B-originality.yml  | Originality statement for Stage B.   |
   | admin/B-contribution.yml | Contribution statement for Stage B.  |



Ass 2 gitlab page:

1. [Race to the Raft - The City of Games (thecityofkings.com)](https://thecityofkings.com/games/race-to-the-raft/)

2. elements:

   1. **checkpoint**

      fields:

      method:

      - isOnBard
      - fire is adjacent location
      - cannot overlap fire and raft
      - cannot overlap cat
      - check if loss:

   2. **locationBoard**

      fields:

      - row
      - column

      methods:

      - getRow
      - getColumn
      - setRow
      - setColumn

   3. **challenges**

      fields:

      - layout for different challenges

      methods:

      

   4. **island board**

      fields:

      - ID: ???

      - locations(row,column)

      - 

      methods:

      - getLocations();

      - setLocations();

      

   5. **cats**

      fields:

      - color

      - ID: 1-5

      - location
      - boolean isExhausted

      methods:

      - move

      

   6. **fire tiles**

      fields:

      - ID: 1-5
      - originRow
      - originColumn

      - initial fire area in locations: location[] area = new location;

      methods:

      - add (originRow, origionColumn, )
      - getAllLocations()
      - rotate
      - horizontalFlip
      - verticalFlip

      

   7. **pathway cards**

      fields:

      - ID: 1-6 cannot be seen by players until you have drawn all six cards.

      - row

      - column

      methods:

      - discard pathway cards

      - rotate

      - getAllLocations()

        

   8. **decks**(enum)

      fields:

      - ID: 1-4 
      - percentColor: 33.3% green  

      methods:

      - isEmpty: if is empty, players cannot draw cards from this deck

      

   9. **raft**

      fields:

      - row
      - column

      methods:

      - getAllLocations()

      

   10. **colors(enum)**

       fields:

       - red, blue, green, yellow, and purple

       method:

       - fromChar
       - toChar

   

3. placement rules:

   - is on board: Pathway cards and fire tiles cannot have any of their squares hanging off the edge of the island.
   - fire is adjacent location: The squares of pathway cards and fire tiles must line up with the squares of the island.
   - rotate and flip: Pathway cards and fire tiles can be rotated in any orientation. Fire tiles can also be flipped, both horizontally and vertically.
   - can overlap pathway: Pathway cards and fire tiles can fully or partially overlap other pathway cards.
   - cannot overlap fire and raft: Pathway cards and fire tiles cannot overlap any squares with fire or any square belonging to a raft card.
   - cannot overlap cat: Pathway cards and fire tiles cannot overlap any squares with cats on them.
   - ?? Pathway cards and fire tiles cannot be placed under other pathway cards or fire tiles.
   - fire is adjacent location: Fire tiles must be placed next to existing fire. That is, at least one square of a fire tile being placed must touch at least one square of existing fire. Note that diagonals are not considered adjacent.
   - pathway don't  have to be adjacent: Pathway cards do not have any adjacency requirements like fire tiles.

4. Winning: all the cats are on the raft card.

5. Loosing：

   If a player cannot place a fire tile on the island in a valid way according to the game rules;

   If there is no way for a cat to reach the raft;

   If a there are no more fire tiles remaining AND the player is required to draw one; or

   If there are no more pathway cards in any of the decks AND the player is required to draw a card.

# D2C midbreak w1:

| Criterion                                                    | Marks |
| :----------------------------------------------------------- | :---- |
| 1. Required admin files have been correctly filled in, committed and pushed, passing the CI compliance test. | 0.25  |
| 2. Git log provides evidence of healthy teamwork and appropriate use of version control. | 0.25  |
| 3. Object-oriented game state representation is built from string representation (Task 3). | 1     |
| 4. Completed state viewer GUI as outlined in Task 4.         | 2     |
| 5. Tests passing for Tasks 2, 5, 6, and 7.                   | 1.5   |
| 6. The implemented design is good and makes appropriate use of object-orientation. | 0.5   |
| 7. Code and design is of outstanding quality.                | 0.5   |

| File                     | Description                          |
| :----------------------- | :----------------------------------- |
| admin/C-originality.yml  | Originality statement for Stage C.   |
| admin/C-contribution.yml | Contribution statement for Stage C.  |
| admin/C-best-uid.yml     | Statements of best code for Stage C. |



#### 6 tasks, start from task 2

2. 
