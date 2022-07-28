package com.example.jointnotes;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "SaveController", value = "/save")
public class SaveController extends HttpServlet {

    Map<String, String> privateLinks;
    Map<String, String> publicLinks;

    @Override
    public void init() {
        privateLinks = new HashMap<>();
        publicLinks = new HashMap<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Note note = createNote(request);
        String noteUrl = getNoteUrl(note);
        saveNote(note, noteUrl, request);
        request.getSession().setAttribute("pvurls", privateLinks);
        getServletContext().setAttribute("pburls", publicLinks);
        request.setAttribute("noteUrl", noteUrl);
        request.getRequestDispatcher("/WEB-INF/confirmation.jsp").forward(request, response);
    }

    private String getNoteUrl(Note note) {
        return NoteUtils.buildNoteUrl(note);
    }

    private void saveNote(Note note, String noteUrl, HttpServletRequest request) {
        if (note.getType().equals(Note.TYPE_PRIVATE)) {
            privateLinks.put(note.getId(), noteUrl);
            request.getSession().setAttribute(note.getId(), note);
        } else if (note.getType().equals(Note.TYPE_PUBLIC)) {
            publicLinks.put(note.getId(), noteUrl);
            getServletContext().setAttribute(note.getId(), note);
        }
    }

    private Note createNote(HttpServletRequest request) {
        String noteId = request.getParameter("noteId");
        String noteContent = request.getParameter("noteContent");
        String noteType = request.getParameter("noteType");
        return new Note(noteId, noteContent, noteType);
    }
}
