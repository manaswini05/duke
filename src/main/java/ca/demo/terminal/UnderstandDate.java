package ca.demo.terminal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/* This class converts the date entered in integer format to words i.e. 11/9/2019 as 11th of September 2019 and 24hr
    timing to 12hr timing. It then concatenates the two pieces and returns the complete string.
 */

public class UnderstandDate {
    protected String date;
    protected String time;

    /*
        @param date
        @param time
     */
    public UnderstandDate(String date, String time) {
        this.date = date;
        this.time = time;
    }

    /*
    The function changes the format of the date and time in the desired format and returns a string.
    @param date the date by which the deadline is due or the event is on
    @param time the time by which the deadline is due or the event is at
    @return string of date and time converted in the required format
     */
    public static String convertDate(String date, String time) {
        String[] vals = date.split("/"); //the date is split whenever there is a backslash
        int D = Integer.parseInt(vals[0]); //first part of the split string is the day
        int M = Integer.parseInt(vals[1]); //second part of the split string is the month
        int Y = Integer.parseInt(vals[2]); //third part of the split string is the year
        String converted_date = null;
        //the months are stored in the form of an array to access them based on int M.
        String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        //The following if-else conditions are to add a suffix to the date values.
        if(D == 1 || D == 21 || D == 31 ) {
            converted_date = D + "st";
        }

        else if(D == 2 || D == 22) {
            converted_date = D + "nd";
        }

        else if(D == 3) {
            converted_date = D + "rd";
        }

        else {
            converted_date = D + "th";
        }

        //Based on the integer M, the particular month is obtained by accessing M-1th index of the array.
        String month = months[M-1];

        //time
        //this Java function converts 24hr time to 12hr time
        String result = LocalTime.parse(time, DateTimeFormatter.ofPattern("Hmm")).format(DateTimeFormatter.ofPattern("hh:mm a"));

        //a string with all the converted date and time is concatenated and returned.
        return (converted_date + " of " + month + " " + Y + " " + "-" + result);
    }
}
