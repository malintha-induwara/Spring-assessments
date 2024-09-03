package lk.ijse.gdse68.notetake.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {

    public enum ResponseCode{
        SUCCESS,FAILED
    }


    public static String createNoteId() {
        return "NOTE-"+UUID.randomUUID();
    }

    public static String createUserId(){
        return "USER-"+UUID.randomUUID();
    }


    public static String toBase64ProfilePic(String profilePic){
        return Base64.getEncoder().encodeToString(profilePic.getBytes());
    }
}

