package org.kursovoi.server.util.rsa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class RSADecipher {

    private final Cipher decryptCipher;

    public String decipher(String encodedMessage) throws IllegalBlockSizeException, BadPaddingException {
        encodedMessage = encodedMessage.replace("\n","");
        byte[] decryptedMessageBytes = decryptCipher.doFinal(Base64.getDecoder()
                .decode(encodedMessage.getBytes(StandardCharsets.UTF_8)));
        return new String(decryptedMessageBytes, StandardCharsets.UTF_8);
    }
}
