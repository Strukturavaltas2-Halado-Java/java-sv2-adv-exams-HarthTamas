package videos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceIT {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");

    UserRepository repository = new UserRepository(factory);

    UserService userService = new UserService(repository);

    User john;

    User jack;

    User jane;

    Video titanicVideo;

    @BeforeEach
    void init() {
        john = new User("John Doe", LocalDate.of(2022, Month.APRIL, 10));
        jane = new User("Jane Doe", LocalDate.of(2022, Month.MARCH, 10));
        jack = new User("Jack Doe", LocalDate.of(2022, Month.FEBRUARY, 10));
        titanicVideo = new Video("Titanic - a short scene");
    }

    @Test
    void testStatusBeginnerAfterSave() {
        User other = repository.saveUser(john);
        assertThat(other.getUserStatus()).isEqualTo(UserStatus.BEGINNER);
        userService.uploadVideo(other.getId(), titanicVideo);

        other = repository.findUserWithVideos(john.getId());
        assertThat(other.getUserStatus()).isEqualTo(UserStatus.BEGINNER);
    }

    @Test
    void testVideoAddedToList() {
        User other = repository.saveUser(john);
        assertThat(other.getVideos().size()).isEqualTo(0);
        userService.uploadVideo(other.getId(), titanicVideo);

        other = repository.findUserWithVideos(john.getId());
        assertThat(other.getVideos().get(0).getTitle()).isEqualTo("Titanic - a short scene");
    }

    @Test
    void testStatusChangeAfter5Video() {
        User other = repository.saveUser(john);
        assertThat(other.getVideos().size()).isEqualTo(0);

        for (int i = 1; i < 5; i++) {
            userService.uploadVideo(john.getId(), new Video("Titanic - scene Nr."+i));
        }
        userService.uploadVideo(john.getId(), new Video("Titanic - final scene"));
        other = repository.findUserWithVideos(john.getId());
        assertThat(other.getUserStatus()).isEqualTo(UserStatus.ADVANCED);
    }

}