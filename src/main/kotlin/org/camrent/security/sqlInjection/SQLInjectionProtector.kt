package org.camrent.security.sqlInjection

class SQLInjectionProtector {

    companion object {

        // ตรวจสอบ SQL Injection
        fun checkForSQLInjection(input: String): Boolean {
            val sqlKeywords = listOf(
                "SELECT",
                "INSERT",
                "UPDATE",
                "DELETE",
                "DROP",
                "TRUNCATE"
            )
            val sanitizedInput = input.toUpperCase()

            // ตรวจสอบว่ามีคำหลัก SQL ที่ไม่ปลอดภัยหรือไม่
            return sqlKeywords.any { sanitizedInput.contains(it) }
        }

        // ทำการกำจัด SQL Injection จากข้อมูล
        fun sanitizeInput(input: String): String {
            // นี่เป็นตัวอย่างเท่านั้น ควรใช้วิธีการที่เหมาะสมกับระบบฐานข้อมูลของคุณ
            // ในที่นี้ใช้การลบคำหลัก SQL ที่ไม่ปลอดภัย
            val sqlKeywords = listOf(
                "SELECT",
                "INSERT",
                "UPDATE",
                "DELETE",
                "DROP",
                "TRUNCATE"
            )

            var sanitizedInput = input
            for (keyword in sqlKeywords) {
                sanitizedInput = sanitizedInput.replace(keyword, "")
            }

            return sanitizedInput
        }
    }
}
