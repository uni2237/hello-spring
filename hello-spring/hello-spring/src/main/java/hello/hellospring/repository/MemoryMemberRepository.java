package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

// MemberRepositrory의 구현체임
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // store에 넣기 전에 id 값 세팅, name은 이미 넘어와있음.(사용자가 입력)
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //id가 null인 경우도 감쌀 수 있음,
        //return store.get(id); id 가 null인 경우 에러
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 루프를 돌다가 하나를 찾음, 못찾아도 Optional이므로 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store.values() : Members들
    }

    public void clearStore() { // 중요! 테스트 함수들이 순서와 상관없이 실행될 수 있도록 repository clear해줌
        store.clear();
    }
}
