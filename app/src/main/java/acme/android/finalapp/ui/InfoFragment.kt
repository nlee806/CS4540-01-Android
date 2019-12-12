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
import kotlinx.android.synthetic.main.fragment_info.*
import java.net.URL
import java.util.concurrent.Executors

class InfoFragment : Fragment() {
    private var listener: FragmentListener? = null
    private var id : String? = null
    private val idkey = "ID"

    fun loadInfo(){
        var queryurl = getString(R.string.apiidurl)
        queryurl += id
        var json = ""
        Executors.newSingleThreadExecutor().execute {
            json = URL(queryurl).readText()
            iinfo?.post{
                iinfo?.text = json
                log(json)
            }
        }
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
    fun log(msg: String){ Log.d("Info Frag: ", msg)}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v =  inflater.inflate(R.layout.fragment_info, container, false)

        loadInfo()
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
            InfoFragment().apply {
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
