package com.cardetailcheck.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    /**
     * Method to match the Car Reg pattern from the list of File lines(i.e. extracted from the input file) and
     *  returning the List of Valid Car Regs in proper format
     *
     * @param lines
     * @return List<String
     */
    public static List<String> getCarRegsFromList(List<String> lines){
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("[A-Z]{2}\\d{2}\\s+[A-Z]{3}|[A-Z]{2}\\d{2}[A-Z]{3}");
        for(String line : lines) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                list.add(matcher.group().replaceAll(" ",""));
            }
        }
        return list;
    }

    /**
     * Method to Get the Each Reg Map from the output file To match the Site Car Details in Then Step
     *
     * @param filePath
     * @return Map<String,Map<String,String>>
     */
    public static Map<String, Map<String,String>> getListOfRecordsMapFromCSV(String filePath){
        Map<String,Map<String,String>> recordsMapList = new HashMap<>();
        try {
            try(BufferedReader bfReader = new BufferedReader(new FileReader(new File(filePath)))){
                String line = bfReader.readLine();
                List<String> headings = Arrays.asList(line.split(","));
                line = bfReader.readLine();
                while(line!=null){
                    if(!line.isEmpty()){
                        List<String> items = Arrays.asList(line.split(","));
                        Map<String,String> recordMap = new HashMap<>();
                        for(int i=0; i<headings.size() ; i++){
                            recordMap.put(headings.get(i),items.get(i));
                        }
                        recordsMapList.put(items.get(0),recordMap);
                    }
                    line = bfReader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recordsMapList;
    }

    /**
     * Method to read the input file records and adding each line as an item in String list.
     *
     * @param filePath
     * @return List<String>
     */
    public static List<String> getListOfRecordsFromCSV( String filePath){
        List<String> inputLines = new ArrayList<>();
        try {
            try(Stream<String> lines = Files.lines(Paths.get(filePath)))    {
                inputLines=lines.collect(Collectors.toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputLines;
    }

}
