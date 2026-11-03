import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import AST.*;

public class Parser2Test4ParserTest {
    @Test
    public void TestParser2Test4Parser() throws Exception {
        var code =          "insert Departments\n"+
         "    1, \"Computer Science\"\n"+
         "\n"+
         "insert Rooms\n"+
         "    1, findOne (name=\"University Administration Building\"), 440\n"+
         "\n"+
         "insert Buildings\n"+
         "    1, \"University Administration Building\", \"University Administration Bldg, Albany, NY 12203\", \"Computer Science\", [findOne (building=1)]\n"+
"";
        var tokens = new Lexer(code).Lex();
        var p=new Parser(tokens);
        var top=p.sharQL().orElseThrow();
Assertions.assertEquals("Departments",top.statement.get(0).insert.orElseThrow().name);
Assertions.assertEquals("1",top.statement.get(0).insert.orElseThrow().values.get(0).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Computer Science",top.statement.get(0).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("Rooms",top.statement.get(1).insert.orElseThrow().name);
Assertions.assertEquals("1",top.statement.get(1).insert.orElseThrow().values.get(0).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(1).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(1).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("University Administration Building",top.statement.get(1).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("440",top.statement.get(1).insert.orElseThrow().values.get(0).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertEquals("Buildings",top.statement.get(2).insert.orElseThrow().name);
Assertions.assertEquals("1",top.statement.get(2).insert.orElseThrow().values.get(0).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("University Administration Building",top.statement.get(2).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertEquals("University Administration Bldg, Albany, NY 12203",top.statement.get(2).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("Computer Science",top.statement.get(2).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(4).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(4).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(4).name.isPresent());
Assertions.assertEquals("building",top.statement.get(2).insert.orElseThrow().values.get(0).value.get(4).valueList.orElseThrow().findOne.get(0).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(2).insert.orElseThrow().values.get(0).value.get(4).valueList.orElseThrow().findOne.get(0).compare.get(0).thecompareOp);
Assertions.assertEquals("1",top.statement.get(2).insert.orElseThrow().values.get(0).value.get(4).valueList.orElseThrow().findOne.get(0).compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(4).valueList.orElseThrow().findOne.get(0).compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(4).valueList.orElseThrow().findOne.get(0).compare.get(0).IDENTIFIER.isPresent());
        }
        }
