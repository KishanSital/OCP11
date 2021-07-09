package chapter19;


import chapter19.models.ExpansionModel;
import chapter19.models.FireArmModel;
import chapter19.models.PropertiesModel;
import chapter19.repositories.ExpansionRepository;
import chapter19.repositories.FireArmsRepository;
import chapter19.serviceImpl.DataFilesImpl;
import chapter19.serviceImpl.ExpansionServiceImpl;
import chapter19.serviceImpl.FireArmsServiceImpl;
import chapter19.services.DataFilesService;
import chapter19.services.ExpansionService;
import chapter19.services.FireArmsService;
import chapter19.utils.StringUtilsFireArmsCategory;
import chapter19.views.ExpansionView;
import chapter19.views.FireArmsView;
import chapter19.views.LoggedInMenuView;
import mypackage.application.MyPackageApplication;
import mypackage.models.UserModel;
import mypackage.services.UserModelWithArguments;
import mypackage.utils.StringUtilsMyPackage;

import java.io.*;
import java.nio.file.*;

import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.Stream;

public class App {


    @SafeVarargs
    // can be applied to methods and constructors that cannot be overridden, and must contain varargs in parameter list
    public static void main(String... args) {

        //create directory
        var srcDirectory = new File(System.getProperty("user.dir"), "chapter19" + System.getProperty("file.separator") + "src");
        var receiptDirectory = new File(System.getProperty("user.dir"), "chapter19" + File.separator + "src" + File.separator + "receipts");

        // working with Path interface
        var enPropertiesFile = Path.of(srcDirectory.getPath(), "Chapter19_en.properties");
        var nlPropertiesFile = Paths.get(srcDirectory.getPath(), "Chapter19_nl.properties");
        var defaultPropertiesFile = Paths.get(srcDirectory.getPath(), "Chapter19.properties"); // Path is immutable
        defaultPropertiesFile.resolve(nlPropertiesFile); // won't change value of defaultPropertiesFile

        DataFilesService dataFilesService = new DataFilesImpl();

        createDirectoriesAndFiles(srcDirectory,
                receiptDirectory,
                enPropertiesFile,
                nlPropertiesFile,
                defaultPropertiesFile,
                dataFilesService);

        checkIfPropertiesFilesExist(enPropertiesFile,
                nlPropertiesFile,
                defaultPropertiesFile);

        populateFiles(enPropertiesFile,
                nlPropertiesFile,
                defaultPropertiesFile,
                dataFilesService);

        startingApp(srcDirectory,
                receiptDirectory,
                dataFilesService);

    }

