package ru.amlet.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.amlet.entity.Comment;
import ru.amlet.service.CommentService;
import ru.amlet.service.TransformerService;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class CommentController {

    private final CommentService commentService;
    private final TransformerService transformerService;

    public CommentController(CommentService commentService, TransformerService transformerService) {
        this.commentService = commentService;
        this.transformerService = transformerService;
    }

    @ShellMethod(value = "Create new comment", key = {"cnc", "createNewComment"})
    public String createComment(String content, long bookId) {
        Comment comment = commentService.create(content, bookId);
        return transformerService.commentTransform(comment);
    }

    @ShellMethod(value = "Find comment by id", key = {"fci", "findCommentById"})
    public String findById(String id) {
        Optional<Comment> comment = commentService.findById(id);
        return comment.map(transformerService::commentTransform)
                .orElse(String.format("Comment id: %s not found.", id));
    }

    @ShellMethod(value = "Find comment by content", key = {"fcc", "findCommentByContent"})
    public String findByContent(String content) {
        List<Comment> comments = commentService.findByContent(content);
        return transformerService.commentsTransform(comments);
    }

    @ShellMethod(value = "Update comment", key = {"uc", "updateComment"})
    public void updateComment(String id, String content, long bookId) {
        commentService.update(id, content, bookId);
    }

    @ShellMethod(value = "Delete comment", key = {"dc", "deleteComment"})
    public void deleteComment(String id) {
        commentService.deleteById(id);
    }

    @ShellMethod(value = "Count comments", key = {"ccs", "countComments"})
    public long count() {
        return commentService.count();
    }
}
