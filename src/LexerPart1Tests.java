import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//tests different parts of my lexer
public class LexerPart1Tests
{
    @Test
    public void testSingleIdentifier() throws Exception
    {
        Lexer lexer = new Lexer("student");

        var tokens = lexer.Lex();

        Assertions.assertEquals(1, tokens.size());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(0).Type);
        Assertions.assertEquals("student", tokens.get(0).Value.orElseThrow());
    }

    @Test //Lexer test with two keywords
    public void testKeywords() throws Exception
    {
        Lexer lexer = new Lexer("create table");

        var tokens = lexer.Lex(); //runs the lexer and stores the created token

        Assertions.assertEquals(2, tokens.size());
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(0).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(1).Type);
    }

    @Test //Lexer test with keyword and numbers
    public void testIdentifierWithNumbers() throws Exception
    {
        Lexer lexer = new Lexer("student123");

        var tokens = lexer.Lex();

        Assertions.assertEquals(1, tokens.size());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(0).Type);
        Assertions.assertEquals("student123", tokens.get(0).Value.orElseThrow());
    }

    @Test //Test with a big space in between the words
    public void testSpacesAreIgnored() throws Exception
    {
        Lexer lexer = new Lexer("create     table");

        var tokens = lexer.Lex();

        Assertions.assertEquals(2, tokens.size());
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(0).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(1).Type);
    }

    @Test  //Test with \n implemented
    public void testNewline() throws Exception
    {
        Lexer lexer = new Lexer("create\ntable");

        var tokens = lexer.Lex();

        Assertions.assertEquals(3, tokens.size());
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(0).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(1).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(2).Type);
    }

    @Test //tests a number
    public void testNumber() throws Exception
    {
        Lexer lexer = new Lexer("12345");

        var tokens = lexer.Lex();

        Assertions.assertEquals(1, tokens.size());
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(0).Type);
        Assertions.assertEquals("12345", tokens.get(0).Value.orElseThrow());
    }

    @Test //tests a decimal number
    public void testDecimalNumber() throws Exception
    {
        Lexer lexer = new Lexer("999999.99");

        var tokens = lexer.Lex();

        Assertions.assertEquals(1, tokens.size());
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(0).Type);
        Assertions.assertEquals("999999.99", tokens.get(0).Value.orElseThrow());
    }

    @Test
    public void testPunctuation() throws Exception
    {
        Lexer lexer = new Lexer("( ) , < > [ ] = <= >= <>");

        var tokens = lexer.Lex();

        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(0).Type);
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(1).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(2).Type);
        Assertions.assertEquals(Token.TokenTypes.LESSTHAN, tokens.get(3).Type);
        Assertions.assertEquals(Token.TokenTypes.GREATERTHAN, tokens.get(4).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(5).Type);
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(6).Type);
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(7).Type);
        Assertions.assertEquals(Token.TokenTypes.LESSTHANEQUAL, tokens.get(8).Type);
        Assertions.assertEquals(Token.TokenTypes.GREATERTHANEQUAL, tokens.get(9).Type);
        Assertions.assertEquals(Token.TokenTypes.NOTEQUAL, tokens.get(10).Type);
    }
}