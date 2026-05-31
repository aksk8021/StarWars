package com.example.testprojects.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testprojects.data.Match
import com.example.testprojects.data.Player
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

/**
 * PlayerViewModel responsible for loading player and match data from assets,
 * calculating player points, and exposing sorted player data via LiveData.
 */
class PlayerViewModel(application: Application) : AndroidViewModel(application) {

    private val _players = MutableLiveData<List<Player>>()
    val players: LiveData<List<Player>> = _players

    private lateinit var matchList: List<Match>

    init {
        loadDataFromAssets()
    }

    /**
     * Loads players and matches from assets, calculates points based on match results,
     * and posts the sorted list of players to LiveData.
     */
    private fun loadDataFromAssets() {
        val assetManager = getApplication<Application>().assets

        val playerStream = assetManager.open("StarWarsPlayers.json")
        val playerReader = InputStreamReader(playerStream)
        val playerType = object : TypeToken<List<Player>>() {}.type
        val players = Gson().fromJson<List<Player>>(playerReader, playerType).toMutableList()

        val matchStream = assetManager.open("StarWarsMatches.json")
        val matchReader = InputStreamReader(matchStream)
        val matchType = object : TypeToken<List<Match>>() {}.type
        matchList = Gson().fromJson(matchReader, matchType)

        val playerMap = players.associateBy { it.id }.toMutableMap()

        for (match in matchList) {
            val p1 = playerMap[match.player1Id]
            val p2 = playerMap[match.player2Id]

            when {
                match.player1Score > match.player2Score -> {
                    p1?.points = (p1?.points ?: 0) + 3
                }
                match.player2Score > match.player1Score -> {
                    p2?.points = (p2?.points ?: 0) + 3
                }
                else -> {
                    p1?.points = (p1?.points ?: 0) + 1
                    p2?.points = (p2?.points ?: 0) + 1
                }
            }
        }

        val sorted =
            playerMap.values.sortedWith(compareByDescending<Player> { it.points }.
            thenBy { it.name })
        _players.postValue(sorted)
    }

    /**
     * Returns the list of matches where the given player participated.
     */
    fun getMatchesForPlayer(playerId: Int): List<Match> {
        return matchList.filter { it.player1Id == playerId || it.player2Id == playerId }
    }
}