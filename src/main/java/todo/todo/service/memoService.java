package todo.todo.service;

import todo.todo.entity.memo;

import java.util.List;
import java.util.Map;

public interface memoService {

    List<memo> getMemos(Map<String,Object> query);

    memo getMemo(Map<String,Object> query);
    int update(memo Memo);
    int create(memo Memo);
}
