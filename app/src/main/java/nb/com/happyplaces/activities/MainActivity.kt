package nb.com.happyplaces.activities


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import nb.com.happyplaces.R
import nb.com.happyplaces.adapters.HappyPlacesAdapter
import nb.com.happyplaces.database.DatabaseHandler
import nb.com.happyplaces.models.HappyPlaceModel

class MainActivity : AppCompatActivity() {
    private lateinit var fabAddHappyPlace: FloatingActionButton

// TODO(Step 1 : Create a different packages for every activities and classes so on used in the app for better understanding and in a proper structure. Move the existing activities one by one in the activities package.)

    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)

        // This is used to align the xml view to this class
        setContentView(R.layout.activity_main)
        fabAddHappyPlace = findViewById(R.id.fabAddHappyPlace)

        // Setting an click event for Fab Button and calling the AddHappyPlaceActivity.
        fabAddHappyPlace.setOnClickListener {
            val intent = Intent(this, AddHappyPlaceActivity::class.java)
            startActivity(intent)
        }
        getHappyPlacesListFromLocalDB()
    }
    private fun setupHappyPlaceRecyclerView(happyPlaceList:ArrayList<HappyPlaceModel>){
        rv_happy_places_list.layoutManager = LinearLayoutManager(this)
        rv_happy_places_list.setHasFixedSize(true)
        val placesAdapter = HappyPlacesAdapter(this, happyPlaceList)
        rv_happy_places_list.adapter = placesAdapter
    }

    private fun getHappyPlacesListFromLocalDB(){
        val dbHandler = DatabaseHandler(this)
        val getHappyPlaceList: ArrayList<HappyPlaceModel> = dbHandler.getHappyPlacesList()

        if (getHappyPlaceList.size>0){
            for (i in getHappyPlaceList){
                Log.e("Title", i.title)
            }
        }
    }

}