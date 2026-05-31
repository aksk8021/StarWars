package com.example.testprojects.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testprojects.adapter.MatchAdapter
import com.example.testprojects.data.Player
import com.example.testprojects.databinding.ActivityMatchesBinding
import com.example.testprojects.utils.CommonUtils
import com.example.testprojects.viewmodel.PlayerViewModel

/**
 * MatchesActivity displays all matches played by a specific player.
 * It shows opponent, score, result, and match date using MatchAdapter.
 * The player is identified via an Intent extra passed from MainActivity.
 */
class MatchesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMatchesBinding
    private lateinit var playerViewModel: PlayerViewModel
    private var playerId: Int = -1
    private var currentPlayer: Player? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CommonUtils.setUpEdgeToEdge(this)

        binding = ActivityMatchesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playerId = intent.getIntExtra("playerId", -1)

        playerViewModel = ViewModelProvider(this)[PlayerViewModel::class.java]

        playerViewModel.players.observe(this) { players ->
            currentPlayer = players.find { it.id == playerId }
            val matches = playerViewModel.getMatchesForPlayer(playerId)
            binding.recyclerViewMatches.layoutManager = LinearLayoutManager(this)
            binding.recyclerViewMatches.adapter = MatchAdapter(matches, players, playerId)
            binding.textTitle.text = "${currentPlayer?.name}'s Matches"
        }
    }
}
