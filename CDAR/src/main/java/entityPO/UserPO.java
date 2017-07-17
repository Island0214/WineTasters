package entityPO;

import entityVO.UserVO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by qianzhihao on 2017/7/17.
 */
@Entity
public class UserPO {
    private Integer id;
    private String account;
    private String password;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length = 16)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Column(length = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserPO() {
    }

    public UserPO(UserVO userVO) {
        this.account = userVO.getAccount();
        this.password = userVO.getPassword();
    }
}
