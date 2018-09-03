package me.rowkey.trainings.rj;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.ArrayUtils;
import rx.Observable;
import rx.internal.util.InternalObservableUtils;

import java.util.concurrent.TimeUnit;

/**
 * Author: Bryant.Hang
 * Date: 2018/4/9
 * Email: superhj1987@126.com
 */
public class RxWindow {
    public static void main(String[] args) throws InterruptedException {
        Observable<Integer> source = Observable.interval(50, TimeUnit.MILLISECONDS)
                .map(i -> RandomUtils.nextInt(2));

        source.window(1, TimeUnit.SECONDS).subscribe(window -> {
            int[] metrics = new int[2];
            window.subscribe(integer -> metrics[integer]++,
                    InternalObservableUtils.ERROR_NOT_IMPLEMENTED,
                    () -> System.out.println("窗口Metrics:" + ArrayUtils.toString(metrics)));
        });
        TimeUnit.SECONDS.sleep(10000);
    }
}
