package newutil;


import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class MyLegacyPhysicalNamingStrategy implements PhysicalNamingStrategy {
    private static final String SUFFIX = "STRTEST";
    public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return name;
    }

    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironmen) {
        return name;
    }

    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironmen) {
        return name;
    }

    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironmen) {

        return convert(name);
    }

    public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironmen) {
        return name;
    }

    private Identifier convert(Identifier identifier) {
        if (identifier == null || identifier.getText().trim().isEmpty()) {
            return identifier;
        }

        String text = identifier.getText();
        text =  text.concat(SUFFIX).toUpperCase();
        return Identifier.toIdentifier(text, identifier.isQuoted());
    }

}
