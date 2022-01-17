package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.model.Book;
import br.com.erudio.data.vo.BookVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.BookRepository;

// spring injeta as dependencias - n precisa dar new ou static;
@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public BookVO findByID(Long id) {
        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no records found for this id"));

        return DozerConverter.parseObject(entity, BookVO.class);
    }

    public List<BookVO> listAll() {
        List<BookVO> entities = DozerConverter.parseListObjects(bookRepository.findAll(), BookVO.class);
        return entities;
    }

    public BookVO create(BookVO person) {
        var entity = DozerConverter.parseObject(person, Book.class);
        var vo = DozerConverter.parseObject(bookRepository.save(entity), BookVO.class);
        return vo;
    }

    public BookVO update(BookVO person) {
        var entity = bookRepository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("no records found for this id"));

        entity.setAuthor(person.getAuthor());
        entity.setLaunchDate(person.getLaunchDate());
        entity.setPrice(person.getPrice());
        entity.setTitle(person.getTitle());
        var vo = DozerConverter.parseObject(bookRepository.save(entity), BookVO.class);
        return vo;

    }

    public void delete(Long id) {
        Book entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no records found for this id"));
        bookRepository.delete(entity);
    }
}
