package burrellyang.calculatorapp;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class UserFunctions {

    public static String[] userFuncs;


    //constructor
    public UserFunctions(Context context){
        userFuncs = readFromFile(context);
    }

    public static void writeToFile(String[] data,Context context) {
        //accepts an array of strings which is will save as 6 functions in the txt file....

        //HAVE MADE IT SO IS ONLY SAVING THE FIRST STRING CURRENTLY............
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("userFunctions.txt", Context.MODE_PRIVATE));
            Log.e("SavingFunctions", "Attempting to save user functions");

            for(int i=0; i<data.length; i++){
                outputStreamWriter.write(data[i]+ "\n");
                Log.e("SavingFunctions", "Saved " + data[i]);
            }
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    //SOURCE
    public static String[] readFromFile(Context context) {

        String ret = "";
        String[] myStringArray = new String[6];

        try {
            InputStream inputStream = context.openFileInput("userFunctions.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                int i = 0;

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    if(i < 6){
                        myStringArray[i] = receiveString;
                    }
                    i++;
                }
                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        for(String x : myStringArray){
            System.out.println(x);
        }

        return myStringArray;
    }

    //I made it NON STATIC
    public static String[] getAllUserFunctions(){
        return userFuncs;
    }

    public static boolean[] detectVariables(String funcString){
        //assume 3 possible variables x, y, z

        boolean[] containsXYZ = new boolean[3];

        containsXYZ[0] = funcString.contains("x"); //only works if no functions contain x
        containsXYZ[1] = funcString.contains("y"); //only works if no functions contain y
        containsXYZ[2] = funcString.contains("z");

        return containsXYZ;
    }

    public static String replaceVariables(String funcString){
        boolean[] containsXYZ = detectVariables(funcString);
        if(containsXYZ[0] && containsXYZ[1]){
            return funcString;
        }
        else if(containsXYZ[0] && containsXYZ[2]){
            return funcString.replace('z', 'y');
        }
        else if(containsXYZ[1] && containsXYZ[2]){
            //changes y to x then z to y...
            //could change it so it just changes z to x
            return (funcString.replace('y','x')).replace('z', 'y');
        }
        else if(containsXYZ[2]){
            return funcString.replace('z','x');
        }
        else if(containsXYZ[1]){
            return funcString.replace('y', 'x');
        }
        else{
            //only contains x or doesn't contain a variable
            return funcString;
        }
    }

    public static int countVariables(String funcString){
        //assume 3 possible variables x, y, z
        int variableCount = 0;
        if(funcString.contains("x")){
            variableCount++;
        }
        if(funcString.contains("y")){
            variableCount++;
        }
        if(funcString.contains("z")){
            variableCount++;
        }
        return variableCount;
    }



}
