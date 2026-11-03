import AST.compareOp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Parser1Test3ParserTest {
    @Test
    public void TestParser1Test3Parser() throws Exception {
        var code = "findOne(name=\"Computer Science\", professor=\"mphipps\", course=\"ICSI311\", section=1 )\n"+
                "";
        var tokens = new Lexer(code).Lex();
        var p = new Parser(tokens);
        var top = p.parse().orElseThrow();
        Assertions.assertEquals("name",top.compare.get(0).left);
        Assertions.assertEquals(compareOp.equal,top.compare.get(0).thecompareOp);
        Assertions.assertFalse(top.compare.get(0).NUMBER.isPresent());
        Assertions.assertEquals("Computer Science",top.compare.get(0).STRINGLITERAL.orElseThrow());
        Assertions.assertFalse(top.compare.get(0).IDENTIFIER.isPresent());
        Assertions.assertEquals("professor",top.compare.get(1).left);
        Assertions.assertEquals(compareOp.equal,top.compare.get(1).thecompareOp);
        Assertions.assertFalse(top.compare.get(1).NUMBER.isPresent());
        Assertions.assertEquals("mphipps",top.compare.get(1).STRINGLITERAL.orElseThrow());
        Assertions.assertFalse(top.compare.get(1).IDENTIFIER.isPresent());
        Assertions.assertEquals("course",top.compare.get(2).left);
        Assertions.assertEquals(compareOp.equal,top.compare.get(2).thecompareOp);
        Assertions.assertFalse(top.compare.get(2).NUMBER.isPresent());
        Assertions.assertEquals("ICSI311",top.compare.get(2).STRINGLITERAL.orElseThrow());
        Assertions.assertFalse(top.compare.get(2).IDENTIFIER.isPresent());
        Assertions.assertEquals("section",top.compare.get(3).left);
        Assertions.assertEquals(compareOp.equal,top.compare.get(3).thecompareOp);
        Assertions.assertEquals("1",top.compare.get(3).NUMBER.orElseThrow());
        Assertions.assertFalse(top.compare.get(3).STRINGLITERAL.isPresent());
        Assertions.assertFalse(top.compare.get(3).IDENTIFIER.isPresent());
    }
}