package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();

    //키값 지정
    private static long sequence = 0L;


    @Override
    public Member save(Member member) {
        //Id 값 세팅
        member.setId(++sequence);

        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //결과가 null이 있을 수도 있기 때문에 Optional.ofNullable 로 감싸 줌
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //member에서 getName의 값이 parameter로 받아온 name과 동일한가?
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();

    }

    @Override
    public List<Member> findAll() {
        //리스트 값 가져오기
        return new ArrayList<>(store.values());
    }


    public void clearStore(){
        store.clear();
    }
}
