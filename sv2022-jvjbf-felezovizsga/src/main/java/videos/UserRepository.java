package videos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class UserRepository {

    private EntityManagerFactory factory;

    public UserRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public User saveUser(User user) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    public User findUserWithVideos(long userId) {
        EntityManager em = factory.createEntityManager();
        User user = em.createQuery("select distinct u from User u left join fetch u.videos where u.id  =:userid", User.class)
                .setParameter("userid",userId)
                .getSingleResult();
        em.close();
        return user;
    }

    public void updateUserWithVideo(long userId, Video video) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        User user = em.getReference(User.class, userId);
        user.addVideo(video);
        em.getTransaction().commit();
        em.close();
    }

    public User updateUserStatus(long userId, UserStatus status) {
        EntityManager em = factory.createEntityManager();
        User user = em.getReference(User.class, userId);
        em.getTransaction().begin();
        user.setUserStatus(status);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    public List<User> findUsersWithMoreVideosThan(int amount) {
        EntityManager em = factory.createEntityManager();
        List<User> users = em.createQuery("select u from User u where u.videos.size > :amount", User.class)
                .setParameter("amount", amount)
                .getResultList();
        em.close();
        return users;
    }





}
