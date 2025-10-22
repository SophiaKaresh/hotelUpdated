package cz.karesova; //v tomto balíčku se třída nachází

import java.time.LocalDate; //importování třídy pro práci s datumami

public class Guest {
    //atributy třídy Guest
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    //konstruktor
    public Guest(String firstName, String lastName, LocalDate birthDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    //gettery a settery
    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public LocalDate getBirthDate(){
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate){
        this.birthDate = birthDate;
    }
}