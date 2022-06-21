package videos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void testUploadOver10Videos() {
        User activeUser = new User("Bob TooActive", LocalDate.of(2021, Month.SEPTEMBER, 12));
        for (int i = 1; i < 11; i++) {
            activeUser.addVideo(new Video("Titanic - scene Nr." + i));
        }
        when(userRepository.findUserWithVideos(2)).thenReturn(activeUser);

        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
                () -> userService.uploadVideo(2, any()));
        assertEquals("Upload is limited to 10 videos. You've already reached the limit!", iae.getMessage());

    }
}