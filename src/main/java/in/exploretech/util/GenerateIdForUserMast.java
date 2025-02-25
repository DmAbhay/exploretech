package in.exploretech.util;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class GenerateIdForUserMast implements IdentifierGenerator {

    private static final String PREFIX = "EXPLORETECH";
    private static final String SUFFIX = "USERMAST";

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        try {
            String query = "SELECT TOP 1 id FROM userMast WHERE id LIKE 'EXPLORETECH%' ORDER BY id DESC";
            List result = session.createNativeQuery(query).getResultList();

            int nextId = 100001; // Default starting number
            if (!result.isEmpty()) {
                String intialId = (String) result.get(0);
                String intermediatId = intialId.replace(SUFFIX, "");
                int lastNumber = Integer.parseInt(intermediatId.replace(PREFIX, ""));
                nextId = lastNumber + 1;
            }

            return PREFIX + nextId + SUFFIX;
        } catch (Exception e) {
            throw new RuntimeException("Error generating custom ID", e);
        }
    }
}


