package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    //ID 찾기
    Optional<Member> findById(Long id);
    //이름 찾기
    Optional<Member> findByName(String name);
    // 회원 전체 조회
    List<Member> findAll();
}
