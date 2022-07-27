package com.example;

import java.io.FileWriter;
import java.io.FileReader;
import java.util.List;
import com.opencsv.*;

public class App 
{
    public static void main( String[] args )
    {
        readAllDataAtOnce(args[0], args[1]);
        System.err.println("Conversao concluida");
    }

    public static void readAllDataAtOnce(String inputFile, String outputFile)
    {
        try {
            FileReader filereader = new FileReader(inputFile);    
            CSVReader csvReader = new CSVReaderBuilder(filereader).build();
            List<String[]> csvData = csvReader.readAll();            
            FileWriter xmlData = new FileWriter(outputFile);
            
            String[] header = csvData.remove(0);
            
            int index;

            xmlData.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
            xmlData.write("<root>\n");
            for (String[] row : csvData)
            {
                index = 0;
                xmlData.write("\t<row>\n");
                for (String cell : row)
                {
                    xmlData.write("\t\t<" + header[index] + ">");
                    xmlData.write(cell);
                    xmlData.write("</" + header[index] + ">\n");
                    index++;
                }
                xmlData.write("\t</row>\n");
            }           
            xmlData.write("</root>\n");
            xmlData.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}


