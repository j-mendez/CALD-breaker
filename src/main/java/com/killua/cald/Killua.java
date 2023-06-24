
package com.killua.cald;

import java.util.concurrent.ConcurrentHashMap;
import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;

// import utils.Utils;

// CALD weights and circuit distribution handling detection
class Killua implements Runnable {
    private static final File myDir = new File("/usr/share/misc");
    private static final String[] ext = new String[] { "cald" };
    private static final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        List<File> images = (List<File>) FileUtils.listFiles(myDir, ext, false);
        // Utils u = new Utils();

        Killua o = new Killua();
        Thread thread = new Thread(o);

        for (Iterator<File> i = images.iterator(); i.hasNext();) {
            File file = i.next();
            String fileName = file.getName();

            if (!fileName.isEmpty()) {
                // System.out.println("Voltage: " + u.weighVoltage(1, 2));
                map.put(fileName, file.hashCode());
            }

            System.out.println(fileName);
        }

        thread.start();
    }
    
    // determine gates
    public static String logicGates(int args) {
        if (args % 2 == 0) {
            return "⎏-";
        } else {
            return "⎐-";
        }
    }

    public void run() {
        int size = map.size();

        System.out.println("Circuits: " + size);

        String breakers = "";

        for (int b = 0; b < size; b++) {
            breakers += Killua.logicGates(b);
        }

        System.out.println(breakers);
    }
}