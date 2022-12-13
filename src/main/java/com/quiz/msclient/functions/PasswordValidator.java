package com.quiz.msclient.functions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Configuration
@PropertySource("classpath:application.properties")
public class PasswordValidator {
    @Value("${passwordValid}")
    private String PASS_REGEX;
    public boolean isValid(String pass) {
        Pattern pattern = Pattern.compile(PASS_REGEX);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }
}
