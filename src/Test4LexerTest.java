import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test4LexerTest {
    @Test
    public void TestTest4Lexer() throws Exception {
        var code =          "create table Departments\n"+
         "    wholeNumber departmentID\n"+
         "    string name\n"+
         "\n"+
         "create table Buildings\n"+
         "    wholeNumber buildingID\n"+
         "    string name\n"+
         "    string address\n"+
         "    Departments department\n"+
         "    list < Rooms > rooms\n"+
         "\n"+
         "create table Rooms\n"+
         "    wholeNumber roomID\n"+
         "    Buildings building\n"+
         "    wholeNumber roomNumber\n"+
         "\n"+
         "create table Professors\n"+
         "    wholeNumber professorID\n"+
         "    string name\n"+
         "    Departments department\n"+
         "    Rooms office\n"+
         "    list < Courses > courses\n"+
         "\n"+
         "create table Majors\n"+
         "    wholeNumber majorID\n"+
         "    string name\n"+
         "    Departments department\n"+
         "    string abbreviation\n"+
         "\n"+
         "create table Courses\n"+
         "    wholeNumber courseID\n"+
         "    string name\n"+
         "    string description\n"+
         "    wholeNumber courseNumber\n"+
         "    list < Majors > majors\n"+
         "    enum (spring, summer, fall, winter) term\n"+
         "    Professors professor\n"+
         "    wholeNumber credits\n"+
         "\n"+
         "create table Students\n"+
         "    wholeNumber studentID\n"+
         "    string name\n"+
         "    enum (freshman, sophomore, junior, senior) year\n"+
         "    list < Majors > majors\n"+
         "    list < Majors > minors\n"+
         "    list < Courses > courses\n"+
         "    Rooms dormRoom\n"+
         "    ParkingPermits parkingPermit\n"+
         "\n"+
         "create table Vehicles\n"+
         "    wholeNumber vehicleID\n"+
         "    string model\n"+
         "    string color\n"+
         "    string licensePlate\n"+
         "\n"+
         "create table ParkingLots\n"+
         "    wholeNumber lotNumber\n"+
         "    enum (resident, commuter) type\n"+
         "    string address\n"+
         "    wholeNumber numberOfSpots\n"+
         "\n"+
         "create table ParkingPermits\n"+
         "    wholeNumber permitNumber\n"+
         "    list < Vehicles > vehicles\n"+
         "    list < ParkingLots > authorizedParkingLots\n"+
         "\n"+
         "insert Departments\n"+
         "    1, \"Computer Science\"\n"+
         "\n"+
         "insert Rooms\n"+
         "    1, findOne (name=\"University Administration Building\"), 440\n"+
         "\n"+
         "insert Buildings\n"+
         "    1, \"University Administration Building\", \"University Administration Bldg, Albany, NY 12203\", \"Computer Science\", [findOne (building=1)]\n"+
         "\n"+
         "from Buildings\n"+
         "    where name=\"University Administration Building\"\n"+
         "\n"+
         "from Vehicles\n"+
         "    where model=\"RAM\"\n"+
         "    return Vehicles.color\n"+
"";
        var tokens = new Lexer(code).Lex();
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(0).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(1).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(2).Type);
        Assertions.assertEquals("Departments",  tokens.get(2).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(3).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(4).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(5).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(6).Type);
        Assertions.assertEquals("departmentID",  tokens.get(6).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(7).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(8).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(9).Type);
        Assertions.assertEquals("name",  tokens.get(9).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(10).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(11).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(12).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(13).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(14).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(15).Type);
        Assertions.assertEquals("Buildings",  tokens.get(15).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(16).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(17).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(18).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(19).Type);
        Assertions.assertEquals("buildingID",  tokens.get(19).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(20).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(21).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(22).Type);
        Assertions.assertEquals("name",  tokens.get(22).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(23).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(24).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(25).Type);
        Assertions.assertEquals("address",  tokens.get(25).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(26).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(27).Type);
        Assertions.assertEquals("Departments",  tokens.get(27).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(28).Type);
        Assertions.assertEquals("department",  tokens.get(28).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(29).Type);
        Assertions.assertEquals(Token.TokenTypes.LIST, tokens.get(30).Type);
        Assertions.assertEquals(Token.TokenTypes.LESSTHAN, tokens.get(31).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(32).Type);
        Assertions.assertEquals("Rooms",  tokens.get(32).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.GREATERTHAN, tokens.get(33).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(34).Type);
        Assertions.assertEquals("rooms",  tokens.get(34).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(35).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(36).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(37).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(38).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(39).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(40).Type);
        Assertions.assertEquals("Rooms",  tokens.get(40).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(41).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(42).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(43).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(44).Type);
        Assertions.assertEquals("roomID",  tokens.get(44).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(45).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(46).Type);
        Assertions.assertEquals("Buildings",  tokens.get(46).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(47).Type);
        Assertions.assertEquals("building",  tokens.get(47).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(48).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(49).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(50).Type);
        Assertions.assertEquals("roomNumber",  tokens.get(50).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(51).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(52).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(53).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(54).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(55).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(56).Type);
        Assertions.assertEquals("Professors",  tokens.get(56).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(57).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(58).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(59).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(60).Type);
        Assertions.assertEquals("professorID",  tokens.get(60).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(61).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(62).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(63).Type);
        Assertions.assertEquals("name",  tokens.get(63).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(64).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(65).Type);
        Assertions.assertEquals("Departments",  tokens.get(65).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(66).Type);
        Assertions.assertEquals("department",  tokens.get(66).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(67).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(68).Type);
        Assertions.assertEquals("Rooms",  tokens.get(68).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(69).Type);
        Assertions.assertEquals("office",  tokens.get(69).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(70).Type);
        Assertions.assertEquals(Token.TokenTypes.LIST, tokens.get(71).Type);
        Assertions.assertEquals(Token.TokenTypes.LESSTHAN, tokens.get(72).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(73).Type);
        Assertions.assertEquals("Courses",  tokens.get(73).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.GREATERTHAN, tokens.get(74).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(75).Type);
        Assertions.assertEquals("courses",  tokens.get(75).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(76).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(77).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(78).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(79).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(80).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(81).Type);
        Assertions.assertEquals("Majors",  tokens.get(81).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(82).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(83).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(84).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(85).Type);
        Assertions.assertEquals("majorID",  tokens.get(85).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(86).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(87).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(88).Type);
        Assertions.assertEquals("name",  tokens.get(88).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(89).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(90).Type);
        Assertions.assertEquals("Departments",  tokens.get(90).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(91).Type);
        Assertions.assertEquals("department",  tokens.get(91).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(92).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(93).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(94).Type);
        Assertions.assertEquals("abbreviation",  tokens.get(94).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(95).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(96).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(97).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(98).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(99).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(100).Type);
        Assertions.assertEquals("Courses",  tokens.get(100).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(101).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(102).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(103).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(104).Type);
        Assertions.assertEquals("courseID",  tokens.get(104).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(105).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(106).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(107).Type);
        Assertions.assertEquals("name",  tokens.get(107).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(108).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(109).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(110).Type);
        Assertions.assertEquals("description",  tokens.get(110).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(111).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(112).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(113).Type);
        Assertions.assertEquals("courseNumber",  tokens.get(113).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(114).Type);
        Assertions.assertEquals(Token.TokenTypes.LIST, tokens.get(115).Type);
        Assertions.assertEquals(Token.TokenTypes.LESSTHAN, tokens.get(116).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(117).Type);
        Assertions.assertEquals("Majors",  tokens.get(117).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.GREATERTHAN, tokens.get(118).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(119).Type);
        Assertions.assertEquals("majors",  tokens.get(119).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(120).Type);
        Assertions.assertEquals(Token.TokenTypes.ENUM, tokens.get(121).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(122).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(123).Type);
        Assertions.assertEquals("spring",  tokens.get(123).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(124).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(125).Type);
        Assertions.assertEquals("summer",  tokens.get(125).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(126).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(127).Type);
        Assertions.assertEquals("fall",  tokens.get(127).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(128).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(129).Type);
        Assertions.assertEquals("winter",  tokens.get(129).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(130).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(131).Type);
        Assertions.assertEquals("term",  tokens.get(131).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(132).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(133).Type);
        Assertions.assertEquals("Professors",  tokens.get(133).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(134).Type);
        Assertions.assertEquals("professor",  tokens.get(134).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(135).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(136).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(137).Type);
        Assertions.assertEquals("credits",  tokens.get(137).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(138).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(139).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(140).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(141).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(142).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(143).Type);
        Assertions.assertEquals("Students",  tokens.get(143).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(144).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(145).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(146).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(147).Type);
        Assertions.assertEquals("studentID",  tokens.get(147).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(148).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(149).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(150).Type);
        Assertions.assertEquals("name",  tokens.get(150).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(151).Type);
        Assertions.assertEquals(Token.TokenTypes.ENUM, tokens.get(152).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(153).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(154).Type);
        Assertions.assertEquals("freshman",  tokens.get(154).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(155).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(156).Type);
        Assertions.assertEquals("sophomore",  tokens.get(156).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(157).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(158).Type);
        Assertions.assertEquals("junior",  tokens.get(158).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(159).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(160).Type);
        Assertions.assertEquals("senior",  tokens.get(160).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(161).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(162).Type);
        Assertions.assertEquals("year",  tokens.get(162).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(163).Type);
        Assertions.assertEquals(Token.TokenTypes.LIST, tokens.get(164).Type);
        Assertions.assertEquals(Token.TokenTypes.LESSTHAN, tokens.get(165).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(166).Type);
        Assertions.assertEquals("Majors",  tokens.get(166).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.GREATERTHAN, tokens.get(167).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(168).Type);
        Assertions.assertEquals("majors",  tokens.get(168).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(169).Type);
        Assertions.assertEquals(Token.TokenTypes.LIST, tokens.get(170).Type);
        Assertions.assertEquals(Token.TokenTypes.LESSTHAN, tokens.get(171).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(172).Type);
        Assertions.assertEquals("Majors",  tokens.get(172).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.GREATERTHAN, tokens.get(173).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(174).Type);
        Assertions.assertEquals("minors",  tokens.get(174).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(175).Type);
        Assertions.assertEquals(Token.TokenTypes.LIST, tokens.get(176).Type);
        Assertions.assertEquals(Token.TokenTypes.LESSTHAN, tokens.get(177).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(178).Type);
        Assertions.assertEquals("Courses",  tokens.get(178).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.GREATERTHAN, tokens.get(179).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(180).Type);
        Assertions.assertEquals("courses",  tokens.get(180).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(181).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(182).Type);
        Assertions.assertEquals("Rooms",  tokens.get(182).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(183).Type);
        Assertions.assertEquals("dormRoom",  tokens.get(183).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(184).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(185).Type);
        Assertions.assertEquals("ParkingPermits",  tokens.get(185).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(186).Type);
        Assertions.assertEquals("parkingPermit",  tokens.get(186).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(187).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(188).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(189).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(190).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(191).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(192).Type);
        Assertions.assertEquals("Vehicles",  tokens.get(192).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(193).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(194).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(195).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(196).Type);
        Assertions.assertEquals("vehicleID",  tokens.get(196).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(197).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(198).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(199).Type);
        Assertions.assertEquals("model",  tokens.get(199).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(200).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(201).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(202).Type);
        Assertions.assertEquals("color",  tokens.get(202).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(203).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(204).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(205).Type);
        Assertions.assertEquals("licensePlate",  tokens.get(205).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(206).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(207).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(208).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(209).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(210).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(211).Type);
        Assertions.assertEquals("ParkingLots",  tokens.get(211).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(212).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(213).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(214).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(215).Type);
        Assertions.assertEquals("lotNumber",  tokens.get(215).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(216).Type);
        Assertions.assertEquals(Token.TokenTypes.ENUM, tokens.get(217).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(218).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(219).Type);
        Assertions.assertEquals("resident",  tokens.get(219).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(220).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(221).Type);
        Assertions.assertEquals("commuter",  tokens.get(221).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(222).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(223).Type);
        Assertions.assertEquals("type",  tokens.get(223).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(224).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(225).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(226).Type);
        Assertions.assertEquals("address",  tokens.get(226).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(227).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(228).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(229).Type);
        Assertions.assertEquals("numberOfSpots",  tokens.get(229).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(230).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(231).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(232).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(233).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(234).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(235).Type);
        Assertions.assertEquals("ParkingPermits",  tokens.get(235).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(236).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(237).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(238).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(239).Type);
        Assertions.assertEquals("permitNumber",  tokens.get(239).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(240).Type);
        Assertions.assertEquals(Token.TokenTypes.LIST, tokens.get(241).Type);
        Assertions.assertEquals(Token.TokenTypes.LESSTHAN, tokens.get(242).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(243).Type);
        Assertions.assertEquals("Vehicles",  tokens.get(243).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.GREATERTHAN, tokens.get(244).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(245).Type);
        Assertions.assertEquals("vehicles",  tokens.get(245).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(246).Type);
        Assertions.assertEquals(Token.TokenTypes.LIST, tokens.get(247).Type);
        Assertions.assertEquals(Token.TokenTypes.LESSTHAN, tokens.get(248).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(249).Type);
        Assertions.assertEquals("ParkingLots",  tokens.get(249).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.GREATERTHAN, tokens.get(250).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(251).Type);
        Assertions.assertEquals("authorizedParkingLots",  tokens.get(251).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(252).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(253).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(254).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(255).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(256).Type);
        Assertions.assertEquals("Departments",  tokens.get(256).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(257).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(258).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(259).Type);
        Assertions.assertEquals("1",  tokens.get(259).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(260).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(261).Type);
        Assertions.assertEquals("Computer Science",  tokens.get(261).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(262).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(263).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(264).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(265).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(266).Type);
        Assertions.assertEquals("Rooms",  tokens.get(266).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(267).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(268).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(269).Type);
        Assertions.assertEquals("1",  tokens.get(269).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(270).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(271).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(272).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(273).Type);
        Assertions.assertEquals("name",  tokens.get(273).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(274).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(275).Type);
        Assertions.assertEquals("University Administration Building",  tokens.get(275).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(276).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(277).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(278).Type);
        Assertions.assertEquals("440",  tokens.get(278).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(279).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(280).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(281).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(282).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(283).Type);
        Assertions.assertEquals("Buildings",  tokens.get(283).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(284).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(285).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(286).Type);
        Assertions.assertEquals("1",  tokens.get(286).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(287).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(288).Type);
        Assertions.assertEquals("University Administration Building",  tokens.get(288).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(289).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(290).Type);
        Assertions.assertEquals("University Administration Bldg, Albany, NY 12203",  tokens.get(290).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(291).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(292).Type);
        Assertions.assertEquals("Computer Science",  tokens.get(292).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(293).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(294).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(295).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(296).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(297).Type);
        Assertions.assertEquals("building",  tokens.get(297).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(298).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(299).Type);
        Assertions.assertEquals("1",  tokens.get(299).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(300).Type);
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(301).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(302).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(303).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(304).Type);
        Assertions.assertEquals(Token.TokenTypes.FROM, tokens.get(305).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(306).Type);
        Assertions.assertEquals("Buildings",  tokens.get(306).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(307).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(308).Type);
        Assertions.assertEquals(Token.TokenTypes.WHERE, tokens.get(309).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(310).Type);
        Assertions.assertEquals("name",  tokens.get(310).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(311).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(312).Type);
        Assertions.assertEquals("University Administration Building",  tokens.get(312).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(313).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(314).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(315).Type);
        Assertions.assertEquals(Token.TokenTypes.FROM, tokens.get(316).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(317).Type);
        Assertions.assertEquals("Vehicles",  tokens.get(317).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(318).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(319).Type);
        Assertions.assertEquals(Token.TokenTypes.WHERE, tokens.get(320).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(321).Type);
        Assertions.assertEquals("model",  tokens.get(321).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(322).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(323).Type);
        Assertions.assertEquals("RAM",  tokens.get(323).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(324).Type);
        Assertions.assertEquals(Token.TokenTypes.RETURN, tokens.get(325).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(326).Type);
        Assertions.assertEquals("Vehicles",  tokens.get(326).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(327).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(328).Type);
        Assertions.assertEquals("color",  tokens.get(328).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(329).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(330).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(331).Type);
    }
}
