package cz.karesova;//v tomto balíčku se třída nachází

import java.time.format.DateTimeFormatter;//import formátování - pro získání českého datumu
import java.util.ArrayList;//import třídy Arraylist
import java.util.List;//import obecného typu seznamu

public class BookingManager{
    //atribut třídy
    List<Booking> bookings = new ArrayList<>();

    //metoda pro vložení nové rezervace do seznamu
    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    //metoda pro získání rezervace se zadaným indexem
    public Booking getBooking(int index){
        return bookings.get(index);
    }

    //metoda pro získání seznamu rezervací
    public List<Booking> getBookings(){
        return bookings;
    }

    //metoda pro smazání seznamu rezervací
    public void clearBookings(){
        bookings.clear();
    }

    //metoda pro zjištění počtu bussiness rezervací (pracovních pobytů)
    public int getNumberOfWorkingBookings() {
        int count = 0;//výchozí hodnota
        for (Booking booking : bookings) {//projde všechny prvky v seznamu bookings
            if (booking.getIsBusinessTrip()) {//pokud je booking bussiness rezervací
                count++;//přičti jedna
            }
        }
        return count;
    }

    //metoda pro zjištění průměrného počtu hostů na rezervaci
    public double getAverageGuests() {
        if (bookings.isEmpty()) return 0;//pokud je booking seznam prádný vrať 0
        int totalGuests = 0;
        for (Booking booking : bookings) {
            totalGuests += booking.getGuestCount();//přidá do proměnné výsledky z getGuestCount
        }
        return (double) totalGuests / bookings.size();//celkový počet hostů/počtem rezervací v des.čísle
    }

    //metoda pro vrácení a vypsání ,,8mi" rekreačních rezervací
    public List<Booking> getTopNHolidayBookings(int n) {//vloží se nějaká int hodnota
        List<Booking> holidayBookings = new ArrayList<>();
        int countOfBookings = 0;
        DateTimeFormatter czechFormat = DateTimeFormatter.ofPattern("d.M.yyyy");//naformátování datumu na český

        for (Booking booking : bookings) {
            if (booking.getIsVacation()) {//pokud je rezervace rekreační
                holidayBookings.add(booking);//přidej jí do listu holidayBookings
                countOfBookings++;//k počtu rezervací se přičte číslo 1

                System.out.println("Rezervace pro: " + booking.getGuest().getFirstName() + " " + booking.getGuest().getLastName() + " (" + booking.getGuest().getBirthDate().format(czechFormat)
                + ")" + " na: 2 termín: " + booking.getStartDate().format(czechFormat) + " - " + booking.getEndDate().format(czechFormat) + " pracovní pobyt: ne ");

                if (countOfBookings == n) break;//pokud se počet rezervací rovná n, ukonči cyklus
            }
        }

        if (holidayBookings.isEmpty()) {//pokud je list holidayBookings prázdný
            System.out.println("Nebyly nalezeny žádné rekreační rezervace.");//vypiš toto
        }

        return holidayBookings;
    }
        public void printGuestStatistics(){
            int oneGuest = 0;
            int twoGuests = 0;
            int moreThanTwo = 0;

            for(Booking booking: bookings){
                int count = booking.getGuestCount();//tato proměnná se rovná getGuestCount
                if (count == 1) oneGuest++;//pokud se rovná count 1 přidej do proměnné oneGuest
                else if(count == 2) twoGuests++;//pokud se rovná count 2 přidej do proměnné twoGuest
                else if(count > 2) moreThanTwo++;//pokud je count 3 a víc přidej do proměnné moreThanTwo
            }
            System.out.println("Statistiky hostů:");
            System.out.println("Počet rezervací s jedním hostem: " + oneGuest);
            System.out.println("Počet rezervací s dvěma hosty: " + twoGuests);
            System.out.println("Počet rezervací s více hosty: " + moreThanTwo);
        }
}