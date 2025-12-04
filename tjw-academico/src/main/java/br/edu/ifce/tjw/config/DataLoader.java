package br.edu.ifce.tjw.config;

import br.edu.ifce.tjw.model.Role;
import br.edu.ifce.tjw.model.Usuario;
import br.edu.ifce.tjw.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(UsuarioRepository usuarioRepository,
                                      PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                Usuario admin = new Usuario(
                        "admin",
                        passwordEncoder.encode("123"),
                        Role.ROLE_ADMIN,
                        "Administrador"
                );

                Usuario secretaria = new Usuario(
                        "secretaria",
                        passwordEncoder.encode("123"),
                        Role.ROLE_SECRETARIA,
                        "Secretaria"
                );

                usuarioRepository.save(admin);
                usuarioRepository.save(secretaria);
            }
        };
    }
}
