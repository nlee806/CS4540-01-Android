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

class VideoFragment : Fragment() {
    private var listener: FragmentListener? = null


    //splash/start up screen fragment. not needed.


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
        var v =  inflater.inflate(R.layout.fragment_splash, container, false)

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
