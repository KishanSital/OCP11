package chapter12_13.models;

public class FireArmModel {
    private Long fireArmId;
    private String fireArmCategory;
    private String fireArmName;
    private int stockAmount;
    private double pricePerItem;

    public FireArmModel(Long fireArmId,
                        String fireArmCategory,
                        String fireArmName,
                        int stockAmount,
                        double pricePerItem) {
        this.fireArmId = fireArmId;
        this.fireArmCategory = fireArmCategory;
        this.fireArmName = fireArmName;
        this.stockAmount = stockAmount;
        this.pricePerItem = pricePerItem;
    }

    public FireArmModel(FireArmModel fireArmModel) {
        this.fireArmId = fireArmModel.getFireArmId();
        this.fireArmCategory = fireArmModel.getFireArmCategory();
        this.fireArmName = fireArmModel.getFireArmName();
        this.stockAmount = fireArmModel.getStockAmount();
        this.pricePerItem = fireArmModel.getPricePerItem();
    }

    public FireArmModel() {
    }


    @Override
    public String toString() {
        return "Firearm:\n" +
                "firearm id = " + fireArmId + "\n" +
                "firearm category = " + fireArmCategory + "\n" +
                "firearm name = " + fireArmName + "\n" +
                "amount = " + stockAmount +"\n"+
                "price per item = $" + pricePerItem +"\n";
    }

    public class FireArmSpecification {
        public String caliber;
        private String weightWithoutMagazine;
        private String weightWithEmptyMagazine;
        private String weightWithLoadedMagazine;
        private int magazineCapacity;
        private String barrelLength;
        private String triggerPull;

        public FireArmSpecification() {
        }

        public FireArmSpecification(String caliber,
                                    String weightWithoutMagazine,
                                    String weightWithEmptyMagazine,
                                    String weightWithLoadedMagazine,
                                    int magazineCapacity,
                                    String barrelLength,
                                    String triggerPull) {
            this.caliber = caliber;
            this.weightWithoutMagazine = weightWithoutMagazine;
            this.weightWithEmptyMagazine = weightWithEmptyMagazine;
            this.weightWithLoadedMagazine = weightWithLoadedMagazine;
            this.magazineCapacity = magazineCapacity;
            this.barrelLength = barrelLength;
            this.triggerPull = triggerPull;
        }

        public FireArmSpecification(FireArmSpecification fireArmSpecification) {
            this.caliber = fireArmSpecification.getCaliber();
            this.weightWithoutMagazine = fireArmSpecification.getWeightWithoutMagazine();
            this.weightWithEmptyMagazine = fireArmSpecification.getWeightWithEmptyMagazine();
            this.weightWithLoadedMagazine = fireArmSpecification.getWeightWithLoadedMagazine();
            this.magazineCapacity = fireArmSpecification.getMagazineCapacity();
            this.barrelLength = fireArmSpecification.getBarrelLength();
            this.triggerPull = fireArmSpecification.getTriggerPull();
        }

        public Long getFireArmId() {
            return FireArmModel.this.getFireArmId();
        }

        public void setFireArmId(Long fireArmId) {
            FireArmModel.this.setFireArmId(fireArmId) ;
        }

        public String getFireArmCategory() {
            return FireArmModel.this.getFireArmCategory();
        }

        public void setFireArmCategory(String fireArmCategory) {
            FireArmModel.this.setFireArmCategory(fireArmCategory);
        }

        public String getFireArmName() {
            return FireArmModel.this.getFireArmName();
        }

        public void setFireArmName(String fireArmName) {
            FireArmModel.this.setFireArmName(fireArmName);
        }

        public int getStockAmount() {
            return FireArmModel.this.getStockAmount();
        }

        public void setStockAmount(int stockAmount) {
            FireArmModel.this.setStockAmount(stockAmount);
        }

        public double getPricePerItem (){
           return FireArmModel.this.getPricePerItem();
        }

        public void setPricePerItem(double pricePerItem){
            FireArmModel.this.setPricePerItem(pricePerItem);
        }

        public String getCaliber() {
            return caliber;
        }

        public void setCaliber(String caliber) {
            this.caliber = caliber;
        }

        public String getWeightWithoutMagazine() {
            return weightWithoutMagazine;
        }

        public void setWeightWithoutMagazine(String weightWithoutMagazine) {
            this.weightWithoutMagazine = weightWithoutMagazine;
        }

        public String getWeightWithEmptyMagazine() {
            return weightWithEmptyMagazine;
        }

        public void setWeightWithEmptyMagazine(String weightWithEmptyMagazine) {
            this.weightWithEmptyMagazine = weightWithEmptyMagazine;
        }

        public String getWeightWithLoadedMagazine() {
            return weightWithLoadedMagazine;
        }

        public void setWeightWithLoadedMagazine(String weightWithLoadedMagazine) {
            this.weightWithLoadedMagazine = weightWithLoadedMagazine;
        }

        public int getMagazineCapacity() {
            return magazineCapacity;
        }

        public void setMagazineCapacity(int magazineCapacity) {
            this.magazineCapacity = magazineCapacity;
        }

        public String getBarrelLength() {
            return barrelLength;
        }

        public void setBarrelLength(String barrelLength) {
            this.barrelLength = barrelLength;
        }

        public String getTriggerPull() {
            return triggerPull;
        }

        public void setTriggerPull(String triggerPull) {
            this.triggerPull = triggerPull;
        }

        @Override
        public String toString() {
            return FireArmModel.this.toString() +
                    "Firearm specification:\n" +
                    "caliber = " + caliber + "\n" +
                    "weight without magazine = " + weightWithoutMagazine + "\n" +
                    "weight with empty magazine = " + weightWithEmptyMagazine + "\n" +
                    "weight with loaded magazine = " + weightWithLoadedMagazine + "\n" +
                    "magazine capacity = " + magazineCapacity +"\n"+
                    "barrel length = " + barrelLength + "\n" +
                    "trigger pull = " + triggerPull + "\n";
        }
    }

    public Long getFireArmId() {
        return fireArmId;
    }

    public void setFireArmId(Long fireArmId) {
        this.fireArmId = fireArmId;
    }

    public String getFireArmCategory() {
        return fireArmCategory;
    }

    public void setFireArmCategory(String fireArmCategory) {
        this.fireArmCategory = fireArmCategory;
    }

    public String getFireArmName() {
        return fireArmName;
    }

    public void setFireArmName(String fireArmName) {
        this.fireArmName = fireArmName;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(double pricePerItem) {
        this.pricePerItem = pricePerItem;
    }
}
