package chapter21.repositories;

import chapter21.models.ExpansionModel;
import chapter21.services.ExpansionRepositoryService;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Supplier;

public final class ExpansionRepository implements ExpansionRepositoryService, Cloneable {

    private Properties credentials;
    private String dbUrl;
    private static final String START_DATE = "start_date";
    private static final String PLAN = "plan";
    private static final String END_DATE = "end_date";


    //defensive copy on mutable objects
    public ExpansionRepository(String dbUrl, Properties credentials) {
        super();
        if (dbUrl == null || credentials == null) {
            throw new NullPointerException("Provide dbUrl and credentials");
        } else {
            this.credentials = new Properties(credentials);
            this.dbUrl = dbUrl;
        }

    }

    // changes won't be reflected on the data in the database
    public Queue<ExpansionModel> getTodoExpansionData() {
        return retrieveToDoExpansionData();
    }

    // changes won't be reflected on the data in the database
    public Set<ExpansionModel> getExecutedExpansionPlanData() {
        return retrieveDoneExpansionData();
    }

    private Connection establishDBConnection() {
        try {
            return DriverManager.getConnection(dbUrl, credentials);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    public int[] insertExpansionPlans(ExpansionModel<String, Date>... expansionPlans) throws SQLException {
        var insertPlansSql = "INSERT INTO expansion_plans(plan,start_date,end_date)VALUES (?,?,?)";
        try (Connection connection = establishDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertPlansSql)) {
                for (ExpansionModel<String, Date> expansionPlan : expansionPlans) {
                    preparedStatement.setString(1, expansionPlan.getPlan());
                    preparedStatement.setDate(2, expansionPlan.getStartDate());
                    preparedStatement.setDate(3, expansionPlan.getEndDate());
                    preparedStatement.addBatch();
                }
                int[] result = preparedStatement.executeBatch();
                return result;
        }
    }

    public ExpansionModel<String, LocalDate> markPlanDone() throws SQLException {
        ExpansionModel<String, LocalDate> followingPlan = retrieveTheNextPlan();
        var insertPlansSql = "INSERT INTO executed_expansion_plans(plan,start_date,end_date) VALUES (?,?,?);";
        var deletePlanSql = "DELETE FROM expansion_plans WHERE plan_id = (SELECT  plan_id FROM  (SELECT  * FROM  expansion_plans ORDER  BY plan_id ASC ) ns LIMIT 1);";
        var newlyExecutedPlanSql = "SELECT * FROM executed_expansion_plans having MAX(executed_plan_id);";
        try (Connection connection = establishDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertPlansSql);
             PreparedStatement deletePreparedStatement = connection.prepareStatement(deletePlanSql);
             PreparedStatement newlyExecutedPlanPreparedStatement = connection.prepareStatement(newlyExecutedPlanSql)) {
            preparedStatement.setString(1, followingPlan.getPlan());
            preparedStatement.setDate(2, java.sql.Date.valueOf(followingPlan.getStartDate()));
            preparedStatement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            preparedStatement.execute();
            deletePreparedStatement.execute();
            ResultSet resultSet = newlyExecutedPlanPreparedStatement.executeQuery();
            if (resultSet.next()) {
                return new ExpansionModel<String, LocalDate>(
                        resultSet.getDate(START_DATE).toLocalDate(),
                        resultSet.getString(PLAN),
                        resultSet.getDate(END_DATE).toLocalDate());
            }
        }
        return null;
    }

