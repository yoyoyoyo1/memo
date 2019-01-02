package todo.todo.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import todo.todo.entity.member;
import todo.todo.entity.memo;
import todo.todo.service.memberService;
import todo.todo.service.memoService;
import todo.todo.util.jobList;
import todo.todo.util.sendMail;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class UploadTask extends QuartzJobBean {
    @Autowired
    jobList Jobs;
    @Autowired
    memoService MemoService;
    @Autowired
    memberService MemberService;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("任务开始");
        Map<String,Object> query = new HashMap<>();
        try {
            long new_time = System.currentTimeMillis();
            Iterator<Map<String,Long>> iter = Jobs.jobs.iterator();
            while(iter.hasNext()){
                Map<String,Long> s = iter.next();
                if(s.get("time").longValue()<=new_time){
                    query.put("id",s.get("memo_id").intValue());
                    memo Memo = MemoService.getMemo(query);
                    query.put("id",Memo.getMember_id());
                    member Member = MemberService.getMember(query);
                    sendMail sm= new sendMail();
                    sm.send(Memo.getContent(),Member.getMail());
                    Memo.setExpired(true);
                    MemoService.update(Memo);
                    iter.remove();
                    System.out.println("邮件发送成功"+Memo.getMember_id()+Memo.getId());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("任务结束");
    }
}