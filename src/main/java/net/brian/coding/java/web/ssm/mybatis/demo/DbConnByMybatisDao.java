package net.brian.coding.java.web.ssm.mybatis.demo;

import java.util.List;

public interface DbConnByMybatisDao {
	public DbConnByMybatisBean findById();
	public List<DbConnByMybatisBean> find();
}
