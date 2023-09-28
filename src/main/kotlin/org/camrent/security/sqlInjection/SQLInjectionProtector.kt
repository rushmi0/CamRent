package org.camrent.security.sqlInjection

class SQLInjectionProtector {

    companion object {

        private val sqlKeywords = SQL.keyWords


        // ตรวจสอบ SQL Injection
        fun checkForSQLInjection(input: String): Boolean {
            val sanitizedInput = input.toUpperCase()

            // ตรวจสอบว่ามีคำหลัก SQL ที่ไม่ปลอดภัยหรือไม่
            return sqlKeywords.any { sanitizedInput.contains(it) }
        }

        // ทำการกำจัด SQL Injection จากข้อมูล
        fun sanitizeInput(input: String): String {

            var sanitizedInput = input
            for (keyword in sqlKeywords) {
                sanitizedInput = sanitizedInput.replace(keyword, "")
            }

            return sanitizedInput
        }
    }
}
