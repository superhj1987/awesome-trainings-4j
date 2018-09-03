package me.rowkey.trainings.orm;

import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * Author: Bryant.Hang
 * Date: 2018/8/22
 * Email: superhj1987@126.com
 */
public class DataEqualizaAlgorithm implements PreciseShardingAlgorithm<Long> {


    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {

        Long uid = preciseShardingValue.getValue();
        Long result = uid % 2;
        for (String dataSource : collection) {
            if (dataSource.replace("datasource", "").equals(String.valueOf(result))) {
                return dataSource;
            }
        }
        return null;
    }
}
