package com.example.bookingg.controller.rest;


import com.example.bookingg.service.bookService.BookService;
import com.example.bookingg.service.bookService.request.BookSaveRequest;
import com.example.bookingg.service.bookService.response.BookDetailResponse;
import com.example.bookingg.service.bookService.response.BookListResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/books")
@AllArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @PostMapping
    public void create(@RequestBody BookSaveRequest request) {
        bookService.create(request);
    }

    //    @GetMapping
//    public ResponseEntity<List<RoomListResponse>> list() {
//        return new ResponseEntity<>(roomService.getAll(), HttpStatus.OK);
//    }
    @GetMapping
    public ResponseEntity<Page<BookListResponse>> list(@PageableDefault(size = 5) Pageable pageable,
                                                       @RequestParam(defaultValue = "") String search,
                                                       @RequestParam(defaultValue = "1") BigDecimal min,
                                                       @RequestParam(defaultValue = "500000000000000000") BigDecimal max
                                                       ) {
        return new ResponseEntity<>(bookService.getAll(search,pageable, min,max), HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long bookId) {
        bookService.delete(bookId);
        return ResponseEntity.ok("Room deleted successfully");
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDetailResponse> findById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid BookSaveRequest request, @PathVariable Long id)  {
        bookService.update(request, id);
        return ResponseEntity.noContent().build();
    }
//    @GetMapping("/search/{min}/{max}")
//    public ResponseEntity<List<RoomListResponse>> searchRooms(
//            @PageableDefault(size = 5) Pageable pageable,
//            @PathVariable BigDecimal min,
//            @PathVariable BigDecimal max
//    ) {
//            List<RoomListResponse> rooms = roomService.searchByPrice(pageable, min , max);
//            return new ResponseEntity<>(rooms, HttpStatus.OK);
//
//    }
}
