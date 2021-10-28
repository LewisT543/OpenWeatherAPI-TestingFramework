import com.sparta.connection.ConnectionManager;
import com.sparta.util.Util;

import java.util.HashMap;

// made by ed

public class Main {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("lat", "51.51");
        hashMap.put("lon", "-0.118");
        try {
            System.out.println(ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND, hashMap).get("url"));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
