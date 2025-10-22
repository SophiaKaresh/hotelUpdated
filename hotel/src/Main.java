//import tříd z balíčku cz.karesova
import cz.karesova.Booking;
import cz.karesova.BookingManager;
import cz.karesova.Guest;
import cz.karesova.Room;

import java.time.LocalDate;//import třídy pro práci s datumami
import java.util.ArrayList;//import třídy Arraylist
import java.util.List;//import obecného typu seznamu

public class Main {
    public static void main(String[] args) {//static znamená, že metoda patří třídě a né jen objektu
        ArrayList<Booking> bookings = fillBookings();//seznam všech rezervací - každý prvek seznamu je objekt booking

        BookingManager manager = new BookingManager();//proměnná manager díky které budu moci volat metody tříd
        for (Booking booking : bookings) {//vezme rezervaci z bookings
            manager.addBooking(booking);//přidá jí do manageru(cyklus)
        }

        //výpis bookingů s českým formátem data narození
        for (Booking booking : bookings) {
            System.out.println("Pokoj č. " + booking.getRoom().getRoomNumber());

            //Host 1 - český formát datumu narození
            LocalDate dateBirthGuest1 = booking.getGuest().getBirthDate();
            System.out.println("Host 1: " + booking.getGuest().getFirstName() + " " + booking.getGuest().getLastName()
                    + " (" + dateBirthGuest1.getDayOfMonth() + ". " + dateBirthGuest1.getMonthValue() + ". " + dateBirthGuest1.getYear() + ")");

            //Host 2 - český formát datumu narození
            if (booking.getOtherGuests() != null) {//pokud otherGuests nejsou v nule
                for (Guest guest : booking.getOtherGuests()) {//zpracuje každého druhého hosta postupně
                    LocalDate dateBirthGuest2 = guest.getBirthDate();
                    System.out.println("Host 2: " + guest.getFirstName() + " " + guest.getLastName()
                            + " (" + dateBirthGuest2.getDayOfMonth() + ". " + dateBirthGuest2.getMonthValue() + ". " + dateBirthGuest2.getYear() + ")");

                    //Tisk informací o bookingách
                    System.out.println("Termín: " + booking.getStartDate() + " - " + booking.getEndDate());
                    System.out.println("Typ pobytu:" + (booking.getIsBusinessTrip() ? "služební" : booking.getIsVacation() ? "rekreační" : "neuveden"));
                    System.out.println();
                }
            }
        }

        String symbol = "=".repeat(40);//v proměnné symbol je vytvořen řetězec se symbolem rovná se 40krát
        System.out.println(symbol);
        System.out.println("Úkol: testovací data a statistiky");
        System.out.println(symbol);

        System.out.println("Počet pracovních pobytů: " + manager.getNumberOfWorkingBookings() + "\n");
        System.out.println("Průměrný počet hostů na rezervaci: " + manager.getAverageGuests() + "\n");
        System.out.print("Prvních osm rekreačních rezervací:\n");
        manager.getTopNHolidayBookings(8);//"volání metody" --> do n parametru se vložilo číslo 8 dle zadání
        System.out.println();//vytiskne prázdný řádek
        manager.printGuestStatistics();//volání metody pro statistiku
        System.out.println();
        System.out.println("Počet pracovních pobytů: " + manager.getNumberOfWorkingBookings() + "\n");
        System.out.println();

        System.out.println("Formátovaný výpis všech rezervací v systému:");
        for (Booking bookingFormat : bookings) {
            System.out.println(bookingFormat.getFormattedSummary());
        }
        System.out.println();
        System.out.println("Celkový počet rezervací je " + bookings.size() + ".");
    }

    private static ArrayList<Booking> fillBookings() {
        ArrayList<Booking> bookings = new ArrayList<>();

        Room room1 = new Room(1, 1, true, true, 1000.0);
        Room room2 = new Room(2, 1, true, true, 1000.0);
        Room room3 = new Room(3, 3, false, true, 2400.0);

        Guest karel1990 = new Guest("Karel", "Dvořák", LocalDate.of(1990, 5, 15));
        Guest karel1979 = new Guest("Karel", "Dvořák", LocalDate.of(1979, 1, 3));
        Guest karolina1992 = new Guest("Karolína", "Tmavá", LocalDate.of(1992, 7, 5));

        bookings.add(new Booking(room3, karel1990, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 7), true, false));
        bookings.add(new Booking(room2, karel1979, LocalDate.of(2023, 7, 18), LocalDate.of(2023, 7, 21), false, true));


        //booking pro pár Karolína a Karel
        List<Guest> otherGuests = new ArrayList<>();
        otherGuests.add(karel1979);//přidání hosta Karla do otherGuests
        bookings.add(new Booking(room3, karolina1992, otherGuests, LocalDate.of(2023, 8, 1), LocalDate.of(2023, 8, 31), true, false));

        //10 bookingů, dlouhých 2 dny pro hosty paní Karolíny
        for (int i = 0; i < 10; i++) {//úvodní příkaz - výchozí hodnota nula, podmínka i menší než 10, pokud ano přidej
            /*plusDays funguje takto:
            1.8.2023 --> i=0 * 2 ==0  - první den startu je 1.8.2023
            po tomto proběhne iterace a i se zvýší --> i=1 * 2 ==2 - druhý den rezervace je počítán takto:
            start 1.8.2023 + 2 = 3.8.2023
            u dalších dní to funguje stejně...
             */
            LocalDate start = LocalDate.of(2023, 8, 1).plusDays(i * 2);
            LocalDate end = start.plusDays(1);//end je vždy o jeden den později než start
            bookings.add(new Booking(room2, karolina1992,start, end, false, true));
        }
        return bookings;//tento cyklus pojede dokud bude i<10 (0,1,2,3,4,5,6,7,8,9) --> cyklus bude 10x
    }
}