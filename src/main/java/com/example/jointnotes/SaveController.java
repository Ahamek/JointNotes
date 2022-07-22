package com.example.jointnotes;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SaveController", value = "/save")
public class SaveController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Note note = createNote(request);
        saveNote(note, request);
        String noteUrl = NoteUtils.buildNoteUrl(note);
        request.setAttribute("noteUrl", noteUrl);
        request.getRequestDispatcher("/confirmation.jsp").forward(request, response);
    }

    private void saveNote(Note note, HttpServletRequest request) {
        if (note.getType().equals(Note.TYPE_PRIVATE)) {
            request.getSession().setAttribute(note.getId(), note);
        } else if (note.getType().equals(Note.TYPE_PUBLIC)) {
            ServletContext servletContext = getServletContext();
            servletContext.setAttribute(note.getId(), note);
        }
    }

    private Note createNote(HttpServletRequest request) {
        String noteId = request.getParameter("noteId");
        String noteContent = request.getParameter("noteContent");
        String noteType = request.getParameter("noteType");
        return new Note(noteId, noteContent, noteType);
    }
}
