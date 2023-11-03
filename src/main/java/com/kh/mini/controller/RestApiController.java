package com.kh.mini.controller;
import com.kh.mini.dao.MemberDAO;
import com.kh.mini.vo.MemberVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//컨트롤러에서는 요청과 응답만 처리해야한다. 데이터 가공 안함.(컨트롤러에서는 데이터 가공 자제해야함. 수정시 복잡.)
@RestController
//공통된 경로 설정
@RequestMapping("/test")
public class RestApiController {
    //GET 방식의 요청
    @GetMapping("/name")
//    http 리퀘스트에 대한 부분을 서블릿이 받아줌. 내가 직접 호출하는게 아니기 때문에 public String getHello() 활성화 안됨
    public String getHello() {
        return "GET 방식에 대해 응답";
    }
    @GetMapping("/board/{pageNo}/{commNo}")
    public  String getVariable(@PathVariable String pageNo, @PathVariable String commNo){
        return  "GET 방식 : " + pageNo + " / " + commNo;
    }
    @GetMapping("/search")
    public  String getRequestParam(
            @RequestParam String model, @RequestParam String price, @RequestParam String company
    ) {
    return " 모델 : " + model + " 가격 : " + price + " 제조사 : " + company;
    }
    @GetMapping("/member")
    public ResponseEntity<List<MemberVO>> getMemberList() {
        List<MemberVO> list = new ArrayList<>();
        MemberDAO dao = new MemberDAO();
        list = dao.memberSelect("ALL");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
//    회원가입의 경우에는 get 방식이 아닌 post 방식을 쓴다. get(출력), post(등록), put(수정), delete(삭제)

    @PostMapping("/setMember")
//    <Boolean> 참조타입이라 첫글자 대문자
    public ResponseEntity<Boolean> memberRegister(@RequestBody Map<String, String> regData) {
        String getId = regData.get("id");
        String getPwd = regData.get("pwd");
        String getName = regData.get("name");
        String getEmail = regData.get("email");
        MemberDAO dao = new MemberDAO();
        boolean isReg = dao.memberRegister(getId, getPwd, getName, getEmail);
        return new ResponseEntity<>(isReg, HttpStatus.OK);
    }


}
