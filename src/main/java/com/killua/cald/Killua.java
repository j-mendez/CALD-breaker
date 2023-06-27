
package com.killua.cald;

import java.util.List;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FileUtils;

import utils.Utils;
import render.Draw;

// CALD weights and circuit distribution handling detection
class Killua implements Runnable {
    private static final String miscLocation = "/usr/share/misc";
    private static final File myDir = new File(miscLocation);
    private static final String[] ext = new String[] { "cald" };
    private static final ConcurrentHashMap<File, Integer> map = new ConcurrentHashMap<>();
    private static File pdf = new File("./pdfs");

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
        File pdfs = new File("./pdfs");

        if (!pdfs.exists()){
            pdfs.mkdirs();
        }

        Iterator<ConcurrentHashMap.Entry<File, Integer>> itr = map.entrySet().iterator();
        String breakers = "";

        while(itr.hasNext()) {
            ConcurrentHashMap.Entry<File, Integer> entry = itr.next();
            Integer v = entry.getValue();
            File f = entry.getKey();
            String n = f.getName();
            Draw d = new Draw(n);

            if (d.saved != null) {
                continue;
            }

            try {
                Scanner reader = new Scanner(f);

                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    String[] cld = data.split("\\s+");
                    if (!data.isEmpty()) {
                        String shape = u.getShape(cld[0].charAt(0));
                        // TODO: Draw shape to graph
                        // TODO: get direction
                        // TODO: parse instructions from file
                    }
                }
                reader.close();
            }   catch (FileNotFoundException e) {
                System.out.println("An error occurred building " + n);
                e.printStackTrace();
            }

            /*
            * TODO: store hash to determine file changes locally and match hash against
            * Build diagram in another thread based on CALD instructions
            */
            breakers += u.logicGates(v);
            System.out.println("K = " + n + ", V = " + v);
        }

        System.out.println(breakers);
    }

    // check all outputs to determine if new graph is needed
    static public Boolean checkStatus(int arg) {
        return true;
    }
}