package org.camrent.security.commandInjection

import java.util.regex.Pattern

class CommandInjectionProtector {

    companion object {

        fun isSafeCommand(input: String): Boolean {
            // ตรวจสอบและกรองคำสั่งที่ไม่ปลอดภัย
            val unsafeCommands = listOf("rm", "del", "cat", "&&", ";", "|", ">", "<", "&", "=", "$")
            val lowerCaseInput = input.toLowerCase()

            return unsafeCommands.none { lowerCaseInput.contains(it) }
        }

        fun sanitizeInput(input: String): String {
            // กรองคำสั่งที่ไม่ปลอดภัย
            return input.replace(Regex("[&|;<>]"), "")
        }
    }

}
