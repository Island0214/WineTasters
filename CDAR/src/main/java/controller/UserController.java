package controller;

import entityVO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by island on 2017/7/17.
 */
@Controller
@RequestMapping("/userAction")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "userLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();

        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String loginInfo = "";
        String result = "";
        System.out.println(username);
        System.out.println(password);
        UserVO userVO = new UserVO();
        userVO.setAccount(username);
        userVO.setPassword(password);

        boolean log = userService.signIn(userVO);
        if(log){
            result = "success";
        }
        else{
            result = "fail";
            loginInfo = "用户名不存在或密码错误";
            map.put("loginInfo", loginInfo);
        }
        map.put("result", result);

        return map;
    }

    @RequestMapping(value = "userSignUp", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> signUp(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        String result = "";
        String signinInfo = "";

        String password = request.getParameter("password");
        String username = request.getParameter("username");
        System.out.println(username);
        System.out.println(password);

        UserVO userVO = new UserVO();
        userVO.setAccount(username);
        userVO.setPassword(password);

        boolean log = userService.signUp(userVO);
        if(log){
            result = "success";
        }
        else{
            result = "fail";
            signinInfo = "用户名已存在";
            map.put("signinInfo", signinInfo);
        }

        map.put("result", result);

        return map;
    }

}
