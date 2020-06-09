package com.zm.inference.mapper;

import com.zm.inference.domain.subClass.SubRule;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @Description TODO
 * @Author zm
 * @Date 2020/6/9 19:50
 **/
public class RuleMapperTest {
    private static RuleMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(RuleMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/RuleMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(RuleMapper.class, builder.openSession(true));
    }

    @Test
    public void testSelectAllRule() throws FileNotFoundException {
        List<SubRule> ruleList = mapper.selectAllRule();
        System.out.println(ruleList.size());
        for(SubRule sr: ruleList){
            System.out.println(sr);
        }
    }
}
