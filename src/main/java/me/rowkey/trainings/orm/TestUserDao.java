package me.rowkey.trainings.orm;

import org.springframework.stereotype.Repository;

/**
 * Author: Bryant Hang
 * Date: 15/1/5
 * Time: 下午7:39
 */
@Repository
public class TestUserDao {

//    public List<TestUser> list() {
//        return this.domainDaoSupport.getListByWhere("1 = 1 order by id desc", 10, 0, new Object[0]);
//    }
//
//    public int count() {
//        try (DBResource dbResource = this.domainDaoSupport.getSqlTemplet().excuteQuery("select sum(`idd`)  from `test_user` where uid=?", 8L)) {
//            ResultSet resultSet = dbResource.getResultSet();
//            resultSet.next();
//            int ret = resultSet.getInt(1);
//            System.out.println(ret);
//            return ret;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return 0;
//    }
}
