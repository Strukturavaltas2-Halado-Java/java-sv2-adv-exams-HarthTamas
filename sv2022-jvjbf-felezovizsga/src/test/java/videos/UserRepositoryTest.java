package videos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {


    EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");

    UserRepository repository = new UserRepository(factory);

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
    void testSaveThenFind() {
        repository.saveUser(jane);
        Long janeId = jane.getId();
        User other = repository.findUserWithVideos(janeId);
        assertThat(other.getUserStatus()).isEqualTo(UserStatus.BEGINNER);
        assertThat(other.getName()).isEqualTo("Jane Doe");
    }

    @Test
    void testUpdateUserWithVideo() {
        repository.saveUser(jack);
        Long jackId = jack.getId();
        assertEquals(0, jack.getVideos().size());
        repository.updateUserWithVideo(jackId, titanicVideo);
        User other = repository.findUserWithVideos(jackId);
        assertEquals(1, other.getVideos().size());
    }

    @Test
    void testUpdateUserStatus() {
        repository.saveUser(john);
        Long johnId = john.getId();
        assertThat(john.getUserStatus()).isEqualTo(UserStatus.BEGINNER);

        User otherUser = repository.updateUserStatus(johnId, UserStatus.ADVANCED);
        assertThat(otherUser.getUserStatus()).isEqualTo(UserStatus.ADVANCED);
    }

    @Test
    void testFindUsersWithMoreVideosThan() {

        Video birdVideo = new Video("Funny birds");
        Video dogVide = new Video("Funny dogs");
        Video catVideo = new Video("Funny cats");
        Video basketballVideo= new Video("Air Jordan");
        Video carVideo= new Video("New Lucid Air");

        jane.addVideo(birdVideo);
        jane.addVideo(dogVide);
        jane.addVideo(catVideo);

        john.addVideo(carVideo);
        john.addVideo(basketballVideo);

        jack.addVideo(titanicVideo);
        repository.saveUser(john);
        repository.saveUser(jack);
        repository.saveUser(jane);

        List<User> usersOver1 = repository.findUsersWithMoreVideosThan(1);
        assertThat(usersOver1)
                .hasSize(2)
                .extracting(User::getName)
                .contains("Jane Doe", "John Doe");

        List<User> usersOver2 = repository.findUsersWithMoreVideosThan(2);
        assertThat(usersOver2)
                .hasSize(1)
                .extracting(User::getName)
                .containsOnly("Jane Doe");
    }


}