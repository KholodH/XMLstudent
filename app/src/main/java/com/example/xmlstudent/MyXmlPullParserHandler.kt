package com.example.xmlstudent

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class MyXmlPullParserHandler {
    private val studentde = ArrayList<Students>()
    private var student: Students? = null
    private var text: String? = null

//    private var name = ""
//    private var marks = 0.0
//    private var id = 0


    fun parse(inputStream: InputStream): List<Students> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                when (eventType) {
                    XmlPullParser.START_TAG ->  if (tagName.equals("student", ignoreCase = true)) {
                        // create a new instance of student
                        student = Students()
                    }

                    XmlPullParser.TEXT -> text = parser.text
                        XmlPullParser.END_TAG ->
                            if (tagName.equals("student", ignoreCase = true)) {
                            // add student object to list
                            student?.let { studentde.add(it) }
                        } else if (tagName.equals("id", ignoreCase = true)) {
                            student!!.id = Integer.parseInt(text)
                        } else if (tagName.equals("name", ignoreCase = true)) {
                            student!!.name = text
                        } else if (tagName.equals("marks", ignoreCase = true)) {
                            student!!.marks = java.lang.Float.parseFloat(text)
                        }

                    else -> {
                    }
                }
                eventType = parser.next()
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return studentde
    }
}