package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository  implements MemberRepository{

    private final EntityManager em; // jpa는 EntitiyManager라는 것으로 모든걸 동작한다

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member; // jpa가 insert쿼리 만들어서 db에 집어넣음, id도 만들어줌
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member= em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name=:name",Member.class)
                .setParameter("name",name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        // jpql 쿼리: sql은 테이블 대상으로 쿼리를 날리지만, 이건 객체(Member Entity)를 대상으로 날림
    }
}
