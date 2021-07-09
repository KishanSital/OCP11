package chapter21.repositories;

import chapter21.models.FireArmModel;
import chapter21.services.FireArmsRepositoryService;

import java.sql.*;
import java.util.*;

public final class FireArmsRepository implements FireArmsRepositoryService {

    private Properties credentials;
    private String dbUrl;
    private static final String NOT_SOLD = "NotSold";
    private static final String SOLD = "Sold";
    private static final String TRANSACTION_ID = "transaction_id";
    private static final String SOLD_FIRE_ARM_ID = "sold_fire_arm_id";
    private static final String FIRE_ARM_ID = "fire_arm_id";
    private static final String FIRE_ARM_CATEGORY = "fire_arm_category";
    private static final String FIRE_ARM_NAME= "fire_arm_name";
    private static final String STOCK_AMOUNT= "stock_amount";
    private static final String PRICE_PER_ITEM= "price_per_item";
    private static final String CALIBER= "caliber";
    private static final String WEIGHT_WITHOUT_MAGAZINE= "weight_without_magazine";
    private static final String WEIGHT_WITH_EMPTY_MAGAZINE= "weight_with_empty_magazine";
    private static final String WEIGHT_WITH_LOADED_MAGAZINE= "weight_with_loaded_magazine";
    private static final String MAGAZINE_CAPACITY= "magazine_capacity";
    private static final String BARREL_LENGTH= "barrel_length";
    private static final String TRIGGER_PULL = "trigger_pull";


    //defensive copy on mutable objects
    public FireArmsRepository(String dbUrl, Properties credentials) {
        super();
        if (dbUrl == null || credentials == null) {
            throw new NullPointerException("Provide dbUrl and credentials");
        } else {
            this.credentials = new Properties(credentials);
            this.dbUrl = dbUrl;
        }
    }

    // changes won't be reflected on the data in the database
    public List<FireArmModel<Long, String, Integer, Double>.FireArmSpecification> getFireArmsData() {
        return retrieveFireArmsData(NOT_SOLD);
    }

    // changes won't be reflected on the data in the database
    public List<FireArmModel<Long, String, Integer, Double>.FireArmSpecification> getSoldFireArmsData() {
        return retrieveFireArmsData(SOLD);
    }

    // changes won't be reflected on the data in the database
    public Map<Long, FireArmModel<Long, String, Integer, Double>.FireArmSpecification> getAfterServiceData() {
        return retrieveSoldFireArmsData();
    }

    private Connection establishDBConnection() {
        try {
            return DriverManager.getConnection(dbUrl, credentials);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return null;
    }


    private List<FireArmModel<Long, String, Integer, Double>.FireArmSpecification> retrieveFireArmsData(String status) {
        List<FireArmModel<Long, String, Integer, Double>.FireArmSpecification> fireArmsData = new ArrayList<>();
        String getAllFireArms;

        if (status.equals(NOT_SOLD)) {
            getAllFireArms = "SELECT * FROM fire_arms ORDER BY fire_arm_id ASC ;";
        } else {
            getAllFireArms = "SELECT * FROM sold_fire_arms ORDER BY sold_fire_arm_id ASC ;";
        }

        try (Connection connection = establishDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllFireArms)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                fireArmsData.add(new FireArmModel<Long, String, Integer, Double>(resultSet.getLong((status.equals(NOT_SOLD) ? FIRE_ARM_ID : SOLD_FIRE_ARM_ID)),
                        resultSet.getString(FIRE_ARM_CATEGORY),
                        resultSet.getString(FIRE_ARM_NAME),
                        resultSet.getInt(STOCK_AMOUNT),
                        resultSet.getDouble(PRICE_PER_ITEM)).
                        new FireArmSpecification(
                        resultSet.getString(CALIBER),
                        resultSet.getString(WEIGHT_WITHOUT_MAGAZINE),
                        resultSet.getString(WEIGHT_WITH_EMPTY_MAGAZINE),
                        resultSet.getString(WEIGHT_WITH_LOADED_MAGAZINE),
                        resultSet.getInt(MAGAZINE_CAPACITY),
                        resultSet.getString(BARREL_LENGTH),
                        resultSet.getString(TRIGGER_PULL)));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return fireArmsData;
    }


    private Map<Long, FireArmModel<Long, String, Integer, Double>.FireArmSpecification> retrieveSoldFireArmsData() {

        Map<Long, FireArmModel<Long, String, Integer, Double>.FireArmSpecification> fireArmsData = new TreeMap<>();

        String getAllFireArms = "SELECT * FROM sold_fire_arms ORDER BY sold_fire_arm_id ASC;";

        FireArmModel.FireArmSpecification fireArm;

        try (Connection connection = establishDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllFireArms)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Long key = resultSet.getLong(TRANSACTION_ID);

                fireArm = new FireArmModel<Long, String, Integer, Double>(
                        resultSet.getLong(SOLD_FIRE_ARM_ID),
                        resultSet.getString(FIRE_ARM_CATEGORY),
                        resultSet.getString(FIRE_ARM_NAME),
                        resultSet.getInt(STOCK_AMOUNT),
                        resultSet.getDouble(PRICE_PER_ITEM)).
                        new FireArmSpecification(
                        resultSet.getString(CALIBER),
                        resultSet.getString(WEIGHT_WITHOUT_MAGAZINE),
                        resultSet.getString(WEIGHT_WITH_EMPTY_MAGAZINE),
                        resultSet.getString(WEIGHT_WITH_LOADED_MAGAZINE),
                        resultSet.getInt(MAGAZINE_CAPACITY),
                        resultSet.getString(BARREL_LENGTH),
                        resultSet.getString(TRIGGER_PULL));

                fireArmsData.put(key, fireArm);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return fireArmsData;
    }

    public int[] insertFireArms(FireArmModel<Long, String, Integer, Double>.FireArmSpecification... fireArms) throws SQLException {

        var insertFireArmsSql = "INSERT INTO fire_arms VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ;";


        try (Connection connection = establishDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertFireArmsSql)) {
            for (FireArmModel<Long, String, Integer, Double>.FireArmSpecification fireArm : fireArms) {
                preparedStatement.setLong(1, fireArm.getFireArmId());
                preparedStatement.setString(2, fireArm.getFireArmCategory());
                preparedStatement.setString(3, fireArm.getFireArmName());
                preparedStatement.setInt(4, fireArm.getStockAmount());
                preparedStatement.setDouble(5, fireArm.getPricePerItem());
                preparedStatement.setString(6, fireArm.getCaliber());
                preparedStatement.setString(7, fireArm.getWeightWithoutMagazine());
                preparedStatement.setString(8, fireArm.getWeightWithEmptyMagazine());
                preparedStatement.setString(9, fireArm.getWeightWithLoadedMagazine());
                preparedStatement.setInt(10, fireArm.getMagazineCapacity());
                preparedStatement.setString(11, fireArm.getBarrelLength());
                preparedStatement.setString(12, fireArm.getTriggerPull());
                preparedStatement.addBatch();

            }
            int[] result = preparedStatement.executeBatch();
            return result;
        }
    }


