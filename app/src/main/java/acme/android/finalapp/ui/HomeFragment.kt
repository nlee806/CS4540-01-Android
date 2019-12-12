package acme.android.finalapp.ui

import acme.android.finalapp.R
import acme.android.finalapp.helper.FragmentListener
import acme.android.finalapp.helper.OmdbHelpber
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
import kotlinx.android.synthetic.main.fragment_home.*
import java.net.URL
import java.util.concurrent.Executors

class HomeFragment : Fragment() {
    private var listener: FragmentListener? = null


    //home fragment show last user search, or a random title.

    fun loadMovie(q :String){

        val testurl = getString(R.string.testapiurl)
        val queryurl = getString(R.string.apiqueryurl)
        var test = queryurl + q
        if(q.length == 0) test = testurl    //default to a known movie.
        title?.text = test
        Executors.newSingleThreadExecutor().execute{
            val json = URL(test ).readText()
            subtext.post { subtext.text = q
            processData(json)}
        }



    }
    //todo replace with full json parser.
    fun processData(data: String){

        if(data.substring(0, 10).contentEquals("{\"Search\":")){
            //serarch result with multiple titles probably.
            var sr : List<String> = data.split("{\"Title")
            log(sr.toString())
           // log(sr[1])
            var fix = "{\"Title" + sr[1]
            log(fix)
            processData(fix)
        }else {


            var result: HashMap<String, String> = OmdbHelpber.getMap(data)

            //print raw/ processed
            log(data)
            log("res: " + result.toString())

            val tt: String? = result.get("Title")
            title?.text = tt
            subtext?.text = result.get("Plot")

            val img = result.get("Poster")
//        log(img.toString())
            poster?.loadUrl(img)

        }
    }


    /*
    ---------------------------------------------------------------------------------------
                required fragment stuff
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    fun log(msg: String){ Log.d("Movies: ", msg)}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v =  inflater.inflate(R.layout.fragment_home, container, false)

//        https://stackoverflow.com/questions/47298935/handling-enter-key-on-edittext-kotlin-android
//        make text box act like a search bar, no need for system search.
        //maybe hide/show with action bar icon.
        val search:EditText = v.findViewById(R.id.searchText)
        search.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                loadMovie(search.text.toString())
                return@OnKeyListener true
            }
            false
        })

        loadMovie("") //load a default page


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
            HomeFragment().apply {
            }
    }

    /*
    ---------------------------------------------------------------------------------------
                required fragment stuff
     */

}
