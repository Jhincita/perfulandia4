package duoc.perfulandia.security;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHashTest {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final Faker faker = new Faker();

    @Test
    void password_Hashed_Matched() {
        // llama datafaker .internet para simular datos
        String rawPassword = faker.internet().password();
        String hashed = encoder.encode(rawPassword);

        assertNotEquals(rawPassword, hashed, "Error: Contaseña no hasheada.");
        assertTrue(encoder.matches(rawPassword, hashed), "Error: La contraseña hasheada no coincide.");
    }
}
