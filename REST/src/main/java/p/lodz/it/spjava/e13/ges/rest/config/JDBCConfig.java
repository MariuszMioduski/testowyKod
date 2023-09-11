package p.lodz.it.spjava.e13.ges.rest.config;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.sql.Connection;
@DataSourceDefinition(
        name = "java:app/jdbc/gesdbDS",
        className = "org.postgresql.ds.PGSimpleDataSource",
        user = "gesuser",
        password = "theges",
        serverName = "127.0.0.1",
        portNumber = 5432,
        databaseName = "gesdb",
        isolationLevel = Connection.TRANSACTION_READ_COMMITTED
//        isolationLevel = Connection.TRANSACTION_READ_UNCOMMITTED
)

public class JDBCConfig {
//    @PersistenceContext(unitName = "gesdbPU")
//    private EntityManager em;
}