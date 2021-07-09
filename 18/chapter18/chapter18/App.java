package chapter18;

import chapter18.models.*;
import chapter18.repositories.*;
import chapter18.serviceImpl.*;
import chapter18.services.*;
import chapter18.utils.*;
import chapter18.views.*;

import mypackage.application.*;
import mypackage.models.*;
import mypackage.services.*;
import mypackage.utils.*;

import java.time.*;
import java.util.*;
import java.util.concurrent.*;


//implementing the Runnable interface
public class App implements Runnable {

    private boolean isLoginTaskCompleted, isWelcomeMessageTaskCompleted;

    private FireArmsService fireArmServiceImpl;
    private ExpansionService expansionServiceImpl;
    private FireArmsView fireArmsView;
    private ExpansionView expansionView;
    private LoggedInMenuView loggedInMenuView;


    @SafeVarargs
    // can be applied to methods and constructors that cannot be overridden, and must contain varargs in parameter list

    public static void main(String... args) throws InterruptedException {
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println("Using Threads to accomplish certain tasks");
        System.out.println("---------------------------------------------------------------------------------------------------");
        App app = new App();

        //working with Runnable, first overriding the run() method
        Runnable welcomeMessageTask = app::startWelcomeMessageTask;
        Runnable loginTask = app::startLoginTask;
        Runnable createServicesImplementationTask = app::startCreationServicesImplementationTask;

        // working with Callable, which returns a generic value
        Callable<String> insertFireArmsTask = app::insertFireArmsTask;
        Callable<String> insertExpansionPlanTask = app::insertExpansionPlanTask;
        Callable<String> createFireArmViewTask = app::createFireArmsViewTask;
        Callable<String> createExpansionViewTask = app::createExpansionViewTask;
        Callable<String> createLoggedInMenuViewTask = app::createLoggedInMenuViewTask;
        Callable<String> displayMenuTask = app::displayMenuTask;

        //working with ExecutorService
        ExecutorService executorService = null;

        //working with ScheduledExecutorService
        ScheduledExecutorService scheduledExecutorService = null;

        welcomeMessageThread(app);

        loginThread(app, loginTask);

        standardInsertThread(executorService,createServicesImplementationTask, insertFireArmsTask, insertExpansionPlanTask);

        viewThread(executorService,createFireArmViewTask, createExpansionViewTask, createLoggedInMenuViewTask);


        displayMenuThread(scheduledExecutorService,displayMenuTask);

    }

