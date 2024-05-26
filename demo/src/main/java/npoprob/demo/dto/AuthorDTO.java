package npoprob.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AuthorDTO {
    private Long id;
    private String name;
    private List<BookDTO> books;
}
