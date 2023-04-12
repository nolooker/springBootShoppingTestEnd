package com.shopping.repository;

import com.shopping.entity.Saram;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SaramRepositoryTest {

    @Autowired
    SaramRepository saramRepository ;

    @Test
    @DisplayName("사람 저장 테스트")
    public void createSaramTest(){
        Saram saram = new Saram();

        saram.setId("lee");
        saram.setName("이순신");
        saram.setAddress("용산구 이태원동");
        saram.setSalary(12345L);

        Saram saveItem = saramRepository.save(saram) ;
        System.out.println(saveItem.toString());

    }
}
