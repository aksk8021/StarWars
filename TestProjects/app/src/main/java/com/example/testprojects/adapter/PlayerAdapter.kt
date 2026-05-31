package com.example.testprojects.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testprojects.databinding.ItemPlayerBinding
import com.example.testprojects.data.Player

/**
 * Adapter for displaying a list of players in the points table.
 * Each item shows the player's avatar, name, and points.
 * Clicking a player triggers a callback to navigate to their matches.
 *
 * @param players The list of Player objects to display.
 * @param onClick Lambda function called when a player item is clicked.
 */
class PlayerAdapter(
    private val players: List<Player>,
    private val onClick: (Player) -> Unit
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    inner class PlayerViewHolder(val binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun getItemCount(): Int = players.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]

        holder.binding.txtName.text = player.name
        holder.binding.txtPoints.text = player.points.toString()

        Glide.with(holder.binding.imgPlayer.context)
            .load(player.icon)
            .into(holder.binding.imgPlayer)

        holder.itemView.setOnClickListener {
            onClick(player)
        }
    }
}