
package com.killua.cald;

import java.util.concurrent.ConcurrentHashMap;
import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;

import utils.Utils;

// CALD weights and circuit distribution handling detection
class Killua implements Runnable {
    private static final String miscLocation = "/usr/share/misc";
    private static final File myDir = new File(miscLocation);
    private static final String[] ext = new String[] { "cald" };
    private static final ConcurrentHashMap<File, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        List<File> images = (List<File>) FileUtils.listFiles(myDir, ext, false);

        Killua o = new Killua();
        Thread thread = new Thread(o);

        for (Iterator<File> i = images.iterator(); i.hasNext();) {
            File file = i.next();
            String fileName = file.getName();

            if (!fileName.isEmpty()) {
                map.put(file, file.hashCode());
            }
        }

        thread.start();
    }

    public void run() {
        int size = map.size();
        System.out.println("Circuits: " + size);
        Utils u = new Utils();

        Iterator<ConcurrentHashMap.Entry<File, Integer>> itr = map.entrySet().iterator();
        String breakers = "";

        while(itr.hasNext()) {
            ConcurrentHashMap.Entry<File, Integer> entry = itr.next();
            Integer v = entry.getValue();
            File f = entry.getKey();

            System.out.println("K = " + f.getName() + ", V = " + v);
            /*
            * TODO: store hash to determine file changes locally and match hash against
            * Build diagram in another thread based on CALD instructions
            */
            breakers += u.logicGates(v);
        }

        System.out.println(breakers);
    }
}