package chapter5.entities;


public class NamesEntity {

    private String  name;

    public NamesEntity() {

    }

    public NamesEntity(String name) {
        this.name = name;
    }

    public NamesEntity (NamesEntity namesEntity){
        this.name = namesEntity.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
