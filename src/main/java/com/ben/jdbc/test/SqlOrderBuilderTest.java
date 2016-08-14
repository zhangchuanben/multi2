package com.ben.jdbc.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.junit.Before;
import org.junit.Test;

import com.ben.jdbc.JdbcMatchMode;
import com.ben.jdbc.SqlBuilder;
import com.ben.jdbc.SqlCriterion;
import com.ben.jdbc.SqlFactory;
import com.ben.jdbc.SqlOrder;

public class SqlOrderBuilderTest {
	private String baseSql = "select * from a";
	@Before
	public void before() {
		//sql = SqlBuilder.baseSql(baseSql);
		//sql.add(SqlFactory.eq("a.name", "zhangsan"));
	}
	
	@Test
	public void testOneSimpleSqlExpression() {
		SqlBuilder sql = getZhangSan();
		System.out.println(sql.getSql());
		assertEquals(getZhangsanSql()+"", sql.getSql());
		assertArrayEquals(new Object[] {"zhangsan"}, sql.getParams());
	}
	
	@Test
	public void testTwoSimpleSqlExpression() {
		SqlBuilder sql = getZhangSan();
		sql.add(SqlFactory.eq("a.address", "bbb"));
		System.out.println(sql.getSql());
		assertEquals(getZhangsanSql()+" and (a.address=?)", sql.getSql());
		assertArrayEquals(new Object[] {"zhangsan","bbb"}, sql.getParams());
	}
	
	@Test
	public void testNullValueSimpleExpression() {
		SqlBuilder sql = getZhangSan();
		sql.add(SqlFactory.eq("a.address", null));
		System.out.println(sql.getSql());
		assertEquals(getZhangsanSql()+" and (a.address=?)", sql.getSql());
		assertArrayEquals(new Object[] {"zhangsan",null}, sql.getParams());
	}
	
	
	@Test
	public void testSimpleSqlExpressionWithOrder() {
		SqlBuilder sql = getZhangSan()
			.addOrder(SqlOrder.desc("a.hello"))
			.addOrder(SqlOrder.asc("a.name"));
		System.out.println(sql.getSql());
		assertEquals(getZhangsanSql()+" order by a.hello desc, a.name asc",
				sql.getSql());
		
	}
	
	@Test
	public void testLike() {
		SqlBuilder sql = getZhangSan()
				.add(SqlFactory.like("a.address", "xxx", JdbcMatchMode.ANYWHERE));
		System.out.println(sql.getSql());
		assertEquals(getZhangsanSql()+" and (a.address like ?)",
				sql.getSql());
		assertArrayEquals(new Object[] {"zhangsan","%xxx%"}, sql.getParams());
	}
	
	@Test
	public void testPropertyEq() {
		SqlBuilder sql = getZhangSan()
				.add(SqlFactory.eqProperty("a.cname", "a.name"));
		System.out.println(sql.getSql());
		assertEquals(getZhangsanSql()+" and (a.cname=a.name)",
				sql.getSql());
		assertArrayEquals(new Object[] {"zhangsan"}, sql.getParams());
	}
	
	@Test
	public void testAnd() {
		SqlCriterion and = SqlFactory.and(SqlFactory.eq("a.name1", "name1"),
				SqlFactory.eqProperty("a.name2", "name3"));
		SqlBuilder sql = getZhangSan()
				.add(and);
		System.out.println(sql.getSql());
		assertEquals(getZhangsanSql()+" and ( (a.name1=?) and (a.name2=name3))",
				sql.getSql());
		assertArrayEquals(new Object[] {"zhangsan","name1"}, sql.getParams());
	}
	
	@Test
	public void testOr() {
		SqlCriterion or = SqlFactory.or(SqlFactory.eq("a.name1", "name1"),
				SqlFactory.eqProperty("a.name2", "name3"));
		SqlBuilder sql = getZhangSan()
				.add(or);
		System.out.println(sql.getSql());
		assertEquals(getZhangsanSql()+" and ( (a.name1=?) or (a.name2=name3))",
				sql.getSql());
		assertArrayEquals(new Object[] {"zhangsan","name1"}, sql.getParams());
	}
	
	@Test
	public void testIn() {
		SqlBuilder sql = getZhangSan()
				.add(SqlFactory.in("name", "val1", "val2"));
		System.out.println(sql.getSql());
		assertEquals(getZhangsanSql()+" and (name in(?,?))",
				sql.getSql());
		assertArrayEquals(new Object[] {"zhangsan","val1", "val2"}, sql.getParams());
	}
	
	@Test
	public void testIsNull () {
		SqlBuilder sql = getZhangSan()
				.add(SqlFactory.isNull("name"));
		System.out.println(sql.getSql());
		assertEquals(getZhangsanSql()+" and (name is null)",
				sql.getSql());
		assertArrayEquals(new Object[] {"zhangsan"}, sql.getParams());
	}
	
	@Test
	public void testIsNotNull () {
		SqlBuilder sql = getZhangSan()
				.add(SqlFactory.isNotNull("name"));
		System.out.println(sql.getSql());
		assertEquals(getZhangsanSql()+" and (name is not null)",
				sql.getSql());
		assertArrayEquals(new Object[] {"zhangsan"}, sql.getParams());
	}
	
	@Test
	public void testNot() {
		SqlBuilder sql = getZhangSan()
				.add(SqlFactory.not(SqlFactory.in("name", "wangwu", "lisi")));
		System.out.println(sql.getSql());
		assertEquals(getZhangsanSql()+" and not (name in(?,?))",
				sql.getSql());
		assertArrayEquals(new Object[] {"zhangsan", "wangwu", "lisi"}, sql.getParams());
	}
	
	@Test
	public void testExist() {
		SqlBuilder sql = getZhangSan()
				.add(SqlFactory.not(SqlFactory.exists(SqlFactory.in("name", "wangwu", "lisi"))));
		System.out.println(sql.getSql());
		assertEquals(getZhangsanSql()+" and not exists (name in(?,?))",
				sql.getSql());
		assertArrayEquals(new Object[] {"zhangsan", "wangwu", "lisi"}, sql.getParams());
	}
	
	@Test
	public void testBetween () {
		SqlBuilder sql = getZhangSan()
				.add(SqlFactory.between("age", 18, 22));
		System.out.println(sql.getSql());
		assertEquals(getZhangsanSql()+" and (age between ? and ?)",
				sql.getSql());
		assertArrayEquals(new Object[] {"zhangsan", 18, 22}, sql.getParams());
	}
	
	@Test
	public void testRawSql () {
		SqlBuilder sql = getZhangSan()
				.add(SqlFactory.rawSql("myage between ? and ?",18,22));
		System.out.println(sql.getSql());
		assertEquals(getZhangsanSql()+" and (myage between ? and ?)",
				sql.getSql());
		assertArrayEquals(new Object[] {"zhangsan", 18, 22}, sql.getParams());
		
		DetachedCriteria criteria = DetachedCriteria.forClass(String.class);
		criteria.setProjection(Projections.distinct(Projections.property("userType")));
	}
	
	private SqlBuilder getZhangSan() {
		SqlBuilder sql = getSb(null);
		sql.add(SqlFactory.eq("a.name", "zhangsan"));
		return sql;
	}
	
	private SqlBuilder getSb(String baseSql) {
		if (baseSql == null) {
			return SqlBuilder.baseSql(this.baseSql);
		} else {
			return SqlBuilder.baseSql(baseSql);
		}
	}
	
	private String getZhangsanSql() {
		return "select * from a where 1=1 and (a.name=?)";
	}
	

}
