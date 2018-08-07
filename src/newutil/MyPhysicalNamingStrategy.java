package newutil;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.io.Serializable;

public class MyPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl implements Serializable {
    private static final String SUFFIX = "STRTEST";
    public static final MyPhysicalNamingStrategy INSTANCE = new MyPhysicalNamingStrategy();

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return new Identifier(addSuffix(name.getText()), name.isQuoted());
    }


    protected static String addSuffix(String name) {
        final StringBuilder buf = new StringBuilder(name + SUFFIX);
        return buf.toString().toUpperCase();
    }

}

