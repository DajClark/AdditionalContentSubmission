package com.example.additionalcontentsubmission;

import android.content.Context;

import java.io.*;

public class SerializeUtility {

    public static final String FILENAME = "list.dat";

    public static <T> T deserializeObject(T object, Context context)
    {
            object = null;
            try {
                // Read data from file and initialise object with existing data.
                FileInputStream fileIn = context.openFileInput(FILENAME);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                object = (T) in.readObject();
                // Close input streams.
                in.close();
                fileIn.close();
            }catch(FileNotFoundException i){
                // Save new data if no existing file is found.
                serializeObject(object, context);
            }catch(IOException i) {
                i.printStackTrace();
            }catch(ClassNotFoundException c) {
                System.out.println("Class not found.");
                c.printStackTrace();
            }
        return object;
    }


    public static <T> T serializeObject( T object, Context context)
    {
        try {
            // Opens stream and writes object.
            FileOutputStream fileOut = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
            // Close output streams.
            out.close();
            fileOut.close();

        } catch(IOException i) {
            // Prints stack trace on exception raised.
            i.printStackTrace();
        }

        return object;
    }

}
