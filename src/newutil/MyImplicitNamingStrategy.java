package newutil;

import org.hibernate.boot.model.naming.EntityNaming;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyHbmImpl;
import org.hibernate.internal.util.StringHelper;

public class MyImplicitNamingStrategy extends ImplicitNamingStrategyLegacyHbmImpl {
    private static final String SUFFIX = "STRTEST";


    @Override
    public String transformEntityName(EntityNaming entityNaming) {
        return StringHelper.unqualify(entityNaming.getEntityName() + SUFFIX).toUpperCase();
       // return StringHelper.unqualify(entityNaming.getEntityName() ).toUpperCase();
    }

}