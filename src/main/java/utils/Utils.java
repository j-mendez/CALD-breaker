package utils;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    private static final HashMap<Character, String> shapes = new HashMap<Character, String>(Map.of(  
        'f', "File", 
        'A', "Register",
        'M', "Event"
    ));

    // determine the watts for the breaker
    public int weighVoltage(int x, int y) {
        return (x / y * 3);
    }
    // rotate gates
    public int rotateGate(int g) {
        return g << 1;
    } 

    // determine gates
    public static String logicGates(int args) {
        if (args % 2 == 0) {
            return "⎏-";
        } else {
            return "⎐-";
        }
    }

    // determine input type
    public static String getShape(Character args) {
        String shape = shapes.get(args);

        return shape;
    }
}