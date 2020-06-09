package com.zm.inference.mapper;

import com.zm.inference.domain.subClass.SubPattern;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @Description TODO
 * @Author zm
 * @Date 2020/6/9 10:48
 **/
public class PatternMapperTest {
    private static PatternMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(PatternMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/PatternMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(PatternMapper.class, builder.openSession(true));
    }

    @Test
    public void testSelectByRuleId() throws FileNotFoundException {
        List<SubPattern> subPatternList1 = mapper.selectFrontByRuleId(3);
        List<SubPattern> subPatternList2 = mapper.selectBackByRuleId(3);

        System.out.println(subPatternList1.get(0).getIsMulti());
        System.out.println(subPatternList2);
    }
}
