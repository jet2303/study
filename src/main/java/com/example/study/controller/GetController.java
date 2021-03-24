package com.example.study.controller;

//controller : 주소들의 묶음
//controller 패키지는 com.example.study 하위에 만들어져야함.
import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")     //localhost:8080/api    매칭됨.
public class GetController {
    @RequestMapping(method = RequestMethod.GET, path =  "/getMethod")       //localhost:8080/api/getMethod
    public String getRequest(){
        return  "Hi getMethod"; //검증 방법 : junit, 웹브라우저, restclient tool 이용
    }

    //getMapping은 requestMapping과는 다르게 resource를 지정하지 않아도됨. 주소만 설정하면됨
    @GetMapping("/getParameter")    //주소 설정.    localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name= "password") String pwd){
        // requestparameter로 들어올수 있는값들에 대해선 로컬변수를 사용하지 않는다. 어쩔수 없는 경우 말고는
        //@RequestParam(name = "password") == pwd는 password라는 이름으로 들어올것이고, pwd가 받아줌.
        String password = "bbbb";
        System.out.println("id: " + id );
        System.out.println("pwd: " + pwd);
        return id+pwd;
    }

    //localhost:8080/api//multiParameter?account=abcd$email=study@gmail.com&page=10
    //변수로 검색하는게 늘어나는 상황

    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParamter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        //객체 리턴시 기본적으로 Json 변환되어 리턴됨.(따로 설정 하지 않는 이상)
        //{"account" : "", "email" : "" , "page" : 0}
        return searchParam;
    }

}
