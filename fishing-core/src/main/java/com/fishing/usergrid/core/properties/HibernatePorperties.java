package com.fishing.usergrid.core.properties;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(locations = "classpath:application-database.yaml", prefix = "hibernate")
public class HibernatePorperties {
	@NotNull
	private String dialect;
	private boolean show_sql;
	private Hbm2ddl hbm2ddl;
	
	
	public String getDialect() {
		return dialect;
	}


	public void setDialect(String dialect) {
		this.dialect = dialect;
	}


	public boolean isShow_sql() {
		return show_sql;
	}


	public void setShow_sql(boolean show_sql) {
		this.show_sql = show_sql;
	}


	public Hbm2ddl getHbm2ddl() {
		return hbm2ddl;
	}


	public void setHbm2ddl(Hbm2ddl hbm2ddl) {
		this.hbm2ddl = hbm2ddl;
	}


	public static class Hbm2ddl {
		private String auto;

		public String getAuto() {
			return auto;
		}

		public void setAuto(String auto) {
			this.auto = auto;
		}
		
	}
}
