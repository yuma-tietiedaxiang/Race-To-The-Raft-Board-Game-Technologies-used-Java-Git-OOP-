## Code Review

Reviewed by: weiqi huang, u7739624

Reviewing code written by: Aditya Arora, u7865708

Component: 

        public static boolean isBoardStringWellFormed(String boardString) {
        
        String[] list = boardString.split("\\r?\\n");

        int length = list[0].length();

        String newline = System.lineSeparator();
        boolean hasNewline = boardString.substring(boardString.length() - 1).contains(newline);

        if ((list.length != 12 && list.length != 15 && list.length != 18) || (length != 9 && length != 18) || !hasNewline) {
            return false;
        }

        for (String string : list) {
            if (string.length() != length) {
                return false;
            }

            for (char ch : string.toCharArray()) {
                if (!isValidChar(ch)) {
                    return false;
                }
            }

        }

        return true; // FIXME TASK 2 - Done!!
    }


    private static boolean isValidChar(char ch) {
        return ch == 'b' || ch == 'B' || ch == 'f' || ch == 'g' || ch == 'G' ||
                ch == 'n' || ch == 'o' || ch == 'p' || ch == 'P' || ch == 'r' ||
                ch == 'R' || ch == 'w' || ch == 'W' || ch == 'y' || ch == 'Y';
    }

### Comments 
    - best features 
    - The code handles different scenarios, such as checking the number of lines, characters per line, 
      and the validity of each character.
    - It uses appropriate methods like `split()`, `length()`, and `toCharArray()` to analyze the input string.

    - well-documented
    - The code is well-documented with comments in Adi's tasks.

    - decomposition (class and method structure) appropriate
    - The code is structured as a single method `isBoardStringWellFormed()` within a class.
    - The helper method `isValidChar()` is used to check the validity of individual characters, 
      promoting code reuse and modularity.

    - Java code conventions
    - The method name `isBoardStringWellFormed()` is descriptive and uses camelCase.
    - Variable names like `list`, `length`, `newline`, and `hasNewline` are meaningful and follow the camelCase convention.

    - not function correctly
    - The code checks if the last character is a newline using `substring()` and `contains()`. 
      However, if the input string is empty, this will also throw a `StringIndexOutOfBoundsException`.
    - To fix this, the code should check the length of `boardString` before performing the substring operation. 
      For example:
    ``` boolean hasNewline = boardString.length() > 0 && boardString.substring(boardString.length() - 1).contains(newline);
    ```



