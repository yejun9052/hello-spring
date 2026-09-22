package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import static org.assertj.core.api.Assertions.*;

import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryRepository = new MemoryMemberRepository();

    @BeforeEach
    public void beforeEach() {
        memoryRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryRepository);
    }


    @AfterEach
    public void afterEach() {
        memoryRepository.clearStore();
    }


    @Test
    void 회원가입() {
        //given - 무언가가 주어짐
        Member member = new Member();
        member.setName("hello");

        //when - 이걸 실행 했을때
        Long saveId = memberService.join(member);

        //then - 결과가 ? 이게 나와야함
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}