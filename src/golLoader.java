import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class golLoader {

    public static int[] coordinateFetcher(String fileName){

        int[] config = new int[4];

        String filePath = ".//gol//";
        String fileDirectory = filePath + fileName;

        File file = new File(fileDirectory);                      

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            System.out.println("Fetching from " + file.getCanonicalFile());

            while ((line = br.readLine()) != null) {
                if (line.startsWith("GRID"))            {
                    String[] data = line.split(" ");
                    config[0] = Integer.parseInt(data[1]);
                    config[1] = Integer.parseInt(data[2]);

                    System.out.println("Grid size set to " + config[0] + "," + config[1] + ".");

                } else if (line.startsWith("START"))    {
                    String[] data = line.split(" ");
                    config[2] = Integer.parseInt(data[1]);
                    config[3] = Integer.parseInt(data[2]);

                    System.out.println("Starting position set to " + config[2] + "," + config[3] + ".");

                } else {
                    continue;
                }
                
            }

            br.close();

            
        } catch (FileNotFoundException e){
            System.out.println("File was not found" + fileDirectory);
            
        } catch (IOException e) {
            System.out.println("Unable to fetch file");
            e.printStackTrace();

        } 

        return config;
    

    }

    public static List<String[]> cellFetcher(String fileName){

        List<String[]> cell = new ArrayList<>();

        String filePath = ".//gol//";
        String fileDirectory = filePath + fileName;

        File file = new File(fileDirectory);                      

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            System.out.println("Fetching from " + file.getCanonicalFile());

            boolean consume = false;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("DATA")){
                    consume = true;
                    continue; 
                } if (consume){
                    cell.add(line.split(""));
                    }   

            }

            br.close();
            
            } catch (FileNotFoundException e){
                System.out.println("File was not found" + fileDirectory);
                    
            } catch (IOException e) {
                System.out.println("Unable to fetch file");
                e.printStackTrace();
        
                } 
        
            return cell;
     
        }  

}
