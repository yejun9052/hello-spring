package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository repository = new MemoryMemberRepository();


    // 회원가입
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복회원 검증
        repository.save(member);
        return member.getId();
    }

    public List<Member> findMembers() {
        return repository.findAll();
    }

    public Optional<Member> findOne (long memId) {
        return repository.findById(memId);
    }

    private void validateDuplicateMember(Member member) {
        repository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
