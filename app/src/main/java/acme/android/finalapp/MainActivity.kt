package acme.android.finalapp

import acme.android.finalapp.helper.FragmentListener
import acme.android.finalapp.ui.*
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentListener, AppCompatActivity() {
    private var showfmenu: Boolean = false


    private fun log(msg:String){
        Log.d("Movie APP: ", msg)
    }



    /*someone please come up with another. more the better.

    pick a fragment. add your name. commit. so we know everyone is online.!

    Fragments:
    0 http://www.omdbapi.com/
        parse the returned json. format return 'movie' datatype -
        http://www.omdbapi.com/?i=tt3896198&apikey=dd906fe0

    1 Home/Search fragment - Saul
    2 Favorites Fragment -
        -save imdb id (shared preference or external file) + download poster
    3 Browse (Abc/genre/year...?) -
            scraping imdb?

    4 watch trailers? new/popular
    5   another thing. idk...
     */

    private fun showFrag(op: Int){

        //add new fragment calls here.
        var f : Fragment = HomeFragment.newInstance()
        when(op){
            0 -> f = SplashFragment.newInstance()
            1 -> f = HomeFragment.newInstance()
            2 -> f = Fragment_2()
        }


        supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slidein_up,
            R.anim.slideout_down).replace(R.id.fragmentFrame,f).commit()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_NoActionBar)  //reset the base theme. remove the slash screen logo.
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            if(!showfmenu) showFM()
            else hideFM()
        }

        showFrag(0) //show the "home fragment"
        iniFM() //setup floating button menu
    }
    /* ---------- Floating button Menu ------------- */


    //main menu listener
    private fun iniFM(){
        fab1.setOnClickListener { showFrag(0) }
        fab1.setOnLongClickListener{
            log("Recent")
            return@setOnLongClickListener true
        }
        fab2.setOnClickListener { showFrag(1) }
        fab2.setOnLongClickListener{
            log("Categories")
            return@setOnLongClickListener true
        }
        fab3.setOnClickListener { showFrag(2) }
        fab3.setOnLongClickListener{
            log("Favorites")
            return@setOnLongClickListener true
        }
    }
    private fun showFM() {
        showfmenu = true

        val c = -133f;
        fab1.animate().translationY(c)
        fab2.animate().translationY(c*2)
        fab3.animate().translationY(c*3)

    }

    private fun hideFM() {
        showfmenu = false
        fab1.animate().translationY(0f)
        fab2.animate().translationY(0f)
        fab3.animate().translationY(0f)

    }

    /* ---------- Floating button Menu ------------- */



    /* ---------- Fragment callbacks ------------- */


    override fun returnSearchedInfo(id: String) {
        TODO("example call back.") //T
    }
}
