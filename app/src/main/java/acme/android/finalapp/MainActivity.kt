package acme.android.finalapp

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BlankFragment.OnFragmentInteractionListener, AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
           if(!showfmenu) showFM()
             else hideFM()
        }

        showFrag(0) //show the "home fragment"
        iniFM() //setup floating button menu
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }



    private fun showFrag(op: Int){

        var f : Fragment = HomeFragment.newInstance()
        when(op){
            0,2 -> f = BlankFragment.newInstance("", "") //replace with real fragments
            1 -> f = HomeFragment.newInstance()
        }


        supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slidein_up,
            R.anim.slideout_down).replace(R.id.fragmentFrame,f).commit()

    }
    private var showfmenu: Boolean = false


    private fun log(msg:String){
        Log.d("Movie APP: ", msg)
    }

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

}
