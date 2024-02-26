package br.com.bytebuilder.managepix.domain.utils

import org.springframework.data.util.Pair
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@Component
object DateUtils {
    const val FORMAT_YYYY_MM_DD = "yyyy-MM-dd"
    fun formatISO(dt: Date?, hh: Int, mm: Int, ss: Int): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val t: String = sdf.format(dt)
        return String.format("%s %02d:%02d:%02d", t, hh, mm, ss)
    }

    fun formatISO(dt: Date?, pattern: String?): String {
        val sdf = pattern?.let { SimpleDateFormat(it) }
        val t: String? = sdf?.format(dt)
        return String.format("%s", t)
    }

    fun formatISO(dt: Date?): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val t: String = sdf.format(dt)
        return String.format("%s", t)
    }

    fun toLocalDate(dt: Date): LocalDate {
        return dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    }

    fun toDate(dt: LocalDate): Date {
        return Date.from(dt.atStartOfDay(ZoneId.systemDefault()).toInstant())
    }

    fun toDate(isoDtString: String?): Date {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        try {
            return sdf.parse(isoDtString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return Date()
    }

    fun toTimestamp(dt: Date?, horaMesSec: String): Long {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            return dateFormat.parse(formatISO(dt) + " " + horaMesSec).getTime() / 1000
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return 0
    }

    fun tomorrow(dt: Date?): Date? {
        var dt = dt
        dt = Date()
        val c: Calendar = Calendar.getInstance()
        c.setTime(dt)
        c.add(Calendar.DATE, 1)
        dt = c.getTime()
        return dt
    }

    fun yesterday(dt: Date?): Date? {
        var dt = dt
        val c: Calendar = Calendar.getInstance()
        c.setTime(dt)
        c.add(Calendar.DATE, -1)
        dt = c.getTime()
        return dt
    }

    fun stringToDate(dateString: String?, format: String): Date? {
        if ((dateString?.isBlank() == true) || (format.isBlank() == true)) {
            return null
        }
        try {
            var instant: Instant? = null

            // it means full date including hour: dd/MM/yyyy HH:mm:ss
            instant = if (format.length > 10) {
                val localDate: LocalDateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(format))
                localDate.atZone(ZoneId.systemDefault()).toInstant()
            } else {
                // it means date: dd/MM/yyyy
                val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(format)
                val localDate: LocalDate = LocalDate.parse(dateString, formatter)
                localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
            }
            return Date.from(instant)
        } catch (e: Exception) {
            // TODO: handle exception
        }
        return null
    }
}
