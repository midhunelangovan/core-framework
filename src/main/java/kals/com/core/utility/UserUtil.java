package kals.com.core.utility;

import org.springframework.stereotype.Component;

@Component
public class UserUtil {

    public static String getUserName() {
        return "systemuser@kal.io";
    }

    public static Long getUserId() {
        return 1L;
    }

    public static String getUserEmail() {
        return "systemuser@kal.io";
    }

}
