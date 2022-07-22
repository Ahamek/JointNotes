package com.example.jointnotes;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "NoteController", value =  {"/private", "/public"})
public class NoteController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        findNote(request).ifPresent(note -> request.setAttribute("note", note));
        request.getRequestDispatcher("/note.jsp").forward(request, response);
    }

    private Optional<Note> findNote(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        String noteId = request.getParameter("id");
        Note note = null;
        if (servletPath.contains(NoteUtils.PRIVATE_PATH)) {
            note = (Note) request.getSession().getAttribute(noteId);
        } else if(servletPath.contains(NoteUtils.PUBLIC_PATH)) {
            note = (Note) getServletContext().getAttribute(noteId);
        }
        return Optional.ofNullable(note);
    }
}
