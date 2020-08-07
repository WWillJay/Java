package com.example.demo;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.util.JdbcConstants;
import com.example.demo.mybatisplus.mapper.UserMapper;
import com.example.demo.mybatisplus.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out :: println);
    }

    @Test
    public void updateTest() {
        User user = new User();
        user.setId(1l);
        user.setName("aaa");
        userMapper.update(user, null);
    }
    @Test
    public void deleteTest() {
        userMapper.deleteById(1l);
    }

    @Test
    public void ans(){
        long start = System.currentTimeMillis();
//        MySqlStatementParser mySqlStatementParser = new MySqlStatementParser("select name,price from tb_order  where id in (select id from tb_order1 where id = 1)");
//
//        SQLSelectStatement sqlSelectStatement = (SQLSelectStatement) mySqlStatementParser.parseSelect();
//
//        SQLSelect sqlSelect = sqlSelectStatement.getSelect();
//        SQLSelectQuery sqlSelectQuery = sqlSelect.getQuery();
//        if (sqlSelectQuery instanceof MySqlSelectQueryBlock) {
//            MySqlSelectQueryBlock mySqlSelectQueryBlock = (MySqlSelectQueryBlock) sqlSelectQuery;
//            MySqlOutputVisitor where = new MySqlOutputVisitor(new StringBuilder());
//            // 获取where 条件
//            mySqlSelectQueryBlock.getWhere().accept(where);
//            System.out.println("##########where###############");
//            System.out.println(where.getAppender() );
//            // 获取表名
//            System.out.println("############table_name##############");
//            MySqlOutputVisitor tableName = new MySqlOutputVisitor(new StringBuilder());
//            mySqlSelectQueryBlock.getFrom().accept(tableName);
//            System.out.println(tableName.getAppender());
//
//            //   获取查询字段
//            System.out.println("############查询字段##############");
//            System.out.println(mySqlSelectQueryBlock.getSelectList());
//        }

        String sql = "select name,price from tb_order  where id in (select id from tb_order1 where id = 1)";
        String dbType = JdbcConstants.MYSQL;

        //格式化输出
        String result = SQLUtils.format(sql, dbType);
        System.out.println(result); // 缺省大写格式
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);

        //解析出的独立语句的个数
        System.out.println("size is:" + stmtList.size());
        for (int i = 0; i < stmtList.size(); i++) {

            SQLStatement stmt = stmtList.get(i);
            MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
            stmt.accept(visitor);

//            //获取表名称
//            System.out.println("Tables : " + visitor.getCurrentTable());
            //获取操作方法名称,依赖于表名称
            System.out.println("Manipulation : " + visitor.getTables().keySet());
            //获取字段名称
            System.out.println("fields : " + visitor.getColumns());
        }

        System.err.println(System.currentTimeMillis() - start);
    }

}
