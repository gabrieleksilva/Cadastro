package com.example.cadastro.models;

public class User {
    public User(
            String fullName,
            String phone,
            String email,
            Boolean shouldJoinMailingList,
            Gender gender,
            String city,
            States states
    ) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.shouldJoinMailingList = shouldJoinMailingList;
        this.gender = gender;
        this.city = city;
        this.states = states;
    }

    final String fullName;
    final String phone;
    final String email;
    final Boolean shouldJoinMailingList;
    final Gender gender;
    final String city;
    final States states;

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", shouldJoinMailingList=" + shouldJoinMailingList +
                ", gender=" + gender +
                ", city='" + city + '\'' +
                ", states=" + states +
                '}';
    }

    public enum Gender {
        MALE("Masculino"),
        FEMALE("Feminino");

        private final String value;

        Gender(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Gender getGenderByValue(String value) {
            for (Gender gender : values()
            ) {
                if (gender.getValue().equals(value)) {
                    return gender;
                }
            }

            throw new RuntimeException("Gender does not exists");
        }
    }
}

