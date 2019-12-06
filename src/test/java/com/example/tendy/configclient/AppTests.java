package com.example.tendy.configclient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tendy
 * @create 2019-11-29 10:05
 **/
public class AppTests {

    @Test
    public void testMatcher() {
        try {
            System.out.println("\\".matches("\\\\"));
            System.out.println(" \n".matches("^[\\s&&^(\\n)]*\\n$"));
            System.out.println(" \n".matches("^[\\s&&^\\n]*\\n$"));
            System.out.println(" \n".matches("^[^\\n&&\\s]*\\n$"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testList() {
        List<Item> itemList = new ArrayList();
        itemList.add(new Item("1", 3));
        itemList.add(new Item("1", 2));
        itemList.add(new Item("1", 1));

        List<Item> itemList2 = new ArrayList();
        itemList2.add(new Item("2", 1));
        itemList2.add(new Item("3", 1));
        itemList2.add(new Item("4", 1));

        itemList.addAll(itemList2);
        System.out.println(itemList.size());

        Map<Integer, List<Item>> map = itemList.stream().collect(Collectors.groupingBy(Item::getV));
        for (Map.Entry<Integer, List<Item>> entry : map.entrySet()) {
            List<Item> list = entry.getValue();
            if (null != list && list.size() > 0) {
                itemList.removeAll(list);
                System.out.println(entry.getKey() + "|-----------|" + itemList.size());
            }
        }
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @ToString
    static class Item {
        private String k;
        private Integer v;

        public Item(Integer v) {
            this.k = String.valueOf(v);
            this.v = v;
        }
    }

    @Test
    public void testDate() {
        System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalTime.now()));
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00").format(LocalDateTime.now()));

        String nowDateStr = LocalDate.now().toString();
        System.out.println(nowDateStr);//2018-03-27

        LocalDate nowDate = LocalDate.parse("2018-03-25");
        System.out.println(nowDate.toString());//2018-03-25


        String nowTimeStr = LocalTime.now().toString();
        System.out.println(nowTimeStr);//13:45:07.105

        LocalTime nowTime = LocalTime.parse("12:10:13");
        System.out.println(nowTime.toString());//12:10:13

        System.out.println(LocalDateTime.now().toString());//2018-03-27T13:55:34.047
        System.out.println(LocalDateTime.now().toLocalDate().toString());//2018-03-27
        System.out.println(LocalDateTime.now().toLocalTime().toString());//13:55:34.047

        System.out.println(LocalDateTime.MAX.toString());//+999999999-12-31T23:59:59.999999999
        System.out.println(LocalDateTime.MIN.toString());//-999999999-01-01T00:00

        date2LocalDateTime(new Date());
        localDateTime2Date(LocalDateTime.now());
    }

    /**
     * Date转换为LocalDateTime
     *
     * @param date
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        System.out.println(localDateTime.toString());//2018-03-27T14:07:32.668
        System.out.println(localDateTime.toLocalDate() + " " + localDateTime.toLocalTime());//2018-03-27 14:48:57.453

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//This class is immutable and thread-safe.@since 1.8
        System.out.println(dateTimeFormatter.format(localDateTime));//2018-03-27 14:52:57
        return localDateTime;
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        Date date = Date.from(zdt.toInstant());
        System.out.println(date.toString());//Tue Mar 27 14:17:17 CST 2018
        return date;
    }

}
