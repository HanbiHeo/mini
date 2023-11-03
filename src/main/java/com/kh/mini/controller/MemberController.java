package com.kh.mini.controller;

import com.kh.mini.dao.MemberDAO;
import com.kh.mini.vo.MemberVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
//restcontroller는 리엑트, 프론트 사이트 렌더링과 통실할 때 쓰는 기능임.
@RestController
@RequestMapping("/users")
public class MemberController {
    //POST 로그인 : 방식이 포스트인 것에 대한 get방식으로 주소 찾기
    @PostMapping("/login")
    //반환타입을 객체로 만들고 불리언타입으로 반환하라는 의미
    public ResponseEntity<Boolean>  memberLogin(@RequestBody Map<String, String> loginData) {
        String id = loginData.get("id");
        String pwd=loginData.get("pwd");
        System.out.println("ID : " + id);
        System.out.println("PWD : " + pwd);
        MemberDAO dao = new MemberDAO();
        boolean rst = dao.loginCheck(id, pwd);
        return new ResponseEntity<>(rst, HttpStatus.OK);
    }



    //GET 방식으로 회원 조회하기
    @GetMapping("/member")
        public ResponseEntity <List<MemberVO>>  memberList(@RequestParam String id){
             System.out.println("받은 ID : " + id);
             MemberDAO dao = new MemberDAO();
             List<MemberVO> list = dao.memberSelect(id);
             return new ResponseEntity<>(list, HttpStatus.OK);
    }
}

