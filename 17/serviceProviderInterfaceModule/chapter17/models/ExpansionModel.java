package chapter17.models;

import chapter17.utils.StringUtilsExpansionModelMessages;

//generic class
public class ExpansionModel <T extends Comparable<T>,U> implements Comparable<ExpansionModel<T,U>>{ // comparable interface for sorting

    private T plan;
    private U startDate;
    private U endDate;

    public ExpansionModel (){
    }

    public ExpansionModel(U startDate,T plan,  U endDate) {
        this.plan = plan;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ExpansionModel(ExpansionModel<T,U> expansionModel) {
        this.plan = expansionModel.getPlan();
        this.startDate = expansionModel.getStartDate();
        this.endDate = expansionModel.getEndDate();
    }

    public T getPlan() {
        return plan;
    }

    public void setPlan(T plan) {
        this.plan = plan;
    }

    public U getStartDate() {
        return startDate;

    }

    public void setStartDate(U startDate) {
        this.startDate = startDate;
    }

    public U getEndDate() {
        return endDate;
    }

    public void setEndDate(U endDate) {
        this.endDate = endDate;
    }

    public String toString() {
        return "\n"+ StringUtilsExpansionModelMessages.EXPANSION_PLAN_MESSAGE.getMessage()+":" + "\n"+
                StringUtilsExpansionModelMessages.PLAN_MESSAGE.getMessage()+" = " + plan +"\n" +
                StringUtilsExpansionModelMessages.START_DATE_MESSAGE.getMessage()+" = " + startDate + "\n" +
                StringUtilsExpansionModelMessages.END_DATE_MESSAGE.getMessage()+" = " + endDate + "\n";
    }

    public int compareTo (ExpansionModel<T,U> expansionModel) { // overridden compareTo method ,
        if (expansionModel == null){ // null check
            return 0;
        }
        // method gets called when calling the sort method on a list containing this class as object
        return this.getPlan().compareTo(expansionModel.getPlan()); // sorted ascending by plan name
    }

}


