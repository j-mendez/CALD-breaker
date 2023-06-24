package utils;

public class Utils {
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
}