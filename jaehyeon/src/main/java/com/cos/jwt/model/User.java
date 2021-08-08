package com.cos.jwt.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Entity // DB생성
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //mysql 사용시 autoincrement가 됨.
    private long id;
    private String username;
    private String password;
    private String roles;

    //role이 하나의 user당 2개이상이 있을 경우 만들기 위하여 작성한것.
    public List<String> getRoleList(){
        if(this.roles.length() > 0 ){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

}
