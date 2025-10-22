package cz.karesova;//v tomto balíčku se třída nachází

import java.time.LocalDate;//import třídy pro práci s datumami
import java.time.format.DateTimeFormatter;//import formátování - pro získání českého datumu
import java.time.temporal.ChronoUnit;//import třídy pro výpočet rozdílů mezi daty
import java.util.List;//import obecného typu seznamu

public class Booking {
    /*Atributy pro třídu booking,
    * první atribut je použit z třídy Guest
    * druhý atribut je použit z trídy Room
    */
    private Room room;
    private Guest guest;
    private List<Guest> otherGuests;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isBusinessTrip;
    private boolean isVacation;

    //konstruktor pro jednoho hosta
    public Booking (Room room, Guest guest, LocalDate startDate, LocalDate endDate, boolean isBusinessTrip, boolean isVacation){
        this.room = room;
        this.guest = guest;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isBusinessTrip = isBusinessTrip;
        this.isVacation = isVacation;
    }

    //konstruktor pro dva hosty(společná rezervace)
    public Booking(Room room, Guest guest, List<Guest> otherGuests, LocalDate startDate, LocalDate endDate, boolean isBusinessTrip, boolean isVacation){
        this.room = room;
        this.guest = guest;
        this.otherGuests = otherGuests;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isBusinessTrip = isBusinessTrip;
        this.isVacation = isVacation;

    }

    //gettery a settery
    public Room getRoom(){
        return room;
    }
    public void setRoom(Room room){
        this.room = room;
    }

    public Guest getGuest(){
        return guest;
    }
    public void setGuest(Guest guest){
        this.guest = guest;
    }

    public List<Guest> getOtherGuests(){
        return otherGuests;
    }
    public void setOtherGuests(List<Guest> otherGuests){
        this.otherGuests= otherGuests;
    }

    public LocalDate getStartDate(){
        return startDate;
    }
    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }
    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

    public boolean getIsBusinessTrip(){
        return isBusinessTrip;
    }
    public void setIsBussinessTrip(boolean isBusinessTrip){
        this.isBusinessTrip = isBusinessTrip;
    }

    public boolean getIsVacation(){
        return isVacation;
    }
    public void setIsVacation(boolean isVacation){
        this.isVacation = isVacation;
    }

    //metoda pro třídu BookingManagera
    public int getGuestCount(){
        int count = 1; //hlavní host
        if (otherGuests != null) {//pokud se otherGuest nerovná nule
            count++;//přičte další hosty
        }
        return count;//vrací počet hostů
    }

    //metoda pro zjištění počtu nocí pro danou rezervaci
    public int getBookingLength(){
        //rozdíl mezi daty(end se nepočítá jako noc)
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }

    //metoda pro spočítání celkové ceny rezervace
    public double getTotalprice(){
        return room.getPricePerNight() * getBookingLength();
    }

    //metoda pro formátovaný výpis
    public String getFormattedSummary(){
        DateTimeFormatter czechFormat = DateTimeFormatter.ofPattern("d.M.yyyy");//naformátování č. datumu pomocí třídy

        String formattedStart = startDate.format(czechFormat);
        String formattedEnd = endDate.format(czechFormat);

        String guestInfo = guest.getFirstName() + " " + guest.getLastName() + " (" + guest.getBirthDate().format(czechFormat) + ") ";

        String formatted = formattedStart + " až " + formattedEnd + ":" + " " + guestInfo + " [" + getGuestCount() + ", " + (room.getIsSeaView() ? "ano" : "ne") +
                "] za " + (int) getTotalprice() + " Kč";

        return formatted;
    }

}
