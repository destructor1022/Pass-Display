package com.salty.passdisplay

import android.util.Log
import android.widget.RemoteViews
import com.google.android.clockwork.tiles.TileData

//import com.google.android.clockwork.tiles.TileData$Builder
import com.google.android.clockwork.tiles.TileProviderService
import kotlin.coroutines.*


class MyTileProviderService : TileProviderService() {

    private var id: Int = -1
    private var updateJob: Job? = null

    override fun onTileUpdate(tileId: Int) {
        Log.d(TAG, "onTileUpdate() called with: tileId = [$tileId]")

        if (!isIdForDummyData(tileId)) {
            tileId = id
            sendRemoteViews()
        }
    }

    override fun onTileFocus(tileId: Int) {
        Log.d(TAG, "onTileFocus() called with: tileId = [$tileId]")

        id = tileId

        sendRemoteViews();

        /*

        updateJob?.cancel()
        updateJob = CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                sendRemoteViews()
                delay(1000)
            }
        }

         */
    }

    override fun onTileBlur(tileId: Int) {
        Log.d(TAG, "onTileBlur() called with: tileId = [$tileId]")

        //updateJob?.cancel()
    }

    private fun sendRemoteViews() {
        Log.d(TAG, "sendRemoteViews")

        val remoteViews = RemoteViews(applicationContext.packageName, R.layout.activity_main)
        // *** Update your tile UI here


        val bob = TileData.Builder()
                .setRemoteViews(remoteViews)
        sendData(id, bob.build())
    }

    companion object {
        private const val TAG = "MyTileProviderService"
    }
}