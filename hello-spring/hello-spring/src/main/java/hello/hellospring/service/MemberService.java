package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service // 스프링이 올라올때 MemberService를 Service네? 하고 스프링이 스프링컨테이너에 memberService를 등록해준다.
public class MemberService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    //@Autowired //memberService를 스프링이 생성시, memberRepository가 필요하구나? 하고 스프링컨테이너에 있는 MemoryMemberRepository(memberRepository 구현체)를 연결해줌.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){

        /*
        // memberRepository.findByName(member.getName()); 에서 ctrl+alt+v 누르면 자동으로 아래처럼 Optional 붙음
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m ->{
        throw new IllegalStateException("이미 존재하는 회원입니다");
        });
        */


        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { // 메소드 추출 단축키 : ctrl+alt+m
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