    private static void displayMenuThread(ScheduledExecutorService scheduledExecutorService,
                                          Callable<String> displayMenuTask) throws InterruptedException {
        // using ScheduledExecutorService to create a single scheduled thread
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        try {
            // scheduled task that will execute after 1 second
            ScheduledFuture<?> displayMenuTaskFuture = scheduledExecutorService.schedule(displayMenuTask, 1, TimeUnit.SECONDS);
            try {
                System.out.println(displayMenuTaskFuture.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } finally {
            scheduledExecutorService.shutdown();
        }

        while (!scheduledExecutorService.isTerminated()) {
            System.out.println("Waiting for scheduledExecutorService to complete display menu task");
            //waiting 1 milli sec for the scheduled tasks to finish, each time the while block evaluates to false
            scheduledExecutorService.awaitTermination(1, TimeUnit.MILLISECONDS);
        }

        System.out.println("scheduledExecutorService's task completed\n");
    }

    private static void viewThread(ExecutorService executorService,
                                   Callable<String> createFireArmViewTask,
                                   Callable<String> createExpansionViewTask,
                                   Callable<String> createLoggedInMenuViewTask) throws InterruptedException {
        executorService = Executors.newSingleThreadExecutor();
        try {
            //creating a list of all callable tasks which return a Future<String> object
            //invokeAll method will call each task in the given order and waits for each of the task to complete
            List<Future<String>> listFuture = executorService.invokeAll(List.of(createFireArmViewTask,
                    createExpansionViewTask,
                    createLoggedInMenuViewTask));
            for (Future<String> eachTask : listFuture) {
                System.out.println(eachTask.get());
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        if (executorService.isTerminated()) {
            System.out.println("Waiting for executorService thread to complete createFireArmViewTask,createExpansionViewTask,createLoggedInMenuViewTask respectively");
            //waiting 500 nano sec for all tasks to finish, each time the while block evaluates to false
            executorService.awaitTermination(500, TimeUnit.NANOSECONDS);
        }
        System.out.println("executorService's tasks completed");
    }

    private static void standardInsertThread(ExecutorService executorService,
                                             Runnable createServicesImplementationTask,
                                             Callable<String> insertFireArmsTask,
                                             Callable<String> insertExpansionPlanTask) throws InterruptedException {
        // using ExecutorService to create a single thread
        executorService = Executors.newSingleThreadExecutor();
        //this will happen in ordered fashion, because all tasks are in a single thread
        try {

            //the execute method from ExecutorService requires a Runnable functional interface
            executorService.execute(createServicesImplementationTask);
            // using submit method with an runnable task and returns a Future object
            Future<?> futureOfInsertFireArmsTask = executorService.submit(insertFireArmsTask);
            Future<?> futureOfInsertExpansionPlanTask = executorService.submit(insertExpansionPlanTask);
            try {
                executorService.awaitTermination(1, TimeUnit.SECONDS);
                System.out.println(futureOfInsertFireArmsTask.get(1, TimeUnit.SECONDS));
                System.out.println(futureOfInsertExpansionPlanTask.get(1, TimeUnit.SECONDS));
                // if task is not done within 1 second or wasn't able to execute either of these exceptions are thrown
            } catch (ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
        } finally {
            //shutting down the executorService task
            executorService.shutdown();
        }
        while (!executorService.isTerminated()) {
            System.out.println("Waiting for executorService thread to complete createServicesImplementationTask, insertFireArmsTask, insertExpansionPlanTask respectively");
            //waiting 1 milli sec for all tasks to finish, each time the while block evaluates to false
            executorService.awaitTermination(1, TimeUnit.MILLISECONDS);
        }

        System.out.println("\nTasks from executorService thread completed\n");
    }

    private static void loginThread(App app,
                                    Runnable loginTask) throws InterruptedException {
        new Thread(loginTask).start();
        // polling
        while (!app.isLoginTaskCompleted) {
            System.out.println("Waiting for login task to be completed");
            //requests current thread of execution to rest for 2 seconds
            Thread.sleep(2000);
        }
        System.out.println("Login task completed\n");
    }

    private static void welcomeMessageThread(App app) throws InterruptedException {
        // working with Thread (requires a runnable functional interface)
        new Thread(app).start();

        // polling
        while (!app.isWelcomeMessageTaskCompleted) {
            System.out.println("Waiting for welcome display message");
            //requests current thread of execution to rest for 1 second
            Thread.sleep(1000);
        }
        System.out.println("Welcome message task completed\n");
    }

    //overriding the run method
    @Override
    public void run() {
        startWelcomeMessageTask();
    }

    private void startWelcomeMessageTask() {
        StringUtilsMyPackage.displayWelcomeMessage();
        isWelcomeMessageTaskCompleted = true;
    }

    private void startLoginTask() {
        //Constructor reference
        UserModelWithArguments userModel = UserModel::new;
        MyPackageApplication.startLoginService(userModel.create(1L, "Kishan", "1234"));
        isLoginTaskCompleted = true;
    }


    private void startCreationServicesImplementationTask() {
        fireArmServiceImpl = new FireArmsServiceImpl(new FireArmsRepository());
        expansionServiceImpl = new ExpansionServiceImpl(new ExpansionRepository());
        System.out.println("\ncreation service implementation task completed");
    }

    private String insertFireArmsTask() {

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

        return "\nInserting firearms task completed\n";


    }

    private String insertExpansionPlanTask() {
        var zoneId = TimeZone.getDefault().toZoneId();

        expansionServiceImpl.getTodoExpansionList().offer(new ExpansionModel(ZonedDateTime.of(LocalDateTime.of(2017, Month.JANUARY, 01, 00, 00), zoneId),
                "Expand storage area",
                null));
        expansionServiceImpl.getTodoExpansionList().offer(new ExpansionModel(ZonedDateTime.of(LocalDateTime.of(2018, Month.JANUARY, 01, 00, 00), zoneId),
                "Expand variety of firearms",
                null));
        expansionServiceImpl.getTodoExpansionList().offer(new ExpansionModel(ZonedDateTime.of(LocalDateTime.of(2019, Month.JANUARY, 01, 00, 00), zoneId),
                "Employ new storage workers",
                null));

        return "Inserting expansion plan task completed\n";
    }

    private String createFireArmsViewTask() {
        fireArmsView = new FireArmsView(fireArmServiceImpl);
        return "\nCreate fire arms view task completed";
    }

    private String createExpansionViewTask() {
        expansionView = new ExpansionView(expansionServiceImpl);
        return "\nCreate expansion view task completed";
    }

    private String createLoggedInMenuViewTask() {
        loggedInMenuView = new LoggedInMenuView(fireArmsView, expansionView);
        return "\nCreate logged in menu view task completed";

    }

    private String displayMenuTask() {
        loggedInMenuView.displayMenu();
        return "\n display menu task completed";
    }


}

