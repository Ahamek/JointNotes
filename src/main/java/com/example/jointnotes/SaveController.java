package com.example.jointnotes;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SaveController", value = "/save")
public class SaveController extends HttpServlet {

    Map<String, String> urls = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Note note = createNote(request);
        saveNote(note, request);
        String noteUrl = getNoteUrl(note);
        request.getSession().setAttribute("urls", urls);
        request.setAttribute("noteUrl", noteUrl);
        request.getRequestDispatcher("/WEB-INF/confirmation.jsp").forward(request, response);
    }

    private String getNoteUrl(Note note) {
        String noteUrl = NoteUtils.buildNoteUrl(note);
        urls.put(note.getId(), noteUrl);
        return noteUrl;
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
