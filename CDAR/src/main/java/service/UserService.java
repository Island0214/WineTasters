package service;

import entityPO.UserPO;
import entityVO.UserVO;

/**
 * Created by mac on 2017/7/17.
 */
public interface UserService {

    /**
     * 注册
     * @param userVO 用户VO
     * @return 是否注册成功，失败表示用户名已存在
     */
    boolean signUp(UserVO userVO);

    /**
     * 登录
     * @param userVO 用户VO
     * @return 是否登录成功
     */
    boolean signIn(UserVO userVO);
}
