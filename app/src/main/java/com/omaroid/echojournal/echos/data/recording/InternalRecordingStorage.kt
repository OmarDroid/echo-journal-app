package com.omaroid.echojournal.echos.data.recording

import android.content.Context
import com.omaroid.echojournal.echos.domain.recording.RecordingStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.time.Instant
import java.time.temporal.ChronoUnit

class InternalRecordingStorage(
    private val context: Context,
) : RecordingStorage {
    override suspend fun savePersistently(tempFilePath: String): String? {
        val tempFile = File(tempFilePath)
        if (!tempFile.exists()) {
            Timber.e("The temporary file exists")
            return null
        }
        return withContext(Dispatchers.IO) {
            try {
                val savedFile = generateSavedFile()
                tempFile.copyTo(savedFile)
                savedFile.absolutePath
            } catch (e: IOException) {
                Timber.e(e.message)
                null
            } finally {
                withContext(NonCancellable) {
                    cleanUpTemporaryFiles()
                }
            }
        }

    }

    override suspend fun cleanUpTemporaryFiles() {
        withContext(Dispatchers.IO) {
            context.cacheDir.listFiles()?.filter { file ->
                file.name.startsWith(RecordingStorage.TEMP_FILE_PREFIX)
            }?.forEach { file ->
                file.delete()
            }
        }
    }

    private fun generateSavedFile(): File {
        val timeStamp = Instant.now().truncatedTo(ChronoUnit.SECONDS).toString()
        return File(
            context.filesDir,
            "${RecordingStorage.PERSISTENT_FILE_PREFIX}_$timeStamp.${RecordingStorage.RECORDING_FILE_EXTENSION}"
        )
    }
}