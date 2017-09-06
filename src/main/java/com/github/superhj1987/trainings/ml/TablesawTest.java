package com.github.superhj1987.trainings.ml;

import it.unimi.dsi.fastutil.floats.FloatArrayList;
import tech.tablesaw.api.DateTimeColumn;
import tech.tablesaw.api.LongColumn;
import tech.tablesaw.api.Table;

import java.io.IOException;

import static tech.tablesaw.api.QueryHelper.column;
import static tech.tablesaw.filtering.AllOf.allOf;

/**
 * Created by Bryant.Hang on 2017/9/6.
 */
public class TablesawTest {
    public static void main(String[] args) throws IOException {
        Table ops = Table.read().csv(
                TablesawTest.class.getClassLoader().getResource("data/operations.csv").getFile());                             // load data

        DateTimeColumn start = ops.dateColumn("Date").atTime(ops.timeColumn("Start"));
        DateTimeColumn end = ops.dateColumn("Date").atTime(ops.timeColumn("End"));
        LongColumn duration = start.differenceInSeconds(end);                        // calc duration
        duration.setName("Duration");
        ops.addColumn(duration);

        Table filtered = ops.selectWhere(                                            // filter
                allOf
                        (column("date").isInQ2(),
                                (column("SKU").startsWith("429")),
                                (column("Operation").isEqualTo("Assembly"))));

        Table summary = filtered.median("Duration").by("Facility", "Shift");         // group medians
        FloatArrayList tops = summary.floatColumn("Median [Duration]").top(5);

        System.out.println(tops);
    }
}
