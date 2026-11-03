import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import AST.*;

public class Parser2Test3ParserTest {
    @Test
    public void TestParser2Test3Parser() throws Exception {
        var code =          "insert patient\n"+
         "	001, \"Urahara Kisuke\", \"(518) 699-9999\", \"hbuthShopkeeper@gmail.com\", \"7/23/1200\"\n"+
         "	002, \"Aizen Sosuke\", \"(518) 123-4444\", \"theRealHogyoku@gmail.com\", \"1/1/1000\"\n"+
         "\n"+
         "insert medicine\n"+
         "	100, \"ReiatsuIVPlus\", \"Injection twice a day every 12 hours for about 20 thousand years\"\n"+
         "\n"+
         "insert prescriptions\n"+
         "	findOne(medicineID=100), findOne(patientID=002), \"08/28/2026\", \"555 Division Road, Soul Society, New York\", 100.9\n"+
         "\n"+
         "insert doctors\n"+
         "	222, \"Dr. Mayuri Kurotsuchi\", 12, findOne(departmentNum=1212)\n"+
         "\n"+
         "insert department\n"+
         "	1212, findOne(staffID=222), \"The 12th Division\", []\n"+
         "\n"+
         "insert rooms\n"+
         "	12, \"6:35\", null\n"+
         "\n"+
         "insert appointments\n"+
         "	findOne(patientID=002), findOne(staffID=222), findOne(roomNum=12), \"6:30 PM\", \"8/28/26\", \"Feeling faint\"\n"+
         "\n"+
         "insert emergencyContact\n"+
         "	findOne(patientID=002), \"Gin Ichimaru\", \"(518) 123-4446\", other\n"+
         "\n"+
         "insert payroll\n"+
         "	findOne(staffID=222), 1000000.90, 0.00, 0.00\n"+
         "\n"+
         "insert insuranceInfo\n"+
         "	findOne(patientID=002), 000555666777, \"SeireiteiUnited\", 500000.00\n"+
         "\n"+
         "insert bill\n"+
         "	findOne(patientID=002), 10.00, 10.00, findOne(insuranceID=000555666777), cash\n"+
         "\n"+
         "delete rooms\n"+
         "    findOne(room=12)\n"+
"";
        var tokens = new Lexer(code).Lex();
        var p=new Parser(tokens);
        var top=p.sharQL().orElseThrow();
Assertions.assertEquals("patient",top.statement.get(0).insert.orElseThrow().name);
Assertions.assertEquals("001",top.statement.get(0).insert.orElseThrow().values.get(0).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Urahara Kisuke",top.statement.get(0).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertEquals("(518) 699-9999",top.statement.get(0).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("hbuthShopkeeper@gmail.com",top.statement.get(0).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(4).NUMBER.isPresent());
Assertions.assertEquals("7/23/1200",top.statement.get(0).insert.orElseThrow().values.get(0).value.get(4).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(0).value.get(4).name.isPresent());
Assertions.assertEquals("002",top.statement.get(0).insert.orElseThrow().values.get(1).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Aizen Sosuke",top.statement.get(0).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(1).value.get(2).NUMBER.isPresent());
Assertions.assertEquals("(518) 123-4444",top.statement.get(0).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(1).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("theRealHogyoku@gmail.com",top.statement.get(0).insert.orElseThrow().values.get(1).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(1).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(1).value.get(4).NUMBER.isPresent());
Assertions.assertEquals("1/1/1000",top.statement.get(0).insert.orElseThrow().values.get(1).value.get(4).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(0).insert.orElseThrow().values.get(1).value.get(4).name.isPresent());
Assertions.assertEquals("medicine",top.statement.get(1).insert.orElseThrow().name);
Assertions.assertEquals("100",top.statement.get(1).insert.orElseThrow().values.get(0).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("ReiatsuIVPlus",top.statement.get(1).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertEquals("Injection twice a day every 12 hours for about 20 thousand years",top.statement.get(1).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(1).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertEquals("prescriptions",top.statement.get(2).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("medicineID",top.statement.get(2).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(2).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("100",top.statement.get(2).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("patientID",top.statement.get(2).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(2).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("002",top.statement.get(2).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertEquals("08/28/2026",top.statement.get(2).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("555 Division Road, Soul Society, New York",top.statement.get(2).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertEquals("100.9",top.statement.get(2).insert.orElseThrow().values.get(0).value.get(4).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(4).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(2).insert.orElseThrow().values.get(0).value.get(4).name.isPresent());
Assertions.assertEquals("doctors",top.statement.get(3).insert.orElseThrow().name);
Assertions.assertEquals("222",top.statement.get(3).insert.orElseThrow().values.get(0).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Dr. Mayuri Kurotsuchi",top.statement.get(3).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("12",top.statement.get(3).insert.orElseThrow().values.get(0).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertEquals("departmentNum",top.statement.get(3).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(3).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("1212",top.statement.get(3).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(3).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("department",top.statement.get(4).insert.orElseThrow().name);
Assertions.assertEquals("1212",top.statement.get(4).insert.orElseThrow().values.get(0).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("staffID",top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("222",top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertEquals("The 12th Division",top.statement.get(4).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(4).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertEquals("rooms",top.statement.get(5).insert.orElseThrow().name);
Assertions.assertEquals("12",top.statement.get(5).insert.orElseThrow().values.get(0).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(5).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(5).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(5).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("6:35",top.statement.get(5).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(5).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(5).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(5).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertEquals("null",top.statement.get(5).insert.orElseThrow().values.get(0).value.get(2).name.orElseThrow());
Assertions.assertEquals("appointments",top.statement.get(6).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("patientID",top.statement.get(6).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(6).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("002",top.statement.get(6).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("staffID",top.statement.get(6).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(6).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("222",top.statement.get(6).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertEquals("roomNum",top.statement.get(6).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(6).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("12",top.statement.get(6).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("6:30 PM",top.statement.get(6).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(4).NUMBER.isPresent());
Assertions.assertEquals("8/28/26",top.statement.get(6).insert.orElseThrow().values.get(0).value.get(4).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(4).name.isPresent());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(5).NUMBER.isPresent());
Assertions.assertEquals("Feeling faint",top.statement.get(6).insert.orElseThrow().values.get(0).value.get(5).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(6).insert.orElseThrow().values.get(0).value.get(5).name.isPresent());
Assertions.assertEquals("emergencyContact",top.statement.get(7).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("patientID",top.statement.get(7).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(7).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("002",top.statement.get(7).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Gin Ichimaru",top.statement.get(7).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertEquals("(518) 123-4446",top.statement.get(7).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(7).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertEquals("other",top.statement.get(7).insert.orElseThrow().values.get(0).value.get(3).name.orElseThrow());
Assertions.assertEquals("payroll",top.statement.get(8).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("staffID",top.statement.get(8).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(8).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("222",top.statement.get(8).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("1000000.90",top.statement.get(8).insert.orElseThrow().values.get(0).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("0.00",top.statement.get(8).insert.orElseThrow().values.get(0).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertEquals("0.00",top.statement.get(8).insert.orElseThrow().values.get(0).value.get(3).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(8).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertEquals("insuranceInfo",top.statement.get(9).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("patientID",top.statement.get(9).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(9).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("002",top.statement.get(9).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("000555666777",top.statement.get(9).insert.orElseThrow().values.get(0).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertEquals("SeireiteiUnited",top.statement.get(9).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertEquals("500000.00",top.statement.get(9).insert.orElseThrow().values.get(0).value.get(3).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(9).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertEquals("bill",top.statement.get(10).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("patientID",top.statement.get(10).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(10).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("002",top.statement.get(10).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("10.00",top.statement.get(10).insert.orElseThrow().values.get(0).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("10.00",top.statement.get(10).insert.orElseThrow().values.get(0).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertEquals("insuranceID",top.statement.get(10).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(10).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("000555666777",top.statement.get(10).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(4).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(4).STRINGLITERAL.isPresent());
Assertions.assertEquals("cash",top.statement.get(10).insert.orElseThrow().values.get(0).value.get(4).name.orElseThrow());
Assertions.assertEquals("rooms",top.statement.get(11).delete.orElseThrow().name);
Assertions.assertFalse(top.statement.get(11).delete.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(11).delete.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(11).delete.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("room",top.statement.get(11).delete.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(11).delete.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("12",top.statement.get(11).delete.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(11).delete.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(11).delete.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
        }
        }
