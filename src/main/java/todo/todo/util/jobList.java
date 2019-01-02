package todo.todo.util;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class jobList {
    public List<Map<String,Long>> jobs = new ArrayList<>();
}