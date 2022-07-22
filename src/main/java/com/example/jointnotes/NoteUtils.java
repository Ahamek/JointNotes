package com.example.jointnotes;

class NoteUtils {
    static final String PRIVATE_PATH = "private";
    static final String PUBLIC_PATH = "public";

    static String buildNoteUrl(Note note) {
        String format = "%s?id=%s";

        if (note.getType().equals(Note.TYPE_PRIVATE))
            return format.formatted(PRIVATE_PATH, note.getId());
        else if (note.getType().equals(Note.TYPE_PUBLIC))
            return format.formatted(PUBLIC_PATH, note.getId());
        else
            throw new IllegalArgumentException("Invalid note type " + note.getType());
    }
}