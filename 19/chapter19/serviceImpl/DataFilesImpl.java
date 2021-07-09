package chapter19.serviceImpl;


import chapter19.models.FireArmModel;
import chapter19.models.PropertiesModel;
import chapter19.services.DataFilesService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataFilesImpl implements DataFilesService {

    public DataFilesImpl() {
    }

    public File[] createDirectory(File... directories) throws IOException {
        for (File file : directories) {
            file.mkdir();
        }
        return directories;
    }

    public File createDirectory(File directory) throws IOException {
        directory.mkdir();
        return directory;
    }

    public File[] createFile(File... files) throws IOException {
        for (File file : files) {
            file.createNewFile();
        }
        return files;
    }

    @Override
    public File[] createFile(Path... files) throws IOException {
        int count = (int) Arrays.stream(files).count();
        File[] filesArray = new File[count];
        int iteration = 0;
        for (Path file : files) {
            if (!Files.exists(file)){
                Files.createFile(file);
                filesArray[iteration] = file.toFile();
            }
            ++iteration;
        }
        return filesArray;
    }

    public File createFile(File file) throws IOException {
        file.createNewFile();
        return file;
    }

    public boolean isFileEmpty(File file) {
        if (file.length() < 1) {
            return true;
        }
        return false;
    }

    public void printDataUsingInputStream(File file) throws IOException {
        try (var bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = bufferedInputStream.read()) != -1) {
                System.out.print((char) data);
            }
        }

    }

    public void printDataUsingReader(File file) throws IOException {
        try (var bufferedReader = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = bufferedReader.read()) != -1) {
                System.out.print((char) data);
            }
        }

    }

    public void writeDataUsingWriter(File file, String text, int amount) throws IOException {
        try (var bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true))) {
            for (int iteration = 0; iteration <= amount; iteration++) {
                bufferedWriter.write(text);
                bufferedWriter.newLine();
                if (iteration % 2 == 0) {
                    bufferedWriter.flush();
                }
            }
        }
    }

    public void writeDataUsingOutputStream(File file, String text, int amount) throws IOException {
        //usage of PrintWriter (can take an OutputStream as well)
        try (var bufferedOutputStream = new PrintWriter(new FileOutputStream(file.getAbsolutePath(), true))) {
            for (int iteration = 0; iteration <= amount; iteration++) {
                for (int textCharIteration = 0; textCharIteration < text.length(); textCharIteration++) {
                    // print method
                    bufferedOutputStream.print(text.charAt(textCharIteration));
                    if (textCharIteration % 2 == 0) {
                        bufferedOutputStream.flush();
                    }
                }
            }
        }
    }

    public void copyFileUsingBinaryStream(File src, File dest) throws IOException {
        try (var bufferedInputStream = new BufferedInputStream(new FileInputStream(src));
             var bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(dest))) {
            var buffer = new byte[1024];
            int data;
            while ((data = bufferedInputStream.read(buffer)) > 0) {
                bufferedOutputStream.write(buffer, 0, data);
                bufferedOutputStream.flush();
            }
        }
    }

    public void copyFileUsingCharacterStream(File src, File dest) throws IOException {
        try (var bufferedReader = new BufferedReader(new FileReader(src));
             var bufferedWriter = new BufferedWriter(new FileWriter(dest))) {
            String data;
            if (isFileEmpty(dest)) {
                while ((data = bufferedReader.readLine()) != null) {
                    bufferedWriter.write(data);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            }
        }
    }

    public void writeDataUsingWriter(List<PropertiesModel> propertiesModels, File... files) throws IOException {
        for (File file : files) {
            if (isFileEmpty(file)) {
                int iteration = 0;
                try (var bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true))) {
                    for (PropertiesModel propertiesModel : propertiesModels) {
                        bufferedWriter.write(propertiesModel.toString());
                        bufferedWriter.newLine();
                        if (iteration % 20 == 0) {
                            bufferedWriter.flush();
                        }
                        ++iteration;
                    }
                }
            }

        }
    }

    public void writeDataUsingObjectOutputStream(List<PropertiesModel> propertiesModels, File... files) throws IOException {
        for (File file : files) {
            if (isFileEmpty(file)) {
                try (var out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
                    for (PropertiesModel propertiesModel : propertiesModels)
                        out.writeObject(propertiesModel);
                }
            }
        }
    }

    public void writeDataUsingObjectOutputStream(FireArmModel.FireArmSpecification fireArm, File file) throws IOException {
        try (var out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            out.writeObject(fireArm);
        }
    }

    public FireArmModel.FireArmSpecification readFireArmUsingObjectInputStream(File dataFile) throws IOException, ClassNotFoundException {
        FireArmModel.FireArmSpecification fireArmSpecification = null;
        try (var data = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))) {
            while (true) {
                var object = data.readObject();
                if (object instanceof FireArmModel.FireArmSpecification)
                    fireArmSpecification = (FireArmModel.FireArmSpecification) object;
            }
        } catch (EOFException e) {
        }
        return fireArmSpecification;
    }

    public List<PropertiesModel> readDataUsingObjectInputStream(File dataFile) throws IOException, ClassNotFoundException {
        var propertiesModels = new ArrayList<PropertiesModel>();
        try (var in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))) {
            while (true) {
                var object = in.readObject();
                if (object instanceof PropertiesModel)
                    propertiesModels.add((PropertiesModel) object);
            }
        } catch (EOFException e) {
        }
        return propertiesModels;
    }


    //  reading data using newBufferedReader method from Files helper class
    public List<String> readDataUsingBufferedReader(Path path) throws IOException {
        List<String> dataList = new ArrayList<>();
        try (var reader = Files.newBufferedReader(path)) {
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {
                dataList.add(currentLine);
            }
            return dataList;
        }
    }

    // writing data using newBufferedWriter method from Files helper class
    public void writeDataUsingBufferedWriter(List<String> listData, Path path) throws IOException {
        try (var writer = Files.newBufferedWriter(path)) {
            for (var line : listData) {
                writer.write(line);
                writer.newLine();
            }
        }
    }



}
