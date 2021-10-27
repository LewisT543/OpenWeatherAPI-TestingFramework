import com.sparta.connection.ConnectionManager;
import com.sparta.util.Util;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("q", "wales");
        //hashMap.put("lon", "-0.118");
        try {
            System.out.println(ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.BOX, hashMap));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }
}
