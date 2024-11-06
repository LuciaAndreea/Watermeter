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

    public void addReading(int year , String month, int coldWaterReading, int warmWaterReading){
        month = month.toLowerCase();
        int indexYear = yearIndex(year);
        Integer monthIndex = months.get(month);

        //check if the month is valid
        if(indexYear <0 || indexYear >=coldWater.length || monthIndex ==null){
            System.out.println("The month is not valid.");
            return;
        }

        //check if the reading already exists
        if(coldWater[indexYear][monthIndex] !=0 || warmWater[indexYear][monthIndex] !=0){
            System.out.println("The reading for "+month+" "+year+" already exists.");
        }

        //check if the values of readings are higher than those oe month ago
        if(indexYear >0 || monthIndex>0){
            int previousYearIndex = monthIndex == 0? indexYear-1 : indexYear;
            int previousMonthIndex = monthIndex ==0? 11 : monthIndex-1;
            if(coldWaterReading < coldWater[indexYear][monthIndex] || warmWaterReading < warmWater[indexYear][monthIndex]){
                System.out.println("The values of watermeters should be higher than last month.");
                return;
            }
        }

        //if all the checking are good add the readings in coldWater and warmWater
        coldWater[indexYear][monthIndex] = coldWaterReading;
        warmWater[indexYear][monthIndex] = warmWaterReading;
        System.out.println("The reading for "+month+" "+year+" has been added.");
    }

    //method to delete an existing reading
    public void deleteReading(int year, String month){
        month = month.toLowerCase();
        int indexYear = yearIndex(year);
        Integer monthIndex = months.get(month);

        //check if the month and year are valid
        if(indexYear<0 || indexYear >=coldWater.length || monthIndex ==null){
            System.out.println("The month is not valid.");
            return;
        }

        //if there is a reading for that month and year , delete it
        coldWater[indexYear][monthIndex] = 0;
        warmWater[indexYear][monthIndex] = 0;
        System.out.println("The reading for "+month+" "+year+" has been erased.");
    }
}
