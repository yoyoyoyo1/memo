package todo.todo.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todo.todo.dao.memoDao;
import todo.todo.entity.member;
import todo.todo.entity.memo;
import todo.todo.service.memoService;

import java.util.List;
import java.util.Map;
@Service
public class memoServiceImp implements memoService {
    @Autowired
    memoDao MDao;
    @Override
    public List<memo> getMemos(Map<String, Object> query) {
        return MDao.getMemos(query);
    }

    @Override
    public memo getMemo(Map<String, Object> query) {
        return MDao.getMemo(query);
    }

    @Override
    public int create(memo Memo) {
        return MDao.create(Memo);
    }

    @Override
    public int update(memo Memo) {
        return MDao.update(Memo);
    }
}
