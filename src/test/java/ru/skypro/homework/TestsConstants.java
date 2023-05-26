package ru.skypro.homework;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entities.Advert;
import ru.skypro.homework.entities.Comment;
import ru.skypro.homework.entities.Image;
import ru.skypro.homework.entities.Users;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ru.skypro.homework.dto.Role.USER;

public class TestsConstants {
    public static final String DATE_STR = "2022-12-30T06:00:00Z";
    public static final OffsetDateTime DATE = OffsetDateTime.parse(DATE_STR);
    public static final Advert ADVERT = new Advert();
    public static final Users AUTHOR = new Users();
    public static final String LAST_NAME = "lastName";
    public static final String FIRST_NAME = "firstName";
    public static final String PHONE = "+71111111222";
    public static final String EMAIL = "user@mail.com";

    public static final String PASSWORD = "password";
    public static final String ENCODED_PASSWORD = "{bcrypt}$2a$10$x.MTPwmQChKlRfOswczsAOrvhsgbsywSHwVQqb.wAqO59F927m/d6";

    public static final String NEW_PASSWORD = "wwww1111";
    public static final String ENCODED_NEW_PASSWORD = "{bcrypt}$2a$10$YOG4AJAnbIz7FPAuuhdauuYv9RIUD.TJpvW2Y79WmThBpCm0tG9i.";

    public static final Role ROLE = USER;

    public static final Advert ADVERT1 = new Advert();
    public static final Advert ADVERT2 = new Advert();
    public static final Comment COMMENT1 = new Comment();
    public static final Comment COMMENT2 = new Comment();
    public static final String TEXT = "text";
    public static final String TEXT1 = "text1";

    public static final String TITLE = "title";
    public static final String TITLE1 = "title1";
    public static final Integer PRICE = Integer.valueOf("100");
    public static final Integer PRICE1 = Integer.valueOf("200");
    public static final String DESCRIPTION = "description";
    public static final String DESCRIPTION1 = "description 2";

    public static final String IMAGE_STR = "ads/image/1";
    public static final String IMAGE_STR1 = "ads/image/2";

    public static final Image IMAGE = new Image();
    public static final List<Advert> LIST_ADS = new ArrayList<>();
    public static final List<Advert> LIST_ADS_NAME = new ArrayList<>();
    public static final List<Comment> LIST_COMMENTS = new ArrayList<>();
    public static final AdsCommentDto ADS_COMMENT_DTO = new AdsCommentDto();
    public static final AdsDto ADS_DTO = new AdsDto();
    public static final Users USER1 = new Users();
    public static final Users USER2 = new Users();
    public static final UserDto USER_DTO1 = new UserDto();
    public static final RegisterReqDto REGISTER_REQ_DTO = new RegisterReqDto();
    public static final List<Users> LIST_USER = new ArrayList<>();
    public static final NewPasswordDto NEW_PASSWORD_DTO = new NewPasswordDto();
    public static final Authentication AUTHENTICATION = new Authentication() {
        @Override
        public String getName() {
            return EMAIL;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public Object getCredentials() {
            return null;
        }

        @Override
        public Object getDetails() {
            return null;
        }

        @Override
        public Object getPrincipal() {
            return null;
        }

        @Override
        public boolean isAuthenticated() {
            return false;
        }

        @Override
        public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

        }

    };
}
