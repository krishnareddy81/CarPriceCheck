package com.car.price.check.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class FileReadUtil {
    public  static List<String> getCarRegNumbers(String inputFileName) throws IOException {
        BufferedReader bufferedReader = null;
        String[] patternWords;
        List<String> regNumbers= new ArrayList<String>();
        try{
            File inputDataFile = getDataFile(inputFileName);
            bufferedReader = new BufferedReader(new FileReader(inputDataFile.getAbsolutePath()));
            String stringLine;
            while ((stringLine = bufferedReader.readLine()) != null)   {
                String pattern = "(^[A-Z]{2}[0-9]{2}([A-Z]{3}|\\s[A-Z]{3})$)";
                patternWords= stringLine.split(" ");
                for (String word : patternWords) {
                    if (Pattern.matches(pattern, word)) {
                        regNumbers.add(word);
                    }
                }

            }

        }catch (Exception exception){
            System.err.println("Exception Details: " + exception.getMessage());
        }finally {
            bufferedReader.close();
        }
        return regNumbers;
    }
    public static  Map<String,List<String>> getOutputDataDetails(String outputFileName) throws IOException {
        BufferedReader bufferedReader = null;
        String outLine=" ";
        List<String> outputDetails=new ArrayList<String>();
        Map<String,List<String>> expectedMap=new HashMap<String, List<String>>();
        try{
            File outputDataFIle = getDataFile(outputFileName);
            bufferedReader = new BufferedReader(new FileReader(outputDataFIle.getAbsolutePath()));
            while ((outLine = bufferedReader.readLine()) != null) {
                outputDetails.add(outLine);
            }
            //Delete headers
            outputDetails.remove(0);

            //creating output data map
            for(String expectedValue:outputDetails)
            {
                String[] expectedDetails = expectedValue.split(",");
                List<String> list = Arrays.asList(expectedDetails);
                expectedMap.put(list.get(0),list);
            }

        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }finally {
            bufferedReader.close();
        }

        return  expectedMap;
    }

    private static File getDataFile(String fileLocation) {
        ClassLoader classLoader = FileReadUtil.class.getClassLoader();
        return new File(classLoader.getResource(fileLocation).getFile());
    }
}

