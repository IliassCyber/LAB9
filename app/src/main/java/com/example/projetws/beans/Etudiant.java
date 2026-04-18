package com.example.projetws.beans;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

public class Etudiant {
    private int id;
    
    @SerializedName("nom")
    private String lastName;
    
    @SerializedName("prenom")
    private String firstName;
    
    @SerializedName("ville")
    private String city;
    
    @SerializedName("sexe")
    private String gender;

    public Etudiant() {}

    public Etudiant(int id, String lastName, String firstName, String city, String gender) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.gender = gender;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    @NonNull
    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", city='" + city + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
