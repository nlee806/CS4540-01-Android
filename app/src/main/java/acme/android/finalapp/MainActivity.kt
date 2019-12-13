package acme.android.finalapp

import acme.android.finalapp.helper.FragmentListener
import acme.android.finalapp.ui.*
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentListener, AppCompatActivity() {
    private var showfmenu: Boolean = false
    private var showSecret: Boolean = false

    val c = -133f;  //animation constant.

    private fun log(msg:String){   Log.d("Movie APP: ", msg) }

    /*
    0 http://www.omdbapi.com/
        parse the returned json. format return 'movie' datatype -
        http://www.omdbapi.com/?i=tt3896198&apikey=dd906fe0

     */

    private fun showFrag(op: Int){

        //add new fragment calls here.
        var f : Fragment = HomeFragment.newInstance()
        when(op){
            0 -> f = DisplayFragment.newInstance(getString(R.string.testjson))
//            0 -> f = SplashFragment.newInstance()
            1 -> f = HomeFragment.newInstance()
            2 -> f = SplashFragment.newInstance()
            }


        supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slidein_up,
            R.anim.slideout_down).replace(R.id.fragmentFrame,f).commit()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_NoActionBar)  //reset the base theme. remove the slash screen logo.
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {  
           toggleFM()
        }
        fab.setOnLongClickListener{
            if(!showSecret) showSecret()
            else hideSecret()
            return@setOnLongClickListener true
        }

        showFrag(1) //show the "home fragment"
        iniFM() //setup floating button menu
    }
    /* ---------- Floating button Menu ------------- */


    private fun toggleFM(){
        if(!showfmenu) showFM()
        else hideFM()
    }
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

    private fun showSecret(){
        showSecret = true
        fab3.animate().translationY(c*3)
        if(!showfmenu) showFM()     //clean hide effect.

    }
    private fun hideSecret(){
        showSecret = false
        fab3.animate().translationY(0f)
    }
    private fun showFM() {
        showfmenu = true

        fab1.animate().translationY(c)
        fab2.animate().translationY(c*2)
        //fab3.animate().translationY(c*3)


    }

    private fun hideFM() {
        showfmenu = false
        fab1.animate().translationY(0f)
        fab2.animate().translationY(0f)
        //fab3.animate().translationY(0f)
        if(showSecret) hideSecret() //
            }

    /* ---------- Floating button Menu ------------- */



    /* ---------- Fragment callbacks ------------- */

    override fun showInfo(id: String){

        var f = InfoFragment.newInstance(id)

        supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slidein_up,R.anim.slideout_down,
            R.anim.slidein_up, R.anim.slideout_down).replace(R.id.fragmentFrame,f).addToBackStack(null).commit()

    }

    override fun showResult(frame: Int, info: String){
        //input full json response. just process and display. ie display fragment
        val ff = intArrayOf ( R.id.subFrame, R.id.subFrame1, R.id.subFrame2, R.id.fragmentFrame)
        var f = DisplayFragment.newInstance(info)
        //this would be a good place for custom animations.
        //there will be multiples of these so no backstack
        if(frame > 2)  supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slidein_up,R.anim.slideout_down,
            R.anim.slidein_up, R.anim.slideout_down).replace(ff[frame],f).addToBackStack(null).commit()
        else
        supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slidein_up,
            R.anim.slideout_down).replace(ff[frame],f).commit()
        //don't add to back stack for sub frame top 3

    }


}
