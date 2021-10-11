package com.springapp.mvc.global.common;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * Created by Dechen Wangdi on 1/22/2020.
 */
public class EncryptedDataSource extends DriverManagerDataSource {
    @Override
    public String getPassword() {
        String password = super.getPassword();
        return decode(password);
    }

    /**
     * Decode Password
     */
    private String decode(String decode) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            decode = new String(decoder.decodeBuffer(decode));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return decode;
    }
}
