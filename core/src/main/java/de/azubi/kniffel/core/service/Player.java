package de.azubi.kniffel.core.service;

public class Player {
    private String surname;
    private String name;
    private int number;

    public Player(String surname, String name, int number ) {
        this.surname = surname;
        this.name = name;
        this.number = number;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Player{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}


