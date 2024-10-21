package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.util.UserServiceUtil;
import ticket.booking.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBooking {
    final private User user;

    private List<User> usersList;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String USER_DATA = "app/src/main/java/ticket/booking/localDB/users.json";

    public UserBooking(User user1) throws IOException {
        this.user = user1;
        File users = new File(USER_DATA);
        usersList = objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }

    public Boolean loginUser() {
        Optional<User> foundUser = usersList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public boolean signUp() {
        try {
            usersList.add(user);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException e) {
            return Boolean.FALSE;
        }
    }
}
