package com.zm.inference.mapper;

import com.zm.inference.domain.Fact;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @Description TODO
 * @Author zm
 * @Date 2020/6/9 11:04
 **/
public class FactMapperTest {
    private static FactMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(FactMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/FactMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(FactMapper.class, builder.openSession(true));
    }

    @Test
    public void testSelectByPatternId() throws FileNotFoundException {
        List<Fact> factList = mapper.selectByPatternId(6);
        System.out.println(factList);
    }
}
