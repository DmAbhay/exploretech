package in.exploretech.collection.controller;

import in.exploretech.collection.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping("/test-external-config")
    public String testExternalConfig(){
        return participantService.testExternalConfig();
    }
}
