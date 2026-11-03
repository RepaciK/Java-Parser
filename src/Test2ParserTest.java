import java.util.LinkedList;
import java.util.*;
import AST.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test2ParserTest {
    @Test
    public void TestTest2Parser() throws Exception {
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
         "    diningTable reservationTable\n"+
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
         "    where servedAt=findOne(name=\"Sarah’s Pizzeria\")\n"+
         "    return name, price\n"+
         "\n"+
         "from diningTable\n"+
         "    where location=findOne(name=\"Viva Cinco De Mayo\")\n"+
         "    return tableNumber, capacity\n"+
         "\n"+
         "from reservation\n"+
         "    where who=findOne(name=\"Takeem\")\n"+
         "    return reservationTable, tableNumber, partySize, time\n"+
"";
        var tokens = new Lexer(code).Lex();
        var p=new Parser(tokens);
        var top=p.sharQL().orElseThrow();
Assertions.assertEquals("restaurant",top.statement.get(0).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(0).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(0).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(0).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("address",top.statement.get(0).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(true,top.statement.get(0).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals("italian",top.statement.get(0).create.orElseThrow().field.get(2).type.enumValue.get(0));
Assertions.assertEquals("mexican",top.statement.get(0).create.orElseThrow().field.get(2).type.enumValue.get(1));
Assertions.assertEquals("thai",top.statement.get(0).create.orElseThrow().field.get(2).type.enumValue.get(2));
Assertions.assertEquals("greek",top.statement.get(0).create.orElseThrow().field.get(2).type.enumValue.get(3));
Assertions.assertEquals("japanese",top.statement.get(0).create.orElseThrow().field.get(2).type.enumValue.get(4));
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(2).type.list);
Assertions.assertFalse(top.statement.get(0).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("cuisine",top.statement.get(0).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.decimalNumber,top.statement.get(0).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("rating",top.statement.get(0).create.orElseThrow().field.get(3).name);
Assertions.assertEquals("menuItem",top.statement.get(1).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(1).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(1).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.decimalNumber,top.statement.get(1).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("price",top.statement.get(1).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(true,top.statement.get(1).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals("ingredient",top.statement.get(1).create.orElseThrow().field.get(2).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(1).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("ingredients",top.statement.get(1).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals("restaurant",top.statement.get(1).create.orElseThrow().field.get(3).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(1).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("servedAt",top.statement.get(1).create.orElseThrow().field.get(3).name);
Assertions.assertEquals("ingredient",top.statement.get(2).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(2).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(2).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(true,top.statement.get(2).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals("none",top.statement.get(2).create.orElseThrow().field.get(1).type.enumValue.get(0));
Assertions.assertEquals("diary",top.statement.get(2).create.orElseThrow().field.get(1).type.enumValue.get(1));
Assertions.assertEquals("gluten",top.statement.get(2).create.orElseThrow().field.get(1).type.enumValue.get(2));
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(1).type.list);
Assertions.assertFalse(top.statement.get(2).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("allergen",top.statement.get(2).create.orElseThrow().field.get(1).name);
Assertions.assertEquals("order",top.statement.get(3).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals("customer",top.statement.get(3).create.orElseThrow().field.get(0).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(3).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("who",top.statement.get(3).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals("restaurant",top.statement.get(3).create.orElseThrow().field.get(1).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(3).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("placedAt",top.statement.get(3).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(true,top.statement.get(3).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals("orderItem",top.statement.get(3).create.orElseThrow().field.get(2).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(3).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("items",top.statement.get(3).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(true,top.statement.get(3).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals("placed",top.statement.get(3).create.orElseThrow().field.get(3).type.enumValue.get(0));
Assertions.assertEquals("preparing",top.statement.get(3).create.orElseThrow().field.get(3).type.enumValue.get(1));
Assertions.assertEquals("outForDelivery",top.statement.get(3).create.orElseThrow().field.get(3).type.enumValue.get(2));
Assertions.assertEquals("delivered",top.statement.get(3).create.orElseThrow().field.get(3).type.enumValue.get(3));
Assertions.assertEquals("cancelled",top.statement.get(3).create.orElseThrow().field.get(3).type.enumValue.get(4));
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(3).type.list);
Assertions.assertFalse(top.statement.get(3).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("status",top.statement.get(3).create.orElseThrow().field.get(3).name);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(4).type.enum_);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(4).type.list);
Assertions.assertEquals("deliveryDriver",top.statement.get(3).create.orElseThrow().field.get(4).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(3).create.orElseThrow().field.get(4).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("driver",top.statement.get(3).create.orElseThrow().field.get(4).name);
Assertions.assertEquals("reservation",top.statement.get(4).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals("customer",top.statement.get(4).create.orElseThrow().field.get(0).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(4).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("who",top.statement.get(4).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals("diningTable",top.statement.get(4).create.orElseThrow().field.get(1).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(4).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("reservationTable",top.statement.get(4).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(4).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("partySize",top.statement.get(4).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(4).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("time",top.statement.get(4).create.orElseThrow().field.get(3).name);
Assertions.assertEquals("customer",top.statement.get(5).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(5).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(5).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(5).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("address",top.statement.get(5).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(5).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("phone",top.statement.get(5).create.orElseThrow().field.get(2).name);
Assertions.assertEquals("review",top.statement.get(6).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals("customer",top.statement.get(6).create.orElseThrow().field.get(0).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(6).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("who",top.statement.get(6).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals("restaurant",top.statement.get(6).create.orElseThrow().field.get(1).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(6).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("about",top.statement.get(6).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(6).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("stars",top.statement.get(6).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(6).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("comment",top.statement.get(6).create.orElseThrow().field.get(3).name);
Assertions.assertEquals("orderItem",top.statement.get(7).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals("menuItem",top.statement.get(7).create.orElseThrow().field.get(0).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(7).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("item",top.statement.get(7).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(7).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("quantity",top.statement.get(7).create.orElseThrow().field.get(1).name);
Assertions.assertEquals("diningTable",top.statement.get(8).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals("restaurant",top.statement.get(8).create.orElseThrow().field.get(0).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(8).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("location",top.statement.get(8).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(8).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("tableNumber",top.statement.get(8).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(8).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("capacity",top.statement.get(8).create.orElseThrow().field.get(2).name);
Assertions.assertEquals("deliveryDriver",top.statement.get(9).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(9).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(9).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(9).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("vehicle",top.statement.get(9).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.decimalNumber,top.statement.get(9).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("rating",top.statement.get(9).create.orElseThrow().field.get(2).name);
Assertions.assertEquals("restaurant",top.statement.get(10).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Sarah’s Pizzeria",top.statement.get(10).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("123 Springfield Blvd, New York, NY",top.statement.get(10).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertEquals("italian",top.statement.get(10).insert.orElseThrow().values.get(0).value.get(2).name.orElseThrow());
Assertions.assertEquals("4.0",top.statement.get(10).insert.orElseThrow().values.get(0).value.get(3).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Viva Cinco De Mayo",top.statement.get(10).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("456 Linden Blvd, New York, NY",top.statement.get(10).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(1).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertEquals("mexican",top.statement.get(10).insert.orElseThrow().values.get(1).value.get(2).name.orElseThrow());
Assertions.assertEquals("4.4",top.statement.get(10).insert.orElseThrow().values.get(1).value.get(3).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(1).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(1).value.get(3).name.isPresent());
Assertions.assertEquals("menuItem",top.statement.get(11).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Pizza",top.statement.get(11).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("12.99",top.statement.get(11).insert.orElseThrow().values.get(0).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertEquals("name",top.statement.get(11).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(11).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Dough",top.statement.get(11).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("name",top.statement.get(11).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(11).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Mozzarella",top.statement.get(11).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertEquals("name",top.statement.get(11).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(11).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Sarah’s Pizzeria",top.statement.get(11).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Burger",top.statement.get(11).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertEquals("8.99",top.statement.get(11).insert.orElseThrow().values.get(1).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertEquals("name",top.statement.get(11).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(11).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Bun",top.statement.get(11).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("name",top.statement.get(11).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(11).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Beef Patty",top.statement.get(11).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(3).name.isPresent());
Assertions.assertEquals("name",top.statement.get(11).insert.orElseThrow().values.get(1).value.get(3).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(11).insert.orElseThrow().values.get(1).value.get(3).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(3).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Sarah’s Pizzeria",top.statement.get(11).insert.orElseThrow().values.get(1).value.get(3).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(3).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(2).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Tacos",top.statement.get(11).insert.orElseThrow().values.get(2).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(2).value.get(0).name.isPresent());
Assertions.assertEquals("9.99",top.statement.get(11).insert.orElseThrow().values.get(2).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(2).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(2).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(2).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(2).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(2).value.get(2).name.isPresent());
Assertions.assertEquals("name",top.statement.get(11).insert.orElseThrow().values.get(2).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(11).insert.orElseThrow().values.get(2).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(2).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Tortilla",top.statement.get(11).insert.orElseThrow().values.get(2).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(2).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("name",top.statement.get(11).insert.orElseThrow().values.get(2).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(11).insert.orElseThrow().values.get(2).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(2).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Salsa",top.statement.get(11).insert.orElseThrow().values.get(2).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(2).value.get(2).valueList.orElseThrow().findOne.get(1).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(2).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(2).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(2).value.get(3).name.isPresent());
Assertions.assertEquals("name",top.statement.get(11).insert.orElseThrow().values.get(2).value.get(3).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(11).insert.orElseThrow().values.get(2).value.get(3).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(2).value.get(3).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Viva Cinco De Mayo",top.statement.get(11).insert.orElseThrow().values.get(2).value.get(3).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(2).value.get(3).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("ingredient",top.statement.get(12).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Dough",top.statement.get(12).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertEquals("gluten",top.statement.get(12).insert.orElseThrow().values.get(0).value.get(1).name.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Mozzarella",top.statement.get(12).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertEquals("diary",top.statement.get(12).insert.orElseThrow().values.get(1).value.get(1).name.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(2).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Beef Patty",top.statement.get(12).insert.orElseThrow().values.get(2).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(2).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(2).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(2).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertEquals("none",top.statement.get(12).insert.orElseThrow().values.get(2).value.get(1).name.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(3).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Bun",top.statement.get(12).insert.orElseThrow().values.get(3).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(3).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(3).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(3).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertEquals("gluten",top.statement.get(12).insert.orElseThrow().values.get(3).value.get(1).name.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(4).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Tortilla",top.statement.get(12).insert.orElseThrow().values.get(4).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(4).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(4).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(4).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertEquals("gluten",top.statement.get(12).insert.orElseThrow().values.get(4).value.get(1).name.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(5).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Salsa",top.statement.get(12).insert.orElseThrow().values.get(5).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(5).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(5).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(5).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertEquals("none",top.statement.get(12).insert.orElseThrow().values.get(5).value.get(1).name.orElseThrow());
Assertions.assertEquals("order",top.statement.get(13).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(13).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Takeem",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Sarah’s Pizzeria",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertEquals("quantity",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(13).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).thecompareOp);
Assertions.assertEquals("5",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertEquals("delivered",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(3).name.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(4).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(4).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(4).name.isPresent());
Assertions.assertEquals("name",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(4).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(13).insert.orElseThrow().values.get(0).value.get(4).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(4).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Elizabeth",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(4).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(4).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(13).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(13).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Francis",top.statement.get(13).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(13).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(13).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Viva Cinco De Mayo",top.statement.get(13).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertEquals("quanity",top.statement.get(13).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(13).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).thecompareOp);
Assertions.assertEquals("6",top.statement.get(13).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(2).valueList.orElseThrow().findOne.get(0).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertEquals("preparing",top.statement.get(13).insert.orElseThrow().values.get(1).value.get(3).name.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(4).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(4).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(4).name.isPresent());
Assertions.assertEquals("name",top.statement.get(13).insert.orElseThrow().values.get(1).value.get(4).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(13).insert.orElseThrow().values.get(1).value.get(4).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(4).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Lijah",top.statement.get(13).insert.orElseThrow().values.get(1).value.get(4).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(1).value.get(4).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("reservation",top.statement.get(14).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(14).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(14).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Takeem",top.statement.get(14).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("tableNumber",top.statement.get(14).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(14).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("1",top.statement.get(14).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("capacity",top.statement.get(14).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(1).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(14).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(1).thecompareOp);
Assertions.assertEquals("6",top.statement.get(14).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(1).IDENTIFIER.isPresent());
Assertions.assertEquals("5",top.statement.get(14).insert.orElseThrow().values.get(0).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("8:00 PM",top.statement.get(14).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(14).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(14).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Francis",top.statement.get(14).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertEquals("tableNumber",top.statement.get(14).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(14).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("3",top.statement.get(14).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("capacity",top.statement.get(14).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(1).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(14).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(1).thecompareOp);
Assertions.assertEquals("4",top.statement.get(14).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(1).IDENTIFIER.isPresent());
Assertions.assertEquals("4",top.statement.get(14).insert.orElseThrow().values.get(1).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("10:00 PM",top.statement.get(14).insert.orElseThrow().values.get(1).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(1).value.get(3).name.isPresent());
Assertions.assertEquals("customer",top.statement.get(15).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Takeem",top.statement.get(15).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("789 Sutphin Blvd",top.statement.get(15).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertEquals("656-778-2245",top.statement.get(15).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Francis",top.statement.get(15).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("632 Foch Blvd",top.statement.get(15).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(2).NUMBER.isPresent());
Assertions.assertEquals("434-776-6654",top.statement.get(15).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertEquals("review",top.statement.get(16).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(16).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(16).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Takeem",top.statement.get(16).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(16).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(16).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Sarah’s Pizzeria",top.statement.get(16).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("4",top.statement.get(16).insert.orElseThrow().values.get(0).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("Tasted good",top.statement.get(16).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(16).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(16).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Francis",top.statement.get(16).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(16).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(16).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Viva Cinco De Mayo",top.statement.get(16).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("4",top.statement.get(16).insert.orElseThrow().values.get(1).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("Fast delivery and the food had a tasty spice to it!",top.statement.get(16).insert.orElseThrow().values.get(1).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(1).value.get(3).name.isPresent());
Assertions.assertEquals("orderItem",top.statement.get(17).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(17).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(17).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Pizza",top.statement.get(17).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("5",top.statement.get(17).insert.orElseThrow().values.get(0).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(17).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(17).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Tacos",top.statement.get(17).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("6",top.statement.get(17).insert.orElseThrow().values.get(1).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertEquals("diningTable",top.statement.get(18).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(18).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(18).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Sarah’s Pizzeria",top.statement.get(18).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("2",top.statement.get(18).insert.orElseThrow().values.get(0).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("4",top.statement.get(18).insert.orElseThrow().values.get(0).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(18).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(18).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Viva Cinco De Mayo",top.statement.get(18).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(1).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("3",top.statement.get(18).insert.orElseThrow().values.get(1).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertEquals("4",top.statement.get(18).insert.orElseThrow().values.get(1).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(2).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(2).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(2).value.get(0).name.isPresent());
Assertions.assertEquals("name",top.statement.get(18).insert.orElseThrow().values.get(2).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(18).insert.orElseThrow().values.get(2).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(2).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Sarah’s Pizzeria",top.statement.get(18).insert.orElseThrow().values.get(2).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(2).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("6",top.statement.get(18).insert.orElseThrow().values.get(2).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(2).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(2).value.get(1).name.isPresent());
Assertions.assertEquals("6",top.statement.get(18).insert.orElseThrow().values.get(2).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(2).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(2).value.get(2).name.isPresent());
Assertions.assertEquals("deliveryDriver",top.statement.get(19).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Elizabeth",top.statement.get(19).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Honda Civic",top.statement.get(19).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("5.0",top.statement.get(19).insert.orElseThrow().values.get(0).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(1).value.get(0).NUMBER.isPresent());
Assertions.assertEquals("Lijah",top.statement.get(19).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Nissan Versa",top.statement.get(19).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertEquals("4.8",top.statement.get(19).insert.orElseThrow().values.get(1).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertEquals("menuItem",top.statement.get(20).from.orElseThrow().name);
Assertions.assertEquals("servedAt",top.statement.get(20).from.orElseThrow().where.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(20).from.orElseThrow().where.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(20).from.orElseThrow().where.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(20).from.orElseThrow().where.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(20).from.orElseThrow().where.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("name",top.statement.get(20).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(20).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(20).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Sarah’s Pizzeria",top.statement.get(20).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(20).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("name",top.statement.get(20).from.orElseThrow().return_.orElseThrow().returnName.get(0).name);
Assertions.assertEquals("price",top.statement.get(20).from.orElseThrow().return_.orElseThrow().returnName.get(1).name);
Assertions.assertEquals("diningTable",top.statement.get(21).from.orElseThrow().name);
Assertions.assertEquals("location",top.statement.get(21).from.orElseThrow().where.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(21).from.orElseThrow().where.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(21).from.orElseThrow().where.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(21).from.orElseThrow().where.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).from.orElseThrow().where.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("name",top.statement.get(21).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(21).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(21).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Viva Cinco De Mayo",top.statement.get(21).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(21).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("tableNumber",top.statement.get(21).from.orElseThrow().return_.orElseThrow().returnName.get(0).name);
Assertions.assertEquals("capacity",top.statement.get(21).from.orElseThrow().return_.orElseThrow().returnName.get(1).name);
Assertions.assertEquals("reservation",top.statement.get(22).from.orElseThrow().name);
Assertions.assertEquals("who",top.statement.get(22).from.orElseThrow().where.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(22).from.orElseThrow().where.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(22).from.orElseThrow().where.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(22).from.orElseThrow().where.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(22).from.orElseThrow().where.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("name",top.statement.get(22).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(22).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(22).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("Takeem",top.statement.get(22).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(22).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("reservationTable",top.statement.get(22).from.orElseThrow().return_.orElseThrow().returnName.get(0).name);
Assertions.assertEquals("tableNumber",top.statement.get(22).from.orElseThrow().return_.orElseThrow().returnName.get(1).name);
Assertions.assertEquals("partySize",top.statement.get(22).from.orElseThrow().return_.orElseThrow().returnName.get(2).name);
Assertions.assertEquals("time",top.statement.get(22).from.orElseThrow().return_.orElseThrow().returnName.get(3).name);
        }
        }
