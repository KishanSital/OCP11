package chapter2.Fish;

public class FishB {
	
public void garbageCollector(){
    var string = new String("A fish");
    string = null;         //  ^ object is eligible for garbage collection
    System.out.println("Stringvalue = "+ string);
}

public void primitives (){
    boolean _isTrue_ = false;
    byte $1byteValue = 0;
    short $_5shortValue = 21;
    int intValue = 01234567;
    long longValue = 1_000_000_000_0L, longValue1 = 10__00_00;
    float floatValue = 10_190_0.0f;
    double doubleValue = 10_10__10_10.00, doubleValue1 = 10_10_10_10.00d, doubleValue2 = floatValue;
    char charValue = 109, charValue1 = 'N';
    System.out.println("isTrue = "+ _isTrue_ +" ,byteValue = "+ $1byteValue +
            " ,shortValue = "+ $_5shortValue +" ,intValue = "+ intValue +" ,longValue = "+ longValue +
            " ,longValue1 = "+longValue1 +" ,floatValue = "+ floatValue+
            " ,doubleValue = "+ doubleValue +" ,doubleValue1 = "+ doubleValue1 +
            " ,doubleValue2 = "+ doubleValue2 +" ,charValue = "+ charValue +
            " ,charValue1 = " + charValue1);
}
public void workingWithVar(){
    var shark = "Shark";
    var var = "Dolphin";
    var tentacles = 2.0;
    System.out.println(shark + " + " + var +" "+ tentacles +"\n");
}
}
