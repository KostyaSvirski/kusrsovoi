package org.kursovoi.server.util.json;

import org.junit.jupiter.api.Test;
import org.kursovoi.server.dto.AuthRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResponseSerializerTest {

    @Autowired
    private ResponseSerializer<AuthRequestDto> responseSerializer;

    @Test
    void test() {
        var account = new AuthRequestDto();
        account.setLogin("aaa");
        account.setPassword("bbb");
        var res = responseSerializer.apply(account);
        assertNotNull(responseSerializer.apply(account));
    }

}