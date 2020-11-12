package ru.netology;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.Main.*;

class MainTest {

    @Test
    void testJsonToListFalse() {

        String testJsonStringFalse = "[\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"country\": \"USA\",\n" +
                "    \"age\": 25\n" +
                "  }\n" +
                "]";

        List<Employee> expectedList = Collections.singletonList(new Employee(1L,"John", "Smith", "USA", 25));
        List<Employee> actualList = jsonToList(testJsonStringFalse);

        assertNotEquals(expectedList, actualList);
    }

    @Test
    void testJsonToListTrue() {

        String testJsonStringTrue = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"country\": \"USA\",\n" +
                "    \"age\": 25\n" +
                "  }\n" +
                "]";

        List<Employee> expectedList = Collections.singletonList(new Employee(1L,"John", "Smith", "USA", 25));
        List<Employee> actualList = jsonToList(testJsonStringTrue);
        assertEquals(expectedList, actualList);
    }

    @Test
    void testListToJsonTrue() {

        List<Employee> testEmployeeList = new ArrayList<>();
        testEmployeeList.add(new Employee(1L,"Jack", "Nixon", "MEXICO", 20));
        testEmployeeList.add(new Employee(3L,"John", "Kennedy", "FRANCE", 10 ));

        String expectedString = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"Jack\",\n" +
                "    \"lastName\": \"Nixon\",\n" +
                "    \"country\": \"MEXICO\",\n" +
                "    \"age\": 20\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 3,\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Kennedy\",\n" +
                "    \"country\": \"FRANCE\",\n" +
                "    \"age\": 10\n" +
                "  }\n" +
                "]";

        String actualString = listToJson(testEmployeeList);

        assertEquals(actualString.strip(), actualString);
    }

    @Test
    void testListToJsonFalse() {

        List<Employee> testEmployeeList = new ArrayList<>();
        testEmployeeList.add(new Employee(1L,"Jack", "Nixon", "MEXICO", 20));
        testEmployeeList.add(new Employee(3L,"John", "Kennedy", "FRANCE", 10 ));

        String expectedString = "[\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"firstName\": \"Jack\",\n" +
                "    \"lastName\": \"Nixon\",\n" +
                "    \"country\": \"MEXICO\",\n" +
                "    \"age\": 20\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 3,\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Kennedy\",\n" +
                "    \"country\": \"FRANCE\",\n" +
                "    \"age\": 10\n" +
                "  }\n" +
                "]";

        String actualString = listToJson(testEmployeeList);

        assertNotEquals(expectedString, actualString);
    }

    @Test
    void parseCSVTrue(){

        String testFileName = "src//test//resources//testdata.csv";
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};

        List<Employee> expectedList = Collections.singletonList(new Employee(1L,"John", "Smith", "USA", 25));
        List<Employee> actualList = parseCSV(columnMapping, testFileName);

        assertEquals(expectedList, actualList);
    }

}