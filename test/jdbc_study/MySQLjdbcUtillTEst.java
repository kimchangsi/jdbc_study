package jdbc_study;


import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import jdbc_study.jdbc.MySQLjdbcUtil;

public class MySQLjdbcUtillTEst {
	static final Logger log = LogManager.getLogger();
	@Test
	public void testConnection() throws SQLException {
		Connection conn = MySQLjdbcUtil.getConnection();
		log.trace(String.format("Connected to database %s successfully.", conn.getCatalog()));
		Assert.assertNotNull(conn);
	}

}