    public ExpansionModel<String, LocalDate> retrieveTheNextPlan() {
        String sql = "SELECT * FROM expansion_plans having MIN(plan_id)";
        try (Connection connection = establishDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new ExpansionModel<String, LocalDate>(
                        resultSet.getDate(START_DATE).toLocalDate(),
                        resultSet.getString(PLAN),
                        null);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    private Queue<ExpansionModel> retrieveToDoExpansionData() {
        Queue<ExpansionModel> todoExpansionData = new LinkedList<>();
        String getAllPlansSql = "SELECT * FROM expansion_plans ORDER BY plan_id ASC";

        try (Connection connection = establishDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllPlansSql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                todoExpansionData.offer(new ExpansionModel(
                        resultSet.getDate(START_DATE).toLocalDate(),
                        resultSet.getString(PLAN),
                        resultSet.getDate(END_DATE)));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return todoExpansionData;

    }

    private Set<ExpansionModel> retrieveDoneExpansionData() {
        Set<ExpansionModel> executedPlans = new TreeSet<>();
        String getAllExecutedPlansSql = "SELECT * FROM executed_expansion_plans ORDER BY executed_plan_id ASC";

        try (Connection connection = establishDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllExecutedPlansSql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                executedPlans.add(new ExpansionModel(
                        resultSet.getDate(START_DATE).toLocalDate(),
                        resultSet.getString(PLAN),
                        resultSet.getDate(END_DATE).toLocalDate()));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return executedPlans;

    }

    public String deleteTables() {
        String dropPlansSql = "DROP TABLE firearmsapp.executed_expansion_plans,firearmsapp.expansion_plans,firearmsapp.fire_arms,firearmsapp.sold_fire_arms; ";
        String dropProcedure = "DROP PROCEDURE CreateTables;";

        try (Connection connection = establishDBConnection();
             PreparedStatement dropPlansPreparedStatement = connection.prepareStatement(dropPlansSql);
             PreparedStatement dropProcedurePreparedStatement = connection.prepareStatement(dropProcedure)) {
            dropPlansPreparedStatement.executeUpdate();
            dropProcedurePreparedStatement.executeUpdate();
            return "Tables deleted successfully";
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return "Something went wrong whilst deleting tables";
    }

    @Override
    public String createTables() {
        if (createTablesProcedure() && callCreateTablesProcedure()) {
            return "Tables created";
        }
        return "Something went wrong whilst creating the tables";
    }

    private boolean createTablesProcedure() {
        String createTablesProcedure =
                " CREATE PROCEDURE CreateTables()" +
                        " BEGIN" +
                        " CREATE TABLE  expansion_plans (plan_id  int NOT NULL AUTO_INCREMENT, plan  varchar(255) DEFAULT NULL, start_date  date DEFAULT NULL, end_date  date DEFAULT NULL,PRIMARY KEY ( plan_id ));" +
                        " CREATE TABLE  executed_expansion_plans  ( executed_plan_id  int NOT NULL AUTO_INCREMENT, plan  varchar(255) DEFAULT NULL, start_date  date DEFAULT NULL, end_date  date DEFAULT NULL,PRIMARY KEY ( executed_plan_id ));" +
                        " CREATE TABLE  fire_arms  ( fire_arm_id  bigint NOT NULL, fire_arm_category  varchar(255) DEFAULT NULL, fire_arm_name  varchar(255) DEFAULT NULL, stock_amount  int DEFAULT NULL, price_per_item  double DEFAULT NULL, caliber  varchar(255) DEFAULT NULL, weight_without_magazine  varchar(255) DEFAULT NULL, weight_with_empty_magazine  varchar(255) DEFAULT NULL, weight_with_loaded_magazine  varchar(255) DEFAULT NULL, magazine_capacity  int DEFAULT NULL, barrel_length  varchar(255) DEFAULT NULL, trigger_pull  varchar(255) DEFAULT NULL,PRIMARY KEY ( fire_arm_id ));" +
                        " CREATE TABLE  sold_fire_arms  ( transaction_id  bigint NOT NULL, sold_fire_arm_id  bigint NOT NULL, fire_arm_category  varchar(255) DEFAULT NULL, fire_arm_name  varchar(255) DEFAULT NULL, stock_amount  int DEFAULT NULL, price_per_item  double DEFAULT NULL, caliber  varchar(255) DEFAULT NULL, weight_without_magazine  varchar(255) DEFAULT NULL, weight_with_empty_magazine  varchar(255) DEFAULT NULL, weight_with_loaded_magazine  varchar(255) DEFAULT NULL, magazine_capacity  int DEFAULT NULL, barrel_length  varchar(255) DEFAULT NULL, trigger_pull  varchar(255) DEFAULT NULL,PRIMARY KEY ( transaction_id ));" +
                        " END;";


        try (Connection connection = establishDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(createTablesProcedure)) {
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    private boolean callCreateTablesProcedure() {
        String callProcedure = "{CALL CreateTables()}";
        try (Connection connection = establishDBConnection();
             CallableStatement callableStatement = connection.prepareCall(callProcedure)) {
            callableStatement.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

}
