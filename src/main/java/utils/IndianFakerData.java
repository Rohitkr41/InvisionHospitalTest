package utils;

import java.time.LocalDate;
import java.util.Locale;

import com.github.javafaker.Faker;

public class IndianFakerData {

    private static final Faker faker = new Faker(new Locale("en", "IN"));

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getLastName() {
        return faker.name().lastName();
    }

    public static String getNextOfKin() {
        return faker.name().firstName();
    }

    // Indian Mobile Number (10 digits)
    public static String getPhoneNumber() {
        return "9" + faker.number().digits(9);
    }

    public static String getHouseAddress() {
        return faker.address().buildingNumber() + " "
                + faker.address().streetName();
    }

    // 12 digit Aadhaar-like number
    public static String getAadhaarNumber() {
        return faker.number().digits(12);
    }

    public static int getAge() {
        return faker.number().numberBetween(18, 60);
    }

    public static String getDOBFromAge() {

        int age = getAge();

        int birthYear = LocalDate.now().getYear() - age;

        return "10-05-" + birthYear;
    }
}