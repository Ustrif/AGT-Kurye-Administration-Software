package utils;
import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import cargo.Cargo;
public class Save implements Control {
    @Override
    public void isValid() {
        try {
            File Obj = new File("log.txt");
            if (Obj.createNewFile()) {
                System.out.println();
            }
            else {
                System.out.println();
            }
        }
        catch (IOException e) {
            System.out.println("DOSYA SİSTEMİ HATASI. GERİ BİLDİRİM VERİNİZ.");
        }
    }

    private void fileWriter(String pathname, String data){
        try{
            String rdata = data + "\n";
            FileWriter myWriter = new FileWriter(pathname,true);
            myWriter.write(rdata);
            myWriter.close();
        }
        catch (IOException a){
            System.out.println("Yazma hatası.");
        }

    }
    public void fileSaver(String path,String data){
        switch (path) {
            case "p" -> fileWriter("personels.txt", data);
            case "e" -> fileWriter("executers.txt", data);
            case null, default -> System.out.println("Bilinmeyen.");
        }
    }

    public void logSaver(String data){
        String latestData;
        latestData = dateReturner() + ": "+ data;
        fileWriter("log.txt", latestData);
    }

    private String dateReturner(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return myDateObj.format(myFormatObj);
    }
    public void addWorker(String k, String usercode, String name, String surname,int telNo,int tcNo, String password){
        String kdatas = usercode + "," + name + "," + surname + "," + telNo + "," + tcNo + "," +  password + ",";
        Save saver = new Save();
        saver.fileSaver(k,kdatas);
    }

    public void noteRecorder(String note){
        try{
            FileWriter myWriter = new FileWriter("notes.txt");
            myWriter.write(note);
            myWriter.close();
        }
        catch (IOException a){
            System.out.println("Yazma hatası.");
        }
    }

    public void removeWorker(int line, int who){
        ArrayList<String> fileData = new ArrayList<>();
        try {
            String[] whoText = {"executers.txt", "personels.txt"};
            FileReader fr = new FileReader(whoText[who]);
            BufferedReader br = new BufferedReader(fr);
            String lines;
            int number = 0;
            while ((lines = br.readLine()) != null) {
                if(number != line)
                    fileData.add(lines);
                number++;
            }

            try{
                FileWriter writer = new FileWriter(whoText[who]);
                for(String data: fileData) {
                    writer.write(data);
                    writer.write("\n");
                }
                writer.close();
            } catch (IOException e){
                System.out.println("Yazma hatası.");
            }

        } catch (IOException e) {
            System.out.println("Okuma hatası.");
        }
    }

    public void saveCargoInfos(Cargo cargo){
        try{
            String cargoText = cargo.getCargo_number() + "," + cargo.getCargo_size() + "," + cargo.getSender_name()
                    + "," + cargo.getSender_surname() + "," + cargo.getSender_adress() + ","
                    + cargo.getReceiver_name() + "," + cargo.getReceiver_surname() + "," +
                    cargo.getReceiver_phone() + "," + cargo.getReceiver_adress() +",YOLDA,\n";
            FileWriter myWriter = new FileWriter("cargoInfos.txt",true);
            myWriter.write(cargoText);
            myWriter.close();
        }
        catch (IOException a){
            System.out.println("Yazma hatası.");
        }
    }

    public void removeCargo(int line){
        ArrayList<String> fileData = new ArrayList<>();
        try {
            FileReader fr = new FileReader("cargoInfos.txt");
            BufferedReader br = new BufferedReader(fr);
            String lines;
            int number = 0;
            while ((lines = br.readLine()) != null) {
                if(number != line)
                    fileData.add(lines);
                number++;
            }

            try{
                FileWriter writer = new FileWriter("cargoInfos.txt");
                for(String data: fileData) {
                    writer.write(data);
                    writer.write("\n");
                }
                writer.close();
            } catch (IOException e){
                System.out.println("Yazma hatası.");
            }

        } catch (IOException e) {
            System.out.println("Okuma hatası.");
        }
    }

    public void deliverCargo(int line){
        ArrayList<String> fileData = new ArrayList<>();
        try {
            FileReader fr = new FileReader("cargoInfos.txt");
            BufferedReader br = new BufferedReader(fr);
            String lines;
            String willChange = "deneme";
            int number = 0;
            while ((lines = br.readLine()) != null) {
                if(number != line)
                    fileData.add(lines);
                else{
                    willChange = lines;
                }
                number++;
            }
            FileWriter writer = new FileWriter("cargoInfos.txt");
            for(String a: fileData){
                writer.write(a +"\n");
            }
            String[] temp = willChange.split(",");
            for(String b: temp){
                if(!b.equals("YOLDA"))
                    writer.write(b + ",");
                else
                    writer.write("TESLİM EDİLDİ,\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Okuma hatası.");
        }
    }
}