package ticket.booking.util;

import ticket.booking.entities.User;

public class UserServiceUtil
{
    public static boolean checkPassword(String password, User user)
    {
        return password.equals(user.getPassword());
    }
}
