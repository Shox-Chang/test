package spring01;

import com.czh.spring01.Dao.UserDaoImpl;
import com.czh.spring01.service.UserServiceImpl;

public class SpringTest {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao(new UserDaoImpl());
        userService.queryUser();
    }
}
