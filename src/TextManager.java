
public class TextManager
{
    private String text; //stores the text we want read
    private int position; //where we are in the word
    private int lineNumber; //keeps track of what line u are on
    private int characterPosition; //keeps track of here we are in the line

    public TextManager(String text) //gives the text it needs to read
    {
        this.text = text;
        position = 0;
        lineNumber = 0;
        characterPosition = 0;
    }

    public char getCharacter() //gets current char and then moves to next char
    {
        char character = text.charAt(position);
        position++;

        if(character == '\n')
        {
            lineNumber++;
            characterPosition = 0;
        }
        else
        {
            characterPosition++;
        }

        return character;
    }

    public char peekCharacter() //looks at the current char and doesnt move foward
    {
        return text.charAt(position);
    }

    public char peekCharacter(int distance) //looks ahead a character without moving
    {
        return text.charAt(position + distance);
    }

    public boolean hasNextCharacter() //stops from looking past end of text
    {
        return position < text.length();
    }

    public boolean isAtEnd() //checks if the end of the text is reached
    {
        return position >= text.length();
    }

    public int getLineNumber() //gets current line num
    {
        return lineNumber;
    }

    public int getCharacterPosition() //gets current position
    {
        return characterPosition;
    }

}
