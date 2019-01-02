package todo.todo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todo.todo.entity.member;
import todo.todo.service.memberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class user {

    @Autowired
    memberService MemberService;
    @GetMapping("/info")
    public member info(HttpServletRequest request) throws Exception {
        Map<String,Object> query = new HashMap<>();
        HttpSession session=request.getSession();//这就是session的创建
        Integer id = (Integer) session.getAttribute("id");
        query.put("id",id);
        member Member = MemberService.getMember(query);
        return Member;
    }
    @PostMapping("/login")
    public boolean login(HttpServletRequest request, @RequestBody Map<String,Object> body){
        Map<String,Object> query = new HashMap<>();
        try{

            String name = (String) body.get("name");
            String password = (String) body.get("password");
            query.put("name",name);
            member Member = MemberService.getMember(query);
            if(!Member.getPassword().equals(password)){
                return false;
            }else {
                HttpSession session=request.getSession();//session的创建
                session.setAttribute("id",Member.getId());
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}