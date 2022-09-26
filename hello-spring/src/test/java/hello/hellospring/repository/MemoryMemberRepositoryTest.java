package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        //순서에 상관없이 실행 후 clear
        memberRepository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        memberRepository.save(member);

        //optional에서 값 빼낼때 -> Get으로 빼내기
        Member result = memberRepository.findById(member.getId()).get();

//        System.out.println("result = " + result.getId());
//        System.out.println("result = " + (result == member)); //true

        Assertions.assertEquals(result, member); //다르다면 AssertionFailError 오류
        
        //주피터 Assertions와 같지만 import 다름
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        //org.opentest4j.AssertionFailedError
        //Member result = repository.findByName("spring2").get();

        Member result = memberRepository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }


    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member1.setName("spring2");
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();

        assertThat(result.size()).isEqualTo(2);


    }


}
