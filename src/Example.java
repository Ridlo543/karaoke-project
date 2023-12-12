import java.util.Calendar;
import java.util.Date;

public class Example {
    public static void main(String[] args) {
        // Menggunakan java.util.Calendar
        Date currentDate = Calendar.getInstance().getTime();
        System.out.println("Tanggal saat ini (Calendar): " + currentDate);

        // Menggunakan java.util.Date
        Date date = new Date();
        System.out.println("Tanggal saat ini (Date): " + date);
    }
}