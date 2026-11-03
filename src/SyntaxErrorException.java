public class SyntaxErrorException extends Exception
{
    private int LineNumber, CharacterPosition;

    public SyntaxErrorException(String message, int LineNumber, int CharacterPosition) 
    {
        super(message);
        this.LineNumber = LineNumber;
        this.CharacterPosition = CharacterPosition;
    }

    public String toString()
    {
        return "Error at line %d at character %d at %s".formatted(LineNumber,CharacterPosition, super.toString());
    }
}
