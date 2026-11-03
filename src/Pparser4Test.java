import AST.insert;
import AST.delete;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Pparser4Test
{
    @Test
    public void testInsert() throws Exception
    {
        String code = "insert professor\n" +
                "    440, 999999.99, \"mphipps\"\n" +
                "    404, 1.00, \"offut\"\n";

        Lexer lexer = new Lexer(code);
        Parser parser = new Parser(lexer.Lex());

        insert result = parser.parseInsert().orElseThrow();

        Assertions.assertEquals("professor", result.name); //checks the table name
        Assertions.assertEquals(2, result.values.size()); //checks theres two rows
        Assertions.assertEquals(3, result.values.get(0).value.size()); //checks theres three values
    }

    @Test
    public void testDelete() throws Exception
    {
        String code = "delete professor\n" +
                "    findOne(name=\"offut\")\n";

        Lexer lexer = new Lexer(code);
        Parser parser = new Parser(lexer.Lex());

        delete result = parser.parseDelete().orElseThrow();

        Assertions.assertEquals("professor", result.name); //checks the table name
        Assertions.assertEquals(1, result.findOne.size()); //checks theres one findOne
        Assertions.assertEquals(1, result.findOne.get(0).compare.size()); //checks theres one comparison
    }


    @Test
    public void testDeleteMultiple() throws Exception
    {
        String code = "delete professor\n" +
                "    findOne(name=\"offut\")\n" +
                "    findOne(name=\"mphipps\")\n";

        Lexer lexer = new Lexer(code);
        Parser parser = new Parser(lexer.Lex());

        delete result = parser.parseDelete().orElseThrow();

        Assertions.assertEquals("professor", result.name);
        Assertions.assertEquals(2, result.findOne.size());
    }

    @Test
    public void testInsertEmptyList() throws Exception
    {
        String code = "insert course\n" +
                "    icsi, 213, \"Data Structures\", []\n";

        Lexer lexer = new Lexer(code);
        Parser parser = new Parser(lexer.Lex());

        insert result = parser.parseInsert().orElseThrow();

        Assertions.assertEquals("course", result.name);
        Assertions.assertEquals(1, result.values.size());
        Assertions.assertEquals(4, result.values.get(0).value.size());
        Assertions.assertEquals(0, result.values.get(0).value.get(3).valueList.orElseThrow().findOne.size());
    }

    @Test
    public void testInsertMultipleFindOnes() throws Exception
    {
        String code = "insert course\n" +
                "    icsi, 311, [findOne(major=icsi), findOne(courseNumber=213)]\n";

        Lexer lexer = new Lexer(code);
        Parser parser = new Parser(lexer.Lex());

        insert result = parser.parseInsert().orElseThrow();

        Assertions.assertEquals("course", result.name);
        Assertions.assertEquals(3, result.values.get(0).value.size());
        Assertions.assertEquals(2, result.values.get(0).value.get(2).valueList.orElseThrow().findOne.size());
    }

    @Test
    public void testInsertMissingTableName() throws Exception
    {
        String code = "insert\n" +
                "    440, \"Michael\"\n";

        Lexer lexer = new Lexer(code);
        Parser parser = new Parser(lexer.Lex());

        Assertions.assertThrows(SyntaxErrorException.class, () -> {
            parser.parseInsert();
        });
    }


    @Test
    public void testProfessorInsert() throws Exception
    {
        String code = "insert professor\n" +
                "    440, \"Michael Phipps\", findOne(name=\"College of Nanotechnology, Science, and Engineering\"), 95000.00, [findOne(name=\"ICSI311\"), findOne(name=\"ICSI302\")]\n" +
                "    215, \"Ariel Zylberman\", findOne(name=\"College of Arts and Sciences\"), 82000.00, [findOne(name=\"APHI325\")]\n";

        Lexer lexer = new Lexer(code);
        Parser parser = new Parser(lexer.Lex());

        insert result = parser.parseInsert().orElseThrow();

        Assertions.assertEquals("professor", result.name);
        Assertions.assertEquals(2, result.values.size());
        Assertions.assertEquals(5, result.values.get(0).value.size());
        Assertions.assertEquals(2, result.values.get(0).value.get(4).valueList.orElseThrow().findOne.size());
        Assertions.assertEquals(1, result.values.get(1).value.get(4).valueList.orElseThrow().findOne.size());
    }


}


/*public class Pparser4Test
{
    @Test
    public void testInsert() throws Exception
    {
        String code = "insert professor\n" +
                "    440, 999999.99, \"mphipps\"\n" +
                "    404, 1.00, \"offut\"\n";

        Lexer lexer = new Lexer(code);
        Parser parser = new Parser(lexer.Lex());

        insert result = parser.parseInsert().orElseThrow();

        Assertions.assertEquals("professor", result.name); //checks the table name
        Assertions.assertEquals(2, result.values.size()); //checks theres two rows
        Assertions.assertEquals(3, result.values.get(0).value.size()); //checks theres three values
    }
}*/
