package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "userLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();

        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String loginInfo = "";
        String result = "";

        map.put("result", result);

        map.put("loginInfo", loginInfo);

//        System.out.println(username);
//        System.out.println(password);
//        System.out.println(loginInfo);
//
        return map;
    }

    @RequestMapping(value = "userSignUp", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> signUp(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        String result = "";
        String errorType = "";


        String password = request.getParameter("password");
        String username = request.getParameter("username");


//        System.out.println(username);
//        System.out.println(password);


        map.put("result", result);

        return map;
    }

}
