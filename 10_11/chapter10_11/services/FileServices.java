package chapter10_11.services;


import java.io.*;
import java.util.*;

import static chapter10_11.constants.Constants.*;
import static chapter10_11.services.TriesValidationService.resetTriesService;
import static chapter10_11.services.TriesValidationService.triesValidation;

public class FileServices {
    private Scanner scanner;
    private boolean isFileNameValid;
    private String textFromFile;
    private File[] filesList;
    private String directory;
    private boolean isDirValid;


    public FileServices(String directory) {
        super();
        this.directory = directory;
        init();
    }

    private void init() {
        scanner = new Scanner(System.in);
    }

    public void writeToFile() {
        resetTriesService();
        do {
            displayFiles();
            System.out.println(ENTER_THE_FILE_NAME_FOR_WRITING_PURPOSES_MESSAGE);
            String directoryAndFileName = directory + scanner.next();

            try (FileWriter writeToFile = new FileWriter(directoryAndFileName, true)) {
                System.out.println(TYPE_SOMETHING_TO_WRITE_TO_FILE_MESSAGE);
                writeToFile.write("\n"+scanner.next());
                System.out.println(WRITING_WAS_SUCCESSFUL_MESSAGE);
                isFileNameValid = true;
            } catch (IOException e) {
                triesValidation();
                System.out.println(SOMETHING_WENT_WRONG_WHILE_TRYING_TO_WRITE_TO_FILE_MESSAGE);
                e.printStackTrace();
            }
        } while (!isFileNameValid);
        resetFileCheckAndScanner();
    }

    private void displayFiles() {
        System.out.println(DISPLAYING_FILES_IN_CURRENT_DIRECTORY_MESSAGE);
        getAllFiles();
    }

    private void reEnterDir() {
        triesValidation();
        System.out.println(ENTER_THE_PATH_MESSAGE);
        System.out.println(INVALID_DIRECTORY_SELECTION);
        directory = scanner.next();
        System.out.println(directory);
        System.out.println("");
    }

    private void getAllFiles() {
        resetTriesService();
        do {
            File currentDirectory = new File(directory + ".");
            filesList = currentDirectory.listFiles();
            try {
                for (File files : filesList) {
                    isDirValid = true;
                    if (files.isFile()) {
                        System.out.println(files.getName());
                    }
                }
                System.out.println("");
            } catch (RuntimeException e) {
                e.printStackTrace();
                reEnterDir();
            }
        } while (!isDirValid);

    }

    public void readFromFile() {
        resetTriesService();
        do {
            displayFiles();
            System.out.println(ENTER_THE_FILE_NAME_FOR_READING_PURPOSES_MESSAGE);
            String directoryAndFileName = directory + scanner.next();
            try {
                File readFile = new File(directoryAndFileName);
                scanner = new Scanner(readFile);
                while (scanner.hasNextLine()) {
                    textFromFile = scanner.nextLine();
                    System.out.println(textFromFile);
                }
                System.out.println("");
                isFileNameValid = true;
            } catch (Exception e) {
                triesValidation();
                System.out.println(SOMETHING_WENT_WRONG_WHILE_TRYING_TO_READ_FROM_FILE_MESSAGE);
                e.printStackTrace();
            } finally {
                scanner.close();
            }
        } while (!isFileNameValid);
        resetFileCheckAndScanner();
    }

    public void deleteFile() {
        do {
            displayFiles();
            System.out.println(ENTER_THE_FILE_NAME_FOR_DELETING_PURPOSES_MESSAGE);
            String directoryAndFileName = directory + scanner.next();
            try {
                File deleteFile = new File(directoryAndFileName);
                if (deleteFile.delete()) {
                    System.out.println(deleteFile.getName() + FILE_DELETED_SUCCESSFULLY_MESSAGE);
                    isFileNameValid = true;
                } else {
                    System.out.println(FAILED_TO_DELETE_FILE_MESSAGE);
                    throw new IOException();
                }
            } catch (IOException e) {
                triesValidation();
                System.out.println(SOMETHING_WENT_WRONG_WHILE_DELETING_FILE_MESSAGE);
                e.printStackTrace();
            }
        } while (!isFileNameValid);
        resetFileCheckAndScanner();
    }

    public void createAFile() {
        resetTriesService();
        do {
            displayFiles();
            System.out.println(TYPE_THE_NAME_OF_THE_NEW_FILE_MESSAGE);
            String directoryAndFileName = directory + scanner.next();
            System.out.println(directoryAndFileName);
            try {
                File newFile = new File(directoryAndFileName);
                if (newFile.createNewFile()) {
                    System.out.println(newFile.getName() + FILE_CREATED_SUCCESSFULLY_MESSAGE);
                    isFileNameValid = true;
                } else {
                    System.out.println(FILE_ALREADY_EXISTS_MESSAGE);
                }
            } catch (IOException e) {
                triesValidation();
                System.out.println(FILE_CREATION_ERROR_OCCURRED_MESSAGE);
                e.printStackTrace();
                continue;
            }
        } while (!isFileNameValid);
        resetFileCheckAndScanner();
    }

    private void resetFileCheckAndScanner() {
        isFileNameValid = false;
        init();
    }
}
