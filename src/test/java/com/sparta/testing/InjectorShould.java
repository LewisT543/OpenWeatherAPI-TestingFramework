package com.sparta.testing;

import com.sparta.connection.ConnectionManager;
import com.sparta.dto.CloudsDTO;
import com.sparta.injector.Injector;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InjectorShould {
    private static CloudsDTO cloudsDTO;
    private static HashMap<String, String> params;

    @BeforeAll
    static void init() {
        params.put("q","London");
        cloudsDTO = Injector.injectCloudsDTO(
                (String) ConnectionManager.getConnection(ConnectionManager.ENDPOINTS.FIND, params).get("url"));
    }


    @Test
    void GivenCloudDTOInjection_ShouldCreateInstanceofCloudDTO() {
        assertTrue(cloudsDTO instanceof CloudsDTO);
    }
}
