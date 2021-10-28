import com.sparta.dto.ResponseDTO;

// made by natasha

public class Main_Alt {
    public static void main(String[] args) {
        ResponseDTO responseDTO = Injector_Alt.injectResponseDTO(ConnectionManager_Alt.getConnection("q", "London"));
        System.out.println(responseDTO);
    }
}
