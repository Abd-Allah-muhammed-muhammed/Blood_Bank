package com.abdo.hp.bank.helper;

public class Validation {

    public static boolean validMail(String mail) {
        final String PATERN_MAIL = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
        return mail.matches(PATERN_MAIL);
    }

    public static boolean validName(String Name) {
        final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
        return Name.matches(USERNAME_PATTERN);
    }

    public static boolean validphone(String phone) {
        if (phone.length() == 11) {
            return true;
        }

        return false;
    }

    public static boolean validPass(String Pass) {

        // The password's first character must be a letter, it must contain at least 4 characters and no more than 15 characters and no characters other than letters, numbers and the underscore may be used
        String PATERN_MAIL = "^[a-zA-Z]\\w{3,14}$";

            return Pass.matches(PATERN_MAIL);

    }
}