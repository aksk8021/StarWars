package com.example.testprojects.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testprojects.data.Match
import com.example.testprojects.data.Player
import com.example.testprojects.databinding.ItemMatchBinding

/**
 * RecyclerView Adapter that displays all matches played by a selected player.
 * It shows opponent's name, icon, score, result (WIN/LOSS/DRAW), and highlights accordingly.
 */
class MatchAdapter(
    private val matches: List<Match>,
    private val players: List<Player>,
    private val selectedPlayerId: Int
) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    inner class MatchViewHolder(val binding: ItemMatchBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = ItemMatchBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return MatchViewHolder(binding)
    }

    override fun getItemCount() = matches.size

    /**
     * Binds match data to the view: opponent info, score, result label and background color.
     */
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matches[position]

        val isPlayer1 = match.player1Id == selectedPlayerId
        val opponentId: Int
        val playerScore: Int
        val opponentScore: Int

        if (isPlayer1) {
            opponentId = match.player2Id
            playerScore = match.player1Score
            opponentScore = match.player2Score
        } else {
            opponentId = match.player1Id
            playerScore = match.player2Score
            opponentScore = match.player1Score
        }

        val opponent = players.find { it.id == opponentId }
        val opponentName = opponent?.name ?: "Unknown"
        val opponentIcon = opponent?.icon

        holder.binding.txtOpponent.text = "Opponent: $opponentName"
        holder.binding.txtScore.text = "Score: $playerScore - $opponentScore"

        opponentIcon?.let {
            Glide.with(holder.binding.imgOpponent.context)
                .load(it)
                .into(holder.binding.imgOpponent)
        }

        val resultText: String
        val resultColor: Int
        val cardColor: Int

        when {
            playerScore > opponentScore -> {
                resultText = "WIN"
                resultColor = Color.parseColor("#2E7D32")
                cardColor = Color.parseColor("#A5D6A7")
            }
            playerScore < opponentScore -> {
                resultText = "LOSS"
                resultColor = Color.parseColor("#C62828")
                cardColor = Color.parseColor("#EF9A9A")
            }
            else -> {
                resultText = "DRAW"
                resultColor = Color.DKGRAY
                cardColor = Color.WHITE
            }
        }

        holder.binding.txtResult.apply {
            text = resultText
            setTextColor(resultColor)
            alpha = 0f
            animate().alpha(1f).setDuration(400).start()
        }

        holder.binding.cardMatch.setCardBackgroundColor(cardColor)
    }
}