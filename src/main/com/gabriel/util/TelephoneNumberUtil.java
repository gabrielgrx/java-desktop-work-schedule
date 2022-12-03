package main.com.gabriel.util;

public abstract class TelephoneNumberUtil {

    public static String formatTelephone(String textField) {
        try {

            if (textField.length() == 8) {
                return textField.replace(textField,
                        textField.substring(0, 4) + " - " + textField.substring(4, 8));

            } else if (textField.length() == 9) {
                return textField.replace(textField,
                        textField.substring(0, 5) + " - " + textField.substring(5, 9));

            } else if (textField.length() == 10) {
                return textField.replace(textField,
                        "(" + textField.substring(0, 2) + ") " + textField.substring(2, 6) +
                                " - " + textField.substring(6, 10));

            } else if (textField.length() == 11) {
                return textField.replace(textField,
                        "(" + textField.substring(0, 2) + ") " + textField.substring(2, 7) +
                                " - " + textField.substring(7, 11));
            } else {
                return textField;
            }
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }
}


