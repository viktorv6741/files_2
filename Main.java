package files_2version;

import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileWriter;


public class Main {
    public static void main(String... args) throws IOException {

        Scanner console = new Scanner(System.in);

        String pathToPropertyFile = "C:\\Users\\Viktor\\Desktop\\Streams\\src\\main\\resources\\user_config_data_set.properties";
        String pathToFile = "C:\\Users\\Viktor\\Desktop\\parameters.txt";

        FileReader fileReader = new FileReader(pathToFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int index = 0;
        String[] tempArray = new String[index];
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            index++;
            tempArray = Arrays.copyOf(tempArray, index);
            tempArray[index - 1] = line;
        }
        bufferedReader.close();

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Properties properties = new Properties();

        while (true) {
            System.out.println("Enter key value or 'exit' ");
            String keyValue = console.nextLine();
            if (!(keyValue.contains("exit"))) {
                System.out.println("Enter parameter");
                String parameter = console.nextLine();
                properties.setProperty(keyValue, parameter);
            } else {
                break;
            }
            
        }
        properties.store(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathToPropertyFile))), null);

        for (int i = 0; i < tempArray.length; i++) {
            if (properties.get(tempArray[i]) != null) {
                tempArray[i] = tempArray[i] + " " + properties.get(tempArray[i]);
            } else {
                tempArray[i] = tempArray[i] + " NOT AVAILABLE";
            }
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String userName = System.getProperty("user.name");

        File outputFile = new File(userName);
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(outputFile);

        for (String value : tempArray) {
            fileWriter.append(value + "\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }
}

