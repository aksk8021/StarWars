package com.example.testprojects.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testprojects.adapter.PlayerAdapter
import com.example.testprojects.viewmodel.PlayerViewModel
import com.example.testprojects.databinding.ActivityMainBinding
import com.example.testprojects.utils.CommonUtils

/**
 * StarWarsMainActivity displays the points table screen.
 * It loads player data using PlayerViewModel, sorts by points,
 * and allows navigation to the Matches screen on item click.
 */
class StarWarsMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val playerViewModel: PlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CommonUtils.setUpEdgeToEdge(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        playerViewModel.players.observe(this, Observer { players ->
            binding.recyclerView.adapter = PlayerAdapter(players) { selectedPlayer ->
                val intent = Intent(this, MatchesActivity::class.java)
                intent.putExtra("playerId", selectedPlayer.id)
                startActivity(intent)
            }
        })

    }
}
