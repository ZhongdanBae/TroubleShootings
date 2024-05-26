package npoprob.demo.controller;

import npoprob.demo.dto.AuthorDTO;
import npoprob.demo.entity.Author;
import npoprob.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // N+1 문제를 발생시키는 엔드포인트 -> 현재는 batchsize 설정이 되어있어 N+1 문제가 발생하지 않음
    @GetMapping("/nplusone")
    public List<AuthorDTO> getAllAuthorsWithNPlusOne() {
        return authorService.getAllAuthorsWithNPlusOne();
    }

    // N+1 문제를 해결한 엔드포인트 - Fetch Join 사용
    @GetMapping("/fetchjoin")
    public List<AuthorDTO> getAllAuthorsWithFetchJoin() {
        return authorService.getAllAuthorsWithFetchJoin();
    }

    // N+1 문제를 해결한 엔드포인트 - Batch Size 설정 사용
    @GetMapping("/batchsize")
    public List<AuthorDTO> getAllAuthorsWithBatchSize() {
        return authorService.getAllAuthorsWithBatchSize();
    }

    // N+1 문제를 해결한 엔드포인트 - EntityGraph 사용
    @GetMapping("/entitygraph")
    public List<AuthorDTO> getAllAuthorsWithEntityGraph() {
        return authorService.getAllAuthorsWithEntityGraph();
    }
}


