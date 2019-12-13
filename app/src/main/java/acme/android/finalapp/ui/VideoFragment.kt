package acme.android.finalapp.ui

import acme.android.finalapp.R
import acme.android.finalapp.helper.FragmentListener
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_home.*
import java.net.URL
import java.util.concurrent.Executors
import android.os.Parcel;
import android.os.Parcelable;
import java.io.StringReader
import android.content.Intent
//import android.R
import android.net.Uri


//import kotlinx.serialization.json.JsonObject

class VideoFragment : Fragment() {
    private var listener: FragmentListener? = null

    /*

    ---------------------------------------------------------------------------------------
                required fragment stuff
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    fun log(msg: String){ Log.d("Movies: ", msg)}

    var img: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v =  inflater.inflate(R.layout.fragment_video, container, false)

        val apiKey = "" //must add API key here
        //From TheMovieDataBase
        val url = "http://api.themoviedb.org/3/movie/popular?api_key="+apiKey
            //"https://api.themoviedb.org/3/movie/76341?api_key="+apiKey
        Log.d("URL", url)
        val result = URL(url).readText()
        //val json = p.parse(StringReader(result)) as JsonObject

        Log.d("Result", result)
 //       val parsedJson = JSONObject(result)
 //       jsonString = resultJsonSlurper slurper = new JsonSlurper()
  //      Map parsedJson = slurper.parseText(jsonString)
        val urls = mutableListOf("")
 //       for(a in parsedJson.results_){
  //          urls += a
    //    }

        //from Trasplazio Garzuglio
        val video_path = "http://www.youtube.com/watch?v=9rLZYyMbJic"//opZ69P-0Jbc"
        var uri = Uri.parse(video_path)

// With this line the Youtube application, if installed, will launch immediately.
// Without it you will be prompted with a list of the application to choose.
        uri = Uri.parse("vnd.youtube:" + uri.getQueryParameter("v"))

        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)

        img = v.findViewById(R.id.splash)
        return v
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement FragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            VideoFragment().apply {
            }
    }

    /*
    ---------------------------------------------------------------------------------------
                required fragment stuff
     */

    override fun onPause(){
        super.onPause()

    }

}
