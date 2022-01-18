package br.com.erudio.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.BookVO;
import br.com.erudio.services.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "BookEndpoint")
@RestController
@RequestMapping("/api/book/v1")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "Find book by id")
    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public BookVO findById(@PathVariable("id") Long id) {

        BookVO bookVO = bookService.findByID(id);
        // adiciona auto relacionamento
        bookVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return bookVO;
    }

    // hateoas ta meio vago ainda..
    @ApiOperation(value = "List all books")
    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public List<BookVO> listAll() {
        List<BookVO> books = bookService.listAll();
        books.stream()
                .forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return books;

    }

    @ApiOperation(value = "Create a new book")
    @PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
            "application/json", "application/xml", "application/x-yaml" })
    public BookVO create(@RequestBody BookVO person) {
        BookVO bookVO = bookService.create(person);
        bookVO.add(linkTo(methodOn(PersonController.class).findById(bookVO.getKey())).withSelfRel());
        return bookVO;
    }

    @ApiOperation(value = "Update a specific book")
    @PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public BookVO update(@RequestBody BookVO book) {
        BookVO bookVO = bookService.update(book);
        bookVO.add(linkTo(methodOn(PersonController.class).findById(bookVO.getKey())).withSelfRel());
        return bookVO;
    }

    @ApiOperation(value = "Delete a specific book by your ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        bookService.delete(id);
        return ResponseEntity.ok().build();
    }

}
