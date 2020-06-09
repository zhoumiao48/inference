package com.zm.inference.mapper;

import com.zm.inference.domain.mapClass.MapPatternFact;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description MapPatternFactMapper的测试类
 * @Author zm
 * @Date 2020/5/18 9:12
 **/
public class MapPatternFactMapperTest {
    private static MapPatternFactMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build
                (MapPatternFactMapperTest.class.getClassLoader().
                        getResourceAsStream("mybatisTestConfiguration/MapPatternFactMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(MapPatternFactMapper.class, builder.openSession(true));
    }

    @Test
    public void testInsertList() throws FileNotFoundException {
        List<MapPatternFact> mapPatternFactList = new ArrayList<>();
        MapPatternFact m1 = new MapPatternFact();
        m1.setPId(1);
        m1.setFId(1);
        MapPatternFact m2 = new MapPatternFact();
        m2.setPId(1);
        m2.setFId(2);
        mapPatternFactList.add(m1);
        mapPatternFactList.add(m2);
        Integer ans = mapper.insertList(mapPatternFactList);
        System.out.println(ans);
    }
}
