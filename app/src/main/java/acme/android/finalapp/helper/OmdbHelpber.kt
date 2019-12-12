package acme.android.finalapp.helper

import acme.android.finalapp.R
import acme.android.finalapp.ui.HomeFragment
import android.app.Activity
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.net.URL
import java.util.concurrent.Executors

object OmdbHelpber{

    //query key q, then notify fragment
    fun query(q :String, activity: Activity, p: TextView?, f: Fragment): String {

        val testurl = activity.getString(R.string.testapiurl)
        val queryurl = activity.getString(R.string.apiqueryurl)
        var test = queryurl + q
        if (q.length == 0) test = testurl    //default to a known movie.

        var json = ""
        Executors.newSingleThreadExecutor().execute {
            json = URL(test).readText()
            p?.post{
                f as HomeFragment
            f.processData(json)
            }
        }
        return json
    }




    //process omdb json response, return hashmap<property, value>
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