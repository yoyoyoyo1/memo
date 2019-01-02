package todo.todo.service;

import org.springframework.stereotype.Service;
import todo.todo.entity.member;

import java.util.Map;

public interface memberService {
    member getMember(Map<String,Object> query);
    int update(member Member);
    int create(member Member);
}
