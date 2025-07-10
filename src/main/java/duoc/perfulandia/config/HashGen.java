package duoc.perfulandia.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashGen {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "totopass4";
        String hashed = encoder.encode(rawPassword);
        System.out.println(hashed);
    }
}
