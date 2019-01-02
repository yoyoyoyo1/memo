package todo.todo.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import todo.todo.dao.sqlProvider.memoSqlProvider;
import todo.todo.entity.memo;

import java.util.List;
import java.util.Map;

@Service
@Mapper
public interface memoDao {
    @SelectProvider(type = memoSqlProvider.class,method = "getMemos")
    List<memo> getMemos(Map<String,Object> query);

    @SelectProvider(type = memoSqlProvider.class,method = "getMemo")
    memo getMemo(Map<String,Object> query);

    @UpdateProvider(type = memoSqlProvider.class,method = "update")
    int update(memo Memo);

    @InsertProvider(type = memoSqlProvider.class,method = "create")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int create(memo Memo);
}
