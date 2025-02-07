package in.exploretech.collection.service;

import in.exploretech.config.ExternalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {

    @Autowired
    private ExternalConfig externalConfig;

    public String testExternalConfig(){
        System.out.println(externalConfig.getMongoPort());
        return externalConfig.getMongoPort();
    }
}
