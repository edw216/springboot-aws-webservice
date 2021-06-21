package com.dongwoo.study.springboot.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name,amount);


        //then
        //JUnit의 assertThat이 아닌 assertj의 assertThat -> CoreMatchers와 달리 추가적으로 라이브러리가 필요없음
        assertThat(dto.getName()).isEqualTo(name);  //assertj라는 테스트 검증 메소드,검증하려는 대상을 인자로 받음
        assertThat(dto.getAmount()).isEqualTo(amount);  //isEqualTo <- assertj의 동등 비교 메소드,인자와 값이 같으면 성공

    }

}