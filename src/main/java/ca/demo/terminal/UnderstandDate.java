package ca.demo.terminal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class UnderstandDate {
    protected String date;
    protected String time;

    public UnderstandDate(String date, String time) {
        this.date = date;
        this.time = time;
    }

    public static String convertDate(String date, String time) {
        String[] vals = date.split("/");
        int D = Integer.parseInt(vals[0]);
        int M = Integer.parseInt(vals[1]);
        int Y = Integer.parseInt(vals[2]);
        String converted_date = null;
        String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

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

        String month = months[M-1];

        //time
        String result = LocalTime.parse(time, DateTimeFormatter.ofPattern("Hmm")).format(DateTimeFormatter.ofPattern("hh:mm a"));

        return (converted_date + " of " + month + " " + Y + " " + "-" + result);
    }
}
