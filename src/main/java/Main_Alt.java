import com.sparta.connection.ConnectionManager_Alt;
import com.sparta.dto.ResponseDTO;
import com.sparta.injector.Injector_Alt;

// made by natasha

public class Main_Alt {
    public static void main(String[] args) {
        ResponseDTO responseDTO = Injector_Alt.injectResponseDTO(ConnectionManager_Alt.getConnection("q", "London"));
        System.out.println(responseDTO);
    }
}
