package com.demowebshop.tests;

import com.github.javafaker.Faker;

import static com.demowebshop.utils.RandomUtils.*;

public class TestData {

    static Faker faker = new Faker();

    public static String firstName = faker.name().firstName();
    public static String newFirstName = faker.name().firstName();
    public static String lastName = faker.name().lastName();
    public static String userEmailUi = getRandomEmailUi();
    public static String userEmailApi = getRandomEmailApi();
    public static String userPassword = getRandomPassword();
    public static String gender = getRandomGender();

    public  static String verificationTokenName = "__RequestVerificationToken";
    public  static String verificationTokenBodyValue = "k7V_vLOxOlQGW4Ltv_WzNm4e"
            + "6TFMaSmMdUXYTg8OUuTfNLoqjVLUm6NpH0DTERdTkt"
            + "_kjU-yXyADeQHhrcgDjvRZaitYAtiJZLHgVGKA74A1";
    public  static String verificationTokenHeaderValue = "_d9Tz4B3vP8uvyARS5wgF7vDgzD"
            + "ohzT5ZxEn4f8KpCzPwUqDwVlAF2rlI60Tr8vbsU"
            + "cfQOibiNMbfbvOQGBVMYrGDpljlE8m9HbfvXYHS501;";
    public static String authCookieName = "NOPCOMMERCE.AUTH";


}
