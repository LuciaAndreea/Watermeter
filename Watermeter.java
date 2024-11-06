import java.util.HashMap;
import java.util.Map;

public class Watermeter {
    //add a bidimesional array to stock the readings for the last 2 years
    //the second array represents the months

    private int[][] coldWater = new int[2][12];
    private int [][] warmWater = new int[2][12];

    //using a HashMap to associate the name of months with the suitable index from the array

    private static final Map<String, Integer> months = new HashMap<>();

    static{
        months.put("jan" , 0);
        months.put("feb" , 1);
        months.put("mar" , 2);
        months.put("apr" , 3);
        months.put("may" , 4);
        months.put("jun" , 5);
        months.put("jul" , 6);
        months.put("aug" , 7);
        months.put("sep" , 8);
        months.put("oct" , 9);
        months.put("nov" , 10);
        months.put("dec" , 11);
    }

    //method to calculate the index of the year in the array
    private int yearIndex(int year){
        return year - 2023; //the year the reading starts
    }
}
