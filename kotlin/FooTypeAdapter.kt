
/**
 *
 *
 * @author
 * @date 
 */

class FooTypeAdapter : TypeAdapter<HashMap<String, String>>() {

    companion object {
        private val KEY_NAME = "key"
        private val LABEL_NAME = "label"
    }

    @Throws(IOException::class)
    override fun write(writer: JsonWriter, values: HashMap<String, String>?) {
        if (values == null) {
            writer.nullValue()
            return
       }
       writeMap(writer, values)
    }

    @Throws(IOException::class)
    private fun writeMap(writer: JsonWriter, values: HashMap<String, String>) {
        writer.beginArray()
        for ((key, value) in values) {
            writer.beginObject()
            writer.name(KEY_NAME).value(key)
            writer.name(LABEL_NAME).value(value)
            writer.endObject()
       }
       writer.endArray()
    }

    @Throws(IOException::class)
    override fun read(reader: JsonReader): HashMap<String, String>? {
        try {
            val token = reader.peek()
            if (token == JsonToken.NULL) {
                reader.nextNull()
                return null
           }

           if (token == JsonToken.BEGIN_ARRAY) {
               return readArray(reader)
           }
        } 
        catch (e: IOException) {
            return null
        }
       return HashMap()
    }

    @Throws(IOException::class)
    private fun readArray(reader: JsonReader): HashMap<String, String> {
        val map = HashMap<String, String>()
        reader.beginArray()
        while (reader.hasNext()) {
            val pair = readObject(reader)
            map[pair.first!!] = pair.second!!
        }
        reader.endArray()
        return map
    }

    @Throws(IOException::class)
    private fun readObject(reader: JsonReader): Pair<String, String> {
        var key = ""
        var label = ""
        reader.beginObject()
        while (reader.hasNext()) {
            val name = reader.nextName()
            when (name) {
                KEY_NAME -> key = reader.nextString()
                LABEL_NAME -> label = reader.nextString()
                else -> reader.skipValue()
            }
       }
       reader.endObject();
       return Pair(key, label)
    }
}