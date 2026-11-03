import java.util.LinkedList;
import java.util.*;
import AST.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test4ParserTest {
    @Test
    public void TestTest4Parser() throws Exception {
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
        var p=new Parser(tokens);
        var top=p.sharQL().orElseThrow();
Assertions.assertEquals("Departments",top.statement.get(0).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(0).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("departmentID",top.statement.get(0).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(0).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(0).create.orElseThrow().field.get(1).name);
Assertions.assertEquals("Buildings",top.statement.get(1).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(1).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("buildingID",top.statement.get(1).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(1).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(1).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(1).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("address",top.statement.get(1).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals("Departments",top.statement.get(1).create.orElseThrow().field.get(3).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(1).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("department",top.statement.get(1).create.orElseThrow().field.get(3).name);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(4).type.enum_);
Assertions.assertEquals(true,top.statement.get(1).create.orElseThrow().field.get(4).type.list);
Assertions.assertEquals("Rooms",top.statement.get(1).create.orElseThrow().field.get(4).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(1).create.orElseThrow().field.get(4).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("rooms",top.statement.get(1).create.orElseThrow().field.get(4).name);
Assertions.assertEquals("Rooms",top.statement.get(2).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(2).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("roomID",top.statement.get(2).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals("Buildings",top.statement.get(2).create.orElseThrow().field.get(1).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(2).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("building",top.statement.get(2).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(2).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("roomNumber",top.statement.get(2).create.orElseThrow().field.get(2).name);
Assertions.assertEquals("Professors",top.statement.get(3).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(3).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("professorID",top.statement.get(3).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(3).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(3).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals("Departments",top.statement.get(3).create.orElseThrow().field.get(2).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(3).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("department",top.statement.get(3).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals("Rooms",top.statement.get(3).create.orElseThrow().field.get(3).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(3).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("office",top.statement.get(3).create.orElseThrow().field.get(3).name);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(4).type.enum_);
Assertions.assertEquals(true,top.statement.get(3).create.orElseThrow().field.get(4).type.list);
Assertions.assertEquals("Courses",top.statement.get(3).create.orElseThrow().field.get(4).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(3).create.orElseThrow().field.get(4).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("courses",top.statement.get(3).create.orElseThrow().field.get(4).name);
Assertions.assertEquals("Majors",top.statement.get(4).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(4).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("majorID",top.statement.get(4).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(4).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(4).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals("Departments",top.statement.get(4).create.orElseThrow().field.get(2).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(4).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("department",top.statement.get(4).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(4).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("abbreviation",top.statement.get(4).create.orElseThrow().field.get(3).name);
Assertions.assertEquals("Courses",top.statement.get(5).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(5).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("courseID",top.statement.get(5).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(5).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(5).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(5).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("description",top.statement.get(5).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(5).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("courseNumber",top.statement.get(5).create.orElseThrow().field.get(3).name);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(4).type.enum_);
Assertions.assertEquals(true,top.statement.get(5).create.orElseThrow().field.get(4).type.list);
Assertions.assertEquals("Majors",top.statement.get(5).create.orElseThrow().field.get(4).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(5).create.orElseThrow().field.get(4).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("majors",top.statement.get(5).create.orElseThrow().field.get(4).name);
Assertions.assertEquals(true,top.statement.get(5).create.orElseThrow().field.get(5).type.enum_);
Assertions.assertEquals("spring",top.statement.get(5).create.orElseThrow().field.get(5).type.enumValue.get(0));
Assertions.assertEquals("summer",top.statement.get(5).create.orElseThrow().field.get(5).type.enumValue.get(1));
Assertions.assertEquals("fall",top.statement.get(5).create.orElseThrow().field.get(5).type.enumValue.get(2));
Assertions.assertEquals("winter",top.statement.get(5).create.orElseThrow().field.get(5).type.enumValue.get(3));
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(5).type.list);
Assertions.assertFalse(top.statement.get(5).create.orElseThrow().field.get(5).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("term",top.statement.get(5).create.orElseThrow().field.get(5).name);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(6).type.enum_);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(6).type.list);
Assertions.assertEquals("Professors",top.statement.get(5).create.orElseThrow().field.get(6).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(5).create.orElseThrow().field.get(6).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("professor",top.statement.get(5).create.orElseThrow().field.get(6).name);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(7).type.enum_);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(7).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(5).create.orElseThrow().field.get(7).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("credits",top.statement.get(5).create.orElseThrow().field.get(7).name);
Assertions.assertEquals("Students",top.statement.get(6).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(6).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("studentID",top.statement.get(6).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(6).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(6).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(true,top.statement.get(6).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals("freshman",top.statement.get(6).create.orElseThrow().field.get(2).type.enumValue.get(0));
Assertions.assertEquals("sophomore",top.statement.get(6).create.orElseThrow().field.get(2).type.enumValue.get(1));
Assertions.assertEquals("junior",top.statement.get(6).create.orElseThrow().field.get(2).type.enumValue.get(2));
Assertions.assertEquals("senior",top.statement.get(6).create.orElseThrow().field.get(2).type.enumValue.get(3));
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(2).type.list);
Assertions.assertFalse(top.statement.get(6).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("year",top.statement.get(6).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(true,top.statement.get(6).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals("Majors",top.statement.get(6).create.orElseThrow().field.get(3).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(6).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("majors",top.statement.get(6).create.orElseThrow().field.get(3).name);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(4).type.enum_);
Assertions.assertEquals(true,top.statement.get(6).create.orElseThrow().field.get(4).type.list);
Assertions.assertEquals("Majors",top.statement.get(6).create.orElseThrow().field.get(4).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(6).create.orElseThrow().field.get(4).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("minors",top.statement.get(6).create.orElseThrow().field.get(4).name);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(5).type.enum_);
Assertions.assertEquals(true,top.statement.get(6).create.orElseThrow().field.get(5).type.list);
Assertions.assertEquals("Courses",top.statement.get(6).create.orElseThrow().field.get(5).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(6).create.orElseThrow().field.get(5).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("courses",top.statement.get(6).create.orElseThrow().field.get(5).name);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(6).type.enum_);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(6).type.list);
Assertions.assertEquals("Rooms",top.statement.get(6).create.orElseThrow().field.get(6).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(6).create.orElseThrow().field.get(6).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("dormRoom",top.statement.get(6).create.orElseThrow().field.get(6).name);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(7).type.enum_);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(7).type.list);
Assertions.assertEquals("ParkingPermits",top.statement.get(6).create.orElseThrow().field.get(7).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(6).create.orElseThrow().field.get(7).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("parkingPermit",top.statement.get(6).create.orElseThrow().field.get(7).name);
Assertions.assertEquals("Vehicles",top.statement.get(7).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(7).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("vehicleID",top.statement.get(7).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(7).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("model",top.statement.get(7).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(7).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("color",top.statement.get(7).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(7).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("licensePlate",top.statement.get(7).create.orElseThrow().field.get(3).name);
Assertions.assertEquals("ParkingLots",top.statement.get(8).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(8).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("lotNumber",top.statement.get(8).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(true,top.statement.get(8).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals("resident",top.statement.get(8).create.orElseThrow().field.get(1).type.enumValue.get(0));
Assertions.assertEquals("commuter",top.statement.get(8).create.orElseThrow().field.get(1).type.enumValue.get(1));
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(1).type.list);
Assertions.assertFalse(top.statement.get(8).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("type",top.statement.get(8).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(8).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("address",top.statement.get(8).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(8).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("numberOfSpots",top.statement.get(8).create.orElseThrow().field.get(3).name);
Assertions.assertEquals("ParkingPermits",top.statement.get(9).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(9).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("permitNumber",top.statement.get(9).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(true,top.statement.get(9).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals("Vehicles",top.statement.get(9).create.orElseThrow().field.get(1).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(9).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("vehicles",top.statement.get(9).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(true,top.statement.get(9).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals("ParkingLots",top.statement.get(9).create.orElseThrow().field.get(2).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(9).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("authorizedParkingLots",top.statement.get(9).create.orElseThrow().field.get(2).name);
Assertions.assertEquals("Departments",top.statement.get(10).insert.orElseThrow().name);
Assertions.assertEquals("1",top.statement.get(10).insert.orElseThrow().values.get(0).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Computer Science",top.statement.get(10).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(10).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("Rooms",top.statement.get(11).insert.orElseThrow().name);
Assertions.assertEquals("1",top.statement.get(11).insert.orElseThrow().values.get(0).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("name",top.statement.get(11).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(11).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("University Administration Building",top.statement.get(11).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("440",top.statement.get(11).insert.orElseThrow().values.get(0).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertEquals("Buildings",top.statement.get(12).insert.orElseThrow().name);
Assertions.assertEquals("1",top.statement.get(12).insert.orElseThrow().values.get(0).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("University Administration Building",top.statement.get(12).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertEquals("University Administration Bldg, Albany, NY 12203",top.statement.get(12).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("Computer Science",top.statement.get(12).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(4).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(4).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(4).name.isPresent());
Assertions.assertEquals("building",top.statement.get(12).insert.orElseThrow().values.get(0).value.get(4).valueList.orElseThrow().findOne.get(0).compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(12).insert.orElseThrow().values.get(0).value.get(4).valueList.orElseThrow().findOne.get(0).compare.get(0).thecompareOp);
Assertions.assertEquals("1",top.statement.get(12).insert.orElseThrow().values.get(0).value.get(4).valueList.orElseThrow().findOne.get(0).compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(4).valueList.orElseThrow().findOne.get(0).compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(4).valueList.orElseThrow().findOne.get(0).compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("Buildings",top.statement.get(13).from.orElseThrow().name);
Assertions.assertEquals("name",top.statement.get(13).from.orElseThrow().where.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(13).from.orElseThrow().where.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(13).from.orElseThrow().where.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("University Administration Building",top.statement.get(13).from.orElseThrow().where.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(13).from.orElseThrow().where.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("Vehicles",top.statement.get(14).from.orElseThrow().name);
Assertions.assertEquals("model",top.statement.get(14).from.orElseThrow().where.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(14).from.orElseThrow().where.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(14).from.orElseThrow().where.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertEquals("RAM",top.statement.get(14).from.orElseThrow().where.orElseThrow().compare.get(0).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(14).from.orElseThrow().where.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("Vehicles",top.statement.get(14).from.orElseThrow().return_.orElseThrow().returnName.get(0).name);
Assertions.assertEquals("color",top.statement.get(14).from.orElseThrow().return_.orElseThrow().returnName.get(0).field.get(0));
        }
        }
