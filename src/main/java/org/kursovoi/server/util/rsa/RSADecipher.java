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
        byte[] decryptedMessageBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encodedMessage));
        return new String(decryptedMessageBytes, StandardCharsets.UTF_8);
    }
}
