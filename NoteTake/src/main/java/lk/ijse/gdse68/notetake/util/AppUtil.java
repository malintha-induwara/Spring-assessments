package lk.ijse.gdse68.notetake.util;

import java.util.UUID;

public class AppUtil {
    public static String createNoteId() {
        return UUID.randomUUID().toString();
    }
}

