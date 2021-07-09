package chapter19.services;

import chapter19.models.FireArmModel;
import chapter19.models.PropertiesModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface DataFilesService {
    File[] createDirectory(File... directories) throws IOException;
    File createDirectory(File directory) throws IOException;
    File[] createFile(File... files) throws IOException;
    File[] createFile(Path... files) throws IOException;
    File createFile(File file) throws IOException;
    boolean isFileEmpty(File file);
    void printDataUsingInputStream(File file) throws IOException;
    void printDataUsingReader(File file) throws IOException;
    void writeDataUsingWriter(File file, String text, int amount) throws IOException;
    void writeDataUsingOutputStream(File file, String text, int amount) throws IOException;
    void copyFileUsingBinaryStream(File src, File dest) throws IOException;
    void copyFileUsingCharacterStream(File src, File dest) throws IOException;
    void writeDataUsingWriter( List<PropertiesModel> propertiesModels, File... file) throws IOException;
    void writeDataUsingObjectOutputStream( List<PropertiesModel> propertiesModels, File... file) throws IOException;
    List<PropertiesModel> readDataUsingObjectInputStream(File dataFile) throws IOException, ClassNotFoundException;
    void writeDataUsingObjectOutputStream(FireArmModel.FireArmSpecification fireArm, File file) throws IOException;
    FireArmModel.FireArmSpecification readFireArmUsingObjectInputStream (File dataFile) throws IOException, ClassNotFoundException;
    List<String> readDataUsingBufferedReader(Path path) throws IOException;
    void writeDataUsingBufferedWriter(List<String> listData, Path path) throws IOException;
}
