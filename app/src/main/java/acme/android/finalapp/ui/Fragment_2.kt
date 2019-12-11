package acme.android.finalapp.ui

import acme.android.finalapp.R
import acme.android.finalapp.helper.FragmentListener
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import java.net.URL
import java.util.concurrent.Executors

class Fragment_2 : Fragment() {
    private var listener: FragmentListener? = null


    //fragment_2 show last user search, or a random title.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    fun log(msg: String){ Log.d("Movies: ", msg)}

    fun loadMovie(){
        Executors.newSingleThreadExecutor().execute({
            val testurl = "http://www.omdbapi.com/?i=tt3896198&apikey=dd906fe0&r=json"
            val queryurl = "http://www.omdbapi.com/?apikey=dd906fe0&s="
            val test = queryurl + "Pulp"
            val json = URL(test ).readText()
            subtext.post { subtext.text = json
            processData(json)}
        })



    }

    fun processData(data: String){
       // logging (data.toString())
        var p = data.indexOf("Poster", 0, false)
        var temp = data.substring(p)
        var c = temp.indexOf(',', 0)
        val imgurl = temp.substring(9, c-1)
        subtext?.text = imgurl
        poster?.loadUrl(  imgurl )
        p = data.indexOf("Title", 0, false)
        temp = data.substring(p)
        c = temp.indexOf(',', 0)
        title?.text = temp.substring(8, c-1)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment_2
        var v =  inflater.inflate(R.layout.fragment_2, container, false)

        loadMovie()

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
            Fragment_2().apply {
            }
    }
}
