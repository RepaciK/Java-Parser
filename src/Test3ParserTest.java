import java.util.LinkedList;
import java.util.*;
import AST.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test3ParserTest {
    @Test
    public void TestTest3Parser() throws Exception {
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
         "    return name, DOB\n"+
         "\n"+
         "from appointments\n"+
         "    where p=findOne(patientID=002)\n"+
         "    return doc.name, reasonFor, room.roomNum\n"+
         "\n"+
         "from bill\n"+
         "    where paymentType=cash\n"+
         "    return p.name\n"+
         "\n"+
         "from emergencyContact\n"+
         "    where p=findOne(patientID=002)\n"+
         "    return contactName, relationship\n"+
         "\n"+
         "from prescriptions\n"+
         "    where p=findOne(patientID=002)\n"+
         "    return meds.medicineName, dosage\n"+
"";
        var tokens = new Lexer(code).Lex();
        var p=new Parser(tokens);
        var top=p.sharQL().orElseThrow();
Assertions.assertEquals("patient",top.statement.get(0).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(0).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("patientID",top.statement.get(0).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(0).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(0).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(0).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("phoneNum",top.statement.get(0).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(0).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("patEmail",top.statement.get(0).create.orElseThrow().field.get(3).name);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(4).type.enum_);
Assertions.assertEquals(false,top.statement.get(0).create.orElseThrow().field.get(4).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(0).create.orElseThrow().field.get(4).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("DOB",top.statement.get(0).create.orElseThrow().field.get(4).name);
Assertions.assertEquals("prescriptions",top.statement.get(1).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals("medicine",top.statement.get(1).create.orElseThrow().field.get(0).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(1).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("meds",top.statement.get(1).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals("patient",top.statement.get(1).create.orElseThrow().field.get(1).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(1).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("p",top.statement.get(1).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(1).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("datePerscribed",top.statement.get(1).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(1).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("pharmacyAddress",top.statement.get(1).create.orElseThrow().field.get(3).name);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(4).type.enum_);
Assertions.assertEquals(false,top.statement.get(1).create.orElseThrow().field.get(4).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.decimalNumber,top.statement.get(1).create.orElseThrow().field.get(4).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("dosage",top.statement.get(1).create.orElseThrow().field.get(4).name);
Assertions.assertEquals("medicine",top.statement.get(2).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(2).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("medicineID",top.statement.get(2).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(2).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("medicineName",top.statement.get(2).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(2).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(2).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("typeOf",top.statement.get(2).create.orElseThrow().field.get(2).name);
Assertions.assertEquals("doctors",top.statement.get(3).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(3).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("staffID",top.statement.get(3).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(3).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("name",top.statement.get(3).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(3).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("officeNum",top.statement.get(3).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(3).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals("department",top.statement.get(3).create.orElseThrow().field.get(3).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(3).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("d",top.statement.get(3).create.orElseThrow().field.get(3).name);
Assertions.assertEquals("department",top.statement.get(4).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(4).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("departmentNum",top.statement.get(4).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals("doctors",top.statement.get(4).create.orElseThrow().field.get(1).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(4).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("depHead",top.statement.get(4).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(4).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("depName",top.statement.get(4).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(4).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(true,top.statement.get(4).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals("doctors",top.statement.get(4).create.orElseThrow().field.get(3).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(4).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("staffList",top.statement.get(4).create.orElseThrow().field.get(3).name);
Assertions.assertEquals("appointments",top.statement.get(5).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals("patient",top.statement.get(5).create.orElseThrow().field.get(0).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(5).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("p",top.statement.get(5).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals("doctors",top.statement.get(5).create.orElseThrow().field.get(1).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(5).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("doc",top.statement.get(5).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals("rooms",top.statement.get(5).create.orElseThrow().field.get(2).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(5).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("room",top.statement.get(5).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(5).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("startTime",top.statement.get(5).create.orElseThrow().field.get(3).name);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(4).type.enum_);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(4).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(5).create.orElseThrow().field.get(4).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("dateOf",top.statement.get(5).create.orElseThrow().field.get(4).name);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(5).type.enum_);
Assertions.assertEquals(false,top.statement.get(5).create.orElseThrow().field.get(5).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(5).create.orElseThrow().field.get(5).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("reasonFor",top.statement.get(5).create.orElseThrow().field.get(5).name);
Assertions.assertEquals("emergencyContact",top.statement.get(6).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals("patient",top.statement.get(6).create.orElseThrow().field.get(0).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(6).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("p",top.statement.get(6).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(6).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("contactName",top.statement.get(6).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(6).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("contactPhone",top.statement.get(6).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(true,top.statement.get(6).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals("mother",top.statement.get(6).create.orElseThrow().field.get(3).type.enumValue.get(0));
Assertions.assertEquals("father",top.statement.get(6).create.orElseThrow().field.get(3).type.enumValue.get(1));
Assertions.assertEquals("sister",top.statement.get(6).create.orElseThrow().field.get(3).type.enumValue.get(2));
Assertions.assertEquals("brother",top.statement.get(6).create.orElseThrow().field.get(3).type.enumValue.get(3));
Assertions.assertEquals("partner",top.statement.get(6).create.orElseThrow().field.get(3).type.enumValue.get(4));
Assertions.assertEquals("child",top.statement.get(6).create.orElseThrow().field.get(3).type.enumValue.get(5));
Assertions.assertEquals("other",top.statement.get(6).create.orElseThrow().field.get(3).type.enumValue.get(6));
Assertions.assertEquals(false,top.statement.get(6).create.orElseThrow().field.get(3).type.list);
Assertions.assertFalse(top.statement.get(6).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("relationship",top.statement.get(6).create.orElseThrow().field.get(3).name);
Assertions.assertEquals("bill",top.statement.get(7).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals("patient",top.statement.get(7).create.orElseThrow().field.get(0).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(7).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("p",top.statement.get(7).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.decimalNumber,top.statement.get(7).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("totalOwed",top.statement.get(7).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.decimalNumber,top.statement.get(7).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("amountPaid",top.statement.get(7).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals("insuranceInfo",top.statement.get(7).create.orElseThrow().field.get(3).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(7).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("insure",top.statement.get(7).create.orElseThrow().field.get(3).name);
Assertions.assertEquals(true,top.statement.get(7).create.orElseThrow().field.get(4).type.enum_);
Assertions.assertEquals("card",top.statement.get(7).create.orElseThrow().field.get(4).type.enumValue.get(0));
Assertions.assertEquals("cash",top.statement.get(7).create.orElseThrow().field.get(4).type.enumValue.get(1));
Assertions.assertEquals("electronic",top.statement.get(7).create.orElseThrow().field.get(4).type.enumValue.get(2));
Assertions.assertEquals("check",top.statement.get(7).create.orElseThrow().field.get(4).type.enumValue.get(3));
Assertions.assertEquals(false,top.statement.get(7).create.orElseThrow().field.get(4).type.list);
Assertions.assertFalse(top.statement.get(7).create.orElseThrow().field.get(4).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("paymentType",top.statement.get(7).create.orElseThrow().field.get(4).name);
Assertions.assertEquals("insuranceInfo",top.statement.get(8).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals("patient",top.statement.get(8).create.orElseThrow().field.get(0).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(8).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("p",top.statement.get(8).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(8).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("insuranceID",top.statement.get(8).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(8).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("insuranceProvider",top.statement.get(8).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(8).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.decimalNumber,top.statement.get(8).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("insuranceCoverage",top.statement.get(8).create.orElseThrow().field.get(3).name);
Assertions.assertEquals("payroll",top.statement.get(9).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals("doctor",top.statement.get(9).create.orElseThrow().field.get(0).type.tableName.get(0));
Assertions.assertFalse(top.statement.get(9).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.isPresent());
Assertions.assertEquals("doc",top.statement.get(9).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.decimalNumber,top.statement.get(9).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("salary",top.statement.get(9).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.decimalNumber,top.statement.get(9).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("bonus",top.statement.get(9).create.orElseThrow().field.get(2).name);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(3).type.enum_);
Assertions.assertEquals(false,top.statement.get(9).create.orElseThrow().field.get(3).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.decimalNumber,top.statement.get(9).create.orElseThrow().field.get(3).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("overtime",top.statement.get(9).create.orElseThrow().field.get(3).name);
Assertions.assertEquals("rooms",top.statement.get(10).create.orElseThrow().name);
Assertions.assertEquals(false,top.statement.get(10).create.orElseThrow().field.get(0).type.enum_);
Assertions.assertEquals(false,top.statement.get(10).create.orElseThrow().field.get(0).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.wholeNumber,top.statement.get(10).create.orElseThrow().field.get(0).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("roomNum",top.statement.get(10).create.orElseThrow().field.get(0).name);
Assertions.assertEquals(false,top.statement.get(10).create.orElseThrow().field.get(1).type.enum_);
Assertions.assertEquals(false,top.statement.get(10).create.orElseThrow().field.get(1).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(10).create.orElseThrow().field.get(1).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("admitanceTime",top.statement.get(10).create.orElseThrow().field.get(1).name);
Assertions.assertEquals(false,top.statement.get(10).create.orElseThrow().field.get(2).type.enum_);
Assertions.assertEquals(false,top.statement.get(10).create.orElseThrow().field.get(2).type.list);
Assertions.assertEquals(string_ORwholeNumberORdecimalNumber.string_,top.statement.get(10).create.orElseThrow().field.get(2).type.thestring_ORwholeNumberORdecimalNumber.orElseThrow());
Assertions.assertEquals("dischargeTime",top.statement.get(10).create.orElseThrow().field.get(2).name);
Assertions.assertEquals("patient",top.statement.get(11).insert.orElseThrow().name);
Assertions.assertEquals("001",top.statement.get(11).insert.orElseThrow().values.get(0).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Urahara Kisuke",top.statement.get(11).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertEquals("(518) 699-9999",top.statement.get(11).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("hbuthShopkeeper@gmail.com",top.statement.get(11).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(4).NUMBER.isPresent());
Assertions.assertEquals("7/23/1200",top.statement.get(11).insert.orElseThrow().values.get(0).value.get(4).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(0).value.get(4).name.isPresent());
Assertions.assertEquals("002",top.statement.get(11).insert.orElseThrow().values.get(1).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Aizen Sosuke",top.statement.get(11).insert.orElseThrow().values.get(1).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(2).NUMBER.isPresent());
Assertions.assertEquals("(518) 123-4444",top.statement.get(11).insert.orElseThrow().values.get(1).value.get(2).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("theRealHogyoku@gmail.com",top.statement.get(11).insert.orElseThrow().values.get(1).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(4).NUMBER.isPresent());
Assertions.assertEquals("1/1/1000",top.statement.get(11).insert.orElseThrow().values.get(1).value.get(4).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(11).insert.orElseThrow().values.get(1).value.get(4).name.isPresent());
Assertions.assertEquals("medicine",top.statement.get(12).insert.orElseThrow().name);
Assertions.assertEquals("100",top.statement.get(12).insert.orElseThrow().values.get(0).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("ReiatsuIVPlus",top.statement.get(12).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertEquals("Injection twice a day every 12 hours for about 20 thousand years",top.statement.get(12).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(12).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertEquals("prescriptions",top.statement.get(13).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("medicineID",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(13).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("100",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("patientID",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("002",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertEquals("08/28/2026",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("555 Division Road, Soul Society, New York",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertEquals("100.9",top.statement.get(13).insert.orElseThrow().values.get(0).value.get(4).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(4).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(13).insert.orElseThrow().values.get(0).value.get(4).name.isPresent());
Assertions.assertEquals("doctors",top.statement.get(14).insert.orElseThrow().name);
Assertions.assertEquals("222",top.statement.get(14).insert.orElseThrow().values.get(0).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Dr. Mayuri Kurotsuchi",top.statement.get(14).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("12",top.statement.get(14).insert.orElseThrow().values.get(0).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertEquals("departmentNum",top.statement.get(14).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(14).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("1212",top.statement.get(14).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(14).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("department",top.statement.get(15).insert.orElseThrow().name);
Assertions.assertEquals("1212",top.statement.get(15).insert.orElseThrow().values.get(0).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("staffID",top.statement.get(15).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(15).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("222",top.statement.get(15).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertEquals("The 12th Division",top.statement.get(15).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(15).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertEquals("rooms",top.statement.get(16).insert.orElseThrow().name);
Assertions.assertEquals("12",top.statement.get(16).insert.orElseThrow().values.get(0).value.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("6:35",top.statement.get(16).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(16).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertEquals("null",top.statement.get(16).insert.orElseThrow().values.get(0).value.get(2).name.orElseThrow());
Assertions.assertEquals("appointments",top.statement.get(17).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("patientID",top.statement.get(17).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(17).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("002",top.statement.get(17).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("staffID",top.statement.get(17).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(17).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("222",top.statement.get(17).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(1).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertEquals("roomNum",top.statement.get(17).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(17).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("12",top.statement.get(17).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(2).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertEquals("6:30 PM",top.statement.get(17).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(4).NUMBER.isPresent());
Assertions.assertEquals("8/28/26",top.statement.get(17).insert.orElseThrow().values.get(0).value.get(4).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(4).name.isPresent());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(5).NUMBER.isPresent());
Assertions.assertEquals("Feeling faint",top.statement.get(17).insert.orElseThrow().values.get(0).value.get(5).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(17).insert.orElseThrow().values.get(0).value.get(5).name.isPresent());
Assertions.assertEquals("emergencyContact",top.statement.get(18).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("patientID",top.statement.get(18).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(18).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("002",top.statement.get(18).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(1).NUMBER.isPresent());
Assertions.assertEquals("Gin Ichimaru",top.statement.get(18).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertEquals("(518) 123-4446",top.statement.get(18).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(18).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertEquals("other",top.statement.get(18).insert.orElseThrow().values.get(0).value.get(3).name.orElseThrow());
Assertions.assertEquals("payroll",top.statement.get(19).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("staffID",top.statement.get(19).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(19).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("222",top.statement.get(19).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("1000000.90",top.statement.get(19).insert.orElseThrow().values.get(0).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("0.00",top.statement.get(19).insert.orElseThrow().values.get(0).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertEquals("0.00",top.statement.get(19).insert.orElseThrow().values.get(0).value.get(3).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(19).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertEquals("insuranceInfo",top.statement.get(20).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("patientID",top.statement.get(20).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(20).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("002",top.statement.get(20).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("000555666777",top.statement.get(20).insert.orElseThrow().values.get(0).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(0).value.get(2).NUMBER.isPresent());
Assertions.assertEquals("SeireiteiUnited",top.statement.get(20).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.orElseThrow());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertEquals("500000.00",top.statement.get(20).insert.orElseThrow().values.get(0).value.get(3).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(20).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertEquals("bill",top.statement.get(21).insert.orElseThrow().name);
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(0).name.isPresent());
Assertions.assertEquals("patientID",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(21).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("002",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("10.00",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(1).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(1).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(1).name.isPresent());
Assertions.assertEquals("10.00",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(2).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(2).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(2).name.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).name.isPresent());
Assertions.assertEquals("insuranceID",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("000555666777",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(3).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(4).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(21).insert.orElseThrow().values.get(0).value.get(4).STRINGLITERAL.isPresent());
Assertions.assertEquals("cash",top.statement.get(21).insert.orElseThrow().values.get(0).value.get(4).name.orElseThrow());
Assertions.assertEquals("patient",top.statement.get(22).from.orElseThrow().name);
Assertions.assertEquals("name",top.statement.get(22).from.orElseThrow().return_.orElseThrow().returnName.get(0).name);
Assertions.assertEquals("DOB",top.statement.get(22).from.orElseThrow().return_.orElseThrow().returnName.get(1).name);
Assertions.assertEquals("appointments",top.statement.get(23).from.orElseThrow().name);
Assertions.assertEquals("p",top.statement.get(23).from.orElseThrow().where.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(23).from.orElseThrow().where.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(23).from.orElseThrow().where.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(23).from.orElseThrow().where.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(23).from.orElseThrow().where.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("patientID",top.statement.get(23).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(23).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("002",top.statement.get(23).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(23).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(23).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("doc",top.statement.get(23).from.orElseThrow().return_.orElseThrow().returnName.get(0).name);
Assertions.assertEquals("name",top.statement.get(23).from.orElseThrow().return_.orElseThrow().returnName.get(0).field.get(0));
Assertions.assertEquals("reasonFor",top.statement.get(23).from.orElseThrow().return_.orElseThrow().returnName.get(1).name);
Assertions.assertEquals("room",top.statement.get(23).from.orElseThrow().return_.orElseThrow().returnName.get(2).name);
Assertions.assertEquals("roomNum",top.statement.get(23).from.orElseThrow().return_.orElseThrow().returnName.get(2).field.get(0));
Assertions.assertEquals("bill",top.statement.get(24).from.orElseThrow().name);
Assertions.assertEquals("paymentType",top.statement.get(24).from.orElseThrow().where.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(24).from.orElseThrow().where.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(24).from.orElseThrow().where.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(24).from.orElseThrow().where.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertEquals("cash",top.statement.get(24).from.orElseThrow().where.orElseThrow().compare.get(0).IDENTIFIER.orElseThrow());
Assertions.assertEquals("p",top.statement.get(24).from.orElseThrow().return_.orElseThrow().returnName.get(0).name);
Assertions.assertEquals("name",top.statement.get(24).from.orElseThrow().return_.orElseThrow().returnName.get(0).field.get(0));
Assertions.assertEquals("emergencyContact",top.statement.get(25).from.orElseThrow().name);
Assertions.assertEquals("p",top.statement.get(25).from.orElseThrow().where.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(25).from.orElseThrow().where.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(25).from.orElseThrow().where.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(25).from.orElseThrow().where.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(25).from.orElseThrow().where.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("patientID",top.statement.get(25).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(25).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("002",top.statement.get(25).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(25).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(25).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("contactName",top.statement.get(25).from.orElseThrow().return_.orElseThrow().returnName.get(0).name);
Assertions.assertEquals("relationship",top.statement.get(25).from.orElseThrow().return_.orElseThrow().returnName.get(1).name);
Assertions.assertEquals("prescriptions",top.statement.get(26).from.orElseThrow().name);
Assertions.assertEquals("p",top.statement.get(26).from.orElseThrow().where.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(26).from.orElseThrow().where.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertFalse(top.statement.get(26).from.orElseThrow().where.orElseThrow().compare.get(0).NUMBER.isPresent());
Assertions.assertFalse(top.statement.get(26).from.orElseThrow().where.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(26).from.orElseThrow().where.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("patientID",top.statement.get(26).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).left);
Assertions.assertEquals(compareOp.equal,top.statement.get(26).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).thecompareOp);
Assertions.assertEquals("002",top.statement.get(26).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).NUMBER.orElseThrow());
Assertions.assertFalse(top.statement.get(26).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).STRINGLITERAL.isPresent());
Assertions.assertFalse(top.statement.get(26).from.orElseThrow().where.orElseThrow().compare.get(0).findOne.orElseThrow().compare.get(0).IDENTIFIER.isPresent());
Assertions.assertEquals("meds",top.statement.get(26).from.orElseThrow().return_.orElseThrow().returnName.get(0).name);
Assertions.assertEquals("medicineName",top.statement.get(26).from.orElseThrow().return_.orElseThrow().returnName.get(0).field.get(0));
Assertions.assertEquals("dosage",top.statement.get(26).from.orElseThrow().return_.orElseThrow().returnName.get(1).name);
        }
        }
