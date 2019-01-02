package todo.todo.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;
import todo.todo.dao.sqlProvider.memberSqlProvider;
import todo.todo.entity.member;

import java.util.List;
import java.util.Map;

@Service
@Mapper
public interface memberDao  {
    @SelectProvider(type = memberSqlProvider.class,method = "getMember")
    member getMember(Map<String,Object> query);

    @UpdateProvider(type = memberSqlProvider.class,method = "update")
    int update(member Member);

    @InsertProvider(type = memberSqlProvider.class,method = "create")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int create(member Member);
}
