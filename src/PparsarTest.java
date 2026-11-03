import AST.compareOp;
import AST.findOne;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PparsarTest
{
    @Test
    public void testSimpleFindOne() throws Exception
    {
        String code = "findOne(name=\"offut\")";

        Lexer lexer = new Lexer(code);
        Parser parser = new Parser(lexer.Lex());

        findOne result = parser.parse().orElseThrow();

        Assertions.assertEquals(1, result.compare.size());
        Assertions.assertEquals("name", result.compare.get(0).left);
        Assertions.assertEquals(compareOp.equal, result.compare.get(0).thecompareOp);
        Assertions.assertEquals("offut", result.compare.get(0).STRINGLITERAL.orElseThrow());
    }

    @Test
    public void testProfessorFourComparisons() throws Exception
    {
        String code = "findOne(name=\"Computer Science\", professor=\"mphipps\", course=\"ICSI311\", section=1 )";

        Lexer lexer = new Lexer(code);
        Parser parser = new Parser(lexer.Lex());

        findOne result = parser.parse().orElseThrow();

        Assertions.assertEquals(4, result.compare.size());

        Assertions.assertEquals("name", result.compare.get(0).left);
        Assertions.assertEquals("Computer Science", result.compare.get(0).STRINGLITERAL.orElseThrow());

        Assertions.assertEquals("professor", result.compare.get(1).left);
        Assertions.assertEquals("mphipps", result.compare.get(1).STRINGLITERAL.orElseThrow());

        Assertions.assertEquals("course", result.compare.get(2).left);
        Assertions.assertEquals("ICSI311", result.compare.get(2).STRINGLITERAL.orElseThrow());

        Assertions.assertEquals("section", result.compare.get(3).left);
        Assertions.assertEquals("1", result.compare.get(3).NUMBER.orElseThrow());
    }
}