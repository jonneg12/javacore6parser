package ru.netology;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.*;
import lombok.SneakyThrows;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};

        String csvFileName = "data.csv";
        String xmlFileName = "data.xml";
        String jsonFileName = "data.json";
        String jsonFileName2 = "data2.json";

        //task 1
        // read and create list of Employees from  csv file
        List<Employee> employeeListFromCSV = parseCSV(columnMapping, csvFileName);

        // create string with all Employees from list in json
        String jsonString = listToJson(employeeListFromCSV);

        // write json string to file data.json
        writeJsonToFile(jsonString, jsonFileName);

        //task 2
        // read an create list of Employees from xml file
        List<Employee> employeeListFromXML = parseXML(xmlFileName);

        // create string with all Employees from list in json
        String jsonString2 = listToJson(employeeListFromXML);

        // write json string to file data2.json
        writeJsonToFile(jsonString2, jsonFileName2);

        //task *
        String json = readString(jsonFileName);

        List<Employee> EmployeeList = jsonToList(json);

        for (Employee employee : EmployeeList) {
            System.out.println(employee);
        }
    }

    @SneakyThrows
    protected static List<Employee> jsonToList(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<Employee>>(){}.getType());
    }

    protected static String readString(String fileName) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @SneakyThrows
    protected static List<Employee> parseXML(String xmlFileName) {
        List<Employee> employeeList = new ArrayList<>();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(xmlFileName));

            NodeList employeeNodeList = doc.getElementsByTagName("employee");

            for (int i = 0; i < employeeNodeList.getLength(); i++) {
                if (employeeNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element employeeElement = (Element)employeeNodeList.item(i);
                    Employee employee = new Employee();
                    NodeList childNodes = employeeElement.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            Element childElement = (Element)childNodes.item(j);
                            switch (childElement.getNodeName()) {
                                case "id": {
                                    employee.setId(Long.valueOf(childElement.getTextContent()));
                                    break;
                                }
                                case "firstName": {
                                    employee.setFirstName(childElement.getTextContent());
                                    break;
                                }
                                case "lastName": {
                                    employee.setLastName(childElement.getTextContent());
                                    break;
                                }
                                case "country": {
                                    employee.setCountry(childElement.getTextContent());
                                    break;
                                }
                                case "age": {
                                    employee.setAge(Integer.parseInt(childElement.getTextContent()));
                                    break;
                                }
                            }
                        }
                    }
                    employeeList.add(employee);
                }
            }
        return employeeList;
    }

    protected static void writeJsonToFile(String jsonString, String jsonFileName) {
        try (FileWriter fw = new FileWriter(jsonFileName)) {
            fw.write(jsonString);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static String listToJson(List<Employee> employeeList) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(employeeList);
        return json;
    }

    protected static List<Employee> parseCSV(String[] columnMapping, String fileName) {

        List<Employee> employeeList = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);

            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(csvReader)
                    .withMappingStrategy(strategy)
                    .build();
            employeeList = csv.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}