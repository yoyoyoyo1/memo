package todo.todo.dao.sqlProvider;

import org.apache.ibatis.jdbc.SQL;
import todo.todo.entity.memo;

import java.util.Map;

public class memoSqlProvider {
    public String getMemo(Map<String,Object> query){
        return new SQL(){{
            SELECT("*");
            FROM("memo");
            WHERE("id=#{id}");
        }}.toString();
    }
    public String getMemos(Map<String,Object> query){
        return new SQL(){{
            SELECT("*");
            FROM("memo");
            WHERE("member_id=#{member_id}");
        }}.toString();
    }
    public String create(memo Memo){
        return new SQL(){{
            INSERT_INTO("memo");
            INTO_COLUMNS("content","hour","day","member_id","mail_warn","time_stamp","deadline","expired");
            INTO_VALUES("#{content}","#{hour}","#{day}","#{member_id}","#{mail_warn}","#{time_stamp}","#{deadline}","#{expired}");
        }}.toString();
    }
    public String update(memo Memo){
        return new SQL(){{
            UPDATE("memo");
            SET("expired=#{expired}");
            WHERE("id=#{id}");
        }}.toString();
    }
}
