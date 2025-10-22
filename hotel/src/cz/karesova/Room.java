package cz.karesova;//v tomto balíčku se třída nachází

public class Room {
    //atributy třídy Room
    private int roomNumber;
    private int numberOfBeds;
    private boolean hasBalcony;
    private boolean isSeaView;
    private double pricePerNight;

    //konstruktor
    public Room(int roomNumber,int numberOfBeds, boolean hasBalcony, boolean isSeaView, double pricePerNight){
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
        this.hasBalcony = hasBalcony;
        this.isSeaView = isSeaView;
        this.pricePerNight = pricePerNight;
    }

    //gettery a settery

    public int getRoomNumber(){
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber){
        this.roomNumber = roomNumber;
    }

    public int getNumberOfBeds(){
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds){
        this.numberOfBeds = numberOfBeds;
    }

    public boolean getHasBalcony(){
        return hasBalcony;
    }

    public void setHasBalcony(boolean hasBalcony){
        this.hasBalcony = hasBalcony;
    }

    public boolean getIsSeaView(){
        return isSeaView;
    }

    public void setIsSeaView(boolean isSeaView) {
        this.isSeaView = isSeaView;
    }

    public double getPricePerNight(){
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight){
        this.pricePerNight = pricePerNight;
    }
}