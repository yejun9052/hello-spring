package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository MemberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.MemberRepository = memberRepository;
    } // @RequiredArgsConstructor 어노테이션으로 생략 가능(자동생성)


    // 회원가입
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복회원 검증
        MemberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers() {
        return MemberRepository.findAll();
    }

    public Optional<Member> findOne (long memId) {
        return MemberRepository.findById(memId);
    }

    private void validateDuplicateMember(Member member) {
        MemberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
