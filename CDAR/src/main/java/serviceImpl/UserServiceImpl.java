package serviceImpl;

import dao.UserDao;
import entityPO.UserPO;
import entityVO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

/**
 * Created by mac on 2017/7/17.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean signUp(UserVO userVO) {
        return userDao.signUp(new UserPO(userVO));
    }

    @Override
    public boolean signIn(UserVO userVO) {
        return userDao.signIn(new UserPO(userVO));
    }
}
