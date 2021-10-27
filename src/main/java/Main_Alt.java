import com.sparta.old.connection.ConnectionManager;
import com.sparta.old.dto.ResponseDTO;
import com.sparta.old.injector.Injector;

// made by natasha

public class Main_Alt {
    public static void main(String[] args) {
        ResponseDTO responseDTO = Injector.injectResponseDTO(ConnectionManager.getConnection("q", "London"));
        System.out.println(responseDTO);
    }
}
