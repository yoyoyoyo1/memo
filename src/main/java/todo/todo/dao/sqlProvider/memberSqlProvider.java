package todo.todo.dao.sqlProvider;

import org.apache.ibatis.jdbc.SQL;
import todo.todo.entity.member;

import java.util.Map;

public class memberSqlProvider {
    public String getMember(Map<String,Object> query){
        return new SQL(){{
            SELECT("*");
            FROM("member");
            if(query.get("name") != null){
                WHERE("name=#{name}");
            }
            if(query.get("id") != null){
                WHERE("id=#{id}");
            }
        }}.toString();
    }

    public String update(member Member) {
        return new SQL(){{
            UPDATE("member");
            if(Member.getName() != null){
                SET("name=#{name}");
            }
            if(Member.getPassword() != null){
                SET("password=#{password}");
            }
            if(Member.getMail() != null){
                SET("mail=#{mail}");
            }
            if(Member.getActivate() != null){
                SET("activate=#{activate}");
            }
            WHERE("id=#{id}");
        }}.toString();

    }
    public String create(member Member){
       return new SQL(){{
           INSERT_INTO("member");
           INTO_COLUMNS("name","mail","password","activate");
           INTO_VALUES("#{name}","#{mail}","#{password}","#{activate}");
       }}.toString();
    }
}
