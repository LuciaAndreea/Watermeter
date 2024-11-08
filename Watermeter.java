import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

    public void showReading(int year, String month){
        month = month.toLowerCase();
        int indexYear = yearIndex(year);
        Integer monthIndex = months.get(month);

        //check if the month and year are valid
        if(indexYear <0 || indexYear >=coldWater.length || monthIndex ==null){
            System.out.println("The month is not valid.");
            return;
        }

        //check if there already exists a reading for that month and year
        if(coldWater[indexYear][monthIndex] ==0 && warmWater[indexYear][monthIndex]==0){
            System.out.println("Doesn't exist a reading for "+month+" "+year+".");
            return;
        }

        int previousYearIndex = monthIndex == 0 ? indexYear - 1 : indexYear;
        int previousMonthIndex = monthIndex == 0 ? 11 : monthIndex - 1;

        if(previousYearIndex < 0 || (coldWater[previousYearIndex][previousMonthIndex] == 0 && warmWater[previousYearIndex][previousMonthIndex]== 0 )){
            System.out.println("Doesn't exist a reading for the previous month.");
            return;
        }

        int currentColdWater = coldWater[indexYear][monthIndex];
        int currentWarmWater = warmWater[indexYear][monthIndex];
        int previousColdWater = coldWater[previousYearIndex][previousMonthIndex];
        int preiousWarmWater = warmWater[previousYearIndex][previousMonthIndex];

        int coldWaterIntake = currentColdWater - previousColdWater;
        int warmWaterIntake = currentWarmWater - preiousWarmWater;

        System.out.println("Intake for "+month+" "+year);
        System.out.println("Cold water: previous month = "+previousColdWater +", current month = "+currentColdWater+", intake = "+coldWaterIntake);
        System.out.println("Warm water: previous month = "+preiousWarmWater +", current month = "+currentWarmWater+", intake = "+warmWaterIntake);
    }

    public static void main(String[] args) {
        Watermeter watermeter = new Watermeter();
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("\n1. Add reading\n2. Delete reading\n3. Display intake\n4. Exit");
            System.out.println("Choose an option: ");
            int option = scanner.nextInt();

            if(option ==4){
                System.out.println("Exit the program -----");
                break;
            }

            System.out.println("Enter the year (eg 2023): ");
            int year = scanner.nextInt();
            System.out.println("Enter the month(eg jan,feb,etc..): ");
            String month = scanner.next();

            switch (option){
                case 1:
                    System.out.println("Enter the reading for cold water: ");
                    int coldWater = scanner.nextInt();
                    System.out.println("Enter the reading for warm water: ");
                    int warmWater = scanner.nextInt();
                    watermeter.addReading(year, month , coldWater , warmWater);
                    break;
                case 2:
                    watermeter.deleteReading(year , month);
                    break;
                case 3:
                    watermeter.showReading(year , month);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }
}
