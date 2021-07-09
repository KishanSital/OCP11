package chapter19.models;

import chapter19.utils.StringUtilsFireArmModelMessages;

import java.io.Serializable;
import java.text.NumberFormat;

public class FireArmModel <L,S extends Comparable<S>,I,D> implements Comparable<FireArmModel<L,S,I,D>.FireArmSpecification>, Serializable {
    private static final long serialVersionUID = 1625144001099L;
    private L fireArmId;
    private S fireArmCategory;
    private S fireArmName;
    private I stockAmount;
    private D pricePerItem;

    public FireArmModel(L fireArmId,
                        S fireArmCategory,
                        S fireArmName,
                        I stockAmount,
                        D pricePerItem) {
        this.fireArmId = fireArmId;
        this.fireArmCategory = fireArmCategory;
        this.fireArmName = fireArmName;
        this.stockAmount = stockAmount;
        this.pricePerItem = pricePerItem;
        
    }

    public FireArmModel(FireArmModel<L,S,I,D> fireArmModel) {
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
        return StringUtilsFireArmModelMessages.FIREARM_MESSAGE.getMessage() +":\n" +
                StringUtilsFireArmModelMessages.FIREARM_ID_MESSAGE.getMessage()+" = " + fireArmId + "\n" +
                StringUtilsFireArmModelMessages.FIREARM_CATEGORY_MESSAGE.getMessage()+" = " + fireArmCategory + "\n" +
                StringUtilsFireArmModelMessages.FIREARM_NAME_MESSAGE.getMessage()+" = " + fireArmName + "\n" +
                StringUtilsFireArmModelMessages.FIREARM_AMOUNT_MESSAGE.getMessage()+" = " + stockAmount +"\n"+
                StringUtilsFireArmModelMessages.PRICE_PER_ITEM_MESSAGE.getMessage()+" = " + NumberFormat.getCurrencyInstance().format(pricePerItem) +"\n"; //NumberFormat
    }


    @Override
    public int compareTo(FireArmModel<L,S,I,D>.FireArmSpecification fireArm) {
        if (fireArm == null){
            return 0;
        }
        return getFireArmName().compareTo(fireArm.getFireArmName());
    }


    public class FireArmSpecification implements Comparable<FireArmModel<L,S,I,D>.FireArmSpecification>, Serializable{
        private static final long serialVersionUID = 1625147001099L;
        public S caliber;
        private S weightWithoutMagazine;
        private S weightWithEmptyMagazine;
        private S weightWithLoadedMagazine;
        private I magazineCapacity;
        private S barrelLength;
        private S triggerPull;

        public FireArmSpecification() {
        }

        public FireArmSpecification(S caliber,
                                    S weightWithoutMagazine,
                                    S weightWithEmptyMagazine,
                                    S weightWithLoadedMagazine,
                                    I magazineCapacity,
                                    S barrelLength,
                                    S triggerPull) {
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

        public L getFireArmId() {
            return FireArmModel.this.getFireArmId();
        }

        public void setFireArmId(L fireArmId) {
            FireArmModel.this.setFireArmId(fireArmId) ;
        }

        public S getFireArmCategory() {
            return FireArmModel.this.getFireArmCategory();
        }

        public void setFireArmCategory(S fireArmCategory) {
            FireArmModel.this.setFireArmCategory(fireArmCategory);
        }

        public S getFireArmName() {
            return FireArmModel.this.getFireArmName();
        }

        public void setFireArmName(S fireArmName) {
            FireArmModel.this.setFireArmName(fireArmName);
        }

        public I getStockAmount() {
            return FireArmModel.this.getStockAmount();
        }

        public void setStockAmount(I stockAmount) {
            FireArmModel.this.setStockAmount(stockAmount);
        }

        public D getPricePerItem (){
           return FireArmModel.this.getPricePerItem();
        }

        public void setPricePerItem(D pricePerItem){
            FireArmModel.this.setPricePerItem(pricePerItem);
        }

        public S getCaliber() {
            return caliber;
        }

        public void setCaliber(S caliber) {
            this.caliber = caliber;
        }

        public S getWeightWithoutMagazine() {
            return weightWithoutMagazine;
        }

        public void setWeightWithoutMagazine(S weightWithoutMagazine) {
            this.weightWithoutMagazine = weightWithoutMagazine;
        }

        public S getWeightWithEmptyMagazine() {
            return weightWithEmptyMagazine;
        }

        public void setWeightWithEmptyMagazine(S weightWithEmptyMagazine) {
            this.weightWithEmptyMagazine = weightWithEmptyMagazine;
        }

        public S getWeightWithLoadedMagazine() {
            return weightWithLoadedMagazine;
        }

        public void setWeightWithLoadedMagazine(S weightWithLoadedMagazine) {
            this.weightWithLoadedMagazine = weightWithLoadedMagazine;
        }

        public I getMagazineCapacity() {
            return magazineCapacity;
        }

        public void setMagazineCapacity(I magazineCapacity) {
            this.magazineCapacity = magazineCapacity;
        }

        public S getBarrelLength() {
            return barrelLength;
        }

        public void setBarrelLength(S barrelLength) {
            this.barrelLength = barrelLength;
        }

        public S getTriggerPull() {
            return triggerPull;
        }

        public void setTriggerPull(S triggerPull) {
            this.triggerPull = triggerPull;
        }

        @Override
        public String toString() {
            return FireArmModel.this.toString() +
                    StringUtilsFireArmModelMessages.FIREARM_SPECIFICATION_MESSAGE.getMessage()+":\n" +
                    StringUtilsFireArmModelMessages.CALIBER_MESSAGE.getMessage()+" = " + caliber + "\n" +
                    StringUtilsFireArmModelMessages.WEIGHT_WITHOUT_MAGAZINE_MESSAGE.getMessage()+" = " + weightWithoutMagazine + "\n" +
                    StringUtilsFireArmModelMessages.WEIGHT_WITH_EMPTY_MAGAZINE_MESSAGE.getMessage()+" = " + weightWithEmptyMagazine + "\n" +
                    StringUtilsFireArmModelMessages.WEIGHT_WITH_LOADED_MAGAZINE_MESSAGE.getMessage()+" = " + weightWithLoadedMagazine + "\n" +
                    StringUtilsFireArmModelMessages.MAGAZINE_CAPACITY_MESSAGE.getMessage()+" = " + magazineCapacity +"\n"+
                    StringUtilsFireArmModelMessages.BARREL_LENGTH_MESSAGE.getMessage()+" = " + barrelLength + "\n" +
                    StringUtilsFireArmModelMessages.TRIGGER_PULL_MESSAGE.getMessage()+" = " + triggerPull + "\n";
        }

        @Override
        public int compareTo(FireArmModel<L,S,I,D>.FireArmSpecification fireArm) {
            if (fireArm == null){
                return 0;
            }
            return FireArmModel.this.compareTo(fireArm);
        }

    }

    public L getFireArmId() {
        return fireArmId;
    }

    public void setFireArmId(L fireArmId) {
        this.fireArmId = fireArmId;
    }

    public S getFireArmCategory() {
        return fireArmCategory;
    }

    public void setFireArmCategory(S fireArmCategory) {
        this.fireArmCategory = fireArmCategory;
    }

    public S getFireArmName() {
        return fireArmName;
    }

    public void setFireArmName(S fireArmName) {
        this.fireArmName = fireArmName;
    }

    public I getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(I stockAmount) {
        this.stockAmount = stockAmount;
    }

    public D getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(D pricePerItem) {
        this.pricePerItem = pricePerItem;
    }
}
