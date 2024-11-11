package com.neton.utils;

import cn.hutool.core.date.DateRange;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class DateUtils {

    /**
     * 将毫秒级别的时间戳转换成对应的时间
     * @param millis
     * @param formatPattern
     * @return
     */
    public static String convertMillisToTimeString(long millis, String formatPattern) {
        Instant instant = Instant.ofEpochMilli(millis);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
        return localDateTime.format(formatter);
    }

    /**
     * 给定时间给定格式，返回格式化好的字符串
     * @param date
     * @param format
     * @return
     */
    public static String formatDateToString(Date date, String format) {
        if (date == null || format == null || format.isEmpty()) {
            throw new IllegalArgumentException("Date and format cannot be null or empty");
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 根据当前系统时区，计算与UTC时间相差几个小时
     * @return
     */
    public static int calculateTimeZoneOffset() {
        // 获取当前系统的默认时区
        ZoneId systemZone = ZoneId.systemDefault();
        //   log.info("当前系统时区==========> :{}",systemZone.getId());
        // 获取当前时间
        //   log.info("当前系统时间 ===============> :{}",formatDateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));

        // 获取上海时区
        ZoneId shanghaiZoneId = ZoneId.of("Asia/Shanghai");

        // 获取当前时间在上海时区的时间
        ZonedDateTime shanghaiTime = ZonedDateTime.now(shanghaiZoneId);

        // 计算时差
        int hourOffset = shanghaiTime.getHour() - LocalDateTime.now().atZone(systemZone).getHour();
        //   log.info("与上海时区相差小时数=================> :{} " + hourOffset);

        return hourOffset;

    }

    /**
     * 获取系统时间
     * @return
     */
    public static Date getSystemDate(){
        int offset = calculateTimeZoneOffset();
        //    log.info("getSystemDate[offset]============> :{}",offset);
        Long time = Long.parseLong(String.valueOf(offset)) * 60L*60L * 1000L;
        Long time1 = new Date().getTime();
        Date date = new Date(time1 + time);
        //    log.info("当前时间 ===============> :{}",formatDateToString(date,"yyyy-MM-dd HH:mm:ss"));
        return date;
    }




    /**
     * 给定LocalDate时间和type类型转换成对应的时间
     * @param localDate
     * @param type 1 开始时间 2结束时间
     * @return
     */
    public static Long convertToLocalDateToLong(Date localDate ,Integer type){
        if (type == 1){
            String toString = formatDateToString(localDate, "yyyy-MM-dd");
            String str = toString+" 00:00:00";
            return StringFormDate(str,"yyyy-MM-dd HH:mm:ss").getTime();
        }else {
            String toString = formatDateToString(localDate, "yyyy-MM-dd");
            String str = toString+" 23:59:59";
            return StringFormDate(str,"yyyy-MM-dd HH:mm:ss").getTime();
        }
    }


    /**
     * 输入一个date类型的时间，返回该月有多少周
     * @param date
     * @return
     */
    public static int calculateWeeksInMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        // 设置日历到该月的第一天
        calendar.set(year, month, 1);

        int weeksInMonth = 0;

        while (calendar.get(Calendar.MONTH) == month) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            // 如果是该月的第一天，或者是周日，则表示新的一周开始
            if (calendar.get(Calendar.DAY_OF_MONTH) == 1 || dayOfWeek == Calendar.SUNDAY) {
                weeksInMonth++;
            }

            // 递增一天
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return weeksInMonth;
    }

    /**
     * 输入两个date类型的时间，返回该时间内，有多少周(自然周)
     * @param startDate
     * @param endDate
     * @return
     */
    public static int calculateWeeksBetweenDatesNature(Date startDate, Date endDate) {
        // 将Date转换为LocalDate
        LocalDate startLocalDate = convertToLocalDate(startDate);
        LocalDate endLocalDate = convertToLocalDate(endDate);

        // 计算完整的周数
        long weeks = ChronoUnit.WEEKS.between(startLocalDate, endLocalDate);

        return (int) weeks;
    }

    /**
     * 输入两个date类型的时间，返回该时间内，有多少周(非自然周)
     * @param startDate
     * @param endDate
     * @return
     */
    public static int calculateWeeksBetweenDatesArtificial(Date startDate, Date endDate) {
        // 将Date转换为LocalDate
        LocalDate startLocalDate = convertToLocalDate(startDate);
        LocalDate endLocalDate = convertToLocalDate(endDate);

        int weeksCount = 1;

        while (startLocalDate.plusDays(7).isBefore(endLocalDate) || startLocalDate.plusDays(7).isEqual(endLocalDate)) {
            startLocalDate = startLocalDate.plusDays(7);
            weeksCount++;
        }
        return weeksCount;
    }

    /**
     * 输入一个开始时间和结束时间，返回两者相差的天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static Integer daysBetween(Date startDate, Date endDate) {
        // 获取毫秒级时间戳
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();

        // 计算毫秒级时间差
        long diff = endTime - startTime;

        // 计算天数差
        Long days = diff / (24 * 60 * 60 * 1000);

        return days.intValue();
    }



    /**
     * 输入一个date类型的时间，返回小时
     * @param date
     * @return
     */
    public static Integer detectTimePeriod(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String timeString = sdf.format(date);

        int hour = Integer.parseInt(timeString.split(":")[0]);

        return hour;
    }

    /**
     * 输入一个data类型的时间，返回该时间所在的月份
     * @param date
     * @return
     */
    public static int detectMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 获取月份，注意Calendar.MONTH是从0开始的，所以需要加1
        int month = calendar.get(Calendar.MONTH) + 1;

        return month;
    }

    /**
     * 输入一个时间，返回一个LocalDate类型的时间
     * @param date
     * @return
     */
    private static LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 输入一个时间，返回这个时间是这个月的第几周
     * @param date
     * @return
     */
    public static int detectWeekOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 获取该月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();

        // 设置星期几的值为 calendar.getFirstDayOfWeek()
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());

        // 获取该月的第一周的第一天
        Date firstDayOfFirstWeek = calendar.getTime();

        // 计算该日期是该月的第几个自然周
        long diff = date.getTime() - firstDayOfFirstWeek.getTime();
        int weekOfMonth = (int) (diff / (7 * 24 * 60 * 60 * 1000)) + 1;

        return weekOfMonth;
    }

    /**
     * 根据时间返回是周几
     * @param date
     * @return
     */
    public static int detectDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 获取该日期是一周中的第几天，1表示星期日，2表示星期一，以此类推
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // 对星期日进行特殊处理，将其值转换为7
        if (dayOfWeek == Calendar.SUNDAY) {
            dayOfWeek = 7;
        } else {
            // 星期一到星期六，将值减1
            dayOfWeek -= 1;
        }

        return dayOfWeek;
    }

    /**
     * 根据字符串格式化时间
     * @param time
     * @param pattern
     * @return
     */
    public static Date StringFormDate(String time , String pattern){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date parse = sdf.parse(time);
            return parse;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }





    /**
     * 计算非自然周的数量
     * @param startDate
     * @param endDate
     * @return
     */
    public static int calculateNonNaturalWeeks(Date startDate, Date endDate) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        int nonNaturalWeeksCount = 0;

        while (startCalendar.before(endCalendar)) {
            // 获取当前周的结束日期
            Calendar currentWeekEnd = (Calendar) startCalendar.clone();
            currentWeekEnd.add(Calendar.DAY_OF_WEEK, 6);

            // 判断当前周是否跨越自然周的边界
            if (currentWeekEnd.after(endCalendar)) {
                break;
            }

            nonNaturalWeeksCount++;
            startCalendar.add(Calendar.DAY_OF_WEEK, 7);
        }

        return nonNaturalWeeksCount;
    }

    /**
     * 计算给定时间在非自然周内是第几周
     * @param calculationTime
     * @param startDate
     * @return
     */
    public static int calculateWeekNumber(Date calculationTime, Date startDate) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);

        Calendar calculationCalendar = Calendar.getInstance();
        calculationCalendar.setTime(calculationTime);

        int weekNumber = 1;

        while (startCalendar.before(calculationCalendar)) {
            startCalendar.add(Calendar.DAY_OF_WEEK, 7);
            weekNumber++;
        }

        return weekNumber;
    }

    /**
     * 获取给定日期的前一天
     * @param date
     * @return
     */
    public static Date getYesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -1); // 将日期减去一天
        return calendar.getTime();
    }

    /**
     *  获取给定日期的前一年
     * @param date
     * @return
     */
    public static Date getLastYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -1); // 将日期减去一年

        return calendar.getTime();
    }


    /**
     *  获取给定日期的前一月
     * @param date
     * @return
     */
    public static Date getLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1); // 将日期减去一月

        return calendar.getTime();
    }

    /**
     * 给定月份获取开始时间结束时间
     * @param startTime
     * @param endTime
     * @return
     */
    public static Map<String,Long> getLastMonthStartAndEndTime(Date startTime,Date endTime){
        String startTimeToString = formatDateToString(startTime, "yyyy-MM");
        String endTimeToString = formatDateToString(endTime, "yyyy-MM-dd");
        Date endTimeTo = StringFormDate(endTimeToString + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
        Date startTimeTo = StringFormDate(startTimeToString + "-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Map<String,Long> res = new HashMap<>();
        res.put("startTime", startTimeTo.getTime());
        res.put("endTime",endTimeTo.getTime());
        return res;
    }



    /**
     * 获取时间的凌晨和结束时间
     * @param date
     * @return
     */
    public static Map<String,Long> getDateStartAndEndTime(Date date){
        String toString = formatDateToString(date, "yyyy-MM-dd");
        Date startTime = StringFormDate(toString + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date endTime = StringFormDate(toString + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
        Map<String,Long> res = new HashMap<>();
        res.put("startTime", startTime.getTime());
        res.put("endTime",endTime.getTime());
        return res;
    }

    /**
     * 获取时间的凌晨和结束时间
     * @param date
     * @return
     */
    public static Map<String,Long> getDateStartAndEndTimeYear(Date date){
        String toString = formatDateToString(date, "yyyy");
        Date startTime = StringFormDate(toString + "-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date endTime = StringFormDate(toString + "-12-31 23:59:59", "yyyy-MM-dd HH:mm:ss");
        Map<String,Long> res = new HashMap<>();
        res.put("startTime", startTime.getTime());
        res.put("endTime",endTime.getTime());
        return res;
    }

    /**
     * 获取给定日期所在月份的最后一天
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 设置日历到下个月的第一天
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        // 减去一天，得到当前月份的最后一天
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        return calendar.getTime();
    }


    /**
     * 获取给定日期的上一个自然周的开始日期（周一）和结束日期（周日）
     * @param date
     * @return
     */
    public static Map<String, Long> getLastWeekDates(Date date) {
        Map<String, Long> result = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 设置日历到上一个星期的开始（周一）
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        result.put("startTime", calendar.getTime().getTime());

        // 设置日历到上一个星期的结束（周日）
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        result.put("endTime", calendar.getTime().getTime());

        return result;
    }


    /**
     * 往后推一年时间
     * @param startDate
     * @param endDate
     * @return
     */
    public static Map<String,Long> calculateHalfYearAfter(Date startDate, Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, +12); // 将开始时间往前推半年
        startDate.setTime(calendar.getTimeInMillis());
        calendar.setTime(endDate);
        calendar.add(Calendar.MONTH, +12);
        endDate.setTime(calendar.getTimeInMillis());

        Map<String,Long> res = new HashMap<>();
        res.put("startTime", startDate.getTime());
        res.put("endTime",endDate.getTime());
        return res;
    }

    /**
     * 往前推半年时间
     * @param startDate
     * @param endDate
     * @return
     */
    public static Map<String,Long> calculateHalfYearAgo(Date startDate, Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, -6); // 将开始时间往前推半年
        startDate.setTime(calendar.getTimeInMillis());
        calendar.setTime(endDate);
        calendar.add(Calendar.MONTH, -6);
        endDate.setTime(calendar.getTimeInMillis());

        Map<String,Long> res = new HashMap<>();
        res.put("startTime", startDate.getTime());
        res.put("endTime",endDate.getTime());
        return res;
    }

    public static String formatUTCDate(Date date,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // 设置为 UTC 时区
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }

    /**
     * 获取utc时间
     * @return
     */
    public static Date getUTCDate(){
        // 将 ZonedDateTime 转换为 Instant
        Instant instant =  Instant.now();
        // 将 Instant 转换为 Date
        Date utcDate = Date.from(instant);
        String s = formatUTCDate(utcDate, "yyyy-MM-dd HH:mm:ss");
        Date utc = StringFormDate(s,"yyyy-MM-dd HH:mm:ss");
        return utc;
    }

    /**
     * 获取utc时区的时间
     * @param date
     * @return
     */
    public static Date toUTCDate(Date date){
        System.out.println(formatUTCDate(date,"yyyy-MM-dd HH:mm:ss"));
        String s = formatUTCDate(date, "yyyy-MM-dd HH:mm:ss");
        Date utc = StringFormDate(s,"yyyy-MM-dd HH:mm:ss");
        return utc;
    }

    public static void main(String[] args) {
        // 测试方法
        Date utcDate = new Date(1727654400000L);

        System.out.println(formatUTCDate(utcDate,"yyyy-MM-dd HH:mm:ss"));
        String s = formatUTCDate(utcDate, "yyyy-MM-dd HH:mm:ss");
        Date utc = StringFormDate(s,"yyyy-MM-dd HH:mm:ss");
        String string = formatDateToString(utc, "yyyy-MM-dd HH:mm:ss");
        System.out.println(string);
    }





    /**
     * 计算输入的开始时间和结束时间之间相差的天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static int calculateDaysDifference(String startDate, String endDate) {
        LocalDate startLocalDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate endLocalDate = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

        long daysDifference = java.time.temporal.ChronoUnit.DAYS.between(startLocalDate, endLocalDate);

        // 如果相差天数小于等于0，则返回1
        return (int)daysDifference+1;
    }

    /**
     * 根据整数和日期，计算往前推的日期
     * @param startDate
     * @param endDate
     * @return
     */
    public static Map<String,Long> calculatePreviousDates(Date startDate,Date endDate) {
        String startDateString = formatDateToString(startDate, "yyyy-MM-dd");
        String endDateString = formatDateToString(endDate, "yyyy-MM-dd");
        int daysToSubtract = calculateDaysDifference(startDateString, endDateString);
        LocalDate date = LocalDate.parse(startDateString, DateTimeFormatter.ISO_DATE);
        List<String> previousDates = new ArrayList<>();

        for (int i = 1; i <= daysToSubtract; i++) {
            LocalDate previousDate = date.minusDays(i);
            previousDates.add(previousDate.format(DateTimeFormatter.ISO_DATE));
        }
        String endTime = previousDates.get(0);
        String startTime = previousDates.get(previousDates.size() - 1);
        Map<String,Long> res = new HashMap<>();
        res.put("endTime",StringFormDate(endTime+" 23:59:59","yyyy-MM-dd HH:mm:ss").getTime());
        res.put("startTime",StringFormDate(startTime+" 00:00:00","yyyy-MM-dd HH:mm:ss").getTime());
        return res;
    }

    /**
     * 输入一个时间Date时间，返回上一天时间
     * @param inputDate
     * @return
     */
    public static Map<String,Long> getPreviousDay(Date inputDate) {
        // 将Date类型转换为LocalDate类型
        LocalDate localDate = inputDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

        // 获取前一天的日期
        LocalDate previousDay = localDate.minusDays(1);

        // 将LocalDate转换为Date类型
        Date date = java.sql.Date.valueOf(previousDay);
        String dateToString = formatDateToString(date, "yyyy-MM-dd");
        Map<String,Long> res = new HashMap<>();
        res.put("endTime",StringFormDate(dateToString+" 23:59:59","yyyy-MM-dd HH:mm:ss").getTime());
        res.put("startTime",StringFormDate(dateToString+" 00:00:00","yyyy-MM-dd HH:mm:ss").getTime());
        return res;
    }

    /**
     * 接受一个 ISO 8601 格式的时间字符串和目标格式的参数，然后返回格式化后的时间字符串
     * @param iso8601String
     * @param targetFormat
     * @return
     */
    public static String formatISO8601ToCustom(String iso8601String, String targetFormat) {
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_INSTANT;
        Instant instant = Instant.from(isoFormatter.parse(iso8601String));

        DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern(targetFormat)
                .withZone(ZoneId.of("Asia/Shanghai")); // 设置为东八区时区

        return targetFormatter.format(instant);
    }




    /**
     * 将毫秒数转换为天
     * @param numberOfMilliseconds
     * @return
     */
    public static BigDecimal calculateDays(BigDecimal  numberOfMilliseconds) {
        if (numberOfMilliseconds.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO;
        } else {
            Long temp = 24L * 60 * 60L*1000L;
            BigDecimal divide = numberOfMilliseconds.divide(new BigDecimal(temp), 2, BigDecimal.ROUND_UP);
            return divide;
        }
    }


    /**
     * 接受两个Date类型的时间参数，返回四舍五入保留两位小数的BigDecimal类型的占比：
     * @param startTime
     * @param endTime
     * @return
     */
    public static BigDecimal calculateTimeRatio(Date startTime, Date endTime, Date currentTime) {

        if (currentTime == null){
            return new BigDecimal("0");
        }
        // 计算已经过去的时间
        long elapsedMillis = currentTime.getTime() - startTime.getTime();

        // 计算总共需要的时间
        long totalMillis = endTime.getTime() - startTime.getTime();

        // 避免除以零的情况
        if (totalMillis == 0) {
            return BigDecimal.ZERO;
        }

        // 计算占比
        BigDecimal ratio = new BigDecimal(elapsedMillis).divide(new BigDecimal(totalMillis), 2, BigDecimal.ROUND_HALF_UP);

        // 确保占比在[0, 1]范围内
        return ratio.min(BigDecimal.ONE).max(BigDecimal.ZERO);
    }



    private static Date getStartDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1, 0, 0, 0);
        return calendar.getTime();
    }

    private static Date getEndDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1, 23, 59, 59);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }
}
