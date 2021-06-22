package com.dongwoo.study.springboot.web;

import com.dongwoo.study.springboot.config.auth.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)  //스프링 부트 테스트와 JUnit사이에 연결자 역할을 한다.
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                            classes = SecurityConfig.class)
        })    //Web(Spring MVC)에 집중할 수 있는 어노테이션이다.
class HelloControllerTest {

    @Autowired  //스프링이 관리하는 빈(Bean)을 주입받는다.
    private MockMvc mvc;    //Web API를 테스트 할때 사용한다.(ex)HTTP GET,POST

    @Test
    @WithMockUser("USER")
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))  //MockMvc를 통해 HTTP GET 요청
                .andExpect(status().isOk()) //결과를 검증함, HTTP Header의 status를 검증함
                .andExpect(content().string(hello));    //Controller의 리턴값을 검증함
    }

    @Test
    @WithMockUser("USER")
    public void helloDto가_리턴된다() throws Exception{
        String name = "test";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name",name)
                .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }

}