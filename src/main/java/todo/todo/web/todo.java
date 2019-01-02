package todo.todo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todo.todo.entity.memo;
import todo.todo.service.memoService;
import todo.todo.util.jobList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.*;

@RestController
@RequestMapping("/todo")
public class todo {
    @Autowired
    jobList jobs;
    @Autowired
    memoService MemoService;
    @GetMapping("")
    public List<memo> info(HttpServletRequest request){
        Map<String,Object> query = new HashMap<>();
        HttpSession session=request.getSession();
        Integer memberId = (Integer) session.getAttribute("id");
        query.put("member_id",memberId);
        List<memo> Memos = MemoService.getMemos(query);
        Iterator<memo> iter = Memos.iterator();
        long new_time = System.currentTimeMillis();
        while(iter.hasNext()){
            memo Memo = iter.next();
            if(!Memo.isExpired() && new_time >= Memo.getDeadline().getTime()){
                Memo.setExpired(true);
                MemoService.update(Memo);
            }
        }
        return Memos;
    }
    @PostMapping("")
    public boolean save(HttpServletRequest request,@RequestBody Map<String,Object> body){
        Map<String,Object> query = new HashMap<>();
        HttpSession session=request.getSession();
        Integer memberId =  (Integer) session.getAttribute("id") ;
        Integer hour = Integer.valueOf((String) body.get("hour"));
        Integer day = Integer.valueOf((String) body.get("day"));
        String content = (String) body.get("content");
        boolean mailWarn = (boolean) body.get("mailWarn");
        //获取0点毫秒数
        long zero = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        Date todo =  new Date(zero + 60L * 1000 * 60 * 24 * day + 60L * 1000 * 60*hour);
//        Date todo = new Date(System.currentTimeMillis());
        try{

            memo Memo = new memo();
            Memo.setContent(content);
            Memo.setDay(day);
            Memo.setHour(hour);
            Memo.setMember_id(memberId);
            Memo.setMail_warn(mailWarn);
            Memo.setTime_stamp(new Date());
            System.out.println(Memo.getTime_stamp().toString());
            Memo.setDeadline(todo);
            Memo.setExpired(false);
            MemoService.create(Memo);
            System.out.println(Memo.getTime_stamp().toString());
            if(mailWarn){
                Map<String,Long> job = new HashMap<>();
                job.put("time",todo.getTime());
                job.put("memo_id",Memo.getId().longValue());
                jobs.jobs.add(job);
            }
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
