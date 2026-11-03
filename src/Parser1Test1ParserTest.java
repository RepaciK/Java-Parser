import AST.compareOp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Parser1Test1ParserTest {
    @Test
    public void TestParser1Test1Parser() throws Exception {
        var code = "findOne(name=\"Computer Science\")\n"+
                "";
        var tokens = new Lexer(code).Lex();
        var p = new Parser(tokens);
        var top = p.parse().orElseThrow();
        Assertions.assertEquals("name",top.compare.get(0).left);
        Assertions.assertEquals(compareOp.equal,top.compare.get(0).thecompareOp);
        Assertions.assertFalse(top.compare.get(0).NUMBER.isPresent());
        Assertions.assertEquals("Computer Science",top.compare.get(0).STRINGLITERAL.orElseThrow());
        Assertions.assertFalse(top.compare.get(0).IDENTIFIER.isPresent());
    }
}