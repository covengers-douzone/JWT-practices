package com.coven.jwt.controller;


import com.coven.jwt.model.User;
import com.coven.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class RestApiController {
//    private static String CODENUMBER = "-9999";

    @Autowired
    private UserRepository userRepository;

    @Qualifier("encodedPwd")
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public  String index() {
        return "main/index";
    }

    @CrossOrigin
    @PostMapping("/login")
    public String login(){ return "main/success"; }

    @GetMapping("/join")
    public String join(){ return "user/join"; }


    @PostMapping("/join")
    public String join(User user){

         /* 문자인증 기능 잠시 정지.
        if(!(CODENUMBER.equals((String) request.getParameter("text")))){
            System.out.println(CODENUMBER);
            System.out.println(request.getParameter("text"));
            System.out.println("실패");
            return "user/join";
        }
        */
        user.setRoles("ROLE_USER");

        // Security 암호화 (비밀번호)
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);

        // 회원가입은 잘되지만, 시큐리티 로그인이 안된다. -> 비밀번호가 암호화가 안되어있기 때문에. 위의 과정 필요
        userRepository.save(user);
        return "redirect:/";
    }

    @CrossOrigin
    @GetMapping("/api/vi/user")
    public @ResponseBody String user(){
        return "<h1>user</h1>";
    }

    @GetMapping("/api/vi/manager")
    public @ResponseBody String manager(){
        return "manager";
    }

    @GetMapping("/api/vi/admin")
    public @ResponseBody String admin(){
        return "admin";
    }
}

//    @ResponseBody
//    @PostMapping(value = "/sendSms")
//    public String sendSms(HttpServletRequest request) throws Exception {
//        String api_key = "NCS24FKUXYQBKKP5";
//        String api_secret = "OPTNWRSSEW1D8ANZMB2DN6QICTIF9BMG";
//
//        Message coolsms = new Message(api_key, api_secret);
//
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("to", (String)request.getParameter("to"));
//        params.put("from", "01025271985");
//        params.put("type", "SMS");
//        params.put("text", (String)request.getParameter("text"));
//        params.put("app_version", "test app 1.2"); // application name and version
//
//        System.out.println(params);
//        try {
//            CODENUMBER = (String)request.getParameter("text");
//            System.out.println("인증번호는 " + CODENUMBER +" 입니다.");
//            JSONObject obj = (JSONObject) coolsms.send(params);
//            System.out.println(obj.toString());
//
//        } catch (CoolsmsException e) {
//            System.out.println(e.getMessage());
//            System.out.println(e.getCode());
//        }
//        return "suc";
//    }
//
