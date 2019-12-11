package acme.android.finalapp.helper

object OmdbHelpber{




    fun getMap(data : String) : HashMap<String, String>{
        var map : HashMap<String, String> = HashMap()
        var e = data?.length
        val dat = data?.substring(2)

            val pairs : List<String> = dat!!.split("\",\"")
            for( i in 0..pairs.size-1) {
                val temp: List<String> = pairs[i].split("\":\"")    //split on ":" sequence.
                val key = temp[0]
                var st = ""
                    if(temp.size > 1)  st = temp[1]
                map.put(key, st)
            }


        return map
    }
}