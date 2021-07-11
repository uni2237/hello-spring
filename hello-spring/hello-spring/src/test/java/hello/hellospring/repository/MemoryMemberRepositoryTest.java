package hello.hellospring.repository;

import hello.hellospring.domain.Member;

// import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest { //public 으로 안해도 됨
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() { // callback 메소드임. 각 테스트함수들 끝날 때마다 이거 실행되서 데이터 clear해줌
        repository.clearStore();
    }

    @Test
    public void save(){
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        repository.save(member);

        // then
        Member result = repository.findById(member.getId()).get(); // Optional이라 .get()까지 붙여줘야함
        // Assertions.assertEquals(member,result); //member랑 result랑 같은지 확인 ->jupiter.api.Assertions

        //Assertions.assertThat(member).isEqualTo(result); -> Assertions 위에서 alt+enter 치고 add ~~~ 누르면 아래처럼 됨. import static org.assertj.core.api.Assertions.*; 이거 추가 됨.
        assertThat(member).isEqualTo(result); //isEqualTo(null); 이면 test failed 됨.
    }

    @Test
    public void findByName(){
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        Member result = repository.findByName("spring1").get();

        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
    }
}
