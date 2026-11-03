//import SharQL.*;
//import SharQL.Lexer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Lexer1UnitTestsLexerTest {
    @Test
    public void TestLexer1UnitTestsLexer() throws Exception {
        var code =          "student class\n"+
         "hours create table\n"+
         "enum string wholeNumber schedule decimalNumber\n"+
         "list insert findOne delete\n"+
         "from where return professor\n"+
"";
        var tokens = new Lexer(code).Lex();
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(0).Type);
        Assertions.assertEquals("student",  tokens.get(0).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(1).Type);
        Assertions.assertEquals("class",  tokens.get(1).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(2).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(3).Type);
        Assertions.assertEquals("hours",  tokens.get(3).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(4).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(5).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(6).Type);
        Assertions.assertEquals(Token.TokenTypes.ENUM, tokens.get(7).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(8).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(9).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(10).Type);
        Assertions.assertEquals("schedule",  tokens.get(10).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DECIMALNUMBER, tokens.get(11).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(12).Type);
        Assertions.assertEquals(Token.TokenTypes.LIST, tokens.get(13).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(14).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(15).Type);
        Assertions.assertEquals(Token.TokenTypes.DELETE, tokens.get(16).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(17).Type);
        Assertions.assertEquals(Token.TokenTypes.FROM, tokens.get(18).Type);
        Assertions.assertEquals(Token.TokenTypes.WHERE, tokens.get(19).Type);
        Assertions.assertEquals(Token.TokenTypes.RETURN, tokens.get(20).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(21).Type);
        Assertions.assertEquals("professor",  tokens.get(21).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(22).Type);
    }
}
