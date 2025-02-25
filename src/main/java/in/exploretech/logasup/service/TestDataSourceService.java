package in.exploretech.logasup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestDataSourceService {



//    public Map<String, Object> getProjectByIdQuery(int projectId) {
//        String sql = "SELECT * FROM projectMaster WHERE projectId = ?";
//        return jdbcTemplate.queryForList(sql, projectId).get(0);
//    }

    public Map<String, Object> getProjectByIdProcedure(int projectId, JdbcTemplate jdbcTemplate) {
        String sql = "EXEC GetProjectById ?";
        return jdbcTemplate.queryForList(sql, projectId).stream().findFirst().orElse(null);
    }


}
