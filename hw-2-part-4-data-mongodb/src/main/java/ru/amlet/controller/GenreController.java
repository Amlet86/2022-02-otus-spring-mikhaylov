package ru.amlet.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.amlet.entity.Genre;
import ru.amlet.service.GenreService;
import ru.amlet.service.TransformerService;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class GenreController {

    private final GenreService genreService;
    private final TransformerService transformerService;

    public GenreController(GenreService genreService, TransformerService transformerService) {
        this.genreService = genreService;
        this.transformerService = transformerService;
    }

    @ShellMethod(value = "Create new genre", key = {"cng", "createNewGenre"})
    public String createGenre(String name) {
        Genre genre = genreService.create(name);
        return transformerService.genreTransform(genre);
    }

    @ShellMethod(value = "Find genre by id", key = {"fgi", "findGenreById"})
    public String findById(String id) {
        Optional<Genre> genre = genreService.findById(id);
        return genre.map(transformerService::genreTransform)
                .orElse(String.format("Genre id: %s not found.", id));
    }

    @ShellMethod(value = "Find genre by name", key = {"fgn", "findGenreByName"})
    public String findByName(String name) {
        List<Genre> genres = genreService.findByName(name);
        return transformerService.genresTransform(genres);
    }

    @ShellMethod(value = "Find all genres", key = {"fag", "findAllGenres"})
    public String findAll() {
        List<Genre> genres = genreService.findAll();
        return transformerService.genresTransform(genres);
    }

    @ShellMethod(value = "Update genre", key = {"ug", "updateGenre"})
    public void updateGenre(String id, String name) {
        genreService.update(id, name);
    }

    @ShellMethod(value = "Delete genre", key = {"dg", "deleteGenre"})
    public void deleteGenre(String id) {
        genreService.deleteById(id);
    }

    @ShellMethod(value = "Count genres", key = {"cgs", "countGenres"})
    public long count() {
        return genreService.count();
    }
}
