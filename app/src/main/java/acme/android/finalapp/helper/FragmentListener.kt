package acme.android.finalapp.helper

/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 *
 *
 * See the Android Training lesson [Communicating with Other Fragments]
 * (http://developer.android.com/training/basics/fragments/communicating.html)
 * for more information.
 */
interface FragmentListener {
    //notifiy the main activy of user input/send data back as arguments.
    fun returnSearchedInfo(id: String)
    fun showInfo(id: String)
    fun showResult(frame: Int, info: String)

}