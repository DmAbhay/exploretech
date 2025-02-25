package in.exploretech;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DmBaseServer {


    private String vNull(Object value, String defaultValue) {
        return (value != null) ? String.valueOf(value) : defaultValue;
    }

    public String getSerialNoInteger(String siteCode, String tableName, JdbcTemplate jdbcTemplate) {
        String sql = "EXEC getCode ?, ?, 0";

        try {
            Integer result = jdbcTemplate.queryForObject(sql, new Object[]{tableName, siteCode}, Integer.class);
            return "1" + vNull(siteCode, "0000") + vNull(result, "0000");
        } catch (Exception e) {
            throw new RuntimeException("Error fetching serial number: " + e.getMessage(), e);
        }
    }

    public String getSerialNoBigInt(String siteCode, String tableName, JdbcTemplate jdbcTemplate) {
        String sql = "EXEC getCode ?, ?, 0";

        try {
            Long result = jdbcTemplate.queryForObject(sql, new Object[]{tableName, siteCode}, Long.class);
            return "1" + vNull(siteCode, "0000") + vNull(result, "00000000000000");
        } catch (Exception e) {
            throw new RuntimeException("Error fetching serial number: " + e.getMessage(), e);
        }
    }
}