    public FireArmModel<Long, String, Integer, Double>.FireArmSpecification findFireArm(String fireArmName) throws SQLException {

        String findFireArmSql = "SELECT * FROM fire_arms WHERE fire_arm_name = ? ;";

        try (Connection connection = establishDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findFireArmSql)) {
            preparedStatement.setString(1, fireArmName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new FireArmModel<Long, String, Integer, Double>(
                        resultSet.getLong(FIRE_ARM_ID),
                        resultSet.getString(FIRE_ARM_CATEGORY),
                        resultSet.getString(FIRE_ARM_NAME),
                        resultSet.getInt(STOCK_AMOUNT),
                        resultSet.getDouble(PRICE_PER_ITEM)).new FireArmSpecification(
                        resultSet.getString(CALIBER),
                        resultSet.getString(WEIGHT_WITHOUT_MAGAZINE),
                        resultSet.getString(WEIGHT_WITH_EMPTY_MAGAZINE),
                        resultSet.getString(WEIGHT_WITH_LOADED_MAGAZINE),
                        resultSet.getInt(MAGAZINE_CAPACITY),
                        resultSet.getString(BARREL_LENGTH),
                        resultSet.getString(TRIGGER_PULL));
            } else {
                return null;
            }

        }

    }

    public int insertSoldFireArm(FireArmModel<Long, String, Integer, Double>.FireArmSpecification fireArm, Long transactionId) throws SQLException {

        var insertFireArmsSql = "INSERT INTO sold_fire_arms VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ;";

        int parameterIndex = 0;

        try (Connection connection = establishDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertFireArmsSql)) {
            preparedStatement.setLong(++parameterIndex, transactionId);
            preparedStatement.setLong(++parameterIndex, fireArm.getFireArmId());
            preparedStatement.setString(++parameterIndex, fireArm.getFireArmCategory());
            preparedStatement.setString(++parameterIndex, fireArm.getFireArmName());
            preparedStatement.setInt(++parameterIndex, fireArm.getStockAmount());
            preparedStatement.setDouble(++parameterIndex, fireArm.getPricePerItem());
            preparedStatement.setString(++parameterIndex, fireArm.getCaliber());
            preparedStatement.setString(++parameterIndex, fireArm.getWeightWithoutMagazine());
            preparedStatement.setString(++parameterIndex, fireArm.getWeightWithEmptyMagazine());
            preparedStatement.setString(++parameterIndex, fireArm.getWeightWithLoadedMagazine());
            preparedStatement.setInt(++parameterIndex, fireArm.getMagazineCapacity());
            preparedStatement.setString(++parameterIndex, fireArm.getBarrelLength());
            preparedStatement.setString(++parameterIndex, fireArm.getTriggerPull());
            preparedStatement.addBatch();

            int result = preparedStatement.executeUpdate();
            return result;
        }
    }

    public int updateStockAmount(FireArmModel<Long, String, Integer, Double>.FireArmSpecification fireArm,
                                 int availableAmount) throws SQLException {

        String updateSql = "UPDATE fire_arms SET stock_amount = ? WHERE fire_arm_id = ?;";
        int parameterIndex = 0;

        try (Connection connection = establishDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setInt(++parameterIndex, availableAmount);
            preparedStatement.setLong(++parameterIndex, fireArm.getFireArmId());
            int resultSet = preparedStatement.executeUpdate();
            return resultSet;
        }
    }



}
