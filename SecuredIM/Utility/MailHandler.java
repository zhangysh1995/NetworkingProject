package Utility;

/**
 * this module handles mail services
 */

public class MailHandler {
    private static String fromMail;

    public static void setMail(String mail) {
        fromMail = mail;
    }

    public static String getMail() {
        try {
            if (fromMail != null)
                return fromMail;
//            else throw  NullPointerException;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    public Boolean sendRequestMail(String email) {
        return true;
    }

    public Boolean sendMessage() {
        return true;
    }

    public Boolean receiveRequestMail() {
        return true;
    }

    public Boolean receiveMessage() {
        return true;
    }
}
