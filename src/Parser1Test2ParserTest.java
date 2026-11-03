import AST.compareOp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Parser1Test2ParserTest {
    @Test
    public void TestParser1Test2Parser() throws Exception {
        var code = "findOne(lockerNumber=101)\n"+
                "";
        var tokens = new Lexer(code).Lex();
        var p = new Parser(tokens);
        var top = p.parse().orElseThrow();
        Assertions.assertEquals("lockerNumber",top.compare.get(0).left);
        Assertions.assertEquals(compareOp.equal,top.compare.get(0).thecompareOp);
        Assertions.assertEquals("101",top.compare.get(0).NUMBER.orElseThrow());
        Assertions.assertFalse(top.compare.get(0).STRINGLITERAL.isPresent());
        Assertions.assertFalse(top.compare.get(0).IDENTIFIER.isPresent());
    }
}