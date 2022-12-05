package ru.skypro.homework.mappertest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.AdsMapper;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.UserMapper;
import ru.skypro.homework.entities.Users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.skypro.homework.TestsConstants.*;

@ExtendWith(MockitoExtension.class)
public class UserMapperTest {
    @InjectMocks
    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @BeforeEach
    void setUp() {
        USER1.setId(1L);
        USER1.setFirstName(FIRST_NAME);
        USER1.setLastName(LAST_NAME);
        USER1.setEmail(EMAIL);
        USER1.setPhone(PHONE);
        USER1.setPassword(PASSWORD);
        USER1.setRole(ROLE.name());


        USER_DTO1.setId(2L);
        USER_DTO1.setFirstName(FIRST_NAME);
        USER_DTO1.setLastName(LAST_NAME);
        USER_DTO1.setPhone(PHONE);
        USER_DTO1.setEmail(EMAIL);
    }

    @Test
    void userToUserDtoIsWork() {
        UserDto userDto = mapper.toUserDTO(USER1);
        assertEquals(1, userDto.getId());
        assertEquals(FIRST_NAME, userDto.getFirstName());
        assertEquals(LAST_NAME, userDto.getLastName());
        assertEquals(PHONE, userDto.getPhone());
        assertEquals(EMAIL, userDto.getEmail());
    }

    @Test
    void userDtoToUserIsWork() {
        Users user = mapper.userDtoFromUsers(USER_DTO1);

        assertEquals(2L, user.getId());
        assertEquals(FIRST_NAME, user.getFirstName());
        assertEquals(LAST_NAME, user.getLastName());
        assertEquals(PHONE, user.getPhone());
        assertEquals(EMAIL, user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getRole());
    }
}
