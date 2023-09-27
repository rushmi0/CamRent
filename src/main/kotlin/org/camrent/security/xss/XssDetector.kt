package org.camrent.security.xss

class XssDetector {

    companion object {

        private val htmlTagsPattern = "<[^>]*>".toRegex()
        private val jsPattern = ".*<script>.*</script>.*".toRegex()

        fun containsHtmlTags(text: String): Boolean {
            return htmlTagsPattern.containsMatchIn(text)
        }

        fun containsJavascript(text: String): Boolean {
            return jsPattern.containsMatchIn(text)
        }
    }
}
