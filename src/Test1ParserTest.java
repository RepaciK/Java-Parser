import java.util.LinkedList;
import java.util.*;
import AST.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test1ParserTest {
    @Test
    public void TestTest1Parser() throws Exception {
        var code =          "create table university\n"+
         "    string name\n"+
         "    string city\n"+
         "\n"+
         "create table college\n"+
         "    string name\n"+
         "    university underUniversity\n"+
         "\n"+
         "create table major\n"+
         "    string name\n"+
         "    college collegeUnder\n"+
         "    wholeNumber studentCount\n"+
         "    wholeNumber requiredCredits\n"+
         "\n"+
         "create table course\n"+
         "    string name\n"+
         "    wholeNumber credits\n"+
         "    college collegeUnder\n"+
         "    string building\n"+
         "\n"+
         "create table professor\n"+
         "    wholeNumber office\n"+
         "    string name\n"+
         "    college collegeUnder\n"+
         "    decimalNumber salary\n"+
         "    list<course> course\n"+
         "\n"+
         "create table advisor\n"+
         "    string name\n"+
         "    college collegeUnder\n"+
         "    decimalNumber salary\n"+
         "    enum(general, major) advisorType\n"+
         "\n"+
         "create table administration\n"+
         "    string name\n"+
         "    string position\n"+
         "    college collegeUnder\n"+
         "    decimalNumber salary\n"+
         "\n"+
         "create table dorm\n"+
         "    string quadName\n"+
         "    string hallName\n"+
         "    wholeNumber rooms\n"+
         "\n"+
         "create table mail\n"+
         "    wholeNumber lockerNumber\n"+
         "    string unlockCombo\n"+
         "\n"+
         "create table dining\n"+
         "    string name\n"+
         "    string location\n"+
         "    enum(retail, swipes) hallType\n"+
         "\n"+
         "create table student\n"+
         "    string name\n"+
         "    wholeNumber studentId\n"+
         "    enum(freshman, sophomore, junior, senior) classYear\n"+
         "    list<major> majors\n"+
         "    mail mailNumber\n"+
         "    wholeNumber earnedCredits\n"+
         "    advisor studentAdvisor\n"+
         "    dorm theirDorm\n"+
         "\n"+
         "\n"+
         "insert university\n"+
         "    \"SUNY Albany\", \"Albany\"\n"+
         "\n"+
         "insert college\n"+
         "    \"College of Arts and Sciences\", findOne (name=\"SUNY Albany\")\n"+
         "    \"College of Nanotechnology, Science, and Engineering\", findOne (name=\"SUNY Albany\")\n"+
         "    \"Rockefeller College of Public Affairs & Policy\", findOne (name=\"SUNY Albany\")\n"+
         "    \"Massry School of Business\", findOne (name=\"SUNY Albany\")\n"+
         "    \"College of Integrated Health Sciences\", findOne (name=\"SUNY Albany\")\n"+
         "    \"School of Criminal Justice\", findOne (name=\"SUNY Albany\")\n"+
         "    \"School of Education\", findOne (name=\"SUNY Albany\")\n"+
         "    \"College of Emergency Preparedness, Homeland Security & Cybersecurity\", findOne (name=\"SUNY Albany\")\n"+
         "\n"+
         "insert major\n"+
         "    \"Computer Science\", findOne (name = \"College of Nanotechnology, Science, and Engineering\"), 800, 92\n"+
         "    \"Philosophy\", findOne(name = \"College of Arts and Sciences\"), 100, 64\n"+
         "    \"Mechanical Engineering\", findOne (name=\"College of Nanotechnology, Science, and Engineering\"), 100, 82\n"+
         "\n"+
         "insert course\n"+
         "    \"ICSI311\", 4, findOne (name=\"College of Nanotechnology, Science, and Engineering\"), \"Social Science\"\n"+
         "    \"ICSI302\", 4, findOne (name=\"College of Nanotechnology, Science, and Engineering\"), \"Lecture Center\"\n"+
         "    \"APHI325\", 3, findOne (name=\"College of Arts and Sciences\"), \"Pine Bush\"\n"+
         "\n"+
         "insert professor\n"+
         "    440, \"Michael Phipps\", findOne(name=\"College of Nanotechnology, Science, and Engineering\"), 95000.00, [findOne(name=\"ICSI311\"), findOne(name=\"ICSI302\")]\n"+
         "    215, \"Ariel Zylberman\", findOne(name=\"College of Arts and Sciences\"), 82000.00, [findOne(name=\"APHI325\")]\n"+
         "\n"+
         "insert advisor\n"+
         "    \"Kathryn Fore\", findOne(name=\"College of Nanotechnology, Science, and Engineering\"), 99000.99, general\n"+
         "    \"Todd Schnitzer\", findOne(name=\"College of Nanotechnology, Science, and Engineering\"), 99000.99, major\n"+
         "\n"+
         "\n"+
         "insert administration\n"+
         "    \"Michele Grimm\", \"Dean\", findOne(name=\"College of Nanotechnology, Science, and Engineering\"), 120000.00\n"+
         "    \"Jeff Offutt\", \"Chair\", findOne(name=\"College of Nanotechnology, Science, and Engineering\"), 99000.00\n"+
         "\n"+
         "insert dorm\n"+
         "    \"State Quad\", \"Steinmetz Hall\", 200\n"+
         "    \"Dutch Quad\", \"Schuyler Hall\", 200\n"+
         "\n"+
         "insert mail\n"+
         "    101, \"34-12-88\"\n"+
         "    205, \"11-45-02\"\n"+
         "\n"+
         "insert dining\n"+
         "    \"Indigenous Dining Hall\", \"Indigenous Quad\", swipes\n"+
         "    \"District East\", \"Campus Center\", swipes\n"+
         "    \"Campus Center Food Court\", \"Campus Center\", retail\n"+
         "\n"+
         "insert student\n"+
         "    \"Madiha Fatima\", 1234567, sophomore, [findOne(name=\"Computer Science\"), findOne(name=\"Philosophy\")], findOne(lockerNumber=101), 67, findOne(name=\"Todd Schnitzer\"), findOne(hallName=\"Steinmetz Hall\")\n"+
         "    \"Potato Fry\", 7654321, junior, [findOne(name=\"Computer Science\")], findOne(lockerNumber=205), 55, findOne(name=\"Kathryn Fore\"), findOne(hallName=\"Schuyler Hall\")\n"+
         "\n"+
         "from student\n"+
         "    where studentId=1234567\n"+
         "    return name, classYear, studentAdvisor.name, studentAdvisor.advisorType, theirDorm.hallName, theirDorm.quadName\n"+
         "\n"+
         "from dining\n"+
         "    where hallType=swipes\n"+
         "    return name, location\n"+
         "\n"+
         "from professor\n"+
         "    where name=\"Michael Phipps\"\n"+
         "    return name, office, salary, collegeUnder.name, collegeUnder.underUniversity.name\n"+
         "\n"+
         "from student\n"+
         "    where name=\"Potato Fry\"\n"+
         "    return name, earnedCredits, mailNumber.lockerNumber, mailNumber.unlockCombo\n"+
"";
        var tokens = new Lexer(code).Lex();
        var p=new Parser(tokens);
        var top=p.sharQL().orElseThrow();
Assertions.assertEquals("university",top.statement.get(0).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(0).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(0).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(0).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("city",top.statement.get(0).create.orElseThrow().field.get(1).name);
Assertions.assertEquals("college",top.statement.get(1).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(1).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(1).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals("university",top.statement.get(1).create.orElseThrow().field.get(1).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(1).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("underUniversity",top.statement.get(1).create.orElseThrow().field.get(1).name);
Assertions.assertEquals("major",top.statement.get(2).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(2).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(2).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals("college",top.statement.get(2).create.orElseThrow().field.get(1).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(2).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("collegeUnder",top.statement.get(2).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(2).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("studentCount",top.statement.get(2).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(2).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("requiredCredits",top.statement.get(2).create.orElseThrow().field.get(3).name);
Assertions.assertEquals("course",top.statement.get(3).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(3).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(3).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(3).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("credits",top.statement.get(3).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals("college",top.statement.get(3).create.orElseThrow().field.get(2).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(3).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("collegeUnder",top.statement.get(3).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(3).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("building",top.statement.get(3).create.orElseThrow().field.get(3).name);
Assertions.assertEquals("professor",top.statement.get(4).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(4).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("office",top.statement.get(4).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(4).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(4).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals("college",top.statement.get(4).create.orElseThrow().field.get(2).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(4).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("collegeUnder",top.statement.get(4).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.decimalNumber,top.statement.get(4).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("salary",top.statement.get(4).create.orElseThrow().field.get(3).name);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(4).type.enum_);
Assertions.assertEquals(true,top.statement.get(4).create.orElseThrow().field.get(4).type.list);
Assertions.assertEquals("course",top.statement.get(4).create.orElseThrow().field.get(4).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(4).create.orElseThrow().field.get(4).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("course",top.statement.get(4).create.orElseThrow().field.get(4).name);
Assertions.assertEquals("advisor",top.statement.get(5).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(5).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(5).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals("college",top.statement.get(5).create.orElseThrow().field.get(1).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(5).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("collegeUnder",top.statement.get(5).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.decimalNumber,top.statement.get(5).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("salary",top.statement.get(5).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(true,top.statement.get(5).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals("general",top.statement.get(5).create.orElseThrow().field.get(3).type.enumValue.get(0));
Assertions.assertEquals("major",top.statement.get(5).create.orElseThrow().field.get(3).type.enumValue.get(1));
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(3).type.list);
Assertions.assertFalse(top.statement.get(5).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("advisorType",top.statement.get(5).create.orElseThrow().field.get(3).name);
Assertions.assertEquals("administration",top.statement.get(6).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(6).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(6).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(6).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("position",top.statement.get(6).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals("college",top.statement.get(6).create.orElseThrow().field.get(2).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(6).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("collegeUnder",top.statement.get(6).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.decimalNumber,top.statement.get(6).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("salary",top.statement.get(6).create.orElseThrow().field.get(3).name);
Assertions.assertEquals("dorm",top.statement.get(7).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(7).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("quadName",top.statement.get(7).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(7).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("hallName",top.statement.get(7).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(7).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("rooms",top.statement.get(7).create.orElseThrow().field.get(2).name);
Assertions.assertEquals("mail",top.statement.get(8).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(8).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("lockerNumber",top.statement.get(8).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(8).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("unlockCombo",top.statement.get(8).create.orElseThrow().field.get(1).name);
Assertions.assertEquals("dining",top.statement.get(9).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(9).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(9).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(9).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("location",top.statement.get(9).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(true,top.statement.get(9).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals("retail",top.statement.get(9).create.orElseThrow().field.get(2).type.enumValue.get(0));
Assertions.assertEquals("swipes",top.statement.get(9).create.orElseThrow().field.get(2).type.enumValue.get(1));
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(2).type.list);
Assertions.assertFalse(top.statement.get(9).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("hallType",top.statement.get(9).create.orElseThrow().field.get(2).name);
Assertions.assertEquals("student",top.statement.get(10).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(10).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(10).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(10).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(10).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(10).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(10).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(10).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("studentId",top.statement.get(10).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(true,top.statement.get(10).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals("freshman",top.statement.get(10).create.orElseThrow().field.get(2).type.enumValue.get(0));
Assertions.assertEquals("sophomore",top.statement.get(10).create.orElseThrow().field.get(2).type.enumValue.get(1));
Assertions.assertEquals("junior",top.statement.get(10).create.orElseThrow().field.get(2).type.enumValue.get(2));
Assertions.assertEquals("senior",top.statement.get(10).create.orElseThrow().field.get(2).type.enumValue.get(3));
Assertions.assertEquals(false,top.statement.get(10).create.orElseThrow().field.get(2).type.list);
Assertions.assertFalse(top.statement.get(10).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("classYear",top.statement.get(10).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(10).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(true,top.statement.get(10).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals("major",top.statement.get(10).create.orElseThrow().field.get(3).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(10).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("majors",top.statement.get(10).create.orElseThrow().field.get(3).name);
Assertions.assertEquals(false,top.statement.get(10).create.orElseThrow().field.get(4).type.enum_);
Assertions.assertEquals(false,top.statement.get(10).create.orElseThrow().field.get(4).type.list);
Assertions.assertEquals("mail",top.statement.get(10).create.orElseThrow().field.get(4).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(10).create.orElseThrow().field.get(4).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("mailNumber",top.statement.get(10).create.orElseThrow().field.get(4).name);
Assertions.assertEquals(false,top.statement.get(10).create.orElseThrow().field.get(5).type.enum_);
Assertions.assertEquals(false,top.statement.get(10).create.orElseThrow().field.get(5).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(10).create.orElseThrow().field.get(5).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("earnedCredits",top.statement.get(10).create.orElseThrow().field.get(5).name);
Assertions.assertEquals(false,top.statement.get(10).create.orElseThrow().field.get(6).type.enum_);
Assertions.assertEquals(false,top.statement.get(10).create.orElseThrow().field.get(6).type.list);
Assertions.assertEquals("advisor",top.statement.get(10).create.orElseThrow().field.get(6).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(10).create.orElseThrow().field.get(6).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("studentAdvisor",top.statement.get(10).create.orElseThrow().field.get(6).name);
Assertions.assertEquals(false,top.statement.get(10).create.orElseThrow().field.get(7).type.enum_);
Assertions.assertEquals(false,top.statement.get(10).create.orElseThrow().field.get(7).type.list);
Assertions.assertEquals("dorm",top.statement.get(10).create.orElseThrow().field.get(7).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(10).create.orElseThrow().field.get(7).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("theirDorm",top.statement.get(10).create.orElseThrow().field.get(7).name);
Assertions.assertEquals("university",top.statement.get(11).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("SUNY Albany",top.statement.get(11).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Albany",top.statement.get(11).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("college",top.statement.get(12).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("College of Arts and Sciences",top.statement.get(12).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(12).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(12).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("SUNY Albany",top.statement.get(12).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("College of Nanotechnology, Science, and Engineering",top.statement.get(12).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(12).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(12).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("SUNY Albany",top.statement.get(12).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(2).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Rockefeller College of Public Affairs & Policy",top.statement.get(12).insert.orElseThrow().values.get(2).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(2).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(2).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(2).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(2).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(12).insert.orElseThrow().values.get(2).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(12).insert.orElseThrow().values.get(2).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(2).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("SUNY Albany",top.statement.get(12).insert.orElseThrow().values.get(2).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(2).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(3).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Massry School of Business",top.statement.get(12).insert.orElseThrow().values.get(3).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(3).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(3).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(3).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(3).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(12).insert.orElseThrow().values.get(3).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(12).insert.orElseThrow().values.get(3).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(3).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("SUNY Albany",top.statement.get(12).insert.orElseThrow().values.get(3).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(3).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(4).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("College of Integrated Health Sciences",top.statement.get(12).insert.orElseThrow().values.get(4).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(4).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(4).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(4).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(4).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(12).insert.orElseThrow().values.get(4).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(12).insert.orElseThrow().values.get(4).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(4).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("SUNY Albany",top.statement.get(12).insert.orElseThrow().values.get(4).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(4).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(5).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("School of Criminal Justice",top.statement.get(12).insert.orElseThrow().values.get(5).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(5).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(5).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(5).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(5).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(12).insert.orElseThrow().values.get(5).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(12).insert.orElseThrow().values.get(5).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(5).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("SUNY Albany",top.statement.get(12).insert.orElseThrow().values.get(5).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(5).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(6).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("School of Education",top.statement.get(12).insert.orElseThrow().values.get(6).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(6).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(6).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(6).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(6).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(12).insert.orElseThrow().values.get(6).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(12).insert.orElseThrow().values.get(6).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(6).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("SUNY Albany",top.statement.get(12).insert.orElseThrow().values.get(6).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(6).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(7).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("College of Emergency Preparedness, Homeland Security & Cybersecurity",top.statement.get(12).insert.orElseThrow().values.get(7).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(7).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(7).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(7).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(7).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(12).insert.orElseThrow().values.get(7).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(12).insert.orElseThrow().values.get(7).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(7).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("SUNY Albany",top.statement.get(12).insert.orElseThrow().values.get(7).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(7).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("major",top.statement.get(13).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Computer Science",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("College of Nanotechnology, Science, and Engineering",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("800",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertEquals("92",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(3).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Philosophy",top.statement.get(13).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(13).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(13).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("College of Arts and Sciences",top.statement.get(13).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("100",top.statement.get(13).insert.orElseThrow().values.get(1).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertEquals("64",top.statement.get(13).insert.orElseThrow().values.get(1).value.get(3).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(2).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Mechanical Engineering",top.statement.get(13).insert.orElseThrow().values.get(2).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(2).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(2).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(2).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(2).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(13).insert.orElseThrow().values.get(2).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(13).insert.orElseThrow().values.get(2).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(2).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("College of Nanotechnology, Science, and Engineering",top.statement.get(13).insert.orElseThrow().values.get(2).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(2).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("100",top.statement.get(13).insert.orElseThrow().values.get(2).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(2).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(2).value.get(2).name.isPresent());
Assertions.assertEquals("82",top.statement.get(13).insert.orElseThrow().values.get(2).value.get(3).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(2).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(2).value.get(3).name.isPresent());
Assertions.assertEquals("course",top.statement.get(14).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("ICSI311",top.statement.get(14).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("4",top.statement.get(14).insert.orElseThrow().values.get(0).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertEquals("name",top.statement.get(14).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(14).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("College of Nanotechnology, Science, and Engineering",top.statement.get(14).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("Social Science",top.statement.get(14).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("ICSI302",top.statement.get(14).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertEquals("4",top.statement.get(14).insert.orElseThrow().values.get(1).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertEquals("name",top.statement.get(14).insert.orElseThrow().values.get(1).value.get(2).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(14).insert.orElseThrow().values.get(1).value.get(2).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(2).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("College of Nanotechnology, Science, and Engineering",top.statement.get(14).insert.orElseThrow().values.get(1).value.get(2).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(2).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("Lecture Center",top.statement.get(14).insert.orElseThrow().values.get(1).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(2).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("APHI325",top.statement.get(14).insert.orElseThrow().values.get(2).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(2).value.get(0).name.isPresent());
Assertions.assertEquals("3",top.statement.get(14).insert.orElseThrow().values.get(2).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(2).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(2).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(2).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(2).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(2).value.get(2).name.isPresent());
Assertions.assertEquals("name",top.statement.get(14).insert.orElseThrow().values.get(2).value.get(2).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(14).insert.orElseThrow().values.get(2).value.get(2).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(2).value.get(2).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("College of Arts and Sciences",top.statement.get(14).insert.orElseThrow().values.get(2).value.get(2).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(2).value.get(2).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(2).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("Pine Bush",top.statement.get(14).insert.orElseThrow().values.get(2).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(2).value.get(3).name.isPresent());
Assertions.assertEquals("professor",top.statement.get(15).insert.orElseThrow().name);
Assertions.assertEquals("440",top.statement.get(15).insert.orElseThrow().values.get(0).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Michael Phipps",top.statement.get(15).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertEquals("name",top.statement.get(15).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(15).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("College of Nanotechnology, Science, and Engineering",top.statement.get(15).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("95000.00",top.statement.get(15).insert.orElseThrow().values.get(0).value.get(3).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(4).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(4).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(4).name.isPresent());
Assertions.assertEquals("name",top.statement.get(15).insert.orElseThrow().values.get(0).value.get(4).valueList.orElseThrow().findOne.get(0).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(15).insert.orElseThrow().values.get(0).value.get(4).valueList.orElseThrow().findOne.get(0).compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(4).valueList.orElseThrow().findOne.get(0).compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("ICSI311",top.statement.get(15).insert.orElseThrow().values.get(0).value.get(4).valueList.orElseThrow().findOne.get(0).compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(4).valueList.orElseThrow().findOne.get(0).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("name",top.statement.get(15).insert.orElseThrow().values.get(0).value.get(4).valueList.orElseThrow().findOne.get(1).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(15).insert.orElseThrow().values.get(0).value.get(4).valueList.orElseThrow().findOne.get(1).compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(4).valueList.orElseThrow().findOne.get(1).compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("ICSI302",top.statement.get(15).insert.orElseThrow().values.get(0).value.get(4).valueList.orElseThrow().findOne.get(1).compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(4).valueList.orElseThrow().findOne.get(1).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("215",top.statement.get(15).insert.orElseThrow().values.get(1).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Ariel Zylberman",top.statement.get(15).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertEquals("name",top.statement.get(15).insert.orElseThrow().values.get(1).value.get(2).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(15).insert.orElseThrow().values.get(1).value.get(2).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(2).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("College of Arts and Sciences",top.statement.get(15).insert.orElseThrow().values.get(1).value.get(2).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(2).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("82000.00",top.statement.get(15).insert.orElseThrow().values.get(1).value.get(3).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(4).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(4).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(4).name.isPresent());
Assertions.assertEquals("name",top.statement.get(15).insert.orElseThrow().values.get(1).value.get(4).valueList.orElseThrow().findOne.get(0).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(15).insert.orElseThrow().values.get(1).value.get(4).valueList.orElseThrow().findOne.get(0).compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(4).valueList.orElseThrow().findOne.get(0).compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("APHI325",top.statement.get(15).insert.orElseThrow().values.get(1).value.get(4).valueList.orElseThrow().findOne.get(0).compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(4).valueList.orElseThrow().findOne.get(0).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("advisor",top.statement.get(16).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Kathryn Fore",top.statement.get(16).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(16).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(16).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("College of Nanotechnology, Science, and Engineering",top.statement.get(16).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("99000.99",top.statement.get(16).insert.orElseThrow().values.get(0).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertEquals("general",top.statement.get(16).insert.orElseThrow().values.get(0).value.get(3).name.orElseThrow());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Todd Schnitzer",top.statement.get(16).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(16).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(16).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("College of Nanotechnology, Science, and Engineering",top.statement.get(16).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("99000.99",top.statement.get(16).insert.orElseThrow().values.get(1).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertEquals("major",top.statement.get(16).insert.orElseThrow().values.get(1).value.get(3).name.orElseThrow());
Assertions.assertEquals("administration",top.statement.get(17).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Michele Grimm",top.statement.get(17).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Dean",top.statement.get(17).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertEquals("name",top.statement.get(17).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(17).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("College of Nanotechnology, Science, and Engineering",top.statement.get(17).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("120000.00",top.statement.get(17).insert.orElseThrow().values.get(0).value.get(3).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Jeff Offutt",top.statement.get(17).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Chair",top.statement.get(17).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(1).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertEquals("name",top.statement.get(17).insert.orElseThrow().values.get(1).value.get(2).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(17).insert.orElseThrow().values.get(1).value.get(2).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(1).value.get(2).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("College of Nanotechnology, Science, and Engineering",top.statement.get(17).insert.orElseThrow().values.get(1).value.get(2).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(1).value.get(2).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("99000.00",top.statement.get(17).insert.orElseThrow().values.get(1).value.get(3).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(1).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(1).value.get(3).name.isPresent());
Assertions.assertEquals("dorm",top.statement.get(18).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("State Quad",top.statement.get(18).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Steinmetz Hall",top.statement.get(18).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("200",top.statement.get(18).insert.orElseThrow().values.get(0).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Dutch Quad",top.statement.get(18).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Schuyler Hall",top.statement.get(18).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertEquals("200",top.statement.get(18).insert.orElseThrow().values.get(1).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertEquals("mail",top.statement.get(19).insert.orElseThrow().name);
Assertions.assertEquals("101",top.statement.get(19).insert.orElseThrow().values.get(0).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("34-12-88",top.statement.get(19).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("205",top.statement.get(19).insert.orElseThrow().values.get(1).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("11-45-02",top.statement.get(19).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertEquals("dining",top.statement.get(20).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Indigenous Dining Hall",top.statement.get(20).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Indigenous Quad",top.statement.get(20).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertEquals("swipes",top.statement.get(20).insert.orElseThrow().values.get(0).value.get(2).name.orElseThrow());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("District East",top.statement.get(20).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Campus Center",top.statement.get(20).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(1).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertEquals("swipes",top.statement.get(20).insert.orElseThrow().values.get(1).value.get(2).name.orElseThrow());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(2).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Campus Center Food Court",top.statement.get(20).insert.orElseThrow().values.get(2).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(2).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(2).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Campus Center",top.statement.get(20).insert.orElseThrow().values.get(2).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(2).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(2).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(2).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertEquals("retail",top.statement.get(20).insert.orElseThrow().values.get(2).value.get(2).name.orElseThrow());
Assertions.assertEquals("student",top.statement.get(21).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Madiha Fatima",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("1234567",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertEquals("sophomore",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(2).name.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertEquals("name",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).valueList.orElseThrow().findOne.get(0).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).valueList.orElseThrow().findOne.get(0).compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).valueList.orElseThrow().findOne.get(0).compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Computer Science",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).valueList.orElseThrow().findOne.get(0).compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).valueList.orElseThrow().findOne.get(0).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("name",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).valueList.orElseThrow().findOne.get(1).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).valueList.orElseThrow().findOne.get(1).compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).valueList.orElseThrow().findOne.get(1).compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Philosophy",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).valueList.orElseThrow().findOne.get(1).compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).valueList.orElseThrow().findOne.get(1).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(4).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(4).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(4).name.isPresent());
Assertions.assertEquals("lockerNumber",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(4).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(21).insert.orElseThrow().values.get(0).value.get(4).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("101",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(4).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(4).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(4).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("67",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(5).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(5).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(5).name.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(6).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(6).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(6).name.isPresent());
Assertions.assertEquals("name",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(6).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(21).insert.orElseThrow().values.get(0).value.get(6).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(6).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Todd Schnitzer",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(6).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(6).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(7).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(7).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(7).name.isPresent());
Assertions.assertEquals("hallName",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(7).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(21).insert.orElseThrow().values.get(0).value.get(7).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(7).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Steinmetz Hall",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(7).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(7).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Potato Fry",top.statement.get(21).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertEquals("7654321",top.statement.get(21).insert.orElseThrow().values.get(1).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertEquals("junior",top.statement.get(21).insert.orElseThrow().values.get(1).value.get(2).name.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(3).name.isPresent());
Assertions.assertEquals("name",top.statement.get(21).insert.orElseThrow().values.get(1).value.get(3).valueList.orElseThrow().findOne.get(0).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(21).insert.orElseThrow().values.get(1).value.get(3).valueList.orElseThrow().findOne.get(0).compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(3).valueList.orElseThrow().findOne.get(0).compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Computer Science",top.statement.get(21).insert.orElseThrow().values.get(1).value.get(3).valueList.orElseThrow().findOne.get(0).compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(3).valueList.orElseThrow().findOne.get(0).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(4).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(4).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(4).name.isPresent());
Assertions.assertEquals("lockerNumber",top.statement.get(21).insert.orElseThrow().values.get(1).value.get(4).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(21).insert.orElseThrow().values.get(1).value.get(4).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("205",top.statement.get(21).insert.orElseThrow().values.get(1).value.get(4).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(4).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(4).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("55",top.statement.get(21).insert.orElseThrow().values.get(1).value.get(5).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(5).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(5).name.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(6).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(6).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(6).name.isPresent());
Assertions.assertEquals("name",top.statement.get(21).insert.orElseThrow().values.get(1).value.get(6).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(21).insert.orElseThrow().values.get(1).value.get(6).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(6).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Kathryn Fore",top.statement.get(21).insert.orElseThrow().values.get(1).value.get(6).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(6).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(7).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(7).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(7).name.isPresent());
Assertions.assertEquals("hallName",top.statement.get(21).insert.orElseThrow().values.get(1).value.get(7).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(21).insert.orElseThrow().values.get(1).value.get(7).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(7).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Schuyler Hall",top.statement.get(21).insert.orElseThrow().values.get(1).value.get(7).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(1).value.get(7).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("student",top.statement.get(22).from.orElseThrow().name);
Assertions.assertEquals("studentId",top.statement.get(22).from.orElseThrow().where.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(22).from.orElseThrow().where.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("1234567",top.statement.get(22).from.orElseThrow().where.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(22).from.orElseThrow().where.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(22).from.orElseThrow().where.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("name",top.statement.get(22).from.orElseThrow().return_.orElseThrow().returnName.get(0).name);
Assertions.assertEquals("classYear",top.statement.get(22).from.orElseThrow().return_.orElseThrow().returnName.get(1).name);
Assertions.assertEquals("studentAdvisor",top.statement.get(22).from.orElseThrow().return_.orElseThrow().returnName.get(2).name);
Assertions.assertEquals("name",top.statement.get(22).from.orElseThrow().return_.orElseThrow().returnName.get(2).field.get(0));
Assertions.assertEquals("studentAdvisor",top.statement.get(22).from.orElseThrow().return_.orElseThrow().returnName.get(3).name);
Assertions.assertEquals("advisorType",top.statement.get(22).from.orElseThrow().return_.orElseThrow().returnName.get(3).field.get(0));
Assertions.assertEquals("theirDorm",top.statement.get(22).from.orElseThrow().return_.orElseThrow().returnName.get(4).name);
Assertions.assertEquals("hallName",top.statement.get(22).from.orElseThrow().return_.orElseThrow().returnName.get(4).field.get(0));
Assertions.assertEquals("theirDorm",top.statement.get(22).from.orElseThrow().return_.orElseThrow().returnName.get(5).name);
Assertions.assertEquals("quadName",top.statement.get(22).from.orElseThrow().return_.orElseThrow().returnName.get(5).field.get(0));
Assertions.assertEquals("dining",top.statement.get(23).from.orElseThrow().name);
Assertions.assertEquals("hallType",top.statement.get(23).from.orElseThrow().where.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(23).from.orElseThrow().where.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(23).from.orElseThrow().where.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(23).from.orElseThrow().where.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertEquals("swipes",top.statement.get(23).from.orElseThrow().where.orElseThrow().compare.get(0).IDENTIFIER.orElseThrow());
Assertions.assertEquals("name",top.statement.get(23).from.orElseThrow().return_.orElseThrow().returnName.get(0).name);
Assertions.assertEquals("location",top.statement.get(23).from.orElseThrow().return_.orElseThrow().returnName.get(1).name);
Assertions.assertEquals("professor",top.statement.get(24).from.orElseThrow().name);
Assertions.assertEquals("name",top.statement.get(24).from.orElseThrow().where.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(24).from.orElseThrow().where.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(24).from.orElseThrow().where.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Michael Phipps",top.statement.get(24).from.orElseThrow().where.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(24).from.orElseThrow().where.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("name",top.statement.get(24).from.orElseThrow().return_.orElseThrow().returnName.get(0).name);
Assertions.assertEquals("office",top.statement.get(24).from.orElseThrow().return_.orElseThrow().returnName.get(1).name);
Assertions.assertEquals("salary",top.statement.get(24).from.orElseThrow().return_.orElseThrow().returnName.get(2).name);
Assertions.assertEquals("collegeUnder",top.statement.get(24).from.orElseThrow().return_.orElseThrow().returnName.get(3).name);
Assertions.assertEquals("name",top.statement.get(24).from.orElseThrow().return_.orElseThrow().returnName.get(3).field.get(0));
Assertions.assertEquals("collegeUnder",top.statement.get(24).from.orElseThrow().return_.orElseThrow().returnName.get(4).name);
Assertions.assertEquals("underUniversity",top.statement.get(24).from.orElseThrow().return_.orElseThrow().returnName.get(4).field.get(0));
Assertions.assertEquals("name",top.statement.get(24).from.orElseThrow().return_.orElseThrow().returnName.get(4).field.get(1));
Assertions.assertEquals("student",top.statement.get(25).from.orElseThrow().name);
Assertions.assertEquals("name",top.statement.get(25).from.orElseThrow().where.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(25).from.orElseThrow().where.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(25).from.orElseThrow().where.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Potato Fry",top.statement.get(25).from.orElseThrow().where.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(25).from.orElseThrow().where.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("name",top.statement.get(25).from.orElseThrow().return_.orElseThrow().returnName.get(0).name);
Assertions.assertEquals("earnedCredits",top.statement.get(25).from.orElseThrow().return_.orElseThrow().returnName.get(1).name);
Assertions.assertEquals("mailNumber",top.statement.get(25).from.orElseThrow().return_.orElseThrow().returnName.get(2).name);
Assertions.assertEquals("lockerNumber",top.statement.get(25).from.orElseThrow().return_.orElseThrow().returnName.get(2).field.get(0));
Assertions.assertEquals("mailNumber",top.statement.get(25).from.orElseThrow().return_.orElseThrow().returnName.get(3).name);
Assertions.assertEquals("unlockCombo",top.statement.get(25).from.orElseThrow().return_.orElseThrow().returnName.get(3).field.get(0));
        }
        }