    private static void populateFiles(Path enPropertiesFile,
                                      Path nlPropertiesFile,
                                      Path defaultPropertiesFile,
                                      DataFilesService dataFilesService) {
        try {
            dataFilesService.writeDataUsingWriter(getNlPropertiesModels(),
                    nlPropertiesFile.toFile());
            dataFilesService.writeDataUsingWriter(getEnPropertiesModels(),
                    enPropertiesFile.toFile());
            dataFilesService.copyFileUsingCharacterStream(enPropertiesFile.toFile(),
                    defaultPropertiesFile.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkIfPropertiesFilesExist(Path enPropertiesFile,
                                                    Path nlPropertiesFile,
                                                    Path defaultPropertiesFile) {
        //implicit toString() method gets called when printing Path
        System.out.println("Does Path: " + enPropertiesFile + " exists? " + (Files.exists(enPropertiesFile) ? "yes" : "no")); // using Files method to check if Path exists
        System.out.println("Does Path: " + nlPropertiesFile + " exists? " + (nlPropertiesFile.toFile().exists() ? "yes" : "no")); // using instance File method to check if file exists
        System.out.println("Does Path: " + defaultPropertiesFile + " exists? " + (defaultPropertiesFile.toFile().exists() ? "yes" : "no")); // using File method to check if file exists
        System.out.println();
    }

    private static void createDirectoriesAndFiles(File srcDirectory,
                                                  File receiptDirectory,
                                                  Path enPropertiesFile,
                                                  Path nlPropertiesFile,
                                                  Path defaultPropertiesFile,
                                                  DataFilesService dataFilesService) {
        try {
            dataFilesService.createDirectory(srcDirectory, receiptDirectory);

            //creating tools directory using method chaining
            var toolsDirectory = Path.of(srcDirectory.getPath()).resolve("tools");

            if (!Files.exists(toolsDirectory)){
                Files.createDirectory(toolsDirectory);
            }

            dataFilesService.createFile(defaultPropertiesFile, enPropertiesFile, nlPropertiesFile);
        } catch (IOException e) {
            System.out.println("Something went wrong while creating the directories");
            e.printStackTrace();
        }
    }

    private static void startingApp(File srcDirectory,
                                    File receiptDirectory,
                                    DataFilesService dataFilesService) {
        System.out.println();

        StringUtilsMyPackage.displayWelcomeMessage();

        UserModelWithArguments userModel = UserModel::new;
        UserModel expectedUser = userModel.create(1L, "Kishan", new char[]{'1', '2', '3', '4'});

        // working with Console class
        MyPackageApplication.startLoginService(expectedUser);

        //usage of format
        System.out.format("Welcome %s %n", expectedUser.getUsername());

        LoggedInMenuView.chooseLanguage(srcDirectory);

        displayChosenLanguageDetails(srcDirectory);

        FireArmsService fireArmServiceImpl = new FireArmsServiceImpl(new FireArmsRepository(),
                                                                    dataFilesService,
                                                                    receiptDirectory);
        ExpansionService expansionServiceImpl = new ExpansionServiceImpl(new ExpansionRepository());

        fireArmServiceImpl.getFireArms().add(new FireArmModel(1L,
                (StringUtilsFireArmsCategory.HAND_GUNS.getCategoryName()),
                "GLOCK-19",
                5, 325.00).new FireArmSpecification("9x19mm",
                "600g",
                "670g",
                "855g",
                30,
                "102mm",
                "28N"));

        fireArmServiceImpl.getFireArms().add(new FireArmModel(2L,
                (StringUtilsFireArmsCategory.LONG_GUNS.getCategoryName()),
                "AK-47",
                5, 1288.46).new FireArmSpecification("7.62x39mm",
                "3.47 Kg",
                "3.90 Kg",
                "4.39 Kg",
                30,
                "369 mm",
                "17.7 N"));

        var zoneId = TimeZone.getDefault().toZoneId();

        expansionServiceImpl.getTodoExpansionList().offer(new ExpansionModel(ZonedDateTime.of(LocalDateTime.of(2017, Month.JANUARY, 01, 00, 00), zoneId),
                "Expand storage area/vergroot opslag plaats",
                null));
        expansionServiceImpl.getTodoExpansionList().offer(new ExpansionModel(ZonedDateTime.of(LocalDateTime.of(2018, Month.JANUARY, 01, 00, 00), zoneId),
                "Expand variety of firearms/verscheidenheid aan vuurwapens uitbreiden",
                null));
        expansionServiceImpl.getTodoExpansionList().offer(new ExpansionModel(ZonedDateTime.of(LocalDateTime.of(2019, Month.JANUARY, 01, 00, 00), zoneId),
                "Employ new storage workers/nieuwe opslagmedewerkers in dienst nemen",
                null));


        var fireArmsView = new FireArmsView(fireArmServiceImpl);
        var expansionView = new ExpansionView(expansionServiceImpl);
        var loggedInMenuView = new LoggedInMenuView(fireArmsView,
                expansionView,
                receiptDirectory.toPath());
        loggedInMenuView.displayMenu();
    }

    private static void displayChosenLanguageDetails(File srcDirectory) {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("\tDisplaying the details and performing a few operations with the selected language properties file\n");

        // conversion from File to Path
        Path getCurrentUserDirectory = srcDirectory.toPath();
        Path languageFile = Paths.get("..\\src\\Chapter19_" + Locale.getDefault().getLanguage() + ".properties");

        // combining 2 paths using resolve method
        Path resolvedPath = getCurrentUserDirectory.resolve(languageFile);//you can't combine 2 absolute paths, if so then the path passed as argument in the resolved method will be returned

        checkPathDetailsOfLanguageFile(resolvedPath);

        Path realPath = performingPathOperations(resolvedPath);

        copyingLanguageFileToNewDirectory(languageFile, realPath);
        checkAttributeDetailsOfLanguageFile(realPath);
        displayAllFilesAvailableInLanguageFolder(realPath);

        System.out.println("-----------------------------------------------------------------------------------------------------------------------");

    }

    private static Path performingPathOperations(Path resolvedPath) {
        Path currentParent = resolvedPath;

        System.out.println("\nLooping through all the parents");
        while ((currentParent = currentParent.getParent()) != null) { // looping through each parent of the file
            System.out.println(" Current parent is: " + currentParent);
        }

        //printing the normalized path by removing unnecessary specified files
        System.out.println("\nThe normalized path : " + resolvedPath.normalize());

        //retrieving the real path
        Path realPath = null;
        try {
            realPath = resolvedPath.toRealPath();
            System.out.println("\nRetrieving the real path: " + realPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return realPath;
    }

    private static void checkPathDetailsOfLanguageFile(Path resolvedPath) {
        System.out.println("The Path is: " + resolvedPath); // the toString method gets called
        System.out.println("\nIs this path Absolute ? " + (resolvedPath.isAbsolute() ? "yes" : "no"));
        System.out.println("\nAmount of file/ directories specified in the path are " + resolvedPath.getNameCount());// counts the number of directories and files specified in the path

        System.out.println("\nPrinting all the elements in the specified path");
        for (int i = 0; i < resolvedPath.getNameCount(); i++) {
            System.out.println(" Element " + i + " is: " + resolvedPath.getName(i)); // retrieve the file or directory name
        }

        System.out.println("\nFilename is: " + resolvedPath.getFileName()); // retrieves the file name
        System.out.println("\nRoot is: " + resolvedPath.getRoot()); // displays the root of the file
    }

    private static void displayAllFilesAvailableInLanguageFolder(Path realPath) {
        //displaying files in our directory using Files.list() method
        System.out.println("Displaying available files/folders in " + realPath.getParent());
        try (Stream<Path> s = Files.list(realPath.getParent())) {
            s.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkAttributeDetailsOfLanguageFile(Path realPath) {
        try {
            System.out.println("Is our language file hidden ? :" + (Files.isHidden(realPath) ? "yes" : "no"));// true if exists and is hidden
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Is our language file readable ? :" + (Files.isReadable(realPath) ? "yes" : "no"));// true if exists and is readable
        System.out.println("Is our language file writable ? :" + (Files.isWritable(realPath) ? "yes" : "no"));// true if exists and is able to be modified
        System.out.println("Is our language file executable ? :" + (Files.isExecutable(realPath) ? "yes" : "no"));//true if it is able to be executed within operating system

        //retrieving attributes with readAttributes()
        try {
            BasicFileAttributes data = Files.readAttributes(realPath,
                    BasicFileAttributes.class);
            System.out.println("Is our language file a directory ? :" + (data.isDirectory() ? "yes" : "no"));// true if directory or symbolic link to a directory
            System.out.println("Is our language file a symbolic link ? :" + (data.isSymbolicLink() ? "yes" : "no"));//true if it's a symbolic link, regardless whether the file or directory it points to exists
            System.out.println("Is our language file a regular file ? :" + (data.isRegularFile() ? "yes\n" : "no\n"));// true if it points to a regular file or a symbolic file that points to a regular file
            System.out.println("Size of our language file :" + data.size() + " bytes\n");// true if it points to a regular file or a symbolic file that points to a regular file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyingLanguageFileToNewDirectory(Path languageFile, Path realPath) {
        // performing Files.copy() using two paths, and using the StandardCopyOption.REPLACE_EXISTING for when file or directory already exists
        System.out.println("\nCopying language file from " + realPath + " to\n" + realPath.getParent().resolve("tools").resolve(languageFile.getFileName()));
        try {
            Files.copy(realPath,
                    realPath.getParent().resolve("tools").resolve(languageFile.getFileName()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Does Path: " + realPath.getParent().resolve("tools").resolve(languageFile.getFileName()) + " exists? "
                + (Files.exists(realPath.getParent().resolve("tools").resolve(languageFile.getFileName())) ? "yes\n" : "no\n")); // using Files method to check if Path exists
    }



    private static List<PropertiesModel> getEnPropertiesModels() {
        List<PropertiesModel> enProperties = new ArrayList<>();
        enProperties.add(new PropertiesModel("EXPANSION_PLAN_MESSAGE", "Expansion plan"));
        enProperties.add(new PropertiesModel("PLAN_MESSAGE", "plan"));
        enProperties.add(new PropertiesModel("START_DATE_MESSAGE", "start date"));
        enProperties.add(new PropertiesModel("END_DATE_MESSAGE", "end date"));
        enProperties.add(new PropertiesModel("NO_EXPANSION_PLANS_MESSAGE", "There are no plans available"));
        enProperties.add(new PropertiesModel("PATTERN", "MMMM dd, yyyy 'at' hh:mm a 'zone' z"));
        enProperties.add(new PropertiesModel("INSERT_PLAN_MESSAGE", "Type your plan"));
        enProperties.add(new PropertiesModel("SOMETHING_WENT_WRONG_DURING_PLAN_INSERT_MESSAGE", "Something went wrong, please try again later"));
        enProperties.add(new PropertiesModel("ARE_YOU_SURE_MESSAGE", "Are you sure that you'd like to mark"));
        enProperties.add(new PropertiesModel("TO_DONE_MESSAGE", "to done"));
        enProperties.add(new PropertiesModel("TYPE_YES_NO_MESSAGE", "Type y for yes or n for no"));
        enProperties.add(new PropertiesModel("ENTER_AN_EXPECTED_VALUE_MESSAGE", "Please enter an expected value"));
        enProperties.add(new PropertiesModel("ALL_EXECUTED_PLANS_MESSAGE", "All the executed plans"));
        enProperties.add(new PropertiesModel("FIREARM_MESSAGE", "Firearm"));
        enProperties.add(new PropertiesModel("FIREARM_ID_MESSAGE", "firearm id"));
        enProperties.add(new PropertiesModel("FIREARM_CATEGORY_MESSAGE", "firearm category"));
        enProperties.add(new PropertiesModel("FIREARM_NAME_MESSAGE", "firearm name"));
        enProperties.add(new PropertiesModel("FIREARM_AMOUNT_MESSAGE", "amount"));
        enProperties.add(new PropertiesModel("PRICE_PER_ITEM_MESSAGE", "price per item"));
        enProperties.add(new PropertiesModel("FIREARM_SPECIFICATION_MESSAGE", "Firearm specification"));
        enProperties.add(new PropertiesModel("CALIBER_MESSAGE", "caliber"));
        enProperties.add(new PropertiesModel("WEIGHT_WITHOUT_MAGAZINE_MESSAGE", "weight without magazine"));
        enProperties.add(new PropertiesModel("WEIGHT_WITH_EMPTY_MAGAZINE_MESSAGE", "weight with empty magazine"));
        enProperties.add(new PropertiesModel("WEIGHT_WITH_LOADED_MAGAZINE_MESSAGE", "weight with loaded magazine"));
        enProperties.add(new PropertiesModel("MAGAZINE_CAPACITY_MESSAGE", "magazine capacity"));
        enProperties.add(new PropertiesModel("BARREL_LENGTH_MESSAGE", "barrel length"));
        enProperties.add(new PropertiesModel("TRIGGER_PULL_MESSAGE", "trigger pull"));
        enProperties.add(new PropertiesModel("HAND_GUNS", "Hand guns"));
        enProperties.add(new PropertiesModel("LONG_GUNS", "Long guns"));
        enProperties.add(new PropertiesModel("INSERT_NAME_MESSAGE", "Enter the name of the firearm you'd like to sell"));
        enProperties.add(new PropertiesModel("FIREARM_NOT_EXISTENT_MESSAGE", "This firearm may be out of stock or it does not exist, please type another name from the list"));
        enProperties.add(new PropertiesModel("INSERT_AMOUNT_MESSAGE", "Enter the amount you'd like to sell"));
        enProperties.add(new PropertiesModel("SELECTED_AMOUNT_NOT_AVAILABLE_MESSAGE", "The selected amount is not available, please try again"));
        enProperties.add(new PropertiesModel("IN_STOCK_MESSAGE", "The firearms which are in stock are"));
        enProperties.add(new PropertiesModel("OUT_OF_STOCK_MESSAGE", "Sorry we're out of stock"));
        enProperties.add(new PropertiesModel("SOLD_FIREARM_MESSAGE", "The sold firearms are"));
        enProperties.add(new PropertiesModel("NOTHING_SOLD_MESSAGE", "Nothing has been sold"));
        enProperties.add(new PropertiesModel("TRANSACTION_TIME_STAMP", "Please note this down, your Transaction ID: "));
        enProperties.add(new PropertiesModel("INSERT_TRANSACTION_ID", "Please enter an existent Transaction id"));
        enProperties.add(new PropertiesModel("TRANSACTION_NOT_EXISTENT_MESSAGE", "This transaction does not exist"));
        enProperties.add(new PropertiesModel("TOTAL_SOLD_PRICE_MESSAGE", "Total sold price"));
        enProperties.add(new PropertiesModel("WELCOME_MENU_MESSAGE", "Welcome to your firearms seller application"));
        enProperties.add(new PropertiesModel("SELL_FIREARM_MESSAGE", "to sell a firearm"));
        enProperties.add(new PropertiesModel("DISPLAY_AVAILABLE_FIREARM_MESSAGE", "to display the available firearms"));
        enProperties.add(new PropertiesModel("DISPLAY_SOLD_FIREARM_MESSAGE", "to display the sold firearms"));
        enProperties.add(new PropertiesModel("REVIEW_DETAILS_TRANSACTION_MESSAGE", "to review details of a transaction"));
        enProperties.add(new PropertiesModel("DISPLAY_FULL_EXPANSION_PLAN", "to display full expansion plan"));
        enProperties.add(new PropertiesModel("DISPLAY_UPCOMING_PLANS", "to display the upcoming plan"));
        enProperties.add(new PropertiesModel("ADD_A_PLAN_MESSAGE", "to add a plan"));
        enProperties.add(new PropertiesModel("MARK_UPCOMING_PLAN_TO_DONE_MESSAGE", "to mark upcoming plan to done"));
        enProperties.add(new PropertiesModel("VIEW_ALL_EXECUTED_PLANS_MESSAGE", "to view all executed plans"));
        enProperties.add(new PropertiesModel("LOG_OUT_AND_EXIT_MESSAGE", "to Log out and exit the program"));
        enProperties.add(new PropertiesModel("TYPE", "Type"));
        return enProperties;
    }

    private static List<PropertiesModel> getNlPropertiesModels() {
        List<PropertiesModel> nlProperties = new ArrayList<>();
        nlProperties.add(new PropertiesModel("EXPANSION_PLAN_MESSAGE", "Uitbreidingsplan"));
        nlProperties.add(new PropertiesModel("PLAN_MESSAGE", "plan"));
        nlProperties.add(new PropertiesModel("START_DATE_MESSAGE", "startdatum"));
        nlProperties.add(new PropertiesModel("END_DATE_MESSAGE", "einddatum"));
        nlProperties.add(new PropertiesModel("NO_EXPANSION_PLANS_MESSAGE", "Er zijn geen plannen beschikbaar"));
        nlProperties.add(new PropertiesModel("PATTERN", "MMMM dd, yyyy 'at' hh:mm a 'zone' z"));
        nlProperties.add(new PropertiesModel("INSERT_PLAN_MESSAGE", "Typ je plan"));
        nlProperties.add(new PropertiesModel("SOMETHING_WENT_WRONG_DURING_PLAN_INSERT_MESSAGE", "Er is iets misgegaan, probeer het later opnieuw"));
        nlProperties.add(new PropertiesModel("ARE_YOU_SURE_MESSAGE", "Weet je zeker dat je wilt afvinken"));
        nlProperties.add(new PropertiesModel("TO_DONE_MESSAGE", "te doen"));
        nlProperties.add(new PropertiesModel("TYPE_YES_NO_MESSAGE", "Typ y voor ja of n voor nee"));
        nlProperties.add(new PropertiesModel("ENTER_AN_EXPECTED_VALUE_MESSAGE", "Voer een verwachte waarde in"));
        nlProperties.add(new PropertiesModel("ALL_EXECUTED_PLANS_MESSAGE", "Alle uitgevoerde plannen"));
        nlProperties.add(new PropertiesModel("FIREARM_MESSAGE", "Vuurwapen"));
        nlProperties.add(new PropertiesModel("FIREARM_ID_MESSAGE", "vuurwapen-ID"));
        nlProperties.add(new PropertiesModel("FIREARM_CATEGORY_MESSAGE", "vuurwapen categorie"));
        nlProperties.add(new PropertiesModel("FIREARM_NAME_MESSAGE", "vuurwapen naam"));
        nlProperties.add(new PropertiesModel("FIREARM_AMOUNT_MESSAGE", "aantal"));
        nlProperties.add(new PropertiesModel("PRICE_PER_ITEM_MESSAGE", "prijs per stuk"));
        nlProperties.add(new PropertiesModel("FIREARM_SPECIFICATION_MESSAGE", "Vuurwapenspecificatie"));
        nlProperties.add(new PropertiesModel("CALIBER_MESSAGE", "kaliber"));
        nlProperties.add(new PropertiesModel("WEIGHT_WITHOUT_MAGAZINE_MESSAGE", "gewicht zonder magazijn"));
        nlProperties.add(new PropertiesModel("WEIGHT_WITH_EMPTY_MAGAZINE_MESSAGE", "gewicht met leeg magazijn"));
        nlProperties.add(new PropertiesModel("WEIGHT_WITH_LOADED_MAGAZINE_MESSAGE", "gewicht met geladen magazijn"));
        nlProperties.add(new PropertiesModel("MAGAZINE_CAPACITY_MESSAGE", "tijdschrift capaciteit"));
        nlProperties.add(new PropertiesModel("BARREL_LENGTH_MESSAGE", "loop lengte"));
        nlProperties.add(new PropertiesModel("TRIGGER_PULL_MESSAGE", "trekker"));
        nlProperties.add(new PropertiesModel("HAND_GUNS", "handpistolen"));
        nlProperties.add(new PropertiesModel("LONG_GUNS", "Lange geweren"));
        nlProperties.add(new PropertiesModel("INSERT_NAME_MESSAGE", "Voer de naam in van het vuurwapen dat u wilt verkopen"));
        nlProperties.add(new PropertiesModel("FIREARM_NOT_EXISTENT_MESSAGE", "Dit vuurwapen is mogelijk niet op voorraad of bestaat niet, typ een andere naam uit de lijst"));
        nlProperties.add(new PropertiesModel("INSERT_AMOUNT_MESSAGE", "Vul het aantal in dat je wilt verkopen"));
        nlProperties.add(new PropertiesModel("SELECTED_AMOUNT_NOT_AVAILABLE_MESSAGE", "Het geselecteerde aantal is niet beschikbaar, probeer het opnieuw"));
        nlProperties.add(new PropertiesModel("IN_STOCK_MESSAGE", "De vuurwapens die op voorraad zijn zijn"));
        nlProperties.add(new PropertiesModel("OUT_OF_STOCK_MESSAGE", "Sorry we zijn niet op voorraad"));
        nlProperties.add(new PropertiesModel("SOLD_FIREARM_MESSAGE", "De verkochte vuurwapens zijn"));
        nlProperties.add(new PropertiesModel("NOTHING_SOLD_MESSAGE", "Er is niets verkocht"));
        nlProperties.add(new PropertiesModel("TRANSACTION_TIME_STAMP", "Noteer dit, uw Transactie-ID:"));
        nlProperties.add(new PropertiesModel("INSERT_TRANSACTION_ID", "Voer een bestaande transactie-ID in"));
        nlProperties.add(new PropertiesModel("TRANSACTION_NOT_EXISTENT_MESSAGE", "Deze transactie bestaat niet"));
        nlProperties.add(new PropertiesModel("TOTAL_SOLD_PRICE_MESSAGE", "Totale verkochte prijs"));
        nlProperties.add(new PropertiesModel("WELCOME_MENU_MESSAGE", "Welkom bij uw toepassing voor vuurwapenverkopers"));
        nlProperties.add(new PropertiesModel("SELL_FIREARM_MESSAGE", "een vuurwapen verkopen"));
        nlProperties.add(new PropertiesModel("DISPLAY_AVAILABLE_FIREARM_MESSAGE", "om de beschikbare vuurwapens weer te geven"));
        nlProperties.add(new PropertiesModel("DISPLAY_SOLD_FIREARM_MESSAGE", "om de verkochte vuurwapens te tonen"));
        nlProperties.add(new PropertiesModel("REVIEW_DETAILS_TRANSACTION_MESSAGE", "om details van een transactie te bekijken"));
        nlProperties.add(new PropertiesModel("DISPLAY_FULL_EXPANSION_PLAN", "om het volledige uitbreidingsplan weer te geven"));
        nlProperties.add(new PropertiesModel("DISPLAY_UPCOMING_PLANS", "m het aanstaande plan weer te geven"));
        nlProperties.add(new PropertiesModel("ADD_A_PLAN_MESSAGE", "om een abonnement toe te voegen"));
        nlProperties.add(new PropertiesModel("MARK_UPCOMING_PLAN_TO_DONE_MESSAGE", "om aankomend plan als voltooid te markeren"));
        nlProperties.add(new PropertiesModel("VIEW_ALL_EXECUTED_PLANS_MESSAGE", "om alle uitgevoerde plannen te bekijken"));
        nlProperties.add(new PropertiesModel("LOG_OUT_AND_EXIT_MESSAGE", "om uit te loggen en het programma te verlaten"));
        nlProperties.add(new PropertiesModel("TYPE", "Typ"));
        return nlProperties;
    }
}
