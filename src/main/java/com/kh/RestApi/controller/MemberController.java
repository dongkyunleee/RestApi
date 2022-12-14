package com.kh.RestApi.controller;

import com.kh.RestApi.service.MemberService;
import com.kh.RestApi.vo.MemberDTO;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MemberController {
    // Service 로직 연결
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/GetMember/list")
    public ResponseEntity<List<MemberDTO>> memberList() {
        List<MemberDTO> list = memberService.getMemberList();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    // userId로 회원 조회하기
    @GetMapping("/GetMember")
    public ResponseEntity<List<MemberDTO>> memberList(@RequestParam String userId) {
        List<MemberDTO> list = memberService.getMemberList(userId);
        return new ResponseEntity(list, HttpStatus.OK);

    }


    //회원 가입 만들기
    @PostMapping("/RegMember")
    public ResponseEntity<Boolean> registerMember(@RequestBody Map<String, String> regData) {
        String userId = regData.get("user");
        String pwd = regData.get("pwd");
        String name = regData.get("name");
        String mail = regData.get("mail");
        boolean result = memberService.regMember(userId, pwd, name, mail);
        if (result) {
            return new ResponseEntity(true, HttpStatus.OK);
        } else {
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 로그인 체크
    @PostMapping("/Login")
    public ResponseEntity<Boolean> memberLogin(@RequestBody Map<String, String> loginData) {
        String user = loginData.get("user");
        String pwd = loginData.get("pwd");
        boolean result = memberService.loginCheck(user, pwd);
        if (result) {
            return new ResponseEntity(true, HttpStatus.OK);
        } else {
            return new ResponseEntity(false, HttpStatus.OK);
        }
    }
}