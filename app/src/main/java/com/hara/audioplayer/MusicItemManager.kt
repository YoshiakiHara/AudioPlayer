package com.hara.audioplayer

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import android.util.Log
import java.util.*


class MusicItemManager(){

    companion object {
        private const val TAG = "MusicItemManager"

        /**
         * 外部ストレージ上から音楽を探してリストを返す。
         * @param context コンテキスト
         * @return 見つかった音楽のリスト
         */
        fun getItems(context: Context): MutableList<MusicItem> {
            val items: MutableList<MusicItem> = LinkedList<MusicItem>()

            // ContentResolver を取得
            val cr: ContentResolver = context.getContentResolver()

            // 外部ストレージから音楽を検索
            val cur: Cursor? = cr.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,
                MediaStore.Audio.Media.IS_MUSIC + " = 1",
                null,
                null
            )
            if (cur != null) {
                if (cur.moveToFirst()) {
                    Log.i(TAG, "Listing...")

                    // 曲情報のカラムを取得
                    val artistColumn: Int = cur.getColumnIndex(MediaStore.Audio.Media.ARTIST)
                    val titleColumn: Int = cur.getColumnIndex(MediaStore.Audio.Media.TITLE)
                    val albumColumn: Int = cur.getColumnIndex(MediaStore.Audio.Media.ALBUM)
                    val durationColumn: Int = cur.getColumnIndex(MediaStore.Audio.Media.DURATION)
                    val idColumn: Int = cur.getColumnIndex(MediaStore.Audio.Media._ID)
                    val idTruck: Int = cur.getColumnIndex(MediaStore.Audio.Media.TRACK)
                    val dataColmun: Int = cur.getColumnIndex(MediaStore.Audio.AudioColumns.DATA)
                    Log.i(TAG, "Title column index: $titleColumn")
                    Log.i(TAG, "ID column index: $titleColumn")

                    // リストに追加
                    do {
//                        Log.i(
//                            TAG,
//                            "ID: " + cur.getString(idColumn)
//                                .toString() + " Title: " + cur.getString(titleColumn)
//                        )
                        items.add(
                            MusicItem(
                                cur.getLong(idColumn),
                                cur.getString(artistColumn),
                                cur.getString(titleColumn),
                                cur.getString(albumColumn),
                                cur.getInt(idTruck),
                                cur.getLong(durationColumn),
                                cur.getString(dataColmun)
                            )
                        )
                    } while (cur.moveToNext())
                    Log.i(TAG, "Done querying media. MusicRetriever is ready.")
                }
                // カーソルを閉じる
                cur.close()
            }

            // 見つかる順番はソートされていないため、アルバム単位でソートする
            Collections.sort(items)
            return items
        }
    }
}