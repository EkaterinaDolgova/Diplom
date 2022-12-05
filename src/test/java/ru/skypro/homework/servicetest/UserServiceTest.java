package ru.skypro.homework.servicetest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static ru.skypro.homework.TestsConstants.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    UserMapper mapper;
    @Mock
    PasswordEncoder encoder;
    @InjectMocks
    UserService userService;
    @BeforeEach
    void setUp() {
        USER1.setId(1L);
        USER1.setFirstName(FIRST_NAME);
        USER1.setLastName(LAST_NAME);
        USER1.setEmail(EMAIL);
        USER1.setPhone(PHONE);

        USER_DTO1.setId(1L);
        USER_DTO1.setFirstName(FIRST_NAME);
        USER_DTO1.setLastName(LAST_NAME);
        USER_DTO1.setEmail(EMAIL);
        USER_DTO1.setPhone(PHONE);

    }

    @Test
    void getUsersMe_whenSuccessful() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(USER1));
        when(mapper.toUserDTO(USER1)).thenReturn(USER_DTO1);

        ResponseEntity<UserDto> response = userService.getMe(AUTHENTICATION);
        assertEquals(ResponseEntity.ok(USER_DTO1), response);
    }
}
