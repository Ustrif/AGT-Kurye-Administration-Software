package utils;

import java.io.File;
import java.io.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Infos {
    public Infos(){
        try {
            File Obj = new File("executers.txt");
            File Obj1 = new File("personels.txt");
            if (Obj.createNewFile() && Obj1.createNewFile()) {
                System.out.println();
            }
            else {
                System.out.println();
            }
        }
        catch (IOException e) {
            System.out.println("Sistem hatasi.");
        }
    }

    public ArrayList<String> getKnameList(String who) {
        ArrayList<String> names = new ArrayList<>();
        try {
            FileReader fr = new FileReader(who);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                names.add(line.split(",")[0]);
            }
        } catch (IOException e) {
            System.out.println("Okuma hatası.");
        }

        return names;

    }

    public ArrayList<String> getCargoNamesList(){
        ArrayList<String> cargoNames= new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("cargoInfos.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                cargoNames.add(line.split(",")[0]);
            }
        } catch (IOException e) {
            System.out.println("Okuma hatası.");
        }
        return cargoNames;
    }

    public String[] getCargoInfos(String cargocode){
        String[] cargoInfos = null;
        try {
            FileReader fr = new FileReader("cargoInfos.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if(line.contains(cargocode)){
                    cargoInfos = line.split(",");
                }
            }

        } catch (IOException e) {
            System.out.println("Okuma hatası.");
        }
        return cargoInfos;
    }



    public String LoginInfos(String kNOfor, String who){
        String[] executerInfos;
        try {
            FileReader fr = new FileReader(who+".txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                executerInfos = line.split(",");
                if(kNOfor.equals(executerInfos[0])){
                    return executerInfos[5];
                }
            }

        } catch (IOException e) {
            System.out.println("Okuma hatası.");
        }
        return null;
    }

    public String executerNoteFetcher(){
        StringBuilder notes = new StringBuilder();
        try {
            FileReader fr = new FileReader("notes.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                notes.append(line);
                notes.append("\n");
            }

        } catch (IOException e) {
            System.out.println("Okuma hatası.");
        }
        return notes.toString();
    }


    public ArrayList<String[]> staffInfoFetcher(String whichfile){
        ArrayList<String[]> forInfos = new ArrayList<>();
        try {
            String filename = whichfile + ".txt";
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                forInfos.add(line.split(","));
            }

        } catch (IOException e) {
            System.out.println("Okuma hatası.");
        }
        return forInfos;
    }

    public ArrayList<String[]> cargoInfosFetcher(){
        ArrayList<String[]> forInfos = new ArrayList<>();
        try {
            FileReader fr = new FileReader("cargoInfos.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                forInfos.add(line.split(","));
            }

        } catch (IOException e) {
            System.out.println("Okuma hatası.");
        }
        return forInfos;
    }

    public ArrayList<String> getStores(){
        ArrayList<String> stores = new ArrayList<>();
        try {
            FileReader fr = new FileReader("stores.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                stores.add(line);
            }

        } catch (IOException e) {
            System.out.println("Okuma hatası.");
        }
        return stores;
    }

    public String getCargoName(){
        int number = 1;
        try{
            FileReader fr = new FileReader("cargoInfos.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((br.readLine()) != null) {
                number++;
            }
        }
        catch (IOException e){
            System.out.println("Okuma hatası.");
        }
        return ("cargo" + number);
    }

    public String questionsFetcher(){
        StringBuilder notes = new StringBuilder();
        try {
            FileReader fr = new FileReader("about.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                notes.append(line);
                notes.append("\n");
            }

        } catch (IOException e) {
            System.out.println("Okuma hatası.");
        }
        return notes.toString();
    }

}

