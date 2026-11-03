import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test3LexerTest {
    @Test
    public void TestTest3Lexer() throws Exception {
        var code =          "create table patient\n"+
         "	wholeNumber patientID\n"+
         "	string name\n"+
         "	string phoneNum\n"+
         "	string patEmail\n"+
         "	string DOB\n"+
         "\n"+
         "create table prescriptions\n"+
         "	medicine meds\n"+
         "	patient p\n"+
         "	string datePerscribed\n"+
         "	string pharmacyAddress\n"+
         "	decimalNumber dosage\n"+
         "\n"+
         "create table medicine\n"+
         "	wholeNumber medicineID\n"+
         "	string medicineName\n"+
         "	string typeOf\n"+
         "\n"+
         "create table doctors\n"+
         "	wholeNumber staffID\n"+
         "	string name\n"+
         "	wholeNumber officeNum\n"+
         "	department d\n"+
         "\n"+
         "create table department\n"+
         "	wholeNumber departmentNum\n"+
         "	doctors depHead\n"+
         "	string depName\n"+
         "	list <doctors> staffList\n"+
         "\n"+
         "create table appointments\n"+
         "	patient p\n"+
         "	doctors doc\n"+
         "	rooms room\n"+
         "	string startTime\n"+
         "	string dateOf\n"+
         "	string reasonFor\n"+
         "\n"+
         "create table emergencyContact\n"+
         "	patient p\n"+
         "	string contactName\n"+
         "	string contactPhone\n"+
         "	enum (mother, father, sister, brother, partner, child, other) relationship\n"+
         "\n"+
         "create table bill\n"+
         "	patient p\n"+
         "	decimalNumber totalOwed\n"+
         "	decimalNumber amountPaid\n"+
         "	insuranceInfo insure\n"+
         "	enum (card, cash, electronic, check) paymentType\n"+
         "\n"+
         "create table insuranceInfo\n"+
         "	patient p\n"+
         "	wholeNumber insuranceID\n"+
         "	string insuranceProvider\n"+
         "	decimalNumber insuranceCoverage\n"+
         "\n"+
         "create table payroll\n"+
         "	doctor doc\n"+
         "	decimalNumber salary\n"+
         "	decimalNumber bonus\n"+
         "	decimalNumber overtime\n"+
         "\n"+
         "create table rooms\n"+
         "	wholeNumber roomNum\n"+
         "	string admitanceTime\n"+
         "	string dischargeTime\n"+
         "\n"+
         "insert patient\n"+
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
         "from patient\n"+
         "return name, DOB\n"+
         "\n"+
         "from appointments\n"+
         "where p=findOne(patientID=002)\n"+
         "return doc.name, reasonFor, room.roomNum\n"+
         "\n"+
         "from bill\n"+
         "where paymentType=cash\n"+
         "return p.name\n"+
         "\n"+
         "from emergencyContact\n"+
         "where p=findOne(patientID=002)\n"+
         "return contactName, relationship\n"+
         "\n"+
         "from prescriptions\n"+
         "where p=findOne(patientID=002)\n"+
         "return meds.medicineName, dosage\n"+
"";
        var tokens = new Lexer(code).Lex();
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(0).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(1).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(2).Type);
        Assertions.assertEquals("patient",  tokens.get(2).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(3).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(4).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(5).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(6).Type);
        Assertions.assertEquals("patientID",  tokens.get(6).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(7).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(8).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(9).Type);
        Assertions.assertEquals("name",  tokens.get(9).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(10).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(11).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(12).Type);
        Assertions.assertEquals("phoneNum",  tokens.get(12).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(13).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(14).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(15).Type);
        Assertions.assertEquals("patEmail",  tokens.get(15).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(16).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(17).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(18).Type);
        Assertions.assertEquals("DOB",  tokens.get(18).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(19).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(20).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(21).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(22).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(23).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(24).Type);
        Assertions.assertEquals("prescriptions",  tokens.get(24).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(25).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(26).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(27).Type);
        Assertions.assertEquals("medicine",  tokens.get(27).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(28).Type);
        Assertions.assertEquals("meds",  tokens.get(28).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(29).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(30).Type);
        Assertions.assertEquals("patient",  tokens.get(30).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(31).Type);
        Assertions.assertEquals("p",  tokens.get(31).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(32).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(33).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(34).Type);
        Assertions.assertEquals("datePerscribed",  tokens.get(34).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(35).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(36).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(37).Type);
        Assertions.assertEquals("pharmacyAddress",  tokens.get(37).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(38).Type);
        Assertions.assertEquals(Token.TokenTypes.DECIMALNUMBER, tokens.get(39).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(40).Type);
        Assertions.assertEquals("dosage",  tokens.get(40).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(41).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(42).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(43).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(44).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(45).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(46).Type);
        Assertions.assertEquals("medicine",  tokens.get(46).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(47).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(48).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(49).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(50).Type);
        Assertions.assertEquals("medicineID",  tokens.get(50).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(51).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(52).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(53).Type);
        Assertions.assertEquals("medicineName",  tokens.get(53).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(54).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(55).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(56).Type);
        Assertions.assertEquals("typeOf",  tokens.get(56).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(57).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(58).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(59).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(60).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(61).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(62).Type);
        Assertions.assertEquals("doctors",  tokens.get(62).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(63).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(64).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(65).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(66).Type);
        Assertions.assertEquals("staffID",  tokens.get(66).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(67).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(68).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(69).Type);
        Assertions.assertEquals("name",  tokens.get(69).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(70).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(71).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(72).Type);
        Assertions.assertEquals("officeNum",  tokens.get(72).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(73).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(74).Type);
        Assertions.assertEquals("department",  tokens.get(74).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(75).Type);
        Assertions.assertEquals("d",  tokens.get(75).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(76).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(77).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(78).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(79).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(80).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(81).Type);
        Assertions.assertEquals("department",  tokens.get(81).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(82).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(83).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(84).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(85).Type);
        Assertions.assertEquals("departmentNum",  tokens.get(85).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(86).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(87).Type);
        Assertions.assertEquals("doctors",  tokens.get(87).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(88).Type);
        Assertions.assertEquals("depHead",  tokens.get(88).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(89).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(90).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(91).Type);
        Assertions.assertEquals("depName",  tokens.get(91).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(92).Type);
        Assertions.assertEquals(Token.TokenTypes.LIST, tokens.get(93).Type);
        Assertions.assertEquals(Token.TokenTypes.LESSTHAN, tokens.get(94).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(95).Type);
        Assertions.assertEquals("doctors",  tokens.get(95).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.GREATERTHAN, tokens.get(96).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(97).Type);
        Assertions.assertEquals("staffList",  tokens.get(97).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(98).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(99).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(100).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(101).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(102).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(103).Type);
        Assertions.assertEquals("appointments",  tokens.get(103).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(104).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(105).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(106).Type);
        Assertions.assertEquals("patient",  tokens.get(106).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(107).Type);
        Assertions.assertEquals("p",  tokens.get(107).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(108).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(109).Type);
        Assertions.assertEquals("doctors",  tokens.get(109).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(110).Type);
        Assertions.assertEquals("doc",  tokens.get(110).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(111).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(112).Type);
        Assertions.assertEquals("rooms",  tokens.get(112).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(113).Type);
        Assertions.assertEquals("room",  tokens.get(113).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(114).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(115).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(116).Type);
        Assertions.assertEquals("startTime",  tokens.get(116).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(117).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(118).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(119).Type);
        Assertions.assertEquals("dateOf",  tokens.get(119).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(120).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(121).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(122).Type);
        Assertions.assertEquals("reasonFor",  tokens.get(122).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(123).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(124).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(125).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(126).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(127).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(128).Type);
        Assertions.assertEquals("emergencyContact",  tokens.get(128).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(129).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(130).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(131).Type);
        Assertions.assertEquals("patient",  tokens.get(131).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(132).Type);
        Assertions.assertEquals("p",  tokens.get(132).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(133).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(134).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(135).Type);
        Assertions.assertEquals("contactName",  tokens.get(135).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(136).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(137).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(138).Type);
        Assertions.assertEquals("contactPhone",  tokens.get(138).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(139).Type);
        Assertions.assertEquals(Token.TokenTypes.ENUM, tokens.get(140).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(141).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(142).Type);
        Assertions.assertEquals("mother",  tokens.get(142).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(143).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(144).Type);
        Assertions.assertEquals("father",  tokens.get(144).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(145).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(146).Type);
        Assertions.assertEquals("sister",  tokens.get(146).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(147).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(148).Type);
        Assertions.assertEquals("brother",  tokens.get(148).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(149).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(150).Type);
        Assertions.assertEquals("partner",  tokens.get(150).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(151).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(152).Type);
        Assertions.assertEquals("child",  tokens.get(152).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(153).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(154).Type);
        Assertions.assertEquals("other",  tokens.get(154).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(155).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(156).Type);
        Assertions.assertEquals("relationship",  tokens.get(156).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(157).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(158).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(159).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(160).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(161).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(162).Type);
        Assertions.assertEquals("bill",  tokens.get(162).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(163).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(164).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(165).Type);
        Assertions.assertEquals("patient",  tokens.get(165).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(166).Type);
        Assertions.assertEquals("p",  tokens.get(166).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(167).Type);
        Assertions.assertEquals(Token.TokenTypes.DECIMALNUMBER, tokens.get(168).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(169).Type);
        Assertions.assertEquals("totalOwed",  tokens.get(169).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(170).Type);
        Assertions.assertEquals(Token.TokenTypes.DECIMALNUMBER, tokens.get(171).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(172).Type);
        Assertions.assertEquals("amountPaid",  tokens.get(172).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(173).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(174).Type);
        Assertions.assertEquals("insuranceInfo",  tokens.get(174).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(175).Type);
        Assertions.assertEquals("insure",  tokens.get(175).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(176).Type);
        Assertions.assertEquals(Token.TokenTypes.ENUM, tokens.get(177).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(178).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(179).Type);
        Assertions.assertEquals("card",  tokens.get(179).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(180).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(181).Type);
        Assertions.assertEquals("cash",  tokens.get(181).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(182).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(183).Type);
        Assertions.assertEquals("electronic",  tokens.get(183).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(184).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(185).Type);
        Assertions.assertEquals("check",  tokens.get(185).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(186).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(187).Type);
        Assertions.assertEquals("paymentType",  tokens.get(187).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(188).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(189).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(190).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(191).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(192).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(193).Type);
        Assertions.assertEquals("insuranceInfo",  tokens.get(193).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(194).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(195).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(196).Type);
        Assertions.assertEquals("patient",  tokens.get(196).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(197).Type);
        Assertions.assertEquals("p",  tokens.get(197).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(198).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(199).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(200).Type);
        Assertions.assertEquals("insuranceID",  tokens.get(200).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(201).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(202).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(203).Type);
        Assertions.assertEquals("insuranceProvider",  tokens.get(203).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(204).Type);
        Assertions.assertEquals(Token.TokenTypes.DECIMALNUMBER, tokens.get(205).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(206).Type);
        Assertions.assertEquals("insuranceCoverage",  tokens.get(206).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(207).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(208).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(209).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(210).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(211).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(212).Type);
        Assertions.assertEquals("payroll",  tokens.get(212).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(213).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(214).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(215).Type);
        Assertions.assertEquals("doctor",  tokens.get(215).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(216).Type);
        Assertions.assertEquals("doc",  tokens.get(216).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(217).Type);
        Assertions.assertEquals(Token.TokenTypes.DECIMALNUMBER, tokens.get(218).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(219).Type);
        Assertions.assertEquals("salary",  tokens.get(219).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(220).Type);
        Assertions.assertEquals(Token.TokenTypes.DECIMALNUMBER, tokens.get(221).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(222).Type);
        Assertions.assertEquals("bonus",  tokens.get(222).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(223).Type);
        Assertions.assertEquals(Token.TokenTypes.DECIMALNUMBER, tokens.get(224).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(225).Type);
        Assertions.assertEquals("overtime",  tokens.get(225).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(226).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(227).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(228).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(229).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(230).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(231).Type);
        Assertions.assertEquals("rooms",  tokens.get(231).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(232).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(233).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(234).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(235).Type);
        Assertions.assertEquals("roomNum",  tokens.get(235).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(236).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(237).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(238).Type);
        Assertions.assertEquals("admitanceTime",  tokens.get(238).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(239).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(240).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(241).Type);
        Assertions.assertEquals("dischargeTime",  tokens.get(241).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(242).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(243).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(244).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(245).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(246).Type);
        Assertions.assertEquals("patient",  tokens.get(246).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(247).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(248).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(249).Type);
        Assertions.assertEquals("001",  tokens.get(249).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(250).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(251).Type);
        Assertions.assertEquals("Urahara Kisuke",  tokens.get(251).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(252).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(253).Type);
        Assertions.assertEquals("(518) 699-9999",  tokens.get(253).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(254).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(255).Type);
        Assertions.assertEquals("hbuthShopkeeper@gmail.com",  tokens.get(255).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(256).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(257).Type);
        Assertions.assertEquals("7/23/1200",  tokens.get(257).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(258).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(259).Type);
        Assertions.assertEquals("002",  tokens.get(259).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(260).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(261).Type);
        Assertions.assertEquals("Aizen Sosuke",  tokens.get(261).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(262).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(263).Type);
        Assertions.assertEquals("(518) 123-4444",  tokens.get(263).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(264).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(265).Type);
        Assertions.assertEquals("theRealHogyoku@gmail.com",  tokens.get(265).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(266).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(267).Type);
        Assertions.assertEquals("1/1/1000",  tokens.get(267).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(268).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(269).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(270).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(271).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(272).Type);
        Assertions.assertEquals("medicine",  tokens.get(272).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(273).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(274).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(275).Type);
        Assertions.assertEquals("100",  tokens.get(275).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(276).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(277).Type);
        Assertions.assertEquals("ReiatsuIVPlus",  tokens.get(277).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(278).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(279).Type);
        Assertions.assertEquals("Injection twice a day every 12 hours for about 20 thousand years",  tokens.get(279).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(280).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(281).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(282).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(283).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(284).Type);
        Assertions.assertEquals("prescriptions",  tokens.get(284).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(285).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(286).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(287).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(288).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(289).Type);
        Assertions.assertEquals("medicineID",  tokens.get(289).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(290).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(291).Type);
        Assertions.assertEquals("100",  tokens.get(291).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(292).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(293).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(294).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(295).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(296).Type);
        Assertions.assertEquals("patientID",  tokens.get(296).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(297).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(298).Type);
        Assertions.assertEquals("002",  tokens.get(298).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(299).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(300).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(301).Type);
        Assertions.assertEquals("08/28/2026",  tokens.get(301).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(302).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(303).Type);
        Assertions.assertEquals("555 Division Road, Soul Society, New York",  tokens.get(303).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(304).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(305).Type);
        Assertions.assertEquals("100.9",  tokens.get(305).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(306).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(307).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(308).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(309).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(310).Type);
        Assertions.assertEquals("doctors",  tokens.get(310).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(311).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(312).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(313).Type);
        Assertions.assertEquals("222",  tokens.get(313).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(314).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(315).Type);
        Assertions.assertEquals("Dr. Mayuri Kurotsuchi",  tokens.get(315).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(316).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(317).Type);
        Assertions.assertEquals("12",  tokens.get(317).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(318).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(319).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(320).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(321).Type);
        Assertions.assertEquals("departmentNum",  tokens.get(321).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(322).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(323).Type);
        Assertions.assertEquals("1212",  tokens.get(323).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(324).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(325).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(326).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(327).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(328).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(329).Type);
        Assertions.assertEquals("department",  tokens.get(329).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(330).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(331).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(332).Type);
        Assertions.assertEquals("1212",  tokens.get(332).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(333).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(334).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(335).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(336).Type);
        Assertions.assertEquals("staffID",  tokens.get(336).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(337).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(338).Type);
        Assertions.assertEquals("222",  tokens.get(338).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(339).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(340).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(341).Type);
        Assertions.assertEquals("The 12th Division",  tokens.get(341).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(342).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(343).Type);
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(344).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(345).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(346).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(347).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(348).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(349).Type);
        Assertions.assertEquals("rooms",  tokens.get(349).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(350).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(351).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(352).Type);
        Assertions.assertEquals("12",  tokens.get(352).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(353).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(354).Type);
        Assertions.assertEquals("6:35",  tokens.get(354).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(355).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(356).Type);
        Assertions.assertEquals("null",  tokens.get(356).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(357).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(358).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(359).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(360).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(361).Type);
        Assertions.assertEquals("appointments",  tokens.get(361).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(362).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(363).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(364).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(365).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(366).Type);
        Assertions.assertEquals("patientID",  tokens.get(366).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(367).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(368).Type);
        Assertions.assertEquals("002",  tokens.get(368).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(369).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(370).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(371).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(372).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(373).Type);
        Assertions.assertEquals("staffID",  tokens.get(373).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(374).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(375).Type);
        Assertions.assertEquals("222",  tokens.get(375).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(376).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(377).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(378).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(379).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(380).Type);
        Assertions.assertEquals("roomNum",  tokens.get(380).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(381).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(382).Type);
        Assertions.assertEquals("12",  tokens.get(382).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(383).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(384).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(385).Type);
        Assertions.assertEquals("6:30 PM",  tokens.get(385).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(386).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(387).Type);
        Assertions.assertEquals("8/28/26",  tokens.get(387).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(388).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(389).Type);
        Assertions.assertEquals("Feeling faint",  tokens.get(389).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(390).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(391).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(392).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(393).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(394).Type);
        Assertions.assertEquals("emergencyContact",  tokens.get(394).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(395).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(396).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(397).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(398).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(399).Type);
        Assertions.assertEquals("patientID",  tokens.get(399).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(400).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(401).Type);
        Assertions.assertEquals("002",  tokens.get(401).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(402).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(403).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(404).Type);
        Assertions.assertEquals("Gin Ichimaru",  tokens.get(404).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(405).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(406).Type);
        Assertions.assertEquals("(518) 123-4446",  tokens.get(406).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(407).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(408).Type);
        Assertions.assertEquals("other",  tokens.get(408).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(409).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(410).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(411).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(412).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(413).Type);
        Assertions.assertEquals("payroll",  tokens.get(413).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(414).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(415).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(416).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(417).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(418).Type);
        Assertions.assertEquals("staffID",  tokens.get(418).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(419).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(420).Type);
        Assertions.assertEquals("222",  tokens.get(420).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(421).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(422).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(423).Type);
        Assertions.assertEquals("1000000.90",  tokens.get(423).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(424).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(425).Type);
        Assertions.assertEquals("0.00",  tokens.get(425).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(426).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(427).Type);
        Assertions.assertEquals("0.00",  tokens.get(427).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(428).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(429).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(430).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(431).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(432).Type);
        Assertions.assertEquals("insuranceInfo",  tokens.get(432).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(433).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(434).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(435).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(436).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(437).Type);
        Assertions.assertEquals("patientID",  tokens.get(437).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(438).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(439).Type);
        Assertions.assertEquals("002",  tokens.get(439).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(440).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(441).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(442).Type);
        Assertions.assertEquals("000555666777",  tokens.get(442).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(443).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(444).Type);
        Assertions.assertEquals("SeireiteiUnited",  tokens.get(444).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(445).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(446).Type);
        Assertions.assertEquals("500000.00",  tokens.get(446).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(447).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(448).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(449).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(450).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(451).Type);
        Assertions.assertEquals("bill",  tokens.get(451).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(452).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(453).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(454).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(455).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(456).Type);
        Assertions.assertEquals("patientID",  tokens.get(456).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(457).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(458).Type);
        Assertions.assertEquals("002",  tokens.get(458).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(459).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(460).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(461).Type);
        Assertions.assertEquals("10.00",  tokens.get(461).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(462).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(463).Type);
        Assertions.assertEquals("10.00",  tokens.get(463).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(464).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(465).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(466).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(467).Type);
        Assertions.assertEquals("insuranceID",  tokens.get(467).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(468).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(469).Type);
        Assertions.assertEquals("000555666777",  tokens.get(469).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(470).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(471).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(472).Type);
        Assertions.assertEquals("cash",  tokens.get(472).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(473).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(474).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(475).Type);
        Assertions.assertEquals(Token.TokenTypes.FROM, tokens.get(476).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(477).Type);
        Assertions.assertEquals("patient",  tokens.get(477).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(478).Type);
        Assertions.assertEquals(Token.TokenTypes.RETURN, tokens.get(479).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(480).Type);
        Assertions.assertEquals("name",  tokens.get(480).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(481).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(482).Type);
        Assertions.assertEquals("DOB",  tokens.get(482).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(483).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(484).Type);
        Assertions.assertEquals(Token.TokenTypes.FROM, tokens.get(485).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(486).Type);
        Assertions.assertEquals("appointments",  tokens.get(486).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(487).Type);
        Assertions.assertEquals(Token.TokenTypes.WHERE, tokens.get(488).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(489).Type);
        Assertions.assertEquals("p",  tokens.get(489).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(490).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(491).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(492).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(493).Type);
        Assertions.assertEquals("patientID",  tokens.get(493).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(494).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(495).Type);
        Assertions.assertEquals("002",  tokens.get(495).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(496).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(497).Type);
        Assertions.assertEquals(Token.TokenTypes.RETURN, tokens.get(498).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(499).Type);
        Assertions.assertEquals("doc",  tokens.get(499).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(500).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(501).Type);
        Assertions.assertEquals("name",  tokens.get(501).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(502).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(503).Type);
        Assertions.assertEquals("reasonFor",  tokens.get(503).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(504).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(505).Type);
        Assertions.assertEquals("room",  tokens.get(505).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(506).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(507).Type);
        Assertions.assertEquals("roomNum",  tokens.get(507).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(508).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(509).Type);
        Assertions.assertEquals(Token.TokenTypes.FROM, tokens.get(510).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(511).Type);
        Assertions.assertEquals("bill",  tokens.get(511).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(512).Type);
        Assertions.assertEquals(Token.TokenTypes.WHERE, tokens.get(513).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(514).Type);
        Assertions.assertEquals("paymentType",  tokens.get(514).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(515).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(516).Type);
        Assertions.assertEquals("cash",  tokens.get(516).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(517).Type);
        Assertions.assertEquals(Token.TokenTypes.RETURN, tokens.get(518).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(519).Type);
        Assertions.assertEquals("p",  tokens.get(519).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(520).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(521).Type);
        Assertions.assertEquals("name",  tokens.get(521).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(522).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(523).Type);
        Assertions.assertEquals(Token.TokenTypes.FROM, tokens.get(524).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(525).Type);
        Assertions.assertEquals("emergencyContact",  tokens.get(525).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(526).Type);
        Assertions.assertEquals(Token.TokenTypes.WHERE, tokens.get(527).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(528).Type);
        Assertions.assertEquals("p",  tokens.get(528).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(529).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(530).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(531).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(532).Type);
        Assertions.assertEquals("patientID",  tokens.get(532).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(533).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(534).Type);
        Assertions.assertEquals("002",  tokens.get(534).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(535).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(536).Type);
        Assertions.assertEquals(Token.TokenTypes.RETURN, tokens.get(537).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(538).Type);
        Assertions.assertEquals("contactName",  tokens.get(538).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(539).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(540).Type);
        Assertions.assertEquals("relationship",  tokens.get(540).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(541).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(542).Type);
        Assertions.assertEquals(Token.TokenTypes.FROM, tokens.get(543).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(544).Type);
        Assertions.assertEquals("prescriptions",  tokens.get(544).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(545).Type);
        Assertions.assertEquals(Token.TokenTypes.WHERE, tokens.get(546).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(547).Type);
        Assertions.assertEquals("p",  tokens.get(547).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(548).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(549).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(550).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(551).Type);
        Assertions.assertEquals("patientID",  tokens.get(551).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(552).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(553).Type);
        Assertions.assertEquals("002",  tokens.get(553).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(554).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(555).Type);
        Assertions.assertEquals(Token.TokenTypes.RETURN, tokens.get(556).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(557).Type);
        Assertions.assertEquals("meds",  tokens.get(557).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.DOT, tokens.get(558).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(559).Type);
        Assertions.assertEquals("medicineName",  tokens.get(559).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(560).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(561).Type);
        Assertions.assertEquals("dosage",  tokens.get(561).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(562).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(563).Type);
    }
}
