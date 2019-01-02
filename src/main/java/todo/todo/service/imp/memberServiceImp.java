package todo.todo.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todo.todo.dao.memberDao;
import todo.todo.entity.member;
import todo.todo.service.memberService;

import java.util.Map;

@Service
public class memberServiceImp implements memberService {
    @Autowired
    private memberDao MDao;

    @Override
    public member getMember(Map<String, Object> query) {
        return MDao.getMember(query);
    }

    @Override
    public int update(member Member) {
        return MDao.update(Member);
    }

    @Override
    public int create(member Member) {
        return MDao.create(Member);
    }
}
