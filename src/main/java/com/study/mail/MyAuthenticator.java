package com.study.mail;


import javax.mail.*;

/**
 * @ClassName MyAuthenticator
 * @Description
 * @Author
 * @Date 2021/12/30 17:12
 * @Version 1.0
 **/
public class MyAuthenticator extends Authenticator {

    String userName=null;
    String password=null;

    public MyAuthenticator( ) {

    }
    public MyAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(userName, password);

    }
}
