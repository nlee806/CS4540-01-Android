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
import android.webkit.WebView
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_display.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.title
import kotlinx.android.synthetic.main.fragment_info.*
import java.net.URL
import java.util.concurrent.Executors


/*
    Dispaly fragment expects a omdb response JSON as string input to new instance factory method
    display poster, title, year...only basics on multi response search.
 */
class DisplayFragment : Fragment() {
    private var listener: FragmentListener? = null
    private var id : String? = null
    private val idkey = "Info"
    private var dbid : String? = null
    fun loadDisplay(v :View){
        log("Display fragment initializing.")
        log("info: "+ id)
        val info = OmdbHelpber.getMap(id!!)
        log("Title: " + info.get("Title"))

        val p = v.findViewById<WebView>(R.id.dposter)   //this should be needed? but it is..!
        p?.loadUrl(info.get("Poster"))
        p?.settings?.loadWithOverviewMode = true
        p?.settings?.useWideViewPort = true


        val t = v.findViewById<TextView>(R.id.dtitle)
        t?.text = info.get("Title")
       // v.findViewById<TextView>(R.id.dtvid).text = info.get("imdbID")
        dbid = info.get("imdbID")
        t?.setOnClickListener{
             listener?.showResult(3, id!!) //show full page on click
        }
        t?.setOnLongClickListener{
            listener?.showInfo(dbid!!)      //show raw info on long click
            return@setOnLongClickListener true
        }

        v.findViewById<TextView>(R.id.dsubtext).text = info.get("Year")
    }
    /*
    ---------------------------------------------------------------------------------------
                required fragment stuff
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString(idkey)
        }

    }
    fun log(msg: String){ Log.d("Display Frag: ", msg)}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v =  inflater.inflate(R.layout.fragment_display, container, false)

        loadDisplay(v)
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
        fun newInstance(id: String) =
            DisplayFragment().apply {
            arguments = Bundle().apply {
                putString(idkey, id)
            }
        }
    }

    /*
    ---------------------------------------------------------------------------------------
                required fragment stuff
     */

}
