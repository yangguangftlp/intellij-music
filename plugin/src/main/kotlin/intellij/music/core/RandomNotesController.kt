package intellij.music.core

import intellij.music.ui.MusicKeyboardEvent
import java.util.*
import java.util.concurrent.TimeUnit

class RandomNotesController(midiBackend: MidiBackend) {
    private val notesPlayer = MidiNotesPlayer(midiBackend)
    private val circleSequencer = CircleOfFifthsSequencer()

    private var lastNoteTime = Date()

    fun keyboardPressed(event: MusicKeyboardEvent) {
        val diff = TimeUnit.MILLISECONDS.toMillis(Date().time - lastNoteTime.time)
        if(diff > 130) {
            notesPlayer.playNote(circleSequencer.nextNote(), BASE_NOTE_VELOCITY)
            lastNoteTime = Date()
        }
    }

    companion object {
        const val BASE_NOTE_VELOCITY: Int = 200
    }
}