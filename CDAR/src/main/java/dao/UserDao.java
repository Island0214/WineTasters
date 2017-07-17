package dao;

import entityPO.UserPO;

/**
 * Created by mac on 2017/7/17.
 */
public interface UserDao {

    /**
     * 注册
     * @param userPO 用户PO
     * @return 是否注册成功，失败表示用户名已存在
     */
    boolean signUp(UserPO userPO);

    /**
     * 登录
     * @param userPO 用户PO
     * @return 是否登录成功
     */
    boolean signIn(UserPO userPO);
}
