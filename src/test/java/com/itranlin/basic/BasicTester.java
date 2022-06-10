package com.itranlin.basic;

import com.itranlin.basic.common.util.JacksonUtil;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author itranlin
 * @since 2021/3/21 12:52
 */
public class BasicTester {
    public static void main(String[] args) {
        B b = new B("123");
        B b1 = JacksonUtil.clone(b, new TypeReference<B>() {
        });
        System.out.println(1);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class B {
    private String b;


}
