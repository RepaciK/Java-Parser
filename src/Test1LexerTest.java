
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test1LexerTest {
    @Test
    public void TestTest1Lexer() throws Exception {
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
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(0).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(1).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(2).Type);
        Assertions.assertEquals("university",  tokens.get(2).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(3).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(4).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(5).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(6).Type);
        Assertions.assertEquals("name",  tokens.get(6).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(7).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(8).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(9).Type);
        Assertions.assertEquals("city",  tokens.get(9).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(10).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(11).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(12).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(13).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(14).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(15).Type);
        Assertions.assertEquals("college",  tokens.get(15).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(16).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(17).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(18).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(19).Type);
        Assertions.assertEquals("name",  tokens.get(19).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(20).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(21).Type);
        Assertions.assertEquals("university",  tokens.get(21).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(22).Type);
        Assertions.assertEquals("underUniversity",  tokens.get(22).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(23).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(24).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(25).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(26).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(27).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(28).Type);
        Assertions.assertEquals("major",  tokens.get(28).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(29).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(30).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(31).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(32).Type);
        Assertions.assertEquals("name",  tokens.get(32).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(33).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(34).Type);
        Assertions.assertEquals("college",  tokens.get(34).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(35).Type);
        Assertions.assertEquals("collegeUnder",  tokens.get(35).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(36).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(37).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(38).Type);
        Assertions.assertEquals("studentCount",  tokens.get(38).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(39).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(40).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(41).Type);
        Assertions.assertEquals("requiredCredits",  tokens.get(41).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(42).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(43).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(44).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(45).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(46).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(47).Type);
        Assertions.assertEquals("course",  tokens.get(47).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(48).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(49).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(50).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(51).Type);
        Assertions.assertEquals("name",  tokens.get(51).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(52).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(53).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(54).Type);
        Assertions.assertEquals("credits",  tokens.get(54).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(55).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(56).Type);
        Assertions.assertEquals("college",  tokens.get(56).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(57).Type);
        Assertions.assertEquals("collegeUnder",  tokens.get(57).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(58).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(59).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(60).Type);
        Assertions.assertEquals("building",  tokens.get(60).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(61).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(62).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(63).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(64).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(65).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(66).Type);
        Assertions.assertEquals("professor",  tokens.get(66).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(67).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(68).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(69).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(70).Type);
        Assertions.assertEquals("office",  tokens.get(70).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(71).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(72).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(73).Type);
        Assertions.assertEquals("name",  tokens.get(73).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(74).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(75).Type);
        Assertions.assertEquals("college",  tokens.get(75).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(76).Type);
        Assertions.assertEquals("collegeUnder",  tokens.get(76).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(77).Type);
        Assertions.assertEquals(Token.TokenTypes.DECIMALNUMBER, tokens.get(78).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(79).Type);
        Assertions.assertEquals("salary",  tokens.get(79).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(80).Type);
        Assertions.assertEquals(Token.TokenTypes.LIST, tokens.get(81).Type);
        Assertions.assertEquals(Token.TokenTypes.LESSTHAN, tokens.get(82).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(83).Type);
        Assertions.assertEquals("course",  tokens.get(83).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.GREATERTHAN, tokens.get(84).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(85).Type);
        Assertions.assertEquals("course",  tokens.get(85).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(86).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(87).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(88).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(89).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(90).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(91).Type);
        Assertions.assertEquals("advisor",  tokens.get(91).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(92).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(93).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(94).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(95).Type);
        Assertions.assertEquals("name",  tokens.get(95).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(96).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(97).Type);
        Assertions.assertEquals("college",  tokens.get(97).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(98).Type);
        Assertions.assertEquals("collegeUnder",  tokens.get(98).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(99).Type);
        Assertions.assertEquals(Token.TokenTypes.DECIMALNUMBER, tokens.get(100).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(101).Type);
        Assertions.assertEquals("salary",  tokens.get(101).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(102).Type);
        Assertions.assertEquals(Token.TokenTypes.ENUM, tokens.get(103).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(104).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(105).Type);
        Assertions.assertEquals("general",  tokens.get(105).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(106).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(107).Type);
        Assertions.assertEquals("major",  tokens.get(107).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(108).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(109).Type);
        Assertions.assertEquals("advisorType",  tokens.get(109).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(110).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(111).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(112).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(113).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(114).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(115).Type);
        Assertions.assertEquals("administration",  tokens.get(115).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(116).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(117).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(118).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(119).Type);
        Assertions.assertEquals("name",  tokens.get(119).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(120).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(121).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(122).Type);
        Assertions.assertEquals("position",  tokens.get(122).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(123).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(124).Type);
        Assertions.assertEquals("college",  tokens.get(124).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(125).Type);
        Assertions.assertEquals("collegeUnder",  tokens.get(125).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(126).Type);
        Assertions.assertEquals(Token.TokenTypes.DECIMALNUMBER, tokens.get(127).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(128).Type);
        Assertions.assertEquals("salary",  tokens.get(128).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(129).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(130).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(131).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(132).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(133).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(134).Type);
        Assertions.assertEquals("dorm",  tokens.get(134).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(135).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(136).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(137).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(138).Type);
        Assertions.assertEquals("quadName",  tokens.get(138).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(139).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(140).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(141).Type);
        Assertions.assertEquals("hallName",  tokens.get(141).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(142).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(143).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(144).Type);
        Assertions.assertEquals("rooms",  tokens.get(144).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(145).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(146).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(147).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(148).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(149).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(150).Type);
        Assertions.assertEquals("mail",  tokens.get(150).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(151).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(152).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(153).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(154).Type);
        Assertions.assertEquals("lockerNumber",  tokens.get(154).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(155).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(156).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(157).Type);
        Assertions.assertEquals("unlockCombo",  tokens.get(157).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(158).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(159).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(160).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(161).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(162).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(163).Type);
        Assertions.assertEquals("dining",  tokens.get(163).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(164).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(165).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(166).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(167).Type);
        Assertions.assertEquals("name",  tokens.get(167).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(168).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(169).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(170).Type);
        Assertions.assertEquals("location",  tokens.get(170).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(171).Type);
        Assertions.assertEquals(Token.TokenTypes.ENUM, tokens.get(172).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(173).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(174).Type);
        Assertions.assertEquals("retail",  tokens.get(174).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(175).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(176).Type);
        Assertions.assertEquals("swipes",  tokens.get(176).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(177).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(178).Type);
        Assertions.assertEquals("hallType",  tokens.get(178).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(179).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(180).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(181).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(182).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(183).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(184).Type);
        Assertions.assertEquals("student",  tokens.get(184).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(185).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(186).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(187).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(188).Type);
        Assertions.assertEquals("name",  tokens.get(188).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(189).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(190).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(191).Type);
        Assertions.assertEquals("studentId",  tokens.get(191).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(192).Type);
        Assertions.assertEquals(Token.TokenTypes.ENUM, tokens.get(193).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(194).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(195).Type);
        Assertions.assertEquals("freshman",  tokens.get(195).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(196).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(197).Type);
        Assertions.assertEquals("sophomore",  tokens.get(197).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(198).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(199).Type);
        Assertions.assertEquals("junior",  tokens.get(199).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(200).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(201).Type);
        Assertions.assertEquals("senior",  tokens.get(201).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(202).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(203).Type);
        Assertions.assertEquals("classYear",  tokens.get(203).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(204).Type);
        Assertions.assertEquals(Token.TokenTypes.LIST, tokens.get(205).Type);
        Assertions.assertEquals(Token.TokenTypes.LESSTHAN, tokens.get(206).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(207).Type);
        Assertions.assertEquals("major",  tokens.get(207).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.GREATERTHAN, tokens.get(208).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(209).Type);
        Assertions.assertEquals("majors",  tokens.get(209).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(210).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(211).Type);
        Assertions.assertEquals("mail",  tokens.get(211).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(212).Type);
        Assertions.assertEquals("mailNumber",  tokens.get(212).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(213).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(214).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(215).Type);
        Assertions.assertEquals("earnedCredits",  tokens.get(215).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(216).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(217).Type);
        Assertions.assertEquals("advisor",  tokens.get(217).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(218).Type);
        Assertions.assertEquals("studentAdvisor",  tokens.get(218).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(219).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(220).Type);
        Assertions.assertEquals("dorm",  tokens.get(220).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(221).Type);
        Assertions.assertEquals("theirDorm",  tokens.get(221).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(222).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(223).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(224).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(225).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(226).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(227).Type);
        Assertions.assertEquals("university",  tokens.get(227).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(228).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(229).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(230).Type);
        Assertions.assertEquals("SUNY Albany",  tokens.get(230).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(231).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(232).Type);
        Assertions.assertEquals("Albany",  tokens.get(232).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(233).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(234).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(235).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(236).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(237).Type);
        Assertions.assertEquals("college",  tokens.get(237).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(238).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(239).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(240).Type);
        Assertions.assertEquals("College of Arts and Sciences",  tokens.get(240).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(241).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(242).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(243).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(244).Type);
        Assertions.assertEquals("name",  tokens.get(244).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(245).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(246).Type);
        Assertions.assertEquals("SUNY Albany",  tokens.get(246).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(247).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(248).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(249).Type);
        Assertions.assertEquals("College of Nanotechnology, Science, and Engineering",  tokens.get(249).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(250).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(251).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(252).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(253).Type);
        Assertions.assertEquals("name",  tokens.get(253).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(254).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(255).Type);
        Assertions.assertEquals("SUNY Albany",  tokens.get(255).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(256).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(257).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(258).Type);
        Assertions.assertEquals("Rockefeller College of Public Affairs & Policy",  tokens.get(258).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(259).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(260).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(261).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(262).Type);
        Assertions.assertEquals("name",  tokens.get(262).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(263).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(264).Type);
        Assertions.assertEquals("SUNY Albany",  tokens.get(264).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(265).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(266).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(267).Type);
        Assertions.assertEquals("Massry School of Business",  tokens.get(267).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(268).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(269).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(270).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(271).Type);
        Assertions.assertEquals("name",  tokens.get(271).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(272).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(273).Type);
        Assertions.assertEquals("SUNY Albany",  tokens.get(273).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(274).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(275).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(276).Type);
        Assertions.assertEquals("College of Integrated Health Sciences",  tokens.get(276).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(277).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(278).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(279).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(280).Type);
        Assertions.assertEquals("name",  tokens.get(280).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(281).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(282).Type);
        Assertions.assertEquals("SUNY Albany",  tokens.get(282).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(283).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(284).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(285).Type);
        Assertions.assertEquals("School of Criminal Justice",  tokens.get(285).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(286).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(287).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(288).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(289).Type);
        Assertions.assertEquals("name",  tokens.get(289).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(290).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(291).Type);
        Assertions.assertEquals("SUNY Albany",  tokens.get(291).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(292).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(293).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(294).Type);
        Assertions.assertEquals("School of Education",  tokens.get(294).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(295).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(296).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(297).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(298).Type);
        Assertions.assertEquals("name",  tokens.get(298).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(299).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(300).Type);
        Assertions.assertEquals("SUNY Albany",  tokens.get(300).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(301).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(302).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(303).Type);
        Assertions.assertEquals("College of Emergency Preparedness, Homeland Security & Cybersecurity",  tokens.get(303).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(304).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(305).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(306).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(307).Type);
        Assertions.assertEquals("name",  tokens.get(307).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(308).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(309).Type);
        Assertions.assertEquals("SUNY Albany",  tokens.get(309).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(310).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(311).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(312).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(313).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(314).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(315).Type);
        Assertions.assertEquals("major",  tokens.get(315).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(316).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(317).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(318).Type);
        Assertions.assertEquals("Computer Science",  tokens.get(318).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(319).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(320).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(321).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(322).Type);
        Assertions.assertEquals("name",  tokens.get(322).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(323).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(324).Type);
        Assertions.assertEquals("College of Nanotechnology, Science, and Engineering",  tokens.get(324).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(325).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(326).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(327).Type);
        Assertions.assertEquals("800",  tokens.get(327).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(328).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(329).Type);
        Assertions.assertEquals("92",  tokens.get(329).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(330).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(331).Type);
        Assertions.assertEquals("Philosophy",  tokens.get(331).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(332).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(333).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(334).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(335).Type);
        Assertions.assertEquals("name",  tokens.get(335).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(336).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(337).Type);
        Assertions.assertEquals("College of Arts and Sciences",  tokens.get(337).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(338).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(339).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(340).Type);
        Assertions.assertEquals("100",  tokens.get(340).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(341).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(342).Type);
        Assertions.assertEquals("64",  tokens.get(342).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(343).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(344).Type);
        Assertions.assertEquals("Mechanical Engineering",  tokens.get(344).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(345).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(346).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(347).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(348).Type);
        Assertions.assertEquals("name",  tokens.get(348).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(349).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(350).Type);
        Assertions.assertEquals("College of Nanotechnology, Science, and Engineering",  tokens.get(350).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(351).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(352).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(353).Type);
        Assertions.assertEquals("100",  tokens.get(353).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(354).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(355).Type);
        Assertions.assertEquals("82",  tokens.get(355).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(356).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(357).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(358).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(359).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(360).Type);
        Assertions.assertEquals("course",  tokens.get(360).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(361).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(362).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(363).Type);
        Assertions.assertEquals("ICSI311",  tokens.get(363).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(364).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(365).Type);
        Assertions.assertEquals("4",  tokens.get(365).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(366).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(367).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(368).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(369).Type);
        Assertions.assertEquals("name",  tokens.get(369).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(370).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(371).Type);
        Assertions.assertEquals("College of Nanotechnology, Science, and Engineering",  tokens.get(371).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(372).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(373).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(374).Type);
        Assertions.assertEquals("Social Science",  tokens.get(374).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(375).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(376).Type);
        Assertions.assertEquals("ICSI302",  tokens.get(376).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(377).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(378).Type);
        Assertions.assertEquals("4",  tokens.get(378).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(379).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(380).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(381).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(382).Type);
        Assertions.assertEquals("name",  tokens.get(382).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(383).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(384).Type);
        Assertions.assertEquals("College of Nanotechnology, Science, and Engineering",  tokens.get(384).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(385).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(386).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(387).Type);
        Assertions.assertEquals("Lecture Center",  tokens.get(387).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(388).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(389).Type);
        Assertions.assertEquals("APHI325",  tokens.get(389).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(390).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(391).Type);
        Assertions.assertEquals("3",  tokens.get(391).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(392).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(393).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(394).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(395).Type);
        Assertions.assertEquals("name",  tokens.get(395).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(396).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(397).Type);
        Assertions.assertEquals("College of Arts and Sciences",  tokens.get(397).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(398).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(399).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(400).Type);
        Assertions.assertEquals("Pine Bush",  tokens.get(400).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(401).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(402).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(403).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(404).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(405).Type);
        Assertions.assertEquals("professor",  tokens.get(405).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(406).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(407).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(408).Type);
        Assertions.assertEquals("440",  tokens.get(408).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(409).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(410).Type);
        Assertions.assertEquals("Michael Phipps",  tokens.get(410).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(411).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(412).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(413).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(414).Type);
        Assertions.assertEquals("name",  tokens.get(414).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(415).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(416).Type);
        Assertions.assertEquals("College of Nanotechnology, Science, and Engineering",  tokens.get(416).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(417).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(418).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(419).Type);
        Assertions.assertEquals("95000.00",  tokens.get(419).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(420).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(421).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(422).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(423).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(424).Type);
        Assertions.assertEquals("name",  tokens.get(424).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(425).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(426).Type);
        Assertions.assertEquals("ICSI311",  tokens.get(426).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(427).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(428).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(429).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(430).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(431).Type);
        Assertions.assertEquals("name",  tokens.get(431).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(432).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(433).Type);
        Assertions.assertEquals("ICSI302",  tokens.get(433).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(434).Type);
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(435).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(436).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(437).Type);
        Assertions.assertEquals("215",  tokens.get(437).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(438).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(439).Type);
        Assertions.assertEquals("Ariel Zylberman",  tokens.get(439).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(440).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(441).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(442).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(443).Type);
        Assertions.assertEquals("name",  tokens.get(443).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(444).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(445).Type);
        Assertions.assertEquals("College of Arts and Sciences",  tokens.get(445).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(446).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(447).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(448).Type);
        Assertions.assertEquals("82000.00",  tokens.get(448).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(449).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(450).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(451).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(452).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(453).Type);
        Assertions.assertEquals("name",  tokens.get(453).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(454).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(455).Type);
        Assertions.assertEquals("APHI325",  tokens.get(455).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(456).Type);
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(457).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(458).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(459).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(460).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(461).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(462).Type);
        Assertions.assertEquals("advisor",  tokens.get(462).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(463).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(464).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(465).Type);
        Assertions.assertEquals("Kathryn Fore",  tokens.get(465).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(466).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(467).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(468).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(469).Type);
        Assertions.assertEquals("name",  tokens.get(469).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(470).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(471).Type);
        Assertions.assertEquals("College of Nanotechnology, Science, and Engineering",  tokens.get(471).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(472).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(473).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(474).Type);
        Assertions.assertEquals("99000.99",  tokens.get(474).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(475).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(476).Type);
        Assertions.assertEquals("general",  tokens.get(476).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(477).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(478).Type);
        Assertions.assertEquals("Todd Schnitzer",  tokens.get(478).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(479).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(480).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(481).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(482).Type);
        Assertions.assertEquals("name",  tokens.get(482).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(483).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(484).Type);
        Assertions.assertEquals("College of Nanotechnology, Science, and Engineering",  tokens.get(484).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(485).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(486).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(487).Type);
        Assertions.assertEquals("99000.99",  tokens.get(487).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(488).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(489).Type);
        Assertions.assertEquals("major",  tokens.get(489).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(490).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(491).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(492).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(493).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(494).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(495).Type);
        Assertions.assertEquals("administration",  tokens.get(495).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(496).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(497).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(498).Type);
        Assertions.assertEquals("Michele Grimm",  tokens.get(498).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(499).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(500).Type);
        Assertions.assertEquals("Dean",  tokens.get(500).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(501).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(502).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(503).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(504).Type);
        Assertions.assertEquals("name",  tokens.get(504).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(505).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(506).Type);
        Assertions.assertEquals("College of Nanotechnology, Science, and Engineering",  tokens.get(506).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(507).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(508).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(509).Type);
        Assertions.assertEquals("120000.00",  tokens.get(509).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(510).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(511).Type);
        Assertions.assertEquals("Jeff Offutt",  tokens.get(511).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(512).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(513).Type);
        Assertions.assertEquals("Chair",  tokens.get(513).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(514).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(515).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(516).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(517).Type);
        Assertions.assertEquals("name",  tokens.get(517).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(518).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(519).Type);
        Assertions.assertEquals("College of Nanotechnology, Science, and Engineering",  tokens.get(519).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(520).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(521).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(522).Type);
        Assertions.assertEquals("99000.00",  tokens.get(522).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(523).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(524).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(525).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(526).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(527).Type);
        Assertions.assertEquals("dorm",  tokens.get(527).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(528).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(529).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(530).Type);
        Assertions.assertEquals("State Quad",  tokens.get(530).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(531).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(532).Type);
        Assertions.assertEquals("Steinmetz Hall",  tokens.get(532).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(533).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(534).Type);
        Assertions.assertEquals("200",  tokens.get(534).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(535).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(536).Type);
        Assertions.assertEquals("Dutch Quad",  tokens.get(536).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(537).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(538).Type);
        Assertions.assertEquals("Schuyler Hall",  tokens.get(538).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(539).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(540).Type);
        Assertions.assertEquals("200",  tokens.get(540).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(541).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(542).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(543).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(544).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(545).Type);
        Assertions.assertEquals("mail",  tokens.get(545).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(546).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(547).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(548).Type);
        Assertions.assertEquals("101",  tokens.get(548).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(549).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(550).Type);
        Assertions.assertEquals("34-12-88",  tokens.get(550).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(551).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(552).Type);
        Assertions.assertEquals("205",  tokens.get(552).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(553).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(554).Type);
        Assertions.assertEquals("11-45-02",  tokens.get(554).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(555).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(556).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(557).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(558).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(559).Type);
        Assertions.assertEquals("dining",  tokens.get(559).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(560).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(561).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(562).Type);
        Assertions.assertEquals("Indigenous Dining Hall",  tokens.get(562).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(563).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(564).Type);
        Assertions.assertEquals("Indigenous Quad",  tokens.get(564).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(565).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(566).Type);
        Assertions.assertEquals("swipes",  tokens.get(566).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(567).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(568).Type);
        Assertions.assertEquals("District East",  tokens.get(568).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(569).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(570).Type);
        Assertions.assertEquals("Campus Center",  tokens.get(570).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(571).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(572).Type);
        Assertions.assertEquals("swipes",  tokens.get(572).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(573).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(574).Type);
        Assertions.assertEquals("Campus Center Food Court",  tokens.get(574).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(575).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(576).Type);
        Assertions.assertEquals("Campus Center",  tokens.get(576).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(577).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(578).Type);
        Assertions.assertEquals("retail",  tokens.get(578).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(579).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(580).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(581).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(582).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(583).Type);
        Assertions.assertEquals("student",  tokens.get(583).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(584).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(585).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(586).Type);
        Assertions.assertEquals("Madiha Fatima",  tokens.get(586).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(587).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(588).Type);
        Assertions.assertEquals("1234567",  tokens.get(588).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(589).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(590).Type);
        Assertions.assertEquals("sophomore",  tokens.get(590).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(591).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(592).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(593).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(594).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(595).Type);
        Assertions.assertEquals("name",  tokens.get(595).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(596).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(597).Type);
        Assertions.assertEquals("Computer Science",  tokens.get(597).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(598).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(599).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(600).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(601).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(602).Type);
        Assertions.assertEquals("name",  tokens.get(602).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(603).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(604).Type);
        Assertions.assertEquals("Philosophy",  tokens.get(604).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(605).Type);
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(606).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(607).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(608).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(609).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(610).Type);
        Assertions.assertEquals("lockerNumber",  tokens.get(610).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(611).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(612).Type);
        Assertions.assertEquals("101",  tokens.get(612).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(613).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(614).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(615).Type);
        Assertions.assertEquals("67",  tokens.get(615).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(616).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(617).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(618).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(619).Type);
        Assertions.assertEquals("name",  tokens.get(619).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(620).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(621).Type);
        Assertions.assertEquals("Todd Schnitzer",  tokens.get(621).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(622).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(623).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(624).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(625).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(626).Type);
        Assertions.assertEquals("hallName",  tokens.get(626).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(627).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(628).Type);
        Assertions.assertEquals("Steinmetz Hall",  tokens.get(628).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(629).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(630).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(631).Type);
        Assertions.assertEquals("Potato Fry",  tokens.get(631).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(632).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(633).Type);
        Assertions.assertEquals("7654321",  tokens.get(633).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(634).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(635).Type);
        Assertions.assertEquals("junior",  tokens.get(635).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(636).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(637).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(638).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(639).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(640).Type);
        Assertions.assertEquals("name",  tokens.get(640).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(641).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(642).Type);
        Assertions.assertEquals("Computer Science",  tokens.get(642).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(643).Type);
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(644).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(645).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(646).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(647).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(648).Type);
        Assertions.assertEquals("lockerNumber",  tokens.get(648).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(649).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(650).Type);
        Assertions.assertEquals("205",  tokens.get(650).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(651).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(652).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(653).Type);
        Assertions.assertEquals("55",  tokens.get(653).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(654).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(655).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(656).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(657).Type);
        Assertions.assertEquals("name",  tokens.get(657).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(658).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(659).Type);
        Assertions.assertEquals("Kathryn Fore",  tokens.get(659).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(660).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(661).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(662).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(663).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(664).Type);
        Assertions.assertEquals("hallName",  tokens.get(664).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(665).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(666).Type);
        Assertions.assertEquals("Schuyler Hall",  tokens.get(666).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(667).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(668).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(669).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(670).Type);
        Assertions.assertEquals(Token.TokenTypes.FROM, tokens.get(671).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(672).Type);
        Assertions.assertEquals("student",  tokens.get(672).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(673).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(674).Type);
        Assertions.assertEquals(Token.TokenTypes.WHERE, tokens.get(675).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(676).Type);
        Assertions.assertEquals("studentId",  tokens.get(676).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(677).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(678).Type);
        Assertions.assertEquals("1234567",  tokens.get(678).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(679).Type);
        Assertions.assertEquals(Token.TokenTypes.RETURN, tokens.get(680).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(681).Type);
        Assertions.assertEquals("name",  tokens.get(681).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(682).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(683).Type);
        Assertions.assertEquals("classYear",  tokens.get(683).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(684).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(685).Type);
        Assertions.assertEquals("studentAdvisor",  tokens.get(685).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(686).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(687).Type);
        Assertions.assertEquals("name",  tokens.get(687).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(688).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(689).Type);
        Assertions.assertEquals("studentAdvisor",  tokens.get(689).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(690).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(691).Type);
        Assertions.assertEquals("advisorType",  tokens.get(691).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(692).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(693).Type);
        Assertions.assertEquals("theirDorm",  tokens.get(693).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(694).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(695).Type);
        Assertions.assertEquals("hallName",  tokens.get(695).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(696).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(697).Type);
        Assertions.assertEquals("theirDorm",  tokens.get(697).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(698).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(699).Type);
        Assertions.assertEquals("quadName",  tokens.get(699).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(700).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(701).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(702).Type);
        Assertions.assertEquals(Token.TokenTypes.FROM, tokens.get(703).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(704).Type);
        Assertions.assertEquals("dining",  tokens.get(704).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(705).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(706).Type);
        Assertions.assertEquals(Token.TokenTypes.WHERE, tokens.get(707).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(708).Type);
        Assertions.assertEquals("hallType",  tokens.get(708).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(709).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(710).Type);
        Assertions.assertEquals("swipes",  tokens.get(710).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(711).Type);
        Assertions.assertEquals(Token.TokenTypes.RETURN, tokens.get(712).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(713).Type);
        Assertions.assertEquals("name",  tokens.get(713).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(714).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(715).Type);
        Assertions.assertEquals("location",  tokens.get(715).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(716).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(717).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(718).Type);
        Assertions.assertEquals(Token.TokenTypes.FROM, tokens.get(719).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(720).Type);
        Assertions.assertEquals("professor",  tokens.get(720).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(721).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(722).Type);
        Assertions.assertEquals(Token.TokenTypes.WHERE, tokens.get(723).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(724).Type);
        Assertions.assertEquals("name",  tokens.get(724).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(725).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(726).Type);
        Assertions.assertEquals("Michael Phipps",  tokens.get(726).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(727).Type);
        Assertions.assertEquals(Token.TokenTypes.RETURN, tokens.get(728).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(729).Type);
        Assertions.assertEquals("name",  tokens.get(729).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(730).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(731).Type);
        Assertions.assertEquals("office",  tokens.get(731).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(732).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(733).Type);
        Assertions.assertEquals("salary",  tokens.get(733).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(734).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(735).Type);
        Assertions.assertEquals("collegeUnder",  tokens.get(735).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(736).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(737).Type);
        Assertions.assertEquals("name",  tokens.get(737).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(738).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(739).Type);
        Assertions.assertEquals("collegeUnder",  tokens.get(739).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(740).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(741).Type);
        Assertions.assertEquals("underUniversity",  tokens.get(741).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(742).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(743).Type);
        Assertions.assertEquals("name",  tokens.get(743).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(744).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(745).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(746).Type);
        Assertions.assertEquals(Token.TokenTypes.FROM, tokens.get(747).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(748).Type);
        Assertions.assertEquals("student",  tokens.get(748).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(749).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(750).Type);
        Assertions.assertEquals(Token.TokenTypes.WHERE, tokens.get(751).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(752).Type);
        Assertions.assertEquals("name",  tokens.get(752).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(753).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(754).Type);
        Assertions.assertEquals("Potato Fry",  tokens.get(754).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(755).Type);
        Assertions.assertEquals(Token.TokenTypes.RETURN, tokens.get(756).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(757).Type);
        Assertions.assertEquals("name",  tokens.get(757).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(758).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(759).Type);
        Assertions.assertEquals("earnedCredits",  tokens.get(759).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(760).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(761).Type);
        Assertions.assertEquals("mailNumber",  tokens.get(761).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(762).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(763).Type);
        Assertions.assertEquals("lockerNumber",  tokens.get(763).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(764).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(765).Type);
        Assertions.assertEquals("mailNumber",  tokens.get(765).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(766).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(767).Type);
        Assertions.assertEquals("unlockCombo",  tokens.get(767).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(768).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(769).Type);
    }
}
