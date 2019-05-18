package com.erp.bean.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: yyc
 * @Date: 2019/5/18 12:10
 */
public class MyDateConverter implements Converter<String, Date> {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public Date convert(String s) {
        try {
            if (s.length()>10) {
                Date parse = simpleDateFormat.parse(s);
                return parse;
            }else{
                Date parse = simpleDateFormat2.parse(s);
                return parse;
            }
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return null;
    }
}
