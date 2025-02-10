package in.exploretech.collection.controller;


import in.dmbase.test.Mathematics;
import in.dmtoolkit.myutil.AuthKey;
import in.exploretech.collection.service.ParticipantService;
import org.mymath.MyMath;
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

    @GetMapping("/test-lib")
    public String testLib(){

        MyMath myMath = new MyMath();

        System.out.println(myMath.add(12, 17));

        AuthKey authKey = new AuthKey();

        Mathematics math = new Mathematics();
        System.out.println(math.add(12, 13));

        System.out.println(authKey.generateSimpleUniqueKey());
        return "success";
    }
}
