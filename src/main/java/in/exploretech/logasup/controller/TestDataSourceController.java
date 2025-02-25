package in.exploretech.logasup.controller;

import in.exploretech.logasup.service.TestDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestDataSourceController {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TestDataSourceController(@Qualifier("companyExploreTechJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private TestDataSourceService testDataSourceService;

    @GetMapping("/test")
    public ResponseEntity<?> getProjectDataBaseName(){
        return ResponseEntity.ok(testDataSourceService.getProjectByIdProcedure(10002, jdbcTemplate));
    }



}
