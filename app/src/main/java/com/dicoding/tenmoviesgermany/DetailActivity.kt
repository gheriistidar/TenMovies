package com.dicoding.tenmoviesgermany

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.TenMoviesGermany.R

class DetailActivity : AppCompatActivity() {

    companion object {
        const val KEY_MOVIE = "key_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataMovie = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Movie>(KEY_MOVIE, Movie::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Movie>(KEY_MOVIE)
        }

        val tvDetailName: TextView = findViewById(R.id.tv_item_name)
        val tvDetailYear: TextView = findViewById(R.id.tv_year_value)
        val tvDetailDescription: TextView = findViewById(R.id.tv_item_description)
        val ivDetailPhoto: ImageView  = findViewById(R.id.img_item_photo)

        tvDetailName.text = dataMovie?.name
        tvDetailYear.text = dataMovie?.year?.toString()
        tvDetailDescription.text = dataMovie?.description
        ivDetailPhoto.setImageResource(dataMovie?.photo ?: R.drawable.ic_launcher_background)

        val shareButton: Button = findViewById(R.id.share_button)
        shareButton.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, dataMovie?.description)
            }
            startActivity(Intent.createChooser(sendIntent, "Teks dibagikan:"))
        }
    }
}