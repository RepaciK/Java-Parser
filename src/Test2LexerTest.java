import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test2LexerTest {
    @Test
    public void TestTest2Lexer() throws Exception {
        var code =          "create table restaurant\n"+
         "    string name\n"+
         "    string address\n"+
         "    enum (italian, mexican, thai, greek, japanese) cuisine\n"+
         "    decimalNumber rating\n"+
         "\n"+
         "create table menuItem\n"+
         "    string name\n"+
         "    decimalNumber price\n"+
         "    list <ingredient> ingredients\n"+
         "    restaurant servedAt\n"+
         "\n"+
         "create table ingredient\n"+
         "    string name\n"+
         "    enum (none, diary, gluten) allergen\n"+
         "\n"+
         "create table order\n"+
         "    customer who\n"+
         "    restaurant placedAt\n"+
         "    list <orderItem> items\n"+
         "    enum (placed, preparing, outForDelivery, delivered, cancelled) status\n"+
         "    deliveryDriver driver\n"+
         "\n"+
         "create table reservation\n"+
         "    customer who\n"+
         "    diningTable table\n"+
         "    wholeNumber partySize\n"+
         "    string time\n"+
         "\n"+
         "create table customer\n"+
         "    string name\n"+
         "    string address\n"+
         "    string phone\n"+
         "\n"+
         "create table review\n"+
         "    customer who\n"+
         "    restaurant about\n"+
         "    wholeNumber stars\n"+
         "    string comment\n"+
         "\n"+
         "create table orderItem\n"+
         "    menuItem item\n"+
         "    wholeNumber quantity\n"+
         "\n"+
         "create table diningTable\n"+
         "    restaurant location\n"+
         "    wholeNumber tableNumber\n"+
         "    wholeNumber capacity\n"+
         "\n"+
         "create table deliveryDriver\n"+
         "    string name\n"+
         "    string vehicle\n"+
         "    decimalNumber rating\n"+
         "\n"+
         "insert restaurant\n"+
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
         "from menuItem\n"+
         "where servedAt=findOne(name=\"Sarah’s Pizzeria\")\n"+
         "return name, price\n"+
         "\n"+
         "from diningTable\n"+
         "where location=findOne(name=\"Viva Cinco De Mayo\")\n"+
         "return tableNumber, capacity\n"+
         "\n"+
         "from reservation\n"+
         "where who=findOne(name=\"Takeem\")\n"+
         "return table, tableNumber, partySize, time\n"+
"";
        var tokens = new Lexer(code).Lex();
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(0).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(1).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(2).Type);
        Assertions.assertEquals("restaurant",  tokens.get(2).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(3).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(4).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(5).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(6).Type);
        Assertions.assertEquals("name",  tokens.get(6).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(7).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(8).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(9).Type);
        Assertions.assertEquals("address",  tokens.get(9).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(10).Type);
        Assertions.assertEquals(Token.TokenTypes.ENUM, tokens.get(11).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(12).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(13).Type);
        Assertions.assertEquals("italian",  tokens.get(13).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(14).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(15).Type);
        Assertions.assertEquals("mexican",  tokens.get(15).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(16).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(17).Type);
        Assertions.assertEquals("thai",  tokens.get(17).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(18).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(19).Type);
        Assertions.assertEquals("greek",  tokens.get(19).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(20).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(21).Type);
        Assertions.assertEquals("japanese",  tokens.get(21).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(22).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(23).Type);
        Assertions.assertEquals("cuisine",  tokens.get(23).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(24).Type);
        Assertions.assertEquals(Token.TokenTypes.DECIMALNUMBER, tokens.get(25).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(26).Type);
        Assertions.assertEquals("rating",  tokens.get(26).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(27).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(28).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(29).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(30).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(31).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(32).Type);
        Assertions.assertEquals("menuItem",  tokens.get(32).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(33).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(34).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(35).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(36).Type);
        Assertions.assertEquals("name",  tokens.get(36).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(37).Type);
        Assertions.assertEquals(Token.TokenTypes.DECIMALNUMBER, tokens.get(38).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(39).Type);
        Assertions.assertEquals("price",  tokens.get(39).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(40).Type);
        Assertions.assertEquals(Token.TokenTypes.LIST, tokens.get(41).Type);
        Assertions.assertEquals(Token.TokenTypes.LESSTHAN, tokens.get(42).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(43).Type);
        Assertions.assertEquals("ingredient",  tokens.get(43).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.GREATERTHAN, tokens.get(44).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(45).Type);
        Assertions.assertEquals("ingredients",  tokens.get(45).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(46).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(47).Type);
        Assertions.assertEquals("restaurant",  tokens.get(47).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(48).Type);
        Assertions.assertEquals("servedAt",  tokens.get(48).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(49).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(50).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(51).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(52).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(53).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(54).Type);
        Assertions.assertEquals("ingredient",  tokens.get(54).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(55).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(56).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(57).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(58).Type);
        Assertions.assertEquals("name",  tokens.get(58).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(59).Type);
        Assertions.assertEquals(Token.TokenTypes.ENUM, tokens.get(60).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(61).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(62).Type);
        Assertions.assertEquals("none",  tokens.get(62).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(63).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(64).Type);
        Assertions.assertEquals("diary",  tokens.get(64).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(65).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(66).Type);
        Assertions.assertEquals("gluten",  tokens.get(66).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(67).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(68).Type);
        Assertions.assertEquals("allergen",  tokens.get(68).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(69).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(70).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(71).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(72).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(73).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(74).Type);
        Assertions.assertEquals("order",  tokens.get(74).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(75).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(76).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(77).Type);
        Assertions.assertEquals("customer",  tokens.get(77).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(78).Type);
        Assertions.assertEquals("who",  tokens.get(78).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(79).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(80).Type);
        Assertions.assertEquals("restaurant",  tokens.get(80).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(81).Type);
        Assertions.assertEquals("placedAt",  tokens.get(81).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(82).Type);
        Assertions.assertEquals(Token.TokenTypes.LIST, tokens.get(83).Type);
        Assertions.assertEquals(Token.TokenTypes.LESSTHAN, tokens.get(84).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(85).Type);
        Assertions.assertEquals("orderItem",  tokens.get(85).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.GREATERTHAN, tokens.get(86).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(87).Type);
        Assertions.assertEquals("items",  tokens.get(87).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(88).Type);
        Assertions.assertEquals(Token.TokenTypes.ENUM, tokens.get(89).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(90).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(91).Type);
        Assertions.assertEquals("placed",  tokens.get(91).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(92).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(93).Type);
        Assertions.assertEquals("preparing",  tokens.get(93).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(94).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(95).Type);
        Assertions.assertEquals("outForDelivery",  tokens.get(95).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(96).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(97).Type);
        Assertions.assertEquals("delivered",  tokens.get(97).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(98).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(99).Type);
        Assertions.assertEquals("cancelled",  tokens.get(99).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(100).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(101).Type);
        Assertions.assertEquals("status",  tokens.get(101).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(102).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(103).Type);
        Assertions.assertEquals("deliveryDriver",  tokens.get(103).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(104).Type);
        Assertions.assertEquals("driver",  tokens.get(104).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(105).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(106).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(107).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(108).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(109).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(110).Type);
        Assertions.assertEquals("reservation",  tokens.get(110).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(111).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(112).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(113).Type);
        Assertions.assertEquals("customer",  tokens.get(113).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(114).Type);
        Assertions.assertEquals("who",  tokens.get(114).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(115).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(116).Type);
        Assertions.assertEquals("diningTable",  tokens.get(116).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(117).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(118).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(119).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(120).Type);
        Assertions.assertEquals("partySize",  tokens.get(120).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(121).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(122).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(123).Type);
        Assertions.assertEquals("time",  tokens.get(123).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(124).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(125).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(126).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(127).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(128).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(129).Type);
        Assertions.assertEquals("customer",  tokens.get(129).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(130).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(131).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(132).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(133).Type);
        Assertions.assertEquals("name",  tokens.get(133).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(134).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(135).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(136).Type);
        Assertions.assertEquals("address",  tokens.get(136).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(137).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(138).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(139).Type);
        Assertions.assertEquals("phone",  tokens.get(139).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(140).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(141).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(142).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(143).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(144).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(145).Type);
        Assertions.assertEquals("review",  tokens.get(145).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(146).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(147).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(148).Type);
        Assertions.assertEquals("customer",  tokens.get(148).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(149).Type);
        Assertions.assertEquals("who",  tokens.get(149).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(150).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(151).Type);
        Assertions.assertEquals("restaurant",  tokens.get(151).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(152).Type);
        Assertions.assertEquals("about",  tokens.get(152).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(153).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(154).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(155).Type);
        Assertions.assertEquals("stars",  tokens.get(155).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(156).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(157).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(158).Type);
        Assertions.assertEquals("comment",  tokens.get(158).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(159).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(160).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(161).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(162).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(163).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(164).Type);
        Assertions.assertEquals("orderItem",  tokens.get(164).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(165).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(166).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(167).Type);
        Assertions.assertEquals("menuItem",  tokens.get(167).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(168).Type);
        Assertions.assertEquals("item",  tokens.get(168).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(169).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(170).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(171).Type);
        Assertions.assertEquals("quantity",  tokens.get(171).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(172).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(173).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(174).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(175).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(176).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(177).Type);
        Assertions.assertEquals("diningTable",  tokens.get(177).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(178).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(179).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(180).Type);
        Assertions.assertEquals("restaurant",  tokens.get(180).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(181).Type);
        Assertions.assertEquals("location",  tokens.get(181).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(182).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(183).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(184).Type);
        Assertions.assertEquals("tableNumber",  tokens.get(184).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(185).Type);
        Assertions.assertEquals(Token.TokenTypes.WHOLENUMBER, tokens.get(186).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(187).Type);
        Assertions.assertEquals("capacity",  tokens.get(187).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(188).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(189).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(190).Type);
        Assertions.assertEquals(Token.TokenTypes.CREATE, tokens.get(191).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(192).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(193).Type);
        Assertions.assertEquals("deliveryDriver",  tokens.get(193).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(194).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(195).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(196).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(197).Type);
        Assertions.assertEquals("name",  tokens.get(197).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(198).Type);
        Assertions.assertEquals(Token.TokenTypes.STRING, tokens.get(199).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(200).Type);
        Assertions.assertEquals("vehicle",  tokens.get(200).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(201).Type);
        Assertions.assertEquals(Token.TokenTypes.DECIMALNUMBER, tokens.get(202).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(203).Type);
        Assertions.assertEquals("rating",  tokens.get(203).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(204).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(205).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(206).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(207).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(208).Type);
        Assertions.assertEquals("restaurant",  tokens.get(208).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(209).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(210).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(211).Type);
        Assertions.assertEquals("Sarah’s Pizzeria",  tokens.get(211).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(212).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(213).Type);
        Assertions.assertEquals("123 Springfield Blvd, New York, NY",  tokens.get(213).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(214).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(215).Type);
        Assertions.assertEquals("italian",  tokens.get(215).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(216).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(217).Type);
        Assertions.assertEquals("4.0",  tokens.get(217).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(218).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(219).Type);
        Assertions.assertEquals("Viva Cinco De Mayo",  tokens.get(219).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(220).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(221).Type);
        Assertions.assertEquals("456 Linden Blvd, New York, NY",  tokens.get(221).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(222).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(223).Type);
        Assertions.assertEquals("mexican",  tokens.get(223).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(224).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(225).Type);
        Assertions.assertEquals("4.4",  tokens.get(225).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(226).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(227).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(228).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(229).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(230).Type);
        Assertions.assertEquals("menuItem",  tokens.get(230).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(231).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(232).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(233).Type);
        Assertions.assertEquals("Pizza",  tokens.get(233).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(234).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(235).Type);
        Assertions.assertEquals("12.99",  tokens.get(235).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(236).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(237).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(238).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(239).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(240).Type);
        Assertions.assertEquals("name",  tokens.get(240).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(241).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(242).Type);
        Assertions.assertEquals("Dough",  tokens.get(242).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(243).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(244).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(245).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(246).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(247).Type);
        Assertions.assertEquals("name",  tokens.get(247).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(248).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(249).Type);
        Assertions.assertEquals("Mozzarella",  tokens.get(249).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(250).Type);
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(251).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(252).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(253).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(254).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(255).Type);
        Assertions.assertEquals("name",  tokens.get(255).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(256).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(257).Type);
        Assertions.assertEquals("Sarah’s Pizzeria",  tokens.get(257).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(258).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(259).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(260).Type);
        Assertions.assertEquals("Burger",  tokens.get(260).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(261).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(262).Type);
        Assertions.assertEquals("8.99",  tokens.get(262).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(263).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(264).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(265).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(266).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(267).Type);
        Assertions.assertEquals("name",  tokens.get(267).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(268).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(269).Type);
        Assertions.assertEquals("Bun",  tokens.get(269).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(270).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(271).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(272).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(273).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(274).Type);
        Assertions.assertEquals("name",  tokens.get(274).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(275).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(276).Type);
        Assertions.assertEquals("Beef Patty",  tokens.get(276).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(277).Type);
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(278).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(279).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(280).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(281).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(282).Type);
        Assertions.assertEquals("name",  tokens.get(282).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(283).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(284).Type);
        Assertions.assertEquals("Sarah’s Pizzeria",  tokens.get(284).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(285).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(286).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(287).Type);
        Assertions.assertEquals("Tacos",  tokens.get(287).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(288).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(289).Type);
        Assertions.assertEquals("9.99",  tokens.get(289).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(290).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(291).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(292).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(293).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(294).Type);
        Assertions.assertEquals("name",  tokens.get(294).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(295).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(296).Type);
        Assertions.assertEquals("Tortilla",  tokens.get(296).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(297).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(298).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(299).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(300).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(301).Type);
        Assertions.assertEquals("name",  tokens.get(301).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(302).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(303).Type);
        Assertions.assertEquals("Salsa",  tokens.get(303).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(304).Type);
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(305).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(306).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(307).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(308).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(309).Type);
        Assertions.assertEquals("name",  tokens.get(309).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(310).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(311).Type);
        Assertions.assertEquals("Viva Cinco De Mayo",  tokens.get(311).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(312).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(313).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(314).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(315).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(316).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(317).Type);
        Assertions.assertEquals("ingredient",  tokens.get(317).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(318).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(319).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(320).Type);
        Assertions.assertEquals("Dough",  tokens.get(320).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(321).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(322).Type);
        Assertions.assertEquals("gluten",  tokens.get(322).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(323).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(324).Type);
        Assertions.assertEquals("Mozzarella",  tokens.get(324).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(325).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(326).Type);
        Assertions.assertEquals("diary",  tokens.get(326).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(327).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(328).Type);
        Assertions.assertEquals("Beef Patty",  tokens.get(328).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(329).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(330).Type);
        Assertions.assertEquals("none",  tokens.get(330).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(331).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(332).Type);
        Assertions.assertEquals("Bun",  tokens.get(332).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(333).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(334).Type);
        Assertions.assertEquals("gluten",  tokens.get(334).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(335).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(336).Type);
        Assertions.assertEquals("Tortilla",  tokens.get(336).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(337).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(338).Type);
        Assertions.assertEquals("gluten",  tokens.get(338).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(339).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(340).Type);
        Assertions.assertEquals("Salsa",  tokens.get(340).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(341).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(342).Type);
        Assertions.assertEquals("none",  tokens.get(342).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(343).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(344).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(345).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(346).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(347).Type);
        Assertions.assertEquals("order",  tokens.get(347).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(348).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(349).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(350).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(351).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(352).Type);
        Assertions.assertEquals("name",  tokens.get(352).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(353).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(354).Type);
        Assertions.assertEquals("Takeem",  tokens.get(354).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(355).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(356).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(357).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(358).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(359).Type);
        Assertions.assertEquals("name",  tokens.get(359).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(360).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(361).Type);
        Assertions.assertEquals("Sarah’s Pizzeria",  tokens.get(361).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(362).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(363).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(364).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(365).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(366).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(367).Type);
        Assertions.assertEquals("quantity",  tokens.get(367).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(368).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(369).Type);
        Assertions.assertEquals("5",  tokens.get(369).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(370).Type);
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(371).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(372).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(373).Type);
        Assertions.assertEquals("delivered",  tokens.get(373).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(374).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(375).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(376).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(377).Type);
        Assertions.assertEquals("name",  tokens.get(377).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(378).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(379).Type);
        Assertions.assertEquals("Elizabeth",  tokens.get(379).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(380).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(381).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(382).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(383).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(384).Type);
        Assertions.assertEquals("name",  tokens.get(384).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(385).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(386).Type);
        Assertions.assertEquals("Francis",  tokens.get(386).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(387).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(388).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(389).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(390).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(391).Type);
        Assertions.assertEquals("name",  tokens.get(391).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(392).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(393).Type);
        Assertions.assertEquals("Viva Cinco De Mayo",  tokens.get(393).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(394).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(395).Type);
        Assertions.assertEquals(Token.TokenTypes.LEFTBRACE, tokens.get(396).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(397).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(398).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(399).Type);
        Assertions.assertEquals("quanity",  tokens.get(399).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(400).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(401).Type);
        Assertions.assertEquals("6",  tokens.get(401).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(402).Type);
        Assertions.assertEquals(Token.TokenTypes.RIGHTBRACE, tokens.get(403).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(404).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(405).Type);
        Assertions.assertEquals("preparing",  tokens.get(405).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(406).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(407).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(408).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(409).Type);
        Assertions.assertEquals("name",  tokens.get(409).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(410).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(411).Type);
        Assertions.assertEquals("Lijah",  tokens.get(411).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(412).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(413).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(414).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(415).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(416).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(417).Type);
        Assertions.assertEquals("reservation",  tokens.get(417).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(418).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(419).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(420).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(421).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(422).Type);
        Assertions.assertEquals("name",  tokens.get(422).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(423).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(424).Type);
        Assertions.assertEquals("Takeem",  tokens.get(424).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(425).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(426).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(427).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(428).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(429).Type);
        Assertions.assertEquals("tableNumber",  tokens.get(429).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(430).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(431).Type);
        Assertions.assertEquals("1",  tokens.get(431).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(432).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(433).Type);
        Assertions.assertEquals("capacity",  tokens.get(433).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(434).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(435).Type);
        Assertions.assertEquals("6",  tokens.get(435).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(436).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(437).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(438).Type);
        Assertions.assertEquals("5",  tokens.get(438).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(439).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(440).Type);
        Assertions.assertEquals("8:00 PM",  tokens.get(440).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(441).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(442).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(443).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(444).Type);
        Assertions.assertEquals("name",  tokens.get(444).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(445).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(446).Type);
        Assertions.assertEquals("Francis",  tokens.get(446).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(447).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(448).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(449).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(450).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(451).Type);
        Assertions.assertEquals("tableNumber",  tokens.get(451).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(452).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(453).Type);
        Assertions.assertEquals("3",  tokens.get(453).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(454).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(455).Type);
        Assertions.assertEquals("capacity",  tokens.get(455).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(456).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(457).Type);
        Assertions.assertEquals("4",  tokens.get(457).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(458).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(459).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(460).Type);
        Assertions.assertEquals("4",  tokens.get(460).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(461).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(462).Type);
        Assertions.assertEquals("10:00 PM",  tokens.get(462).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(463).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(464).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(465).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(466).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(467).Type);
        Assertions.assertEquals("customer",  tokens.get(467).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(468).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(469).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(470).Type);
        Assertions.assertEquals("Takeem",  tokens.get(470).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(471).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(472).Type);
        Assertions.assertEquals("789 Sutphin Blvd",  tokens.get(472).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(473).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(474).Type);
        Assertions.assertEquals("656-778-2245",  tokens.get(474).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(475).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(476).Type);
        Assertions.assertEquals("Francis",  tokens.get(476).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(477).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(478).Type);
        Assertions.assertEquals("632 Foch Blvd",  tokens.get(478).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(479).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(480).Type);
        Assertions.assertEquals("434-776-6654",  tokens.get(480).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(481).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(482).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(483).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(484).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(485).Type);
        Assertions.assertEquals("review",  tokens.get(485).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(486).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(487).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(488).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(489).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(490).Type);
        Assertions.assertEquals("name",  tokens.get(490).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(491).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(492).Type);
        Assertions.assertEquals("Takeem",  tokens.get(492).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(493).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(494).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(495).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(496).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(497).Type);
        Assertions.assertEquals("name",  tokens.get(497).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(498).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(499).Type);
        Assertions.assertEquals("Sarah’s Pizzeria",  tokens.get(499).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(500).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(501).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(502).Type);
        Assertions.assertEquals("4",  tokens.get(502).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(503).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(504).Type);
        Assertions.assertEquals("Tasted good",  tokens.get(504).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(505).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(506).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(507).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(508).Type);
        Assertions.assertEquals("name",  tokens.get(508).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(509).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(510).Type);
        Assertions.assertEquals("Francis",  tokens.get(510).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(511).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(512).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(513).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(514).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(515).Type);
        Assertions.assertEquals("name",  tokens.get(515).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(516).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(517).Type);
        Assertions.assertEquals("Viva Cinco De Mayo",  tokens.get(517).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(518).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(519).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(520).Type);
        Assertions.assertEquals("4",  tokens.get(520).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(521).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(522).Type);
        Assertions.assertEquals("Fast delivery and the food had a tasty spice to it!",  tokens.get(522).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(523).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(524).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(525).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(526).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(527).Type);
        Assertions.assertEquals("orderItem",  tokens.get(527).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(528).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(529).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(530).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(531).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(532).Type);
        Assertions.assertEquals("name",  tokens.get(532).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(533).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(534).Type);
        Assertions.assertEquals("Pizza",  tokens.get(534).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(535).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(536).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(537).Type);
        Assertions.assertEquals("5",  tokens.get(537).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(538).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(539).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(540).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(541).Type);
        Assertions.assertEquals("name",  tokens.get(541).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(542).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(543).Type);
        Assertions.assertEquals("Tacos",  tokens.get(543).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(544).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(545).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(546).Type);
        Assertions.assertEquals("6",  tokens.get(546).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(547).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(548).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(549).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(550).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(551).Type);
        Assertions.assertEquals("diningTable",  tokens.get(551).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(552).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(553).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(554).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(555).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(556).Type);
        Assertions.assertEquals("name",  tokens.get(556).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(557).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(558).Type);
        Assertions.assertEquals("Sarah’s Pizzeria",  tokens.get(558).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(559).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(560).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(561).Type);
        Assertions.assertEquals("2",  tokens.get(561).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(562).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(563).Type);
        Assertions.assertEquals("4",  tokens.get(563).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(564).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(565).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(566).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(567).Type);
        Assertions.assertEquals("name",  tokens.get(567).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(568).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(569).Type);
        Assertions.assertEquals("Viva Cinco De Mayo",  tokens.get(569).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(570).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(571).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(572).Type);
        Assertions.assertEquals("3",  tokens.get(572).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(573).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(574).Type);
        Assertions.assertEquals("4",  tokens.get(574).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(575).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(576).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(577).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(578).Type);
        Assertions.assertEquals("name",  tokens.get(578).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(579).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(580).Type);
        Assertions.assertEquals("Sarah’s Pizzeria",  tokens.get(580).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(581).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(582).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(583).Type);
        Assertions.assertEquals("6",  tokens.get(583).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(584).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(585).Type);
        Assertions.assertEquals("6",  tokens.get(585).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(586).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(587).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(588).Type);
        Assertions.assertEquals(Token.TokenTypes.INSERT, tokens.get(589).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(590).Type);
        Assertions.assertEquals("deliveryDriver",  tokens.get(590).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(591).Type);
        Assertions.assertEquals(Token.TokenTypes.INDENT, tokens.get(592).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(593).Type);
        Assertions.assertEquals("Elizabeth",  tokens.get(593).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(594).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(595).Type);
        Assertions.assertEquals("Honda Civic",  tokens.get(595).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(596).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(597).Type);
        Assertions.assertEquals("5.0",  tokens.get(597).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(598).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(599).Type);
        Assertions.assertEquals("Lijah",  tokens.get(599).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(600).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(601).Type);
        Assertions.assertEquals("Nissan Versa",  tokens.get(601).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(602).Type);
        Assertions.assertEquals(Token.TokenTypes.NUMBER, tokens.get(603).Type);
        Assertions.assertEquals("4.8",  tokens.get(603).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(604).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(605).Type);
        Assertions.assertEquals(Token.TokenTypes.DEDENT, tokens.get(606).Type);
        Assertions.assertEquals(Token.TokenTypes.FROM, tokens.get(607).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(608).Type);
        Assertions.assertEquals("menuItem",  tokens.get(608).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(609).Type);
        Assertions.assertEquals(Token.TokenTypes.WHERE, tokens.get(610).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(611).Type);
        Assertions.assertEquals("servedAt",  tokens.get(611).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(612).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(613).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(614).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(615).Type);
        Assertions.assertEquals("name",  tokens.get(615).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(616).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(617).Type);
        Assertions.assertEquals("Sarah’s Pizzeria",  tokens.get(617).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(618).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(619).Type);
        Assertions.assertEquals(Token.TokenTypes.RETURN, tokens.get(620).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(621).Type);
        Assertions.assertEquals("name",  tokens.get(621).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(622).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(623).Type);
        Assertions.assertEquals("price",  tokens.get(623).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(624).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(625).Type);
        Assertions.assertEquals(Token.TokenTypes.FROM, tokens.get(626).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(627).Type);
        Assertions.assertEquals("diningTable",  tokens.get(627).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(628).Type);
        Assertions.assertEquals(Token.TokenTypes.WHERE, tokens.get(629).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(630).Type);
        Assertions.assertEquals("location",  tokens.get(630).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(631).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(632).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(633).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(634).Type);
        Assertions.assertEquals("name",  tokens.get(634).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(635).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(636).Type);
        Assertions.assertEquals("Viva Cinco De Mayo",  tokens.get(636).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(637).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(638).Type);
        Assertions.assertEquals(Token.TokenTypes.RETURN, tokens.get(639).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(640).Type);
        Assertions.assertEquals("tableNumber",  tokens.get(640).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(641).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(642).Type);
        Assertions.assertEquals("capacity",  tokens.get(642).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(643).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(644).Type);
        Assertions.assertEquals(Token.TokenTypes.FROM, tokens.get(645).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(646).Type);
        Assertions.assertEquals("reservation",  tokens.get(646).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(647).Type);
        Assertions.assertEquals(Token.TokenTypes.WHERE, tokens.get(648).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(649).Type);
        Assertions.assertEquals("who",  tokens.get(649).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(650).Type);
        Assertions.assertEquals(Token.TokenTypes.FINDONE, tokens.get(651).Type);
        Assertions.assertEquals(Token.TokenTypes.OPENPAREN, tokens.get(652).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(653).Type);
        Assertions.assertEquals("name",  tokens.get(653).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.EQUAL, tokens.get(654).Type);
        Assertions.assertEquals(Token.TokenTypes.STRINGLITERAL, tokens.get(655).Type);
        Assertions.assertEquals("Takeem",  tokens.get(655).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.CLOSEPAREN, tokens.get(656).Type);
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(657).Type);
        Assertions.assertEquals(Token.TokenTypes.RETURN, tokens.get(658).Type);
        Assertions.assertEquals(Token.TokenTypes.TABLE, tokens.get(659).Type);
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(660).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(661).Type);
        Assertions.assertEquals("tableNumber",  tokens.get(661).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(662).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(663).Type);
        Assertions.assertEquals("partySize",  tokens.get(663).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.COMMA, tokens.get(664).Type);
        Assertions.assertEquals(Token.TokenTypes.IDENTIFIER, tokens.get(665).Type);
        Assertions.assertEquals("time",  tokens.get(665).Value.orElseThrow());
        Assertions.assertEquals(Token.TokenTypes.NEWLINE, tokens.get(666).Type);
    }
}
