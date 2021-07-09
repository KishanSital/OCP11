package chapter19.models;

import java.io.Serializable;

public class PropertiesModel implements Serializable {
    private static final long serialVersionUID = 1625144001095L;
    private transient String key;
    private transient String value;
    private transient int test;


    public PropertiesModel(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public PropertiesModel() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return key + " = "+ value;
    }
}
