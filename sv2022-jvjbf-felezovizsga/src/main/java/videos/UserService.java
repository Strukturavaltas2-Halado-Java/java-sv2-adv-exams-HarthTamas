package videos;

public class UserService {


    private static final int LIMIT_OF_VIDEOS_TO_UPLOAD = 10;
    private static final int NUMBER_OF_VIDEOS_FOR_ADVANCED = 4;
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void uploadVideo(long id, Video video) {
        User user = userRepository.findUserWithVideos(id);
        if (user.videos.size() >= LIMIT_OF_VIDEOS_TO_UPLOAD) {
            throw new IllegalArgumentException("Upload is limited to 10 videos. You've already reached the limit!");
        }
        if (user.videos.size() == NUMBER_OF_VIDEOS_FOR_ADVANCED) {
            userRepository.updateUserStatus(id, UserStatus.ADVANCED);
        }
        userRepository.updateUserWithVideo(id,video);
    }


}
