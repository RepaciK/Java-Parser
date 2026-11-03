import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import AST.*;

public class Parser2Test2ParserTest {
    @Test
    public void TestParser2Test2Parser() throws Exception {
        var code =          "insert restaurant\n"+
         "    \"Sarah’s Pizzeria\", \"123 Springfield Blvd, New York, NY\", italian, 4.0\n"+
         "    \"Viva Cinco De Mayo\", \"456 Linden Blvd, New York, NY\", mexican, 4.4\n"+
         "\n"+
         "insert menuItem\n"+
         "    \"Pizza\", 12.99, [findOne(name=\"Dough\"), findOne(name=\"Mozzarella\")],findOne(name=\"Sarah’s Pizzeria\")\n"+
         "    \"Burger\", 8.99, [findOne(name=\"Bun\"), findOne(name=\"Beef Patty\")],findOne(name=\"Sarah’s Pizzeria\")\n"+
         "    \"Tacos\", 9.99, [findOne(name=\"Tortilla\"), findOne(name=\"Salsa\")], findOne(name=\"Viva Cinco De Mayo\")\n"+
         "\n"+
         "insert ingredient\n"+
         "    \"Dough\", gluten\n"+
         "    \"Mozzarella\", diary\n"+
         "    \"Beef Patty\", none\n"+
         "    \"Bun\", gluten\n"+
         "    \"Tortilla\", gluten\n"+
         "    \"Salsa\", none\n"+
         "\n"+
         "insert order\n"+
         "    findOne(name=\"Takeem\"), findOne(name=\"Sarah’s Pizzeria\"), [findOne(quantity=5)],delivered, findOne(name=\"Elizabeth\")\n"+
         "    findOne(name=\"Francis\"), findOne(name=\"Viva Cinco De Mayo\"), [findOne(quanity=6)], preparing, findOne(name=\"Lijah\")\n"+
         "\n"+
         "insert reservation\n"+
         "    findOne(name=\"Takeem\"), findOne(tableNumber=1, capacity=6), 5, \"8:00 PM\"\n"+
         "    findOne(name=\"Francis\"), findOne(tableNumber=3, capacity=4), 4, \"10:00 PM\"\n"+
         "\n"+
         "insert customer\n"+
         "    \"Takeem\", \"789 Sutphin Blvd\", \"656-778-2245\"\n"+
         "    \"Francis\", \"632 Foch Blvd\", \"434-776-6654\"\n"+
         "\n"+
         "insert review\n"+
         "    findOne(name=\"Takeem\"), findOne(name=\"Sarah’s Pizzeria\"), 4, \"Tasted good\"\n"+
         "    findOne(name=\"Francis\"), findOne(name=\"Viva Cinco De Mayo\"), 4, \"Fast delivery and the food had a tasty spice to it!\"\n"+
         "\n"+
         "insert orderItem\n"+
         "    findOne(name=\"Pizza\"), 5\n"+
         "    findOne(name=\"Tacos\"), 6\n"+
         "\n"+
         "insert diningTable\n"+
         "    findOne(name=\"Sarah’s Pizzeria\"), 2, 4\n"+
         "    findOne(name= \"Viva Cinco De Mayo\"), 3, 4\n"+
         "    findOne(name=\"Sarah’s Pizzeria\"), 6, 6\n"+
         "\n"+
         "insert deliveryDriver\n"+
         "    \"Elizabeth\", \"Honda Civic\", 5.0\n"+
         "    \"Lijah\", \"Nissan Versa\", 4.8\n"+
         "\n"+
         "delete deliveryDriver\n"+
         "    findOne(name=\"Lijah\")\n"+
"";
        var tokens = new Lexer(code).Lex();
        var p=new Parser(tokens);
        var top=p.sharQL().orElseThrow();
Assertions.assertEquals("restaurant",top.statement.get(0).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Sarah’s Pizzeria",top.statement.get(0).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("123 Springfield Blvd, New York, NY",top.statement.get(0).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertEquals("italian",top.statement.get(0).insert.orElseThrow().values.get(0).value.get(2).name.orElseThrow());
Assertions.assertEquals("4.0",top.statement.get(0).insert.orElseThrow().values.get(0).value.get(3).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Viva Cinco De Mayo",top.statement.get(0).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("456 Linden Blvd, New York, NY",top.statement.get(0).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(1).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertEquals("mexican",top.statement.get(0).insert.orElseThrow().values.get(1).value.get(2).name.orElseThrow());
Assertions.assertEquals("4.4",top.statement.get(0).insert.orElseThrow().values.get(1).value.get(3).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(1).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(1).value.get(3).name.isPresent());
Assertions.assertEquals("menuItem",top.statement.get(1).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Pizza",top.statement.get(1).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("12.99",top.statement.get(1).insert.orElseThrow().values.get(0).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertEquals("name",top.statement.get(1).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(1).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Dough",top.statement.get(1).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("name",top.statement.get(1).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(1).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Mozzarella",top.statement.get(1).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertEquals("name",top.statement.get(1).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(1).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Sarah’s Pizzeria",top.statement.get(1).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Burger",top.statement.get(1).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertEquals("8.99",top.statement.get(1).insert.orElseThrow().values.get(1).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(1).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertEquals("name",top.statement.get(1).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(1).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Bun",top.statement.get(1).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("name",top.statement.get(1).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(1).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Beef Patty",top.statement.get(1).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(1).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(1).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(1).value.get(3).name.isPresent());
Assertions.assertEquals("name",top.statement.get(1).insert.orElseThrow().values.get(1).value.get(3).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(1).insert.orElseThrow().values.get(1).value.get(3).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(1).value.get(3).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Sarah’s Pizzeria",top.statement.get(1).insert.orElseThrow().values.get(1).value.get(3).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(1).value.get(3).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(2).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Tacos",top.statement.get(1).insert.orElseThrow().values.get(2).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(2).value.get(0).name.isPresent());
Assertions.assertEquals("9.99",top.statement.get(1).insert.orElseThrow().values.get(2).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(2).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(2).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(2).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(2).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(2).value.get(2).name.isPresent());
Assertions.assertEquals("name",top.statement.get(1).insert.orElseThrow().values.get(2).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(1).insert.orElseThrow().values.get(2).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(2).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Tortilla",top.statement.get(1).insert.orElseThrow().values.get(2).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(2).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("name",top.statement.get(1).insert.orElseThrow().values.get(2).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(1).insert.orElseThrow().values.get(2).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(2).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Salsa",top.statement.get(1).insert.orElseThrow().values.get(2).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(2).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(2).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(2).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(2).value.get(3).name.isPresent());
Assertions.assertEquals("name",top.statement.get(1).insert.orElseThrow().values.get(2).value.get(3).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(1).insert.orElseThrow().values.get(2).value.get(3).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(2).value.get(3).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Viva Cinco De Mayo",top.statement.get(1).insert.orElseThrow().values.get(2).value.get(3).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(2).value.get(3).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("ingredient",top.statement.get(2).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Dough",top.statement.get(2).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertEquals("gluten",top.statement.get(2).insert.orElseThrow().values.get(0).value.get(1).name.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Mozzarella",top.statement.get(2).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertEquals("diary",top.statement.get(2).insert.orElseThrow().values.get(1).value.get(1).name.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(2).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Beef Patty",top.statement.get(2).insert.orElseThrow().values.get(2).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(2).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(2).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(2).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertEquals("none",top.statement.get(2).insert.orElseThrow().values.get(2).value.get(1).name.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(3).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Bun",top.statement.get(2).insert.orElseThrow().values.get(3).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(3).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(3).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(3).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertEquals("gluten",top.statement.get(2).insert.orElseThrow().values.get(3).value.get(1).name.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(4).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Tortilla",top.statement.get(2).insert.orElseThrow().values.get(4).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(4).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(4).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(4).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertEquals("gluten",top.statement.get(2).insert.orElseThrow().values.get(4).value.get(1).name.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(5).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Salsa",top.statement.get(2).insert.orElseThrow().values.get(5).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(5).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(5).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(5).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertEquals("none",top.statement.get(2).insert.orElseThrow().values.get(5).value.get(1).name.orElseThrow());
Assertions.assertEquals("order",top.statement.get(3).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(3).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(3).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Takeem",top.statement.get(3).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(3).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(3).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Sarah’s Pizzeria",top.statement.get(3).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertEquals("quantity",top.statement.get(3).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(3).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).thecompareOp);
Assertions.assertEquals("5",top.statement.get(3).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertEquals("delivered",top.statement.get(3).insert.orElseThrow().values.get(0).value.get(3).name.orElseThrow());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(4).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(4).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(4).name.isPresent());
Assertions.assertEquals("name",top.statement.get(3).insert.orElseThrow().values.get(0).value.get(4).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(3).insert.orElseThrow().values.get(0).value.get(4).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(4).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Elizabeth",top.statement.get(3).insert.orElseThrow().values.get(0).value.get(4).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(4).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(3).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(3).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Francis",top.statement.get(3).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(3).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(3).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Viva Cinco De Mayo",top.statement.get(3).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertEquals("quanity",top.statement.get(3).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(3).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).thecompareOp);
Assertions.assertEquals("6",top.statement.get(3).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertEquals("preparing",top.statement.get(3).insert.orElseThrow().values.get(1).value.get(3).name.orElseThrow());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(4).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(4).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(4).name.isPresent());
Assertions.assertEquals("name",top.statement.get(3).insert.orElseThrow().values.get(1).value.get(4).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(3).insert.orElseThrow().values.get(1).value.get(4).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(4).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Lijah",top.statement.get(3).insert.orElseThrow().values.get(1).value.get(4).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(1).value.get(4).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("reservation",top.statement.get(4).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(4).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(4).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Takeem",top.statement.get(4).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("tableNumber",top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("1",top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("capacity",top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(1).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(1).thecompareOp);
Assertions.assertEquals("6",top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(1).IDENTIFIER.isPresent());
Assertions.assertEquals("5",top.statement.get(4).insert.orElseThrow().values.get(0).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("8:00 PM",top.statement.get(4).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(4).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(4).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Francis",top.statement.get(4).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertEquals("tableNumber",top.statement.get(4).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(4).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("3",top.statement.get(4).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("capacity",top.statement.get(4).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(1).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(4).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(1).thecompareOp);
Assertions.assertEquals("4",top.statement.get(4).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(1).IDENTIFIER.isPresent());
Assertions.assertEquals("4",top.statement.get(4).insert.orElseThrow().values.get(1).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(1).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("10:00 PM",top.statement.get(4).insert.orElseThrow().values.get(1).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(1).value.get(3).name.isPresent());
Assertions.assertEquals("customer",top.statement.get(5).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(5).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Takeem",top.statement.get(5).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(5).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(5).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("789 Sutphin Blvd",top.statement.get(5).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(5).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(5).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertEquals("656-778-2245",top.statement.get(5).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(5).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(5).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Francis",top.statement.get(5).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(5).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(5).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("632 Foch Blvd",top.statement.get(5).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(5).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(5).insert.orElseThrow().values.get(1).value.get(2).NUMBER.isPresent());
Assertions.assertEquals("434-776-6654",top.statement.get(5).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(5).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertEquals("review",top.statement.get(6).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(6).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(6).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Takeem",top.statement.get(6).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(6).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(6).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Sarah’s Pizzeria",top.statement.get(6).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("4",top.statement.get(6).insert.orElseThrow().values.get(0).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("Tasted good",top.statement.get(6).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(6).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(6).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Francis",top.statement.get(6).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(6).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(6).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Viva Cinco De Mayo",top.statement.get(6).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("4",top.statement.get(6).insert.orElseThrow().values.get(1).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(1).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("Fast delivery and the food had a tasty spice to it!",top.statement.get(6).insert.orElseThrow().values.get(1).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(1).value.get(3).name.isPresent());
Assertions.assertEquals("orderItem",top.statement.get(7).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(7).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(7).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Pizza",top.statement.get(7).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("5",top.statement.get(7).insert.orElseThrow().values.get(0).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(7).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(7).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Tacos",top.statement.get(7).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("6",top.statement.get(7).insert.orElseThrow().values.get(1).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertEquals("diningTable",top.statement.get(8).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(8).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(8).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Sarah’s Pizzeria",top.statement.get(8).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("2",top.statement.get(8).insert.orElseThrow().values.get(0).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("4",top.statement.get(8).insert.orElseThrow().values.get(0).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(8).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(8).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Viva Cinco De Mayo",top.statement.get(8).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("3",top.statement.get(8).insert.orElseThrow().values.get(1).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertEquals("4",top.statement.get(8).insert.orElseThrow().values.get(1).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(2).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(2).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(2).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(8).insert.orElseThrow().values.get(2).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(8).insert.orElseThrow().values.get(2).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(2).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Sarah’s Pizzeria",top.statement.get(8).insert.orElseThrow().values.get(2).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(2).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("6",top.statement.get(8).insert.orElseThrow().values.get(2).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(2).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(2).value.get(1).name.isPresent());
Assertions.assertEquals("6",top.statement.get(8).insert.orElseThrow().values.get(2).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(2).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(2).value.get(2).name.isPresent());
Assertions.assertEquals("deliveryDriver",top.statement.get(9).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Elizabeth",top.statement.get(9).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Honda Civic",top.statement.get(9).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("5.0",top.statement.get(9).insert.orElseThrow().values.get(0).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Lijah",top.statement.get(9).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Nissan Versa",top.statement.get(9).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertEquals("4.8",top.statement.get(9).insert.orElseThrow().values.get(1).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertEquals("deliveryDriver",top.statement.get(10).delete.orElseThrow().name);
Assertions.assertFalse(top.statement.get(10).delete.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(10).delete.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(10).delete.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(10).delete.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(10).delete.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(10).delete.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Lijah",top.statement.get(10).delete.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(10).delete.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
        }
        }
